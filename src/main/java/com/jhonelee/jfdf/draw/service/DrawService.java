package com.jhonelee.jfdf.draw.service;

import com.jhonelee.jfdf.draw.entity.Draw;
import com.jhonelee.jfdf.draw.repository.DrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DrawService {

    @Autowired
    private DrawRepository drawRepository;

    @Transactional
    public void saveOrUpdated(Draw draw) {
        drawRepository.save(draw);
    }
}
