package com.zy.mybatis.bean;

import com.zy.mybatis.enums.EmployeeEnum;

public class Employee {
    private Integer id;

    private String lastName;

    private Integer gender;

    private String email;

    private Integer deptId;

    private EmployeeEnum employeeEnum;

    public EmployeeEnum getEmployeeEnum() {
        return employeeEnum;
    }

    public void setEmployeeEnum(EmployeeEnum employeeEnum) {
        this.employeeEnum = employeeEnum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Employee() {
    }

    public Employee(String lastName, Integer gender, String email, Integer deptId, EmployeeEnum employeeEnum) {
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.deptId = deptId;
        this.employeeEnum = employeeEnum;
    }

    public Employee(String lastName, Integer gender, String email, Integer deptId) {
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", deptId=" + deptId +
                ", employeeEnum=" + employeeEnum +
                '}';
    }
}