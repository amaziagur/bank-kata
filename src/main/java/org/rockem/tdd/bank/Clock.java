package org.rockem.tdd.bank;

import java.time.LocalDate;

public class Clock implements ClockProvider {

    @Override
    public String getToday(){
        LocalDate now = LocalDate.now();
        return now.getDayOfMonth() + "/" + now.getMonthValue() + "/" + now.getYear();
    }
}
