package com.sky.mapper;

import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author cyan
 * @version 1.0
 */
@Mapper
public interface OrderMapper {




    void insert(Orders orders);
}