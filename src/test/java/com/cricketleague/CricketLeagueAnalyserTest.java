package com.cricketleague;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CricketLeagueAnalyserTest {

    private static CricketLeagueAnalyser cricketLeagueAnalyser;
    private static final String IPL_MOST_RUNS_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";

    @BeforeClass
    public static void setUp() throws Exception {
        cricketLeagueAnalyser = new CricketLeagueAnalyser();
    }

    @Test
    public void givenCricketMostRunData_WhenSorted_ShouldReturnMostRun() {
        try {
            cricketLeagueAnalyser.loadCricketData(IPL_MOST_RUNS_FILE_PATH);
            String sortedCricketData = cricketLeagueAnalyser.getSortedCricketData(SortedField.AVG);
            MostRunCsv[] mostRunCsv = new Gson().fromJson(sortedCricketData, MostRunCsv[].class);
            Assert.assertEquals(83.2, mostRunCsv[0].battingAvg, 0.0);
        }catch (CricketAnalyserException e){}
    }

    @Test
    public void givenCricketMostRunData_When_Sorted_ShouldReturnStrikeRate() {
        try{
            cricketLeagueAnalyser.loadCricketData(IPL_MOST_RUNS_FILE_PATH);
            String sortedStrikeRateData = cricketLeagueAnalyser.getSortedCricketData(SortedField.StrikeRate);
            MostRunCsv[] mostRunCsv = new Gson().fromJson(sortedStrikeRateData, MostRunCsv[].class);
            Assert.assertEquals(333.33,mostRunCsv[0].strikRate,0.0);
        }catch (CricketAnalyserException e){}
    }

    @Test
    public void givenCricketMostSixAndFour_When_Sorted_ShouldReturnTotalSixAndFour() {
        try{
            cricketLeagueAnalyser.loadCricketData(IPL_MOST_RUNS_FILE_PATH);
            String sortedCricketData = cricketLeagueAnalyser.getSortedCricketData(SortedField.MAXIMUM_SIX);
            MostRunCsv[] mostRunCsv = new Gson().fromJson(sortedCricketData, MostRunCsv[].class);
            Assert.assertEquals(83,mostRunCsv[0].six+mostRunCsv[0].four);
        }catch (CricketAnalyserException e){}
    }

    @Test
    public void givenCricketMostSixAndFour_When_Sorted_ShouldReturnMaximumStrikeRates() {
        try{
            cricketLeagueAnalyser.loadCricketData(IPL_MOST_RUNS_FILE_PATH);
            String sortedCricketData = cricketLeagueAnalyser.getSortedCricketData(SortedField.MAXIMUM_SIX);
            MostRunCsv[] mostRunCsv = new Gson().fromJson(sortedCricketData, MostRunCsv[].class);
            Assert.assertEquals(204.81,mostRunCsv[0].strikRate,0.0);
        }catch (CricketAnalyserException e){}
    }
}
