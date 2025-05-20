package com.mcdade.payapp.model;

public class Employee {
    private String employeeNumber;
    private String firstName;
    private String lastName;
    private String position;
    private String phone;

    // Extended fields for detailed view
    private String title;
    private String licenseNumber;
    private String el1Number;
    private String workPhone;
    private String hireDate;
    private String birthDate;
    private int employeeId;
    private boolean isActive;

    // Basic constructor
    public Employee(String employeeNumber, String firstName, String lastName, String position, String phone) {
        this.employeeNumber = employeeNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.phone = phone;
    }

    // Extended constructor
    public Employee(int employeeId, String employeeNumber, String firstName, String lastName, String position, String phone,
                    String title, String licenseNumber, String el1Number,
                    String workPhone, String hireDate, String birthDate, boolean isActive) {
        this.employeeId = employeeId;
        this.employeeNumber = employeeNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.phone = phone;
        this.title = title;
        this.licenseNumber = licenseNumber;
        this.el1Number = el1Number;
        this.workPhone = workPhone;
        this.hireDate = hireDate;
        this.birthDate = birthDate;
        this.isActive = isActive;
    }

    // Getters
    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public String getPhone() {
        return phone;
    }

    public String getTitle() {
        return title;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getEl1Number() {
        return el1Number;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getHireDate() {
        return hireDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public boolean isActive() {
        return isActive;
    }

}
