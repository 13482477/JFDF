package com.jhonelee.jfdf.employee.controller;

import com.jhonelee.jfdf.employee.dto.EmployeeDTO;
import com.jhonelee.jfdf.employee.entity.Employee;
import com.jhonelee.jfdf.employee.repository.EmployeeRepository;
import com.jhonelee.jfdf.employee.service.EmployeeService;
import com.jhonelee.jfdf.web.convert.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping(value = "/openApi/employees", method = RequestMethod.GET)
    @ResponseBody
    public List<EmployeeDTO> getEmployees(@RequestParam(value = "signed", required = false) boolean signed, @RequestParam(value = "winning", required = false) boolean winning) {
        List<Employee> employees = null;
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();

        if (signed)
            employees = employeeService.getSignedEmployees();
        else if (winning)
            employees = employeeService.getWinningEmployees();

        for (Employee employee : employees) {
            employeeDTOS.add(ConvertUtils.convert(employee, input -> {
                EmployeeDTO employeeDTO = new EmployeeDTO();

                employeeDTO.setId(input.getId());
                employeeDTO.setName(input.getName());
                employeeDTO.setStaffId(input.getStaffId());

                return employeeDTO;
            }));

        }

        return employeeDTOS;
    }
}
