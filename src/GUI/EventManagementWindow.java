package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.EventManager;

public class EventManagementWindow extends JFrame {

    private JTextField txtEventId, txtEventName, txtEventDate, txtEventTime, txtDescription, txtOrganiser;
    private EventManager eventManager;

    public EventManagementWindow() {
        eventManager = new EventManager();
        
        setTitle("Event Management");
        setSize(500, 500); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(255, 240, 245)); // Light pink background

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(255, 105, 180)); // Hot Pink
        headerPanel.setBounds(0, 0, 500, 70);
        headerPanel.setLayout(new FlowLayout());

        JLabel lblHeader = new JLabel("Manage Events");
        lblHeader.setForeground(Color.WHITE);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 28));
        headerPanel.add(lblHeader);
        add(headerPanel);

        // Main Panel
        JPanel panel = new JPanel();
        panel.setBounds(25, 90, 450, 320);
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(new Color(255, 105, 180), 2)); // Pink border
        add(panel);

        // Event ID
        JLabel lblEventId = createLabel("Event ID:", 30, 20);
        panel.add(lblEventId);

        txtEventId = createTextField(180, 20);
        panel.add(txtEventId);

        // Event Name
        JLabel lblEventName = createLabel("Event Name:", 30, 60);
        panel.add(lblEventName);

        txtEventName = createTextField(180, 60);
        panel.add(txtEventName);

        // Event Date
        JLabel lblEventDate = createLabel("Event Date (yyyy-MM-dd):", 30, 100);
        panel.add(lblEventDate);

        txtEventDate = createTextField(180, 100);
        panel.add(txtEventDate);

        // Event Time
        JLabel lblEventTime = createLabel("Event Time (HH:mm:ss):", 30, 140);
        panel.add(lblEventTime);

        txtEventTime = createTextField(180, 140);
        panel.add(txtEventTime);

        // Description
        JLabel lblDescription = createLabel("Description:", 30, 180);
        panel.add(lblDescription);

        txtDescription = createTextField(180, 180);
        panel.add(txtDescription);

        // Organiser
        JLabel lblOrganiser = createLabel("Organiser:", 30, 220);
        panel.add(lblOrganiser);

        txtOrganiser = createTextField(180, 220);
        panel.add(txtOrganiser);

        // Buttons
        JButton btnCreate = createButton("Create Event", 30, 270);
        JButton btnEdit = createButton("Edit Event", 160, 270);
        JButton btnDelete = createButton("Delete Event", 290, 270);

        panel.add(btnCreate);
        panel.add(btnEdit);
        panel.add(btnDelete);

        // Action listeners for buttons
        btnCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String eventName = txtEventName.getText();
                String eventDate = txtEventDate.getText();
                String eventTime = txtEventTime.getText();
                String description = txtDescription.getText();
                String organiser = txtOrganiser.getText();

                try {
                    java.sql.Date sqlDate = java.sql.Date.valueOf(eventDate);
                    java.sql.Time sqlTime = java.sql.Time.valueOf(eventTime);
                    eventManager.createEvent(eventName, sqlDate, sqlTime, description, organiser);
                    JOptionPane.showMessageDialog(null, "Event Created Successfully!");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid date or time format.");
                }
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String eventId = txtEventId.getText();
                String eventName = txtEventName.getText();
                String eventDate = txtEventDate.getText();
                String eventTime = txtEventTime.getText();
                String description = txtDescription.getText();
                String organiser = txtOrganiser.getText();

                try {
                    java.sql.Date sqlDate = java.sql.Date.valueOf(eventDate);
                    java.sql.Time sqlTime = java.sql.Time.valueOf(eventTime);
                    eventManager.editEvent(Integer.parseInt(eventId), eventName, sqlDate, sqlTime, description, organiser);
                    JOptionPane.showMessageDialog(null, "Event Edited Successfully!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Event ID.");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid date or time format.");
                }
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String eventId = txtEventId.getText();

                try {
                    eventManager.deleteEvent(Integer.parseInt(eventId));
                    JOptionPane.showMessageDialog(null, "Event Deleted Successfully!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Event ID.");
                }
            }
        });

        setVisible(true);
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 180, 30);
        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        label.setForeground(new Color(255, 105, 180)); // Pink color for labels
        return label;
    }

    private JTextField createTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 200, 30);
        textField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createLineBorder(new Color(255, 105, 180))); // Pink border
        return textField;
    }

    private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 120, 30);
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
        new EventManagementWindow();
    }
}
