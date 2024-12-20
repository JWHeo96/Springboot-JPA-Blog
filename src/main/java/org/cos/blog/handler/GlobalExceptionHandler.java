package org.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // Exception 발생 시 해당 클래스로 들어옴
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value=Exception.class)
    public String handleArgumentException(Exception e) {
        if (e instanceof IllegalArgumentException) {
            System.out.println("IllegalArgumentException 발생");
            return "<h1>Illegal Argument Exception: " + e.getMessage() + "</h1>";
        } else if (e instanceof NullPointerException) {
            System.out.println("NullPointerException 발생");
            return "<h1>Null Pointer Exception: " + e.getMessage() + "</h1>";
        } else {
            System.out.println("알 수 없는 예외 발생: " + e.getClass().getSimpleName());
            return "<h1>Exception: " + e.getMessage() + "</h1>";
        }
    }
}
