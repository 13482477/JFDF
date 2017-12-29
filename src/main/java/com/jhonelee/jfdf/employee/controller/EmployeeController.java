package com.jhonelee.jfdf.employee.controller;

import com.jhonelee.jfdf.employee.repository.EmployeeRepository;
import com.jhonelee.jfdf.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/openApi/employees", method = RequestMethod.POST)
    @ResponseBody
    public void importEmployeeExcel(@RequestParam(value = "excel") MultipartFile file) throws IOException {
        employeeService.importEmployees(file);
    }
}
