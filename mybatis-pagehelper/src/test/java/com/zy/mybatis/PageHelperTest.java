package com.zy.mybatis;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zy.mybatis.bean.Employee;
import com.zy.mybatis.bean.EmployeeExample;
import com.zy.mybatis.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PageHelperTest {

    public static SqlSessionFactory getSqlSessionFactory() throws IOException {

        String resource = "config/mybatis-config.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        return  sqlSessionFactory;
    }

    /**
     * 使用PageHelper :
     *              1.引入依赖pageHelper
     *              2.在mybatis全局配置文件中加入plugins标签，配置interceptor类com.github.pagehelper.PageInterceptor拦截 Execute和Query
     * @throws IOException
     */
    @Test
    public void testPageHelper() throws IOException {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        try{
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            EmployeeExample employeeExample = new EmployeeExample();
            EmployeeExample.Criteria criteria = employeeExample.createCriteria();
            criteria.andLastNameLike("%C%");
            //pageNum:页码，pageSize：每页的数据条数
            Page<Object> page = PageHelper.startPage(2, 3);
            //offset 下标索引：从0开始，0代表第一行数据，limit：从offset开始总共需要的数据条数
//            Page<Object> page = PageHelper.offsetPage(0, 5);
//            System.out.println("pageTotal:"+page.getTotal()+" pageNum:"+page.getPageNum()+" pages:"+page.getPages()+" PageSize:"+page.getPageSize());
            List<Employee> employeeList = mapper.selectByExample(employeeExample);
            //以命名空间的方式调用sql查询，第二个参数为查询条件参数,new RowBounds([offset 下标索引：从0开始，0代表第一行数据]，[limit：从offset开始总共需要的数据条数]);
//            List<Employee> employeeList = sqlSession.selectList("com.zy.mybatis.dao.EmployeeMapper.selectByExample", null, new RowBounds(5, 5));
            for (Employee e:employeeList) {
                System.out.println(e);
            }
        }finally {
            sqlSession.close();
        }
    }
}
