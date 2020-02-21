package com.zy.mybatis;

import com.zy.mybatis.bean.Employee;
import com.zy.mybatis.bean.EmployeeExample;
import com.zy.mybatis.bean.EmployeeExample.Criteria;
import com.zy.mybatis.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MybatisGeneratorTest {

    public static SqlSessionFactory getSqlSessionFactory() throws IOException {

        String resource = "config/mybatis-config.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        return  sqlSessionFactory;

    }

    @Test
    public void testGenerator() throws Exception {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File("src/main/resources/generator/mybatis-generator.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
                callback, warnings);
        myBatisGenerator.generate(null);
    }

    @Test
    public void testExample() throws IOException {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        try{
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            EmployeeExample employeeExample = new EmployeeExample();
            //创建查询条件，Criteria为条件封装bean
            Criteria criteria = employeeExample.createCriteria();
            //条件为LastName中包含C，Id在1开始(包括1)到13中的所有符合条件的数据
            criteria.andLastNameLike("%C%").andIdBetween(1,13);
//            =>  Preparing: select id, last_name, gender, email, dept_id from tbl_employee WHERE ( last_name like ? and id between ? and ? )
//            当要使用or时 创建一个新的Criteria,使用Example的or方法拼接起来
            Criteria criteria2 = employeeExample.createCriteria();
            criteria2.andEmailLike("%E%");
//            ==>  Preparing: select id, last_name, gender, email, dept_id from tbl_employee WHERE ( last_name like ? and id between ? and ? ) or( email like ? )
            employeeExample.or(criteria2);
            List<Employee> employees = mapper.selectByExample(employeeExample);
            for (Employee e:employees) {
                System.out.println(e.toString());
            }
        }finally {
            sqlSession.close();
        }



    }
}
