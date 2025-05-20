package com.mcdade.payapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.mcdade.payapp.model.Employee;

public class EmployeeDAO {

    private static Map<Integer, String> cachedRateMap = null;

    public List<Employee> getEmployeesByStatus(boolean isActive, boolean excludeCorporate) {
        long start = System.currentTimeMillis();
        List<Employee> employees = new ArrayList<>();

        Map<Integer, String> titleRateMap = null;
        if (excludeCorporate) {
            if (cachedRateMap == null) {
                cachedRateMap = new TitleDAO().getAllTitleRateTypes();
                System.out.println("✅ Cached title-to-rate map loaded");
            }
            titleRateMap = cachedRateMap;
        }

        String query = "SELECT EmployeeNumber, EmployeeFirstName, EmployeeLastName, Position, TitleID, PrimaryPhone, SecondaryPhone " +
                    "FROM EmployeesT WHERE IsActive = ? ORDER BY EmployeeFirstName ASC";

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
        ) {
            stmt.setFetchSize(200);
            stmt.setInt(1, isActive ? 1 : 0);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int titleId = rs.getInt("TitleID");

                if (excludeCorporate) {
                    String rateType = titleRateMap.get(titleId);
                    if ("Corporate".equalsIgnoreCase(rateType)) continue;
                }

                String employeeNumber = clean(rs.getString("EmployeeNumber"));
                String first = rs.getString("EmployeeFirstName");
                String last = rs.getString("EmployeeLastName");
                String position = clean(rs.getString("Position"));

                String phone = clean(rs.getString("PrimaryPhone"));
                if (phone.isEmpty()) {
                    phone = clean(rs.getString("SecondaryPhone"));
                }

                Employee emp = new Employee(employeeNumber, first, last, position, phone);
                employees.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Query time: " + (System.currentTimeMillis() - start) + "ms");
        return employees;
    }

    public List<Employee> getCorporateEmployees() {
        List<Employee> corporateList = new ArrayList<>();

        String query = "SELECT E.EmployeeNumber, E.EmployeeFirstName, E.EmployeeLastName, E.Position, " +
               "E.PrimaryPhone, E.SecondaryPhone " +
               "FROM EmployeesT E " +
               "JOIN TitleT T ON E.TitleID = T.TitleID " +
               "JOIN RateT R ON T.RateID = R.ID " +
               "WHERE E.IsActive = 1 AND R.RateType = 'Corporate' " +
               "ORDER BY E.EmployeeFirstName ASC";

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
        ) {
            stmt.setFetchSize(200);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String employeeNumber = clean(rs.getString("EmployeeNumber"));
                String first = rs.getString("EmployeeFirstName");
                String last = rs.getString("EmployeeLastName");
                String position = clean(rs.getString("Position"));

                String phone = clean(rs.getString("PrimaryPhone"));
                if (phone.isEmpty()) {
                    phone = clean(rs.getString("SecondaryPhone"));
                }

                Employee emp = new Employee(employeeNumber, first, last, position, phone);
                corporateList.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return corporateList;
    }

    private String clean(String value) {
        return value == null ? "" : value.replace("\"", "").trim();
    }

    public Employee getEmployeeDetailsByName(String firstName, String lastName) {
        String query = "SELECT E.*, T.TitleName " +
                    "FROM EmployeesT E " +
                    "LEFT JOIN TitleT T ON E.TitleID = T.TitleID " +
                    "WHERE E.EmployeeFirstName = ? AND E.EmployeeLastName = ?";

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int employeeId = rs.getInt("EmployeeID");
                String position = rs.getString("Position");
                String title = rs.getString("TitleName");
                String license = rs.getString("LicenseNumber");
                String el1 = rs.getString("EL1Number");
                String workPhone = rs.getString("PrimaryPhone");
                String personalPhone = rs.getString("SecondaryPhone");
                String hireDate = rs.getString("HireDate");
                String birthDate = rs.getString("BirthDate");
                String employeeNumber = rs.getString("EmployeeNumber");
                boolean isActive = rs.getInt("IsActive") == 1;

                return new Employee(employeeId, employeeNumber, firstName, lastName, position, personalPhone, title, license, el1, workPhone, hireDate, birthDate, isActive);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateEmployee(Employee emp) throws Exception {
        String query =
            "UPDATE E " +
            "SET E.EmployeeFirstName = ?, " +
            "    E.EmployeeLastName = ?, " +
            "    E.Position = T.Position, " +
            "    E.TitleID = T.TitleID, " +
            "    E.LicenseNumber = ?, " +
            "    E.EL1Number = ?, " +
            "    E.PrimaryPhone = ?, " +
            "    E.SecondaryPhone = ?, " +
            "    E.HireDate = ?, " +
            "    E.BirthDate = ?, " +
            "    E.IsActive = ? " +
            "FROM EmployeesT E " +
            "JOIN TitleT T ON T.TitleName = ? " +
            "WHERE E.EmployeeID = ?";

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setString(1, emp.getFirstName());
            stmt.setString(2, emp.getLastName());
            stmt.setString(3, emp.getLicenseNumber());
            stmt.setString(4, emp.getEl1Number());
            stmt.setString(5, emp.getWorkPhone());
            stmt.setString(6, emp.getPhone());

            if (emp.getHireDate() != null && !emp.getHireDate().trim().isEmpty()) {
                stmt.setDate(7, java.sql.Date.valueOf(formatSafely(emp.getHireDate())));
            } else {
                stmt.setNull(7, java.sql.Types.DATE);
            }

            if (emp.getBirthDate() != null && !emp.getBirthDate().trim().isEmpty()) {
                stmt.setDate(8, java.sql.Date.valueOf(formatSafely(emp.getBirthDate())));
            } else {
                stmt.setNull(8, java.sql.Types.DATE);
            }

            stmt.setBoolean(9, emp.isActive());
            stmt.setString(10, emp.getTitle());
            stmt.setInt(11, emp.getEmployeeId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public List<String> getAllPositions() {
        List<String> positions = new ArrayList<>();
        String query = "SELECT DISTINCT Position FROM EmployeesT WHERE Position IS NOT NULL AND Position <> ''";
        try (
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)
        ) {
            while (rs.next()) {
                positions.add(rs.getString("Position"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return positions;
    }

    private String formatSafely(String input) {
        DateTimeFormatter[] acceptedFormats = new DateTimeFormatter[] {
            DateTimeFormatter.ofPattern("M/d/yyyy[ H:mm:ss]"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd[ H:mm:ss]"),
        };

        for (DateTimeFormatter fmt : acceptedFormats) {
            try {
                LocalDate parsed = LocalDate.parse(input.trim(), fmt);
                return parsed.toString();
            } catch (Exception ignored) {}
        }
        throw new IllegalArgumentException("Invalid date format: " + input);
    }

    public boolean createEmployee(Employee emp) throws Exception {
        String query =
            "INSERT INTO EmployeesT (EmployeeNumber, EmployeeFirstName, EmployeeLastName, " +
            "Position, TitleID, LicenseNumber, EL1Number, PrimaryPhone, SecondaryPhone, HireDate, BirthDate, IsActive) " +
            "SELECT ?, ?, ?, T.Position, T.TitleID, ?, ?, ?, ?, ?, ?, ? FROM TitleT T WHERE T.TitleName = ?";

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setString(1, emp.getEmployeeNumber());
            stmt.setString(2, emp.getFirstName());
            stmt.setString(3, emp.getLastName());
            stmt.setString(4, emp.getLicenseNumber());
            stmt.setString(5, emp.getEl1Number());
            stmt.setString(6, emp.getWorkPhone());
            stmt.setString(7, emp.getPhone());

            if (emp.getHireDate() != null && !emp.getHireDate().trim().isEmpty()) {
                stmt.setDate(8, java.sql.Date.valueOf(formatSafely(emp.getHireDate())));
            } else {
                stmt.setNull(8, java.sql.Types.DATE);
            }

            if (emp.getBirthDate() != null && !emp.getBirthDate().trim().isEmpty()) {
                stmt.setDate(9, java.sql.Date.valueOf(formatSafely(emp.getBirthDate())));
            } else {
                stmt.setNull(9, java.sql.Types.DATE);
            }

            stmt.setBoolean(10, emp.isActive());
            stmt.setString(11, emp.getTitle());  // Used to match TitleName in subquery

            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }


} 
