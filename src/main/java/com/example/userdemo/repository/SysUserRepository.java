package com.example.userdemo.repository;

import com.example.userdemo.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserRepository extends JpaRepository<SysUser, Integer> {

    SysUser findByNameAndPassword(String name, String password);

    SysUser findByName(String name);
}
