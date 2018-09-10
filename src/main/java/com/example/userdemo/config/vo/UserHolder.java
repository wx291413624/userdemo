package com.example.userdemo.config.vo;


import com.example.userdemo.domain.SysUser;

public class UserHolder {

    private static ThreadLocal<SysUser> localUser = new ThreadLocal<>();

    public static void setUser(SysUser user) {
        localUser.set(user);
    }

    public static SysUser getUser() {
        return localUser.get();
    }
}
