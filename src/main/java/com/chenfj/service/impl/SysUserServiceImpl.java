package com.chenfj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chenfj.entity.SysUser;
import com.chenfj.mapper.UserMapper;
import com.chenfj.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户表(SysUser)表服务实现类
 *
 * @author makejava
 * @since 2023-02-10 15:13:52
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public SysUser getUserInfoByUserId(String userId) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(SysUser::getId, userId);
        return userMapper.selectOne(queryWrapper);
    }
}

