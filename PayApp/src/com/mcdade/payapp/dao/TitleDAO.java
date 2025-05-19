package com.mcdade.payapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class TitleDAO {
    public Map<Integer, String> getAllTitleRateTypes() {
        Map<Integer, String> map = new HashMap<>();
        String query = "SELECT T.TitleID, R.RateType FROM TitleT T JOIN RateT R ON T.RateID = R.ID";

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
        ) {
            while (rs.next()) {
                int titleId = rs.getInt("TitleID");
                String rateType = rs.getString("RateType");
                map.put(titleId, rateType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }
}

