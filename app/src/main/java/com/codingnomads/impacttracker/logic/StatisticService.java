package com.codingnomads.impacttracker.logic;

import com.codingnomads.impacttracker.data.UriStatisticRepository;

import java.util.List;

import static com.codingnomads.impacttracker.logic.ImpactUtils.transformStatisticToImpactList;

public class StatisticService {

    private UriStatisticRepository statisticRepository;

    public StatisticService(UriStatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    public List<Impact> getImpact() {
        return transformStatisticToImpactList(statisticRepository.getImpact());
    }
}
