package org.recap.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dharmendrag on 1/2/17.
 * Handles Error from Controllers
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value=Exception.class)
    public String defaultErrorHandler(HttpServletRequest request,Exception exp)
    {
        StackTraceElement [] elements=exp.getStackTrace();
        logger.debug("Exception due to "+exp.getMessage());
        logger.error(exp.getMessage()+" occured in "+elements[0].getClassName()+" & line number:"+elements[0].getLineNumber());
        return "error";
    }
}
