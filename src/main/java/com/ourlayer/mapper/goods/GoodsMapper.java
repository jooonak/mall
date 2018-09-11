package com.ourlayer.mapper.goods;

import com.ourlayer.dto.goods.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    @Insert("INSERT INTO GOODS_IMG (GOODS_NO, FILE_PATH, ORD, REGR) VALUES (#{goodsNo}, #{filePath}, #{ord}, #{regr})")
	void insertGoodsImage(GoodsImg img);
	
    @Select("SELECT GOODS_NO, FILE_PATH, ORD FROM GOODS_IMG WHERE GOODS_NO = #{_parameter}")
	List<GoodsImg> getGoodsImages(String goodsNo);
}
