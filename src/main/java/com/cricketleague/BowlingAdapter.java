package com.cricketleague;

import java.util.Map;

public class BowlingAdapter extends CricketAdapter {
    @Override
    public Map<String, CricketCsvDto> loadCricketData(String... csvFilePath) {
        Map<String,CricketCsvDto> cricketCsvMap = super.loadCricketData(MostBowlingCsv.class,csvFilePath[0]);
        return cricketCsvMap;
    }
}
