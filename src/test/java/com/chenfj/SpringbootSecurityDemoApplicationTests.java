package com.chenfj;

import com.chenfj.entity.SysUser;
import com.chenfj.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class SpringbootSecurityDemoApplicationTests {

    @Resource
    private UserMapper userMapper;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Test
    public void mapper(){
        List<SysUser> sysUsers = userMapper.selectList(null);
        System.out.println(sysUsers);
    }

    @Test
    public void passwordEncoder(){
        String encode = passwordEncoder.encode("123456");
        System.out.println("123456"+encode);
    }

    @Test
    void contextLoads() {
    }

}
