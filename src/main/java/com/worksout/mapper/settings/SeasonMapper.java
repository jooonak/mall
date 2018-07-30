package com.worksout.mapper.settings;

import com.worksout.dto.settings.Season;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SeasonMapper {

    @Select("SELECT SEASON_NO, SEASON_NM, USE_YN, ORD FROM SEASON ORDER BY ORD ASC")
    List<Season> getSeasons();

}
