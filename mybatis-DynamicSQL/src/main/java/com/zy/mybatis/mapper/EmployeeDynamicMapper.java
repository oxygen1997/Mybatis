package com.zy.mybatis.mapper;

import com.zy.mybatis.pojo.Employee;

import java.util.List;

public interface EmployeeDynamicMapper {
    List<Employee> getEmpByConditionIf(Employee employee);
    List<Employee> getEmpByConditionTrim(Employee employee);
}
