package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import models.VenueManager;

public class VenueManagementWindow extends JFrame {

    private JTextField txtVenueId, txtVenueName, txtAddress, txtCapacity;

    public VenueManagementWindow() {
        setTitle("Venue Management");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(255, 240, 245)); // Light pink background

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(255, 105, 180)); // Hot pink
        headerPanel.setBounds(0, 0, 500, 70);
        headerPanel.setLayout(new FlowLayout());

        JLabel lblHeader = new JLabel("Manage Venues");
        lblHeader.setForeground(Color.WHITE);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 28));
        headerPanel.add(lblHeader);
        add(headerPanel);

        // Main Panel
        JPanel panel = new JPanel();
        panel.setBounds(25, 90, 450, 300);
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(new Color(255, 105, 180), 2)); // Pink border
        add(panel);

        // Venue ID
        JLabel lblVenueId = createLabel("Venue ID:", 30, 20);
        panel.add(lblVenueId);

        txtVenueId = createTextField(150, 20);
        panel.add(txtVenueId);

        // Venue Name
        JLabel lblVenueName = createLabel("Venue Name:", 30, 70);
        panel.add(lblVenueName);

        txtVenueName = createTextField(150, 70);
        panel.add(txtVenueName);

        // Address
        JLabel lblAddress = createLabel("Address:", 30, 120);
        panel.add(lblAddress);

        txtAddress = createTextField(150, 120);
        panel.add(txtAddress);

        // Capacity
        JLabel lblCapacity = createLabel("Capacity:", 30, 170);
        panel.add(lblCapacity);

        txtCapacity = createTextField(150, 170);
        panel.add(txtCapacity);

        // Buttons
        JButton btnAddVenue = createButton("Add Venue", 30, 220);
        JButton btnEditVenue = createButton("Edit Venue", 160, 220);
        JButton btnDeleteVenue = createButton("Delete Venue", 290, 220);

        panel.add(btnAddVenue);
        panel.add(btnEditVenue);
        panel.add(btnDeleteVenue);

        // Action listeners for buttons
        btnAddVenue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = txtVenueName.getText();
                String address = txtAddress.getText();
                int capacity = Integer.parseInt(txtCapacity.getText());

                new VenueManager().addVenue(name, address, capacity, true);
                JOptionPane.showMessageDialog(null, "Venue Added Successfully!");
            }
        });

        btnEditVenue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String venueIdStr = txtVenueId.getText();
                if (venueIdStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the Venue ID to edit.");
                    return;
                }

                int venueId = Integer.parseInt(venueIdStr);
                String name = txtVenueName.getText();
                String address = txtAddress.getText();
                int capacity = Integer.parseInt(txtCapacity.getText());

                new VenueManager().editVenue(venueId, name, address, capacity, true);
                JOptionPane.showMessageDialog(null, "Venue Updated Successfully!");
            }
        });

        btnDeleteVenue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String venueIdStr = txtVenueId.getText();
                if (venueIdStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the Venue ID to delete.");
                    return;
                }

                int venueId = Integer.parseInt(venueIdStr);
                new VenueManager().deleteVenue(venueId);
                JOptionPane.showMessageDialog(null, "Venue Deleted Successfully!");
            }
        });

        setVisible(true);
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 120, 30);
        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        label.setForeground(new Color(255, 105, 180)); // Hot pink
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
        button.setBackground(new Color(255, 105, 180)); // Hot pink background
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(255, 105, 180))); // Pink border

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(255, 182, 193)); // Lighter pink on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(255, 105, 180)); // Original hot pink
            }
        });

        return button;
    }

    public static void main(String[] args) {
        new VenueManagementWindow();
    }
}
