package com.pricer.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

public class ThreadCheckHistoryFolder extends Thread {
	
	static Logger logger = Logger.getLogger(ThreadCheckHistoryFolder.class);
	static Wini ini;
	static String gestArchiveFolder ;
	static String priceArchiveFolder;
	static String dataFileName 	;
	static String stockFileName ;
	static String printFileName ;
	static int tempo	;
	static int nbrOfDayTokeep ;
	static List<String> lstFileName = new ArrayList<String>();
	
	private void GetIni() {
		try {
			ini = new Wini(new File("preference.ini"));
		} catch (InvalidFileFormatException e1) {

			logger.fatal("Unable to Read Preference.ini File, check your configuration cause : " + e1.getCause() + "/"
					+ e1.getMessage());
			logger.fatal("Exit Application");
			System.exit(1);

		} catch (IOException e1) {
			logger.fatal("Unable to Read Preference.ini File, check your configuration cause : " + e1.getCause() + "/"
					+ e1.getMessage());
			logger.fatal("Exit Application");
			System.exit(1);

		}
		
	}
	
	public ThreadCheckHistoryFolder(){
		
		GetIni();
    	   		
    		gestArchiveFolder		= ini.get("Folders", "ArchiveFolder");
    		priceArchiveFolder	= ini.get("Folders", "PriceArchiveFolder");
    		dataFileName		= ini.get("Files","PriceFileName").replace("*", "");
    		stockFileName		= ini.get("Files","StockFileName");
    		printFileName		= ini.get("Files","PrintFileName");
    		tempo				= Integer.valueOf(ini.get("FTP","timer"));
    		nbrOfDayTokeep		= Integer.valueOf(ini.get("Folders","NbrOfDayToKeep"));
    		
    		
    		lstFileName = new ArrayList<String>();
    		
//    		lstFileName.add(dataFileName);
//   		lstFileName.add(stockFileName);
//    		lstFileName.add(printFileName);
    				
    			
	
	}

}
