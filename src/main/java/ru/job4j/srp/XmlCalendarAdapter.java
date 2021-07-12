package ru.job4j.srp;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class XmlCalendarAdapter extends XmlAdapter<String, Calendar> {

    private final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Calendar unmarshal(String s) throws Exception {
        Calendar result = Calendar.getInstance();
        result.setTime(f.parse(s));
        return result;
    }

    @Override
    public String marshal(Calendar calendar) throws Exception {
        return f.format(calendar.getTime());
    }
}
