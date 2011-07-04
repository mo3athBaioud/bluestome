package com.xc.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ssi.common.utils.IOUtil;

public class CvsFileParser {

	static String FILE_PATH = "D:\\numberingplans\\";
	
	public static void main(String args[]){
		
		List<String[]> list = getCSV("employer1_without_imei.csv");
		HashMap<String,Object> map = new HashMap<String,Object>();
		for(String[] s:list){
			map.put(s[0].trim(), s);
//			System.out.println();
		}
		System.out.println(" >> 记录.size:"+list.size());
		System.out.println(" >> 不重复的号码数量.size:"+map.size());
	}
	
	/**
	 * 返回CSV文件中的数据列表，并以数据的形式返回
	 * @param fileName
	 * s[0] = 统计日期 实际数据中不需要
	 * s[1] = 用户标识
	 * s[2] = 手机号码
	 * s[3] = imei号码
	 * s[4] = 业务区编码
	 * s[5] = 通话次数
	 * s[6] = 基本计费跳
	 * s[7] = 通话时长
	 * s[8] = tac码
	 * @return
	 */
	public static List<String[]> getCSV(String fileName){
		List<String[]> list = new ArrayList<String[]>();
		System.gc();
		try{
			Thread.sleep(1000);
			int u = 100002;
			int i = 0;
			String tmp = IOUtil.readFile(FILE_PATH+fileName, "GBK", ".csv");
			if(null != tmp && !"".equals(tmp)){
				String[] rows = tmp.split("\r\n");
				int count = rows.length;
				for(String row:rows){
					if(i < u ){
						String[] s = row.replace("\"", "").trim().split(",");
						list.add(s);
						i++;
					}else{
						break;
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 返回CSV文件中的数据列表，并以数据的形式返回
	 * @param fileName
	 * s[0] = 统计日期 实际数据中不需要
	 * s[1] = 用户标识
	 * s[2] = 手机号码
	 * s[3] = imei号码
	 * s[4] = 业务区编码
	 * s[5] = 通话次数
	 * s[6] = 基本计费跳
	 * s[7] = 通话时长
	 * s[8] = tac码
	 * @return
	 */
	public static List<String[]> getCSVAbsPath(String fileName){
		List<String[]> list = new ArrayList<String[]>();
		System.gc();
		try{
			Thread.sleep(1000);
			int u = 100002;
			int i = 0;
			String tmp = IOUtil.readFile(fileName, "GBK", ".csv");
			if(null != tmp && !"".equals(tmp)){
				String[] rows = tmp.split("\r\n");
				int count = rows.length;
				for(String row:rows){
					if(i < u ){
						String[] s = row.replace("\"", "").trim().split(",");
						list.add(s);
						i++;
					}else{
						break;
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
}
