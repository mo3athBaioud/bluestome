package test.android.skymobi.messenger.net;

import android.skymobi.messenger.net.beans.sup.SupRequest;
import android.skymobi.messenger.net.beans.sup.SupResponse;

import com.skymobi.android.bean.tlv.decode.decoders.BeanTLVDecoder;
import com.skymobi.android.bean.tlv.encode.TLVEncodeContext;
import com.skymobi.android.bean.tlv.encode.encoders.BeanTLVEncoder;
import com.skymobi.android.util.ByteUtils;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;


/**
 * @ClassName: SupBizTest
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-21 下午03:29:23
 */
public class SupBizTest {

    /**
     * 编码方法测试
     */
    @Test
    public void encoder(){
        SupRequest request = new SupRequest();
        request.setReqType(1);
        BeanTLVEncoder beanEncoder = new BeanTLVEncoder();
        TLVEncodeContext encode = beanEncoder.getEncodeContextFactory()
                .createEncodeContext(request.getClass(), null);
        byte[] body = ByteUtils.union(beanEncoder.encode(request, encode));
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost postMethod = new HttpPost("http://172.16.3.35:6011/shouxinUpdate");
        postMethod.setHeader("Content-Type", "application/x-tar");
        
        HttpEntity entity=new ByteArrayEntity(body);
        postMethod.setEntity(entity);
        
        HttpResponse resp;
        try {
            resp = httpClient.execute(postMethod);
            if(null != resp){
                StatusLine statusLine = resp.getStatusLine();
                if(statusLine.getStatusCode() == 200 ){
                    byte[] result = IOUtils.toByteArray(resp.getEntity().getContent());
                    BeanTLVDecoder beanDecoder = new BeanTLVDecoder();
                    Object obj  = beanDecoder.decode(result.length, result,
                            beanDecoder.getDecodeContextFactory()
                                    .createDecodeContext(SupResponse.class, null));
                    if(null != obj){
                        SupResponse subResp = (SupResponse)obj;
                        System.out.println(" > response_code:"+subResp.getResponseCode());
                        System.out.println(" > response_file_len:"+subResp.getFilelen());
                    }
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    @Test
    public void printMD5(){
        String[] hexs = {
                "02d011733bfbffa98cd1e80e30571a2e",
                "79e6149df95a8e28b87265077a3f87d4",
                "5c7634e401769a9d6bdb53b755e287ac"
        };
        for(String hexz:hexs){
            byte[] tmp = hexz.getBytes();
            String hex = ByteUtils.bytesAsTLVHexString(tmp, tmp.length);
            System.out.println(hex);
        }
    }
    

}
