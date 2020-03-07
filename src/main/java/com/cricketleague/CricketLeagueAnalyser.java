package com.cricketleague;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;

public class CricketLeagueAnalyser {
    List <MostRunCsv> cricketCSVList=null;

    public CricketLeagueAnalyser() {
        this.cricketCSVList = new ArrayList<>();
    }

    public int loadCricketData(String csvFilePath) {
        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
            CsvToBeanBuilder<MostRunCsv> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(MostRunCsv.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<MostRunCsv> csvToBean = csvToBeanBuilder.build();
            Iterator<MostRunCsv> censusCSVIterator = csvToBean.iterator();
            int numOfEateries=0;
            while (censusCSVIterator.hasNext()){
                numOfEateries++;
                MostRunCsv runsData = censusCSVIterator.next();
                cricketCSVList.add(runsData);
            }
            return numOfEateries;
        }catch (IOException e){
           throw  new CricketAnalyserException(e.getMessage(),CricketAnalyserException.ExceptionType.CRICKET_FILE_PROBLEM);
        }
    }

    public String getSortedCricketData() {

        if(cricketCSVList == null || cricketCSVList.size() == 0){
            throw new CricketAnalyserException("No Data",CricketAnalyserException.ExceptionType.CRICKET_DATA_NOT_FOUND);
        }
        Comparator<MostRunCsv> cricketComparator = Comparator.comparing(census -> census.battingAvg);
        this.sort(cricketCSVList,cricketComparator);
        Collections.reverse(cricketCSVList);
        String sortedStateCensus=new Gson().toJson(cricketCSVList);
        return sortedStateCensus;
    }

    private void sort(List<MostRunCsv> cricketCSVList, Comparator<MostRunCsv> censusComparator) {
        for(int i=0;i<cricketCSVList.size()-1;i++){
            for(int j=0;j<cricketCSVList.size()-i-1;j++){
                MostRunCsv run1 = cricketCSVList.get(j);
                MostRunCsv run2 = cricketCSVList.get(j+1);
                if(censusComparator.compare(run1,run2)>0){
                    cricketCSVList.set(j,run2);
                    cricketCSVList.set(j+1,run1);
                }
            }
        }
    }
}
