package com.cricketleague;

import java.util.Map;

public class CricketRunsAdapter extends CricketAdapter {
    @Override
    public Map<String, CricketCsvDto> loadCricketData(String... csvFilePath) {
        Map<String,CricketCsvDto> cricketCsvMap = super.loadCricketData(MostRunCsv.class,csvFilePath[0]);
        return cricketCsvMap;
    }
}
