package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.User;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author cyan
 * @version 1.0
 */
@Mapper
public interface UserMapper {


    @Select("Select * from user where openid =#{openid} ")
    User getByOpenid(String openid);


    void insert(User user);
}
