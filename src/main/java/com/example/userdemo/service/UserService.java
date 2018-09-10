package com.example.userdemo.service;

import com.example.userdemo.redis.RedisService;
import com.example.userdemo.util.ResponseUtill;
import com.example.userdemo.domain.SysUser;
import com.example.userdemo.repository.SysUserRepository;
import com.example.userdemo.util.vo.TokenResp;
//import com.example.userdemo.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private SysUserRepository sysUserRepository;
    @Autowired
    private RedisService redisService;

    public Object login(String name, String password) {
        SysUser byName = sysUserRepository.findByName(name);
        if (byName != null) {
            if (!password.equals(byName.getPassword())) {
                return ResponseUtill.responseErrorDate("密码错误");
            }
            String token = redisService.createToken(byName);
            return ResponseUtill.responseAccessDate(new TokenResp(token, byName.getId()));
        }
        return ResponseUtill.responseErrorDate("用户名错误");
    }

    public Object register(String name, String netName, String email, String password) {
        try {
            SysUser sysUser = new SysUser(name, password, email, netName);
            sysUserRepository.save(sysUser);
            return ResponseUtill.responseAccessDate();
        } catch (Exception e) {
            return ResponseUtill.responseErrorDate();
        }
    }

//    public Object findAll() {
//        List<SysUser> all = sysUserRepository.findAll();
//        ConcurrentHashMap<String, WebSocketServer> webSocketSet = WebSocketServer.webSocketSet;
//        Collection<WebSocketServer> values = webSocketSet.values();
//        List<SysUser> collect = values.stream().map(vale -> vale.getSysUser())
//                .collect(Collectors.toList());
//        all.forEach(sysUser -> {
//            if (!collect.isEmpty()) {
//                collect.forEach(webSysUser -> {
//                    if (sysUser.getId().equals(webSysUser.getId())) {
//                        sysUser.setType(true);
//                    } else {
//                        sysUser.setType(false);
//                    }
//                });
//            } else {
//                sysUser.setType(false);
//            }
//        });
//        return ResponseUtill.responseAccessDate(all);
//    }
}
