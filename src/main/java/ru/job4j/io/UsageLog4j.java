package ru.job4j.io;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte dayOfMonth = 16;
        short month = 6;
        int year = 2021;
        char hemisphere = 'N';
        double latitude = 56.70035;
        float longitude = 41.341F;
        boolean active = true;
        long timeStamp = System.currentTimeMillis();
        LOG.debug(
                "Gps dayOfMonth : {}, month : {}, year: {}, hemisphere: {}, latitude: {},"
                + " longitude: {}, active: {}, timestamp: {}",
                dayOfMonth, month, year, hemisphere, latitude, longitude, active, timeStamp
        );
    }
}
