package com.cricketleague;

import java.util.Comparator;

public class CompareAverage implements Comparator<CricketCsvDto> {
    @Override
    public int compare(CricketCsvDto p1, CricketCsvDto p2) {
        int avg =  ((int)(p1.battingAvg+p1.bowlerAvg));
        return avg;
    }
}
