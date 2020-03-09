package com.cricketleague;

public class CricketCsvDto {
    public int runs;
    public double avg;
    public String playerName;
    public double strikRate;
    public int four;
    public int six;
    public int fourWickets;
    public int fiveWickets;
    public double economy;

    public CricketCsvDto(MostRunCsv mostRunCsv) {
     runs = mostRunCsv.runs;
     avg = mostRunCsv.avg;
     playerName = mostRunCsv.playerName;
     strikRate = mostRunCsv.strikRate;
     four = mostRunCsv.four;
     six = mostRunCsv.six;
    }

    public CricketCsvDto(MostBowlingCsv mostBowlingCsv) {
        avg = mostBowlingCsv.avg;
        strikRate = mostBowlingCsv.strikRate;
        playerName = mostBowlingCsv.playerName;
       fourWickets = mostBowlingCsv.fourWicket;
       fiveWickets = mostBowlingCsv.fiveWicket;
       economy = mostBowlingCsv.economy;
    }
}
