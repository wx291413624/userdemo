package com.example.userdemo.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sys_role_user")
public class SysRoleUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 10)
    private int id;

    @Column(name = "role_id", length = 10)
    private Integer roleId;

    @Column(name = "user_id", length = 10)
    private Integer userId;
}
