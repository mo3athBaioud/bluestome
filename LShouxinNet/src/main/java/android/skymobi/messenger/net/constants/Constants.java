
package android.skymobi.messenger.net.constants;

import android.skymobi.messenger.net.beans.SxApplyFastTalkReq;
import android.skymobi.messenger.net.beans.SxApplyFastTalkResp;
import android.skymobi.messenger.net.beans.SxBindChangeNotify;
import android.skymobi.messenger.net.beans.SxCompareTerminalUIDReq;
import android.skymobi.messenger.net.beans.SxCompareTerminalUIDResp;
import android.skymobi.messenger.net.beans.SxCompleteDeleteContactsReq;
import android.skymobi.messenger.net.beans.SxCompleteDeleteContactsResp;
import android.skymobi.messenger.net.beans.SxCreateFastTalkNotify;
import android.skymobi.messenger.net.beans.SxGetConfigurationReq;
import android.skymobi.messenger.net.beans.SxGetConfigurationResp;
import android.skymobi.messenger.net.beans.SxGetRestorableContactsReq;
import android.skymobi.messenger.net.beans.SxGetRestorableContactsResp;
import android.skymobi.messenger.net.beans.SxLeaveFastTalkNotify;
import android.skymobi.messenger.net.beans.SxLeaveFastTalkReq;
import android.skymobi.messenger.net.beans.SxLeaveFastTalkResp;
import android.skymobi.messenger.net.beans.SxRestoreContactsReq;
import android.skymobi.messenger.net.beans.SxRestoreContactsResp;
import android.skymobi.messenger.net.beans.SxUploadAbilityReq;
import android.skymobi.messenger.net.beans.SxUploadAbilityResp;
import android.skymobi.messenger.net.beans.SxUploadTerminalUIDReq;
import android.skymobi.messenger.net.beans.SxUploadTerminalUIDResp;
import android.skymobi.messenger.net.beans.commons.ConfigInfo;
import android.skymobi.messenger.net.beans.commons.RestorableContacts;
import android.skymobi.messenger.net.beans.lcs.LcsAndroidComplexRequest;
import android.skymobi.messenger.net.beans.lcs.LcsAndroidComplexResponse;
import android.skymobi.messenger.net.beans.lcs.LcsLogStatisticsRequest;
import android.skymobi.messenger.net.beans.lcs.LcsLogStatisticsResponse;

import com.skymobi.android.transport.protocol.esb.signal.GenerateUniqueIDReq;
import com.skymobi.android.transport.protocol.esb.signal.GenerateUniqueIDResp;

import java.util.HashSet;
import java.util.Set;

/**
 * 常量类
 * 
 * @ClassName: Constants
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-8 下午07:59:15
 */
public class Constants {

    // 调用PPA时的客户端源ID
    public final static short APP_SOURCE_ID = 18003;

    // 应用来源
    public final static String APP_SOURCE = "SHOUXIN_ANDROID";

    // 成功的状态
    public final static int SUCCESS = 200;

    // 网络状态
    public final static int NETWORK_STATUS = 200;

    // ACCESS IP
    public static final String ACCESS_IP = "115.238.91.240";

    // ACCESS PORT
    public static final int ACCESS_PORT = 10122;

    // 系统定义需要编解码的对象集合
    public final static Set<Class<?>> xipTypeMetainfoSet = new HashSet<Class<?>>();

    // 初始化需要编解码的对象
    static {
        xipTypeMetainfoSet
                .add(com.skymobi.android.transport.protocol.esb.AbstractAccessHeaderable.class);
        xipTypeMetainfoSet.add(com.skymobi.android.transport.protocol.esb.ESBFixedHeader.class);
        xipTypeMetainfoSet.add(com.skymobi.android.transport.protocol.esb.AccessRespHeader.class);
        xipTypeMetainfoSet.add(com.skymobi.android.transport.protocol.esb.AccessFixedHeader.class);
        xipTypeMetainfoSet
                .add(com.skymobi.android.transport.protocol.esb.AccessWithSeqFixedHeader.class);
        xipTypeMetainfoSet
                .add(com.skymobi.android.transport.protocol.esb.signal.AbstractEsbT2ASignal.class);
        xipTypeMetainfoSet
                .add(com.skymobi.android.transport.protocol.esb.signal.EsbAccess2TerminalSignal.class);

        xipTypeMetainfoSet
                .add(com.skymobi.android.transport.protocol.esb.signal.RegisterToAccessRedirectResp.class);
        xipTypeMetainfoSet
                .add(com.skymobi.android.transport.protocol.esb.signal.RegisterToAccessResp.class);
        xipTypeMetainfoSet
                .add(com.skymobi.android.transport.protocol.esb.signal.RegisterToAccessReq.class);
        xipTypeMetainfoSet
                .add(com.skymobi.android.transport.protocol.esb.signal.RegisterToAccessWithoutProtocolReq.class);
        xipTypeMetainfoSet
                .add(com.skymobi.android.transport.protocol.esb.signal.RetryToAccessReq.class);
        xipTypeMetainfoSet
                .add(com.skymobi.android.transport.protocol.esb.signal.RetryToAccessResp.class);
        xipTypeMetainfoSet
                .add(com.skymobi.android.transport.protocol.esb.signal.AccessOfflineNotify.class);
        xipTypeMetainfoSet
                .add(com.skymobi.android.transport.protocol.esb.signal.HeartbeatToAccessReq.class);
        xipTypeMetainfoSet
                .add(com.skymobi.android.transport.protocol.esb.signal.HeartbeatToAccessResp.class);
        xipTypeMetainfoSet
                .add(com.skymobi.android.transport.protocol.esb.signal.HeartbeatToEsbReq.class);
        xipTypeMetainfoSet
                .add(com.skymobi.android.transport.protocol.esb.signal.HeartbeatToEsbResp.class);
        xipTypeMetainfoSet
                .add(com.skymobi.android.transport.protocol.esb.signal.RegisterToEsbReq.class);
        xipTypeMetainfoSet
                .add(com.skymobi.android.transport.protocol.esb.signal.RegisterToEsbResp.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.commons.ChangeStatNotify.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.commons.ChangeStateAck.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.commons.EsbResult.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.commons.EsbSuccess.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.commons.EsbFailed.class);
        xipTypeMetainfoSet
                .add(android.skymobi.messenger.net.beans.commons.GetFriendsListSuccess.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.commons.RecommendMsg.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.commons.FriendGroupSt.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.commons.FriendItemSt.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.commons.Contacts.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.commons.Phone.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.commons.SimpleStatus.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.commons.SimpleStatusItem.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.commons.SimpleUserInfo.class);
        xipTypeMetainfoSet
                .add(android.skymobi.messenger.net.beans.commons.SimpleUserInfoItem.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.commons.NearUser.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.commons.WifiCell.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.commons.MobileCell.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.commons.Location.class);

        // 手信业务模块对象
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxAddBlackReq.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxAddBlackResp.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxAddFeedBackReq.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxAddFeedBackResp.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxDelBlackReq.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxDelBlackResp.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxGetRecommendMsgReq.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxGetRecommendMsgResp.class);
        xipTypeMetainfoSet
                .add(android.skymobi.messenger.net.beans.SxGetSpecifiedContactsStatusReq.class);
        xipTypeMetainfoSet
                .add(android.skymobi.messenger.net.beans.SxGetSpecifiedContactsStatusResp.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxGetUserInfoReq.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxGetUserInfoResp.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxSendChatMsgReq.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxSendChatMsgResp.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxSendInviteReq.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxSendInviteResp.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxSendVCardReq.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxSendVCardResp.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxSetRecommendedReq.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxSetRecommendedResp.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxOperateContactsReq.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxOperateContactsResp.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxGetFriendsReq.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxGetFriendsResp.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxSendChatMsgReq.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxSendChatMsgResp.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxAddFriendReq.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxAddFriendResp.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxGetContactsListReq.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxGetContactsListResp.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxGetContactsStatusReq.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxGetContactsStatusResp.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxGetUpdateTimeReq.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxGetUpdateTimeResp.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxFriendsMsgNotify.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxTLVNotifyAck.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxChatMsgNotify.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxSyncContactsReq.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxSyncContactsResp.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxSetUserinfoReq.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxSetUserinfoResp.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxGetBlackListReq.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxGetBlackListResp.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxGetRecommendedReq.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxGetRecommendedResp.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxGetOnlineStatusReq.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxGetOnlineStatusResp.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxGetSimpleStatusReq.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxGetSimpleStatusResp.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxGetSimpleUserInfoReq.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxGetSimpleUserInfoResp.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxCalcFriendsReq.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxCalcFriendsResp.class);
        // 2012-06-20 新增LBS数据
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxSaveUserLocationReq.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxSaveUserlocationResp.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxGetNearbyReq.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxGetNearbyResp.class);
        // 2012-07-18 新增单独获取推荐短信类型和根据短信类型获取短信列表请求，响应对象
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxGetRecommendMsgTypeReq.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxGetRecommendMsgTypeResp.class);
        xipTypeMetainfoSet
                .add(android.skymobi.messenger.net.beans.SxGetRecommendMsgByUpdateTimeReq.class);
        xipTypeMetainfoSet
                .add(android.skymobi.messenger.net.beans.SxGetRecommendMsgByUpdateTimeResp.class);

        // 2012-06-21 新增根据用户名查找联系人
        xipTypeMetainfoSet
                .add(android.skymobi.messenger.net.beans.SxGetUserInfoByUserNameReq.class);
        xipTypeMetainfoSet
                .add(android.skymobi.messenger.net.beans.SxGetUserInfoByUserNameResp.class);

        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxMarketingMessageNotify.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxSysMsgNotify.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxVCardNotify.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.SxOnlineStateChangeNotify.class);

        // 2012-08-13 新增获取服务端配置列表接口对象
        xipTypeMetainfoSet.add(ConfigInfo.class);
        xipTypeMetainfoSet.add(RestorableContacts.class);
        xipTypeMetainfoSet.add(SxGetConfigurationReq.class);
        xipTypeMetainfoSet.add(SxGetConfigurationResp.class);
        xipTypeMetainfoSet.add(SxGetRestorableContactsReq.class);
        xipTypeMetainfoSet.add(SxGetRestorableContactsResp.class);
        xipTypeMetainfoSet.add(SxRestoreContactsReq.class);
        xipTypeMetainfoSet.add(SxRestoreContactsResp.class);

        // 2012-10-11 申请快聊接口
        xipTypeMetainfoSet.add(SxApplyFastTalkReq.class);
        xipTypeMetainfoSet.add(SxApplyFastTalkResp.class);
        xipTypeMetainfoSet.add(SxLeaveFastTalkReq.class);
        xipTypeMetainfoSet.add(SxLeaveFastTalkResp.class);
        xipTypeMetainfoSet.add(SxCreateFastTalkNotify.class);
        xipTypeMetainfoSet.add(SxLeaveFastTalkNotify.class);

        // 2012-09-26 LCS 模块对象
        xipTypeMetainfoSet.add(LcsLogStatisticsRequest.class);
        xipTypeMetainfoSet.add(LcsLogStatisticsResponse.class);
        // 2012-10-16 新增复合日志接口
        xipTypeMetainfoSet.add(LcsAndroidComplexRequest.class);
        xipTypeMetainfoSet.add(LcsAndroidComplexResponse.class);

        // 2012-10-25 新增彻底删除联系人接口
        xipTypeMetainfoSet.add(SxCompleteDeleteContactsReq.class);
        xipTypeMetainfoSet.add(SxCompleteDeleteContactsResp.class);
        // 2012-10-25 解绑通知
        xipTypeMetainfoSet.add(SxBindChangeNotify.class);
        // 2012-10-25 比较终端UID
        xipTypeMetainfoSet.add(SxCompareTerminalUIDReq.class);
        xipTypeMetainfoSet.add(SxCompareTerminalUIDResp.class);
        // 2012-10-25 上传终端UID
        xipTypeMetainfoSet.add(SxUploadTerminalUIDReq.class);
        xipTypeMetainfoSet.add(SxUploadTerminalUIDResp.class);
        // 2012-10-25 上传终端能力值
        xipTypeMetainfoSet.add(SxUploadAbilityReq.class);
        xipTypeMetainfoSet.add(SxUploadAbilityResp.class);
        // 2012-11-08 向服务端申请UID
        xipTypeMetainfoSet.add(GenerateUniqueIDReq.class);
        xipTypeMetainfoSet.add(GenerateUniqueIDResp.class);

        // PPA业务对象
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.ppa.LoginPhoneRequest.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.ppa.LoginPhoneResponse.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.ppa.RegisterReq.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.ppa.RegisterResp.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.ppa.RegisterUserInfo.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.ppa.SetUserInfoReq.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.ppa.SetUserInfoResp.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.ppa.UserInfo.class);
        xipTypeMetainfoSet
                .add(android.skymobi.messenger.net.beans.ppa.GetUserInfoByImsiRequest.class);
        xipTypeMetainfoSet
                .add(android.skymobi.messenger.net.beans.ppa.GetUserInfoByImsiResponse.class);
        xipTypeMetainfoSet
                .add(android.skymobi.messenger.net.beans.ppa.JudgeMobileStatusRequest.class);
        xipTypeMetainfoSet
                .add(android.skymobi.messenger.net.beans.ppa.JudgeMobileStatusResponse.class);
        xipTypeMetainfoSet
                .add(android.skymobi.messenger.net.beans.ppa.GetUserInfoWithTokenReq.class);
        xipTypeMetainfoSet
                .add(android.skymobi.messenger.net.beans.ppa.GetUserInfoWithTokenResp.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.ppa.SetUserInfoReq.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.ppa.SetUserInfoResp.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.ppa.LogoutRequest.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.ppa.LogoutResponse.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.ppa.ModifyPwdRequest.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.ppa.ModifyPwdResponse.class);
        xipTypeMetainfoSet
                .add(android.skymobi.messenger.net.beans.ppa.GetMessage4SetPasswdReq.class);
        xipTypeMetainfoSet
                .add(android.skymobi.messenger.net.beans.ppa.GetMessage4SetPasswdResp.class);
        xipTypeMetainfoSet
                .add(android.skymobi.messenger.net.beans.ppa.GetUserBindedMobileRequest.class);
        xipTypeMetainfoSet
                .add(android.skymobi.messenger.net.beans.ppa.GetUserBindedMobileResponse.class);

        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.ppa.GetUsernameReq.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.ppa.GetUsernameResp.class);

        // 文件服务模块
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.fs.FsUploadRequest.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.fs.FsUploadResponse.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.fs.FsResponseInfo.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.fs.FsDownloadReq.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.fs.FsDownloadResp.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.fs.FsDownloadRespInfo.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.fs.FsImageDownloadReq.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.fs.FsImageDownloadResp.class);
        xipTypeMetainfoSet
                .add(android.skymobi.messenger.net.beans.fs.FsImageDownloadRespInfo.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.fs.FsImageUploadRequest.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.fs.FsImageUploadResponse.class);
        xipTypeMetainfoSet.add(android.skymobi.messenger.net.beans.fs.ImageDownloadInfo.class);
    }
}
