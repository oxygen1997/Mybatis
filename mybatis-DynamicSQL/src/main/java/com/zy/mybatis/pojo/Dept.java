package com.zy.mybatis.pojo;

import java.util.List;

public class Dept {
    Integer id;
    String deptName;
    private List<Employee> employeeList;


    public Dept() {
    }

    public Dept(Integer id) {
        this.id = id;
    }

    public Dept(String deptName, List<Employee> employeeList) {
        this.deptName = deptName;
        this.employeeList = employeeList;
    }

    public Dept(Integer id, String deptName, List<Employee> employeeList) {
        this.id = id;
        this.deptName = deptName;
        this.employeeList = employeeList;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", deptName='" + deptName + '\'' +
                '}';
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> list) {
        this.employeeList = list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

}
