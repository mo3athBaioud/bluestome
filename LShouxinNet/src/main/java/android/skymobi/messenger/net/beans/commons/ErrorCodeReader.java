package android.skymobi.messenger.net.beans.commons;

import com.skymobi.android.commons.logger.Logger;
import com.skymobi.android.commons.logger.LoggerFactory;

import org.apache.commons.lang.StringUtils;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class ErrorCodeReader {
	private static final Logger logger = LoggerFactory.getLogger(ErrorCodeReader.class);
	static Properties p = new Properties();
	static {
		try {
			InputStream ins = ErrorCodeReader.class.getClassLoader()
					.getResourceAsStream("errorcode.properties");
			p.load(ins);
		} catch (Exception e) {
			logger.info("Read ConfigFile fail");
		}
	}
	
	public static String getValue(String code){
		String value = ErrorCodeReader.p.getProperty(code);
		if(StringUtils.isNotBlank(value)){
			try {
				value =  new String(ErrorCodeReader.p.getProperty(code).getBytes("ISO8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return value;
	}
	
}
