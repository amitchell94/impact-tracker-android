package com.codingnomads.impacttracker.logic.reduction;

import com.codingnomads.impacttracker.data.ReductionRepository;

import java.util.List;

public class ReductionService {

    private ReductionRepository reductionRepository;

    public ReductionService(ReductionRepository reductionRepository) {
        this.reductionRepository = reductionRepository;
    }

    public List<Reduction> getReductions(){
        return reductionRepository.getReductions();
    }
}
