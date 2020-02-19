package com.zy.mybatis.service;

import com.zy.mybatis.bean.Employee;
import com.zy.mybatis.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmployeeService {

    @Resource
    EmployeeMapper employeeMapper;

    public Employee getById(Integer id){
        return employeeMapper.getById(id);
    }

    public List<Employee> getByGenderList(Employee employee){
        return employeeMapper.getByGenderList(employee);
    }
    public List<Employee> getAllEmployee(){
        return employeeMapper.getAllEmployee();
    }

}
