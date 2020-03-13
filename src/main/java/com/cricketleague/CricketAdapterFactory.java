package com.cricketleague;

import java.util.Map;

public class CricketAdapterFactory {
    public static Map<String,CricketCsvDto> getCricketData(CricketLeagueAnalyser.Cricket cricket,String...csvFilePath){
        if(cricket.equals(CricketLeagueAnalyser.Cricket.RUNS)){
            return new CricketRunsAdapter().loadCricketData(csvFilePath);
        }
        if(cricket.equals(CricketLeagueAnalyser.Cricket.WICKETS)){
            return new BowlingAdapter().loadCricketData(csvFilePath);
        }
        throw new CricketAnalyserException("NO Records Found",CricketAnalyserException.ExceptionType.CRICKET_DATA_NOT_FOUND);
    }

}
