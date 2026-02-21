package models;

import db.DatabaseConnection;

import java.sql.*;

public class VenueManager {

    
    public void addVenue(String name, String address, int capacity, boolean availability) {
        String query = "INSERT INTO Venues (venue_name, address, capacity, availability) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setInt(3, capacity);
            stmt.setBoolean(4, availability);
            stmt.executeUpdate(); 

            System.out.println("Venue added successfully.");
        } catch (SQLException e) {
            System.err.println("Error adding venue: " + e.getMessage());
        }
    }

    
    public void editVenue(int venueId, String name, String address, int capacity, boolean availability) {
        String query = "UPDATE Venues SET venue_name=?, address=?, capacity=?, availability=? WHERE venue_id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setInt(3, capacity);
            stmt.setBoolean(4, availability);
            stmt.setInt(5, venueId);
            int rowsUpdated = stmt.executeUpdate(); 

            if (rowsUpdated > 0) {
                System.out.println("Venue updated successfully.");
            } else {
                System.out.println("No venue found with the given ID.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating venue: " + e.getMessage());
        }
    }

    
    public void deleteVenue(int venueId) {
        String query = "DELETE FROM Venues WHERE venue_id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            
            stmt.setInt(1, venueId);
            int rowsDeleted = stmt.executeUpdate(); 

            if (rowsDeleted > 0) {
                System.out.println("Venue deleted successfully.");
            } else {
                System.out.println("No venue found with the given ID.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting venue: " + e.getMessage());
        }
    }

    
    public ResultSet getAllVenues() {
        String query = "SELECT * FROM Venues";
        ResultSet rs = null;
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery(); 
        } catch (SQLException e) {
            System.err.println("Error fetching venues: " + e.getMessage());
        }
        return rs; 
    }
}
