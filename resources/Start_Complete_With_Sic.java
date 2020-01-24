import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.log4j.Logger;

public class Start {

	
	final static Logger logger=Logger.getLogger(Start.class);
	
	
	public static void main(String[] args) {

		logger.info("Starting Application");	
		String FileSource = null;
		String typeOperation = null;
		int ItemPositionBegin = 0;
		int ItemPositionEnd = 0;	
		String itemIDFromFile;
		int LineSizeWanted = 0;
		
try {		
	FileSource = args[0];
	typeOperation = args[1]; // F = Fixed ; S = Split
	ItemPositionBegin = Integer.parseInt(args[2]); // if type operation is Split, take only sItemPositionBegin
	ItemPositionEnd = Integer.parseInt(args[3]);	 // use only if Type = Fixed
	LineSizeWanted = Integer.parseInt(args[4]);	 
}

catch (IndexOutOfBoundsException iex) {
	
logger.fatal("Bad Or Missing Parameter : completeWithSic.jar \"FileSource\" \"typeOperation(F=Fixed size or S=Separator)\" \"ItemPositionBegin\" \"ItemPositionEnd\" \"LineSizeWanted\"");
logger.fatal("Exit application");	
System.exit(0);
}

	System.out.println("begin Operation : " + new Date());
	logger.info("fileSource = " + FileSource);
	logger.info("typeOperation = " + typeOperation);
	logger.info("itemPositionBegin = " + ItemPositionBegin);
	logger.info("itemPositionEnd = " + ItemPositionEnd);
	logger.info("Element Size = " + LineSizeWanted);
	
	System.out.println("fileSource = " + FileSource);
	System.out.println("typeOperation = " + typeOperation);
	System.out.println("itemPositionBegin = " + ItemPositionBegin);
	System.out.println("itemPositionEnd = " + ItemPositionEnd);
		
		

	TreeMap<String,String> lstItemSics = new OperationOnDB().getlstItemSics();
	
/*
	for(Entry<String, String> entry : lstItemSics.entrySet()) {
		  String key = entry.getKey();
		  String value = entry.getValue();

		  System.out.println("SIC = " + key + " => " + "EAN = " + value);
		}	
	
*/
	System.out.println("nbre of linked items = " + lstItemSics.size());
	logger.info("nbre of SIC items in dtabase = " + lstItemSics.size());
		
	
	System.out.println("copy current file to history folder");
	
	File fFileSource=new File(FileSource);
	File fFileDestination = new  File(fFileSource.getParentFile() + "/history/" + fFileSource.getName());
	File historyDirectory = new File(fFileSource.getParentFile() + "/history/");
    if (!historyDirectory.exists()) {
        if (historyDirectory.mkdir()) {
            System.out.println("Directory /history/ is created!");
        } else {
            System.out.println("Failed to create directory!");
        }
    }
	
	
	
	
	System.out.println("copy ok from " + fFileSource.getPath() + " to " + fFileDestination.getPath());
	
	
	try {
		copyFileUsingChannel(fFileSource,fFileDestination);
	} catch (IOException e2) {

		
		System.out.println("unable to copy file source to history folder, make sure that the folder is created or file source/destination is not locked...");
		e2.printStackTrace();
	}
	
	
	/* copy file source to history folder */
	
	
	
	
	
	PrintStream datafile = null;
	try {
		datafile		=	new PrintStream(new BufferedOutputStream(new FileOutputStream(FileSource + ".tmp",true)));
	} catch (FileNotFoundException e1) {

		e1.printStackTrace();
		logger.fatal("Unable to open the File, please check path name : " + FileSource + ".tmp");
	}
	
	try {
		BufferedReader bufferReader = new BufferedReader(  new InputStreamReader( new FileInputStream(fFileSource)));
		
		String line;
		
		try {
			while ((line = bufferReader.readLine()) != null) {
		
				
				//System.out.println("line = " + line);
				
		switch (typeOperation.toUpperCase()) {
		
		case "F" :
			
			//System.out.println("fixed length ...");
			itemIDFromFile = line.substring(ItemPositionBegin,ItemPositionEnd);
			if (line.length()!=LineSizeWanted) {
				itemIDFromFile = "error";
				
				logger.warn("line Size (" + line.length() + ") is different as expected " + LineSizeWanted  + " For this Line : "  + line);
				
			}
			
			break;
		
		
		
		case "S" :
			
			line="CDISCOUNT"+ ";" + line;
			itemIDFromFile = line.split(";")[ItemPositionBegin + 1];	
			
			int count = line.length() - line.replace(";", "").length();
			if (count!=(LineSizeWanted + 1)) {
				itemIDFromFile = "error";
				
				logger.warn("the number of elements (" + count + ") is different as expected " + (LineSizeWanted + 1)  + " For this Line : "  + line );
				
			}
			
			
			
			//System.out.println("itemid from file =  " + itemIDFromFile);
			break;
			
		default : itemIDFromFile = "error";	
		
		//System.out.println("line rejected : " + itemIDFromFile + " doesn't exist in database for line ==> " + line);	
		logger.warn("line rejected : " + itemIDFromFile + " doesn't exist in database for line ==> " + line);
		// write log 
		break;
		
		}
				
			
			
		if (itemIDFromFile.equals("error")==false && lstItemSics.containsKey(itemIDFromFile)==true) {
			
			
			//System.out.println("found item for : " + itemIDFromFile );
			datafile.println(line.replace(itemIDFromFile, lstItemSics.get(itemIDFromFile)));	
			datafile.flush();	
			
		}
		
		
		
				
		line = "";
		
			
				
				
				
				}
			
			
			bufferReader.close();	
			
			
			
		} catch (IOException e) {

			e.printStackTrace();
			logger.error("File Exception Please Check if file is not locked ");
		}
				
		
		
		
		
		
	} catch (FileNotFoundException e) {

		e.printStackTrace();
		logger.error("File Exception Please Check if file exist ");
	}
	
	
	
	
		
	datafile.close();	
	

logger.info("deleting the source file : " + FileSource);	
fFileSource.delete();

try {
	Thread.sleep(1000);
} catch (InterruptedException e) {

	e.printStackTrace();
}



logger.info("Rename Temp File to Source File ");
File ancien_nom = new File(FileSource + ".tmp");
File nouveau_nom = new File(FileSource); 
ancien_nom.renameTo(nouveau_nom);

System.out.println("End Operation : " + new Date());
logger.info("###### End Operation for this file  : " + FileSource + " ######");

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static void copyFileUsingChannel(File source, File dest) throws IOException {
	    FileChannel sourceChannel = null;
	    FileChannel destChannel = null;
	    try {
	        sourceChannel = new FileInputStream(source).getChannel();
	        destChannel = new FileOutputStream(dest).getChannel();
	        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
	       }finally{
	           sourceChannel.close();
	           destChannel.close();
	       }
	}
	
	

}
