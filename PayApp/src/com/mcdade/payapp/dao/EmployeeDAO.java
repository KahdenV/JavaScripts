package com.mcdade.payapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        String query = "SELECT EmployeeNumber, EmployeeFirstName, EmployeeLastName, Position, PrimaryPhone, SecondaryPhone " +
                       "FROM EmployeesT E " +
                       "JOIN TitleT T ON E.TitleID = T.TitleID " +
                       "JOIN RateT R ON T.RateID = R.ID " +
                       "WHERE E.IsActive = 1 AND R.RateType = 'Corporate' " +
                       "ORDER BY EmployeeFirstName ASC";

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

                String position = rs.getString("Position");
                String title = rs.getString("TitleName");
                String license = rs.getString("LicenseNumber");
                String el1 = rs.getString("EL1Number");
                String workPhone = rs.getString("PrimaryPhone");
                String personalPhone = rs.getString("SecondaryPhone");
                String hireDate = rs.getString("HireDate");
                String birthDate = rs.getString("BirthDate");
                String employeeNumber = rs.getString("EmployeeNumber"); // may be null

                return new Employee(employeeNumber, firstName, lastName, position, personalPhone, title, license, el1, workPhone, hireDate, birthDate);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
