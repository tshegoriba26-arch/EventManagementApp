package main;

import GUI.AttendeeRegistrationWindow;
import GUI.EventManagementWindow;
import GUI.VenueManagementWindow;
import gui.OverviewWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventManagementApp extends JFrame {

    public EventManagementApp() {
        setTitle("Event Management System");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(255, 240, 245)); // Light pink background

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(255, 105, 180)); // Hot pink
        headerPanel.setBounds(0, 0, 500, 80);
        headerPanel.setLayout(new FlowLayout());

        JLabel lblHeader = new JLabel("Event Management System");
        lblHeader.setForeground(Color.WHITE);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 28));
        headerPanel.add(lblHeader);
        add(headerPanel);

        // Buttons
        JButton btnEventManagement = createStyledButton("Event Management");
        btnEventManagement.setBounds(150, 120, 200, 50);
        add(btnEventManagement);

        JButton btnAttendeeRegistration = createStyledButton("Attendee Registration");
        btnAttendeeRegistration.setBounds(150, 190, 200, 50);
        add(btnAttendeeRegistration);

        JButton btnVenueManagement = createStyledButton("Venue Management");
        btnVenueManagement.setBounds(150, 260, 200, 50);
        add(btnVenueManagement);

        JButton btnOverview = createStyledButton("Overview");
        btnOverview.setBounds(150, 330, 200, 50);
        add(btnOverview);

        // Action listeners for buttons
        btnEventManagement.addActionListener(e -> new EventManagementWindow());
        btnAttendeeRegistration.addActionListener(e -> new AttendeeRegistrationWindow());
        btnVenueManagement.addActionListener(e -> new VenueManagementWindow());
        btnOverview.addActionListener(e -> new OverviewWindow());

        setVisible(true);
    }

    // Utility method to create a styled button
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(255, 105, 180)); // Hot pink background
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

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

    public static void main(String[] args) {
        new EventManagementApp();
    }
}
