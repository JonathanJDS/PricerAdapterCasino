package com.pricer.main;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

import com.pricer.model.UtilityClass;

import it.sauronsoftware.cron4j.Predictor;
import it.sauronsoftware.cron4j.Scheduler;

public class Start {
	
	static Logger logger = Logger.getLogger(Start.class);
	static Wini ini;
	static UtilityClass utility = new UtilityClass();
	
	public static void InitializeIni() {
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


	public static void main(String[] args) {
		
		InitializeIni();
		
		/* Using Cron Value for checking archives files to delete according to nbre of fdays to keep from preference.ini*/
		String cronValue = ini.get("Folders", "CheckArchiveCronValue");
		Scheduler scheduler = new Scheduler();
		it.sauronsoftware.cron4j.Predictor predictor = new Predictor(cronValue);
		logger.info("Next execution date for Checkin old files in Archives Folder : " + predictor.nextMatchingDate());

		try {

			scheduler.schedule(cronValue, new Runnable() {

				public void run() {

					ThreadCheckHistoryFolder threadCheckHistoryFolder = new ThreadCheckHistoryFolder();
					threadCheckHistoryFolder.setPriority(1);
					threadCheckHistoryFolder.start();

				}
		});
	}

		catch (IndexOutOfBoundsException iofbe) {
			// iofbe.printStackTrace();
			logger.error("Unable to execute scheduler with value " + cronValue + " Please check your parameters...");

		}

		scheduler.start();



//		ThreadCheckPriceFiles getPriceFiles = new ThreadCheckPriceFiles();
//		getPriceFiles.setPriority(1);
//		getPriceFiles.start();


		ThreadCheckGestFiles getGestFiles = new ThreadCheckGestFiles();
		getGestFiles.setPriority(1);
		getGestFiles.start();
		
		ThreadCheckCDiscount getCDiscountFiles = new ThreadCheckCDiscount();
		getCDiscountFiles.setPriority(1);
		getCDiscountFiles.start();
		
		


		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

}
