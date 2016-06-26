/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSC.controllers;

import org.apache.VNSC.controllers.FileDownload;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
/**
 *
 * @author haonguyen
 */

public class FileDownloadImpl implements FileDownload {

    @Override
    public DataHandler downloadFile(String fileName) {
        FileDataSource dataSource = new FileDataSource("/home/haonguyen/data"+fileName);
	DataHandler fileDataHandler = new DataHandler(dataSource);
	   return fileDataHandler;
    }
}
