package com.zy.mybatis.mapper;

import com.zy.mybatis.pojo.Dept;

public interface DeptMapper {
    Dept getDeptById(Integer id);
    Dept getByDeptList(Integer id);
    Dept getDeptByIdStep(Integer id);
}
