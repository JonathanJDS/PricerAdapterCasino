package com.pricer.model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.logging.Level;

public class FileProperty {

	private File file = null;
	
    public FileProperty(String filename) {
        file = new File(filename);
    }

	public void SplitFile(int nbrOfLines,String destinationPathName){
		int numLines =0 ;
		int numLinesBreak =0 ;
		int fileNumber = 1;


		FileReader fileReader;
		try {
		fileReader = new FileReader(this.file);
		BufferedReader bufReader = new BufferedReader(fileReader);
		String line = null;
		String headerLine = null;
		StringBuffer breakLine = new StringBuffer();
		String splitFileName = null;
		File splitFile = null;
		FileWriter fileWritter = null;
		PrintWriter bufferWritter = null;
		headerLine = bufReader.readLine();
		while( (line = bufReader.readLine()) != null) {
		numLines++;
		numLinesBreak++;
		breakLine.append(line+"\n");
		if(numLinesBreak>=nbrOfLines  && line.substring(0,2).equals("06")){
		splitFileName = destinationPathName + file.getName() +  "_" + fileNumber;
		splitFile = new File(splitFileName);
		if(splitFile.exists()){
		splitFile.delete();
		splitFile.createNewFile();
		}
		fileWritter = new FileWriter(splitFile);
		bufferWritter = new PrintWriter(fileWritter);
		breakLine.deleteCharAt(breakLine.length()-1);//remove last newline
		bufferWritter.println(headerLine);
		bufferWritter.println(breakLine.toString());
		bufferWritter.close();
		fileWritter.close();
		//bufReader.close();
		//fileReader.close();

		fileNumber++;
		numLinesBreak=0;
		breakLine.setLength(0);
		}
		//System.out.println(numLines+":Line:"+lin e);
		}
		//for last iteration
		splitFileName = destinationPathName + file.getName() +  "_" + fileNumber;
		splitFile = new File(splitFileName);
		//configuration.writeLog(Level.INFO, "File Splited = " + splitFileName );

		if(splitFile.exists()){
		splitFile.delete();
		splitFile.createNewFile();
		}
		fileWritter = new FileWriter(splitFile);
		bufferWritter = new PrintWriter(fileWritter);
		breakLine.deleteCharAt(breakLine.length()-1);//remove last newline
		bufferWritter.println(headerLine);
		bufferWritter.println(breakLine.toString());
		bufferWritter.close();
		fileWritter.close();
		bufReader.close();
		fileReader.close();
		fileNumber++;
		numLinesBreak=0;

		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}







		}
	
	
}
