package com.zy.mybatis.mapper;

import com.zy.mybatis.pojo.Employee;


public interface EmployeeMapperPlus {
    Employee getById(Integer id);
    Employee getByDeptId(Integer id);
    Employee getByDeptId2(Integer id);
    Employee getByDeptStep(Integer id);
    Employee getByDeptStepDis(Integer id);
}
