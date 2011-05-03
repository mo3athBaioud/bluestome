package com.xc.tools;

import java.util.ArrayList;
import java.util.List;

import com.ssi.common.utils.IOUtil;

public class CmDataParser {

	static String FILE_PATH = "E:\\5.SELF\\6.西安\\data\\";
	
	public static void main(String args[]){
//		List<String[]> list = getCSV("移动数据.csv");
//		int i=0;
//		for(String[] s:list){
//			if( i == 0){
//				i++;
//				continue;
//			}
//			System.out.println("\t* s[0] = 统计日期:"+s[0]);
//			System.out.println("\t* s[1] = 用户标识:"+s[1]);
//			System.out.println("\t* s[2] = 手机号码:"+s[2]);
//			System.out.println("\t* s[3] = imei号码:"+s[3]);
//			System.out.println("\t* s[4] = 业务区编码:"+s[4]);
//			System.out.println("\t* s[5] = 通话次数:"+s[5]);
//			System.out.println("\t* s[6] = 基本计费跳:"+s[6]);
//			System.out.println("\t* s[7] = 通话时长:"+s[7]);
//			System.out.println("\t* s[8] = tac码:"+s[8]);
//			System.out.println("\r\n");
//			i++;
//		}
//		System.out.println(" >> size:"+list.size());
		
//		List<Cmdata> list = getCmDataList();
//		System.out.println(" >> "+list.size());
		
	}
	
//	public static List<Cmdata> getCmDataList(){
//		List<Cmdata> list = new ArrayList<Cmdata>();
//		Cmdata data = null;
//		try{
//			List<String[]> tl = getCSV("移动数据.csv");
//			for(String[] s:tl){
//				data = new Cmdata();
//				data.setAcode(s[4]);
//				data.setCallDuring(Integer.valueOf(s[7]));
//				data.setCallTimes(Integer.valueOf(s[5]));
//				data.setFeeTimes(Integer.valueOf(s[6]));
//				data.setImei(s[3]);
//				data.setPhoneNum(s[2]);
//				data.setTac(s[8]);
//				data.setUcode(s[1]);
//				list.add(data);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return list;
//	}
	
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
