package pri.tangjiang.graduationdesign.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pri.tangjiang.graduationdesign.bean.param.LoginParam;
import pri.tangjiang.graduationdesign.service.login.LoginService;
import pri.tangjiang.graduationdesign.util.Result;

import javax.annotation.Resource;

@RestController
@RequestMapping("/tj")
public class LoginController {
    @Resource
    private LoginService loginService;

    @RequestMapping("/login")
    public Result login(@RequestBody @Validated LoginParam param) {
        return loginService.login(param.getUsername(), param.getPassword(), param.getIsAdmin());
    }

    @RequestMapping("/test")
    public String test() {
        return "ok";
    }
}
