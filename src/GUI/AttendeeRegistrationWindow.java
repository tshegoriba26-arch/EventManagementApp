package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
import models.AttendeeManager;
import db.DatabaseConnection;

public class AttendeeRegistrationWindow extends JFrame {

    private JTextField txtAttendeeId, txtAttendeeName, txtEmail, txtContact;
    private JComboBox<Integer> eventDropdown;

    public AttendeeRegistrationWindow() {
        setTitle("Attendee Registration");
        setSize(550, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(255, 228, 225)); // Light pink background

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(255, 105, 180)); // Hot pink
        headerPanel.setBounds(0, 0, 550, 70);
        headerPanel.setLayout(new FlowLayout());

        JLabel lblHeader = new JLabel("Attendee Registration");
        lblHeader.setForeground(Color.WHITE);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 28));
        headerPanel.add(lblHeader);
        add(headerPanel);

        // Main Content Panel
        JPanel panel = new JPanel();
        panel.setBounds(25, 90, 480, 290);
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(new Color(255, 105, 180), 2)); // Pink border
        add(panel);

        // Attendee ID
        JLabel lblAttendeeId = createLabel("Attendee ID:", 30, 20);
        panel.add(lblAttendeeId);

        txtAttendeeId = createTextField(150, 20);
        panel.add(txtAttendeeId);

        // Name
        JLabel lblAttendeeName = createLabel("Name:", 30, 60);
        panel.add(lblAttendeeName);

        txtAttendeeName = createTextField(150, 60);
        panel.add(txtAttendeeName);

        // Email
        JLabel lblEmail = createLabel("Email:", 30, 100);
        panel.add(lblEmail);

        txtEmail = createTextField(150, 100);
        panel.add(txtEmail);

        // Contact
        JLabel lblContact = createLabel("Contact:", 30, 140);
        panel.add(lblContact);

        txtContact = createTextField(150, 140);
        panel.add(txtContact);

        // Select Event
        JLabel lblEvent = createLabel("Select Event:", 30, 180);
        panel.add(lblEvent);

        eventDropdown = new JComboBox<>();
        eventDropdown.setBounds(150, 180, 250, 30);
        eventDropdown.setFont(new Font("SansSerif", Font.PLAIN, 14));
        eventDropdown.setBorder(BorderFactory.createLineBorder(new Color(255, 105, 180))); // Pink border
        loadEventDropdown();
        panel.add(eventDropdown);

        // Buttons
        JButton btnRegister = createButton("Register", 30, 230);
        JButton btnEdit = createButton("Edit", 160, 230);
        JButton btnDelete = createButton("Delete", 290, 230);

        panel.add(btnRegister);
        panel.add(btnEdit);
        panel.add(btnDelete);

        // Action listeners for buttons
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = txtAttendeeName.getText();
                String email = txtEmail.getText();
                String contact = txtContact.getText();
                int eventId = (Integer) eventDropdown.getSelectedItem();

                new AttendeeManager().registerAttendee(name, email, contact, eventId);
                JOptionPane.showMessageDialog(null, "Attendee Registered Successfully!");
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String attendeeIdStr = txtAttendeeId.getText();
                if (attendeeIdStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the Attendee ID to edit.");
                    return;
                }

                int attendeeId = Integer.parseInt(attendeeIdStr);
                String name = txtAttendeeName.getText();
                String email = txtEmail.getText();
                String contact = txtContact.getText();

                new AttendeeManager().updateAttendee(attendeeId, name, email, contact);
                JOptionPane.showMessageDialog(null, "Attendee Updated Successfully!");
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String attendeeIdStr = txtAttendeeId.getText();
                if (attendeeIdStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the Attendee ID to delete.");
                    return;
                }

                int attendeeId = Integer.parseInt(attendeeIdStr);
                new AttendeeManager().deleteAttendee(attendeeId);
                JOptionPane.showMessageDialog(null, "Attendee Deleted Successfully!");
            }
        });

        setVisible(true);
    }

    private void loadEventDropdown() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT event_id FROM events")) {

            while (rs.next()) {
                eventDropdown.addItem(rs.getInt("event_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 120, 30);
        label.setFont(new Font("SansSerif", Font.BOLD, 16));
        label.setForeground(new Color(255, 105, 180)); // Pink text
        return label;
    }

    private JTextField createTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 250, 30);
        textField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createLineBorder(new Color(255, 105, 180))); // Pink border
        return textField;
    }

    private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 100, 30);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(255, 105, 180)); // Pink background
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(255, 105, 180)));

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(255, 182, 193)); // Lighter pink on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(255, 105, 180)); // Original pink
            }
        });

        return button;
    }

    public static void main(String[] args) {
        new AttendeeRegistrationWindow();
    }
}
