package com.zy.mybatis.test;

import com.zy.mybatis.mapper.EmployeeDynamicMapper;
import com.zy.mybatis.pojo.Dept;
import com.zy.mybatis.pojo.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DynamicSQLTest {
    private SqlSession sqlSession = null;

    public static SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "config/mybatis-config.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        return sqlSessionFactory;
    }

    /**
     * 测试动态SQL If条件
     * @throws IOException
     */
    @Test
    public void getEmpByConditionIf() throws IOException {
        this.sqlSession = getSqlSessionFactory().openSession();
        try{
            EmployeeDynamicMapper employeeDynamicMapper = sqlSession.getMapper(EmployeeDynamicMapper.class);
            Employee employee = new Employee();
            employee.setLastName("J");
            employee.setGender("1");
            employee.setId(3);
            employee.setEmail(null);
//            logging.jdbc.BaseJdbcLogger 2020-02-18 11:10:59
//            ==>  Preparing: select * from tbl_employee where id=? and last_name like concat('%',?,'%') and gender=?
//            当Email传值为null时，动态SQL不会对其拼接，以上SQL证明了这个道理
            List<Employee> empList = employeeDynamicMapper.getEmpByConditionIf(employee);
            for (Employee e:empList) {
                System.out.println(e);
            }
        }finally {
            this.sqlSession.close();
        }
    }

    @Test
    public void  getEmpByConditionWhere() throws IOException {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        try{
            /**
             * 没带Id情况下SQL拼接语法出错（ where and ）
             * ==>  Preparing: select * from tbl_employee where and last_name like concat('%',?,'%') and gender=?
             * 解决方法1：where 1=1
             * Preparing: select * from tbl_employee where 1=1 and last_name like concat('%',?,'%') and gender=?
             * 解决方法2：使用Mybatis的where标签，将所有的SQL查询条件包括在内,将多余的第一个 and 或者 or 去掉（类似SQL语法检查）
             * ==>  Preparing: select * from tbl_employee WHERE last_name like concat('%',?,'%') and gender=?
             */
            EmployeeDynamicMapper mapper = sqlSession.getMapper(EmployeeDynamicMapper.class);
            Employee employee = new Employee();
            employee.setId(null);
            employee.setGender("1");
            employee.setLastName("J");
            employee.setEmail(null);
            List<Employee> empList = mapper.getEmpByConditionIf(employee);
            for (Employee e:empList) {
                System.out.println(e);
            }
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void  getEmpByConditionTrim() throws IOException {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        try{
            /**
             * 未加prefix="where" suffixOverrides="and" 属性的动态SQL
             * ==>  Preparing: select * from tbl_employee last_name like concat('%',?,'%') and gender=? and
             * 加入prefix="where" suffixOverrides="and" 属性后的动态SQL
             * ==>  Preparing: select * from tbl_employee where last_name like concat('%',?,'%') and gender=?
             * prefix="" 前缀
             * prefixOverrides=""前缀覆盖：将字符串前面多余的and或者or去掉
             * suffix="" 后缀
             * suffixOverrides=""后缀覆盖：将字符串后面多余的and或者or去掉
             */
            EmployeeDynamicMapper mapper = sqlSession.getMapper(EmployeeDynamicMapper.class);
            Employee employee = new Employee();
            employee.setId(null);
            employee.setGender("1");
            employee.setLastName("J");
            employee.setEmail(null);
            List<Employee> empList = mapper.getEmpByConditionTrim(employee);
            for (Employee e:empList) {
                System.out.println(e);
            }
        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void  getEmpByConditionChoose() throws IOException {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        try{
            /**
             * when单分支选择
             * otherwise 其他情况选择
             */
            EmployeeDynamicMapper mapper = sqlSession.getMapper(EmployeeDynamicMapper.class);
            Employee employee = new Employee();
            employee.setId(null);
            employee.setGender(null);
//            employee.setLastName("D");
            employee.setEmail(null);
            List<Employee> empList = mapper.getEmpByConditionChoose(employee);
            for (Employee e:empList) {
                System.out.println(e);
            }
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void  updateEmpByConditionSet() throws IOException {
        SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        try{
            /**
             * set标签更新操作
             */
            EmployeeDynamicMapper mapper = sqlSession.getMapper(EmployeeDynamicMapper.class);
            Employee employee = new Employee();
            employee.setId(7);
            employee.setGender("0");
            employee.setLastName("吴彦祖");
            employee.setEmail("wyz@cool.com");
            mapper.updateEmpByConditionSet(employee);
        }finally {
            sqlSession.close();
        }
    }

    //遍历传入的集合参数
    @Test
    public void getEmpByConditionForeach() throws IOException {
        this.sqlSession = getSqlSessionFactory().openSession();
        try{
            EmployeeDynamicMapper mapper = sqlSession.getMapper(EmployeeDynamicMapper.class);
            List<Employee> list = mapper.getEmpByConditionForeach(Arrays.asList(1, 2, 3));
            for (Employee e:list) {
                System.out.println(e);
            }
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 批量添加
     * @throws IOException
     */
    @Test
    public void addBatchEmpByConditionForeach() throws IOException {
         sqlSession = getSqlSessionFactory().openSession();
         try{
             EmployeeDynamicMapper mapper = sqlSession.getMapper(EmployeeDynamicMapper.class);
             List<Employee> list = new ArrayList<Employee>();
             list.add(new Employee( "Jack", "1", "Jack@cool.com", new Dept(1)));
             list.add(new Employee( "ChenGuanXi", "1", "Eadison@cool.com", new Dept(1)));
             mapper.addEmpByConditionForeach(list);
             sqlSession.commit();
         }finally {
             sqlSession.close();
         }
    }

    /**
     * 内置参数的使用
     * @throws IOException
     */
    @Test
    public void getEmpByInnerParameter() throws IOException {
        sqlSession = getSqlSessionFactory().openSession();
        try{
            EmployeeDynamicMapper mapper = sqlSession.getMapper(EmployeeDynamicMapper.class);
            List<Employee> list = mapper.getEmpByInnerParameter(new Employee( "mr chen", null, null, null));
            System.out.println(list);
        }finally {
            sqlSession.close();
        }
    }
    /**
     * bind标签
     */
    @Test
    public void getEmpByIdBind() throws IOException {
        sqlSession = getSqlSessionFactory().openSession();
        try{
            EmployeeDynamicMapper mapper = sqlSession.getMapper(EmployeeDynamicMapper.class);
            List<Employee> employee = mapper.getEmpByIdBind(new Employee("e", null, null, null));
            for (Employee e:employee) {
                System.out.println(e);
            }
        }finally {
            sqlSession.close();
        }
    }

    @Test
   public void getById() throws IOException {
        sqlSession = getSqlSessionFactory().openSession();
        try{
            EmployeeDynamicMapper mapper = sqlSession.getMapper(EmployeeDynamicMapper.class);
            Employee e = mapper.getById(2);
            System.out.println(e);
        }finally {
            sqlSession.close();
        }
   }
}
