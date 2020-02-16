package com.zy.mybatis.test;

import com.zy.mybatis.mapper.EmployeeMapper;
import com.zy.mybatis.pojo.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisTest {

    /**
     * 获取会话工厂的公用方法
     * @return
     * @throws IOException
     */
    public static SqlSessionFactory getSqlSessionFactory() throws IOException {
        //类路径映射的是resources目录
        String resource = "config/mybatis-config.xml";
        //Resources是Mybatis下的工具类
        InputStream in = Resources.getResourceAsStream(resource);
        //SqlSession工厂SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);

        return sqlSessionFactory;
    }

//    面向接口的实现SQL
    //通过id获取一条数据
    @Test
    public void getById() throws IOException {
        SqlSession sqlSession = null;
        //获取连接工厂对象sqlSessionFactory
        //开启会话
        try{
            sqlSession = getSqlSessionFactory().openSession();
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = employeeMapper.getByIdEmp(1);
            //openSession对象不会自动提交
            System.out.println("【 "+employeeMapper.getClass()+" 】");//检测mybatis是否会为接口创建代理对象。
            System.out.println(employee);
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 增删改允许的返回值为 Integer、boolean、Long、void
     * 自动提交事务，SqlSessionFactory.openSession(true)；
     * @author chenzeyi
     * @throws IOException
     */
    //增加一条数据
    @Test
    public void add() throws IOException {
        SqlSession sqlSession = null;
        try{
            sqlSession = getSqlSessionFactory().openSession();
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = new Employee();
            employee.setLastName("彭于晏");
            employee.setGender("5");
            employee.setEmail("彭于晏@188.com");
            employeeMapper.addEmp(employee);
            System.out.println("useGeneratedKeys："+employee.getId());
            //openSession对象不会自动提交
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
    }

    //修改一条数据
    @Test
    public void update() throws IOException {
        SqlSession sqlSession = null;
        try{
            sqlSession = getSqlSessionFactory().openSession();
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = new Employee();
            employee.setId(2);
            employee.setLastName("MyGirl2");
            employee.setGender("5");
            employee.setEmail("MyGirl@126.com");
            employeeMapper.updateEmp(employee);
            //openSession对象不会自动提交
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
    }

    //通过id删除一条数据
    @Test
    public void delById() throws IOException {
        SqlSession sqlSession = null;
        //获取连接工厂对象sqlSessionFactory
        //开启会话
        try{
            sqlSession = getSqlSessionFactory().openSession();
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            employeeMapper.deleteEmp(4);
            //openSession对象不会自动提交
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
    }

    //多个参数条件查询
    @Test
    public void getByIdAndLastName() throws IOException {
        SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        try{
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = employeeMapper.getByIdAndLastName(9, "彭于晏");
            System.out.println("【"+employee+"】");
        }finally {
            sqlSession.close();
        }
    }

    //多个参数封装入JavaBean条件查询
    @Test
    public void getByIBean() throws IOException {
        SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        try{
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employeeBean = new Employee();
            employeeBean.setGender("5");
            employeeBean.setEmail("彭于晏@188.com");
            Employee employee = employeeMapper.getByBean(employeeBean);
            System.out.println("【"+employee+"】");
        }finally {
            sqlSession.close();
        }
    }
    //多个参数封装入Map条件查询
    @Test
    public void getByMap() throws IOException {
        SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        try{
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            HashMap<String,String> map = new HashMap<String,String>();
            map.put("email","lubenwei@163.com");
            map.put("lastName","卢本伟");
            Employee employee = employeeMapper.getByMap(map);
            System.out.println("【"+employee+"】");
        }finally {
            sqlSession.close();
        }
    }
    //模糊查询返回集合List
    @Test
    public void getByLikeName() throws IOException {
        SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        try{
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            List<Employee> employeeList = employeeMapper.getByLikeName("y");
            for (Employee e:employeeList) {
                System.out.println(e);
            }
        }finally {
            sqlSession.close();
        }
    }

    //返回map类型
    @Test
    public void getByIdMap() throws IOException {
        SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        try{
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            Map<String,Object> map = employeeMapper.getByIdMap(3);
            System.out.println(map);
        }finally {
            sqlSession.close();
        }
    }

    //返回map类型,封装  Map(Employee.id,Employee)
    @Test
    public void getByLikeNameMap2() throws IOException {
        SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        try{
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            Map<Integer,Employee> map = employeeMapper.getByLikeNameMap2("m");
            System.out.println(map);
        }finally {
            sqlSession.close();
        }
    }
}
