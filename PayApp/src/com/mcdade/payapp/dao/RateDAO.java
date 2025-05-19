package com.mcdade.payapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RateDAO {

    public String getRateTypeById(int titleId) {
        String rateType = null;
        String query = "SELECT RateType FROM RateT WHERE ID = ?";

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setInt(1, titleId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                rateType = rs.getString("RateType");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rateType;
    }

    public String getRateTypeByTitle(String position) {
        String rateType = null;
        String query = "SELECT RateType FROM RateT WHERE Title = ?";

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setString(1, position);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                rateType = rs.getString("RateType");
                if (rateType != null) {
                    rateType = rateType.trim();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rateType;
    }



}
