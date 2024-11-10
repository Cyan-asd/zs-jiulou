package com.sky.mapper;

import com.sky.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author cyan
 * @version 1.0
 */
@Mapper
public interface OrderDetailMapper {


    void insertBatch(List<OrderDetail> list);

    @Select("select * from orders where id =#{id}")
    List<OrderDetail> getByOrderId(Long id);
}
