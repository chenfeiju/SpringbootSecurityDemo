package com.chenfj.config;

import com.chenfj.filter.JwtAuthencationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @Auther: feiju.chen
 * @Date: 2023/2/10 16:16
 * @Description:
 * @version: 1.0
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthencationTokenFilter jwtAuthencationTokenFilter;

    //创建BCryptPasswordEncoder 注入到容器
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //登录认证时使用AuthenticationManager
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()//关闭csrf
                //不通过session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/user/login")
                .anonymous()
                //除了上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();

        // 把token过滤器添加到过滤器链中
        http.addFilterBefore(jwtAuthencationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
