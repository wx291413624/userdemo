package com.example.userdemo.repository;


import com.example.userdemo.domain.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysPermissionRepository extends JpaRepository<SysPermission, Integer> {
}
