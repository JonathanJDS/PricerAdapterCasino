package com.pricer.model;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;


public class UtilityClass {
	
	private static Wini ini;
	static Logger logger = Logger.getLogger(UtilityClass.class);
	

	
	public void MoveFile(String source, String destination) {
		
		
		FileProperty fpSourceFile = new FileProperty(source);
	  
		if (fpSourceFile.FileExist()) {
		
		if (!fpSourceFile.moveToFolder(destination)) {
		  
		  logger.warn("Unable to copy file to destination : " + destination);
		  logger.warn("waiting for end of threatment...");
	  }
		
		
		}
		
		
		else {
			logger.debug("source file not present : " + source);
			logger.debug("nothing to do ....");
			
		}
		
	}
	
	public  void ZipFile(String sourceFolder,String sourceFile, String temporaryFolder, String stockFileName, String archiveFolder) {

		String dateOfFile ;			
		String completeFileName = sourceFolder + "\\" + sourceFile;
				
		
		FileProperty fpCurrentFile = new FileProperty(completeFileName);
		FileProperty fpTempFile = new FileProperty(temporaryFolder + "\\" + stockFileName);
		
if (fpTempFile.FileExist() == false) {
		if (fpCurrentFile.FileExist()) {

			if (fpCurrentFile.fileIsGrowing() == false) {

				dateOfFile = new SimpleDateFormat("yyyyMMdd_Hmmss").format(new Date());
				//fpCurrentFile.zipFile(archiveFolder + "\\" + stockFileName + "_" + dateOfFile + ".zip");
				fpCurrentFile.zipFile(archiveFolder, sourceFile + "_" + dateOfFile + ".zip");

				

			}
		}
}
	
	
	else if (fpCurrentFile.FileExist() && fpTempFile.FileExist()){
		logger.warn("unable to zip file : " +  completeFileName);
		logger.warn(stockFileName + " is already present into temporary folder, waiting for end of threatment...");

	}
}
	
	  public ArrayList<String> listFilesFromDirectory(String directoryPath,String filterName) {
	    	 
    	  System.out.println("filtername = " + filterName);

	    	try{ 

	    	 ArrayList<String> data=new ArrayList<String>();
	    	  
	    	 File directory = new File(directoryPath);
	    			
	    	 
	    	 
	    	 
	    			if (!directory.exists()) {
	    				logger.warn("File Or Directory '" + directoryPath + "' doesn't exist !!!!");
	    				
	    			} else if (!directory.isDirectory()) {
	    				logger.warn("PATH Of  '" + directoryPath + "' is a File, not a Folder !!!!");
	    			} else{   
	    	     
	    	ListDataManager ldm=new ListDataManager(directoryPath, filterName);
	    	ldm.sortFilesLeft();



	    	for (int i=0;i<ldm.getFilesLeftSize();i++){

	    	File subfile = ldm.getCurrentFile();
	    					
	    	


	    	String fileName= subfile.getName();    
	    	   
	    	    data.add(fileName);
	       	System.out.println("found file : " + fileName);
	    	   
	    	    
	    	    ldm.nextFile();    


	    	}
	    	                }
	    	            

	    	 return data;  
	    	 
	    	 }
	    	 catch(java.lang.Exception err){
	    	// vect.add(err.getMessage());
	    	
	    		 logger.warn(err.getMessage() + " , detail :" + err.getCause());
	    	  return null;
	    	 }      
	    	}
	
	
	
	

	public Wini getIni() {
		return ini;
	}

	public void setIni(Wini ini) {
		UtilityClass.ini = ini;
	}
	
	
}
