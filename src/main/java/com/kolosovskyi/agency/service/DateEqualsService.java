package com.kolosovskyi.agency.service;

import com.kolosovskyi.agency.dao.UserToursDAO;

import java.time.LocalDate;

public class DateEqualsService {
    private DateEqualsService(){}
    private static class DateEqualsHolder{
        private static final DateEqualsService INSTANCE = new DateEqualsService();
    }

    public static DateEqualsService getInstance(){
        return DateEqualsHolder.INSTANCE;
    }

    public boolean isCanceled(LocalDate orderDate, LocalDate nowDate){
        int result = nowDate.compareTo(orderDate);
        return result < 0;
    }
}
