package Controller;

import Model.Artworks;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtworkController {
    public List<Artworks> getAllArtworks() {
        List<Artworks> artworks = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                 "SELECT a.id, a.title, a.description, a.image_path, a.user_id, u.name as artist_name " +
                 "FROM artworks a JOIN users u ON a.user_id = u.id")) {
            
            while (rs.next()) {
                Artworks artwork = new Artworks(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getString("image_path"),
                    rs.getInt("user_id")
                );
                artwork.setArtistName(rs.getString("artist_name"));
                artworks.add(artwork);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return artworks;
    }

    public boolean addArtwork(String title, String description, int userId) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                 "INSERT INTO artworks (title, description, user_id) VALUES (?, ?, ?)")) {
            
            pstmt.setString(1, title);
            pstmt.setString(2, description);
            pstmt.setInt(4, userId);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}