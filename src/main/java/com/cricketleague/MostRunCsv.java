package com.cricketleague;

import com.opencsv.bean.CsvBindByName;

public class MostRunCsv {

    @CsvBindByName(column = "Avg",required = true)
    public double battingAvg;

    @CsvBindByName(column = "SR",required = true)
    public double strikRate;
}
