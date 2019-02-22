package com.codingnomads.impacttracker.logic.reduction;

import java.util.List;
import java.util.Map;

public class ReductionSpinnerData {

    private List<String> reductionStrings;
    private Map<String,Integer> reductionIdMap;

    public List<String> getReductionStrings() {
        return reductionStrings;
    }

    public void setReductionStrings(List<String> reductionStrings) {
        this.reductionStrings = reductionStrings;
    }

    public Map<String, Integer> getReductionIdMap() {
        return reductionIdMap;
    }

    public void setReductionIdMap(Map<String, Integer> reductionIdMap) {
        this.reductionIdMap = reductionIdMap;
    }
}
