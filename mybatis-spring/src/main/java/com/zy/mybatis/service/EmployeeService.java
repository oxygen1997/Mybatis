package com.zy.mybatis.service;

import com.zy.mybatis.bean.Employee;
import com.zy.mybatis.mapper.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private SqlSession sqlSession;

    @Resource
    EmployeeMapper employeeMapper;
    
    //批处理sql
    public void batchInsert(){
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for(int i=0;i<10000;i++){
            employeeMapper.addEmployee(new Employee("WuYanzu"+(i+1),i+1+"@163.com","1",null));
        }
    }
    
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
