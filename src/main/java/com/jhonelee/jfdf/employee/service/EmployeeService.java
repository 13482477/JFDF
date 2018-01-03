package com.jhonelee.jfdf.employee.service;

import com.jhonelee.jfdf.employee.entity.Employee;
import com.jhonelee.jfdf.employee.repository.EmployeeRepository;
import com.jhonelee.jfdf.employee.util.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Employee> getSignedEmployees() {
        return employeeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.isNotEmpty(root.get("signList")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
        });
    }

    public List<Employee> getWinningEmployees() {
        return employeeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.isNotNull(root.get("draw").get("id")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
        });
    }
}
