package com.cricketleague;

import com.opencsv.bean.CsvBindByName;

public class MostRunCsv {

    @CsvBindByName(column = "Avg",required = true)
    public double battingAvg;

    @CsvBindByName(column = "SR",required = true)
    public double strikRate;

    @CsvBindByName(column = "4s",required = true)
    public int four;

    @CsvBindByName(column = "6s",required = true)
    public int six;

    @CsvBindByName(column = "Runs",required = true)
    public int runs;
}
