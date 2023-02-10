package com.chenfj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenfj.entity.SysUser;

/**
 * 用户表(SysUser)表服务接口
 *
 * @author makejava
 * @since 2023-02-10 15:13:49
 */
public interface SysUserService {

    SysUser getUserInfoByUserId(String userId);
}

