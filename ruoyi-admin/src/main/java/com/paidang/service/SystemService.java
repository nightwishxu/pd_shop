package com.paidang.service;

import com.base.util.PropertySupport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SystemService {


    @Value("${spring.profiles.active}")
    private String profiles;


    @PostConstruct
    public void init(){
        PropertySupport.initProfile(profiles);
    }
}
