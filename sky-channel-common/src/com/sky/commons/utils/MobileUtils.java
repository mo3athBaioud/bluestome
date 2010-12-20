package com.sky.commons.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MobileUtils {

	// ºÅÂë¶Î
	private static Map<String, String> numberSegmentMap = new HashMap<String, String>(
			3);
	// initial numberSegmentMap
	//1,chinamobile,2=chinaunicom,3,chinatelecom
	static {
		numberSegmentMap.put(
				"134-135-136-137-138-139-147-150-151-152-157-158-159-187-188",
				"1");
		numberSegmentMap.put("130-131-132-155-156-185-186", "2");
		numberSegmentMap.put("133-153-180-189", "3");
	}

	/**
	 * get provider code from mobile number
	 * @param mobile
	 * @return
	 */
	public static String getProvider(String mobile) {
		String provider = "1";
		if (mobile != null&&mobile.length()>=3) {
			Set<String> keySet = numberSegmentMap.keySet();
			for (String key : keySet) {
				if(key.indexOf(mobile.substring(0,3))>=0){
					provider = numberSegmentMap.get(key);
					break;
				}
			}

		}
		return provider;
	}
	
	public static boolean isValidPhonenum(String phonenum){
		boolean isValid = true;
		if(phonenum==null||phonenum.length()!=11){
			isValid = false;
		}else if(!phonenum.startsWith("1")){
			isValid = false;
		}
		return isValid;
	}
	
	
	public static void main(String args[]){
		String provider = getProvider("158");
		System.out.println("provider="+provider);
		provider = getProvider("qqq");
		System.out.println("provider="+provider);
		provider = getProvider("130");
		System.out.println("provider="+provider);
		provider = getProvider("189");
		System.out.println("provider="+provider);
	}


	public void setNumberSegmentMap(Map<String, String> numberSegmentMap) {
		MobileUtils.numberSegmentMap = numberSegmentMap;
	}
}
