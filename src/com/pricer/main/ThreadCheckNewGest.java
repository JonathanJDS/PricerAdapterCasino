package com.pricer.main;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

import com.pricer.model.FileUtility;
import com.pricer.product.ProductGestion;


public class ThreadCheckNewGest extends Thread {

	static Logger logger =  LogManager.getLogger(ThreadCheckNewGest.class);

	static Wini ini;
	static String gestArchiveFolder ;	
	static String newGestFileName  	;
	
	static int tempo;
	static String sourceFolder;
	static String temporaryFolder;
	static String pricerDataFilesFolder;
	static String pricerMessageFilesFolder;
	static String pricerResultFilesFolder;
	static String internalCodeSize;
	static String hostAPI;
	static String portAPI;
	static String userAPI;
	static String keyAPI;
	
	Timer timer = new Timer();
	FileUtility utility = new FileUtility();
	
	public ThreadCheckNewGest() {

		logger.info("Starting Thread ThreadCheckGestFiles");
		
		InitializeIni();
		System.out.println("init ini");
		
		
		/*******Archive folders **********/
		gestArchiveFolder		= ini.get("Folders", "GestArchiveFolder");
		
		/********* FileNames *************/
		newGestFileName 		= ini.get("Files","NewGestFileName");
		
		/*****Pricer Path **************/
		tempo 						= Integer.valueOf(ini.get("Files", "timer"));
		sourceFolder 				= ini.get("Folders", "SourceFolder");
		temporaryFolder 			= ini.get("Folders", "TemporaryFolder");
		pricerDataFilesFolder 		= ini.get("Folders", "PricerDataFiles");
		pricerMessageFilesFolder 	= ini.get("Folders", "PricerMessageFiles");
		pricerResultFilesFolder		= ini.get("Folders", "PricerResultFiles");
		
		/*****Format DATA ***********/
		
		internalCodeSize	= ini.get("FormatData", "InternalCodeSize");
		
		/******** API **************/
		hostAPI	= ini.get("API", "Host");
		portAPI = ini.get("API", "Port");
		userAPI = ini.get("API", "API_USER");
		keyAPI	= ini.get("API", "API_KEY");		

		
		
		
		
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				
				FileUtility FdataFile = new FileUtility(sourceFolder + "\\" +  newGestFileName);
				FileUtility FTemporaryFile = new FileUtility(temporaryFolder + "\\" + newGestFileName);


				// check first if something is present in temporary Folder, if not process source Folder
				if (FTemporaryFile.FileExist()) {
					logger.warn ("File is present in temporary Folder !! priority for that !!!!");
					ProcessFile(FTemporaryFile);
				}

				else {

						// process only one file in temporary (one by one ) .
						if (FdataFile.FileExist() && !FTemporaryFile.FileExist()) {
							utility.ZipFile(sourceFolder, FdataFile.getFileName(), gestArchiveFolder, FdataFile.getFileName());
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
		logger.info("Processing Gest file : " + FtemporaryFile.getFileName() );
		
		
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
       	StringBuffer completeLine = null ;
        
        dataFileName_Update		=	pricerDataFilesFolder		+ "\\"	+ "data_stock05_" + dateOfFile + ".i1";
        messageFileName_Update	=	pricerMessageFilesFolder	+ "\\"	+ "data_stock05_" + dateOfFile + ".m1";
        resultFileName_Update	=	pricerResultFilesFolder		+ "\\"	+ "data_stock05_" + dateOfFile + ".r7";
        
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
		List<String> lstFile  =	new ArrayList<String>();
		String[] lineSplited2 = null;
		List<String> lstItems;

		
		logger.info("Purging gest file, getting linked items ... ");

		TreeSet<String> lstLinkedItems = new OperationOnDB().lstLinkedItems();
		
		for(String line : lstMapFile) {
			
			lineSplited2 = line.split(";");
			
			lstItems = new ArrayList<String>();
			lstItems.add(lineSplited2[1].trim());
			
				for(String itemid : lstItems) {
					
					if(lstLinkedItems.contains(itemid)) {
						lstFile.add(line);
					}
					
				}
			
			
			
		}
		
		logger.info("Generating i1 file ...");

		for (String line : lstFile) {
			
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
		
		
		productGestion = new ProductGestion();
		productGestion.setCodeMagasinGes(splitedTabLine.get(0));
		productGestion.setItemIDGest(splitedTabLine.get(1));
		productGestion.setMasterEan(splitedTabLine.get(2));
		productGestion.setPrixPapier(splitedTabLine.get(4));
		productGestion.setFlagPapier(splitedTabLine.get(5));
		productGestion.setPlu(splitedTabLine.get(9));
		productGestion.setFlagS(splitedTabLine.get(10));
		productGestion.setFlagTriangle(splitedTabLine.get(11));
		productGestion.setFlagPromo(splitedTabLine.get(12));
		productGestion.setFlagInfo(splitedTabLine.get(13));
		productGestion.setFlagD(splitedTabLine.get(15));
		productGestion.setFreeText1(splitedTabLine.get(22));
		productGestion.setFreeText2(splitedTabLine.get(23));
		productGestion.setFreeText3(splitedTabLine.get(24));
		productGestion.setFreeText4(splitedTabLine.get(25));
		productGestion.setFreeText5(splitedTabLine.get(26));

		        
		 
		 

				
		 completeLine.append("0001 ").append(String.format("%"+internalCodeSize+"d",Integer.parseInt(productGestion.getItemIDGest()) ));
		 completeLine.append(" 330 0 |").append(productGestion.getMasterEan());
		 completeLine.append("| 612 0 |").append(productGestion.getPrixPapier());
		 completeLine.append("| 613 0 |").append(productGestion.getFlagPapier());
		 completeLine.append("| 94 0 |").append(productGestion.getPlu());
		 completeLine.append("| 614 0 |").append(productGestion.getFlagS());
		 completeLine.append("| 615 0 |").append(productGestion.getFlagTriangle());
		 completeLine.append("| 37 0 |").append(productGestion.getFlagPromo());
		 completeLine.append("| 616 0 |").append(productGestion.getFlagInfo());
		 completeLine.append("| 617 0 |").append(productGestion.getFlagD());
		 completeLine.append("| 618 0 |").append(productGestion.getFreeText1());
		 completeLine.append("| 619 0 |").append(productGestion.getFreeText2());
		 completeLine.append("| 620 0 |").append(productGestion.getFreeText3());
		 completeLine.append("| 621 0 |").append(productGestion.getFreeText4());
		 completeLine.append("| 622 0 |").append(productGestion.getFreeText5());

                 
         completeLine.append("|,");
         
        //System.out.println( completeLine.toString());
        datafile_Update.println(completeLine.toString());
        datafile_Update.flush();

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
		
		
		System.out.println("delete file " + temporaryFolder + "\\" + newGestFileName);
		new File(temporaryFolder + "\\" + newGestFileName).delete();
	
		
		
		
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
	

}
