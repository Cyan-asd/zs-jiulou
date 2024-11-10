package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sky.constant.MessageConstant;
import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.mapper.UserMapper;
import com.sky.properties.WeChatProperties;
import com.sky.service.UserService;
import com.sky.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cyan
 * @version 1.0
 */
@Slf4j
@Service
public class UserServiceimpl implements UserService {
    //微信服务接口地址
    public static final String WX_LOGIN="https://api.weixin.qq.com/sns/jscode2session";
    @Autowired
    private WeChatProperties weChatProperties; //配置文件中传入的值会被放入配置类，所以只需要自动注入然后获取即可
    @Autowired
    private UserMapper userMapper;

    /**
     * 微信登录
     * @param userLoginDTO
     * @return
     */
    @Override
    public User wxLogin(UserLoginDTO userLoginDTO) throws LoginException {

        log.info("UserLoginDTO {}",userLoginDTO);
        String openid = getOpenid(userLoginDTO.getCode());

        //判断是否为空
        if(openid==null){
            throw new LoginException(MessageConstant.LOGIN_FAILED);
        }

        //判断是否为新用户
        User user =userMapper.getByOpenid(openid);
        //如果是新用户 自动完成注册
        if(user==null){
            user=user.builder()
                    .openid(openid)
                    .createTime(LocalDateTime.now())
                    .build();
            log.info("user {}",user);
            userMapper.insert(user);
        }
        return user;
    }
    private String getOpenid(String code){
        Map<String,String> map = new HashMap<>();
        map.put("appid",weChatProperties.getAppid());
        map.put("secret",weChatProperties.getSecret());
        map.put("js_code",code);
        map.put("grant_type","authorization_code");
        //调用微信接口 获取openid
        String json =HttpClientUtil.doGet(WX_LOGIN,map);
        JSONObject jsonObejct = JSON.parseObject(json);
        String openid = jsonObejct.getString("openid");
        log.info("json {}",jsonObejct);
        return  openid;
    }
}
