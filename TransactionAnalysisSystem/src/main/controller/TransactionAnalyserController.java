package main.controller;

import java.nio.file.NoSuchFileException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import main.read.ReadCSV;
import main.model.TransactionRecord;

/**
 * @Author Neha Modi
 * @Email  modineha257@gmail.com
 * @Date   15 Oct 2018
 */
public class TransactionAnalyserController{
	/**
	 * This method is to retrieve transactions based on input parameters 
	 * @param fromDate
	 * @param toDate
	 * @param merchant
	 */
	public Map <Integer,Double> retrieveNumOfTransactions(Date fromDate, Date toDate, String merchant){
		int count=0;
		double totalTransactionValue=0.0;
		double avgTxValue=0.0;

		Map <Integer,Double> result = new HashMap<Integer,Double>(); 
		ReadCSV readCSV= new ReadCSV();
		List<TransactionRecord> txRecordList= readCSV.retrieveProcessedTransactions("C:\\TransactionAnalysisSystem\\src\\main\\input\\inputFile.csv");
		
		List revTxList = new ArrayList();
		if(txRecordList != null && !txRecordList.isEmpty()){
			for(TransactionRecord txList : txRecordList){
				if(txList.getRelatedTransaction()!=null && !txList.getRelatedTransaction().equals("")){
					revTxList.add(txList.getRelatedTransaction());
				}
			}
		}
		if(txRecordList != null && !txRecordList.isEmpty()){
			for(TransactionRecord tx : txRecordList){
				//DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
				Date txDate = tx.getDate();
				//dateFormat.format(txDate);
				if(txDate.after(fromDate)  && txDate.before(toDate) && tx.getMerchant().equalsIgnoreCase(merchant)){
					if(revTxList !=null && !revTxList.contains(tx.getID())){
						count++;
						totalTransactionValue = totalTransactionValue+tx.getAmount();
					}

				}

			}
		}
		avgTxValue = totalTransactionValue/count;
		result.put(count, avgTxValue);

		return result;
	}
}