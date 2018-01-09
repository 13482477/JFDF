package com.jhonelee.jfdf.draw.controller;

import com.jhonelee.jfdf.draw.entity.Draw;
import com.jhonelee.jfdf.draw.service.DrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DrawController {

    @Autowired
    private DrawService drawService;

    @RequestMapping(value = "/openApi/draw", method = RequestMethod.POST)
    @ResponseBody
    public void winning(@RequestBody Draw draw) {

        drawService.saveOrUpdated(draw);
    }

}
