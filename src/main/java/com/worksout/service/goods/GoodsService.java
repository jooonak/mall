package com.worksout.service.goods;

import com.worksout.dto.goods.Goods;
import com.worksout.mapper.goods.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    public List<Goods> selectGoodsList() {
        return goodsMapper.selectGoodsList();
    }
}
