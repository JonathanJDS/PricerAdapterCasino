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
import java.util.Timer;
import java.util.TimerTask;

import org.apache.logging.log4j.*;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

import com.pricer.model.FileUtility;
import com.pricer.product.ProductVLL;

public class ThreadCheckVLL extends Thread {
	

	static Logger logger =  LogManager.getLogger(ThreadCheckVLL.class);
	static Wini ini;
	static String vllArchiveFolder;	
	static String vllFileName;
	
	static int tempo;
	static String sourceFolder;
	static String temporaryFolder;
	static String pricerDataFilesFolder;
	static String pricerMessageFilesFolder;
	static String pricerResultFilesFolder;
	
	Timer timer = new Timer();
	FileUtility utility = new FileUtility();
	
	public ThreadCheckVLL() {

		logger.info("Starting Thread ThreadCheckVLLFiles");
		
		InitializeIni();
		
		/*******Archive folders **********/
		vllArchiveFolder		= ini.get("Folders", "VLLArchiveFolder");
		
		/********* FileNames *************/
		vllFileName 		= ini.get("Files","VLLFileName");
		
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
				
				ArrayList<String> lstFiles = utility.listFilesFromDirectory(sourceFolder + "\\", vllFileName);
				ArrayList<String> lstFilesTemporary = utility.listFilesFromDirectory(temporaryFolder + "\\", vllFileName);


				// check first if something is present in temporary Folder, if not process source Folder

				if (lstFilesTemporary.size() != 0) {

					logger.warn ("File is present in temporary Folder !! priority for that !!!!");
					// processing all files from temporary.

						try {
							ProcessFile(temporaryFolder + "\\" + vllFileName);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					

				}


				else {
					
					//COMPLETE WITH SIC
					CompleteWithSic completeWithSIC = new CompleteWithSic();
					
					//System.out.println("Path source file before completeWithSIC : "+ sourceFile);
			
					for (String fileNameFilter : lstFiles) {
						// process only one file in temporary (one by one ) .
						if (lstFilesTemporary.size() == 0) {
							
							String sourceFile = sourceFolder + "\\" +vllFileName;
							utility.ZipFile(sourceFolder, "BO"+vllFileName, temporaryFolder, vllFileName, vllArchiveFolder);
							completeWithSIC.completeWithSic(sourceFile, "F", "5", "18", "307");
							
							utility.ZipFile(sourceFolder, "PRICER"+vllFileName, temporaryFolder, vllFileName, vllArchiveFolder);
							utility.MoveFile(sourceFolder + "\\" + vllFileName, temporaryFolder + "\\" + vllFileName);
						}
							try {
								ProcessFile(temporaryFolder + "\\" + vllFileName);
							} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							}
					}

				}

			}


		};
		
		timer.scheduleAtFixedRate(task, 0, tempo * 1000);
	}
	
	private void ProcessFile(String temporaryFile) throws IOException {
		
		System.out.println("Processing VLL file");
		logger.info("Processing VLL file !");
		
		FileUtility fpTemporaryFile = new FileUtility(temporaryFile);
		boolean bdatafile_Update_opened=false;
		ProductVLL vllData = null;
		
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
       	       	       	
       	
        
        dataFileName_Update		=	pricerDataFilesFolder		+ "\\"	+ "data_vll_" + dateOfFile + ".i1";
        messageFileName_Update	=	pricerMessageFilesFolder	+ "\\"	+ "data_vll_" + dateOfFile + ".m1";
        resultFileName_Update	=	pricerResultFilesFolder		+ "\\"	+ "data_vll_" + dateOfFile + ".r7";
        
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
		System.out.println(fpTemporaryFile.getPathFilename());


		List<String> lstMapFile = fpTemporaryFile.fileToMap();
		
		
		for (String line : lstMapFile) {
			
			//System.out.println("line = " + line);
						
			
			//List<String> splitedTabLine = splitLine(line, ";");

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
		
		
		vllData = new ProductVLL();
		
		vllData.setCodeMagasin(line.substring(0, 5));
		vllData.setItemID(line.substring(5,14));
		vllData.setDebutPromo(line.substring(14, 26));
		vllData.setFinPromo(line.substring(26, 38));
		vllData.setQteLot(line.substring(38, 42));
		vllData.setPricePromoLot(line.substring(42, 48));
		vllData.setNameLot(line.substring(48, 303).trim()); 

				
		 completeLine.append("0001 ").append(vllData.getItemID());
         completeLine.append(" 400 0 |").append(vllData.getCodeMagasin());
         completeLine.append("| 401 0 |").append(vllData.getDebutPromo());
         completeLine.append("| 402 0 |").append(vllData.getFinPromo());
         completeLine.append("| 218 0 |").append(vllData.getQteLot());
         completeLine.append("| 217 0 |").append(vllData.getPricePromoLot());
         completeLine.append("| 403 0 |").append(vllData.getNameLot());

                 
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
		
		
		System.out.println("delete file " + temporaryFolder + "\\" + vllFileName);

		new File(temporaryFolder + "\\" + vllFileName).delete();


		
		
		
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
