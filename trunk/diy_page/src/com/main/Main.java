package com.main;

import com.chinamilitary.htmlparser.ChinaTUKUParser;
import com.chinamilitary.htmlparser.MilitaryParser;
import com.chinamilitary.htmlparser.PCPOPHtmlParser;
import com.thread.RequestRecordThread;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//启动入库线程
		Thread th = new Thread(new RequestRecordThread(),"recordupdate");
		th.start();
		
		try{
			
		//PCPOP
		PCPOPHtmlParser.processC();
		
		//ChinaTUKUParser
		ChinaTUKUParser.processC();
		
		//MilitaryParser
		MilitaryParser.processC();
		
		
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
