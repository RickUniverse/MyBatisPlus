package org.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.mybatisplus.pojo.Employee;

import java.util.List;

/**
 * 只需要继承BaseMapper即可实现通用的CRUD操作
 */
public interface EmployeeMapper extends BaseMapper<Employee> {
    List<Employee> findAll();
}
