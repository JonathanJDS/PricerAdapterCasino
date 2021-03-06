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


public class ThreadCheckGestFiles extends Thread {

	static Logger logger =  LogManager.getLogger(ThreadCheckGestFiles.class);

	static Wini ini;
	static String gestArchiveFolder ;	
	static String GestFileName  	;
	
	static int tempo;
	static String sourceFolder;
	static String temporaryFolder;
	static String pricerDataFilesFolder;
	static String pricerMessageFilesFolder;
	static String pricerResultFilesFolder;
	static String internalCodeSize;
	static String disableCodeMagasin;
	static String hostAPI;
	static String portAPI;
	static String userAPI;
	static String keyAPI;
	
	Timer timer = new Timer();
	FileUtility utility = new FileUtility();
	
	public ThreadCheckGestFiles() {

		logger.info("Starting Thread ThreadCheckGestFiles");
		
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
		
		/*****Format DATA ***********/
		
		internalCodeSize	= ini.get("FormatData", "InternalCodeSize");
		disableCodeMagasin = ini.get("FormatData", "DisableCodeMagasin");
		
		
		/******** API **************/
		hostAPI	= ini.get("API", "Host");
		portAPI = ini.get("API", "Port");
		userAPI = ini.get("API", "API_USER");
		keyAPI	= ini.get("API", "API_KEY");		

		
		
		
		
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
        
        dataFileName_Update		=	pricerDataFilesFolder		+ "\\"	+ "data_stock02_" + dateOfFile + ".i1";
        messageFileName_Update	=	pricerMessageFilesFolder	+ "\\"	+ "data_stock02_" + dateOfFile + ".m1";
        resultFileName_Update	=	pricerResultFilesFolder		+ "\\"	+ "data_stock02_" + dateOfFile + ".r7";
        
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
			
			if(disableCodeMagasin.equalsIgnoreCase("1")) {
			
				line = "99999"+line;
				System.out.println("line = " + line);
			
			}
			

			
			

			
			lineSplited2 = line.split(";");
			
			lstItems = new ArrayList<String>();
			
			if(internalCodeSize.equalsIgnoreCase("09") && lineSplited2[1].trim().length() < 9) {
				lstItems.add("000"+lineSplited2[1].trim());
			}
			else
				lstItems.add(lineSplited2[1].trim());
			
			
				for(String itemid : lstItems) {
					
					if(lstLinkedItems.contains(itemid)) {
						lstFile.add(line);
					}
					
				}
			
			
			
		}
		
		logger.info("Generating i1 file ...");

		for (String line : lstFile) {
			
		
		
			//line = "9999" + line;
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
		if(internalCodeSize.equalsIgnoreCase("09")) {
			productGestion.setItemIDGest("000"+splitedTabLine.get(1));
		}else
			productGestion.setItemIDGest(splitedTabLine.get(1));
		productGestion.setPresentationStock(splitedTabLine.get(2));
		productGestion.setFacing(splitedTabLine.get(3));
		productGestion.setQteInStock(splitedTabLine.get(4));
		productGestion.setCommandeEnCours(splitedTabLine.get(5));
		productGestion.setQteDerniereLivraison(splitedTabLine.get(6));
		
		try {
			
			productGestion.setDateDerniereLivraison(sAfter.format(sBefore.parse(splitedTabLine.get(7))));
			
		} catch (ParseException e){
			productGestion.setDateDerniereLivraison("");
		}
		
		productGestion.setQteProchaineLivraison(splitedTabLine.get(8));
		
		try {
			
			productGestion.setDateProchaineLivraison(sAfter.format(sBefore.parse(splitedTabLine.get(9))));
			
		} catch (ParseException e) {
			productGestion.setDateProchaineLivraison("");
		}
		
		productGestion.setDateDernierComptage(splitedTabLine.get(10));
		productGestion.setQteDernierComptage(splitedTabLine.get(11));
		productGestion.setArticleSupprime(splitedTabLine.get(12));
		productGestion.setAffichage(splitedTabLine.get(13));
		productGestion.setPointSmiles(splitedTabLine.get(14));
		productGestion.setSalesContractId(splitedTabLine.get(16));
		        
		 

				
		 completeLine.append("0001 ").append(String.format("%"+internalCodeSize+"d",Integer.parseInt(productGestion.getItemIDGest()) ));
		 completeLine.append(" 324 0 |").append(productGestion.getPresentationStock().trim());
		 completeLine.append("| 154 0 |").append(productGestion.getFacing());
		 completeLine.append("| 323 0 |").append(productGestion.getQteInStock());
		 completeLine.append("| 326 0 |").append(productGestion.getCommandeEnCours());
		 completeLine.append("| 325 0 |").append(productGestion.getQteDerniereLivraison());
		 completeLine.append("| 327 0 |").append(productGestion.getDateDerniereLivraison());
		 completeLine.append("| 320 0 |").append(productGestion.getQteProchaineLivraison());
		 completeLine.append("| 318 0 |").append(productGestion.getDateProchaineLivraison());
		 completeLine.append("| 249 0 |").append(productGestion.getDateDernierComptage());
		 completeLine.append("| 248 0 |").append(productGestion.getQteDernierComptage());
		 completeLine.append("| 200 0 |").append(productGestion.getArticleSupprime());
		 completeLine.append("| 402 0 |").append(productGestion.getSalesContractId());
                 
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
		
		
		System.out.println("delete file " + temporaryFolder + "\\" + GestFileName);
		new File(temporaryFolder + "\\" + GestFileName).delete();
	
		
		
		
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
