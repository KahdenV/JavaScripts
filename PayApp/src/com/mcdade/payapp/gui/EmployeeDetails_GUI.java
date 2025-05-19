package com.mcdade.payapp.gui;

import javax.swing.*;
import java.awt.*;
import com.mcdade.payapp.dao.EmployeeDAO;
import com.mcdade.payapp.model.Employee;

public class EmployeeDetails_GUI extends JFrame {

    public static EmployeeDetails_GUI openInstance = null;

    public EmployeeDetails_GUI(String firstName, String lastName) {
        setTitle("Employee Details - " + firstName + " " + lastName);
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Ensure only one open
        if (openInstance != null) openInstance.dispose();
        openInstance = this;

        // Fetch by name instead of employee number
        EmployeeDAO dao = new EmployeeDAO();
        Employee emp = dao.getEmployeeDetailsByName(firstName, lastName);

        if (emp == null) {
            JOptionPane.showMessageDialog(this, "Employee not found.");
            dispose();
            return;
        }

        // Panel setup
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        panel.setBackground(new Color(235, 245, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.anchor = GridBagConstraints.WEST;

        int row = 0;
        addLabelAndValue(panel, "Employee Number:", emp.getEmployeeNumber(), row++, gbc);
        addLabelAndValue(panel, "Full Name:", emp.getFirstName() + " " + emp.getLastName(), row++, gbc);
        addLabelAndValue(panel, "Position:", emp.getPosition(), row++, gbc);
        addLabelAndValue(panel, "Title:", emp.getTitle(), row++, gbc);
        addLabelAndValue(panel, "License Number:", emp.getLicenseNumber(), row++, gbc);
        addLabelAndValue(panel, "EL1 Number:", emp.getEl1Number(), row++, gbc);
        addLabelAndValue(panel, "Work Phone:", emp.getWorkPhone(), row++, gbc);
        addLabelAndValue(panel, "Personal Phone:", emp.getPhone(), row++, gbc);
        addLabelAndValue(panel, "Hire Date:", emp.getHireDate(), row++, gbc);
        addLabelAndValue(panel, "Birth Date:", emp.getBirthDate(), row++, gbc);

        // Edit Button at bottom
        JButton editButton = new JButton("Edit Employee");
        editButton.setFocusable(false);
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(editButton, gbc);

        add(panel);
        setVisible(true);
    }

    private void addLabelAndValue(JPanel panel, String label, String value, int row, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = row;
        JLabel l = new JLabel(label);
        l.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(l, gbc);

        gbc.gridx = 1;
        JTextField field = new JTextField(value);
        field.setEditable(false);
        field.setBackground(new Color(235, 245, 255));
        field.setBorder(BorderFactory.createEmptyBorder());
        panel.add(field, gbc);
    }
} 
