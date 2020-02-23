package com.zy.mybatis;

import com.zy.mybatis.bean.Employee;
import com.zy.mybatis.bean.EmployeeExample;
import com.zy.mybatis.dao.EmployeeMapper;
import com.zy.mybatis.enums.EmployeeEnum;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class EnumTest {

    private SqlSession sqlSession = null;

    public static SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "config/mybatis-config.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        return sqlSessionFactory;
    }

    @Test
    public void enumSave() throws IOException {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        try{
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
//            Employee employee = new Employee("Test_Enum", 1, "Enum@test.com", null, EmployeeEnum.REMOVE);
//            mapper.insert(employee);
//            sqlSession.commit();
//            Integer id = employee.getId();
//            System.out.println("success: id "+id);

            Employee employees = mapper.selectByPrimaryKey(14);
            System.out.println(employees);

        }finally {
            sqlSession.close();
        }
    }

    /**
     * 处理枚举类型存储枚举索引
     * 全局配置文件中加入typeHandler
     * 默认mybatis在处理枚举对象的时候保存的是枚举的名字：EnumTypeHandler
     * 改变使用：handler=EnumOrdinalTypeHandler：配置文件中：
     * <typeHandlers>
     *     <typeHandler handler="org.apache.ibatis.type.EnumOrdinalTypeHandler" javaType="com.zy.mybatis.enums.EmployeeEnum"/>
     * </typeHandlers>
     */
    @Test
    public void test(){
        EmployeeEnum employeeEnum = EmployeeEnum.LOGOUT;
        //枚举类型的索引,从0开始，按代码中枚举类型顺序排序
        int ordinal = employeeEnum.ordinal();
        //枚举类型的名字，Mybatis默认处理枚举类型，保存的是枚举名
        String name = employeeEnum.name();
        System.out.println("枚举的name："+name+"   枚举的索引："+ordinal);
        String msg = employeeEnum.getMsg();
        Integer code = employeeEnum.getCode();
        System.out.println("code: "+code+"  msg："+msg);
    }
}
