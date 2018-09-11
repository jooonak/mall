package com.ourlayer.mapper.settings;

import com.ourlayer.dto.settings.Season;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SeasonMapper {

    @Select("SELECT SEASON_NO, SEASON_NM, USE_YN, ORD FROM SEASON ORDER BY ORD ASC")
    List<Season> getSeasons();

    @Select("SELECT SEASON_NM FROM SEASON WHERE USE_YN = true ORDER BY ORD LIMIT 1")
    String getCurrSeason();
}
