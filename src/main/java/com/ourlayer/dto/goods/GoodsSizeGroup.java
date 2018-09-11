package com.ourlayer.dto.goods;

import lombok.Data;

@Data
public class GoodsSizeGroup {

    private Integer groupNo;      //사이즈 그룹 번호
    private String size;          //사이즈
    private String groupNm;       //그룹 이름 ( S - XL .. )
    private Integer sizeOrd;      //그룹 내 사이즈 순서
    private Integer displayOrd;   //그룹의 노출 순서

}
