package com.generator.service.impl;

import com.generator.beans.Employee;
import com.generator.mapper.EmployeeMapper;
import com.generator.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Rick
 * @since 2020-12-11
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
    /**
     * 在ServiceImpl<EmployeeMapper, Employee>中已经帮我们注入了baseMapper不需要在手动注入
     * @Autowired
     *     protected M baseMapper;
     */

}
