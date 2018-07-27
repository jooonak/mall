package com.worksout.dto.goods;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Goods {

    //테이블 데이터
    private Integer categoryNo;         //카테고리번호
    private String season;              //시즌
    private String goodsNo;             //상품번호
    private String goodsNm;             //상품명
    private String color;               //색상
    private String repColor;            //대표색상
    private Integer sizeGroupNo;        //사이즈그룹 번호
    private String goodsDesc;           //상품설명
    private Integer supplyPrice;        //공급가
    private Integer retailPrice;        //소매가
    private Integer onSalePrice;        //세일가
    private Integer colorOrd;           //색상별 노출순서
    private Integer categoryOrd;        //카테고리페이지 노출순서
    private Integer mainOrd;            //메인페이지 노출순서
    private boolean displayYn;          //노출여부
    private String mainImg;             //메인이미지 ( 색상별 대표이미지 )
    private String updr;                //수정한사람
    private Date updDt;                 //수정한날짜
    private String regr;                //등록한사람
    private Date regDt;                 //등록날짜

    //추가 필요 데이터
    private List<String> colors;        //전체색상
    private List<String> mainImgs;      //색상별 메인이미지 리스트
    private List<GoodsImg> imgs;        //상품이미지 리스트
    private String categoryNm;          //카테고리 이름
    private String sizeGroupNm;         //사이즈그룹 이름
    private String repImg;              //대표색상 이미지

    private String originColor;         //기존 상품색상 ( 상품 업데이트시 사용 )
    private Integer originSizeGroupNo;  //기존 사이즈그룹 번호 ( 상품 업데이트시 사용 )

}
