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

public class MybatisTest {

    public static SqlSessionFactory getConn() throws IOException {
        //类路径映射的是resources目录
        String resource = "config/mybatis-config.xml";
        //Resources是Mybatis下的工具类
        InputStream in = Resources.getResourceAsStream(resource);
        //SqlSession工厂SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        return sqlSessionFactory;
    }

//    通过命名空间和唯一标识符来执行SQL
    @Test
    public void getOneById() throws IOException {

        SqlSessionFactory sqlSessionFactory = getConn();
        //sqlsession是和数据库的一次会话，用完必须关闭。
        SqlSession sqlSession = null;
        try{
            sqlSession = sqlSessionFactory.openSession();
            //selectOne两个参数 第一个是你要执行的已映射sql(sql的唯一标识id的地址)【命名空间+唯一标识】，第二个参数是传递的sql参数。
            Employee employee = sqlSession.selectOne("com.zy.mybatis.mapper.EmployeeMapper.selectOne", 1);
            System.out.println(employee);
        }finally {
            sqlSession.close();
        }

    }
//    面向接口的实现SQL
    @Test
    public void getById2() throws IOException {
        //获取连接工厂对象sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = getConn();
        //开启会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = employeeMapper.getById(1);
            System.out.println("【 "+employeeMapper.getClass()+" 】");//检测mybatis是否会为接口创建代理对象。
            System.out.println(employee);
        }finally {
            sqlSession.close();
        }
    }
}
