
package android.skymobi.messenger.net.client;

import android.skymobi.messenger.net.beans.SxAddBlackReq;
import android.skymobi.messenger.net.beans.SxAddBlackResp;
import android.skymobi.messenger.net.beans.SxAddFeedBackReq;
import android.skymobi.messenger.net.beans.SxAddFeedBackResp;
import android.skymobi.messenger.net.beans.SxAddFriendReq;
import android.skymobi.messenger.net.beans.SxAddFriendResp;
import android.skymobi.messenger.net.beans.SxApplyFastTalkReq;
import android.skymobi.messenger.net.beans.SxApplyFastTalkResp;
import android.skymobi.messenger.net.beans.SxCalcFriendsReq;
import android.skymobi.messenger.net.beans.SxCalcFriendsResp;
import android.skymobi.messenger.net.beans.SxCompareTerminalUIDReq;
import android.skymobi.messenger.net.beans.SxCompareTerminalUIDResp;
import android.skymobi.messenger.net.beans.SxCompleteDeleteContactsReq;
import android.skymobi.messenger.net.beans.SxCompleteDeleteContactsResp;
import android.skymobi.messenger.net.beans.SxDelBlackReq;
import android.skymobi.messenger.net.beans.SxDelBlackResp;
import android.skymobi.messenger.net.beans.SxGetBlackListReq;
import android.skymobi.messenger.net.beans.SxGetBlackListResp;
import android.skymobi.messenger.net.beans.SxGetConfigurationReq;
import android.skymobi.messenger.net.beans.SxGetConfigurationResp;
import android.skymobi.messenger.net.beans.SxGetContactsListReq;
import android.skymobi.messenger.net.beans.SxGetContactsListResp;
import android.skymobi.messenger.net.beans.SxGetContactsStatusReq;
import android.skymobi.messenger.net.beans.SxGetContactsStatusResp;
import android.skymobi.messenger.net.beans.SxGetFriendsReq;
import android.skymobi.messenger.net.beans.SxGetFriendsResp;
import android.skymobi.messenger.net.beans.SxGetNearbyReq;
import android.skymobi.messenger.net.beans.SxGetNearbyResp;
import android.skymobi.messenger.net.beans.SxGetOnlineStatusReq;
import android.skymobi.messenger.net.beans.SxGetOnlineStatusResp;
import android.skymobi.messenger.net.beans.SxGetRecommendMsgByUpdateTimeReq;
import android.skymobi.messenger.net.beans.SxGetRecommendMsgByUpdateTimeResp;
import android.skymobi.messenger.net.beans.SxGetRecommendMsgReq;
import android.skymobi.messenger.net.beans.SxGetRecommendMsgResp;
import android.skymobi.messenger.net.beans.SxGetRecommendMsgTypeReq;
import android.skymobi.messenger.net.beans.SxGetRecommendMsgTypeResp;
import android.skymobi.messenger.net.beans.SxGetRecommendedReq;
import android.skymobi.messenger.net.beans.SxGetRecommendedResp;
import android.skymobi.messenger.net.beans.SxGetRestorableContactsReq;
import android.skymobi.messenger.net.beans.SxGetRestorableContactsResp;
import android.skymobi.messenger.net.beans.SxGetSimpleStatusReq;
import android.skymobi.messenger.net.beans.SxGetSimpleStatusResp;
import android.skymobi.messenger.net.beans.SxGetSimpleUserInfoReq;
import android.skymobi.messenger.net.beans.SxGetSimpleUserInfoResp;
import android.skymobi.messenger.net.beans.SxGetSpecifiedContactsStatusReq;
import android.skymobi.messenger.net.beans.SxGetSpecifiedContactsStatusResp;
import android.skymobi.messenger.net.beans.SxGetUpdateTimeReq;
import android.skymobi.messenger.net.beans.SxGetUpdateTimeResp;
import android.skymobi.messenger.net.beans.SxGetUserInfoByUserNameReq;
import android.skymobi.messenger.net.beans.SxGetUserInfoByUserNameResp;
import android.skymobi.messenger.net.beans.SxGetUserInfoReq;
import android.skymobi.messenger.net.beans.SxGetUserInfoResp;
import android.skymobi.messenger.net.beans.SxGetoffmessageReq;
import android.skymobi.messenger.net.beans.SxLeaveFastTalkReq;
import android.skymobi.messenger.net.beans.SxLeaveFastTalkResp;
import android.skymobi.messenger.net.beans.SxOperateContactsReq;
import android.skymobi.messenger.net.beans.SxOperateContactsResp;
import android.skymobi.messenger.net.beans.SxRestoreContactsReq;
import android.skymobi.messenger.net.beans.SxRestoreContactsResp;
import android.skymobi.messenger.net.beans.SxSendChatMsgReq;
import android.skymobi.messenger.net.beans.SxSendChatMsgResp;
import android.skymobi.messenger.net.beans.SxSendInviteReq;
import android.skymobi.messenger.net.beans.SxSendVCardReq;
import android.skymobi.messenger.net.beans.SxSendVCardResp;
import android.skymobi.messenger.net.beans.SxSetRecommendedReq;
import android.skymobi.messenger.net.beans.SxSetRecommendedResp;
import android.skymobi.messenger.net.beans.SxSetUserinfoReq;
import android.skymobi.messenger.net.beans.SxSetUserinfoResp;
import android.skymobi.messenger.net.beans.SxSyncContactsReq;
import android.skymobi.messenger.net.beans.SxSyncContactsResp;
import android.skymobi.messenger.net.beans.SxUploadAbilityReq;
import android.skymobi.messenger.net.beans.SxUploadAbilityResp;
import android.skymobi.messenger.net.beans.SxUploadTerminalUIDReq;
import android.skymobi.messenger.net.beans.SxUploadTerminalUIDResp;
import android.skymobi.messenger.net.beans.lcs.LcsAndroidComplexRequest;
import android.skymobi.messenger.net.beans.lcs.LcsAndroidComplexResponse;
import android.skymobi.messenger.net.beans.lcs.LcsLogStatisticsRequest;
import android.skymobi.messenger.net.beans.lcs.LcsLogStatisticsResponse;
import android.skymobi.messenger.net.beans.ppa.GetMessage4SetPasswdReq;
import android.skymobi.messenger.net.beans.ppa.GetMessage4SetPasswdResp;
import android.skymobi.messenger.net.beans.ppa.GetUserBindedMobileRequest;
import android.skymobi.messenger.net.beans.ppa.GetUserBindedMobileResponse;
import android.skymobi.messenger.net.beans.ppa.GetUserInfoByImsiRequest;
import android.skymobi.messenger.net.beans.ppa.GetUserInfoByImsiResponse;
import android.skymobi.messenger.net.beans.ppa.GetUserInfoWithTokenReq;
import android.skymobi.messenger.net.beans.ppa.GetUserInfoWithTokenResp;
import android.skymobi.messenger.net.beans.ppa.GetUsernameReq;
import android.skymobi.messenger.net.beans.ppa.GetUsernameResp;
import android.skymobi.messenger.net.beans.ppa.JudgeMobileStatusRequest;
import android.skymobi.messenger.net.beans.ppa.JudgeMobileStatusResponse;
import android.skymobi.messenger.net.beans.ppa.LoginPhoneRequest;
import android.skymobi.messenger.net.beans.ppa.LoginPhoneResponse;
import android.skymobi.messenger.net.beans.ppa.LogoutRequest;
import android.skymobi.messenger.net.beans.ppa.LogoutResponse;
import android.skymobi.messenger.net.beans.ppa.ModifyPwdRequest;
import android.skymobi.messenger.net.beans.ppa.ModifyPwdResponse;
import android.skymobi.messenger.net.beans.ppa.RegisterReq;
import android.skymobi.messenger.net.beans.ppa.RegisterResp;
import android.skymobi.messenger.net.beans.ppa.SetUserInfoReq;
import android.skymobi.messenger.net.beans.ppa.SetUserInfoResp;

/**
 * 针对服务端交互的业务接口
 * 
 * @author Bluestome.Zhang
 */
public interface IServerBiz {

    /**
     * 根据用户名获取用户绑定的手机号码
     * 
     * @param request
     * @return
     */
    GetUserBindedMobileResponse getMobileByUsername(GetUserBindedMobileRequest request);

    /**
     * 检查绑定状态
     * 
     * @return
     */
    GetUserInfoByImsiResponse checkBindStatus(GetUserInfoByImsiRequest request);

    /**
     * 根据SKYID获取用户名
     * 
     * @param request
     * @return
     */
    GetUsernameResp getUsername(GetUsernameReq request);

    /**
     * 获取绑定状态
     * 
     * @param request
     * @return 绑定状态响应对象
     */
    JudgeMobileStatusResponse bind(JudgeMobileStatusRequest request);

    /**
     * 注册业务类接口2
     * 
     * @param request
     * @return 注册响应
     */
    RegisterResp register(RegisterReq request);

    /**
     * 登录方法2
     * 
     * @param request
     * @return LoginPhoneResponse 登录响应对象
     */
    LoginPhoneResponse login(LoginPhoneRequest request);

    /**
     * 注销用户
     * 
     * @param request
     * @return
     */
    LogoutResponse logout(LogoutRequest request);

    /**
     * 调用PPA服务设置用户信息
     * 
     * @param request
     * @return
     */
    SetUserInfoResp setUserInfo(SetUserInfoReq request);

    /**
     * 调用手信服务设置用户信息
     * 
     * @param request
     * @return
     */
    SxSetUserinfoResp setUserInfo(SxSetUserinfoReq request);

    /**
     * 通过token获取用户信息
     * 
     * @param request
     * @return
     */
    GetUserInfoWithTokenResp getUserInfo(GetUserInfoWithTokenReq request);

    /**
     * 修改密码
     * 
     * @param request
     * @return
     */
    ModifyPwdResponse modifyPwd(ModifyPwdRequest request);

    /**
     * 上传通讯录
     * 
     * @param request
     * @return
     */
    SxOperateContactsResp uploadContactsList(SxOperateContactsReq request);

    /**
     * 忘记密码
     * 
     * @param request
     * @return
     */
    GetMessage4SetPasswdResp forgetPwd(GetMessage4SetPasswdReq request);

    /**
     * 增量同步联系人
     * 
     * @param request
     * @return
     */
    SxSyncContactsResp synContacts(SxSyncContactsReq request);

    /**
     * 分页获取联系人列表
     * 
     * @param request
     * @return
     */
    SxGetContactsListResp getContactsList(SxGetContactsListReq request);

    /**
     * 新分页获取联系人列表
     * 
     * @param request
     * @return
     */
    SxGetSimpleUserInfoResp getContactsList(SxGetSimpleUserInfoReq request);

    /**
     * 比对联系人版本
     * 
     * @param request
     * @return
     */
    SxGetUpdateTimeResp getContactVersion(SxGetUpdateTimeReq request);

    /**
     * 分页获取推荐联系人
     * 
     * @param request
     * @return
     */
    SxGetFriendsResp getRecommendFriendsList(SxGetFriendsReq request);

    /**
     * 获取推荐消息列表
     * 
     * @param request
     * @return
     */
    SxGetRecommendMsgResp getRecommendMsgList(SxGetRecommendMsgReq request);

    /**
     * 获取推荐短信类型
     * 
     * @param updateTime 服务端短信类型版本
     * @return
     */
    SxGetRecommendMsgTypeResp getRecommendMsgType(SxGetRecommendMsgTypeReq request);

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
    SxGetRecommendMsgByUpdateTimeResp getRecommendMsgNew(SxGetRecommendMsgByUpdateTimeReq request);

    /**
     * 分页获取联系人状态
     * 
     * @param request
     * @return
     */
    SxGetContactsStatusResp getContactsStatus(SxGetContactsStatusReq request);

    /**
     * 新分页获取联系人状态
     * 
     * @param request
     * @return
     */
    SxGetSimpleStatusResp getContactsStatus2(SxGetSimpleStatusReq request);

    /**
     * 获取指定联系人状态
     * 
     * @param request
     * @return
     */
    SxGetSpecifiedContactsStatusResp getSpecifiedContactsStatus(String contactSkyids,
            String contactPhones);

    /**
     * 获取指定联系人状态
     * 
     * @param request
     * @return
     */
    SxGetSpecifiedContactsStatusResp getSpecifiedContactsStatus(
            SxGetSpecifiedContactsStatusReq request);

    /**
     * 发送网络消息
     * 
     * @param request
     * @return
     */
    SxSendChatMsgResp sendChatMessage(SxSendChatMsgReq request);

    /**
     * 发送名片消息
     * 
     * @param request
     * @return
     */
    SxSendVCardResp sendVCardMsg(SxSendVCardReq request);

    /**
     * 将联系人添加到黑名单
     * 
     * @param request
     * @return
     */
    SxAddBlackResp addBlackList(SxAddBlackReq request);

    /**
     * 从黑名单中删除联系人
     * 
     * @param request
     * @return
     */
    SxDelBlackResp delBlackList(SxDelBlackReq request);

    /**
     * 添加联系人
     * 
     * @param request
     * @return
     */
    SxAddFriendResp addFriends(SxAddFriendReq request);

    /**
     * 添加反馈信息
     * 
     * @param request
     * @return
     */
    SxAddFeedBackResp addFeedBack(SxAddFeedBackReq request);

    /**
     * 发送邀请联系人开通
     * 
     * @param request
     * @return
     */
    int sendInvite(SxSendInviteReq request);

    /**
     * 获取用户信息
     * 
     * @param request
     * @return
     */
    SxGetUserInfoResp getUserInfo(SxGetUserInfoReq request);

    /**
     * 设置是否允许推荐
     * 
     * @param request
     * @return
     */
    SxSetRecommendedResp setRecommended(SxSetRecommendedReq request);

    /**
     * 获取设置是否被推荐值
     */
    SxGetRecommendedResp getSetRecommendedValue(SxGetRecommendedReq request);

    /**
     * 离线消息推送确认接口
     * 
     * @param request
     */
    void offlineMsgPushConfirm(SxGetoffmessageReq request);

    /**
     * 日志数据统计接口
     * 
     * @param request
     */
    LcsLogStatisticsResponse logStatistics(LcsLogStatisticsRequest request);

    /**
     * 获取黑名单列表接口
     * 
     * @param request
     * @return
     */
    SxGetBlackListResp getBlackList(SxGetBlackListReq request);

    /**
     * 获取指定人（陌生人）的在线状态
     * 
     * @param request
     * @return
     */
    SxGetOnlineStatusResp getBuddyOnlineStatus(SxGetOnlineStatusReq request);

    /**
     * 计算推荐联系人
     * 
     * @param request
     * @return
     */
    SxCalcFriendsResp calcFriendsList(SxCalcFriendsReq request);

    /**
     * 获取好友和推荐联系人之间的关系
     * 
     * @param request
     * @return
     */
    SxCalcFriendsResp getRecommendRelation(SxCalcFriendsReq request);

    /**
     * 获取附近的人业务接口
     * 
     * @param request
     * @return
     */
    SxGetNearbyResp getNearByFriends(SxGetNearbyReq request);

    /**
     * 根据用户名查找联系人
     * 
     * @param request
     * @return
     */
    SxGetUserInfoByUserNameResp getUserInfoByUserName(SxGetUserInfoByUserNameReq request);

    /**
     * 获取服务端最新的配置内容
     * 
     * @param request
     * @return
     */
    SxGetConfigurationResp getConfiguration(SxGetConfigurationReq request);

    /**
     * 查看可恢复联系人列表
     * 
     * @param request
     * @return
     */
    SxGetRestorableContactsResp getRestorableContacts(SxGetRestorableContactsReq request);

    /**
     * 批量恢复联系人
     * 
     * @param request
     * @return
     */
    SxRestoreContactsResp restoreContacts(SxRestoreContactsReq request);

    /**
     * 申请快聊
     * 
     * @param request
     * @return
     */
    SxApplyFastTalkResp applyFastChat(SxApplyFastTalkReq request);

    /**
     * 结束快聊
     * 
     * @param request
     * @return
     */
    SxLeaveFastTalkResp leaveFastChat(SxLeaveFastTalkReq request);

    /**
     * 复杂接口
     * 
     * @param request
     * @return
     */
    LcsAndroidComplexResponse complexMessage(LcsAndroidComplexRequest request);

    /**
     * 彻底删除联系人
     * 
     * @param request
     * @return
     */
    SxCompleteDeleteContactsResp completeDeleteContacts(SxCompleteDeleteContactsReq request);

    /**
     * 比较终端的UID
     * 
     * @param request
     * @return
     */
    SxCompareTerminalUIDResp compareTerminalUID(SxCompareTerminalUIDReq request);

    /**
     * 上传终端UID
     * 
     * @param request
     * @return
     */
    SxUploadTerminalUIDResp uploadTerminalUID(SxUploadTerminalUIDReq request);

    /**
     * 上传终端能力值
     * 
     * @param request
     * @return
     */
    SxUploadAbilityResp uploadAbility(SxUploadAbilityReq request);

}
