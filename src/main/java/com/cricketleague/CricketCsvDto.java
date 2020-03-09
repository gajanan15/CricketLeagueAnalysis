package com.cricketleague;

public class CricketCsvDto {
    public int runs;
    public double avg;
    public double strikRate;
    public int four;
    public int six;
    public int fourWickets;
    public int fiveWickets;

    public CricketCsvDto(MostRunCsv mostRunCsv) {
     runs = mostRunCsv.runs;
     avg = mostRunCsv.avg;
     strikRate = mostRunCsv.strikRate;
     four = mostRunCsv.four;
     six = mostRunCsv.six;
    }

    public CricketCsvDto(MostBowlingCsv mostBowlingCsv) {
        avg = mostBowlingCsv.avg;
       fourWickets = mostBowlingCsv.fourWicket;
       fiveWickets = mostBowlingCsv.fiveWicket;
    }
}
