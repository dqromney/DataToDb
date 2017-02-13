package com.dqr.dataToDb;

import com.dqr.dataToDb.io.ProcessEodData;
import com.dqr.dataToDb.io.ReadData;
import com.dqr.dataToDb.io.UncompressFile;
import com.dqr.dataToDb.model.Eod;
import com.dqr.dataToDb.repository.DataSourceFactory;
import com.dqr.dataToDb.utils.ReadConfig;
import org.skife.jdbi.v2.DBI;

import javax.sql.DataSource;
import java.io.IOException;
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

    public static void main(String[] args) throws IOException {
        DataToDb dataToDb = new DataToDb();
        dataToDb.init();
        dataToDb.execute();
        dataToDb.egress();
    }

    public void init() {
        System.out.println("Hello Data to DB Application.");
        dbi = new DBI(dataSource);
    }
    public void execute() throws IOException {
        // Get list of files in directory
        // Process each file
        String zipPathFile = "DataToDb/src/main/resources/A.ZIP";
        UncompressFile uncompressFile = new UncompressFile(zipPathFile);
        if (uncompressFile.isValid()) {
            uncompressFile.unZip();
        }

        ReadData readData = new ReadData("./A.CSV");
        List<String[]> lineList = readData.getAllLines();
        ProcessEodData processEodData = new ProcessEodData();
        for(String[] item: lineList) {
            Eod eod = processEodData.process(item);
            System.out.println(eod);
        }
    }

    public void egress() {
        System.out.println("Exiting Data to DB Application.");
    }

}
