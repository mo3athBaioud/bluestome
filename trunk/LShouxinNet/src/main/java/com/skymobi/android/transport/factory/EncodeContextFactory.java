package com.skymobi.android.transport.factory;

import java.util.HashMap;
import java.util.Map;

import com.skymobi.android.bean.tlv.encode.DefaultEncodeContextFactory;
import com.skymobi.android.bean.tlv.encode.DefaultEncoderRepository;
import com.skymobi.android.bean.tlv.encode.TLVEncoder;
import com.skymobi.android.bean.tlv.encode.encoders.BeanTLVEncoder;
import com.skymobi.android.bean.tlv.encode.encoders.BooleanTLVEncoder;
import com.skymobi.android.bean.tlv.encode.encoders.ByteArrayTLVEncoder;
import com.skymobi.android.bean.tlv.encode.encoders.ByteTLVEncoder;
import com.skymobi.android.bean.tlv.encode.encoders.DateTLVEncoder;
import com.skymobi.android.bean.tlv.encode.encoders.IntTLVEncoder;
import com.skymobi.android.bean.tlv.encode.encoders.LongTLVEncoder;
import com.skymobi.android.bean.tlv.encode.encoders.MapTLVEncoder;
import com.skymobi.android.bean.tlv.encode.encoders.ShortTLVEncoder;
import com.skymobi.android.bean.tlv.encode.encoders.StringTLVEncoder;
import com.skymobi.android.util.DefaultNumberCodecs;

/**
 * 编码上下文工厂
 * @author Bluestome.Zhang
 *
 */
public class EncodeContextFactory {
	
	private static  DefaultEncodeContextFactory instance = null;
	
	private EncodeContextFactory(){}
	
	public synchronized static DefaultEncodeContextFactory getInstance(){
		if(instance == null){
			instance = new DefaultEncodeContextFactory();
			DefaultEncoderRepository repo = new DefaultEncoderRepository();
			repo.setEncoders(getEncoders());
			instance.setEncoderRepository(repo);
			instance.setNumberCodec(DefaultNumberCodecs.getBigEndianNumberCodec());
		}
		return instance;
	}
	
	private static Map<Class<?>, TLVEncoder> getEncoders(){
		Map<Class<?>, TLVEncoder> encoders = new HashMap<Class<?>, TLVEncoder>();
		encoders.put(String.class, new StringTLVEncoder());
		encoders.put(byte.class, new ByteTLVEncoder());
		encoders.put(byte[].class, new ByteArrayTLVEncoder());
		encoders.put(Byte.class, new ByteTLVEncoder());
		encoders.put(short.class, new ShortTLVEncoder());
		encoders.put(Short.class, new ShortTLVEncoder());
		encoders.put(int.class, new IntTLVEncoder());
		encoders.put(Integer.class, new IntTLVEncoder());
		encoders.put(Object.class, new BeanTLVEncoder());
		encoders.put(long.class, new LongTLVEncoder());
		encoders.put(java.lang.Long.class, new LongTLVEncoder());
		encoders.put(java.util.Date.class, new DateTLVEncoder());
		encoders.put(boolean.class, new BooleanTLVEncoder());
		encoders.put(java.lang.Boolean.class, new BooleanTLVEncoder());
		encoders.put(java.util.Map.class, new MapTLVEncoder());
		encoders.put(java.util.HashMap.class, new MapTLVEncoder());
	
		return encoders;
	}

}
