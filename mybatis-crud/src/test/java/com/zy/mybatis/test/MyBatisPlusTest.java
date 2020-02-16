package com.zy.mybatis.test;


import com.zy.mybatis.mapper.DeptMapper;
import com.zy.mybatis.mapper.EmployeeMapperPlus;
import com.zy.mybatis.pojo.Dept;
import com.zy.mybatis.pojo.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisPlusTest {

    private  SqlSession sqlSession = null;

    public static SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "config/mybatis-config.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        return sqlSessionFactory;
    }

    @Test
    public void getById() throws IOException {
        try{
            sqlSession = getSqlSessionFactory().openSession();
            EmployeeMapperPlus employeeMapperPlus = sqlSession.getMapper(EmployeeMapperPlus.class);
            Employee employee = employeeMapperPlus.getById(2);
            System.out.println(employee);
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 级联查询 根据部门id查询该员工部门
     * @throws IOException
     */
    @Test
    public void getByDeptId() throws IOException {
        try{
            sqlSession = getSqlSessionFactory().openSession();
            EmployeeMapperPlus employeeMapperPlus = sqlSession.getMapper(EmployeeMapperPlus.class);
            Employee employee = employeeMapperPlus.getByDeptId(3);
            System.out.println(employee);
            System.out.println(employee.getDept());
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 级联association查询 根据部门id查询该员工部门
     * @throws IOException
     */
    @Test
    public void getByDeptId2() throws IOException {
        try{
            sqlSession = getSqlSessionFactory().openSession();
            EmployeeMapperPlus employeeMapperPlus = sqlSession.getMapper(EmployeeMapperPlus.class);
            Employee employee = employeeMapperPlus.getByDeptId2(5);
            System.out.println(employee);
            System.out.println(employee.getDept());
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 级联association分步查询
     * 调用已经封装好的sql查询
     * 根据部门id查询该员工部门
     * @throws IOException
     */
    @Test
    public void getByStep() throws IOException {
        try{
            sqlSession = getSqlSessionFactory().openSession();
            EmployeeMapperPlus employeeMapperPlus = sqlSession.getMapper(EmployeeMapperPlus.class);
            Employee employee = employeeMapperPlus.getByDeptStep(5);
//            System.out.println(employee);
//            System.out.println(employee.getDept());
            System.out.println(employee.getLastName());
            System.out.println(employee.getDept());
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 鉴别器查询
     * 女生返回部门信息
     * 男生不返回部门信息
     */
    @Test
    public void getByDeptStepDis() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        this.sqlSession = sqlSessionFactory.openSession();
        try{
            EmployeeMapperPlus mapper = sqlSession.getMapper(EmployeeMapperPlus.class);
            //男生
            Employee employee = mapper.getByDeptStepDis(3);
            System.out.println("------------男-------------");
            System.out.println(employee);
            System.out.println(employee.getDept());
            //女生
            Employee employee2 = mapper.getByDeptStepDis(4);
            System.out.println("------------女-------------");
            System.out.println(employee2);
            System.out.println(employee2.getDept());
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 一对多集合collection查询
     * 通过员工id查询员工部门信息的同时查出所有在部门人员的信息
     * @throws IOException
     */
    @Test
    public void getByDeptList() throws IOException {
        try{
            sqlSession = getSqlSessionFactory().openSession();
            DeptMapper deptMapper  = sqlSession.getMapper(DeptMapper.class);
            Dept dept = deptMapper.getByDeptList(5);
            System.out.println(dept);
            for (Employee e:dept.getEmployeeList()) {
                System.out.println(e);
            }
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 一对多集合collection分布查询
     * 通过员工id查询员工部门信息的同时查出所有在部门人员的信息
     * @throws IOException
     */
    @Test
    public void getByDeptListStep() throws IOException {
        try{
            sqlSession = getSqlSessionFactory().openSession();
            DeptMapper deptMapper  = sqlSession.getMapper(DeptMapper.class);
            Dept dept = deptMapper.getDeptByIdStep(3);
            System.out.println(dept);
            for (Employee e:dept.getEmployeeList()) {
                System.out.println(e);
            }
        }finally {
            sqlSession.close();
        }
    }
}
