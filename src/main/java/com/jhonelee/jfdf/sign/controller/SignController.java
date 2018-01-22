package com.jhonelee.jfdf.sign.controller;

import com.jhonelee.jfdf.employee.dto.EmployeeDTO;
import com.jhonelee.jfdf.employee.entity.Employee;
import com.jhonelee.jfdf.employee.repository.EmployeeRepository;
import com.jhonelee.jfdf.sign.dto.SignDTO;
import com.jhonelee.jfdf.sign.service.SignService;
import com.jhonelee.jfdf.web.WebResult;
import com.jhonelee.jfdf.web.convert.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SignController {

    @Autowired
    private SignService signService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(value = "/openApi/sign", method = RequestMethod.POST)
    @ResponseBody
    public WebResult signIn(@RequestBody SignDTO signDTO) {
        Employee employee = employeeRepository.findByStaffId(signDTO.getStaffId());
        if (employee != null) {
            signService.saveOrUpdate(signDTO);
            return WebResult.builder().returnCode("0").returnMessage("签到成功！").data(ConvertUtils.convert(employee, input -> {
                EmployeeDTO employeeDTO = new EmployeeDTO();

                employeeDTO.setId(employee.getId());
                employeeDTO.setName(employee.getName());
                employeeDTO.setGift(employee.getGift());
                employeeDTO.setRegular(employee.getRegular());
                employeeDTO.setStaffId(employee.getStaffId());

                return employeeDTO;
            })).build();
        } else
            return WebResult.builder().returnCode("1").returnMessage("员工号错误！").build();
    }

}
