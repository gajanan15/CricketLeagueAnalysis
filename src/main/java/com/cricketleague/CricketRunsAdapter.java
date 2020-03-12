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

public class CricketRunsAdapter extends CricketAdapter {
    Map<String,CricketCsvDto> cricketCsvMap =new HashMap<>();
    @Override
    public Map<String, CricketCsvDto> loadCricketData(String... csvFilePath) {
        Map<String, CricketCsvDto> cricketCsvMap = super.loadCricketData(MostRunCsv.class, csvFilePath[0]);
        if (csvFilePath.length > 1)
            this.loadBowlerData(cricketCsvMap,csvFilePath[1]);
        return cricketCsvMap;
    }

    private int loadBowlerData(Map<String, CricketCsvDto> cricketCsvMap, String csvFilePath){
        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
            IcsvBuilder icsvBuilder = CsvBuilderFactory.getOpenCsvBuilder();
            Iterator<MostBowlingCsv> mostBowlingCsvIterator = icsvBuilder.getCsvFileIterator(reader,MostBowlingCsv.class);
            Iterable<MostBowlingCsv> mostBowlingCsvIterable = ()->mostBowlingCsvIterator;
            StreamSupport.stream(mostBowlingCsvIterable.spliterator(),false)
                    .filter(mostBowlingCsv -> cricketCsvMap.get(mostBowlingCsv.playerName)!=null)
                    .forEach(mostBowlingCsv -> {
                        cricketCsvMap.get(mostBowlingCsv.playerName).bowlerAvg = mostBowlingCsv.avg;
                        cricketCsvMap.get(mostBowlingCsv.playerName).AllWicket = mostBowlingCsv.wickets;
                    });
            return cricketCsvMap.size();
        } catch (IOException e) {
            throw new CricketAnalyserException(e.getMessage(),CricketAnalyserException.ExceptionType.CRICKET_FILE_PROBLEM);
        }
    }

}
