package com.pricer.main;

import com.pricer.model.FileProperty;
import com.pricer.model.UtilityClass;
import com.pricer.product.ProductPrice;
import org.apache.log4j.Logger;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ThreadCheckPriceFiles extends Thread {

	static Logger logger = Logger.getLogger(Start.class);

	static Wini ini;
	static String priceArchiveFolder;
	static String priceFileName;

	static int tempo;
	static String sourceFolder;
	static String temporaryFolder;
	static String pricerDataFilesFolder;
	static String pricerMessageFilesFolder;
	static String pricerResultFilesFolder;

	Timer timer = new Timer();
	UtilityClass utility = new UtilityClass();

	public ThreadCheckPriceFiles() {

		logger.info("Starting Thread ThreadCheckPriceFiles");
		
		InitializeIni();
		System.out.println("init ini");
		
		
		/*******Archive folders **********/
		priceArchiveFolder		= ini.get("Folders", "priceArchiveFolder");
		
		/********* FileNames *************/
		priceFileName 		= ini.get("Files","priceFileName");
		
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
				
				ArrayList<String> lstFiles =  utility.listFilesFromDirectory(sourceFolder + "\\",priceFileName);
				
				for(String fileNameFilter : lstFiles) {
					
				utility.ZipFile (sourceFolder, fileNameFilter, temporaryFolder, fileNameFilter, priceArchiveFolder);
				utility.MoveFile(sourceFolder + "\\" + fileNameFilter,temporaryFolder + "\\" + fileNameFilter);	
				
				ProcessFile(temporaryFolder + "\\" + fileNameFilter);
				
				}

			}


		};
		
		timer.scheduleAtFixedRate(task, 0, tempo * 1000);
	}
	
	private void ProcessFile(String temporaryFile) {
		
		System.out.println("Processing data file");
		logger.info("Processing data file !");
		
		FileProperty fpTemporaryFile = new FileProperty(temporaryFile);
		boolean bdatafile_Update_opened=false;

		ProductPrice priceData = null;
		OperationOnDB opDB = null;
		
		Date d = new Date(); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_Hmmss");
        String dateOfFile		=	sdf.format(d);
				
	    String dataFileName_Update		=	null;
        String messageFileName_Update	=	null;
        String resultFileName_Update	=	null;
        String contentMessageFile_Update = null;
        
        PrintStream datafile_Update		=	null;
       	PrintStream messagefile_Update	=	null;
       	StringBuffer completeLine ;
       	
       	/**************FILE CONTENT *******************/
       	       	       	
       	
        
        dataFileName_Update		=	pricerDataFilesFolder		+ "\\"	+ "data_price_" + dateOfFile + ".i1";
        messageFileName_Update	=	pricerMessageFilesFolder	+ "\\"	+ "data_price_" + dateOfFile + ".m1";
        resultFileName_Update	=	pricerResultFilesFolder		+ "\\"	+ "data_price_" + dateOfFile + ".r7";
        
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
		
		List<String> lstMapFile = fpTemporaryFile.fileToMap();
		
		
		
		for (String line : lstMapFile) {
			
			//System.out.println("line = " + line);
			
			
			List<String> splitedTabLine = splitLine(line, ";");

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


		priceData = new ProductPrice();
		opDB = new OperationOnDB();
		
		
		//cdiscountData.setItemIPF(splitedTabLine.get(0));
		priceData.setItemID(splitedTabLine.get(1));



		        
		 completeLine.append("0001 ").append(priceData.getItemID());

         completeLine.append("|,");
         
         
         System.out.println( completeLine.toString());
        // datafile_Update.println(completeLine.toString());
        // datafile_Update.flush();

		}
			catch (IndexOutOfBoundsException indx){
			logger.warn("line empty or out of bound...." + line);
			 
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
		
		
		System.out.println("delete file " + temporaryFolder + "\\" + priceFileName);
		new File(temporaryFolder + "\\" + priceFileName).delete();
	
		
		
		
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
	
	private static List<String> splitLine(String sLine, String sSeparator) {
		
		List<String> lSplitLine = new ArrayList<String>();
		String tmp;
		boolean pipetmp = false;
		int j = 0;
		
		StringTokenizer st = new StringTokenizer(sLine, sSeparator, true);
		while (st.hasMoreTokens()) {
			
			tmp = st.nextToken();
			if (tmp.equals(sSeparator)) {
				if (pipetmp == true) {
					lSplitLine.add("");
					j++;
				}
				pipetmp = true;
			} else {
				pipetmp = false;
				lSplitLine.add(tmp);
				
				j++;
			}
		}
		j = 0;
		
		return lSplitLine;
	}
	

}