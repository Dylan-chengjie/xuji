package com.fb.xujimanage.controller;

import com.fb.xujimanage.entity.vo.EmployeeVo;
import com.fb.xujimanage.service.EmployeeService;
import com.fb.xujimanage.util.CommonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @auther Sam.yang
 * @date 2020/8/25
 * @description 员工信息管理Controller
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    /*@ApiOperation("员工信息下拉列表")
    @GetMapping("/list")
    public CommonResult getEmployeeList() {
        List<EmployeeVo> employeeList = employeeService.getEmployeeList();
        return CommonResult.ok("获取员工信息下拉列表成功",employeeList);
    }*/
}
