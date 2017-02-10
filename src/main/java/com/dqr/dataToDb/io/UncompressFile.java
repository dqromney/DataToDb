package com.dqr.dataToDb.io;

import com.dqr.dataToDb.utils.UnZip;

import java.io.File;

/**
 * Uncompress Zip file.
 *
 * Created by dqromney on 2/9/17.
 */
public class UncompressFile {
    String zipPathFile;

    public UncompressFile(String pPathFile) {
        zipPathFile = pPathFile;
    }

    public Boolean isValid() {
        return new File(zipPathFile).isFile();
    }

    public void unZip() {
        UnZip unZip = new UnZip();
        unZip.unZipIt(zipPathFile, ".");
    }
}
