package com.cricketleague;

public class CricketCsvDto {
    public int runs;
    public double avg;
    public double battingAvg;
    public String playerName;
    public double strikRate;
    public int four;
    public int six;
    public int fourWickets;
    public int fiveWickets;
    public double economy;
    public int wickets;
    public double bowlerAvg;
    public int AllWicket;

    public CricketCsvDto(MostRunCsv mostRunCsv) {
     runs = mostRunCsv.runs;
     avg = mostRunCsv.avg;
     battingAvg = mostRunCsv.avg;
     playerName = mostRunCsv.playerName;
     strikRate = mostRunCsv.strikRate;
     four = mostRunCsv.four;
     six = mostRunCsv.six;
    }

    public CricketCsvDto(MostBowlingCsv mostBowlingCsv) {
        bowlerAvg = mostBowlingCsv.avg;
        avg = mostBowlingCsv.avg;
        strikRate = mostBowlingCsv.strikRate;
        playerName = mostBowlingCsv.playerName;
       fourWickets = mostBowlingCsv.fourWicket;
       fiveWickets = mostBowlingCsv.fiveWicket;
       economy = mostBowlingCsv.economy;
       wickets = mostBowlingCsv.wickets;
    }
}
