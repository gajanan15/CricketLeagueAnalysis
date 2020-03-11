package com.cricketleague;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CricketLeagueAnalyserTest {

    private static CricketLeagueAnalyser cricketLeagueAnalyser;
    private static final String IPL_MOST_RUNS_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_MOST_BOWLING_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";

    @BeforeClass
    public static void setUp() throws Exception {
        cricketLeagueAnalyser = new CricketLeagueAnalyser();
    }

    //Find Top Batting Average Player Name
    @Test
    public void givenCricketMostRunData_WhenSorted_ShouldReturnMostRun() {
        try {
            cricketLeagueAnalyser.loadCricketData(CricketLeagueAnalyser.Cricket.RUNS,IPL_MOST_RUNS_FILE_PATH);
            String sortedCricketData = cricketLeagueAnalyser.getSortedCricketData(SortedField.AVG);
            MostRunCsv[] mostRunCsv = new Gson().fromJson(sortedCricketData, MostRunCsv[].class);
            Assert.assertEquals("MS Dhoni", mostRunCsv[0].playerName);
        }catch (CricketAnalyserException e){}
    }

    //Find Top Strike Rate Player Name
    @Test
    public void givenCricketMostRunData_When_Sorted_ShouldReturnStrikeRate() {
        try{
            cricketLeagueAnalyser.loadCricketData(CricketLeagueAnalyser.Cricket.RUNS,IPL_MOST_RUNS_FILE_PATH,IPL_MOST_BOWLING_FILE_PATH);
            String sortedStrikeRateData = cricketLeagueAnalyser.getSortedCricketData(SortedField.Strike_Rate);
            MostRunCsv[] mostRunCsv = new Gson().fromJson(sortedStrikeRateData, MostRunCsv[].class);
            Assert.assertEquals("Ishant Sharma",mostRunCsv[0].playerName);
        }catch (CricketAnalyserException e){}
    }

    //Player Who Hit Maximum Six And Four
    @Test
    public void givenCricketMostSixAndFour_When_Sorted_ShouldReturnTotalSixAndFour() {
        try{
            cricketLeagueAnalyser.loadCricketData(CricketLeagueAnalyser.Cricket.RUNS,IPL_MOST_RUNS_FILE_PATH);
            String sortedCricketData = cricketLeagueAnalyser.getSortedCricketData(SortedField.TOTAL_SIX_AND_FOUR);
            MostRunCsv[] mostRunCsv = new Gson().fromJson(sortedCricketData, MostRunCsv[].class);
            Assert.assertEquals("Andre Russell",mostRunCsv[0].playerName);
        }catch (CricketAnalyserException e){}
    }

   // Player Who Had Best Striking Rates With Six And Four

    @Test
    public void givenCricketMostSixAndFour_When_Sorted_ShouldReturnMaximumStrikeRates() {
        try{
            cricketLeagueAnalyser.loadCricketData(CricketLeagueAnalyser.Cricket.RUNS,IPL_MOST_RUNS_FILE_PATH);
            String sortedCricketData = cricketLeagueAnalyser.getSortedCricketData(SortedField.BEST_STRIKE_RATE_WITH_SIX_AND_FOUR);
            MostRunCsv[] mostRunCsv = new Gson().fromJson(sortedCricketData, MostRunCsv[].class);
            Assert.assertEquals("Andre Russell",mostRunCsv[0].playerName);
        }catch (CricketAnalyserException e){}
    }

    //Player Who Had Great Averages With The Best Striking Rates
    @Test
    public void givenCricketMostRunData_WhenStrikeRates_ShouldReturnMaximumAverages() {
        try{
            cricketLeagueAnalyser.loadCricketData(CricketLeagueAnalyser.Cricket.RUNS,IPL_MOST_RUNS_FILE_PATH);
            String sortedCricketData = cricketLeagueAnalyser.getSortedCricketData(SortedField.AVERAGE_WITH_STRIKE_RATE);
            MostRunCsv[] mostRunCsv = new Gson().fromJson(sortedCricketData, MostRunCsv[].class);
            Assert.assertEquals("MS Dhoni",mostRunCsv[0].playerName);
        }catch (CricketAnalyserException e){}
    }

    //Player Who Hit Maximum Runs With Best Averages
    @Test
    public void givenCricketMostRunData_WhenSortedBattingAverage_ShouldReturnMaximumRun() {
        try{
            cricketLeagueAnalyser.loadCricketData(CricketLeagueAnalyser.Cricket.RUNS,IPL_MOST_RUNS_FILE_PATH);
            String sortedCricketData = cricketLeagueAnalyser.getSortedCricketData(SortedField.MAX_RUN_WITH_BEST_AVG);
            MostRunCsv[] mostRunCsv = new Gson().fromJson(sortedCricketData, MostRunCsv[].class);
            Assert.assertEquals("David Warner",mostRunCsv[0].playerName);
        }catch (CricketAnalyserException e){}
    }

    //Find Top Bowling Average Player Name
    @Test
    public void givenCricketMostBowlingData_WhenSorted_ShouldReturnTopBowlingAverage() {
        try {
            cricketLeagueAnalyser.loadCricketData(CricketLeagueAnalyser.Cricket.WICKETS,IPL_MOST_BOWLING_FILE_PATH);
            String sortedCricketData = cricketLeagueAnalyser.getSortedCricketData(SortedField.AVG);
            MostBowlingCsv[] mostBowlingCsv = new Gson().fromJson(sortedCricketData, MostBowlingCsv[].class);
            Assert.assertEquals("Krishnappa Gowtham",mostBowlingCsv[0].playerName);
        }catch (CricketAnalyserException e){}
    }

    //Find Top Strike Rate Player Name
    @Test
    public void givenCricketMostBowlingData_WhenSorted_ShouldReturnTopStrikeRates() {
        try{
            cricketLeagueAnalyser.loadCricketData(CricketLeagueAnalyser.Cricket.WICKETS,IPL_MOST_BOWLING_FILE_PATH);
            String sortedCricketData = cricketLeagueAnalyser.getSortedCricketData(SortedField.Strike_Rate);
            MostBowlingCsv[] mostBowlingCsv = new Gson().fromJson(sortedCricketData, MostBowlingCsv[].class);
            Assert.assertEquals("Krishnappa Gowtham",mostBowlingCsv[0].playerName);
        }catch (CricketAnalyserException e){}
    }

    //Find Best Economy
    @Test
    public void givenCricketMostBowlingData_WhenSorted_ShouldReturnBestEconomy() {
        try{
            cricketLeagueAnalyser.loadCricketData(CricketLeagueAnalyser.Cricket.WICKETS,IPL_MOST_BOWLING_FILE_PATH);
            String sortedCricketData = cricketLeagueAnalyser.getSortedCricketData(SortedField.Economy);
            MostBowlingCsv[] mostBowlingCsv = new Gson().fromJson(sortedCricketData, MostBowlingCsv[].class);
            Assert.assertEquals("Ben Cutting",mostBowlingCsv[0].playerName);
        }catch (CricketAnalyserException e){}
    }

    //Best Strike Rate With 4W and 5W
    @Test
    public void givenCricketMostBowlingDataWithWickets_WhenSorted_ShouldReturnPlayerName() {
        try{
            cricketLeagueAnalyser.loadCricketData(CricketLeagueAnalyser.Cricket.WICKETS,IPL_MOST_BOWLING_FILE_PATH);
            String sortedCricketData = cricketLeagueAnalyser.getSortedCricketData(SortedField.BEST_STRIKE_RATE_WITH_FOUR_AND_FIVE_WICKETS);
            MostBowlingCsv[] mostBowlingCsvs = new Gson().fromJson(sortedCricketData, MostBowlingCsv[].class);
            Assert.assertEquals("Lasith Malinga",mostBowlingCsvs[0].playerName);
        }catch (CricketAnalyserException e){}
    }

    //Best Bowling Avg With Best String Rate

    @Test
    public void givenCricketMostBowlingDataWithStrikeRate_WhenSorted_ShouldReturnPlayerName() {
        try {
            cricketLeagueAnalyser.loadCricketData(CricketLeagueAnalyser.Cricket.WICKETS,IPL_MOST_BOWLING_FILE_PATH);
            String sortedCricketData = cricketLeagueAnalyser.getSortedCricketData(SortedField.BEST_BOWLING_AVG_WITH_STRIKE_RATE);
            MostBowlingCsv[] mostBowlingCsv = new Gson().fromJson(sortedCricketData, MostBowlingCsv[].class);
            Assert.assertEquals("Krishnappa Gowtham",mostBowlingCsv[0].playerName);
        }catch (CricketAnalyserException e){}
    }

    //Maximum Wickets With Best Bowling Average

    @Test
    public void givenCricketMostBowlingDataWithBestBowlingAverage_WhenSorted_ShouldReturnPlayerName() {
        try {
            cricketLeagueAnalyser.loadCricketData(CricketLeagueAnalyser.Cricket.WICKETS,IPL_MOST_BOWLING_FILE_PATH);
            String sortedCricketData = cricketLeagueAnalyser.getSortedCricketData(SortedField.MAXIMUM_WICKET_WITH_BEST_BOWLING_AVG);
            MostBowlingCsv[] mostBowlingCsv = new Gson().fromJson(sortedCricketData, MostBowlingCsv[].class);
            Assert.assertEquals("Imran Tahir",mostBowlingCsv[0].playerName);
        }catch (CricketAnalyserException e){}
    }

    //Best Batting And Bowling Average
    @Test
    public void givenCricketDataBestBattingAndBowlingAverage_WhenSorted_ShouldReturnPlayerName() {
        try {
            cricketLeagueAnalyser.loadCricketData(CricketLeagueAnalyser.Cricket.RUNS,IPL_MOST_RUNS_FILE_PATH,IPL_MOST_BOWLING_FILE_PATH);
            String sortedCricketData = cricketLeagueAnalyser.getSortedCricketData(SortedField.BEST_BATTING_BOWLING_AVG);
            MostRunCsv[] mostBowlingCsv = new Gson().fromJson(sortedCricketData, MostRunCsv[].class);
            Assert.assertEquals("Marcus Stoinis",mostBowlingCsv[0].playerName);
        }catch (CricketAnalyserException e){}
    }

    //Find AllRounder (Maximum Wicket And Run)
    @Test
    public void givenCricketDataBestAllRounders_WhenSorted_ShouldReturnPlayerName() {
        try{
            cricketLeagueAnalyser.loadCricketData(CricketLeagueAnalyser.Cricket.ALLROUNDERSMAIN,IPL_MOST_RUNS_FILE_PATH,IPL_MOST_BOWLING_FILE_PATH);
            String sortedCricketData = cricketLeagueAnalyser.getSortedCricketData(SortedField.BEST_ALLROUNDER);
            System.out.println(sortedCricketData);
            MostRunCsv[] mostRunCsv = new Gson().fromJson(sortedCricketData, MostRunCsv[].class);
            Assert.assertEquals("Hardik Pandya",mostRunCsv[0].playerName);
        }catch (CricketAnalyserException e){}
    }
}
