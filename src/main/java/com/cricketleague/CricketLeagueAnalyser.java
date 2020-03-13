package com.cricketleague;

import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class CricketLeagueAnalyser {

    public enum Cricket{RUNS,WICKETS};

    List <CricketCsvDto> cricketDTOCSVList;
    Map<String,CricketCsvDto> cricketCsvDtoMap;


    public CricketLeagueAnalyser() {
        this.cricketDTOCSVList = new ArrayList<>();
        this.cricketCsvDtoMap = new HashMap<>();
        SortedField.initalizesortFiled();
    }

    public int loadCricketData(Cricket cricket,String... csvFilePath) {
        cricketCsvDtoMap = CricketAdapterFactory.getCricketData(cricket,csvFilePath);
        return cricketCsvDtoMap.size();
    }

    public String getSortedCricketData(SortedField sortedField) {
        cricketDTOCSVList = cricketCsvDtoMap.values().stream().collect(Collectors.toList());
        if(cricketDTOCSVList == null || cricketDTOCSVList.size() == 0){
            throw new CricketAnalyserException("No Data",CricketAnalyserException.ExceptionType.CRICKET_DATA_NOT_FOUND);
        }
        this.sort(sortedField.sortedMap.get(sortedField));
        Collections.reverse(cricketDTOCSVList);
        String sortedStateCensus=new Gson().toJson(cricketDTOCSVList);
        return sortedStateCensus;
    }

    private void sort(Comparator<CricketCsvDto> cricketComparator) {
        for(int i = 0; i< this.cricketDTOCSVList.size()-1; i++){
            for(int j = 0; j< this.cricketDTOCSVList.size()-i-1; j++){
                CricketCsvDto run1 = this.cricketDTOCSVList.get(j);
                CricketCsvDto run2 = this.cricketDTOCSVList.get(j+1);
                if(cricketComparator.compare(run1,run2)>0){
                    cricketDTOCSVList.set(j,run2);
                    cricketDTOCSVList.set(j+1,run1);
                }
            }
        }
    }
}
