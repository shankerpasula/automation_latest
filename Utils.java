package com.fmsinvoicefeed.utils;

import java.io.File;

import org.apache.log4j.Logger;

import com.fmsinvoicefeed.logger.LoggerHelper;
import com.fmsinvoicefeed.tests.sanity.CustomerDataAnalysis;

public class Utils {
	final static Logger logger = LoggerHelper.getLogger(Utils.class);
	public static void setAnumber(int oldSourceNo, int newSourceNo) {
        if (newSourceNo >= 1 && newSourceNo <= 10) {
        	oldSourceNo = newSourceNo;
        } else {
            throw new IllegalArgumentException("Newnumber out of range");
        }
    }
	
	public static void createCustomerFolder(String customerDataFolderPath) {
		File directory = new File(customerDataFolderPath);
		// make sure directory exists
		if (directory.exists()) {
			Utils.delete(directory);
			directory.mkdir();
		}
			else
			{
				directory.mkdir();
			}
	}
	
	public static void delete(File file) {
		if (file.isDirectory()) {
			// directory is empty, then delete it
			if (file.list().length == 0) {
				file.delete();
			} else {
				// list all the directory contents
				String files[] = file.list();
				for (String temp : files) {
					// construct the file structure
					File fileDelete = new File(file, temp);
					// recursive delete
					delete(fileDelete);
				}
				// check the directory again, if empty then delete it
				if (file.list().length == 0) {
					file.delete();
					//logger.info("Directory is deleted : " + file.getAbsolutePath());
				}
			}
		} else {
			file.delete();
			//logger.info("File is deleted : " + file.getAbsolutePath());
		}
	}
}
