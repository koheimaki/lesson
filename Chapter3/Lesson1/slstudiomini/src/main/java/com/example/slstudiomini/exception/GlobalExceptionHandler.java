package com.example.slstudiomini.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public Object handleAllExceptions(Exception ex) {
        ModelAndView mav = new ModelAndView("error/custom-error");
        mav.addObject("errorMessage", "開発者の恥です。ごめんなさい。");
        return mav;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public Object handleDataIntegrityViolationException(Exception ex) {
    ModelAndView mav = new ModelAndView("error/custom-error");
    mav.addObject("errorMessage", "ユニーク性違反でエラーが出た可能性があります");
    return mav;
}
}
