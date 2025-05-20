package com.mcdade.payapp.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import com.mcdade.payapp.model.Employee;
import com.mcdade.payapp.dao.EmployeeDAO;
import com.mcdade.payapp.dao.TitleDAO;

public class EmployeeEdit_GUI extends JFrame {

    public EmployeeEdit_GUI(Employee emp, boolean isNew) {
        setTitle((isNew ? "New Employee" : ("Edit Employee - " + emp.getFirstName() + " " + emp.getLastName())));
        setSize(450, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        panel.setBackground(new Color(245, 250, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.anchor = GridBagConstraints.WEST;

        EmployeeDAO dao = new EmployeeDAO();
        TitleDAO titleDAO = new TitleDAO();

        int row = 0;

        if (!isNew) {
            JTextField idField = addLabelAndField(panel, "Employee ID:", String.valueOf(emp.getEmployeeId()), row++, gbc);
            idField.setEditable(false);
        }

        JTextField numberField = addLabelAndField(panel, "Employee Number:", emp.getEmployeeNumber(), row++, gbc);
        JTextField firstNameField = addLabelAndField(panel, "First Name:", emp.getFirstName(), row++, gbc);
        JTextField lastNameField = addLabelAndField(panel, "Last Name:", emp.getLastName(), row++, gbc);

        JLabel currentPosition = new JLabel(emp.getPosition());
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("Position:"), gbc);
        gbc.gridx = 1;
        panel.add(currentPosition, gbc);
        row++;

        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("Title:"), gbc);
        gbc.gridx = 1;
        JComboBox<String> titleBox = new JComboBox<>();
        List<String> titles = titleDAO.getAllTitleNames();
        for (String title : titles) {
            titleBox.addItem(title);
        }
        titleBox.setSelectedItem(emp.getTitle());
        panel.add(titleBox, gbc);
        row++;

        JTextField licenseField = addLabelAndField(panel, "License Number:", emp.getLicenseNumber(), row++, gbc);
        JTextField el1Field = addLabelAndField(panel, "EL1 Number:", emp.getEl1Number(), row++, gbc);
        JTextField workPhoneField = addLabelAndField(panel, "Work Phone:", emp.getWorkPhone(), row++, gbc);
        JTextField personalPhoneField = addLabelAndField(panel, "Personal Phone:", emp.getPhone(), row++, gbc);
        JTextField hireDateField = addLabelAndField(panel, "Hire Date:", emp.getHireDate(), row++, gbc);
        JTextField birthDateField = addLabelAndField(panel, "Birth Date:", emp.getBirthDate(), row++, gbc);

        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("Is Active:"), gbc);

        gbc.gridx = 1;
        JCheckBox isActiveBox = new JCheckBox();
        isActiveBox.setSelected(emp.isActive());
        panel.add(isActiveBox, gbc);
        row++;

        JButton saveButton = new JButton("Save Changes");
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(saveButton, gbc);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee updated = new Employee(
                    emp.getEmployeeId(),
                    numberField.getText(),
                    firstNameField.getText(),
                    lastNameField.getText(),
                    emp.getPosition(),
                    personalPhoneField.getText(),
                    (String) titleBox.getSelectedItem(),
                    licenseField.getText(),
                    el1Field.getText(),
                    workPhoneField.getText(),
                    hireDateField.getText(),
                    birthDateField.getText(),
                    isActiveBox.isSelected()
                );

                try {
                    boolean success = isNew ? dao.createEmployee(updated) : dao.updateEmployee(updated);
                    if (success) {
                        JOptionPane.showMessageDialog(null, "✅ Employee saved successfully.");

                        if (EmployeeDetails_GUI.openInstance != null) {
                            EmployeeDetails_GUI.openInstance.dispose();
                            EmployeeDetails_GUI.openInstance = null;
                        }

                        SwingUtilities.invokeLater(() -> new EmployeeDetails_GUI(updated.getFirstName(), updated.getLastName()));

                        if (EmployeeList_GUI.instance != null) {
                            EmployeeList_GUI.instance.refreshData();
                        }

                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "⚠️ No changes saved.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "❌ Failed to save employee:\n" + ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(panel);
        setVisible(true);
    }

    private JTextField addLabelAndField(JPanel panel, String label, String value, int row, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        JTextField field = new JTextField(value, 20);
        panel.add(field, gbc);
        return field;
    }
} 