package com.ourlayer.dto.settings;

import lombok.Data;

import java.util.Date;

@Data
public class Season {

    private Integer seasonNo;
    private String seasonNm;
    private boolean useYn;
    private Integer ord;
    private Date startDt;
    private Date endDt;
    private String updr;
    private Date updDt;
    private String regr;
    private Date regDt;

}
