
package android.skymobi.messenger.net.constants;

/**
 * 服务端响应码常量集合
 * 
 * @author Bluestome.Zhang
 */
public class ResponseCodeConstants {

    // 手信服务端响应码

    // 批量操作联系人响应码
    public final static int OPER_CONTACT_CODE = 0xA802;

    // 联系人列表响应码
    public final static int CONTACT_LIST_CODE = 0xA804;

    // 新联系人列表响应码
    public final static int CONTACT_LIST_CODE_2 = 0xA842;

    // 联系人版本响应码
    public final static int CONTACT_VERSION_CODE = 0xA806;

    // 推荐用户响应码
    public final static int RECOMMEND_FRIENDS_CODE = 0xA808;

    // 计算推荐用户响应码
    public final static int CALC_RECOMMEND_FRIENDS_CODE = 0xA846;

    // 添加好友响应码
    public final static int ADD_FRIENDS_CODE = 0xA810;

    // 联系人状态响应码
    public final static int CONTACT_STATUS_CODE = 0xA812;

    // 新用户在线状态响应码
    public final static int CONTACT_STATUS_CODE_2 = 0xA840;

    // 发送聊天消息响应码
    public final static int SEND_CHAT_MSG_CODE = 0xA814;

    // 发送名片消息响应码
    public final static int SEND_VCARD_MSG_CODE = 0xA816;

    // 添加黑名单响应码
    public final static int ADD_BLACK_CODE = 0xA818;

    // 删除黑名单响应码
    public final static int DEL_BLACK_CODE = 0xA820;

    // 推荐短信响应码
    public final static int RECOMMEND_MSG_CODE = 0xA822;

    // 添加反馈响应码
    public final static int ADD_FEEDBACK_CODE = 0xA824;

    // 获取指定联系人响应码
    public final static int CONTACT_SPECIFIED_CODE = 0xA828;

    // 发送邀请响应码
    public final static int SEND_INVITE_CODE = 0xA830;

    // 获取用户信息响应码
    public final static int USER_INFO_CODE = 0xA832;

    // 是否推荐响应码
    public final static int SET_RECOMMENDED_CODE = 0xA834;

    // 获取推荐值响应代码
    public final static int GET_SET_RECOMMEND_CODE = 0xA850;

    // 增量更新联系人响应码
    public final static int SHOUXIN_INCREMENT_OPER_CONTACE_CODE = 0xA836;

    // 设置用户信息响应码
    public final static int SHOUXIN_SET_USERINFO_CODE = 0xA838;

    // 获取用户黑名单列表
    public final static int SHOUXIN_GET_BLACK_LIST_CODE = 0xA848;

    // M2A通知消息响应码
    // 在线状态通知响应码
    public final static int MSG_ONLINE_NOTIFY_CODE = 0xB802;

    // 聊天消息通知响应码
    public final static int MSG_CHAT_NONTIFY_CODE = 0xB803;

    // 名片消息通知响应码
    public final static int MSG_VCARD_NOTIFY_CODE = 0xB804;

    // 系统消息通知响应码
    public final static int MSG_SYS_NOTIFY_CODE = 0xB805;

    // 推荐好友通知
    public final static int RECOMMENDS_FRIENDS_NOTIFY_CODE = 0xB806;

    // 状态更新通知响应码
    public final static int CHANGE_STATE_NOTIFY_CODE = 0xA415;

    // 营销消息响应码
    public final static int MARKETING_NOTIFY_CODE = 0xBE03;

    // 创建快聊通知响应码
    public final static int CREATE_FASTCHAT_NOTIFY_CODE = 0xB807;

    // 离开快聊通知响应码
    public final static int LEAVE_FASTCHAT_NOTIFY_CODE = 0xB808;

    // 用户在线状态响应码
    public final static int ONLINE_STATUS_CODE = 0xA852;

    // 获取推荐短信类型
    public final static int GET_RECOMMEND_MSG_TYPE = 0xA854;

    // 根据短信类型获取短信列表
    public final static int GET_RECOMMEND_MSG_BY_TYPE_NEW = 0xA856;

    // 获取附近的人响应码
    public final static int GET_NEAR_BY_FRIEND_CODE = 0xA860;

    // 根据用户名查找联系人
    public final static int GET_USER_INFO_BY_USERNAME = 0xA858;

    // 获取服务端的配置列表响应码
    public final static int GET_CONFIGURATION_CODE = 0xA862;

    // 获取可恢复的联系人响应码
    public final static int GET_RESTORABLE_CONTACTS_CODE = 0xA864;

    // 批量恢复的联系人响应码
    public final static int RESTORABLE_CONTACTS_CODE = 0xA866;

    // 申请快聊响应码
    public final static int APPLY_FASTCHAT_CODE = 0xA868;

    // 离开快聊响应码
    public final static int LEAVE_FASTCHAT_CODE = 0xA870;

    // PPA消息
    // 注册代码响应码
    public final static int PPA_REGISTER_CODE = 0xC202;

    // 根据用户名获取绑定的手机号码
    public final static int PPA_GET_MOBILE_BY_USERNAME = 0xC222;

    // 登录代码响应码
    public final static int PPA_LOGIN_CODE = 0xC20A;

    // 注销用户响应码
    public final static int PPA_LOGOUT_CODE = 0xC20E;

    // 修改密码响应码
    public final static int PPA_MODIFY_PASSWORD_CODE = 0xC210;

    // 设置用户信息代码响应码
    public final static int PPA_SET_USERINFO_CODE = 0xC220;

    // 获取用户信息响应码
    public final static int PPA_GET_USERINFO_CODE = 0xC226;

    // 找回密码响应码
    public final static int PPA_FIND_PASSWORD_CODE = 0xC22A;

    // 绑定或者确认绑定状态响应码
    public final static int PPA_CHECK_BIND_CODE = 0xC234;

    // 检查手机绑定状态
    public final static int PPA_CHECK_BIND_STATUS_CODE = 0xC238;

    // 根据SKYID获取用户名
    public final static int PPA_GET_USERNAME_BY_SKYID_CODE = 0xC21A;

    /**
     * LCS
     */
    public final static int LCS_LOGSTATISTICS_CODE = 0xA112;
    public final static int LCS_COMPLEXMESSAGE_CODE = 0xA412;

}
