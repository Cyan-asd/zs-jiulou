package com.sky.controller.user;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author cyan
 * @version 1.0
 */
@Slf4j
@Api(tags= "店铺相关接口")
@RestController("userShopController")
@RequestMapping("/user/shop")
public class ShopController {

    public static final String key="shop_status";

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询营业状态
     * @return
     */
    @ApiOperation("查询营业状态")
    @GetMapping("/status")
    public Result<Integer> getStatus(){

        Integer status =(Integer) redisTemplate.opsForValue().get(key);
        log.info("获取营业状态为 {}",(status==1 ? "营业中":"打烊"));
        return Result.success(status);
    } 

}
