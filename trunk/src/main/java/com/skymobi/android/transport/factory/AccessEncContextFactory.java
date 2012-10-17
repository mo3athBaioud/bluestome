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
import com.skymobi.android.bean.bytebean.core.DefaultEncContextFactory;
import com.skymobi.android.bean.esb.codec.AnyCodecForCustomCodec;
import com.skymobi.android.bean.esb.codec.ArrayListCodec;
import com.skymobi.android.bean.esb.codec.EsbBeanCodecSupportTLV;
import com.skymobi.android.bean.esb.codec.EsbBeanCodecSupportTLV2;
import com.skymobi.android.bean.esb.codec.StringCodecForLengthInBytes;
import com.skymobi.android.bean.xip.core.XipBean;
import com.skymobi.android.util.DefaultNumberCodecs;

public class AccessEncContextFactory {
	
	private static  DefaultEncContextFactory instance = null;
	
	private AccessEncContextFactory(){}
	
	public synchronized static DefaultEncContextFactory getInstance(){
		if(instance == null){
			instance = new DefaultEncContextFactory();
			DefaultCodecProvider provider = new DefaultCodecProvider();
			provider.setCodecs(getEncoders());
			instance.setCodecProvider(provider);
			instance.setNumberCodec(DefaultNumberCodecs.getBigEndianNumberCodec());
		}
		return instance;
	}
	
	private static Vector<ByteFieldCodec> getEncoders(){
		Vector<ByteFieldCodec> encoders = new Vector<ByteFieldCodec>();
		encoders.add(new EsbBeanCodecSupportTLV());
		encoders.add(new StringCodecForLengthInBytes());
		encoders.add(new AnyCodecForCustomCodec());
		encoders.add(new ArrayListCodec());
		encoders.add(new LongCodec());
		encoders.add(new IntCodec());
		encoders.add(new ShortCodec());
		encoders.add(new ByteCodec());
		encoders.add(new ByteArrayCodec());
		
		encoders.add(new AnyCodec(ByteBean.class));
		return encoders;
	}
	
}
