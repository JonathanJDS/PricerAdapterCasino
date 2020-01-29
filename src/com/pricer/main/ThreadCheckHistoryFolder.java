package com.pricer.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.pricer.model.ListDataManager;
import org.apache.logging.log4j.*;
//import org.apache.log4j.Logger;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

public class ThreadCheckHistoryFolder extends Thread {

	static Logger logger =  LogManager.getLogger(ThreadCheckHistoryFolder.class);
	static Wini ini;









	private void GetIni() {
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
	
	public ThreadCheckHistoryFolder(){
		
		GetIni();

		String	priceArchiveFolder		= ini.get("Folders", "PriceArchiveFolder");
		String 	gestArchiveFolder		= ini.get("Folders", "GestArchiveFolder");
		String	cDiscountArchiveFolder	= ini.get("Folders", "CDiscountArchiveFolder");
		String	vllArchiveFolder		= ini.get("Folders", "VLLArchiveFolder");
		String 	relArchiveFolder		= ini.get("Folders", "RelArchiveFolder");


		String	priceFilterName		= ini.get("Files","PriceFileName");
		String	gestFilterName		= ini.get("Files","GestFileName");
		String  cDiscountFilterName	= ini.get("Files","CDiscountFileName");
		String  relFilterName		= ini.get("Files","RelFileName");
		String	vllFilterName		= ini.get("Files","VLLFileName");


		int	nbrOfDayTokeep		= Integer.valueOf(ini.get("Folders","NbrOfDayToKeep"));



		 List<String> lstFileNamePrice 		= listFilesFromDirectory(priceArchiveFolder + "\\",priceFilterName + "*.*" );
		 List<String> lstFileNameCDiscount	= listFilesFromDirectory(cDiscountArchiveFolder + "\\",cDiscountFilterName  + "*.*" );
		 List<String> lstFileNameGest		= listFilesFromDirectory(gestArchiveFolder + "\\",gestFilterName  + "*.*" );
		 List<String> lstFileNameVLL		= listFilesFromDirectory(vllArchiveFolder + "\\",vllFilterName  + "*.*" );
		 List<String> lstFileNameRel		= listFilesFromDirectory(relArchiveFolder + "\\",relFilterName  + "*.*" );



		PurgeHistoryFolder purgeHistory = new PurgeHistoryFolder();
		purgeHistory.purgeFilesFromDirectory(lstFileNamePrice,priceArchiveFolder + "\\",nbrOfDayTokeep);
		purgeHistory.purgeFilesFromDirectory(lstFileNameCDiscount,cDiscountArchiveFolder + "\\",nbrOfDayTokeep);
		purgeHistory.purgeFilesFromDirectory(lstFileNameGest,gestArchiveFolder + "\\",nbrOfDayTokeep);
		purgeHistory.purgeFilesFromDirectory(lstFileNameVLL,vllArchiveFolder + "\\",nbrOfDayTokeep);
		purgeHistory.purgeFilesFromDirectory(lstFileNameRel,relArchiveFolder + "\\",nbrOfDayTokeep);

	
	}








	static ArrayList<String> listFilesFromDirectory(String directoryPath,String filterName) {



		try{

			ArrayList<String> data=new ArrayList<String>();
			File directory = new File(directoryPath);
			if (!directory.exists()) {
				logger.warn("File Or Directory '" + directoryPath + "' doesn't exist !!!!");
			} else if (!directory.isDirectory()) {
				logger.warn("PATH Of  '" + directoryPath + "' is a File, not a Folder !!!!");
			} else{

				ListDataManager ldm=new ListDataManager(directoryPath, filterName.toUpperCase());
				ldm.sortFilesLeft();
				for (int i=0;i<ldm.getFilesLeftSize();i++){
					File subfile = ldm.getCurrentFile();
					String fileName= subfile.getName();
					System.out.println("!!!!!!!!!!!!!! filename = " + fileName);
					data.add(fileName);
					ldm.nextFile();
				}
			}
			return data;
		}
		catch(java.lang.Exception err){
			// vect.add(err.getMessage());

			logger.fatal("detail :" + err.getCause() + " " + err.getMessage());
			return null;
		}
	}


}
