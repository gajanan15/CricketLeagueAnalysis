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
    List <CricketCsvDto> cricketDTOCSVList;
    Map<SortedField,Comparator<CricketCsvDto>> sortedMap;
    Map<String,CricketCsvDto> cricketCsvDtoMap;

    public CricketLeagueAnalyser() {
        this.cricketDTOCSVList = new ArrayList<>();
        this.sortedMap = new HashMap<>();
        this.cricketCsvDtoMap = new HashMap<>();

        this.sortedMap.put(SortedField.AVG,Comparator.comparing(ipldata -> ipldata.avg));
        this.sortedMap.put(SortedField.StrikeRate,Comparator.comparing(ipldata -> ipldata.strikRate));
        this.sortedMap.put(SortedField.MAXIMUM_SIX,Comparator.comparing(ipldata -> ipldata.four+ipldata.six));
        this.sortedMap.put(SortedField.RUN,Comparator.comparing(ipldata -> ipldata.runs));
    }

    public int loadCricketData(String csvFilePath) {
        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
            IcsvBuilder icsvBuilder = CsvBuilderFactory.getOpenCsvBuilder();
            Iterator<MostRunCsv> mostRunCsvIterator = icsvBuilder.getCsvFileIterator(reader,MostRunCsv.class);

            while (mostRunCsvIterator.hasNext()){
                MostRunCsv mostRunCsv = mostRunCsvIterator.next();
                this.cricketCsvDtoMap.put(mostRunCsv.playerName,new CricketCsvDto(mostRunCsv));
            }

            cricketDTOCSVList = cricketCsvDtoMap.values().stream().collect(Collectors.toList());
            return cricketDTOCSVList.size();
        }catch (IOException e){
           throw  new CricketAnalyserException(e.getMessage(),CricketAnalyserException.ExceptionType.CRICKET_FILE_PROBLEM);
        }
    }

    public String getSortedCricketData(SortedField sortedField) {

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

    public int loadBowlingData(String csvFilePath) {
        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
            IcsvBuilder icsvBuilder = CsvBuilderFactory.getOpenCsvBuilder();
            Iterator<MostBowlingCsv> mostBowlingCsvIterator = icsvBuilder.getCsvFileIterator(reader,MostBowlingCsv.class);

            while (mostBowlingCsvIterator.hasNext()){
                MostBowlingCsv mostBowlingCsv = mostBowlingCsvIterator.next();
                this.cricketCsvDtoMap.put(mostBowlingCsv.playerName,new CricketCsvDto(mostBowlingCsv));
            }
            cricketDTOCSVList = cricketCsvDtoMap.values().stream().collect(Collectors.toList());
            return cricketDTOCSVList.size();
        }catch (IOException e){
            throw  new CricketAnalyserException(e.getMessage(),CricketAnalyserException.ExceptionType.CRICKET_FILE_PROBLEM);
        }
    }
}
