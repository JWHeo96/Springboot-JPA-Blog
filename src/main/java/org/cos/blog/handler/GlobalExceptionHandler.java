package org.cos.blog.handler;

import org.cos.blog.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // Exception 발생 시 해당 클래스로 들어옴
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value=Exception.class)
    public ResponseDto<String> handleArgumentException(Exception e) {
        if (e instanceof IllegalArgumentException) {
            System.out.println("IllegalArgumentException 발생");
        } else if (e instanceof NullPointerException) {
            System.out.println("NullPointerException 발생");
        } else {
            System.out.println("알 수 없는 예외 발생: " + e.getClass().getSimpleName());
        }
        return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}
