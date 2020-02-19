package com.zy.mybatis.mapper;

import com.zy.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    Employee getById(Integer id);
    List<Employee> getByGenderList(Employee employee);
    List<Employee> getAllEmployee();
}
