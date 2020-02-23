package com.zy.mybatis;

import com.zy.mybatis.bean.Employee;
import com.zy.mybatis.dao.EmployeeMapper;
import com.zy.mybatis.enums.EmployeeEnum;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class BatchExecuteTest {

    private SqlSession sqlSession = null;

    public static SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "config/mybatis-config.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        return sqlSessionFactory;
    }

    /**
     * 对当前session开启批处理
     * 执行方式：[ 预编译：1次 ]   [设置参数1万次]   [执行1次]
     *              Parameters: null, 9998LiuDehua(String), 1(Integer), ldh9998@cool.com(String), 2(Integer)
     *              Parameters: null, 9999LiuDehua(String), 1(Integer), ldh9999@cool.com(String), 2(Integer)
     *              Parameters: null, 10000LiuDehua(String), 1(Integer), ldh10000@cool.com(String), 2(Integer)
     *              预编译一次，设置参数10000次
     * 10000条记录，执行时长：5074       5.1秒
     * 100000条记录，执行时长：15013     15.0秒
     * @throws IOException
     */
    @Test
    public void batchTest() throws IOException {
        Long start = System.currentTimeMillis();
        Long end = null;
        //对本次session开启批处理sql
        sqlSession = getSqlSessionFactory().openSession(ExecutorType.BATCH);
        try{
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            for (int i = 1; i <=100000 ; i++) {
                mapper.insert(new Employee(i+"LiuDehua",1,"ldh"+i+"@cool.com",2));
            }
            end = System.currentTimeMillis();
            sqlSession.commit();
        }finally {
//            10000条记录，执行时长：5074        5.1秒
//            100000条记录，执行时长：15013      15.0秒
            System.out.println("执行时长："+(end-start));
            sqlSession.close();
        }
    }

    /**
     * 执行方式：[预编译1万次，设置参数1万次，执行1万次]
     *           ==>  Preparing: insert into tbl_employee (id, last_name, gender, email, dept_id) values (?, ?, ?, ?, ?)
     *           ==> Parameters: null, 9999JinChenWU(String), 1(Integer), jcw9999@cool.com(String), 2(Integer)
     *           预编译10000次，设置参数10000次
     * 10000条记录，执行时长：15385  15.4秒
     * 100000条记录，执行时长：81367 81.4秒
     * @throws IOException
     */
    @Test
    public void simpleInsertTest() throws IOException {
        //对本次session不开启批处理sql
        Long start = System.currentTimeMillis();
        Long end = null;
        sqlSession = getSqlSessionFactory().openSession();
        try{
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            for (int i = 1; i <=100000 ; i++) {
                mapper.insert(new Employee(i+"JinChenWU",1,"jcw"+i+"@cool.com",2));
            }
            end = System.currentTimeMillis();
            sqlSession.commit();
        }finally {
//            10000条记录，执行时长：15385       15.4秒
//            100000条记录，执行时长：81367      81.4秒
            System.out.println("执行时长："+(end-start));
            sqlSession.close();
        }
    }
    /**
     * 开启全局批处理：配置文件中Setting标签
     *  <setting name="defaultExecutorType" value="BATCH"/>
     * 当前session开启批处理-->>>执行方式：[预编译：1次]      [设置参数1万次]       [执行1次]    [时长：5.1秒]
     * 当前session不开启批处理--->>>执行方式：[预编译1万次]  [设置参数1万次]       [执行1万次]   [时长：15.4秒]
     */

}
