package com.pricer.main;


import com.pricer.model.FileUtility;
import com.pricer.product.ProductBase;

//import org.apache.log4j.Logger;
import org.apache.logging.log4j.*;

import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ThreadCheckPriceFiles extends Thread {

	static Logger logger =  LogManager.getLogger(ThreadCheckPriceFiles.class);

	static Wini ini;
	static String priceArchiveFolder;
	static String priceFileName;
	static String pricerRelFileName;

	static int tempo;
	static String sourceFolder;
	static String temporaryFolder;
	static String pricerDataFilesFolder;
	static String pricerMessageFilesFolder;
	static String pricerResultFilesFolder;

	Timer timer = new Timer();
	FileUtility utility = new FileUtility();

	public ThreadCheckPriceFiles() {

		logger.info("Starting Thread ThreadCheckPriceFiles");
		
		InitializeIni();
		System.out.println("init ini");
		
		
		/*******Archive folders **********/
		priceArchiveFolder		= ini.get("Folders", "PriceArchiveFolder");
		
		/********* FileNames *************/
		priceFileName 		= ini.get("Files","PriceFileName");
		pricerRelFileName	= ini.get("Files","RelFileName");


		/*****Pricer Path **************/
		tempo 						= Integer.valueOf(ini.get("Files", "timer"));
		sourceFolder 				= ini.get("Folders", "SourceFolder");
		temporaryFolder 			= ini.get("Folders", "TemporaryFolder");
		pricerDataFilesFolder 		= ini.get("Folders", "PricerDataFiles");
		pricerMessageFilesFolder 	= ini.get("Folders", "PricerMessageFiles");
		pricerResultFilesFolder		= ini.get("Folders", "PricerResultFiles");



		HashMap<String, String> lstFormatLabels = new HashMap<>();
		for (String key : ini.get("FormatLabels").keySet()) {
		lstFormatLabels.put(key, ini.get("FormatLabels").fetch(key));
		System.out.println(key + ":" + ini.get("FormatLabels").fetch(key));
		}



		TimerTask task = new TimerTask() {
			@Override
			public void run() {


				FileUtility FdataFile = new FileUtility(sourceFolder + "\\" +  priceFileName);
				FileUtility FTemporaryFile = new FileUtility(temporaryFolder + "\\" + priceFileName);

				FileUtility FEEGEANFile = new FileUtility(sourceFolder + "\\" + pricerRelFileName);
				FileUtility FTemporaryEEGEANFile = new FileUtility(temporaryFolder + "\\" + pricerRelFileName);

				// check first if something is present in temporary Folder, if not process source Folder
				if (FTemporaryFile.FileExist()) {
					logger.warn ("File is present in temporary Folder !! priority for that !!!!");
					ProcessFile(FTemporaryFile);
				}

				else {

						// process only one file in temporary (one by one ) .
						if (FdataFile.FileExist() && !FTemporaryFile.FileExist()) {
							utility.ZipFile(sourceFolder, FdataFile.getFileName(), temporaryFolder, FdataFile.getFileName(), priceArchiveFolder);
							utility.MoveFile(sourceFolder + "\\" + FdataFile.getFileName(), temporaryFolder + "\\" + FdataFile.getFileName());
							ProcessFile(FTemporaryFile);
						}


					// check and zip list of product to print
					if (FEEGEANFile.FileExist() && !FTemporaryEEGEANFile.FileExist()) {
							utility.ZipFile(sourceFolder, FEEGEANFile.getFileName(), temporaryFolder, FEEGEANFile.getFileName(), priceArchiveFolder);
							utility.MoveFile(sourceFolder + "\\" + FEEGEANFile.getFileName(), temporaryFolder + "\\" + FEEGEANFile.getFileName());
					}

				}

			}

		};

		// execute this thread 2 s after original tempo value.
		timer.scheduleAtFixedRate(task, 0, (tempo * 1000) + 2000);
	}
	
	private void ProcessFile(FileUtility FtemporaryFile) {
		
		System.out.println("Processing data file");
		logger.info("Processing data file : " + FtemporaryFile.getFileName() );
		

		boolean bdatafile_Update_opened=false;

		ProductBase priceData = null;
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


		String model1 = "|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|XXXXXXXX|XXXXXXXX|XXXXXXXX|X|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|XXXXX|XXXXXXXXXXXXXXX|XXXXXXXXXXXXXXX|XXXXXXXXXXXXXXX|XXXXXXXXXXXXXXX|XX|XX|XXXXX|XX|";
		String model2 = "|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|\":SUPP \"|\":SUPP \"|\":SUPP \"|X|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|XXXXX|XXXXXXXXXXXXXXX|XXXXXXXXXXXXXXX|XXXXXXXXXXXXXXX|XXXXXXXXXXXXXXX|XX|00|XXXXX|XX|";
		ProductBase product = null;
		List<String> lstEANSics = null ;
		List<ProductBase> lstProducts = new ArrayList<ProductBase>();
		//List<ProductToDelete> lstDelete = new ArrayList<ProductToDelete>();
		//List<ProductToDelete> lstDeletePFI = new ArrayList<ProductToDelete>();
		List<String> lstEANTOPrint = null;






		dataFileName_Update		=	pricerDataFilesFolder		+ "\\"	+ "data_price_" + dateOfFile + ".i1";
        messageFileName_Update	=	pricerMessageFilesFolder	+ "\\"	+ "data_price_" + dateOfFile + ".m1";
        resultFileName_Update	=	pricerResultFilesFolder		+ "\\"	+ "data_price_" + dateOfFile + ".r7";
        
        contentMessageFile_Update = "UPDATE,0001,," + dataFileName_Update + "," + resultFileName_Update;
		
		
		if (!FtemporaryFile.FileExist()) {
			logger.debug("temporary file is not present");
			return;
			
		}
		
		
		if (FtemporaryFile.fileIsGrowing()) {
			
			logger.warn("file is growing waiting... : " + FtemporaryFile.getFileName());
			return ;
		}
		
		
		System.out.println("let's GO!!!" );
		
		//put all lines in a MAP of String
		List<String> lstMapFile = FtemporaryFile.fileToMap();


		// Map Iteration
		for (String line : lstMapFile) {

			List<String> splitedTabLine = splitLine(line, "|");

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


		priceData = new ProductBase();
		priceData.setItemID(splitedTabLine.get(1));
		completeLine.append("0001 ").append(priceData.getItemID());
		completeLine.append("|,");



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
		FtemporaryFile.deleteFile();

		
		
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
