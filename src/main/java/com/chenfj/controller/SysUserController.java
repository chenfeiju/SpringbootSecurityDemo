package com.chenfj.controller;

import com.chenfj.domain.ResponseResult;
import com.chenfj.entity.SysUser;
import com.chenfj.service.SysUserService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * 用户表(SysUser)表控制层
 *
 * @author makejava
 * @since 2023-02-10 15:13:39
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {
    /**
     * 服务对象
     */
    @Resource
    private SysUserService sysUserService;

    /**
     *
     * @param userId
     * @return 数据
     */
    @GetMapping("/getUserInfo")
    public ResponseResult getUserInfo(@RequestParam String userId) {
        SysUser sysUser = sysUserService.getUserInfoByUserId(userId);
        return new ResponseResult(2000,"",sysUser);
    }

}

