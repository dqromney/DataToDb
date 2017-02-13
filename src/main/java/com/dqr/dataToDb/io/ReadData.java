package com.dqr.dataToDb.io;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Read Data.
 * <p>
 * Created by dqromney on 2/9/17.
 */
public class ReadData {
    private FileReader fileReader;
    private CSVReader csvReader;

    public ReadData(String pPathFile) throws FileNotFoundException {
        csvReader = new CSVReader(new FileReader(pPathFile));
    }

    public String[] getNextLine() throws IOException {
        String[] nextLine = csvReader.readNext();
        return nextLine;
    }

    public List<String[]> getAllLines() throws IOException {
        List<String[]> lineList = new ArrayList<>();
        String[] nextLine;
        while((nextLine = getNextLine()) != null) {
            lineList.add(nextLine);
        }
        return lineList;
    }

}
