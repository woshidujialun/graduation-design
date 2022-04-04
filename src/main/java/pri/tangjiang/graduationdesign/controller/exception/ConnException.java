package pri.tangjiang.graduationdesign.controller.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pri.tangjiang.graduationdesign.util.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class ConnException {

    @ExceptionHandler(value = Exception.class)
    public Result handler(HttpServletRequest req, HttpServletResponse res, Exception e){
        return Result.fail("系统异常");
    }

}
