package com.mcdade.payapp.gui;

import javax.swing.*;
import java.awt.*;

/**
 * This class creates the main menu GUI for the Pay App using Java Swing.
 * It includes sections for Employees, Invoices, and Payroll with corresponding buttons.
 */
public class MainMenu_GUI extends JFrame {

    public MainMenu_GUI() {
        // Set title of the window
        super("Main Menu");

        // Set the size of the window (width, height)
        setSize(600, 450);

        // Close the app when the window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Use BorderLayout to divide the top title and main button grid
        setLayout(new BorderLayout());

        // Top panel for the "Main Menu" title
        JLabel titleLabel = new JLabel("Main Menu", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(200, 230, 250));
        add(titleLabel, BorderLayout.NORTH);

        // Main panel to hold the button groups (Employees, Invoices, Payroll)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 0));
        buttonPanel.setBackground(new Color(70, 110, 160));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(buttonPanel, BorderLayout.CENTER);

        // === Employees Panel ===
        JPanel employeePanel = createPanel("Employees", new Color(173, 216, 230));
        employeePanel.add(createButton("Open Job List"));
        employeePanel.add(createButton("Employee List"));

        // === Invoices Panel ===
        JPanel invoicePanel = createPanel("Invoices", new Color(255, 228, 225));
        invoicePanel.add(createButton("New Invoices"));
        invoicePanel.add(createButton("Vendor List"));
        invoicePanel.add(createButton("Adder List"));

        // === Payroll Panel ===
        JPanel payrollPanel = createPanel("Payroll", new Color(144, 238, 144));
        payrollPanel.add(createButton("Material Categories"));
        payrollPanel.add(createButton("Composite Rate"));
        payrollPanel.add(createButton("GC Management"));
        payrollPanel.add(createButton("GC Management MOS"));

        // Add panels to main button container
        buttonPanel.add(employeePanel);
        buttonPanel.add(invoicePanel);
        buttonPanel.add(payrollPanel);

        // Bottom exit button
        JButton exitButton = new JButton("❌");
        exitButton.setPreferredSize(new Dimension(50, 50));
        exitButton.addActionListener(e -> System.exit(0));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(70, 110, 160));
        bottomPanel.add(exitButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Center the window on screen
        setLocationRelativeTo(null);

        // Make the window visible
        setVisible(true);
    }

    /**
     * Helper method to create a panel for each section with a title.
     */
    private JPanel createPanel(String title, Color bgColor) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(bgColor);
        panel.setBorder(BorderFactory.createTitledBorder(title));
        return panel;
    }

    /**
     * Helper method to create a styled button.
     */
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }
}