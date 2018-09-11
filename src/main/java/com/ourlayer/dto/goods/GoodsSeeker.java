package com.ourlayer.dto.goods;

import lombok.Data;

@Data
public class GoodsSeeker {

    private String seasonNm;
    private Integer categoryNo;
    private String searchGb;
    private String searchTxt;
    private String color;
    private String repColor;
    private String size;
    private Integer minPrice;
    private Integer maxPrice;
    private Integer sizeGroupNo;
    private Boolean displayYn;
    private String updr;
    private String regr;

}
