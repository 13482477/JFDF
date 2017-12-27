package com.jhonelee.jfdf.employee.service;

import com.jhonelee.jfdf.employee.entity.Employee;
import com.jhonelee.jfdf.employee.repository.EmployeeRepository;
import com.jhonelee.jfdf.employee.util.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public void saveOrUpdate(Employee employee) {
        employeeRepository.save(employee);
    }

    @Transactional
    public void delete(Long id) {
        employeeRepository.delete(id);
    }

    @Transactional
    public void importEmployees(MultipartFile file) throws IOException {
        ArrayList<ArrayList<String>> dataList = ExcelUtils.importExcel(file.getInputStream());
        for (ArrayList<String> data : dataList) {
            Employee employee = employeeRepository.findByStaffId(data.get(0));
            if (employee == null) {
                employee = new Employee();
                employee.setStaffId(data.get(0));
                employee.setName(data.get(1));
                saveOrUpdate(employee);
            }
        }
    }
}
