package com.flight.dao;

import com.flight.bean.PassengerBean;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassengerDao {

    public static List<PassengerBean> getAllPassengers() {
        List<PassengerBean> list = new ArrayList<>();
        try (Connection con = DBConnection.getCon();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM passenger");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PassengerBean p = new PassengerBean();
                p.setPassengerId(rs.getString("PassengerID"));
                p.setName(rs.getString("Name"));
                p.setAge(rs.getInt("Age"));
                p.setGender(rs.getString("Gender"));
                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
