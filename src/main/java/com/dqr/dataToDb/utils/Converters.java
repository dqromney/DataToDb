package com.dqr.dataToDb.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Converter utilities.
 *
 * Created by dqromney on 2/13/17.
 */
public class Converters {

    public static Long strToLong(String s) {
        Long n;
        if (s.contains(".")) {
            Double d = new Double(s);
            n = d.longValue();
        } else {
            n = new Long(s);
        }
        return n;
    }

    public static Date strToDate(String dateStr) {
            DateFormat df = new SimpleDateFormat("MM/dd/yy");
            Date dateFromStr = null;
            try {
                dateFromStr = df.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return dateFromStr;
    }


}
