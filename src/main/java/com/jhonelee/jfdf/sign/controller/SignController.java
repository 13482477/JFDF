package com.jhonelee.jfdf.sign.controller;

import com.jhonelee.jfdf.sign.dto.SignDTO;
import com.jhonelee.jfdf.sign.entity.Sign;
import com.jhonelee.jfdf.sign.repository.SignRepository;
import com.jhonelee.jfdf.sign.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;

@Controller
public class SignController {

    @Autowired
    private SignService signService;

    @RequestMapping(value = "/openApi/sign", method = RequestMethod.POST)
    @ResponseBody
    public void signIn(@RequestBody SignDTO signDTO) throws ParseException {
        signService.saveOrUpdate(signDTO);
    }

}
