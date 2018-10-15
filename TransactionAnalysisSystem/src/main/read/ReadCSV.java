package main.read;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import main.model.TransactionRecord;

public class ReadCSV{
	/**
	 * This method is to read CSV file and retrieve transaction records 
	 * @param fileName
	 */
	 public List<TransactionRecord> retrieveProcessedTransactions(String fileName) {
	    	Path pathToFile = Paths.get(fileName);
	    	List<TransactionRecord> taRecordList = new ArrayList<>();
	        // create an instance of BufferedReader
	        // using try with resource, Java 7 feature to close resources
	        try (BufferedReader br = Files.newBufferedReader(pathToFile,
	                StandardCharsets.US_ASCII)) {

	            // read the first line from the text file
	            String line = br.readLine();

	            // loop until all lines are read
	            while (line != null) {

	                // use string.split to load a string array with the values from
	                // each line of
	                // the file, using a comma as the delimiter
	                String[] attributes = line.split(",",-1);
	                String id = attributes[0].trim();
	                String inputDate = attributes[1].trim();
	                DateFormat df = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
	                Date date=null;
					try {
						date = df.parse(inputDate);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					
	    	        Double amount = Double.parseDouble(attributes[2]);
	    	        String merchant = attributes[3].trim(); 
	    	        String type = attributes[4].trim();
	    	        
	    	        String relatedTransaction="";
	    	        if(attributes[5] !=null || !attributes[5].equals("")){
	    	        relatedTransaction = attributes[5].trim();
	    	        }
	    	        TransactionRecord txRecord = new TransactionRecord();
	    	        txRecord.setID(id);
	    	        if(date !=null){
	    	        txRecord.setDate(date);
	    	        }
	    	        else{
	    	        	System.out.println("Date format is incorrect");
	    	        }
	    	        txRecord.setAmount(amount);
	    	        txRecord.setMerchant(merchant);
	    	        txRecord.setType(type);
	    	        txRecord.setRelatedTransaction(relatedTransaction);
	    	        
	    	        if(txRecord != null){
	                taRecordList.add(txRecord);
	    	        }

	                // read next line before looping
	                // if end of file reached, line would be null
	                line = br.readLine();
	            }

	        }catch (NoSuchFileException ex){
	        	System.out.println("Input file cannot be accessed");
	        }catch (IOException ioe) {
	            ioe.printStackTrace();
	        }

	        return taRecordList;
	    }

	}
