package com.worksout.dto.goods;

import lombok.Data;

import java.util.Date;

@Data
public class GoodsImg {

	private String goodsNo;       //품번
	private String filename;      //이미지파일이름
	private Integer ord;          //순서
	private String updr;          //수정자
	private Date updDt;           //수정날짜
	private String regr;          //등록자
	private Date regDt;           //등록날짜

}
