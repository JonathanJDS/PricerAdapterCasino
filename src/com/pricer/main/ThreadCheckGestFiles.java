package com.pricer.main;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.log4j.Logger;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

import com.pricer.model.FileUtility;
import com.pricer.product.ProductCDiscount;
import com.pricer.product.ProductGestion;

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
				
				FileUtility FdataFile = new FileUtility(sourceFolder + "\\" +  GestFileName);
				FileUtility FTemporaryFile = new FileUtility(temporaryFolder + "\\" + GestFileName);


				// check first if something is present in temporary Folder, if not process source Folder
				if (FTemporaryFile.FileExist()) {
					logger.warn ("File is present in temporary Folder !! priority for that !!!!");
					ProcessFile(FTemporaryFile);
				}

				else {

						// process only one file in temporary (one by one ) .
						if (FdataFile.FileExist() && !FTemporaryFile.FileExist()) {
							utility.ZipFile(sourceFolder, FdataFile.getFileName(), FdataFile.getFileName(), gestArchiveFolder);
							utility.MoveFile(sourceFolder + "\\" + FdataFile.getFileName(), temporaryFolder + "\\" + FdataFile.getFileName());
							ProcessFile(FTemporaryFile);
						}

				}

			}


		};
		
		timer.scheduleAtFixedRate(task, 0, tempo * 1000);
	}
	
	private void ProcessFile(FileUtility FtemporaryFile) {
		
		System.out.println("Processing gest file");
		
		
	    SimpleDateFormat sBefore	=	new SimpleDateFormat("ddMMyyyy");
	    SimpleDateFormat sAfter		=	new SimpleDateFormat("ddMMyy");
		Date d = new Date(); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_Hmmss");
        String dateOfFile		=	sdf.format(d);
        ProductGestion productGestion = null;
		
		
	    String dataFileName_Update		=	null;
        String messageFileName_Update	=	null;
        String resultFileName_Update	=	null;
        String contentMessageFile_Update = null;
        boolean bdatafile_Update_opened=false;
        
        PrintStream datafile_Update		=	null;
       	PrintStream messagefile_Update	=	null;
       	StringBuffer completeLine ;
        
        dataFileName_Update		=	pricerDataFilesFolder		+ "\\"	+ "data_stock_" + dateOfFile + ".i1";
        messageFileName_Update	=	pricerMessageFilesFolder	+ "\\"	+ "data_stock_" + dateOfFile + ".m1";
        resultFileName_Update	=	pricerResultFilesFolder		+ "\\"	+ "data_stock_" + dateOfFile + ".r7";
        
        contentMessageFile_Update = "UPDATE,0001,," + dataFileName_Update + "," + resultFileName_Update;
		
		
		if (!FtemporaryFile.FileExist() == true ) {
			logger.debug("temporary file is empty");
			return;
			
		}
		
		
		if (FtemporaryFile.fileIsGrowing()==true) {
			
			logger.warn("file is growing waiting... : " + FtemporaryFile.getFileName());
			return ;
		}
		
		
		System.out.println("let's GO!!!" );
		System.out.println(FtemporaryFile.getPathFilename());
		
		List<String> lstMapFile = FtemporaryFile.fileToMap();


		for (String line : lstMapFile) {
			
		//System.out.println("line = " + line);
		

			List<String> splitedTabLine = splitLine(line, ";");
			
			if(splitedTabLine.size()>=15) {

			  try {
			 
				  if (bdatafile_Update_opened==false){
				  
					datafile_Update=new PrintStream(new BufferedOutputStream(new FileOutputStream(dataFileName_Update,true)),true);
					System.out.println("dataFileName_Update =" + dataFileName_Update);
					bdatafile_Update_opened=true;
				  }
		    	
		    }
		    	catch (FileNotFoundException e) {
		    		logger.fatal("File Not Found, Unable to create File : " + dataFileName_Update );
		    	}
		 
			
			
			completeLine = new StringBuffer(); 
			
			
				try {		
		
		
					productGestion = new ProductGestion();
		

		        
		 
		 

				
					completeLine.append("0001 ").append("ITEMID EN DUR");

                 
					completeLine.append("|,");
         
					//System.out.println( completeLine.toString());
					datafile_Update.println(completeLine.toString());
					datafile_Update.flush();

				}
				catch (IndexOutOfBoundsException indx){
				logger.warn("line empty or out of bound...." + line);			 
				} 
			
		}
		}
		
		
		if (bdatafile_Update_opened==true){
	 		datafile_Update.close(); 
	 		try {
				messagefile_Update=new PrintStream(new BufferedOutputStream(new FileOutputStream(messageFileName_Update)),true);
			} catch (FileNotFoundException e) {
				logger.fatal("unable to create Message File : " + e.getMessage() + ":" + e.getCause());
				
			}
	 messagefile_Update.print(contentMessageFile_Update);	
	 messagefile_Update.flush();
	 messagefile_Update.close();
	 	}
		
		
		System.out.println("delete file " + temporaryFolder + "\\" + GestFileName);
		new File(temporaryFolder + "\\" + GestFileName).delete();
		
		
		
		
		
		
		
		
	}
	
	private static List<String> splitLine(String sLine, String sSeparator) {
		
		List<String> lSplitLine = new ArrayList<String>();
		String tmp;
		boolean pipetmp = false;
		StringTokenizer st = new StringTokenizer(sLine, sSeparator, true);
		while (st.hasMoreTokens()) {
			
			tmp = st.nextToken();
			if (tmp.equals(sSeparator)) {
				if (pipetmp == true) {
					lSplitLine.add("");
				}
				pipetmp = true;
			} else {
				pipetmp = false;
				lSplitLine.add(tmp);
			}
		}
		return lSplitLine;
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
