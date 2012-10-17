package android.skymobi.messenger.net.utils;

import android.skymobi.messenger.net.beans.commons.VCardContent;
import android.skymobi.messenger.net.client.bean.NetVCardNotify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ParserUtils
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-3-15 下午02:11:49
 */
public class ParserUtils {
    
    //名片格式模版
    public static String VCARD_TEMPLATE = "<ssvd dat={content}/>{contactname}";
    
    /**
     * 解析服务端发送的名片协议
     * @param vCardContent
     * @return
     */
    public static Map decoderVCard(String vCardContent){
        Map map = new HashMap();
        boolean b = vCardContent.startsWith("<ssvd ");
        if(b){
            //内容开始位置
            int symbolIdx = vCardContent.indexOf("=");
            if(symbolIdx != -1){
                //内容结束位置
                int endIdx = vCardContent.lastIndexOf("/");
                if(endIdx != -1){
                    String content = vCardContent.substring(symbolIdx+1,endIdx);
                    
                    //详情开始位置
                    int istart = content.indexOf("[");
                    //详情结束位置
                    int iend = content.lastIndexOf("]");
                    if(istart != -1 && iend != -1){ //约束当号码没有详情时，也必须填写该内容
                        //获取手机号码部分内容
                        String phones = content.substring(0,istart);
                        //拆分手机号码 (*)
                        char[] chars = phones.toCharArray();
                        //获取分隔的数量 (*)
                        int c = SysUtils.getCharsNum(chars, ',');
                        //组装分割字符串数组 (*)
                        String[] phoness = assemblyStringArray(phones, c);
                        String contactName = phoness[0];
                        map.put("CONTACT_NAME", contactName);
                        //获取详情部分内容
                        String info = content.substring(istart,iend+1);
                        //拆分详情部分内容
                        String[] infos = info.split("\\],");
                        //2012-03-16 新增号码段和详情段数量不一致的情况下，不使用手机号码段的数据下标来获取详情数据。
                        List<VCardContent> list = new ArrayList<VCardContent>();
                        VCardContent vcard = null;
                        for(int i=1;i<phoness.length;i++){
                            vcard = new VCardContent();
                            String tmp = phoness[i].trim();
                            vcard.setPhone(tmp);
                            list.add(vcard);
                        }
                        //增加对数据下标越界异常的捕获
                        try{
                            vcard = new VCardContent();
                            String tinfo = infos[infos.length-1].replace("[", "").replace("]", "").trim();
                            if(null != tinfo && !tinfo.equals("")){
                                String[] subInfo = tinfo.split(",");
                                //对详情内容进行解析，通过判断长度来设置对应的详情值
                                switch(subInfo.length){
                                    case 1:
                                        vcard.setSkyid(subInfo[0]);
                                        break;
                                    case 2:
                                        vcard.setSkyid(subInfo[0]);
                                        vcard.setNickname(subInfo[1]);
                                        break;
                                    case 3:
                                        vcard.setSkyid(subInfo[0]);
                                        vcard.setNickname(subInfo[1]);
                                        vcard.setUsertype(subInfo[2]);
                                        break;
                                    case 4:
                                        vcard.setSkyid(subInfo[0]);
                                        vcard.setNickname(subInfo[1]);
                                        vcard.setUsertype(subInfo[2]);
                                        vcard.setHeadicon(subInfo[3]);
                                        break;
                                    default:
                                        break;
                                }
                            }
                            list.add(vcard);
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                        map.put(NetVCardNotify.CONTACT_DETAIL_LIST, list);
                    }else{
                        //获取手机号码部分内容
                        String phones = content.substring(0,content.length());
                        //拆分手机号码 (*)
                        char[] chars = phones.toCharArray();
                        //获取分隔的数量 (*)
                        int c = SysUtils.getCharsNum(chars, ',');
                        //组装分割字符串数组 (*)
                        String[] phoness = assemblyStringArray(phones, c);
                        String contactName = phoness[0];
                        map.put(NetVCardNotify.CONTACT_NAME, contactName);
                        
                        List<VCardContent> list = new ArrayList<VCardContent>();
                        VCardContent vcard = null;
                        for(int i=1;i<phoness.length;i++){
                            vcard = new VCardContent();
                            String tmp = phoness[i].trim();
                            vcard.setPhone(tmp);
                            list.add(vcard);
                        }
                        //TODO 添加skyid的[]空内容
                        vcard = new VCardContent();
                        list.add(vcard);
                        map.put(NetVCardNotify.CONTACT_DETAIL_LIST, list);
                    }
                }
            }
        }
        return map;
    }
    
    /**
     * 对编码内容进行解析
     * @param map
     * @return
     */
    public static String encodeVCard(Map map){
        StringBuffer sb = new StringBuffer();
        //新增联系人默认名称
        String contactName = "好友";
        if(null != map && map.size() == 2){
            contactName = (String)map.get(NetVCardNotify.CONTACT_NAME);
            sb.append(contactName);
            //详情内容列表数量固定为6，其中5个为号码，1个为[]详情
            List<VCardContent> list = (List<VCardContent>)map.get(NetVCardNotify.CONTACT_DETAIL_LIST);
            if(null != list && list.size() > 0){
                sb.append(",");
                int c = list.size();
                
                if(c > 0 && c == 6){
                    //构造电话号码
                    String phones = "";
                    //总列表中减去一个详情，剩余就是号码段内容
                    for(int i=0;i<c-1;i++){
                        VCardContent vc = list.get(i);
                        if(null != vc.getPhone() && !vc.getPhone().equals("")){
                            phones += vc.getPhone()+",";
                        }else{
                            phones += ",";
                        }
                    }
                    
                    //构造详情
                    StringBuffer sbd = new StringBuffer();
                    sbd.append(phones);
                    VCardContent vc = list.get(c-1);
                    String detail = "[";
                    
                    //获取SKYID
                    if(null != vc.getSkyid() && !vc.getSkyid().equals("")){
                        detail += vc.getSkyid()+",";
                    }else{
                        detail += ",";
                    }
                    
                    //获取昵称
                    if(null != vc.getNickname() && !vc.getNickname().equals("")){
                        detail += vc.getNickname()+",";
                    }else{
                        detail += ",";
                    }
                    
                    //获取用户类型
                    if(null != vc.getUsertype() && !vc.getUsertype().equals("")){
                        detail += vc.getUsertype() + ",";
                    }else{
                        detail += ",";
                    }
                    
                    //获取头像,在最后一个字段后跟上逗号(,)
                    if(null != vc.getHeadicon() && !vc.getHeadicon().equals("")){
                        detail += vc.getHeadicon()+",";
                    }else{
                        detail += ",";
                    }
                    //在详情结束符后面跟上逗号(,)
                    detail += "],";
                    sbd.append(detail); 
                    sb.append(sbd.toString());
                }else{
                    //TODO 非正常状态下的编码方法
                    if(c < 6){
                        //构造电话号码
                        String phones = "";
                        //总列表中减去一个详情，剩余就是号码段内容
                        for(int i=0;i<c;i++){
                            VCardContent vc = list.get(i);
                            if(null != vc.getPhone() && !vc.getPhone().equals("")){
                                phones += vc.getPhone()+",";
                            }else{
                                phones += ",";
                            }
                        }
                        
                        for(int i=0;i<5-c;i++){
                            phones += ",";
                        }
                        
                        //构造空详情
                        String detail = "[,,,,],";
                        sb.append(detail);
                    }else{
                        //构造电话号码
                        String phones = "";
                        //总列表中减去一个详情，剩余就是号码段内容
                        for(int i=0;i<5;i++){
                            VCardContent vc = list.get(i);
                            if(null != vc.getPhone() && !vc.getPhone().equals("")){
                                phones += vc.getPhone()+",";
                            }else{
                                phones += ",";
                            }
                        }
                        
                        //构造详情
                        StringBuffer sbd = new StringBuffer();
                        sbd.append(phones);
                        VCardContent vc = list.get(5);
                        String detail = "[";
                        
                        //获取SKYID
                        if(null != vc.getSkyid() && !vc.getSkyid().equals("")){
                            detail += vc.getSkyid()+",";
                        }else{
                            detail += ",";
                        }
                        
                        //获取昵称
                        if(null != vc.getNickname() && !vc.getNickname().equals("")){
                            detail += vc.getNickname()+",";
                        }else{
                            detail += ",";
                        }
                        
                        //获取用户类型
                        if(null != vc.getUsertype() && !vc.getUsertype().equals("")){
                            detail += vc.getUsertype() + ",";
                        }else{
                            detail += ",";
                        }
                        
                        //获取头像,在最后一个字段后跟上逗号(,)
                        if(null != vc.getHeadicon() && !vc.getHeadicon().equals("")){
                            detail += vc.getHeadicon()+",";
                        }else{
                            detail += ",";
                        }
                        //在详情结束符后面跟上逗号(,)
                        detail += "],";
                        sbd.append(detail); 
                        sb.append(sbd.toString());
                    }
                }
            }else{
                sb.append(",,,,,,[,,,,],");
            }
        }
        //在模版最后跟上联系人名称
        String format = VCARD_TEMPLATE.replace("{content}", sb.toString()).replace("{contactname}", contactName);
        return format;
    }

    /**
     * 组装字符串
     * @param str
     * @param c
     * @return
     */
    public static String[] assemblyStringArray(String str,int c){
        String[] strs = new String[c];
        char[] chs = str.toCharArray();
        int start = 0;
        int offset = 0;
        for(int i=0;i<chs.length;i++){
            if(chs[i] == ','){
                String tmp = str.substring(start,i);
                strs[offset] = tmp;
                offset++;
                start = i + 1;
            }
        }
        return strs;
    }
    
    public static void main(String[] args){
//        String detail = "<ssvd dat=张晓,15158180876,15158180877,15158180878,15158180879,15158180890,[1,abc,0,123],[2,abc,0,234],[],[3,abc,0,345],[]/>";
//        String detail = "<ssvd dat=张晓,,15158180877,15158180878,,15158180890,[,,,],[2,acd,0,345],[3,abc,0,345],[,,,],[5,efg,0,567]/>";
//        String detail = "<ssvd dat=李四,15158180876,,,,15158180890,[405211060,玩家227249179,0,256,],[,,,,],[,,,,],[,,,,],[405211061,玩家221546326,0,256,],/>张晓";
    	String detail = "<ssvd dat=流行雨322,,,,,,[405193171,bluestome332,1,,],/>流行雨322";
//        String detail = "<ssvd dat=张晓,15158180876,15158180877,15158180878,15158180879,15158180890,[405210963,mp2023424242,1,0,],/>张晓";
//        String detail = "<ssvd dat=微信昵称改过了,,,,,,[405193442,,1,7,],/>";
        Map tmp = decoderVCard(detail);
        String format = encodeVCard(tmp);
        System.out.println(format);
    }
}
