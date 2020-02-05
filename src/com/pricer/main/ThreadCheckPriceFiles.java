package com.pricer.main;


import com.pricer.model.FileUtility;
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
	static String internalCodeSize;
	static HashMap<String, String> lstFormatLabels;
	Timer timer = new Timer();
	FileUtility utility = new FileUtility();


	public ThreadCheckPriceFiles() {

		logger.info("Starting Thread ThreadCheckPriceFiles");
		InitializeIni();


		/*******Archive folders **********/
		priceArchiveFolder		= ini.get("Folders", "PriceArchiveFolder");

		/********* FileNames *************/
		priceFileName 		= ini.get("Files","PriceFileName");
		pricerRelFileName	= ini.get("Files","RelFileName");
		
		/*****Format DATA ***********/
		
		internalCodeSize	= ini.get("FormatData", "InternalCodeSize");


		/*****Pricer Path **************/
		tempo 						= Integer.valueOf(ini.get("Files", "timer"));
		sourceFolder 				= ini.get("Folders", "SourceFolder");
		temporaryFolder 			= ini.get("Folders", "TemporaryFolder");
		pricerDataFilesFolder 		= ini.get("Folders", "PricerDataFiles");
		pricerMessageFilesFolder 	= ini.get("Folders", "PricerMessageFiles");
		pricerResultFilesFolder		= ini.get("Folders", "PricerResultFiles");
		pricerRelArchiveFolder		= ini.get("Folders", "RelArchiveFolder");


		lstFormatLabels = new HashMap<>();
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
						utility.ZipFile(sourceFolder, FdataFile.getFileName(), priceArchiveFolder, FdataFile.getFileName());
						utility.MoveFile(sourceFolder + "\\" + FdataFile.getFileName(), temporaryFolder + "\\" + FdataFile.getFileName());




					// check and zip list of product to print
					if (FEEGEANFile.FileExist() && !FTemporaryEEGEANFile.FileExist()) {
						utility.ZipFile(sourceFolder, FEEGEANFile.getFileName(), pricerRelArchiveFolder, FEEGEANFile.getFileName());
						utility.MoveFile(sourceFolder + "\\" + FEEGEANFile.getFileName(), temporaryFolder + "\\" + FEEGEANFile.getFileName());
					}


					ProcessFile(FTemporaryFile);


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

		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_Hmmss");
		String dateOfFile		=	sdf.format(d);
		String dataFileName_Update;
		String messageFileName_Update;
		String messageFileName_UpdateUnderProcess;
		String resultFileName_Update;
		String contentMessageFile_Update;
		String dataFileName_Delete		=	null;
		String messageFileName_Delete	=	null;
		String resultFileName_Delete	=	null;


		PrintStream datafile_Update		=	null;
		PrintStream messagefile_Update	=	null;
		PrintStream datafile_Delete		=	null;
		PrintStream messagefile_Delete	=	null;

		//StringBuffer completeLine ;

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
		StringBuffer completeLine2 = null;

		dataFileName_Update					=	pricerDataFilesFolder		+ "\\"	+ "data_price_" 			 + dateOfFile + ".i1";
		messageFileName_Update				=	pricerMessageFilesFolder	+ "\\"	+ "data_price_" 			 + dateOfFile + ".m1";
		messageFileName_UpdateUnderProcess	=	pricerMessageFilesFolder 	+ "\\"  + "UnderProcess\\data_price" + dateOfFile + ".m1";
		resultFileName_Update				=	pricerResultFilesFolder		+ "\\"	+ "data_price_" 			 + dateOfFile + ".r7";


		dataFileName_Delete					=	pricerDataFilesFolder		+ "\\"	+	"delete_"		+ dateOfFile + ".i1";
		messageFileName_Delete				=	pricerMessageFilesFolder	+ "\\"	+ 	"delete_"	+ dateOfFile + ".m1";
		resultFileName_Delete				=	pricerResultFilesFolder		+ "\\"	+	"delete_"	+ dateOfFile + ".r7";


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


			try {

				opCode = splitedTabLine.get(0); // Code Operation
				lineToCheck = "|";
				for (int k = 3; k <= 17; k++) {

					try {


						lineToCheck = lineToCheck + splitedTabLine.get(k) + "|";
					} catch (IndexOutOfBoundsException iobe) {
						logger.warn("anomalie the line is not formated correctly : rejected " + "==>" + lstMapFile.get(i));
						logger.log(Level.getLevel("REJECTED"),"line rejected" + lstMapFile.get(i));
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
					isnewProduct = true;

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

				}


				if (opCode.equals("03") && lineToCheck.equals(model1) == true && lineToCheck.equals(model2) == false && isnewProduct == false) {
					if (splitedTabLine.get(1).equals(product.getCodeInterne()) == true) {
						lstEANSics.add(splitedTabLine.get(2));
						isnewProduct = false;
					} else {

						logger.warn("anomalie main EAN is not the same at :" + lstMapFile.get(i));

					}

					product.setLstEANsic(lstEANSics);

				}

			}

			catch (IndexOutOfBoundsException indx){
				logger.log(Level.getLevel("REJECTED"),"line rejected" + lstMapFile.get(i));

			}


		}



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

				completeLine2 = new StringBuffer();
				completeLine2.append("0001 ").append(String.format("%"+internalCodeSize+"d",Integer.parseInt(produit.getCodeInterne())));
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


				String dateApplicationFormatted = "";


				try {
					dateApplicationFormatted = formatter2.format(formatter.parse(produit.getDateApplication().replace("{", "").trim()));
				} catch (ParseException e) {
					logger.fatal("unable to parse date for " + produit.getDateApplication() + "=> " + e.getMessage() + "..." + e.getCause());
					//logger.warn("anomalie the line is not formated correctly : rejected " + "==>" + lstMapFile.get(i));
					logger.log(Level.getLevel("REJECTED"),"Product rejected" + product.getCodeInterne());

				}

				if (produit.getLstEANsic().size() > 0) {
					completeLine2.append("0001 ").append(String.format("%"+internalCodeSize+"d", Integer.parseInt(produit.getCodeInterne())));
					completeLine2.append("| 9510 0 |").append(produit.getEAN()).append(" ").append(produit.getLstEANsic().toString().replace("[", "").replace("]", "").replace(",", ""));

				}


				completeLine2.append("| 9500 0 |").append(dateApplicationFormatted + " 00:30:00");
				completeLine2.append("|,");


			} catch (NullPointerException ex) {
				System.out.println("Produit is null");
			}
			datafile_Update.println(completeLine2.toString());
		}


		datafile_Update.flush();
		logger.info("datafile (" +  dataFileName_Update + ") is written......");



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


					completeLineDelete.append("0001 ").append(String.format("%"+internalCodeSize+"d",Integer.parseInt(productToDeletePFI.getCodeInterne()))).append(",").append(Newligne);

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



		if (lstDelete.size()>0) {


			// suppressions des items
			StringBuffer deleteRequest =new StringBuffer();

			deleteRequest.append("delete from item_sic where (");
			OperationOnDB operationondbDelete = new OperationOnDB();
			for (ProductToDelete productToDelete04 : lstDelete) {


				//System.out.println( "product code interne : " + productToDelete04.getCodeInterne() + " // EAN :" + productToDelete04.getEAN());



				deleteRequest.append( "SIC=").append("'").append(String.format("%"+internalCodeSize+"d",Integer.parseInt(productToDelete04.getCodeInterne()))).append("'").append(" And ITEMID=").append("'").append(productToDelete04.getEAN()).append("'").append(")").append(" OR (");




			}

			StringBuffer strDelete = new StringBuffer();
			strDelete.append( deleteRequest.substring(0, deleteRequest.length()-4).toString());
			strDelete.append(";");
			//System.out.println("request delete = " + strDelete);
			operationondbDelete.deleteItemSIC(strDelete);

		}



		FileUtility FTemporaryEEGEANFile = new FileUtility(temporaryFolder + "\\" + pricerRelFileName);

		if (FTemporaryEEGEANFile.FileExist()  && !FTemporaryEEGEANFile.fileIsGrowing()) {

			lstEANTOPrint = FTemporaryEEGEANFile.fileToMap();
			logger.info("Print File is present in temporary : " + FTemporaryEEGEANFile.getPathFilename());

		}


		if (lstEANTOPrint != null && lstEANTOPrint.size()>0) {

			FileUtility messageFile	= new FileUtility(	messageFileName_Update);
			FileUtility messageFileUpdateUnderProcess	= new FileUtility(	messageFileName_UpdateUnderProcess);

			int j=0;
			//Max 1 Hour
			while((messageFile.FileExist()==true || messageFileUpdateUnderProcess.FileExist()==true) && j < 360 ) {
			j+=1;
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

			int i=0;

			//max = 1 hour
			while(fpResultFileName_Update.FileExist()==false && i< 360 ) {
				i+=1;
				try {
					logger.info("Waiting for Result file : " + fpResultFileName_Update.getFileName());
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					logger.error(e.getMessage() + "..." + e.getCause());
				}

			}


			logger.info("create printer List");

			final String API_USER	= ini.get("API","API_USER");
			final String API_KEY	= ini.get("API","API_KEY");
			final String API_HOST	= ini.get("API","Host");
			final String API_PORT	= ini.get("API","Port");

			ArrayList<PrintRequest> lstRemotePrint = new ArrayList<PrintRequest>();
			java.text.SimpleDateFormat sdf3 = new java.text.SimpleDateFormat("yyMMdd_HHmmss");

			PricerPublicAPI50 pricerInterfaceR5 ;

			try {
				pricerInterfaceR5  = new R5WSAPI(API_USER,API_KEY,API_HOST,API_PORT).getPricerInterfaceR5();
				System.out.println("Pricer Version = " + pricerInterfaceR5.getSystemVersion().getVersion());

			}

			catch (Exception ex) {
				logger.fatal("unable to connect to the Pricer api with this apikey : " + API_KEY + " Please check your credentials or running service !!!");
				logger.fatal("deleting  data File : " + FtemporaryFile.getFileName()  + " From Temporary Folder !!!, you will be able to find it in Archives Folder");
				FtemporaryFile.deleteFile();
				logger.fatal("deleting  EEGEAN File : " + FTemporaryEEGEANFile.getFileName() + " From Temporary Folder !!!, you will be able to find it in Archives Folder");
				FTemporaryEEGEANFile.deleteFile();

			return;
			}



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

				try {

					item50 = pricerInterfaceR5.getItem(itemID);

					if (item50 != null) {
						lstitemproperty = item50.getItemProperties();

						for (PropertyValue propertyValue : lstitemproperty) {
							System.out.println("checking property : " + propertyValue.getValue());

							if (propertyValue.getId().getName().equalsIgnoreCase("FORMAT_LABEL")) {

								System.out.println("found default model Id from   = " + itemID + " : " + propertyValue.getValue());
								defaultModelIdFromCasino = propertyValue.getValue();

							}


						}

						// now we need to find format label name from xml config file

						defaultModelNameFromCasino = lstFormatLabels.get(defaultModelIdFromCasino);
						lstESLModels = pricerInterfaceR5.getEslModels();

						for (ESLModel ESLmodel : lstESLModels) {

							if (ESLmodel.getName().equalsIgnoreCase(defaultModelNameFromCasino)) {
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


					} else {

						logger.warn("item not exist for Batch Print : " + itemID);
					}


				}
				catch (NullPointerException npe) {

					logger.warn("Exception, unable to add product into Batch_Print , item is null for barcode : " + itemID + " , cause : " + npe.getMessage() +  " " + npe.getCause());
				}

			}


		}



			logger.info("SaveOverlayRequest lstRemotePrint, Nbre of products =  " + lstRemotePrint.size());

			//PrintBatch printBatch = new PrintBatch("REL_" + sdf3.format(new Date()), "config", lstRemotePrint);
			PrintBatch printBatch = new PrintBatch();
			printBatch.setBatchName("REL_" + sdf3.format(new Date()));
			printBatch.setRequests(lstRemotePrint);
			//printBatch.setBatchId("config");

			try {
				pricerInterfaceR5.savePrintBatch(printBatch);
				//gui.saveOverlayRequest(lstRemotePrint);
			}

			catch (Exception exp) {

				logger.fatal("Unable to create printBatch for file :" + FTemporaryEEGEANFile.getPathFilename());
				logger.fatal("cause : " + exp.getMessage() + " " + exp.getCause());
			}
			logger.info("Operation Done ");


		}


		System.out.println("delete file " + temporaryFolder + "\\" + priceFileName);

		if (FtemporaryFile.FileExist()) {
			try {
				FtemporaryFile.deleteFile();
				logger.info("file deleted : " + FtemporaryFile.getPathFilename());
			}
			catch (Exception ioex) {
				logger.error("unable to delete file : " + FtemporaryFile.getPathFilename());
			}

		}


		if (FTemporaryEEGEANFile.FileExist()) {
			try {
			FTemporaryEEGEANFile.deleteFile();
				logger.info("file deleted : " + FTemporaryEEGEANFile.getPathFilename());
			}
			catch (Exception ioex) {
				logger.error("unable to delete file : " + FTemporaryEEGEANFile.getPathFilename());
			}
		}

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
