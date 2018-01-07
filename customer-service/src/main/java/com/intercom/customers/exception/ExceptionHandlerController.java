package com.intercom.customers.exception;

import com.intercom.customers.model.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
@RequestMapping("/error")
public class ExceptionHandlerController {

    Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ResponseEntity<CustomException> defaultErrorHandler(HttpServletRequest request, Exception e) {

        logger.info(String.format("executing %s.defaultErrorHandler", this.getClass().getName()));

        CustomException exception = new CustomException();

        exception.setTimestamp(new Date());
        exception.setStatus(HttpStatus.NOT_FOUND.toString());
        exception.setMessage(e.getMessage());
        exception.setException(e.toString());
        exception.setUrl(request.getRequestURL().toString());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }
}