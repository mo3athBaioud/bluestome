package com.xc.tools;

import java.util.ArrayList;
import java.util.List;

import com.ssi.common.utils.*;
public class IMEIParser {

	static String FILE_PATH = "E:\\5.SELF\\6.西安\\data\\";
	
	public static void main(String args[]){
//		List<String[]> list = getCSV("IMEI终端型号统计.csv");
		List<String[]> list = getCSV("imeishuju.csv");
		for(String[] s:list){
			for(String t:s){
				System.out.println("t:"+t);
			}
//			System.out.println("s[0]:"+s[0]);
//			System.out.println("s[1]:"+s[1]);
//			System.out.println("s[2]:"+s[2]);
			System.out.println("\r\n");
		}
		System.out.println(" >> size:"+list.size());
	}
	
	/**
	 * 返回CSV文件中的数据列表，并以数据的形式返回
	 * @param fileName
	 * str[0] = TAC
	 * str[1] = 厂商
	 * str[2] = 机型
	 * @return
	 */
	public static List<String[]> getCSV(String fileName){
		List<String[]> list = new ArrayList<String[]>();
		try{
			String tmp = IOUtil.readFile(FILE_PATH+fileName, "GBK", ".csv");
			if(null != tmp && !"".equals(tmp)){
				String[] rows = tmp.split("\r\n");
				for(String row:rows){
					String[] s = row.split(",");
					list.add(s);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
}
