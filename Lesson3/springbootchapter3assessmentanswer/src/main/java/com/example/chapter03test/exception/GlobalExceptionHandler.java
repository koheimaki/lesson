package com.example.chapter03test.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(InvalidFileException.class)
    public Object handleInvalidFileException(InvalidFileException e) {
        logger.error("Image loading error occurred: " + e.getMessage());

        ModelAndView mav = new ModelAndView("error/custom-error");
        mav.addObject("errorMessage", e.getMessage());
        return mav;
    }
}