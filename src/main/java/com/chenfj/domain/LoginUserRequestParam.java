package com.chenfj.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Auther: feiju.chen
 * @Date: 2023/2/10 16:36
 * @Description:
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserRequestParam implements Serializable {
    private String userName;
    private String password;
}
