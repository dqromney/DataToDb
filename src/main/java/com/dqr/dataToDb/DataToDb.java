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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

        String sourceDir = "/Users/dqromney/Google Drive/TBSP-Trading-Data-92-97";
        Files.walk(Paths.get(sourceDir))
                .filter(Files::isRegularFile)
                .forEach(f -> {
                    File file = f.toFile();
                    Path path = file.toPath();
                    if (!file.isHidden()) {
                        String theSymbol = file.getName().substring(0, file.getName().lastIndexOf('.'));
                        String[] pathArray = path.toString().split("/");

                        SymbolType symbolType = SymbolType.findByName(pathArray[pathArray.length - 3]);
                        if (!symbolType.equals(SymbolType.UNDEFINED)) {
                            Symbol symbol = new Symbol(null, theSymbol, null, symbolType.getValue(), null);

                            UncompressFile uncompressFile = new UncompressFile(file.toPath().toString());
                            if (uncompressFile.isValid()) {
                                uncompressFile.unZip();
                            }
                            String csvPathFile = String.format("src/main/resources/%1$s.CSV", theSymbol);
                            try {
                                ReadData readData = new ReadData(csvPathFile);
                                List<String[]> lineList = readData.getAllLines();
                                ProcessEodData processEodData = new ProcessEodData();
                                for (String[] item : lineList) {
                                    Eod eod = processEodData.process(item);
                                    Securities securities = new Securities(dbi);
                                    securities.add(symbol, eod);
                                    System.out.println(eod);
                                }

                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                File removeFile = new File(csvPathFile);
                                if (removeFile.delete()) {
                                    System.out.println(String.format("File %1$s removed", csvPathFile));
                                } else {
                                    System.out.println(String.format("Error removing file %1$s!", csvPathFile));
                                }
                            }
                        }
                    }
                });

        // Process each file
//        String zipPathFile = "src/main/resources/A.ZIP";
//        UncompressFile uncompressFile = new UncompressFile(zipPathFile);
//        if (uncompressFile.isValid()) {
//            uncompressFile.unZip();
//        }
//
//        Symbol symbol = new Symbol(null, "A", "Agilent Technologies, Inc.", SymbolType.STOCK.getValue(), "NYSE");
//
//        ReadData readData = new ReadData("src/main/resources/A.CSV");
//        List<String[]> lineList = readData.getAllLines();
//        ProcessEodData processEodData = new ProcessEodData();
//        for(String[] item: lineList) {
//            Eod eod = processEodData.process(item);
//            Securities securities = new Securities(dbi);
//            securities.add(symbol, eod);
//            System.out.println(eod);
//        }
    }

    public void egress() {
        System.out.println("Exiting Data to DB Application.");
    }

}
