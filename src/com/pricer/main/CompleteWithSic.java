package com.pricer.main;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Date;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CompleteWithSic {

	
	static Logger logger =  LogManager.getLogger(CompleteWithSic.class);
	
	
	public void completeWithSic(String sourceFile, String operationType, String positionBegin, String positionEnd, String lineSize) {

		logger.info("Starting Application");	
		String FileSource = null;
		String typeOperation = null;
		int ItemPositionBegin = 0;
		int ItemPositionEnd = 0;	
		int LineSizeWanted = 0;
		
		try {		
			FileSource = sourceFile;
			typeOperation = operationType; // F = Fixed ; S = Split
			ItemPositionBegin = Integer.parseInt(positionBegin); // if type operation is Split, take only sItemPositionBegin
			ItemPositionEnd = Integer.parseInt(positionEnd);	 // use only if Type = Fixed
			LineSizeWanted = Integer.parseInt(lineSize);	 
		}

        catch (IndexOutOfBoundsException iex) {
            logger.fatal("Bad Or Missing Parameter : completeWithSic.jar \"FileSource\" \"typeOperation(F=Fixed size or S=Separator)\" \"ItemPositionBegin\" \"ItemPositionEnd\" \"LineSizeWanted\"");
            logger.fatal("Exit application");
        }
		
        System.out.println("begin Operation : " + new Date());
        
        logger.info(("fileSource = " + FileSource));
        logger.info(("typeOperation = " + typeOperation));
        logger.info(("itemPositionBegin = " + ItemPositionBegin));
        logger.info(("itemPositionEnd = " + ItemPositionEnd));
        logger.info(("Element Size = " + LineSizeWanted));
        
        System.out.println("fileSource = " + FileSource);
        System.out.println("typeOperation = " + typeOperation);
        System.out.println("itemPositionBegin = " + ItemPositionBegin);
        System.out.println("itemPositionEnd = " + ItemPositionEnd);
        
        final TreeMap<String, String> lstItemSics = new OperationOnDB().getlstItemSics();
        
        System.out.println("nbre of linked items = " + lstItemSics.size());    
        logger.info(("nbre of SIC items in dtabase = " + lstItemSics.size()));
        
        final File fFileSource = new File(FileSource);
        PrintStream datafile = null;
        try {
            datafile = new PrintStream(new BufferedOutputStream(new FileOutputStream(FileSource + ".tmp", true)));
        }
        catch (FileNotFoundException e1) {
            e1.printStackTrace();
            logger.fatal(("Unable to open the File, please check path name : " + FileSource + ".tmp"));
        }
        try {
            final BufferedReader bufferReader = new BufferedReader(new InputStreamReader(new FileInputStream(fFileSource)));
            try {
                String line;
                while ((line = bufferReader.readLine()) != null) {
                    String itemIDFromFile = null;
                    Label_0769: {
                        switch (typeOperation.toUpperCase()) {
                            case "F": {
                                itemIDFromFile = line.substring(ItemPositionBegin, ItemPositionEnd);
                                if (line.length() != LineSizeWanted) {
                                    itemIDFromFile = "error";
                                    logger.warn(("line Size (" + line.length() + ") is different as expected " + LineSizeWanted + " For this Line : " + line));
                                }
                                break Label_0769;
                            }
                            case "S": {
                                line = "CDISCOUNT;" + line;
                                line = line.replace("|", " - ");
                                itemIDFromFile = line.split(";")[ItemPositionBegin + 1];
                               //System.out.println(itemIDFromFile);
//                                if (line.split(";").length != LineSizeWanted + 1) {
//                                    itemIDFromFile = "error";
//                                  logger.warn(("the number of elements (" + line.split(";").length + ") is different as expected " + (LineSizeWanted + 1) + " For this Line : " + line));
//                                }
                                break Label_0769;
                            }
                            default:
                                break;
                        }
                        itemIDFromFile = "error";
                        logger.warn(("line rejected : " + itemIDFromFile + " doesn't exist in database for line ==> " + line));
                    }
                   // System.out.println("line = " + line);

/*                    if (!itemIDFromFile.equals("error") && lstItemSics.containsKey(itemIDFromFile)) {
                        System.out.println(("COntains itemID"));
                    //    datafile.println(line.replace(itemIDFromFile, lstItemSics..get(itemIDFromFile)));
                    	datafile.println(line);
                        datafile.flush();
                    }*/
                    if (!itemIDFromFile.equals("error") && lstItemSics.containsKey(itemIDFromFile)) {
                        //System.out.println(("COntains itemID"));
                        //    datafile.println(line.replace(itemIDFromFile, lstItemSics.get(itemIDFromFile)));
                        datafile.println(line);
                        datafile.flush();
                    }
                    line = "";
                }
                bufferReader.close();
            }
            catch (IOException e2) {
                e2.printStackTrace();
                logger.error("File Exception Please Check if file is not locked ");
            }
        }
        catch (FileNotFoundException e3) {
            e3.printStackTrace();
            logger.error("File Exception Please Check if file exist ");
        }
        datafile.close();
        logger.info(("deleting the source file : " + FileSource));
        fFileSource.delete();
        try {
            Thread.sleep(1000L);
        }
        catch (InterruptedException e4) {
            e4.printStackTrace();
        }
        logger.info("Rename Temp File to Source File ");
        final File ancien_nom = new File(FileSource + ".tmp");
        final File nouveau_nom = new File(FileSource);
        ancien_nom.renameTo(nouveau_nom);
        System.out.println("End Operation : " + new Date());
        logger.info(("###### End Operation for this file  : " + FileSource + " ######"));
    }
}
