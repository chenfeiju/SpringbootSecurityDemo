package com.chenfj.controller;

import com.chenfj.domain.LoginUserRequestParam;
import com.chenfj.domain.ResponseResult;
import com.chenfj.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: feiju.chen
 * @Date: 2023/2/10 16:33
 * @Description:
 * @version: 1.0
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody LoginUserRequestParam userRequestParam){

        ResponseResult responseResult = loginService.login(userRequestParam);
        return responseResult;
    }

}
