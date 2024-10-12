package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Mapper;
import com.github.pagehelper.Page;
import java.util.List;

/**
 * @author cyan
 * @version 1.0
 */
@Mapper
public interface DishFlavorMapper {

    //@AutoFill(value = OperationType.INSERT)
    public void insertBatch(List<DishFlavor> flavors);

    Page<DishVO> select(Dish dish);
}
