package com.worksout.service.goods;

import com.worksout.dto.goods.*;
import com.worksout.mapper.goods.GoodsMapper;
import lombok.extern.java.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Log
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

    /**
     * 상품등록 메서드
     * 상품정보와 상품상세 테이블에 데이터를 넣기 위해
     * 입력받은 데이터를 바탕으로 상세 정보를 세팅
     * @param goods
     */
    @Transactional
    public void registerGoods(Goods goods) {
        goods.setGoodsNo(selectGoodsNo()); // 같은 상품은 색상 구별없이 같은 품번 세팅
        List<String> goodsSizes = goodsMapper.getSizeDetail(goods);
        String[] colors = goods.getDetail().getColor().split(","); // 입력받은 색상 ex) white, black, navy

        goods.setDetails(new ArrayList<>());
        goods.getDetail().setRegr(goods.getRegr());
        goods.getDetail().setGoodsNo(goods.getGoodsNo());
        goods.getDetail().setActualPrice(goods.getDetail().getRetailPrice());

        log.info("GOODS : " + goods);

        for (int i = 0; i < colors.length; i++) {
            goods.getDetail().setColor(colors[i].trim().toUpperCase());
            goods.getDetail().setColorOrd(i);

            for (int j = 0; j < goodsSizes.size(); j++) {
                goods.getDetail().setSize(goodsSizes.get(j));
                goods.getDetail().setSizeOrd(j);
                GoodsDetail copiedDetail = new GoodsDetail(); // 세팅한 정보 유지를 위한 객체 생성
                BeanUtils.copyProperties(goods.getDetail(), copiedDetail);
                log.info("GOODS DETAIL : " + copiedDetail);
                goods.getDetails().add(copiedDetail);
            }
        }
        goodsMapper.registerGoods(goods);
        goodsMapper.registerGoodsDetail(goods);
    }

    public Goods getGoods(String goodsNo) {
        return goodsMapper.getGoods(goodsNo);
    }

    @Transactional
    public void updateGoods(Goods goods) {
        goodsMapper.updateGoods(goods);
        goodsMapper.updateGoodsDetail(goods);
    }
}
