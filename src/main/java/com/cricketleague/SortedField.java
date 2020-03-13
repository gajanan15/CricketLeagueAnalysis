package com.cricketleague;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public enum SortedField {
    AVG, Strike_Rate, TOTAL_SIX_AND_FOUR, AVERAGE_WITH_STRIKE_RATE, MAX_RUN_WITH_BEST_AVG, BEST_STRIKE_RATE_WITH_SIX_AND_FOUR,
    Economy, BEST_STRIKE_RATE_WITH_FOUR_AND_FIVE_WICKETS, BEST_BOWLING_AVG_WITH_STRIKE_RATE, MAXIMUM_WICKET_WITH_BEST_BOWLING_AVG,
    BEST_BATTING_BOWLING_AVG, BEST_ALLROUNDER;

        static   Map<SortedField, Comparator<CricketCsvDto>> sortedMap = new HashMap<>();
        static void initalizesortFiled(){

            sortedMap.put(SortedField.AVG,Comparator.comparing(ipldata -> ipldata.avg));
            Comparator<CricketCsvDto> average = Comparator.comparing(ipldata -> ipldata.avg);
            sortedMap.put(SortedField.AVERAGE_WITH_STRIKE_RATE,average.thenComparing(ipldata -> ipldata.strikRate));
            sortedMap.put(SortedField.Strike_Rate,Comparator.comparing(ipldata -> ipldata.strikRate));
            sortedMap.put(SortedField.TOTAL_SIX_AND_FOUR,Comparator.comparing(ipldata -> ipldata.four+ipldata.six));
            Comparator<CricketCsvDto> csvDtoComparator = Comparator.comparing(ipldata -> ipldata.four+ipldata.six);
            sortedMap.put(SortedField.BEST_STRIKE_RATE_WITH_SIX_AND_FOUR,csvDtoComparator.thenComparing(ipldata -> ipldata.strikRate));
            Comparator<CricketCsvDto> maxRun = Comparator.comparing(ipldata -> ipldata.runs);
            sortedMap.put(SortedField.MAX_RUN_WITH_BEST_AVG,maxRun.thenComparing(ipldata -> ipldata.avg));
            sortedMap.put(SortedField.Economy,Comparator.comparing(ipldata -> ipldata.economy));
            Comparator<CricketCsvDto> fourAndFiveWickets = Comparator.comparing(ipldata -> ipldata.fiveWickets+ipldata.fourWickets);
            sortedMap.put(SortedField.BEST_STRIKE_RATE_WITH_FOUR_AND_FIVE_WICKETS,fourAndFiveWickets.thenComparing(ipldata -> ipldata.strikRate));
            sortedMap.put(SortedField.BEST_BOWLING_AVG_WITH_STRIKE_RATE,average.thenComparing(ipldata -> ipldata.strikRate));
            Comparator<CricketCsvDto> maxWickets = Comparator.comparing(ipldata -> ipldata.wickets);
            sortedMap.put(SortedField.MAXIMUM_WICKET_WITH_BEST_BOWLING_AVG,maxWickets.thenComparing(ipldata -> ipldata.avg));
            sortedMap.put(SortedField.BEST_BATTING_BOWLING_AVG, new CompareAverage());
            sortedMap.put(SortedField.BEST_ALLROUNDER, new AllRounders());

        }
}
