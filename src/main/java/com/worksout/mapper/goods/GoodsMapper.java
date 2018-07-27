package com.worksout.mapper.goods;

import com.worksout.dto.goods.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsMapper {

    List<Goods> selectGoodsList();

}
