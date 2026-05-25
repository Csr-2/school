package com.test.student.excep;

import com.test.student.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
//捕获全部Controller类的异常
@RestControllerAdvice
public class GlobleExceptionHandler {
    @ExceptionHandler(NullPointerException.class)
    public Result handleNullPointerException(NullPointerException e) {
        e.printStackTrace();
        return Result.failed("空指针异常！");
    }

    @ExceptionHandler(ArithmeticException.class)
    public Result handleIllegalArgumentException(ArithmeticException e) {
        e.printStackTrace();
        return Result.failed("算数异常！");
    }
}
