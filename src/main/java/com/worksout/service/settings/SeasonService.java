package com.worksout.service.settings;

import com.worksout.dto.settings.Season;
import com.worksout.mapper.settings.SeasonMapper;
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

}
