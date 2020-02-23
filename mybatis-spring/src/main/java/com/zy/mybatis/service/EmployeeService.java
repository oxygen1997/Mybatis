package com.zy.mybatis.service;

import com.zy.mybatis.bean.Dept;
import com.zy.mybatis.bean.Employee;
import com.zy.mybatis.mapper.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    //使用applicationContext配置文件中的配置好的SqlSession bean来自动注入使用批处理
    @Autowired
    private SqlSession sqlSession;

    @Resource
    EmployeeMapper employeeMapper;
    
    //批处理sql
    public Map<String,Object> batchInsert(){
        boolean status = false;
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        long start = System.currentTimeMillis();
        for(int i=0;i<10000;i++){
            status =  employeeMapper.addEmployee(new Employee("WuYanzu"+(i+1),"1",
                    i+1+"@163.com",new Dept(2,null,null)));
        }
        long end = System.currentTimeMillis();
        Long time = end-start;
        System.out.println("执行时长："+time);
        HashMap<String, Object> map = new HashMap<>();
        map.put("time",time);
        map.put("status",status);
        return map;
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
