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
import com.pricer.product.ProductTechnicalFile;

public class ThreadCheckTechnicalFiles extends Thread {

	static Logger logger =  LogManager.getLogger(ThreadCheckTechnicalFiles.class);

	static Wini ini;
	static String technicalArchiveFolder;	
	static String technicalFileName;
	
	static int tempo;
	static String sourceFolder;
	static String temporaryFolder;
	static String pricerDataFilesFolder;
	static String pricerMessageFilesFolder;
	static String pricerResultFilesFolder;
	
	Timer timer = new Timer();
	FileUtility utility = new FileUtility();
	
	public ThreadCheckTechnicalFiles() {

		logger.info("Starting Thread ThreadCheckTechnicalFiles");
		
		InitializeIni();
		
		/*******Archive folders **********/
		technicalArchiveFolder		= ini.get("Folders", "TechnicalArchiveFolder");
		
		/********* FileNames *************/
		technicalFileName 		= ini.get("Files","TechnicalFileName");
		
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
				
				FileUtility FdataFile = new FileUtility(sourceFolder + "\\" +  technicalFileName);
				FileUtility FTemporaryFile = new FileUtility(temporaryFolder + "\\" + technicalFileName);

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

							
							String sourceFile = sourceFolder + "\\" +technicalFileName;
							utility.ZipFile(sourceFolder, "BO"+technicalFileName, technicalArchiveFolder, technicalFileName);
							completeWithSIC.completeWithSic(sourceFile, "S", "0", "0", "46");
							
							utility.ZipFile(sourceFolder, "PRICER"+technicalFileName, technicalArchiveFolder, technicalFileName);
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
		
		System.out.println("Processing Technical file");
		logger.info("Processing data file : " + FtemporaryFile.getFileName() );
		
		boolean bdatafile_Update_opened=false;
		ProductTechnicalFile technicalData = null;
		
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
       	       	       	
       	
        
        dataFileName_Update		=	pricerDataFilesFolder		+ "\\"	+ "data_technical_" + dateOfFile + ".i1";
        messageFileName_Update	=	pricerMessageFilesFolder	+ "\\"	+ "data_technical_" + dateOfFile + ".m1";
        resultFileName_Update	=	pricerResultFilesFolder		+ "\\"	+ "data_technical_" + dateOfFile + ".r7";
        
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
		
		
		technicalData = new ProductTechnicalFile();
		
//		technicalData.setItemID(splitedTabLine.get(1));
//		technicalData.setProductID(splitedTabLine.get(2));
//		technicalData.setItemNamePeche(splitedTabLine.get(3));
//		technicalData.setItemNameCDiscount(splitedTabLine.get(6));
//		technicalData.setUrlFicheTechnique(splitedTabLine.get(7));
//		technicalData.setGarantie(splitedTabLine.get(9));
//		technicalData.setPictoTitre1(splitedTabLine.get(10));
//		technicalData.setPictoValeur1(splitedTabLine.get(11));
//		technicalData.setPictoTitre2(splitedTabLine.get(12));
//		technicalData.setPictoValeur2(splitedTabLine.get(13));
//		technicalData.setPictoTitre3(splitedTabLine.get(14));
//		technicalData.setPictoValeur3(splitedTabLine.get(15));
//		technicalData.setPictoTitre4(splitedTabLine.get(16));
//		technicalData.setPictoValeur4(splitedTabLine.get(17));
//		technicalData.setPictoTitre5(splitedTabLine.get(18));
//		technicalData.setPictoValeur5(splitedTabLine.get(19));
//		technicalData.setFlagSoldes(splitedTabLine.get(37));
//		technicalData.setNoteMoyenne(splitedTabLine.get(40));
//		technicalData.setNbreAvisClients(splitedTabLine.get(41));
//		technicalData.setPrixPrecoFournisseur(splitedTabLine.get(42));
//		technicalData.setDispoPiecesDetachees(splitedTabLine.get(43));
//		technicalData.setCommercialInfo(splitedTabLine.get(44));
//		technicalData.setWeigherKey(splitedTabLine.get(45));
		
		technicalData.setItemID(splitedTabLine.get(1));
		technicalData.setProductID(splitedTabLine.get(2));
		technicalData.setItemNamePeche(splitedTabLine.get(5));
		technicalData.setItemNameCDiscount(splitedTabLine.get(8));
		technicalData.setUrlFicheTechnique(splitedTabLine.get(9));
		technicalData.setGarantie(splitedTabLine.get(11));
		technicalData.setPictoTitre1(splitedTabLine.get(12));
		technicalData.setPictoValeur1(splitedTabLine.get(13));
		technicalData.setPictoTitre2(splitedTabLine.get(14));
		technicalData.setPictoValeur2(splitedTabLine.get(15));
		technicalData.setPictoTitre3(splitedTabLine.get(16));
		technicalData.setPictoValeur3(splitedTabLine.get(17));
		technicalData.setPictoTitre4(splitedTabLine.get(18));
		technicalData.setPictoValeur4(splitedTabLine.get(19));
		technicalData.setPictoTitre5(splitedTabLine.get(20));
		technicalData.setPictoValeur5(splitedTabLine.get(21));
		technicalData.setPictoTitre7(splitedTabLine.get(34));
		technicalData.setPictoValeur7(splitedTabLine.get(35));
		technicalData.setPictoTitre8(splitedTabLine.get(36));
		technicalData.setPictoValeur8(splitedTabLine.get(37));
		technicalData.setFlagSoldes(splitedTabLine.get(39));
		technicalData.setNoteMoyenne(splitedTabLine.get(42));
		technicalData.setNbreAvisClients(splitedTabLine.get(43));
		technicalData.setPrixPrecoFournisseur(splitedTabLine.get(44));
		technicalData.setDispoPiecesDetachees(splitedTabLine.get(45));
		technicalData.setCommercialInfo(splitedTabLine.get(46));
		technicalData.setWeigherKey(splitedTabLine.get(47));
		
		        
		 
		 

				
		 completeLine.append("0001 ").append(technicalData.getItemID());
         completeLine.append(" 121 0 |").append("CDISCOUNT");
         completeLine.append("| 244 0 |").append(technicalData.getProductID());
         completeLine.append("| 239 0 |").append(technicalData.getUrlFicheTechnique());
         completeLine.append("| 237 0 |").append(technicalData.getGarantie());
         completeLine.append("| 227 0 |").append(technicalData.getPictoTitre1());
         completeLine.append("| 228 0 |").append(technicalData.getPictoValeur1());
         completeLine.append("| 229 0 |").append(technicalData.getPictoTitre2());
         completeLine.append("| 230 0 |").append(technicalData.getPictoValeur2());
         completeLine.append("| 231 0 |").append(technicalData.getPictoTitre3());
         completeLine.append("| 232 0 |").append(technicalData.getPictoValeur3());
         completeLine.append("| 233 0 |").append(technicalData.getPictoTitre4());
         completeLine.append("| 234 0 |").append(technicalData.getPictoValeur4());
         completeLine.append("| 235 0 |").append(technicalData.getPictoTitre5());
         completeLine.append("| 236 0 |").append(technicalData.getPictoValeur5());
         completeLine.append("| 627 0 |").append(technicalData.getPictoTitre7());
         completeLine.append("| 628 0 |").append(technicalData.getPictoValeur7());
         completeLine.append("| 629 0 |").append(technicalData.getPictoTitre8());
         completeLine.append("| 630 0 |").append(technicalData.getPictoValeur8());
         completeLine.append("| 240 0 |").append(technicalData.getNoteMoyenne());
         completeLine.append("| 241 0 |").append(technicalData.getNbreAvisClients());
         completeLine.append("| 245 0 |").append(technicalData.getPrixPrecoFournisseur());
         completeLine.append("| 238 0 |").append(technicalData.getDispoPiecesDetachees());
         completeLine.append("| 300 0 |").append(technicalData.getItemNameCDiscount());
         completeLine.append("| 500 0 |").append(technicalData.getFlagSoldes());
         completeLine.append("| 94 0 |").append(technicalData.getWeigherKey());
         completeLine.append("| 605 0 |").append(technicalData.getCommercialInfo());
         completeLine.append("| 610 0 |").append(technicalData.getItemNamePeche());
                 
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
		
		
		System.out.println("delete file " + temporaryFolder + "\\" + technicalFileName);
		new File(temporaryFolder + "\\" + technicalFileName).delete();
	
		
		
		
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
