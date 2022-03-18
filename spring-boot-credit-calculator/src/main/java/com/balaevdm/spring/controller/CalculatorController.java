package com.balaevdm.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class CalculatorController {

    @GetMapping
    public String main(){
        return "calculatorpage";
    }

    @PostMapping
    public String calculateCredit(){
        return "calculatorpage";
    }
}
