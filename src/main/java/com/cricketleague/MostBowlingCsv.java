package com.cricketleague;

import com.opencsv.bean.CsvBindByName;

public class MostBowlingCsv {

    @CsvBindByName(column = "Avg",required = true)
    public double avg;

    @CsvBindByName(column = "PLAYER",required = true)
    public String playerName;

    @CsvBindByName(column = "4w",required = true)
    public int fourWicket;

    @CsvBindByName(column = "5w",required = true)
    public int fiveWicket;

    @CsvBindByName(column = "SR",required = true)
    public double strikRate;
}
