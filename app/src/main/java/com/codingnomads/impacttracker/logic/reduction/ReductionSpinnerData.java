package com.codingnomads.impacttracker.logic.reduction;

import java.util.List;
import java.util.Map;

public class ReductionSpinnerData {

    private List<String> reductionStrings;
    private Map<String,Reduction> reductionMap;

    public List<String> getReductionStrings() {
        return reductionStrings;
    }

    public void setReductionStrings(List<String> reductionStrings) {
        this.reductionStrings = reductionStrings;
    }

    public Map<String, Reduction> getReductionMap() {
        return reductionMap;
    }

    public void setReductionMap(Map<String, Reduction> reductionMap) {
        this.reductionMap = reductionMap;
    }
}
