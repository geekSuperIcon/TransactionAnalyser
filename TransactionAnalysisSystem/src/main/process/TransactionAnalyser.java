package main.process;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

import main.controller.TransactionAnalyserController;

public class TransactionAnalyser{

	@SuppressWarnings("rawtypes")
	public static void main(String[] args){
		Date fromDate=null;
		Date toDate=null;
		String merchant="";
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("Welcome to Transaction Analyser System ");
			System.out.println("Please enter below details: ");
			System.out.println("Enter fromDate: ");
			String fromDateInput = scanner.nextLine();
			System.out.println("Enter toDate: ");
			String toDateInput = scanner.nextLine();
			System.out.println("Enter merchant: ");
			merchant = scanner.nextLine();
			fromDate = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss").parse(fromDateInput);
			toDate =new SimpleDateFormat("dd/mm/yyyy HH:mm:ss").parse(toDateInput);  
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Please enter valid dates");
		}catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}   
		scanner.close();
		Map <Integer,Double> txMap =null;
		if(fromDate !=null && toDate !=null && merchant !=null){
			TransactionAnalyserController txController= new TransactionAnalyserController();
			txMap = txController.retrieveNumOfTransactions(fromDate, toDate, merchant);
		}

		if(txMap !=null){			
			for(Map.Entry entry:txMap.entrySet()){
				System.out.println("Number of transactions = " + entry.getKey());
				System.out.println("Average Transaction Value = " + entry.getValue());
			}
		}
	}
}
