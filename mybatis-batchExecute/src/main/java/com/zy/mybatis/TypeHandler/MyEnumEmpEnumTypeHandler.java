package com.zy.mybatis.TypeHandler;

import com.zy.mybatis.enums.EmployeeEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *  实现TypeHandler接口。或者继承BaseTypeHandler
 */
public class MyEnumEmpEnumTypeHandler implements TypeHandler<EmployeeEnum> {

    public void setParameter(PreparedStatement preparedStatement, int i, EmployeeEnum employeeEnum, JdbcType jdbcType) throws SQLException {
        System.out.println("【 保存的状态码： "+employeeEnum.getCode()+" 】");
        preparedStatement.setString(i,employeeEnum.getCode().toString());
    }

    public EmployeeEnum getResult(ResultSet resultSet, String columnName) throws SQLException {
        int code = resultSet.getInt(columnName);
        System.out.println("从数据库中获取的状态码："+code);
        EmployeeEnum employeeEnum = EmployeeEnum.getEnum(code);
        return employeeEnum;
    }

    public EmployeeEnum getResult(ResultSet resultSet, int columnIndex) throws SQLException {
        int code = resultSet.getInt(columnIndex);
        System.out.println("从数据库中获取的状态码："+code);
        EmployeeEnum employeeEnum = EmployeeEnum.getEnum(code);
        return employeeEnum;
    }

    public EmployeeEnum getResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        int code = callableStatement.getInt(columnIndex);
        System.out.println("从数据库中获取的状态码："+code);
        EmployeeEnum employeeEnum = EmployeeEnum.getEnum(code);
        return employeeEnum;
    }
}
