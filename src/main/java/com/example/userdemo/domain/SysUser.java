package com.example.userdemo.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;

@Data
@Entity
@Table(name = "sys_user")
@NoArgsConstructor
public class SysUser implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "name", length = 120)
    private String name;
    @Column(name = "net_name", length = 120)
    private String netName;
    @Column(name = "email", length = 50)
    private String email;
    @Column(name = "password", length = 120)
    private String password;
    @Temporal(TemporalType.DATE)
    @Column(name = "dob", length = 10)
    private Date dob;
    @Column(name = "img", length = 500)
    private String img;

    @Transient
    private Boolean type;


    public SysUser(String name, String password, String email, String netName) {
        this.name = name;
        this.netName = netName;
        this.email = email;
        this.password = password;
    }

    public static SysUser get() {//惰性求值
        Supplier<SysUser> personSupplier = SysUser::new;
        return personSupplier.get();
    }

    public static void set(String name, String password, String email, String netName) {//????
        Consumer<SysUser> greeter = (p) -> {
            p.email = email;
            p.name = name;
            p.password = password;
            p.netName = netName;
        };
        greeter.accept(new SysUser("Luke", "Skywalker", "Luke", "Skywalker"));
    }


}