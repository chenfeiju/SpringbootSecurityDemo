package com.chenfj.service;

import com.chenfj.domain.LoginUserRequestParam;
import com.chenfj.domain.ResponseResult;

/**
 * @Auther: feiju.chen
 * @Date: 2023/2/10 16:34
 * @Description:
 * @version: 1.0
 */
public interface LoginService {
    ResponseResult login(LoginUserRequestParam userRequestParam);
}
