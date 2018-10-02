package com.myproject.contactbook.util;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Component
public class DateUtil {

    private String dateFormat = "yyyy-MM-dd'T'HH:mm:ssXXX";
    private String dateFormatOutput = "yyyy-MM-dd HH:mm:ss";

    public Date currentDate;


    public Date getDate(String dateStr) throws ParseException {
        setTimeZone();
        Date date = new SimpleDateFormat(dateFormat).parse(dateStr);

        return date;
    }
    public String getFormattedDate(Date date) {
        DateFormat df = new SimpleDateFormat(dateFormatOutput);
        return df.format(date);

    }
    public Date getDate() {
        setTimeZone();
        Date date = new Date();
        return date;
    }

    public long getTimeStamp() {
        Date date = getDate();
        return date.getTime();
    }

    public long getTimeStamp(String date) throws ParseException {
        Date datetime = getDate(date);
        return datetime.getTime();
    }

    public void setTimeZone() {
        String timezone = "UTC";
        TimeZone.setDefault(TimeZone.getTimeZone(timezone));
    }

}
