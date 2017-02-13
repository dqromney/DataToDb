package com.dqr.dataToDb.io;

import com.dqr.dataToDb.model.Eod;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Process Data.
 *
 * Created by dqromney on 2/11/17.
 */
public class ProcessEodData {

    public Eod process(String[] line) {
        Eod eod = new Eod(null, null,
                strToDate(line[0]),
                new BigDecimal(line[1]), new BigDecimal(line[2]), new BigDecimal(line[3]), new BigDecimal(line[4]),
                new Long(line[5]), new Long(line[6]),
                new Date());
        return eod;
    }

    public Date strToDate(String dateStr) {
        Date date = null;
        try {
            date = DateFormat.getDateInstance().parse(dateStr);
            System.out.println(date.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}

