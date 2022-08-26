package com.kolosovskyi.agency.service;

import java.time.LocalDate;

public class TourService {
    private TourService(){}
    private static class DateEqualsHolder{
        private static final TourService INSTANCE = new TourService();
    }

    public static TourService getInstance(){
        return DateEqualsHolder.INSTANCE;
    }

    public boolean isEnded(LocalDate orderDate, LocalDate nowDate){
        int result = nowDate.compareTo(orderDate);
        return result < 0;
    }
}
