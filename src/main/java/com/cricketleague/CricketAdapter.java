package com.cricketleague;

import com.jarfile.CsvBuilderFactory;
import com.jarfile.IcsvBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public abstract  class CricketAdapter extends RuntimeException{
    public abstract Map<String,CricketCsvDto> loadCricketData(String...csvFilePath);

    public <E> Map<String,CricketCsvDto> loadCricketData(Class<E> cricketCSVClass,String csvFilePath) {
        Map<String,CricketCsvDto> cricketMap = new HashMap<>();
        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
            IcsvBuilder opencsvBuilder = CsvBuilderFactory.getOpenCsvBuilder();
            Iterator<E> cricketCsvIterator = opencsvBuilder.getCsvFileIterator(reader,cricketCSVClass);
            Iterable<E> cricketCsvIterable = ()->cricketCsvIterator;

    if(cricketCSVClass.getName().equals("com.cricketleague.MostRunCsv")){
        StreamSupport.stream(cricketCsvIterable.spliterator(),false)
                .map(MostRunCsv.class::cast)
                .forEach(mostRunCsv -> cricketMap.put(mostRunCsv.playerName,new CricketCsvDto(mostRunCsv)));
    }
    else if(cricketCSVClass.getName().equals("com.cricketleague.MostBowlingCsv")){
        StreamSupport.stream(cricketCsvIterable.spliterator(),false)
                .map(MostBowlingCsv.class::cast)
                .forEach(mostBowlingCsv -> cricketMap.put(mostBowlingCsv.playerName,new CricketCsvDto(mostBowlingCsv)));
    }
            return cricketMap;
        }catch (IOException e){
            throw  new CricketAnalyserException(e.getMessage(),CricketAnalyserException.ExceptionType.CRICKET_FILE_PROBLEM);
        }
    }
}
