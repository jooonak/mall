package com.worksout.dto.goods;

import lombok.Data;

@Data
public class GoodsCategory {

    private Integer supCategoryNo;   //상위카테고리번호
    private Integer categoryNo;      //카테고리번호
    private String categoryNm;       //카테고리명
    private Integer categoryOrd;     //카테고리순서

}
