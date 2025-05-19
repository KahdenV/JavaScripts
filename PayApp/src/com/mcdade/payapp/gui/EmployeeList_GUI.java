package com.mcdade.payapp.gui;

import javax.swing.*;
import java.awt.*;
import com.mcdade.payapp.dao.EmployeeDAO;
import com.mcdade.payapp.model.Employee;
import java.util.List;

public class EmployeeList_GUI extends JFrame {

    private final String[] columnNames = { "ID", "Name", "Position", "Phone" };
    private final JTabbedPane tabs = new JTabbedPane();
    private final JLabel countLabel = new JLabel();
    private final EmployeeDAO dao = new EmployeeDAO();

    private List<Employee> activeList;
    private List<Employee> inactiveList;
    private List<Employee> corporateList;

    public EmployeeList_GUI() {
        super("Employee List");

        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title Label
        JLabel titleLabel = new JLabel("Current Employee List", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(200, 230, 250));
        add(titleLabel, BorderLayout.NORTH);

        // Buttons + Count Label
        JButton newButton = new JButton("New Employee");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(newButton);
        buttonPanel.add(countLabel);
        buttonPanel.setBackground(new Color(200, 230, 250));
        add(buttonPanel, BorderLayout.SOUTH);

        // Add Tabs
        add(tabs, BorderLayout.CENTER);

        // Load all data eagerly
        activeList = dao.getEmployeesByStatus(true, true);     // Exclude corporate
        inactiveList = dao.getEmployeesByStatus(false, false); // Include all
        corporateList = dao.getCorporateEmployees();           // Pre-joined

        addTab("Active Employees", activeList);
        addTab("Inactive Employees", inactiveList);
        addTab("Corporate Employees", corporateList);

        // Update count label on tab switch
        tabs.addChangeListener(e -> {
            int index = tabs.getSelectedIndex();
            if (index == 0) {
                countLabel.setText("Total Active Employees: " + activeList.size());
            } else if (index == 1) {
                countLabel.setText("Total Inactive Employees: " + inactiveList.size());
            } else {
                countLabel.setText("Total Corporate Employees: " + corporateList.size());
            }
        });

        // Show initial count
        countLabel.setText("Total Active Employees: " + activeList.size());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addTab(String title, List<Employee> list) {
        String[][] data = new String[list.size()][4];
        for (int i = 0; i < list.size(); i++) {
            Employee emp = list.get(i);
            data[i][0] = emp.getEmployeeNumber();
            data[i][1] = emp.getFirstName() + " " + emp.getLastName(); // full name for display
            data[i][2] = emp.getPosition();
            data[i][3] = emp.getPhone();
        }
        JTable table = new JTable(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JScrollPane scrollPane = new JScrollPane(table);
        tabs.addTab(title, scrollPane);

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    JTable source = (JTable) evt.getSource();
                    int row = source.rowAtPoint(evt.getPoint());
                    if (row < 0) return;

                    Employee selected = list.get(row); // use original list, not split string
                    String first = selected.getFirstName();
                    String last = selected.getLastName();

                    if (EmployeeDetails_GUI.openInstance != null) {
                        EmployeeDetails_GUI.openInstance.dispose();
                    }
                    new EmployeeDetails_GUI(first, last);
                }
            }
        });
    }
}
