package com.cricketleague;

public class AllRounders implements java.util.Comparator<CricketCsvDto> {
    @Override
    public int compare(CricketCsvDto p1, CricketCsvDto p2) {
        int total = (p1.runs*p1.AllWicket)-(p2.runs*p2.AllWicket);
        return total;
    }
}
