
package android.skymobi.messenger.net.utils;

import org.apache.commons.lang.ArrayUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SysUtils {
    public static int COLOR_CODE_RED = Rgb2Pixel565(255, 0, 0);
    public static int COLOR_CODE_GREEN = Rgb2Pixel565(0, 64, 0);
    public static int COLOR_CODE_YELLOW = Rgb2Pixel565(255, 255, 0);
    private static final char[] DIGITS = {
        '0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

    public static int Rgb2Pixel565(int r, int g, int b) {
        return ((((r) & 0xf8) << 8) | (((g) & 0xfc) << 3) | (((b) & 0xf8) >> 3));
    }

    public static String utf8ToTerm(String txt) {
        try {
            return new String(txt.getBytes("UTF-8"), "UTF-16BE");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return txt;
    }

    public static String term2Utf8(String txt) {
        try {
            return new String(txt.getBytes("UTF-16BE"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return txt;
    }

    public static final String DEFAULT_CHARSET = "UTF-16BE";

    public static int length(String str) {
        return length(str, DEFAULT_CHARSET);
    }

    public static int length(String str, String charset) {
        if (str == null)
            return 0;
        try {
            return str.getBytes(charset).length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public final static Map<String, String> emotion = new HashMap<String, String>();
    static {
        emotion.put("/wx", "[微笑]");
        emotion.put("/gg", "[尴尬]");
        emotion.put("/dh", "[大笑]");
        emotion.put("/ku", "[装酷]");
        emotion.put("/hx", "[坏笑]");
        emotion.put("/dk", "[大哭]");
        emotion.put("/jy", "[惊讶]");
        emotion.put("/tu", "[呕吐]");
        emotion.put("/fn", "[愤怒]");
        emotion.put("/wq", "[委屈]");
        emotion.put("/lh", "[害羞]");
        emotion.put("/hl", "[寒]");
        emotion.put("/yw", "[疑惑]");
        emotion.put("/nl", "[努力]");
        emotion.put("/se", "[色色]");
        emotion.put("/ty", "[晕倒]");
        emotion.put("/zz", "[zZZ]");
        emotion.put("/zk", "[抓狂]");
        emotion.put("/bb", "[拜拜]");
        emotion.put("/tp", "[调皮]");
    }
    private static java.util.regex.Pattern p_emotion = java.util.regex.Pattern.compile("/[a-z]{2}");

    /**
     * text需要转换返回转后的内容,否则返回NULL
     * 
     * @param text
     * @return
     */
    public static String transtosimple(String text) {

        boolean isNeed = false;
        java.util.regex.Matcher m_emotion = p_emotion.matcher(text);
        while (m_emotion.find()) {
            if (emotion.containsKey(m_emotion.group())) {
                isNeed = true;
                text = text.replaceFirst(m_emotion.group(), emotion.get(m_emotion.group()));
            }
        }
        return isNeed ? text : null;
    }

    public static byte[] pwdEncrypt(String pwd) {
        if (pwd == null || pwd.length() == 0)
            return null;
        try {
            return ArrayUtils.addAll(new byte[] {
                    0x00
            }, pwd.getBytes("UTF-16BE"));
        } catch (Exception e) {
        }
        return null;
    }

    // SUP接口中使用的MD5编码
    public static byte[] md5Encrypt(String md5t) throws StringIndexOutOfBoundsException{
        byte[] md5 = new byte[16];
        for (int idx = 0; idx < md5.length; idx++) {
            md5[idx] =
                    (byte) Short.parseShort(md5t.substring(idx
                            * 2, idx * 2 + 2), 16);
        }
        return md5;
    }

    /**
     * @param md5
     * @return
     */
    public static String byteArray2Md5(byte[] md5) {
        char[] charArray = encodeHex(md5);
        return new String(charArray);
    }

    /**
     * list转成string的方法
     * 
     * @param list
     * @return
     */
    public static String list2String(ArrayList<String> list) {
        String st = "";
        for (int i = 0; i < list.size(); i++) {
            String tmp = list.get(i);
            st += tmp;
            if (i < list.size() - 1) {
                st += ",";
            }
        }
        return st;
    }

    private static char[] encodeHex(byte[] data) {

        int l = data.length;

        char[] out = new char[l << 1];

        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS[0x0F & data[i]];
        }

        return out;
    }

    /**
     * 获取字符数组中出现sch的频率
     * @param chars
     * @param sch
     * @return
     */
    public static int getCharsNum(char[] chars,char sch){
        int c = 0;
        for(char ch:chars){
            if(ch == sch){
                c++;
            }
        }
        return c;
    }
    
}
