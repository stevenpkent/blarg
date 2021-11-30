package com.shopping.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {
    private static final Logger logger = Logger.getLogger(UserDAO.class.getName());

    public User getDetails(String userName) throws SQLException {
        User user = new User();

        try {
            Connection conn = H2DatabaseConnection.getConnectionToDatabase();
            PreparedStatement ps = conn.prepareStatement("select * from User where username = ?");
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
                user.setGender(rs.getString("gender"));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Could not execute query", ex);
        }

        return user;
    }
}
