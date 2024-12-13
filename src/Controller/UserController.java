package Controller;

import Model.Users;
import java.sql.*;

public class UserController {
    public Users login(String email, String password) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                 "SELECT id, name, email FROM users WHERE email = ? AND password = ?")) {
            
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Users(
                    rs.getInt("id"), 
                    rs.getString("name"), 
                    rs.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}