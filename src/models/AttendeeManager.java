
package models;


import db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AttendeeManager {

    
    public void registerAttendee(String name, String email, String contact, int eventId) {
        String query = "INSERT INTO Attendees (attendee_name, email, contact_number, event_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, contact);
            stmt.setInt(4, eventId);
            stmt.executeUpdate(); 
        } catch (SQLException e) {
            System.err.println("Error registering attendee: " + e.getMessage());
        }
    }

    public void updateAttendee(int attendeeId, String name, String email, String contact) {
        String query = "UPDATE Attendees SET attendee_name=?, email=?, contact_number=? WHERE attendee_id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, contact);
            stmt.setInt(4, attendeeId);
            int rowsUpdated = stmt.executeUpdate(); 
            
            if (rowsUpdated > 0) {
                System.out.println("Attendee updated successfully.");
            } else {
                System.out.println("No attendee found with the given ID.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating attendee: " + e.getMessage());
        }
    }

    
    public void deleteAttendee(int attendeeId) {
        String query = "DELETE FROM Attendees WHERE attendee_id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            
            stmt.setInt(1, attendeeId);
            int rowsDeleted = stmt.executeUpdate();
            
            if (rowsDeleted > 0) {
                System.out.println("Attendee deleted successfully.");
            } else {
                System.out.println("No attendee found with the given ID.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting attendee: " + e.getMessage());
        }
    }

    
     public ResultSet getAllAttendees() {
        String query = "SELECT * FROM Attendees";
        ResultSet rs = null;
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error fetching attendees: " + e.getMessage());
        }
        return rs;
    }
}
