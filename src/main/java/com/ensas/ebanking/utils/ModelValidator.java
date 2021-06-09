package com.ensas.ebanking.utils;

import org.springframework.validation.BindingResult;

public class ModelValidator {
    public static String getErrorsFromBindingResult(BindingResult bindingResult){
        StringBuilder errorStringBuilder= new StringBuilder();
        bindingResult.getAllErrors().forEach(obj->errorStringBuilder.append(obj.getDefaultMessage()).append("-"));
        return errorStringBuilder.toString();
    }
}
