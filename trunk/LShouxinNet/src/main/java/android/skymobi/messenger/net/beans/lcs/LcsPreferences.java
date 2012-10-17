
package android.skymobi.messenger.net.beans.lcs;

/**
 * @ClassName: LcsPreferences
 * @Description: 数据埋点统计的配置文件
 * @author Michael.Pan
 * @date 2012-7-26 上午10:33:28
 */
public class LcsPreferences {

    /**
     * Data Code定义
     */
    public static final int MSGCODE_DATA_STATISTIC = 1; // 普通统计相关
    public static final int MSGCODE_DATA_LBSSTATIC = 2; // LBS统计相关
    /**
     * MSG_DEST (1:单聊短信, 2:单聊网络消息, 3:群聊纯短信, 4:群聊纯网络消息, 5:群聊混合消息)
     */
    public static final byte MSG_DEST_DEFAULT = 0; // 打招呼或者LBS点击次数
    public static final byte MSG_DEST_SINGLE_SMS = 1; //
    public static final byte MSG_DEST_SINGLE_NET = 2; //
    public static final byte MSG_DEST_MASS_SMS = 3;
    public static final byte MSG_DEST_MASS_NET = 4;
    public static final byte MSG_DEST_MASS_MULTI = 5;

    /**
     * MSG_TYPE (1:文本消息；2:名片；3:语音；4: buddy: 打招呼次数; 5: 使用LBS功能次数(点击附近的人次数); 6:
     * 打招呼对象个数(对几个陌生人打了招呼))
     */
    public static final byte MSG_TYPE_TEXT = 1;
    public static final byte MSG_TYEP_CARD = 2;
    public static final byte MSG_TYPE_VOICE = 3;
    public static final byte MSG_TYPE_CLICK_BUDDY = 4;
    public static final byte MSG_TYPE_CLICK_LBS = 5;
    public static final byte MSG_TYPE_BUDDY_PEOPLE = 6;

    // 文件名
    private static final String MESSENGER_LCS_PREFERENCES = "messenger_lcs_preferences";

    // 消息类型
    private static final String SINGLE_SMS_COUNT = "single_sms_count"; // 传统单聊短信
    private static final String MASS_SMS_COUNT = "mass_sms_count"; // 群发短信
    private static final String MASS_NET_COUNT = "mass_net_count"; // 群发网络消息
    private static final String MASS_MULTI_COUNT = "mass_multi_count"; // 群发混合消息（短信+网络消息）
    private static final String NET_TEXT_COUNT = "net_text_count"; // 网络文本消息
    private static final String NET_CARD_COUNT = "net_card_count"; // 网络名片消息
    private static final String NET_VOICE_COUNT = "net_voice_count"; // 网络语音消息
    private static final String CLICK_BUDDY_COUNT = "click_buddy_count"; // 点击打招呼次数
    private static final String CLICK_LBS_COUNT = "click_lbs_count"; // 点击lbs次数
    private static final String BUDDY_PEOPLE_COUNT = "buddy_people_count"; // 打招呼对象的个数

}
