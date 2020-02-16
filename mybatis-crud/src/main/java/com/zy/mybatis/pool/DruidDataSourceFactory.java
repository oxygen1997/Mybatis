package com.zy.mybatis.pool;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

//自定义第三方连接池Druid，继承UnpooledDataSourceFactory，初始化数据源值为Druid数据源
public class DruidDataSourceFactory extends UnpooledDataSourceFactory {
    public DruidDataSourceFactory(){
        this.dataSource = new DruidDataSource();
    }
}
