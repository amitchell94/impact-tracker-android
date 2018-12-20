package com.codingnomads.impacttracker.logic;

import com.codingnomads.impacttracker.data.UriStatisticRepository;

import java.util.List;

public class StatisticService {

    private UriStatisticRepository statisticRepository;

    public StatisticService(UriStatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    public List<Statistic> getImpact() {
        return statisticRepository.getImpact();
    }
}
