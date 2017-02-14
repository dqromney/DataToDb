package com.dqr.dataToDb.io;

import com.dqr.dataToDb.model.Eod;
import com.dqr.dataToDb.utils.Converters;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

/**
 * Process Data.
 * <p>
 * Created by dqromney on 2/11/17.
 */
public class ProcessEodData {

    public Eod process(String[] line) throws ParseException {
        Eod eod = new Eod(null, null,
                Converters.strToDate(line[0]),
                new BigDecimal(line[1]), new BigDecimal(line[2]), new BigDecimal(line[3]), new BigDecimal(line[4]),
                Converters.strToLong(line[5]), Converters.strToLong(line[6]),
                new Date());
        return eod;
    }

}

