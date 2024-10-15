package com.sky.mapper;

import com.sky.entity.ShoppingCart;
import com.sky.result.Result;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author cyan
 * @version 1.0
 */
@Mapper
public interface ShoppingCartMapper {


    List<ShoppingCart> list(ShoppingCart shoppingCart);

    @Update("update shopping_cart set number = #{number} where id= #{id}")
    void updateNumberById(ShoppingCart cart);

    @Insert("insert into  shopping_cart(name, image, user_id, dish_id, setmeal_id, dish_flavor, amount, create_time)" +
            "values (#{name},#{image},#{userId},#{dishId},#{setmealId},#{dishFlavor},#{amount},#{createTime})")
    void insert(ShoppingCart shoppingCart);

    @Select("select * from shopping_cart where user_id =#{userId}")
    List<ShoppingCart> get(Long userId);

    @Delete("Delete  from shopping_cart where user_id =#{userId}")
    void clean(Long userId);
}
