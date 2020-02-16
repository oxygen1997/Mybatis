package com.zy.mybatis.mapper;


import com.zy.mybatis.pojo.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {

     //通过id查一条数据
     Employee getByIdEmp(Integer id);

     //获取全部数据
     List<Employee> getAllEmp();

     //增加一条数据
     void addEmp(Employee employee);

     //通过id删除一条数据
     void deleteEmp(Integer id);

     //通过id修改数据
     void updateEmp(Employee employee);

     //通过多个条件查询一条记录
     Employee getByIdAndLastName(@Param("id") Integer id, @Param("lastName")String lastName);

     //多个参数时可以使用JavaBean（当多个参数都存在与JavaBean中）
     Employee getByBean(Employee employee);

     //多个参数时可以使用Map（当传入部分参数不存在与映射的JavaBean属性中）
     Employee getByMap(Map<String,String> map);

    //多个参数时可以使用Map（当传入部分参数不存在与映射的JavaBean属性中）
    List<Employee> getByLikeName(String lastName);

    //返回map类型
     Map<String,Object>  getByIdMap(Integer id);

     //返回map类型,多个参数
     @MapKey("id")
     Map<Integer,Employee>  getByLikeNameMap2(String lastName);
}
