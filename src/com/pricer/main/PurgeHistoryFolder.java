package com.pricer.main;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import org.apache.logging.log4j.*;
import com.pricer.model.FileUtility;


public class PurgeHistoryFolder {
static Logger logger =  LogManager.getLogger(PurgeHistoryFolder.class);

 public void purgeFilesFromDirectory(List<String> lstFileName,String directoryPath, int NbrOfDayToKeep) {

	logger.info("Purging Archives Folder :" + directoryPath + "*.zip");
	com.pricer.model.FileUtility fileToDelete=null;
	Calendar calendar=Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd H:mm:ss");
 	calendar.add(Calendar.DAY_OF_MONTH,- (NbrOfDayToKeep-1));
	String filterDay=sdf.format(calendar.getTime());
	 
	for (String fileName : lstFileName) {

		try	{
		logger.info("check file :" + fileName);
	  	File directory = new File(directoryPath);
			
			if (!directory.exists()) {
				logger.warn("File or directory doesn't exist : " + directory);
							} else if (!directory.isDirectory()) {
				logger.warn("Source is not a directory : " + directory);
				
			}
			else {

					String fileNametoCheck=directoryPath + fileName;
					FileUtility fichier=new FileUtility(fileNametoCheck);
					//logger.info("compare date file :" + fichier.getFormatedDate() + " with " + filterDay);
					//logger.info("difference day = :" + filterDay.compareTo(fichier.getFormatedDate()));

							if (filterDay.compareTo(fichier.getFormatedDate())>0){
							//logger.info("date du fichier " + fichier.getFormatedDate().toString());
							//logger.info("date à comparer : " + filterDay.toString());
							//logger.info("fichier.getFormatedDate().compareTo(filterDay)>0   ?  :" + fichier.getFormatedDate().compareTo(filterDay));
							//logger.info("le fichier :" + fileNametoCheck + " est en date du jours ");
	   						fileToDelete=new FileUtility(directoryPath + fileName);
	   						logger.info("deleting History file : " + fileToDelete.getPathFilename());
	  						 fileToDelete.deleteFile();
							}
			}
		}
	 	catch(java.lang.Exception err)	{
		logger.error("error in java Class ==> purgeHistoryFolder when trying to delete file" + fileToDelete.getFileName());
	    
		 	 }

	}
	
 }

}
