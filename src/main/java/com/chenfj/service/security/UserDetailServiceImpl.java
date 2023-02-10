package com.chenfj.service.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chenfj.entity.SysUser;
import com.chenfj.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Auther: feiju.chen
 * @Date: 2023/2/10 15:31
 * @Description:
 * @version: 1.0
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(SysUser::getUserName,username);
        SysUser sysUser = userMapper.selectOne(queryWrapper);
        if(Objects.isNull(sysUser)){
            throw new RuntimeException("没有该用户");
        }

        // todo 查询权限信息
        // 把数据封装成UserDetails返回
        return new LoginUserDetails(sysUser);
    }
}
