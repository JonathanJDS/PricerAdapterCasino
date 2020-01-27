package com.pricer.main;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.logging.log4j.*;
//import org.apache.log4j.Logger;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

import com.pricer.model.FileUtility;

public class ThreadCheckGestFiles extends Thread {

	static Logger logger =  LogManager.getLogger(Start.class);

	static Wini ini;
	static String gestArchiveFolder ;	
	static String GestFileName  	;
	
	static int tempo;
	static String sourceFolder;
	static String temporaryFolder;
	static String pricerDataFilesFolder;
	static String pricerMessageFilesFolder;
	static String pricerResultFilesFolder;
	
	Timer timer = new Timer();
	FileUtility utility = new FileUtility();
	
	public ThreadCheckGestFiles() {

		logger.info("Starting Thread ThreadCheckDataFiles");
		
		InitializeIni();
		System.out.println("init ini");
		
		
		/*******Archive folders **********/
		gestArchiveFolder		= ini.get("Folders", "GestArchiveFolder");
		
		/********* FileNames *************/
		GestFileName 		= ini.get("Files","GestFileName");
		
		/*****Pricer Path **************/
		tempo 						= Integer.valueOf(ini.get("Files", "timer"));
		sourceFolder 				= ini.get("Folders", "SourceFolder");
		temporaryFolder 			= ini.get("Folders", "TemporaryFolder");
		pricerDataFilesFolder 		= ini.get("Folders", "PricerDataFiles");
		pricerMessageFilesFolder 	= ini.get("Folders", "PricerMessageFiles");
		pricerResultFilesFolder		= ini.get("Folders", "PricerResultFiles");

		
		
		
		
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				
				ArrayList<String> lstFiles =  utility.listFilesFromDirectory(sourceFolder + "\\",GestFileName);
				
				for(String fileNameFilter : lstFiles) {
					
				utility.ZipFile (sourceFolder, fileNameFilter,gestArchiveFolder, fileNameFilter);
				utility.MoveFile(sourceFolder + "\\" + fileNameFilter,temporaryFolder + "\\" + fileNameFilter);	
				
				ProcessFile(temporaryFolder + "\\" + fileNameFilter);
				
				}

			}


		};
		
		timer.scheduleAtFixedRate(task, 0, tempo * 1000);
	}
	
	private void ProcessFile(String temporaryFile) {
		
		System.out.println("Processing gest file");
		
		FileUtility fpTemporaryFile = new FileUtility(temporaryFile);
		
		Date d = new Date(); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_Hmmss");
        String dateOfFile		=	sdf.format(d);
		
		
	    String dataFileName_Update		=	null;
        String messageFileName_Update	=	null;
        String resultFileName_Update	=	null;
        String contentMessageFile_Update = null;
        
        dataFileName_Update		=	pricerDataFilesFolder		+ "\\"	+ "data_stock_" + dateOfFile + ".i1";
        messageFileName_Update	=	pricerMessageFilesFolder	+ "\\"	+ "data_stock_" + dateOfFile + ".m1";
        resultFileName_Update	=	pricerResultFilesFolder		+ "\\"	+ "data_stock_" + dateOfFile + ".r7";
        
        contentMessageFile_Update = "UPDATE,0001,," + dataFileName_Update + "," + resultFileName_Update;
		
		
		if (!fpTemporaryFile.FileExist() == true ) {
			logger.debug("temporary file is empty");
			return;
			
		}
		
		
		if (fpTemporaryFile.fileIsGrowing()==true) {
			
			logger.warn("file is growing waiting... : " + temporaryFile);
			return ;
		}
		
		
		System.out.println("let's GO!!!" );
		
		
		
		
		
		
		
		
		
		
	}
	
	public void InitializeIni() {
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
	

}
