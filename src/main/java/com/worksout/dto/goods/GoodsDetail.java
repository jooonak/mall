package com.worksout.dto.goods;

import lombok.Data;

import java.util.Date;

@Data
public class GoodsDetail {

    private String goodsNo;           //상품번호 (PK)
    private String color;             //상품색상 (PK)
    private String size;              //상품사이즈 (PK)
    private Integer colorOrd;         //색상순서
    private Integer sizeOrd;          //사이즈순서
    private Integer supplyPrice;      //공급가
    private Integer retailPrice;      //판매가
    private Integer actualPrice;      //실제판매가
    private boolean discountYn;       //할인여부
    private Integer stock;            //재고
    private String updr;              //수정자
    private Date updDt;               //수정날짜
    private String regr;              //등록자
    private Date regDt;               //등록날짜

}
