package com.zy.mybatis.controller;

import com.zy.mybatis.bean.Employee;
import com.zy.mybatis.service.EmployeeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/getAll")
    public String getAll(Map<String,Object> map){
        List<Employee> employees = employeeService.getAllEmployee();
        map.put("empList",employees);
        return "list";
    }
    @RequestMapping(value = "/getById",produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String getById(Integer id, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setCharacterEncoding("utf-8");
        Employee employee = employeeService.getById(id);
        return employee.toString();
    }
}
