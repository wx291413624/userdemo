package com.example.userdemo.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sys_permission")
public class SysPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 10)
    private int id;

    @Column(name = "name", length = 100)
    private String name;//角色名称

    @Column(name = "description", length = 100)
    private String description;//角色对应的用户实体

    @Column(name = "url", length = 100)
    private String url;

    @Column(name = "pid", length = 10)
    private Integer pid;
}
