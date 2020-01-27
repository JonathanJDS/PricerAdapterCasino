package com.pricer.main;


import com.pricer.model.FileUtility;
import com.pricer.product.ProductBase;

//import org.apache.log4j.Logger;
import com.pricer.product.ProductPrice;
import com.pricer.product.ProductToDelete;
import org.apache.logging.log4j.*;

import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;
import se.pricer._interface.public_5_0.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ThreadCheckPriceFiles extends Thread {

	static Logger logger =  LogManager.getLogger(ThreadCheckPriceFiles.class);

	static Wini ini;
	static String priceArchiveFolder;
	static String priceFileName;
	static String pricerRelFileName;
	static String pricerRelArchiveFolder; // zip EEGEAN into REL Folder

	static int tempo;
	static String sourceFolder;
	static String temporaryFolder;
	static String pricerDataFilesFolder;
	static String pricerMessageFilesFolder;
	static String pricerResultFilesFolder;
	static HashMap<String, String> lstFormatLabels;

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
		pricerRelArchiveFolder		= ini.get("Folders", "RelArchiveFolder");


		this.lstFormatLabels = new HashMap<>();
		for (String key : ini.get("FormatLabels").keySet()) {
		this.lstFormatLabels.put(key, ini.get("FormatLabels").fetch(key));
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
							utility.ZipFile(sourceFolder, FdataFile.getFileName(), priceArchiveFolder, FdataFile.getFileName());
							utility.MoveFile(sourceFolder + "\\" + FdataFile.getFileName(), temporaryFolder + "\\" + FdataFile.getFileName());
							ProcessFile(FTemporaryFile);
						}


					// check and zip list of product to print
					if (FEEGEANFile.FileExist() && !FTemporaryEEGEANFile.FileExist()) {
							utility.ZipFile(sourceFolder, FEEGEANFile.getFileName(), pricerRelArchiveFolder, FEEGEANFile.getFileName());
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
		boolean bdatafile_Delete_opened=false;


		ProductBase priceData = null;
		OperationOnDB opDB = null;
		
		Date d = new Date(); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_Hmmss");
        String dateOfFile		=	sdf.format(d);
				
	    String dataFileName_Update		=	null;
        String messageFileName_Update	=	null;
		String messageFileName_UpdateUnderProcess = null;

		String resultFileName_Update	=	null;
        String contentMessageFile_Update = null;
		String dataFileName_Delete		=	null;
		String messageFileName_Delete	=	null;
		String resultFileName_Delete	=	null;


        PrintStream datafile_Update		=	null;
       	PrintStream messagefile_Update	=	null;


		PrintStream datafile_Delete		=	null;
		PrintStream messagefile_Delete	=	null;

		StringBuffer completeLine ;


		String model1 = "|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|XXXXXXXX|XXXXXXXX|XXXXXXXX|X|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|XXXXX|XXXXXXXXXXXXXXX|XXXXXXXXXXXXXXX|XXXXXXXXXXXXXXX|XXXXXXXXXXXXXXX|XX|XX|XXXXX|XX|";
		String model2 = "|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|\":SUPP \"|\":SUPP \"|\":SUPP \"|X|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|XXXXX|XXXXXXXXXXXXXXX|XXXXXXXXXXXXXXX|XXXXXXXXXXXXXXX|XXXXXXXXXXXXXXX|XX|00|XXXXX|XX|";
		ProductPrice product = null;
		List<String> lstEANSics = null ;
		List<ProductPrice> lstProducts = new ArrayList<ProductPrice>();
		List<ProductToDelete> lstDelete = new ArrayList<ProductToDelete>();
		List<ProductToDelete> lstDeletePFI = new ArrayList<ProductToDelete>();
		List<String> lstEANTOPrint = null;
		boolean isnewProduct = true;
		String opCode;
		String lineToCheck ;
		List<String> lstLineFromLstFile;
		List<String> lineFromLstFile1 = null;
		List<String> lineFromLstFile2;
		String Newligne=System.getProperty("line.separator"); /* just adding cr/lf at the end of each line */




		dataFileName_Update					=	pricerDataFilesFolder		+ "\\"	+ "data_price_" 			 + dateOfFile + ".i1";
        messageFileName_Update				=	pricerMessageFilesFolder	+ "\\"	+ "data_price_" 			 + dateOfFile + ".m1";
		messageFileName_UpdateUnderProcess	=	pricerMessageFilesFolder 	+ "\\"  + "UnderProcess\\data_price" + dateOfFile + ".m1";
		resultFileName_Update				=	pricerResultFilesFolder		+ "\\"	+ "data_price_" 			 + dateOfFile + ".r7";
        
        contentMessageFile_Update = "UPDATE,0001,," + dataFileName_Update + "," + resultFileName_Update;
		String contentMessageFile_Delete 	=	"DELETE,0001,,"	+	dataFileName_Delete +	","	+	resultFileName_Delete;
		
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
		for (int i=0;i<lstMapFile.size();i++) {

			List<String> splitedTabLine = splitLine(lstMapFile.get(i), "|");




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

		opCode = splitedTabLine.get(0); // Code Operation
		lineToCheck = "|";
		for (int k = 3; k <= 17; k++) {

			try {

				//System.out.println("adding : " + lstLineFromLstFile.get(k));
				lineToCheck = lineToCheck + splitedTabLine.get(k) + "|";
			} catch (IndexOutOfBoundsException iobe) {
				logger.warn("anomalie the line is not formated correctly : rejected " + "==>" + lstMapFile.get(i));

			}

		}


		if (opCode.equals("02") || opCode.equals("22")) {
			ProductToDelete productToDeletePFI = new ProductToDelete();
			productToDeletePFI.setCodeInterne(splitedTabLine.get(1));
			lstDeletePFI.add(productToDeletePFI);
		}


		if (opCode.equals("04")) {
			ProductToDelete productToDelete04 = new ProductToDelete();
			productToDelete04.setCodeInterne(splitedTabLine.get(0));
			productToDelete04.setEAN(splitedTabLine.get(1));
			lstDelete.add(productToDelete04);
		}

		if (opCode.equals("03") && lineToCheck.equals(model1) == false && lineToCheck.equals(model2) == false && isnewProduct == false) {
			product.setLstEANsic(lstEANSics);
			lstProducts.add(product);
			//System.out.println("new product added : " + product.getCodeInterne());
			isnewProduct = true;
			//lstFile.remove(i);
		}


		if (opCode.equals("03") && lineToCheck.equals(model1) == false && lineToCheck.equals(model2) == false && isnewProduct == true) {


			product = new ProductPrice();
			product.setCodeInterne(splitedTabLine.get(1));
			product.setEAN(splitedTabLine.get(2));
			product.setLibelle(splitedTabLine.get(3));
			product.setPrix(splitedTabLine.get(4));
			product.setPrixUnitaire(splitedTabLine.get(5));
			product.setPrixEnFranc(splitedTabLine.get(6));
			product.setFlagPromo(splitedTabLine.get(7));
			product.setPav(splitedTabLine.get(8).substring(0, 1));
			product.setFlagPrixKilo(splitedTabLine.get(8).substring(1, 2));
			product.setUniteDeVente(splitedTabLine.get(9));
			product.setFamille(splitedTabLine.get(10));
			product.setContenance(splitedTabLine.get(11));
			product.setEanAimprimer(splitedTabLine.get(12));
			product.setCodeLogo(splitedTabLine.get(15));
			product.setSticker(splitedTabLine.get(16));
			product.setTypeEEG(splitedTabLine.get(17));


			//System.out.println("checking ecopart  = " + line.substring(181,196));
			if (splitedTabLine.get(13).trim().equalsIgnoreCase("Dont Ecopart")) {
				product.setFlagECOPART("1");
				lineFromLstFile2 = splitLine(lstMapFile.get(i + 2), "|");
				product.setMontantECOPART(lineFromLstFile2.get(6).replace(":E", "").trim());
			} else {


				product.setFlagECOPART("0");
				product.setMontantECOPART("");
			}


			lineFromLstFile1 = splitLine(lstMapFile.get(i + 1), "|");
			product.setDateApplication(lineFromLstFile1.get(6));


			lstEANSics = new ArrayList<String>();
			isnewProduct = false;
			//lstFile.remove(i);

		}


		if (opCode.equals("03") && lineToCheck.equals(model1) == true && lineToCheck.equals(model2) == false && isnewProduct == false) {
			if (splitedTabLine.get(1).toString().equals(product.getCodeInterne()) == true) {
				lstEANSics.add(splitedTabLine.get(2));
				isnewProduct = false;
			} else {

				logger.warn("anomalie main EAN is not the same at :" + lstMapFile.get(i));

			}
			//lstFile.remove(i);



		product.setLstEANsic(lstEANSics);
		lstProducts.add(product);
		StringBuffer completeLine2 = new StringBuffer();



		for (ProductPrice produit : lstProducts) {

			try {


				if (bdatafile_Update_opened == false) {


					try {
						datafile_Update = new PrintStream(new BufferedOutputStream(new FileOutputStream(dataFileName_Update, true)), true);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					bdatafile_Update_opened = true;
				}


				completeLine2.append("0001 ").append(String.format("%06d", Integer.parseInt(produit.getCodeInterne())));
				//completeLine2.append(" 252 0 |").append(produit.getCodeInterne());
				completeLine2.append(" 7 0 |").append(produit.getLibelle().trim());
				completeLine2.append("| 23 0 |").append(produit.getPrix().replace(",", ""));
				completeLine2.append("| 45 0 |").append(produit.getPrixUnitaire().replace(",", ""));
				completeLine2.append("| 24 0 |").append(produit.getPrixEnFranc());
				completeLine2.append("| 37 0 |").append(produit.getFlagPromo());
				completeLine2.append("| 71 0 |").append(produit.getUniteDeVente().trim());
				completeLine2.append("| 5 0 |").append(produit.getFamille().trim());
				completeLine2.append("| 12 0 |").append(produit.getContenance().trim());
				completeLine2.append("| 330 0 |").append(produit.getEanAimprimer().trim());
				completeLine2.append("| 88 0 |").append(produit.getCodeLogo());
				completeLine2.append("| 251 0 |").append(produit.getTypeEEG());
				completeLine2.append("| 452 0 |").append(produit.getPav());
				completeLine2.append("| 453 0 |").append(produit.getFlagPrixKilo());
				completeLine2.append("| 40 0 |").append(produit.getMontantECOPART());
				completeLine2.append("| 41 0 |").append(produit.getFlagECOPART());


				SimpleDateFormat formatter = new SimpleDateFormat("ddMMyy");
				SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");


				String dateApplicationFormatted = new String();


				try {
					dateApplicationFormatted = formatter2.format(formatter.parse(produit.getDateApplication().replace("{", "").trim()));
				} catch (ParseException e) {
					logger.fatal("unable to parse date for " + produit.getDateApplication() + "=> " + e.getMessage() + "..." + e.getCause());
					e.printStackTrace();
				}


				completeLine2.append("| 9500 0 |").append(dateApplicationFormatted + " 00:30:00");

				completeLine2.append("|,");
				completeLine2.append(Newligne);


				if (produit.getLstEANsic().size() == 0) {

					completeLine2.append("0001 ").append(String.format("%06d", Integer.parseInt(produit.getCodeInterne())));
					// completeLine2.append("0001 ").append(produit.getCodeInterne());
					completeLine2.append(" 9510 0 |").append(produit.getEAN()).append(" ");
					completeLine2.append("|,");
					completeLine2.append(Newligne);

				}


				if (produit.getLstEANsic().size() > 0) {
					completeLine2.append("0001 ").append(String.format("%06d", Integer.parseInt(produit.getCodeInterne())));
					// completeLine2.append("0001 ").append(produit.getCodeInterne());
					completeLine2.append(" 9510 0 |").append(produit.getEAN()).append(" ").append(produit.getLstEANsic().toString().replace("[", "").replace("]", "").replace(",", ""));
					completeLine2.append("|,");
					completeLine2.append(Newligne);

				}

			} catch (NullPointerException ex) {
				System.out.println("Produit is null");
			}


		}
	}
		datafile_Update.println(completeLine.toString());
		logger.info("datafile (" +  dataFileName_Update + ") is written......");


	}
			catch (IndexOutOfBoundsException indx){
			logger.log(Level.getLevel("REJECTED"),"line rejected" + lstMapFile.get(i));
		 }


			datafile_Update.flush();



		}



		if (lstDeletePFI.size()>0){
			try {
				if (bdatafile_Delete_opened==false){

						datafile_Delete=new PrintStream(new BufferedOutputStream(new FileOutputStream(dataFileName_Delete,true)),true);

					}
					bdatafile_Delete_opened=true;

							}
			catch (FileNotFoundException e) {

				logger.fatal("unable to print into : " + dataFileName_Delete);
			}



			StringBuffer completeLineDelete = new StringBuffer();

			for (ProductToDelete productToDeletePFI : lstDeletePFI) {

				try {


					completeLineDelete.append("0001 ").append(String.format("%06d",Integer.parseInt(productToDeletePFI.getCodeInterne()))).append(",").append(Newligne);

				}
				catch(NumberFormatException nfe) {
					logger.warn(nfe.getMessage() + " unable to format product to 6 digits with code interne : " + productToDeletePFI.getCodeInterne());
				}


			}

			datafile_Delete.println(completeLineDelete.toString());
			datafile_Delete.flush();

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

		if (bdatafile_Delete_opened==true){
			datafile_Delete.close();
			try {
				messagefile_Delete=new PrintStream(new BufferedOutputStream(new FileOutputStream(messageFileName_Delete)),true);
				messagefile_Delete.print(contentMessageFile_Delete);
				messagefile_Delete.flush();
				messagefile_Delete.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}


		FileUtility FTemporaryEEGEANFile = new FileUtility(temporaryFolder + "\\" + pricerRelFileName);

		if (FTemporaryEEGEANFile.FileExist()  && !FTemporaryEEGEANFile.fileIsGrowing()) {

			lstEANTOPrint = FTemporaryEEGEANFile.fileToMap();


		}



		// si il y a des eans � imprimer, cr�er une liste d'impression
		if (lstEANTOPrint != null && lstEANTOPrint.size()>0) {


			FileUtility messageFile	= new FileUtility(	messageFileName_Update);
			FileUtility messageFileUpdateUnderProcess	= new FileUtility(	messageFileName_UpdateUnderProcess);


			while(messageFile.FileExist()==true || messageFileUpdateUnderProcess.FileExist()==true) {


				try {
					logger.info("Waiting for m1 file integration : " + messageFile.getFileName());
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					logger.error(e.getMessage() + "..." + e.getCause());
				}

			}



			// ok fichier message supprim?, cr?er la liste d'impression.



			// attente creation du fichier result  resultFileName_Update
			FileUtility fpResultFileName_Update= new FileUtility(resultFileName_Update);

			while(fpResultFileName_Update.FileExist()==false) {


				try {
					logger.info("Waiting for Result file : " + fpResultFileName_Update.getFileName());
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					logger.error(e.getMessage() + "..." + e.getCause());
				}

			}


			logger.info("create printer List");


			final String API_USER = ini.get("API","API_USER");
			final String API_KEY = ini.get("API","API_KEY");
			final String pricerServerAddress = ini.get("API","Host");
			ArrayList<PrintRequest> lstRemotePrint = new ArrayList<PrintRequest>();
			java.text.SimpleDateFormat sdf3 = new java.text.SimpleDateFormat("yyMMdd_HHmmss");

			PricerPublicAPI50 pricerInterfaceR5  = new R5WSAPI(API_USER,API_KEY).getPricerInterfaceR5();


			String itemID;
			Integer defaultModel = null;
			String defaultModelIdFromCasino = "";
			String defaultModelNameFromCasino = "";
			Integer defaultModelIdFromPricer=null;

			Item item50 = null;
			List<ESLModel> lstESLModels = null;

			List<PropertyValue> lstitemproperty=null;
			boolean isItemExist = false;

			for (String line : lstEANTOPrint) {

				if (line.trim()!="" && line!=null && line.trim().length()>4) {
					//System.out.println("line of eegean = " + line);

					String[] splitItemID= line.split(" ");

					itemID= splitItemID[0].trim();


					logger.info("ItemID For remote Printing = " + itemID);
					//se.pricer.r3_3.remote.RemotePrintRequest rpq = new se.pricer.r3_3.remote.RemotePrintRequest(batchName, noOfCopies, modelName, orderNumber, aisle, itemId, userId, itemName, transmit)



						item50 = pricerInterfaceR5.getItem(itemID);
						isItemExist = true;


					if (isItemExist==true) {
						lstitemproperty = item50.getItemProperties();

						for (PropertyValue propertyValue : lstitemproperty){

							//System.out.println("checking property : " + propertyValue.getValue());

							if (propertyValue.getId().getName().equalsIgnoreCase("FORMAT_LABEL")){


								//System.out.println("found default model Id from   = " + itemID + " : " + propertyValue.getValue());
								defaultModelIdFromCasino = propertyValue.getValue();



							}



						}

						// now we need to find format label name from xml config file



						defaultModelNameFromCasino = lstFormatLabels.get(defaultModelIdFromCasino);
						lstESLModels = pricerInterfaceR5.getEslModels();



						for (ESLModel ESLmodel : lstESLModels) {

							if (    ESLmodel.getName().equalsIgnoreCase(defaultModelNameFromCasino)) {


								defaultModel = ESLmodel.getId();
								break;
							}


						}


						PrintRequest rpq = new PrintRequest();
						rpq.setItemId(itemID);
						rpq.setModelId(defaultModel);
						rpq.setNumOfCopies(1);
						//se.pricer.r3_3.remote.RemotePrintRequest rpq = new se.pricer.r3_3.remote.RemotePrintRequest("REL_" + sdf3.format(new Date()), "1", "Default", "1", "1", itemID, "config", "", false);
						lstRemotePrint.add(rpq);
						//	System.out.println(itemID + " added in remote print list " + "print_" + sdf3.format(new Date()));

						logger.info(" added in remote print list " + "print_" + sdf3.format(new Date()));


					}


					else if(isItemExist==false) {

						logger.warn("item not exist : " + itemID);
					}

				}






			}








				logger.info("SaveOverlayRequest lstRemotePrint, Nbre of products =  " + lstRemotePrint.size());


				//PrintBatch printBatch = new PrintBatch("REL_" + sdf3.format(new Date()), "config", lstRemotePrint);
			PrintBatch printBatch = new PrintBatch();
			printBatch.setBatchName("REL_" + sdf3.format(new Date()));
				printBatch.setRequests(lstRemotePrint);
				//printBatch.setBatchId("config");

				pricerInterfaceR5.savePrintBatch(printBatch);
				//gui.saveOverlayRequest(lstRemotePrint);

				logger.info("Operation Done ");


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
