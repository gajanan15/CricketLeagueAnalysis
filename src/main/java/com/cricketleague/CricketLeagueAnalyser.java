package com.cricketleague;

import com.google.gson.Gson;
import com.jarfile.CsvBuilderFactory;
import com.jarfile.IcsvBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CricketLeagueAnalyser {

    public enum Cricket{RUNS,WICKETS};

    List <CricketCsvDto> cricketDTOCSVList;
    Map<SortedField,Comparator<CricketCsvDto>> sortedMap;
    Map<String,CricketCsvDto> cricketCsvDtoMap;

    public CricketLeagueAnalyser() {
        this.cricketDTOCSVList = new ArrayList<>();
        this.sortedMap = new HashMap<>();
        this.cricketCsvDtoMap = new HashMap<>();

        this.sortedMap.put(SortedField.AVG,Comparator.comparing(ipldata -> ipldata.avg));
        Comparator<CricketCsvDto> avrage = Comparator.comparing(ipldata -> ipldata.avg);
        this.sortedMap.put(SortedField.AVERAGEWITHSTRIKERATE,avrage.thenComparing(ipldata -> ipldata.strikRate));

        this.sortedMap.put(SortedField.StrikeRate,Comparator.comparing(ipldata -> ipldata.strikRate));

        this.sortedMap.put(SortedField.TOTALSIXANDFOUR,Comparator.comparing(ipldata -> ipldata.four+ipldata.six));
        Comparator<CricketCsvDto> csvDtoComparator = Comparator.comparing(ipldata -> ipldata.four+ipldata.six);
        this.sortedMap.put(SortedField.TOTALSIXANDFOUR,csvDtoComparator.thenComparing(ipldata -> ipldata.four+ipldata.six));

        this.sortedMap.put(SortedField.BESTSTRIKERATEWITHSIXANDFOUR,csvDtoComparator.thenComparing(ipldata -> ipldata.strikRate));

        this.sortedMap.put(SortedField.RUN,Comparator.comparing(ipldata -> ipldata.runs));
        Comparator<CricketCsvDto> maxRun = Comparator.comparing(ipldata -> ipldata.runs);
        this.sortedMap.put(SortedField.MAXRUNWITHBESTAVG,maxRun.thenComparing(ipldata -> ipldata.avg));

        this.sortedMap.put(SortedField.Economy,Comparator.comparing(ipldata -> ipldata.economy));

        Comparator<CricketCsvDto> fourAndFiveWickets = Comparator.comparing(ipldata -> ipldata.fiveWickets+ipldata.fourWickets);
        this.sortedMap.put(SortedField.BESTSTRIKERATEWITHFOURANDFIVEWICKETS,fourAndFiveWickets.thenComparing(ipldata -> ipldata.strikRate));
    }

    public int loadCricketData(Cricket cricket,String... csvFilePath) {
        cricketCsvDtoMap = CensusAdapterFactory.getCricketData(cricket,csvFilePath);
        return cricketCsvDtoMap.size();
    }

    public String getSortedCricketData(SortedField sortedField) {
        cricketDTOCSVList = cricketCsvDtoMap.values().stream().collect(Collectors.toList());
        if(cricketDTOCSVList == null || cricketDTOCSVList.size() == 0){
            throw new CricketAnalyserException("No Data",CricketAnalyserException.ExceptionType.CRICKET_DATA_NOT_FOUND);
        }
        this.sort(this.sortedMap.get(sortedField));
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
