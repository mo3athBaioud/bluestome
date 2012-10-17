
package android.skymobi.messenger.net.client;

import android.skymobi.messenger.net.beans.SxGetNearbyReq;
import android.skymobi.messenger.net.beans.commons.ConfigInfo;
import android.skymobi.messenger.net.client.bean.NetAddFriendResponse;
import android.skymobi.messenger.net.client.bean.NetApplyFastChatResponse;
import android.skymobi.messenger.net.client.bean.NetBindResponse;
import android.skymobi.messenger.net.client.bean.NetBlackResponse;
import android.skymobi.messenger.net.client.bean.NetCalcFriendsResponse;
import android.skymobi.messenger.net.client.bean.NetChatRequest;
import android.skymobi.messenger.net.client.bean.NetChatResponse;
import android.skymobi.messenger.net.client.bean.NetContacts;
import android.skymobi.messenger.net.client.bean.NetContactsVersionResponse;
import android.skymobi.messenger.net.client.bean.NetForgetPwdResponse;
import android.skymobi.messenger.net.client.bean.NetGetBlakListResponse;
import android.skymobi.messenger.net.client.bean.NetGetConfigurationResponse;
import android.skymobi.messenger.net.client.bean.NetGetContactsList2Response;
import android.skymobi.messenger.net.client.bean.NetGetContactsListResponse;
import android.skymobi.messenger.net.client.bean.NetGetContactsStatus2Response;
import android.skymobi.messenger.net.client.bean.NetGetContactsStatusResponse;
import android.skymobi.messenger.net.client.bean.NetGetFriendstResponse;
import android.skymobi.messenger.net.client.bean.NetGetNearByFriendResponse;
import android.skymobi.messenger.net.client.bean.NetGetRecommendedMsgNewResponse;
import android.skymobi.messenger.net.client.bean.NetGetRecommendedMsgResponse;
import android.skymobi.messenger.net.client.bean.NetGetRecommendedMsgTypeResponse;
import android.skymobi.messenger.net.client.bean.NetGetSetRecommendResponse;
import android.skymobi.messenger.net.client.bean.NetGetUserInfoByUserNameResponse;
import android.skymobi.messenger.net.client.bean.NetGetUsernameResponse;
import android.skymobi.messenger.net.client.bean.NetLoginResponse;
import android.skymobi.messenger.net.client.bean.NetOnlineStatusResponse;
import android.skymobi.messenger.net.client.bean.NetOperateContactsResponse;
import android.skymobi.messenger.net.client.bean.NetRegResponse;
import android.skymobi.messenger.net.client.bean.NetResponse;
import android.skymobi.messenger.net.client.bean.NetRestorableContactsResponse;
import android.skymobi.messenger.net.client.bean.NetRestoreContactsResp;
import android.skymobi.messenger.net.client.bean.NetSpecifiedContactsStatusResponse;
import android.skymobi.messenger.net.client.bean.NetSyncContactsResponse;
import android.skymobi.messenger.net.client.bean.NetUserInfo;
import android.skymobi.messenger.net.client.bean.NetUserInfoResponse;
import android.skymobi.messenger.net.client.bean.NetmodifyPwdResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 针对客户端service的客户端接口类
 * 
 * @author Bluestome.Zhang
 */
public interface INetClient extends INetBiz, IFSBiz, IImageBiz, ISupBiz, ILcs {

    /**
     * 获取绑定状态
     * 
     * @return
     */
    NetBindResponse getBind();

    /**
     * 发起bind请求
     * 
     * @return 绑定状态响应对象
     */
    NetBindResponse bind();

    /**
     * 根据SKYID获取用户名
     * 
     * @param skyid
     * @return
     */
    NetGetUsernameResponse getUsernameBySkyid(int skyid);

    /**
     * 带用户名和密码的注册方法
     * 
     * @param username 用户名
     * @param pwd 密码
     * @return
     */
    NetRegResponse register(String username, String pwd);

    /**
     * 快速注册接口
     * 
     * @return 返回注册响应对象
     */
    NetRegResponse qregister();

    /**
     * 注册前判读绑定关系
     * 
     * @return 返回注册响应对象
     */
    NetRegResponse beforeCheckReg();

    /**
     * 登录业务接口
     * 
     * @param username 用户名
     * @param password 密码
     * @return
     */
    NetLoginResponse login(String username, String password);

    /**
     * 登录业务接口2
     * 
     * @param username 用户名
     * @param password 编码过的密码
     * @return
     */
    NetLoginResponse login(String username, byte[] password);

    /**
     * 注销登录
     * 
     * @param token
     * @return
     */
    NetResponse logout(String token);

    /**
     * 设置用户个性化昵称
     * 
     * @param skyid 斯凯ID
     * @param token 登录返回的TOKEN
     * @param nickName 个性昵称
     * @return
     */
    NetResponse setUserNickName(int skyid, String token, String nickName);

    /**
     * 设置用户信息
     * 
     * @param skyid 斯凯ID
     * @param token 登录返回的TOKEN
     * @param nui 用户信息
     * @return
     */
    NetResponse setUserInfo(String token, NetUserInfo nui);

    /**
     * 获取用户自身的详细信息
     * 
     * @param token 登录返回的TOKEN
     * @return
     */
    NetUserInfoResponse getUserInfo(String token);

    /**
     * 发送聊天消息，文本/语音
     * 
     * @param chat 聊天消息对象
     * @return
     */
    NetChatResponse sendChatMsg(NetChatRequest chat);

    /**
     * 修改密码
     * 
     * @param token 登录是服务端返回的令牌
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @return
     */
    NetmodifyPwdResponse modifyPwd(String token, String oldPwd, String newPwd);

    /**
     * 忘记密码
     * 
     * @param username 用户帐号或者手机号码
     * @param newPwd 新密码
     * @return
     */
    NetForgetPwdResponse forgetPwd(String username, String newPwd);

    /**
     * 获取指定联系人状态，在3个参数都存在的情况下，服务端优先考虑第3个参数。
     * 
     * @param contactSkyids 联系人和SKYID字符串
     *            ,例如：3,301064589|4,301064588，其中3和4是联系人ID，
     *            301064589和301064588是skyid。
     * @param contactPhones
     *            联系人ID和手机号码,例如：1,13325718080|2,13325719090，其中1和2是联系人ID，
     *            13325718080和13325719090是手机号码
     * @param contanctsId 联系人ID
     * @return
     */
    NetSpecifiedContactsStatusResponse getSpecifiedContactsStatus(String contactSkyids,
            String contactPhones, int contactsId);

    /**
     * 获取联系人版本
     * 
     * @return
     */
    NetContactsVersionResponse getContactsVersion();

    /**
     * 获取好友的详细信息
     * 
     * @param skyid
     * @return
     */
    NetUserInfoResponse getBuddyUserInfo(int skyid);

    /**
     * 设置是否可推荐
     * 
     * @param isRecommend false：否 | true：是
     * @return
     */
    NetResponse setRecommend(boolean isRecommend, boolean hideLBS);

    /**
     * 设置是否推荐
     * 
     * @return
     */
    NetGetSetRecommendResponse getSetRecommendValue();

    /**
     * 用户反馈接口
     * 
     * @param nickName
     * @param content
     * @return
     */
    NetResponse feedBack(String nickName, String content);

    /**
     * 添加黑名单
     * 
     * @param contactId
     * @param destSkyid
     * @return
     */
    NetBlackResponse addBlackList(int contactId, int destSkyid);

    /**
     * 从黑名单列表中删除
     * 
     * @param contactId
     * @param destSkyid
     * @return
     */
    NetBlackResponse delBlackList(int contactId, int destSkyid);

    /**
     * 获取推荐好友列表
     * 
     * @param start
     * @param pageSize
     * @return
     */
    NetGetFriendstResponse getFriendsList(int start, int pageSize, long updateTime);

    /**
     * 计算推荐联系人
     * 
     * @return NetCalcFriendsResponse
     */
    NetCalcFriendsResponse calcFriends(String token);

    /**
     * 获取指定联系人与登录用户的推荐关系
     * 
     * @param destSkyid
     * @param token
     * @return
     */
    NetCalcFriendsResponse getRecommendRelation(int destSkyid, String token);

    /**
     * 批量操作联系人
     * 
     * @param ContactsList
     * @return
     */
    NetOperateContactsResponse operateContacts(ArrayList<NetContacts> ContactsList);

    /**
     * 增量同步联系人
     * 
     * @param updateTime
     * @param start
     * @param pageSize
     * @return
     */
    NetSyncContactsResponse syncContacts(long updateTime, int start, int pageSize);

    /**
     * 添加联系人
     * 
     * @param destSkyid
     * @param contacType
     * @return
     */
    NetAddFriendResponse addFriend(int destSkyid, byte contacType);

    /**
     * 获取推荐短信
     * 
     * @param msgType
     * @param start
     * @param pageSize
     * @return
     */
    NetGetRecommendedMsgResponse getRecommendedMsg(int msgType, int start, int pageSize);

    /**
     * 获取推荐短信类型
     * 
     * @param updateTime 服务端短信类型版本
     * @return
     */
    NetGetRecommendedMsgTypeResponse getRecommendMsgType(long updateTime);

    /**
     * 根据短信类别获取短信列表
     * 
     * @param msgType 短信类别
     * @param updateTime 短信类别时间戳
     * @param start 起始页
     * @param pageSize 每页数量
     * @param capacity 最大数量
     * @return
     */
    NetGetRecommendedMsgNewResponse getRecommendMsgNew(int msgType, long updateTime, int start,
            int pageSize, int capacity);

    /**
     * 获取联系人列表
     * 
     * @param update
     * @param start
     * @param pageSize
     * @return
     */
    NetGetContactsListResponse getContactsList(long updateTime, int start, int pageSize);

    /**
     * 获取联系人列表
     * 
     * @param start 起始页数
     * @param pageSize 每页数量
     * @param fetch 0：全部，默认；1：指定人
     * @param contactId 联系人ID
     * @return
     */
    NetGetContactsList2Response getContactsList(int start, int pageSize, int fetchFlag,
            int contactId);

    /**
     * 获取联系人状态
     * 
     * @param start 起始页数
     * @param pageSize 每页数量
     * @return
     */
    NetGetContactsStatusResponse getContactsStatusList(int start, int pageSize);

    /**
     * 新获取联系人状态
     * 
     * @param start 起始页数
     * @param pageSize 每页数量
     * @param fetch 0：全部，默认；1：指定人
     * @param contactId 联系人ID
     * @return
     */
    NetGetContactsStatus2Response getContactsStatusList(int start, int pageSize, int fetch,
            int contactId);

    /**
     * 获取黑名单列表接口
     * 
     * @param start 起始页数
     * @param pageSize 每页数量
     * @param fetchFlag 过滤类型[0：全部；1：陌生人；1：联系人]
     * @return
     */
    NetGetBlakListResponse getBlackList(int start, int pageSize, int fetchFlag);

    /**
     * 获取用户在线状态
     * 
     * @param destSkyids 目标用户的skid，多个skyid之间用,(英语键盘的逗号)分割
     * @return
     */
    NetOnlineStatusResponse getOnlineStatus(String destSkyids);

    /**
     * 发送名片
     * 
     * @param destSkyids
     * @param nickname
     * @param vContent 该MAP中包含2部分内容： 1.KEY为"CONTACT_NAME"的联系人姓名,字符串值
     *            2.KEY为"CONTACT_LIST_DETAIL"
     *            的联系人号码详情列表，该列表中的是以对象(VCardContent)方式保存的
     *            ，也就是ArrayList<VCardContent>形式的列表。
     * @return
     */
    NetChatResponse sendVCard(String destSkyids, String nickname, Map vContent);

    /**
     * 离线消息确认推送接口
     */
    void offLineMsgPushConfirm(int appid);

    /**
     * 获取附近的人业务接口
     * 
     * @param request
     * @return
     */
    NetGetNearByFriendResponse getNearByFriends(SxGetNearbyReq request);

    /**
     * 根据用户名查找联系人
     * 
     * @param userName 用户名
     * @return
     */
    NetGetUserInfoByUserNameResponse getUserInfoByUserName(String userName);

    /**
     * 获取配置信息
     * 
     * @param configType
     * @param update
     * @return
     */
    NetGetConfigurationResponse getConfiguration(ArrayList<ConfigInfo> configInfos);

    /**
     * 查看可恢复联系人接口
     * 
     * @param start
     * @param pageSize
     * @return
     */
    NetRestorableContactsResponse getRestorableConacts(int start, int pageSize);

    /**
     * 批量恢复联系人
     * 
     * @param ids
     * @return
     */
    NetRestoreContactsResp restoreConacts(List<Integer> ids);

    /**
     * 申请快聊
     * 
     * @param usex 性别
     * @return
     */
    NetApplyFastChatResponse applyFastChat(String usex);

    /**
     * 离开快聊
     * 
     * @param destSkyid
     * @return
     */
    NetResponse leaveFastChat(int destSkyid);
}
