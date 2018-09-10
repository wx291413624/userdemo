package com.example.userdemo.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sys_permission_role")
public class SysPermissionRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 10)
    private int id;

    @Column(name = "role_id", length = 10)
    private Integer roleId;

    @Column(name = "permission_id", length = 10)
    private Integer permissionId;
}