package com.example.userdemo.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sys_role")
public class SysRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 10)
    private int id;

    @Column(name = "name", length = 100)
    private String name;//角色名称


}