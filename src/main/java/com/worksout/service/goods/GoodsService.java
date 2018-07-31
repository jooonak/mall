package com.worksout.service.goods;

import com.worksout.dto.goods.Goods;
import com.worksout.dto.goods.GoodsCategory;
import com.worksout.dto.goods.GoodsSeeker;
import com.worksout.dto.goods.GoodsSizeGroup;
import com.worksout.mapper.goods.GoodsMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    private String selectGoodsNo() {
        return goodsMapper.selectGoodsNo();
    }

    public List<Goods> getGoodsList() {
        return goodsMapper.getGoodsList();
    }

    public List<Goods> getGoodsList(GoodsSeeker seeker) {
        return goodsMapper.getConditionalGoodsList(seeker);
    }

    public List<GoodsCategory> getCategoryList() {
        return goodsMapper.getCategoryList();
    }

    public List<GoodsSizeGroup> getSizeGroup() {
        return goodsMapper.getSizeGroup();
    }

    public void registerGoods(Goods goods) {
        List<Goods> goodsList = new ArrayList<>();
        goods.setGoodsNo(selectGoodsNo()); // 같은 상품은 색상 구별없이 같은 품번 세팅

        String[] colors = goods.getColor().split(",");

        for (int i = 0; i < colors.length; i++) {
            goods.setColor(colors[i].trim().toUpperCase());
            goods.setColorOrd(i);

            Goods copiedGoods = new Goods();
            BeanUtils.copyProperties(goods, copiedGoods);
            goodsList.add(copiedGoods);
        }
        goodsMapper.registerGoods(goodsList);
    }

}
