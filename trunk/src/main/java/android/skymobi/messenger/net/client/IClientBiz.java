
package android.skymobi.messenger.net.client;

import android.skymobi.messenger.net.beans.commons.Contacts;
import android.skymobi.messenger.net.beans.commons.ResponseBean;
import android.skymobi.messenger.net.beans.commons.ResponseBean.BindStatus;
import android.skymobi.messenger.net.beans.commons.ResponseBean.CheckBindStatus;
import android.skymobi.messenger.net.beans.commons.ResponseBean.LoginResponse;
import android.skymobi.messenger.net.beans.commons.ResponseBean.RegisterResponse;
import android.skymobi.messenger.net.beans.ppa.UserInfo;

import java.util.ArrayList;

/**
 * 针对客户端service的业务类
 * 
 * @author Bluestome.Zhang
 */
public interface IClientBiz {

    /**
     * 检查绑定状态
     * 
     * @return
     */
    CheckBindStatus checkBindStatus2();

    /**
     * 获取绑定状态
     * 
     * @param request
     * @return 绑定状态响应对象
     */
    BindStatus bind2();

    /**
     * 绑定状态 无参数，是由于终端会发送UA给ACCESS，ESB会从头中拿到终端属性 该接口前提为需要登录
     * 
     * @return JudgeMobileStatusResponse
     */
    int bind();

    /**
     * 快速注册接口2
     * 
     * @return 返回注册响应对象
     */
    RegisterResponse qregister2();

    /**
     * 快速注册业务类接口
     * 
     * @param nickname 用户昵称
     * @return 结果对象
     */
    int qregister();

    /**
     * 登录业务接口2
     * 
     * @param username
     * @param password
     * @return
     */
    LoginResponse login2(String username, String password);

    /**
     * 登录业务类接口
     * 
     * @param username 用户名
     * @param password 密码，加密过的
     * @return
     */
    int login(String username, String password);

    /**
     * 注销登录
     * 
     * @param token
     * @return
     */
    int logout(String token);

    /**
     * 修改密码
     * 
     * @param token
     * @param oldPwd
     * @param newPwd
     * @return
     */
    int modifyPwd(String token, String oldPwd, String newPwd);

    /**
     * 忘记密码
     * 
     * @param username 用户帐号或者手机号码
     * @param newPwd 新密码
     * @return
     */
    int forgetPwd(String username, String newPwd);

    /**
     * 设置用户个性化昵称
     * 
     * @param skyid 斯凯ID
     * @param token 登录返回的TOKEN
     * @param nickName 个性昵称
     * @return
     */
    int setUserNickName(int skyid, String token, String nickName);
    ResponseBean setUserNickName2(int skyid, String token, String nickName);
    
    /**
     * 设置用户信息,完整
     * @param skyid
     * @param token
     * @param userInfo
     */
    void setUserInfo(int skyid,String token,UserInfo userInfo);

    /**
     * 通过PPA获取用户信息
     * 
     * @param skyid 斯凯ID
     * @param token 登录后的TOKEN
     * @param loginTag 登录标识 默认使用 0 【0 获取用户信息操作|1 带token的登陆跳转】
     * @return
     */
    int getUserInfo(int skyid, String token, int loginTag);

    /**
     * 查询当前手机是否绑定过帐号
     * 
     * @return
     */
    int checkBindStatus();

    /**
     * 上传通讯录
     * 
     * @param list 通讯录列表
     * @return 结果对象
     */
    int uploadContactsList(ArrayList<Contacts> list);

    /**
     * 分页获取联系人列表
     * 
     * @param updateTime 联系人最后更新时间 长整形
     * @param start 开始位置
     * @param pageSize 每屏显示数量
     * @return 联系人响应体
     */
    int getContactsList(long updateTime, int start, int pageSize);

    /**
     * 比对联系人版本
     * 
     * @return
     */
    int getUpdateTimeReq();

    /**
     * 分页获取推荐联系人
     * 
     * @param start 开始位置
     * @param pageSize 每屏显示数量
     * @return 推荐联系人响应体
     */
    int getRecommendFriendsList(int start, int pageSize);

    /**
     * 获取推荐消息列表
     * 
     * @param msgTypeId 消息类型ID
     * @param start 起始记录值
     * @param pageSize 每页显示的记录数量
     * @param updateTime 推荐列表版本
     */
    int getRecommendMsgList(int msgTypeId, int start, int pageSize, long updateTime);

    /**
     * 分页获取联系人状态
     * 
     * @param start 开始位置
     * @param pageSize 每屏显示的数量
     * @return 联系人状态响应体
     */
    int getContactsStatus(int start, int pageSize);

    /**
     * 获取指定联系人状态
     * 
     * @param contactSkyids 联系人SKYID
     * @param contactPhones 联系人手机号码
     */
    int getSpecifiedContactsStatus(String contactSkyids, String contactPhones);

    /**
     * 发送网络消息
     * 
     * @param skyids 斯凯ID组（多个斯凯ID用都好(,)分割）
     * @param nickname 发送用户的昵称
     * @param msgContent 发送的内容
     * @return 结果对象
     */
    int sendMessage(ArrayList<String> skyids, String sendNickname, String msgContent);

    /**
     * 发送名片消息
     * 
     * @param destSkyids 接受者ID，多个用户用逗号隔开每个skyid
     * @param nickname 发送者昵称
     * @param vCardContent 名片内容
     */
    int sendVCardMsg(int destSkyids, String nickname, String vCardContent);

    /**
     * 将联系人添加到黑名单
     * 
     * @param contactId 联系人ID
     * @param destSkyid 陌生人ID
     */
    int addBlackList(int contactId, int destSkyid);

    /**
     * 从黑名单中删除联系人
     * 
     * @param contactId 联系人ID
     * @param destSkyid 陌生人ID
     */
    int delBlackList(int contactId, int destSkyid);

    /**
     * 添加联系人
     * 
     * @param skyid 斯凯ID
     * @param contactType 联系人类型
     * @return 结果对象
     */
    int addFriends(int skyid, int contactType);

    /**
     * 添加反馈信息
     * 
     * @param nickname 发送者昵称
     * @Param content 反馈内容
     */
    int addFeedBack(String nickname, String content);

    /**
     * 发送邀请联系人开通
     * 
     * @param buddyId
     */
    int sendInvite(int buddyId);

    /**
     * 获取用户信息
     * 
     * @param buddyId
     */
    int getUserInfo(int buddyId);

    /**
     * 设置是否允许推荐
     * 
     * @param isRecommend
     */
    int setRecommended(boolean isRecommend);

    /**
     * 检查更新
     * 
     * @param ua 终端UA信息
     * @param reqtype
     * @return
     */
    int checkUpdate(Object ua, int reqtype);
    
    /**
     * 增量同步联系人
     * @param updateTime  联系人版本
     * @return
     */
    int syncContacts(long updateTime);
}
