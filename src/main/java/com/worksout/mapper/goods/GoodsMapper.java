package com.worksout.mapper.goods;

import com.worksout.dto.goods.Goods;
import com.worksout.dto.goods.GoodsCategory;
import com.worksout.dto.goods.GoodsSeeker;
import com.worksout.dto.goods.GoodsSizeGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface GoodsMapper {

    @Select("SELECT /* selectGoodsNo */ LPAD(GOODS_NO, 7, 0) AS GOODS_NO FROM (SELECT IFNULL(MAX(GOODS_NO + 1), 1) AS GOODS_NO FROM GOODS_INFO) GOODS")
    String selectGoodsNo();

    List<Goods> getGoodsList();

    List<Goods> getConditionalGoodsList(GoodsSeeker seeker);

    @Select("SELECT /* getCategoryList */ SUP_CATEGORY_NO, CATEGORY_NO, CATEGORY_NM FROM GOODS_CATEGORY ORDER BY SUP_CATEGORY_NO ASC, CATEGORY_ORD ASC")
    List<GoodsCategory> getCategoryList();

    @Select("SELECT /* getSizeGroup */ DISTINCT GROUP_NO, GROUP_NM FROM GOODS_SIZE_GROUP ORDER BY DISPLAY_ORD ASC")
    List<GoodsSizeGroup> getSizeGroup();

    @Select("SELECT /* getSizeDetail */ SIZE FROM GOODS_SIZE_GROUP WHERE GROUP_NO = #{sizeGroupNo} ORDER BY SIZE_ORD")
    List<String> getSizeDetail(Goods goods);

    void registerGoods(Goods goods);

    void registerGoodsDetail(Goods goods);

    Goods getGoods(String goodsNo);

	int updateGoods(Goods goods);

    int updateGoodsDetail(Goods goods);
}
