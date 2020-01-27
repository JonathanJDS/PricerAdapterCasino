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

import org.apache.logging.log4j.*;
//import org.apache.log4j.Logger;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

import com.pricer.model.FileUtility;
import com.pricer.product.ProductCDiscount;

public class ThreadCheckCDiscount extends Thread {

	static Logger logger =  LogManager.getLogger(ThreadCheckCDiscount.class);

	static Wini ini;
	static String cdiscountArchiveFolder;	
	static String cdiscountFileName;
	
	static int tempo;
	static String sourceFolder;
	static String temporaryFolder;
	static String pricerDataFilesFolder;
	static String pricerMessageFilesFolder;
	static String pricerResultFilesFolder;
	
	Timer timer = new Timer();
	FileUtility utility = new FileUtility();
	
	public ThreadCheckCDiscount() {

		logger.info("Starting Thread ThreadCheckDataFiles");
		
		InitializeIni();
		
		/*******Archive folders **********/
		cdiscountArchiveFolder		= ini.get("Folders", "CDiscountArchiveFolder");
		
		/********* FileNames *************/
		cdiscountFileName 		= ini.get("Files","CDiscountFileName");
		
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
				
				FileUtility FdataFile = new FileUtility(sourceFolder + "\\" +  cdiscountFileName);
				FileUtility FTemporaryFile = new FileUtility(temporaryFolder + "\\" + cdiscountFileName);

				// check first if something is present in temporary Folder, if not process source Folder

				if (FTemporaryFile.FileExist()) {
					logger.warn ("File is present in temporary Folder !! priority for that !!!!");
					try {
						ProcessFile(FTemporaryFile);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						logger.fatal("ERROR when trying to process file "+FdataFile.getFileName()+" in temporary : "+e);
					}
				}


				else {
					
					//COMPLETE WITH SIC
					CompleteWithSic completeWithSIC = new CompleteWithSic();
					
					//System.out.println("Path source file before completeWithSIC : "+ sourceFile);
			
					if(FdataFile.FileExist() && !FTemporaryFile.FileExist()) {

							
							String sourceFile = sourceFolder + "\\" +cdiscountFileName;
							utility.ZipFile(sourceFolder, "BO"+cdiscountFileName, cdiscountArchiveFolder, cdiscountFileName);
							completeWithSIC.completeWithSic(sourceFile, "S", "0", "0", "45");
							
							utility.ZipFile(sourceFolder, "PRICER"+cdiscountFileName, cdiscountArchiveFolder, cdiscountFileName);
							utility.MoveFile(sourceFolder + "\\" + FdataFile.getFileName(), temporaryFolder + "\\" + FdataFile.getFileName());
							try {
								ProcessFile(FTemporaryFile);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								logger.fatal("ERROR when trying to process file "+FdataFile.getFileName()+" : "+e);

							}
						}					

				}

			}


		};
		
		timer.scheduleAtFixedRate(task, 0, tempo * 1000);
	}
	
	private void ProcessFile(FileUtility FtemporaryFile) throws IOException {
		
		System.out.println("Processing CDiscount file");
		logger.info("Processing data file : " + FtemporaryFile.getFileName() );
		
		boolean bdatafile_Update_opened=false;
		ProductCDiscount cdiscountData = null;
		
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
       	       	       	
       	
        
        dataFileName_Update		=	pricerDataFilesFolder		+ "\\"	+ "data_cdiscount_" + dateOfFile + ".i1";
        messageFileName_Update	=	pricerMessageFilesFolder	+ "\\"	+ "data_cdiscount_" + dateOfFile + ".m1";
        resultFileName_Update	=	pricerResultFilesFolder		+ "\\"	+ "data_cdiscount_" + dateOfFile + ".r7";
        
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
		
		
		cdiscountData = new ProductCDiscount();
		
		cdiscountData.setItemID(splitedTabLine.get(1));
		cdiscountData.setProductID(splitedTabLine.get(2));
		cdiscountData.setItemNameCDiscount(splitedTabLine.get(6));
		cdiscountData.setUrlFicheTechnique(splitedTabLine.get(7));
		cdiscountData.setGarantie(splitedTabLine.get(9));
		cdiscountData.setPictoTitre1(splitedTabLine.get(10));
		cdiscountData.setPictoValeur1(splitedTabLine.get(11));
		cdiscountData.setPictoTitre2(splitedTabLine.get(12));
		cdiscountData.setPictoValeur2(splitedTabLine.get(13));
		cdiscountData.setPictoTitre3(splitedTabLine.get(14));
		cdiscountData.setPictoValeur3(splitedTabLine.get(15));
		cdiscountData.setPictoTitre4(splitedTabLine.get(16));
		cdiscountData.setPictoValeur4(splitedTabLine.get(17));
		cdiscountData.setPictoTitre5(splitedTabLine.get(18));
		cdiscountData.setPictoValeur5(splitedTabLine.get(19));
		cdiscountData.setFlagSoldes(splitedTabLine.get(37));
		cdiscountData.setNoteMoyenne(splitedTabLine.get(40));
		cdiscountData.setNbreAvisClients(splitedTabLine.get(41));
		cdiscountData.setPrixPrecoFournisseur(splitedTabLine.get(42));
		cdiscountData.setDispoPiecesDetachees(splitedTabLine.get(43));
		        
		 
		 

				
		 completeLine.append("0001 ").append(cdiscountData.getItemID());
         completeLine.append(" 121 0 |").append("CDISCOUNT");
         completeLine.append("| 244 0 |").append(cdiscountData.getProductID());
         completeLine.append("| 239 0 |").append(cdiscountData.getUrlFicheTechnique());
         completeLine.append("| 237 0 |").append(cdiscountData.getGarantie());
         completeLine.append("| 227 0 |").append(cdiscountData.getPictoTitre1());
         completeLine.append("| 228 0 |").append(cdiscountData.getPictoValeur1());
         completeLine.append("| 229 0 |").append(cdiscountData.getPictoTitre2());
         completeLine.append("| 230 0 |").append(cdiscountData.getPictoValeur2());
         completeLine.append("| 231 0 |").append(cdiscountData.getPictoTitre3());
         completeLine.append("| 232 0 |").append(cdiscountData.getPictoValeur3());
         completeLine.append("| 233 0 |").append(cdiscountData.getPictoTitre4());
         completeLine.append("| 234 0 |").append(cdiscountData.getPictoValeur4());
         completeLine.append("| 235 0 |").append(cdiscountData.getPictoTitre5());
         completeLine.append("| 236 0 |").append(cdiscountData.getPictoValeur5());
         completeLine.append("| 240 0 |").append(cdiscountData.getNoteMoyenne());
         completeLine.append("| 241 0 |").append(cdiscountData.getNbreAvisClients());
         completeLine.append("| 245 0 |").append(cdiscountData.getPrixPrecoFournisseur());
         completeLine.append("| 238 0 |").append(cdiscountData.getDispoPiecesDetachees());
         completeLine.append("| 300 0 |").append(cdiscountData.getItemNameCDiscount());
         completeLine.append("| 500 0 |").append(cdiscountData.getFlagSoldes());
                 
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
		
		
		System.out.println("delete file " + temporaryFolder + "\\" + cdiscountFileName);
		new File(temporaryFolder + "\\" + cdiscountFileName).delete();
	
		
		
		
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
