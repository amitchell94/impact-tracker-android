package com.codingnomads.impacttracker.logic;

import com.codingnomads.impacttracker.data.ImpactRepository;

import java.util.List;

import static com.codingnomads.impacttracker.logic.ImpactUtils.transformStatisticToImpactList;

public class ImpactService {

    private ImpactRepository impactRepository;

    public ImpactService(ImpactRepository impactRepository) {
        this.impactRepository = impactRepository;
    }

    public List<Impact> getImpact() {
        return transformStatisticToImpactList(impactRepository.getImpact());
    }
}
