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
    public void importEmployeeExcel(@RequestParam(value = "excel") MultipartFile file, @RequestParam(value = "awarded", required = false) Boolean awarded, @RequestParam(value = "medalist", required = false) Boolean medalist) throws IOException {
        employeeService.importEmployees(file, awarded, medalist);
    }

    @RequestMapping(value = "/openApi/employees", method = RequestMethod.GET)
    @ResponseBody
    public List<EmployeeDTO> getEmployees(@RequestParam(value = "signed", required = false) Boolean signed, @RequestParam(value = "winning", required = false) Boolean winning,
                                          @RequestParam(value = "awarded", required = false) Boolean awarded, @RequestParam(value = "medalist", required = false) Boolean medalist) {
        List<Employee> employees = null;
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();

        EmployeeDTO employeeDTO = new EmployeeDTO(awarded, medalist, signed, winning);
        employees = employeeService.getEmployees(employeeDTO);

        for (Employee employee : employees) {
            employeeDTOS.add(ConvertUtils.convert(employee, input -> {
                EmployeeDTO dto = new EmployeeDTO();

                dto.setId(input.getId());
                dto.setName(input.getName());
                dto.setStaffId(input.getStaffId());
                dto.setAwarded(input.isAwarded());
                dto.setMedalist(input.isMedalist());

                return dto;
            }));

        }

        return employeeDTOS;
    }
}
