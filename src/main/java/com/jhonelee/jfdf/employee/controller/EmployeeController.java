package com.jhonelee.jfdf.employee.controller;

import com.jhonelee.jfdf.employee.dto.EmployeeDTO;
import com.jhonelee.jfdf.employee.entity.Employee;
import com.jhonelee.jfdf.employee.repository.EmployeeRepository;
import com.jhonelee.jfdf.employee.service.EmployeeService;
import com.jhonelee.jfdf.web.convert.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    public void importEmployeeExcel(@RequestParam(value = "excel") MultipartFile file, @RequestParam(value = "investor", required = false) Boolean investor, @RequestParam(value = "division", required = false) Integer division) throws IOException {
        employeeService.importEmployees(file, investor, division);
    }

    @RequestMapping(value = "/openApi/employees", method = RequestMethod.GET)
    @ResponseBody
    public List<EmployeeDTO> getEmployees(@RequestParam(value = "signed", required = false) Boolean signed, @RequestParam(value = "winning", required = false) Boolean winning,
                                          @RequestParam(value = "investor", required = false) Boolean investor, @RequestParam(value = "division", required = false) Integer division, @RequestParam(value = "award", required = false) Integer award) {
        List<Employee> employees = null;
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();

        EmployeeDTO employeeDTO = new EmployeeDTO(investor, division, signed, winning, award);
        employees = employeeService.getEmployees(employeeDTO);

        for (Employee employee : employees) {
            employeeDTOS.add(ConvertUtils.convert(employee, input -> {
                EmployeeDTO dto = new EmployeeDTO();

                dto.setId(input.getId());
                dto.setName(input.getName());
                dto.setStaffId(input.getStaffId());
                dto.setInvestor(input.getInvestor());
                dto.setDivision(input.getDivision());

                return dto;
            }));

        }

        return employeeDTOS;
    }
}
