package com.codingnomads.impacttracker.logic;

import com.codingnomads.impacttracker.R;

import java.util.ArrayList;
import java.util.List;

public class ImpactUtils {

    public static List<Impact> transformStatisticToImpactList (Statistic statistic) {
        List<Impact> impacts = new ArrayList<>();

        if (statistic.getTonsOfCo2() > 0) {
            Impact co2 = new Impact();
            co2.setAmount((long) statistic.getTonsOfCo2());
            co2.setUnits(" tons of CO2");
            co2.setBackgroundId(R.drawable.co2_card_background);
            impacts.add(co2);
        }

        if (statistic.getGallonsOfWater() > 0) {
            Impact water = new Impact();
            water.setAmount((long) statistic.getGallonsOfWater());
            water.setUnits(" gallons of water");
            water.setBackgroundId(R.drawable.water_card_background);
            impacts.add(water);
        }
        return impacts;
    }
}
