package com.jhonelee.jfdf.draw.service;

import com.jhonelee.jfdf.draw.repository.DrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrawService {

    @Autowired
    private DrawRepository drawRepository;

}
