package com.skymobi.android.transport.factory;

import java.util.Vector;

import com.skymobi.android.bean.bytebean.ByteBean;
import com.skymobi.android.bean.bytebean.codec.AnyCodec;
import com.skymobi.android.bean.bytebean.codec.ArrayCodec;
import com.skymobi.android.bean.bytebean.codec.ByteArrayCodec;
import com.skymobi.android.bean.bytebean.codec.ByteCodec;
import com.skymobi.android.bean.bytebean.codec.IntCodec;
import com.skymobi.android.bean.bytebean.codec.LongCodec;
import com.skymobi.android.bean.bytebean.codec.ShortCodec;
import com.skymobi.android.bean.bytebean.codec.StringCodec;
import com.skymobi.android.bean.bytebean.core.ByteFieldCodec;
import com.skymobi.android.bean.bytebean.core.DefaultCodecProvider;
import com.skymobi.android.bean.bytebean.core.DefaultDecContextFactory;
import com.skymobi.android.bean.esb.codec.AnyCodecForCustomCodec;
import com.skymobi.android.bean.esb.codec.ArrayListCodec;
import com.skymobi.android.bean.esb.codec.EsbBeanCodecSupportTLV;
import com.skymobi.android.bean.esb.codec.EsbBeanCodecSupportTLV2;
import com.skymobi.android.bean.esb.codec.StringCodecForLengthInBytes;
import com.skymobi.android.bean.xip.core.XipBean;
import com.skymobi.android.util.DefaultNumberCodecs;

public class AccessDecContextFactory {
	
	private static  DefaultDecContextFactory instance = null;
	
	private AccessDecContextFactory(){}
	
	public synchronized static DefaultDecContextFactory getInstance(){
		if(instance == null){
			instance = new DefaultDecContextFactory();
			DefaultCodecProvider provider = new DefaultCodecProvider();
			provider.setCodecs(getDecoders());
			instance.setCodecProvider(provider);
			instance.setNumberCodec(DefaultNumberCodecs.getBigEndianNumberCodec());
		}
		return instance;
	}
	
	
	private static Vector<ByteFieldCodec> getDecoders(){
		Vector<ByteFieldCodec> decoders = new Vector<ByteFieldCodec>();
		decoders.add(new EsbBeanCodecSupportTLV());
		decoders.add(new StringCodecForLengthInBytes());
		decoders.add(new AnyCodecForCustomCodec());
		decoders.add(new ArrayListCodec());
		decoders.add(new LongCodec());
		decoders.add(new IntCodec());
		decoders.add(new ShortCodec());
		decoders.add(new ByteCodec());
		decoders.add(new ByteArrayCodec());
		
		decoders.add(new AnyCodec(ByteBean.class));
		return decoders;
	}
	
	/**
	private static Map<Class<?>, TLVDecoder> getDecoders(){
		Map<Class<?>, TLVDecoder> decoders = new HashMap<Class<?>, TLVDecoder>();
		decoders.put(String.class, new StringTLVDecoder());
		decoders.put(Integer.class, new IntTLVDecoder());
		decoders.put(int.class, new IntTLVDecoder());
		decoders.put(Byte.class, new ByteTLVDecoder());
		decoders.put(byte.class, new ByteTLVDecoder());
		decoders.put(byte[].class, new ByteArrayTLVDecoder());
		decoders.put(Short.class, new ShortTLVDecoder());
		decoders.put(short.class, new ShortTLVDecoder());
		decoders.put(Object.class, new BeanTLVDecoder());
		return decoders;
	}
	*/
}
