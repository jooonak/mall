package com.worksout.mapper.goods;

import com.worksout.dto.goods.Goods;
import com.worksout.dto.goods.GoodsCategory;
import com.worksout.dto.goods.GoodsSizeGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsMapper {

    List<Goods> getGoodsList();

    @Select("SELECT /* getCategoryList */ SUP_CATEGORY_NO, CATEGORY_NO, CATEGORY_NM FROM GOODS_CATEGORY ORDER BY SUP_CATEGORY_NO ASC, CATEGORY_ORD ASC")
    List<GoodsCategory> getCategoryList();

    @Select("SELECT DISTINCT GROUP_NO, GROUP_NM FROM GOODS_SIZE_GROUP ORDER BY DISPLAY_ORD ASC")
    List<GoodsSizeGroup> getSizeGroup();
}
