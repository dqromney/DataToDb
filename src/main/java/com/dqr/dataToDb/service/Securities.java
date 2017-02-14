package com.dqr.dataToDb.service;

import com.dqr.dataToDb.model.Eod;
import com.dqr.dataToDb.model.Symbol;
import com.dqr.dataToDb.repository.EodDao;
import com.dqr.dataToDb.repository.SymbolDao;
import org.skife.jdbi.v2.DBI;

/**
 * Securities service.
 *
 * Created by dqromney on 2/13/17.
 */
public class Securities {
    private DBI dbi;
    private SymbolDao symbolDao;
    private EodDao eodDao;

    public Securities(DBI pDbi) {
        this.dbi = pDbi;
        symbolDao = dbi.open(SymbolDao.class);
        eodDao = dbi.open(EodDao.class);
    }

    public void add(Symbol pSymbol, Eod pEod) {

        Symbol symbol = symbolDao.findBySymbol(pSymbol.getSymbol());
        if (symbol == null) {
            symbolDao.insert(pSymbol.getSymbol(), pSymbol.getName(), pSymbol.getType(), pSymbol.getExchange());
            symbol = symbolDao.findBySymbol(pSymbol.getSymbol());
        }
        pEod.setSymbolId(symbol.getId());
        eodDao.insert(symbol.getId(), pEod.getDate(), pEod.getOpen(), pEod.getHigh(), pEod.getLow(), pEod.getClose(), pEod.getVolume(), pEod.getOpenInterest(), pEod.getLastUpdate());
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        symbolDao.close();
        eodDao.close();
        symbolDao = null;
        eodDao = null;
        System.out.println(String.format("Closing connections to symbolDao=[%1$s] and eodDao=[%2$s]",symbolDao, eodDao));
    }
}
