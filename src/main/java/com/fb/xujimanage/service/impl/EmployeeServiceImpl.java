package com.fb.xujimanage.service.impl;

import com.fb.xujimanage.dao.EmployeeDao;
import com.fb.xujimanage.entity.Employee;
import com.fb.xujimanage.entity.vo.EmployeeVo;
import com.fb.xujimanage.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 员工信息表(Employee)表服务实现类
 *
 * @author sam.yang
 * @since 2020-08-25 15:14:51
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeDao employeeDao;


   /* @Override
    public List<EmployeeVo> getEmployeeList() {
        List<EmployeeVo> employeeVoList=employeeDao.selectEmployeeList();
        return employeeVoList;
    }*/

}