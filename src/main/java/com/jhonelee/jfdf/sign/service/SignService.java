package com.jhonelee.jfdf.sign.service;

import com.jhonelee.jfdf.employee.repository.EmployeeRepository;
import com.jhonelee.jfdf.sign.dto.SignDTO;
import com.jhonelee.jfdf.sign.entity.Sign;
import com.jhonelee.jfdf.sign.repository.SignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class SignService {

    @Autowired
    private SignRepository signRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Transactional
    public void saveOrUpdate(SignDTO signDTO) throws ParseException {
        Sign sign = new Sign();
        sign.setDate(new Date());
        sign.setPosition(signDTO.getPosition());
        sign.setEmployee(employeeRepository.findByStaffId(signDTO.getStaffId()));
        signRepository.save(sign);
    }
}
