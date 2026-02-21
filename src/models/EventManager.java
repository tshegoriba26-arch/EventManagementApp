/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;


import db.DatabaseConnection;

import java.sql.*;

public class EventManager {

    
    public void createEvent(String name, java.sql.Date date, java.sql.Time time, String description, String organiser) {
        String query = "INSERT INTO Events (event_name, event_date, event_time, description, organiser_name) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, name);
            stmt.setDate(2, date);
            stmt.setTime(3, time);
            stmt.setString(4, description);
            stmt.setString(5, organiser);
            stmt.executeUpdate(); 
            
            System.out.println("Event created successfully.");
        } catch (SQLException e) {
            System.err.println("Error creating event: " + e.getMessage());
        }
    }

    
    public void editEvent(int eventId, String name, java.sql.Date date, java.sql.Time time, String description, String organiser) {
        String query = "UPDATE Events SET event_name=?, event_date=?, event_time=?, description=?, organiser_name=? WHERE event_id=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, name);
            stmt.setDate(2, date);
            stmt.setTime(3, time);
            stmt.setString(4, description);
            stmt.setString(5, organiser);
            stmt.setInt(6, eventId);
            int rowsUpdated = stmt.executeUpdate(); 
            
            if (rowsUpdated > 0) {
                System.out.println("Event updated successfully.");
            } else {
                System.out.println("No event found with the given ID.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating event: " + e.getMessage());
        }
    }

    
    public void deleteEvent(int eventId) {
        String query = "DELETE FROM Events WHERE event_id=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, eventId);
            int rowsDeleted = stmt.executeUpdate(); 
            
            if (rowsDeleted > 0) {
                System.out.println("Event deleted successfully.");
            } else {
                System.out.println("No event found with the given ID.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting event: " + e.getMessage());
        }
    }

    
    public ResultSet getAllEvents() {
        String query = "SELECT * FROM Events";
        ResultSet rs = null;
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery(); 
        } catch (SQLException e) {
            System.err.println("Error fetching events: " + e.getMessage());
        }
        return rs; 
    }
}

