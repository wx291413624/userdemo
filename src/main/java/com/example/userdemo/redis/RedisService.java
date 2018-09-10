package com.example.userdemo.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.userdemo.domain.SysUser;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RedisService {

    @Value("${token.adminTimeout}")
    public Integer adminTimeout;
    @Value("${token.userTimeout}")
    public Integer userTimeout;

    private static final String adminToken = "login:admin:token:";
    private static final String adminUidToken = "login:admin:uid:token:";


    private static final String userToken = "login:user:token:";
    private static final String userUidToken = "login:user:uid:token:";

    @Autowired
    private RedisUtil redisUtil;


    public String createToken(SysUser sysUser) {
        Optional.ofNullable(sysUser).orElseThrow(() -> new IllegalArgumentException("is null is null"));
        String token = generateRandomToken();
        try {
            delByUserId(sysUser.getId());

            String PTKey = userToken + token;
            redisUtil.set(PTKey, JSONObject.toJSONString(sysUser));
            redisUtil.expire(PTKey, userTimeout);

            String TUKey = userUidToken + sysUser.getId();
            redisUtil.set(TUKey, token);
            redisUtil.expire(TUKey, userTimeout);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public SysUser findUser(String token) {
        String to = userToken + token;
        String s = redisUtil.get(to);
        SysUser user = JSON.parseObject(s, SysUser.class);
        return user;
    }


    private String generateRandomToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public void delByUserId(long userId) {
        Optional.ofNullable(userId).orElseThrow(() -> new IllegalArgumentException("userId is required param!"));
        String token = redisUtil.get(userUidToken + userId);
        String user = redisUtil.get(userToken + token);
        if (token != null) redisUtil.expire(userUidToken + userId, 2);
        if (user != null) redisUtil.expire(userToken + token, 2);
    }
}
