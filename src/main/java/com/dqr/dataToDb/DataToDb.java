package com.dqr.dataToDb;

import com.dqr.dataToDb.io.ProcessEodData;
import com.dqr.dataToDb.io.ReadData;
import com.dqr.dataToDb.io.UncompressFile;
import com.dqr.dataToDb.model.Eod;
import com.dqr.dataToDb.model.Symbol;
import com.dqr.dataToDb.repository.DataSourceFactory;
import com.dqr.dataToDb.service.Securities;
import com.dqr.dataToDb.types.SymbolType;
import com.dqr.dataToDb.utils.ReadConfig;
import org.skife.jdbi.v2.DBI;

import javax.sql.DataSource;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Properties;

/**
 * Main driver.
 */
public class DataToDb {
    private DataSource dataSource;
    private DBI dbi;
    private String emailAddress;
    private String password;
    private Properties configProps;

    public DataToDb() {
        ReadConfig readConfig = new ReadConfig("config.properties");
        dataSource = DataSourceFactory.getMySQLDataSource();

        try {
            configProps = readConfig.getPropValues();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, ParseException {
        DataToDb dataToDb = new DataToDb();
        dataToDb.init();
        dataToDb.execute();
        dataToDb.egress();
    }

    public void init() {
        System.out.println("Hello Data to DB Application.");
        dbi = new DBI(dataSource);
    }
    public void execute() throws IOException, ParseException {
        // Get list of files in directory
        // Process each file
        String zipPathFile = "src/main/resources/A.ZIP";
        UncompressFile uncompressFile = new UncompressFile(zipPathFile);
        if (uncompressFile.isValid()) {
            uncompressFile.unZip();
        }

        Symbol symbol = new Symbol(null, "A", "Agilent Technologies, Inc.", SymbolType.STOCK, "NYSE");

        ReadData readData = new ReadData("src/main/resources/A.CSV");
        List<String[]> lineList = readData.getAllLines();
        ProcessEodData processEodData = new ProcessEodData();
        for(String[] item: lineList) {
            Eod eod = processEodData.process(item);
            Securities securities = new Securities(dbi);
            securities.add(symbol, eod);
            System.out.println(eod);
        }
    }

    public void egress() {
        System.out.println("Exiting Data to DB Application.");
    }

}
