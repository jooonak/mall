package com.worksout.service.goods;

import com.worksout.dto.goods.Goods;
import com.worksout.dto.goods.GoodsCategory;
import com.worksout.dto.goods.GoodsSizeGroup;
import com.worksout.mapper.goods.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    public List<Goods> getGoodsList() {
        return goodsMapper.getGoodsList();
    }

    public List<GoodsCategory> getCategoryList() {
        return goodsMapper.getCategoryList();
    }

    public List<GoodsSizeGroup> getSizeGroup() {
        return goodsMapper.getSizeGroup();
    }

    public void registerGoods(Goods goods) {
        System.out.println(goods);
    }
}
