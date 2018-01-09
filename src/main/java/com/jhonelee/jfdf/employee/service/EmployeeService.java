package com.jhonelee.jfdf.employee.service;

import com.jhonelee.jfdf.draw.entity.Draw;
import com.jhonelee.jfdf.employee.dto.EmployeeDTO;
import com.jhonelee.jfdf.employee.entity.Employee;
import com.jhonelee.jfdf.employee.repository.EmployeeRepository;
import com.jhonelee.jfdf.employee.util.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
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
    public void importEmployees(MultipartFile file, Boolean awarded, Boolean medalist) throws IOException {
        ArrayList<ArrayList<String>> dataList = ExcelUtils.importExcel(file.getInputStream());
        for (ArrayList<String> data : dataList) {
            Employee employee = employeeRepository.findByStaffId(data.get(0));
            if (employee == null) {
                employee = new Employee();
                employee.setStaffId(data.get(0));
            }

            if (awarded != null)
                employee.setAwarded(awarded);
            if (medalist != null)
                employee.setMedalist(medalist);

            employee.setName(data.get(1));

            saveOrUpdate(employee);
        }
    }

    public List<Employee> getEmployees(EmployeeDTO employeeDTO) {
        return employeeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (employeeDTO.getSigned() != null)
                predicates.add(criteriaBuilder.isNotEmpty(root.get("signList")));

            if (employeeDTO.getWinning() != null)
                predicates.add(criteriaBuilder.isNotNull(root.get("draw").get("id")));
            else {
                Subquery<Draw> subQuery = criteriaQuery.subquery(Draw.class);
                Root<Draw> drawRoot = subQuery.from(Draw.class);
                subQuery.select(drawRoot);
                Predicate predicate = criteriaBuilder.equal(drawRoot.get("employee"), root);

                predicates.add(criteriaBuilder.exists(subQuery.where(predicate)).not());

            }

            if (employeeDTO.getAwarded() != null)
                predicates.add(criteriaBuilder.equal(root.get("awarded"), Boolean.TRUE));

            if (employeeDTO.getMedalist() != null)
                predicates.add(criteriaBuilder.equal(root.get("medalist"), Boolean.TRUE));

            return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
        });
    }

}
