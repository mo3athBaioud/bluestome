package com.skymobi.android.transport.factory;

import java.util.HashMap;
import java.util.Map;

import com.skymobi.android.bean.tlv.decode.DefaultDecodeContextFactory;
import com.skymobi.android.bean.tlv.decode.DefaultDecoderRepository;
import com.skymobi.android.bean.tlv.decode.TLVDecoder;
import com.skymobi.android.bean.tlv.decode.decoders.BeanTLVDecoder;
import com.skymobi.android.bean.tlv.decode.decoders.BooleanTLVDecoder;
import com.skymobi.android.bean.tlv.decode.decoders.ByteArrayTLVDecoder;
import com.skymobi.android.bean.tlv.decode.decoders.ByteTLVDecoder;
import com.skymobi.android.bean.tlv.decode.decoders.DateTLVDecoder;
import com.skymobi.android.bean.tlv.decode.decoders.IntTLVDecoder;
import com.skymobi.android.bean.tlv.decode.decoders.LongTLVDecoder;
import com.skymobi.android.bean.tlv.decode.decoders.MapTLVDecoder;
import com.skymobi.android.bean.tlv.decode.decoders.ShortTLVDecoder;
import com.skymobi.android.bean.tlv.decode.decoders.StringTLVDecoder;
import com.skymobi.android.util.DefaultNumberCodecs;

/**
 * 解码上下文工厂
 * @author Bluestome.Zhang
 *
 */
public class DecodeContextFactory {
	
	private static  DefaultDecodeContextFactory instance = null;
	
	private DecodeContextFactory(){}
	
	public synchronized static DefaultDecodeContextFactory getInstance(){
		if(instance == null){
			instance = new DefaultDecodeContextFactory();
			DefaultDecoderRepository repo = new DefaultDecoderRepository();
			repo.setDecoders(getDecoders());
			instance.setDecoderRepository(repo);
			instance.setNumberCodec(DefaultNumberCodecs.getBigEndianNumberCodec());
		}
		return instance;
	}
	
	private static Map<Class<?>, TLVDecoder> getDecoders(){
		Map<Class<?>, TLVDecoder> decoders = new HashMap<Class<?>, TLVDecoder>();
		decoders.put(String.class, new StringTLVDecoder());
		decoders.put(byte.class, new ByteTLVDecoder());
		decoders.put(byte[].class, new ByteArrayTLVDecoder());
		decoders.put(Byte.class, new ByteTLVDecoder());
		decoders.put(short.class, new ShortTLVDecoder());
		decoders.put(Short.class, new ShortTLVDecoder());
		decoders.put(int.class, new IntTLVDecoder());
		decoders.put(Integer.class, new IntTLVDecoder());
		decoders.put(long.class, new LongTLVDecoder());
		decoders.put(java.lang.Long.class, new LongTLVDecoder());
		decoders.put(java.util.Date.class, new DateTLVDecoder());
		decoders.put(Object.class, new BeanTLVDecoder());
		decoders.put(boolean.class, new BooleanTLVDecoder());
		decoders.put(java.lang.Boolean.class, new BooleanTLVDecoder());
		decoders.put(java.util.Map.class, new MapTLVDecoder());
		decoders.put(java.util.HashMap.class, new MapTLVDecoder());
		return decoders;
	}

}
