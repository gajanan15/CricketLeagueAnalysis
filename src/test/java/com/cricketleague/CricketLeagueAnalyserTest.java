package com.cricketleague;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CricketLeagueAnalyserTest {

    private static CricketLeagueAnalyser cricketLeagueAnalyser;
    private static final String IPL_MOST_RUNS_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_MOST_BOWLING_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";
    private static final String IPL_WRONG_FILE_PATH = "./src/test/IPL2019FactsheetMostWkts.csv";

    @BeforeClass
    public static void setUp() throws Exception {
        cricketLeagueAnalyser = new CricketLeagueAnalyser();
    }
    CricketCsvDto[] cricketCsvDto;

    //Find Top Batting Average Player Name
    @Test
    public void givenCricketMostRunData_WhenSorted_ShouldReturnMostRun() {
        try {
            cricketLeagueAnalyser.loadCricketData(CricketLeagueAnalyser.Cricket.RUNS,IPL_MOST_RUNS_FILE_PATH);
            String sortedCricketData = cricketLeagueAnalyser.getSortedCricketData(SortedField.AVG);
            cricketCsvDto = new Gson().fromJson(sortedCricketData, CricketCsvDto[].class);
            Assert.assertEquals("MS Dhoni", cricketCsvDto[0].playerName);
        }catch (CricketAnalyserException cricketException){
            cricketException.printStackTrace();
        }
    }

    //Pass Wrong File Path
    @Test
    public void givenCricketData_WhenWrongFilePath_ShouldReturnException() {
        try{
            cricketLeagueAnalyser.loadCricketData(CricketLeagueAnalyser.Cricket.RUNS,IPL_WRONG_FILE_PATH);
            String sortedCricketData = cricketLeagueAnalyser.getSortedCricketData(SortedField.AVG);
            cricketCsvDto = new Gson().fromJson(sortedCricketData, CricketCsvDto[].class);
        }catch (CricketAnalyserException cricketException){
            Assert.assertEquals(cricketException.type,CricketAnalyserException.ExceptionType.CRICKET_FILE_PROBLEM);
        }
    }

    @Test
    public void givenCricketData_WhenWrongEnum_ShouldReturnException() {
        try{
            cricketLeagueAnalyser.loadCricketData(CricketLeagueAnalyser.Cricket.RUNS,IPL_MOST_BOWLING_FILE_PATH);
            String sortedCricketData = cricketLeagueAnalyser.getSortedCricketData(SortedField.AVG);
            cricketCsvDto = new Gson().fromJson(sortedCricketData, CricketCsvDto[].class);
        }catch (CricketAnalyserException cricketException){
            Assert.assertEquals(cricketException.type,CricketAnalyserException.ExceptionType.CRICKET_FILE_PROBLEM);
        }
    }

    //Find Top Strike Rate Player Name
    @Test
    public void givenCricketMostRunData_When_Sorted_ShouldReturnStrikeRate() {
        try{
            cricketLeagueAnalyser.loadCricketData(CricketLeagueAnalyser.Cricket.RUNS,IPL_MOST_RUNS_FILE_PATH,IPL_MOST_BOWLING_FILE_PATH);
            String sortedStrikeRateData = cricketLeagueAnalyser.getSortedCricketData(SortedField.Strike_Rate);
            cricketCsvDto = new Gson().fromJson(sortedStrikeRateData, CricketCsvDto[].class);
            Assert.assertEquals("Ishant Sharma",cricketCsvDto[0].playerName);
        }catch (CricketAnalyserException cricketException){
            cricketException.printStackTrace();
        }
    }

    //Player Who Hit Maximum Six And Four
    @Test
    public void givenCricketMostSixAndFour_When_Sorted_ShouldReturnTotalSixAndFour() {
        try{
            cricketLeagueAnalyser.loadCricketData(CricketLeagueAnalyser.Cricket.RUNS,IPL_MOST_RUNS_FILE_PATH);
            String sortedCricketData = cricketLeagueAnalyser.getSortedCricketData(SortedField.TOTAL_SIX_AND_FOUR);
            cricketCsvDto = new Gson().fromJson(sortedCricketData, CricketCsvDto[].class);
            Assert.assertEquals("Andre Russell",cricketCsvDto[0].playerName);
        }catch (CricketAnalyserException cricketException){
            cricketException.printStackTrace();
        }
    }

   // Player Who Had Best Striking Rates With Six And Four

    @Test
    public void givenCricketMostSixAndFour_When_Sorted_ShouldReturnMaximumStrikeRates() {
        try{
            cricketLeagueAnalyser.loadCricketData(CricketLeagueAnalyser.Cricket.RUNS,IPL_MOST_RUNS_FILE_PATH);
            String sortedCricketData = cricketLeagueAnalyser.getSortedCricketData(SortedField.BEST_STRIKE_RATE_WITH_SIX_AND_FOUR);
            cricketCsvDto = new Gson().fromJson(sortedCricketData, CricketCsvDto[].class);
            Assert.assertEquals("Andre Russell",cricketCsvDto[0].playerName);
        }catch (CricketAnalyserException cricketException){
            cricketException.printStackTrace();
        }
    }

    //Player Who Had Great Averages With The Best Striking Rates
    @Test
    public void givenCricketMostRunData_WhenStrikeRates_ShouldReturnMaximumAverages() {
        try{
            cricketLeagueAnalyser.loadCricketData(CricketLeagueAnalyser.Cricket.RUNS,IPL_MOST_RUNS_FILE_PATH);
            String sortedCricketData = cricketLeagueAnalyser.getSortedCricketData(SortedField.AVERAGE_WITH_STRIKE_RATE);
            cricketCsvDto = new Gson().fromJson(sortedCricketData, CricketCsvDto[].class);
            Assert.assertEquals("MS Dhoni",cricketCsvDto[0].playerName);
        }catch (CricketAnalyserException cricketException){
            cricketException.printStackTrace();
        }
    }

    //Player Who Hit Maximum Runs With Best Averages
    @Test
    public void givenCricketMostRunData_WhenSortedBattingAverage_ShouldReturnMaximumRun() {
        try{
            cricketLeagueAnalyser.loadCricketData(CricketLeagueAnalyser.Cricket.RUNS,IPL_MOST_RUNS_FILE_PATH);
            String sortedCricketData = cricketLeagueAnalyser.getSortedCricketData(SortedField.MAX_RUN_WITH_BEST_AVG);
            cricketCsvDto = new Gson().fromJson(sortedCricketData, CricketCsvDto[].class);
            Assert.assertEquals("David Warner",cricketCsvDto[0].playerName);
        }catch (CricketAnalyserException cricketException){
            cricketException.printStackTrace();
        }
    }

    //Find Top Bowling Average Player Name
    @Test
    public void givenCricketMostBowlingData_WhenSorted_ShouldReturnTopBowlingAverage() {
        try {
            cricketLeagueAnalyser.loadCricketData(CricketLeagueAnalyser.Cricket.WICKETS,IPL_MOST_BOWLING_FILE_PATH);
            String sortedCricketData = cricketLeagueAnalyser.getSortedCricketData(SortedField.AVG);
            cricketCsvDto = new Gson().fromJson(sortedCricketData, CricketCsvDto[].class);
            Assert.assertEquals("Krishnappa Gowtham",cricketCsvDto[0].playerName);
        }catch (CricketAnalyserException cricketException){
            cricketException.printStackTrace();
        }
    }

    //Find Top Strike Rate Player Name
    @Test
    public void givenCricketMostBowlingData_WhenSorted_ShouldReturnTopStrikeRates() {
        try{
            cricketLeagueAnalyser.loadCricketData(CricketLeagueAnalyser.Cricket.WICKETS,IPL_MOST_BOWLING_FILE_PATH);
            String sortedCricketData = cricketLeagueAnalyser.getSortedCricketData(SortedField.Strike_Rate);
            cricketCsvDto = new Gson().fromJson(sortedCricketData, CricketCsvDto[].class);
            Assert.assertEquals("Krishnappa Gowtham",cricketCsvDto[0].playerName);
        }catch (CricketAnalyserException cricketException){
            cricketException.printStackTrace();
        }
    }

    //Find Best Economy
    @Test
    public void givenCricketMostBowlingData_WhenSorted_ShouldReturnBestEconomy() {
        try{
            cricketLeagueAnalyser.loadCricketData(CricketLeagueAnalyser.Cricket.WICKETS,IPL_MOST_BOWLING_FILE_PATH);
            String sortedCricketData = cricketLeagueAnalyser.getSortedCricketData(SortedField.Economy);
            cricketCsvDto = new Gson().fromJson(sortedCricketData, CricketCsvDto[].class);
            Assert.assertEquals("Ben Cutting",cricketCsvDto[0].playerName);
        }catch (CricketAnalyserException cricketException){
            cricketException.printStackTrace();
        }
    }

    //Best Strike Rate With 4W and 5W
    @Test
    public void givenCricketMostBowlingDataWithWickets_WhenSorted_ShouldReturnPlayerName() {
        try{
            cricketLeagueAnalyser.loadCricketData(CricketLeagueAnalyser.Cricket.WICKETS,IPL_MOST_BOWLING_FILE_PATH);
            String sortedCricketData = cricketLeagueAnalyser.getSortedCricketData(SortedField.BEST_STRIKE_RATE_WITH_FOUR_AND_FIVE_WICKETS);
            cricketCsvDto = new Gson().fromJson(sortedCricketData, CricketCsvDto[].class);
            Assert.assertEquals("Lasith Malinga",cricketCsvDto[0].playerName);
        }catch (CricketAnalyserException cricketException){
            cricketException.printStackTrace();
        }
    }

    //Best Bowling Avg With Best String Rate

    @Test
    public void givenCricketMostBowlingDataWithStrikeRate_WhenSorted_ShouldReturnPlayerName() {
        try {
            cricketLeagueAnalyser.loadCricketData(CricketLeagueAnalyser.Cricket.WICKETS,IPL_MOST_BOWLING_FILE_PATH);
            String sortedCricketData = cricketLeagueAnalyser.getSortedCricketData(SortedField.BEST_BOWLING_AVG_WITH_STRIKE_RATE);
            cricketCsvDto= new Gson().fromJson(sortedCricketData, CricketCsvDto[].class);
            Assert.assertEquals("Krishnappa Gowtham",cricketCsvDto[0].playerName);
        }catch (CricketAnalyserException cricketException){
            cricketException.printStackTrace();
        }
    }

    //Maximum Wickets With Best Bowling Average

    @Test
    public void givenCricketMostBowlingDataWithBestBowlingAverage_WhenSorted_ShouldReturnPlayerName() {
        try {
            cricketLeagueAnalyser.loadCricketData(CricketLeagueAnalyser.Cricket.WICKETS,IPL_MOST_BOWLING_FILE_PATH);
            String sortedCricketData = cricketLeagueAnalyser.getSortedCricketData(SortedField.MAXIMUM_WICKET_WITH_BEST_BOWLING_AVG);
            cricketCsvDto = new Gson().fromJson(sortedCricketData, CricketCsvDto[].class);
            Assert.assertEquals("Imran Tahir",cricketCsvDto[0].playerName);
        }catch (CricketAnalyserException cricketException){
            cricketException.printStackTrace();
        }
    }

    //Best Batting And Bowling Average
    @Test
    public void givenCricketDataBestBattingAndBowlingAverage_WhenSorted_ShouldReturnPlayerName() {
        try {
            cricketLeagueAnalyser.loadCricketData(CricketLeagueAnalyser.Cricket.RUNS,IPL_MOST_RUNS_FILE_PATH,IPL_MOST_BOWLING_FILE_PATH);
            String sortedCricketData = cricketLeagueAnalyser.getSortedCricketData(SortedField.BEST_BATTING_BOWLING_AVG);
            cricketCsvDto = new Gson().fromJson(sortedCricketData, CricketCsvDto[].class);
            Assert.assertEquals("Krishnappa Gowtham",cricketCsvDto[0].playerName);
        }catch (CricketAnalyserException cricketException){
            cricketException.printStackTrace();
        }
    }

    //Find AllRounder (Maximum Wicket And Run)
    @Test
    public void givenCricketDataBestAllRounders_WhenSorted_ShouldReturnPlayerName() {
        try{
            cricketLeagueAnalyser.loadCricketData(CricketLeagueAnalyser.Cricket.RUNS,IPL_MOST_RUNS_FILE_PATH,IPL_MOST_BOWLING_FILE_PATH);
            String sortedCricketData = cricketLeagueAnalyser.getSortedCricketData(SortedField.BEST_ALLROUNDER);
            cricketCsvDto = new Gson().fromJson(sortedCricketData, CricketCsvDto[].class);
            Assert.assertEquals("Hardik Pandya",cricketCsvDto[0].playerName);
        }catch (CricketAnalyserException cricketException){
            cricketException.printStackTrace();
        }
    }
}
