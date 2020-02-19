package com.zy.mybatis.bean;


import java.io.Serializable;

public class Employee implements Serializable {

    private Integer id;
    private String lastName;
    private String gender;
    private String email;
    //设置外键关联tbl_dept.id <-------> tbl_employee.dept_id时，外键的属性和长度都要一致
    private Dept dept;

    public Employee() {
    }

    public Employee(String lastName, String gender, String email, Dept dept) {
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.dept = dept;
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
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
