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
    public void importEmployeeExcel(@RequestParam(value = "excel") MultipartFile file, @RequestParam(value = "investor", required = false) Boolean investor, @RequestParam(value = "division", required = false) Integer division, @RequestParam(value = "regular" ,required = false) Boolean regular) throws IOException {
        employeeService.importEmployees(file, investor, division, regular);
    }

    @RequestMapping(value = "/openApi/employees", method = RequestMethod.GET)
    @ResponseBody
    public List<EmployeeDTO> getEmployees(@RequestParam(value = "signed", required = false) Boolean signed, @RequestParam(value = "winning", required = false) Boolean winning,
                                          @RequestParam(value = "investor", required = false) Boolean investor, @RequestParam(value = "division", required = false) Integer division,
                                          @RequestParam(value = "award", required = false) Integer award, @RequestParam(value = "regular", required = false) Boolean regular,
                                          @RequestParam(value = "gift", required = false) Boolean gift) {
        List<Employee> employees = null;
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();

        EmployeeDTO employeeDTO = new EmployeeDTO(investor, division, signed, winning, award, regular, gift);
        employees = employeeService.getEmployees(employeeDTO);

        for (Employee employee : employees) {
            employeeDTOS.add(ConvertUtils.convert(employee, input -> {
                EmployeeDTO dto = new EmployeeDTO();

                dto.setId(input.getId());
                dto.setName(input.getName());
                dto.setStaffId(input.getStaffId());
                dto.setInvestor(input.getInvestor());
                dto.setDivision(input.getDivision());
                dto.setRegular(input.getRegular());
                dto.setGift(input.getGift());

                return dto;
            }));

        }

        return employeeDTOS;
    }

    @RequestMapping(value = "/openApi/employees/{staffId}", method = RequestMethod.PUT)
    @ResponseBody
    public EmployeeDTO updateEmployee(@PathVariable(value = "staffId") String staffId) {
        Employee employee = employeeRepository.findByStaffId(staffId);
        if (employee != null) {
            employee.setGift(true);
            employeeService.saveOrUpdate(employee);
        }

        return ConvertUtils.convert(employee, input -> {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setStaffId(employee.getStaffId());
            employeeDTO.setRegular(employee.getRegular());
            employeeDTO.setName(employee.getName());
            employeeDTO.setGift(employee.getGift());

            return employeeDTO;
        });
    }


}
