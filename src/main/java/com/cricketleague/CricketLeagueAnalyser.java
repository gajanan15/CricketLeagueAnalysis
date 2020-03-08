package com.cricketleague;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CricketLeagueAnalyser {
    List <MostRunCsv> cricketCSVList;
    Map<SortedField,Comparator<MostRunCsv>> sortedMap;

    public CricketLeagueAnalyser() {
        this.cricketCSVList = new ArrayList<>();
        this.sortedMap = new HashMap<>();

        this.sortedMap.put(SortedField.AVG,Comparator.comparing(ipldata -> ipldata.battingAvg));
        this.sortedMap.put(SortedField.StrikeRate,Comparator.comparing(ipldata -> ipldata.strikRate));
        this.sortedMap.put(SortedField.MAXIMUM_SIX,Comparator.comparing(ipldata -> ipldata.four+ipldata.six));
    }

    public int loadCricketData(String csvFilePath) {
        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
            CsvToBeanBuilder<MostRunCsv> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(MostRunCsv.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<MostRunCsv> csvToBean = csvToBeanBuilder.build();
            Iterator<MostRunCsv> mostRunCsvIterator = csvToBean.iterator();
            int numOfEateries=0;
            while (mostRunCsvIterator.hasNext()){
                numOfEateries++;
                MostRunCsv runsData = mostRunCsvIterator.next();
               cricketCSVList.add(runsData);
            }

            return numOfEateries;
        }catch (IOException e){
           throw  new CricketAnalyserException(e.getMessage(),CricketAnalyserException.ExceptionType.CRICKET_FILE_PROBLEM);
        }
    }

    public String getSortedCricketData(SortedField sortedField) {

        if(cricketCSVList == null || cricketCSVList.size() == 0){
            throw new CricketAnalyserException("No Data",CricketAnalyserException.ExceptionType.CRICKET_DATA_NOT_FOUND);
        }
        this.sort(cricketCSVList,this.sortedMap.get(sortedField));
        Collections.reverse(cricketCSVList);
        String sortedStateCensus=new Gson().toJson(cricketCSVList);
        return sortedStateCensus;
    }

    private void sort(List<MostRunCsv> cricketCSVList, Comparator<MostRunCsv> cricketComparator) {
        for(int i=0;i<cricketCSVList.size()-1;i++){
            for(int j=0;j<cricketCSVList.size()-i-1;j++){
                MostRunCsv run1 = cricketCSVList.get(j);
                MostRunCsv run2 = cricketCSVList.get(j+1);
                if(cricketComparator.compare(run1,run2)>0){
                    cricketCSVList.set(j,run2);
                    cricketCSVList.set(j+1,run1);
                }
            }
        }
    }

}
