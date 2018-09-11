package com.ourlayer.service.settings;

import com.ourlayer.dto.settings.Season;
import com.ourlayer.mapper.settings.SeasonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeasonService {

    @Autowired
    private SeasonMapper seasonMapper;

    public List<Season> getSeasons () {
        return seasonMapper.getSeasons();
    }

    public String getCurrSeason () {
        return seasonMapper.getCurrSeason();
    }

}
