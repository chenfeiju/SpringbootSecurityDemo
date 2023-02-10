package com.chenfj.filter;

import com.chenfj.service.security.LoginUserDetails;
import com.chenfj.utils.JwtUtil;
import com.chenfj.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * token 认证过滤器
 * @Auther: feiju.chen
 * @Date: 2023/2/10 18:29
 * @Description:
 * @version: 1.0
 */
@Component
public class JwtAuthencationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // 获取token
        String token = httpServletRequest.getHeader("token");
        if(!StringUtils.hasText(token)){
            filterChain.doFilter(httpServletRequest,httpServletResponse);//往下继续执行其他securityfilter
            return;
        }

        String userId = "";
        // 解析token
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        }catch (Exception e){
            throw new RuntimeException("非法token");
        }

        // 从redis中获取信息
        String redisKey = "login:"+userId;
        LoginUserDetails loginUserDetails = redisCache.getCacheObject(redisKey);
        if(Objects.isNull(loginUserDetails)){
            throw new RuntimeException("用户未登录");
        }

       // todo 获取权限信息封装到authenticationToken
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDetails,
                        null,
                        null);//这里必须用三个参数的构造函数，原因如下：
        /*public UsernamePasswordAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority > authorities) {
            super(authorities);
            this.principal = principal;
            this.credentials = credentials;
            super.setAuthenticated(true);
        }*/

        // 封装成Authencation 放入securityContextHolder中
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
