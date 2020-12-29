package com.generator.controller;

import com.generator.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Rick
 * @since 2020-12-11
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public void login() {
        /*
        * 可以直接使用已经定义好的方法
        * */
        employeeService.getById(1);
    }
}

