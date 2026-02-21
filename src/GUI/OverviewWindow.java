package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.EventManager;
import models.VenueManager;
import models.AttendeeManager;

public class OverviewWindow extends JFrame {

    private JTable eventTable, venueTable, attendeeTable;
    private EventManager eventManager;
    private VenueManager venueManager;
    private AttendeeManager attendeeManager;

    public OverviewWindow() {
        eventManager = new EventManager();
        venueManager = new VenueManager();
        attendeeManager = new AttendeeManager();

        // Set up the window
        setTitle("Overview: Events, Venues, and Attendees");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // Create background panel with light pink
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBackground(new Color(255, 240, 245)); // Light pink background
        backgroundPanel.setBounds(0, 0, 1000, 700);
        backgroundPanel.setLayout(null);
        add(backgroundPanel);

        // Header
        JLabel header = new JLabel("Events, Venues, and Attendees Overview", JLabel.CENTER);
        header.setBounds(50, 20, 900, 50);
        header.setFont(new Font("Arial", Font.BOLD, 30));
        header.setForeground(new Color(255, 105, 180)); // Hot pink
        backgroundPanel.add(header);

        // Create buttons for viewing events, venues, and attendees
        JButton btnViewEvents = createStyledButton("View Events", 50, 80);
        backgroundPanel.add(btnViewEvents);

        JButton btnViewVenues = createStyledButton("View Venues", 250, 80);
        backgroundPanel.add(btnViewVenues);

        JButton btnViewAttendees = createStyledButton("View Attendees", 450, 80);
        backgroundPanel.add(btnViewAttendees);

        // Tables to display events, venues, and attendees
        eventTable = createStyledTable();
        JScrollPane eventScrollPane = new JScrollPane(eventTable);
        eventScrollPane.setBounds(50, 140, 850, 150);
        backgroundPanel.add(eventScrollPane);

        venueTable = createStyledTable();
        JScrollPane venueScrollPane = new JScrollPane(venueTable);
        venueScrollPane.setBounds(50, 310, 850, 150);
        backgroundPanel.add(venueScrollPane);

        attendeeTable = createStyledTable();
        JScrollPane attendeeScrollPane = new JScrollPane(attendeeTable);
        attendeeScrollPane.setBounds(50, 480, 850, 150);
        backgroundPanel.add(attendeeScrollPane);

        // Action listeners for viewing data
        btnViewEvents.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadEventTable();
            }
        });

        btnViewVenues.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadVenueTable();
            }
        });

        btnViewAttendees.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadAttendeeTable();
            }
        });

        setVisible(true);
    }

    // Method to load events into JTable
    private void loadEventTable() {
        ResultSet rs = eventManager.getAllEvents();
        DefaultTableModel model = new DefaultTableModel(new String[]{"Event ID", "Name", "Date", "Time", "Description", "Organiser"}, 0);
        try {
            while (rs != null && rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("event_id"),
                    rs.getString("event_name"),
                    rs.getDate("event_date"),
                    rs.getTime("event_time"),
                    rs.getString("description"),
                    rs.getString("organiser_name")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        eventTable.setModel(model); // Set the model for the event table
    }

    // Method to load venues into JTable
    private void loadVenueTable() {
        ResultSet rs = venueManager.getAllVenues();
        DefaultTableModel model = new DefaultTableModel(new String[]{"Venue ID", "Name", "Address", "Capacity", "Availability"}, 0);
        try {
            while (rs != null && rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("venue_id"),
                    rs.getString("venue_name"),
                    rs.getString("address"),
                    rs.getInt("capacity"),
                    rs.getBoolean("availability")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        venueTable.setModel(model); // Set the model for the venue table
    }

    // Method to load attendees into JTable
    private void loadAttendeeTable() {
        ResultSet rs = attendeeManager.getAllAttendees();
        DefaultTableModel model = new DefaultTableModel(new String[]{"Attendee ID", "Name", "Email", "Contact", "Event ID"}, 0);
        try {
            while (rs != null && rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("attendee_id"),
                    rs.getString("attendee_name"),
                    rs.getString("email"),
                    rs.getString("contact_number"),
                    rs.getInt("event_id")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        attendeeTable.setModel(model); // Set the model for the attendee table
    }

    // Utility method to create a styled button
    private JButton createStyledButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 150, 40);
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(255, 105, 180)); // Hot pink
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());

        // Hover effect for buttons
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(255, 182, 193)); // Light pink on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(255, 105, 180)); // Original hot pink
            }
        });
        return button;
    }

    // Utility method to create a styled JTable
    private JTable createStyledTable() {
        JTable table = new JTable();
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(255, 105, 180)); // Hot pink header
        table.getTableHeader().setForeground(Color.WHITE);
        return table;
    }

    public static void main(String[] args) {
        new OverviewWindow(); // Launch the overview window
    }
}
