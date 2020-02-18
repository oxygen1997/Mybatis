package com.zy.mybatis.mapper;

import com.zy.mybatis.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeDynamicMapper {
//    If标签动态SQL查询
    List<Employee> getEmpByConditionIf(Employee employee);
//    Trim标签去除动态SQL多余后缀和添加前缀
    List<Employee> getEmpByConditionTrim(Employee employee);
//    choose选择语句标签查询
    List<Employee> getEmpByConditionChoose(Employee employee);
//    set标签更新元素内容
    void updateEmpByConditionSet(Employee employee);
//    批量获取集合参数中的元素
    List<Employee> getEmpByConditionForeach(List<Integer> list);
//    批量添加多条数据
    void addEmpByConditionForeach(@Param("empList") List<Employee> employeeList);
//    内置参数_parameter和_databaseId的使用
    List<Employee> getEmpByInnerParameter(Employee employee);
//    bind标签的使用，指定参数共同引用
    List<Employee> getEmpByIdBind(Employee employee);
//    <sql>和<include>标签
    Employee getById(Integer id);
}
