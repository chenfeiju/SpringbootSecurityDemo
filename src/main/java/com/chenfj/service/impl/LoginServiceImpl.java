package com.chenfj.service.impl;

import com.chenfj.domain.LoginUserRequestParam;
import com.chenfj.domain.ResponseResult;
import com.chenfj.service.LoginService;
import com.chenfj.service.security.LoginUserDetails;
import com.chenfj.utils.JwtUtil;
import com.chenfj.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

/**
 * @Auther: feiju.chen
 * @Date: 2023/2/10 16:34
 * @Description:
 * @version: 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(LoginUserRequestParam userRequestParam) {

        // AuthenticationManager authenticate 进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userRequestParam.getUserName(),userRequestParam.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 用户认证没通过给出响应的提示
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("登录失败");
        }

        // 用户认证通过了，使用userid生成一个jwt jwt存入ResponseResult 返回
        LoginUserDetails loginUserDetails = (LoginUserDetails) authenticate.getPrincipal();
        String userId = loginUserDetails.getSysUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        HashMap<String,Object> map = new HashMap<>();
        map.put("token",jwt);

        // 把完整的用户信息存入到redis userid作为key
        redisCache.setCacheObject("login:"+userId,loginUserDetails);
        return new ResponseResult(ResponseResult.CodeStatus.OK,"登录成功",map);
    }
}
