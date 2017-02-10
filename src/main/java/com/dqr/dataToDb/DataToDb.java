package com.dqr.dataToDb;

import com.dqr.dataToDb.repository.DataSourceFactory;
import com.dqr.dataToDb.utils.ReadConfig;
import org.skife.jdbi.v2.DBI;

import javax.sql.DataSource;
import java.io.IOException;
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

    public static void main(String[] args) {
        DataToDb dataToDb = new DataToDb();
        dataToDb.init();

    }

    public void init() {
        System.out.println("Hello Data to DB Application.");
        dbi = new DBI(dataSource);
    }
    public void execute() {
        // Get list of files in directory
        // Process each file
    }

    public void egress() {
        System.out.println("Exiting Data to DB Application.");
    }

}
