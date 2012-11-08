
package android.skymobi.messenger.net.receiver;

import android.skymobi.messenger.net.beans.SxAddBlackReq;
import android.skymobi.messenger.net.beans.SxAddBlackResp;
import android.skymobi.messenger.net.beans.SxAddFeedBackReq;
import android.skymobi.messenger.net.beans.SxAddFeedBackResp;
import android.skymobi.messenger.net.beans.SxAddFriendReq;
import android.skymobi.messenger.net.beans.SxAddFriendResp;
import android.skymobi.messenger.net.beans.SxApplyFastTalkReq;
import android.skymobi.messenger.net.beans.SxApplyFastTalkResp;
import android.skymobi.messenger.net.beans.SxBindChangeNotify;
import android.skymobi.messenger.net.beans.SxCalcFriendsReq;
import android.skymobi.messenger.net.beans.SxCalcFriendsResp;
import android.skymobi.messenger.net.beans.SxChatMsgNotify;
import android.skymobi.messenger.net.beans.SxCompareTerminalUIDReq;
import android.skymobi.messenger.net.beans.SxCompareTerminalUIDResp;
import android.skymobi.messenger.net.beans.SxCompleteDeleteContactsReq;
import android.skymobi.messenger.net.beans.SxCompleteDeleteContactsResp;
import android.skymobi.messenger.net.beans.SxCreateFastTalkNotify;
import android.skymobi.messenger.net.beans.SxDelBlackReq;
import android.skymobi.messenger.net.beans.SxDelBlackResp;
import android.skymobi.messenger.net.beans.SxFriendsMsgNotify;
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
import android.skymobi.messenger.net.beans.SxLeaveFastTalkNotify;
import android.skymobi.messenger.net.beans.SxMarketingMessageNotify;
import android.skymobi.messenger.net.beans.SxOnlineStateChangeNotify;
import android.skymobi.messenger.net.beans.SxOperateContactsReq;
import android.skymobi.messenger.net.beans.SxOperateContactsResp;
import android.skymobi.messenger.net.beans.SxRestoreContactsReq;
import android.skymobi.messenger.net.beans.SxRestoreContactsResp;
import android.skymobi.messenger.net.beans.SxSendChatMsgReq;
import android.skymobi.messenger.net.beans.SxSendChatMsgResp;
import android.skymobi.messenger.net.beans.SxSendVCardReq;
import android.skymobi.messenger.net.beans.SxSendVCardResp;
import android.skymobi.messenger.net.beans.SxSetRecommendedReq;
import android.skymobi.messenger.net.beans.SxSetRecommendedResp;
import android.skymobi.messenger.net.beans.SxSetUserinfoReq;
import android.skymobi.messenger.net.beans.SxSetUserinfoResp;
import android.skymobi.messenger.net.beans.SxSyncContactsReq;
import android.skymobi.messenger.net.beans.SxSyncContactsResp;
import android.skymobi.messenger.net.beans.SxSysMsgNotify;
import android.skymobi.messenger.net.beans.SxTLVNotifyAck;
import android.skymobi.messenger.net.beans.SxUploadAbilityReq;
import android.skymobi.messenger.net.beans.SxUploadAbilityResp;
import android.skymobi.messenger.net.beans.SxUploadTerminalUIDReq;
import android.skymobi.messenger.net.beans.SxUploadTerminalUIDResp;
import android.skymobi.messenger.net.beans.SxVCardNotify;
import android.skymobi.messenger.net.beans.commons.ChangeStatNotify;
import android.skymobi.messenger.net.beans.commons.ChangeStateAck;
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
import android.skymobi.messenger.net.constants.ResponseCodeConstants;
import android.skymobi.messenger.net.constants.ShareBlock;
import android.skymobi.messenger.net.notify.IServerBizNotify;
import android.skymobi.messenger.net.notify.IServerNotify;

import com.skymobi.android.bean.bytebean.core.BeanFieldCodec;
import com.skymobi.android.bean.esb.codec.EsbBeanCodecSupportTLV;
import com.skymobi.android.bean.util.meta.Int2TypeMetainfo;
import com.skymobi.android.commons.logger.Logger;
import com.skymobi.android.commons.logger.LoggerFactory;
import com.skymobi.android.transport.AbstractEndpoint;
import com.skymobi.android.transport.Receiver;
import com.skymobi.android.transport.TCPConnector;
import com.skymobi.android.transport.codec.AccessSignalCodecUtils;
import com.skymobi.android.transport.protocol.esb.AccessFixedHeader;
import com.skymobi.android.transport.protocol.esb.signal.EsbAccess2TerminalSignal;
import com.skymobi.android.transport.protocol.esb.signal.GenerateUniqueIDReq;
import com.skymobi.android.transport.protocol.esb.signal.GenerateUniqueIDResp;
import com.skymobi.android.transport.protocol.esb.signal.HeartbeatToAccessResp;
import com.skymobi.android.transport.protocol.esb.signal.RegisterToAccessRedirectResp;
import com.skymobi.android.transport.protocol.esb.signal.RegisterToAccessResp;
import com.skymobi.android.transport.protocol.esb.signal.RetryToAccessResp;

import org.apache.mina.core.session.IoSession;

/**
 * 网络数据接收类
 * 
 * @author Bluestome.Zhang
 */
public class NetReceiver implements Receiver {

    private static final Logger logger = LoggerFactory.getLogger(NetReceiver.class);

    private BeanFieldCodec esbBeanCodec;
    private Int2TypeMetainfo esbTypeMetainfo;
    private AbstractEndpoint endPoint;
    // 定义一个客户端通知接口
    private IServerNotify notify;

    /**
     * 底层连接类
     */
    private TCPConnector tcpConnector;

    private NetReceiver() {
        esbBeanCodec = new EsbBeanCodecSupportTLV();
    }

    public NetReceiver(IServerNotify notify, IServerBizNotify ServerBizNotify) {
        this.notify = notify;
        esbBeanCodec = new EsbBeanCodecSupportTLV();
    }

    public BeanFieldCodec getEsbBeanCodec() {
        return esbBeanCodec;
    }

    public void setEsbBeanCodec(BeanFieldCodec esbBeanCodec) {
        this.esbBeanCodec = esbBeanCodec;
    }

    public Int2TypeMetainfo getEsbTypeMetainfo() {
        return esbTypeMetainfo;
    }

    public void setEsbTypeMetainfo(Int2TypeMetainfo esbTypeMetainfo) {
        this.esbTypeMetainfo = esbTypeMetainfo;
    }

    public AbstractEndpoint getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(AbstractEndpoint endPoint) {
        this.endPoint = endPoint;
    }

    /**
     * 消息接收类
     */
    @Override
    public void messageReceived(IoSession session, Object msg) {
        logger.debug(" >>>> " + getClass().getSimpleName() + " messageReceivered");
        try {
            byte[] head = null;
            AccessFixedHeader fixedHdr = null;
            byte[] body = null;
            int blen = -1;
            if (msg instanceof byte[]) {
                byte[] pack = (byte[]) msg;
                if (null != pack) {
                    try {
                        int len = pack.length;

                        // 如果内容长度只有通用头那么长，则表明该内容无正文，丢弃掉
                        if (len == AccessFixedHeader.COMMON_HEADER_SIZE) {
                            logger.debug("\t>>>>>> 正文长度为8,不符合实际内容，应该大于8");
                            endPoint.setRetryStatus(false);
                            session.close(true);
                        }

                        byte[] flag = new byte[2];
                        // 获取通用头中的第7，8位值，该值表示为当前响应的内容为flag.
                        System.arraycopy(pack, 6, flag, 0, 2);
                        int iFlag = 0;
                        try {
                            iFlag = byteToInt2(flag);
                        } catch (NumberFormatException e) {
                            logger.error("\t>>>>>> flag value format exception");
                        }
                        // 判断flag的十进制为0 或者 整个包是否小于最大的头，如果是，则表明当前包为一个没有SEQ和ACK的包，
                        if (iFlag == 0 || len < AccessFixedHeader.FIXED_HEADER_SIZE) {
                            if (pack.length < AccessFixedHeader.WITHOUT_SEQ_NUM_HEADER_SIZE) {
                                logger.error("\t>>>>>> 正文长度比数据头长少");
                                return;
                            }
                            head = new byte[AccessFixedHeader.WITHOUT_SEQ_NUM_HEADER_SIZE];
                            System.arraycopy(pack, 0, head, 0,
                                    AccessFixedHeader.WITHOUT_SEQ_NUM_HEADER_SIZE);
                        } else {
                            if (pack.length < AccessFixedHeader.WITHOUT_SEQ_NUM_HEADER_SIZE) {
                                logger.error("\t>>>>>> 正文长度比数据头长少");
                                return;
                            }
                            head = new byte[AccessFixedHeader.FIXED_HEADER_SIZE];
                            System.arraycopy(pack, 0, head, 0, AccessFixedHeader.FIXED_HEADER_SIZE);
                        }
                        fixedHdr = AccessSignalCodecUtils.decodeAccessHeader(head,
                                esbBeanCodec);

                        if (null == fixedHdr) {
                            logger.error(" Can't decode access's fixed header");
                            return;
                        }

                        logger.debug("\t>>>>> fixedHdr.class:"
                                + fixedHdr.getClass().getSimpleName() + ",value:" + fixedHdr);

                        // 对没有序号的消息提的解码，包括没有加密和加密的支持。
                        if (fixedHdr.getFlags() == 0x0 || fixedHdr.getFlags() == 0x400) {
                            // 没有序列的消息对象
                            blen = len - AccessFixedHeader.WITHOUT_SEQ_NUM_HEADER_SIZE;
                            body = new byte[blen];
                            System.arraycopy(pack, AccessFixedHeader.WITHOUT_SEQ_NUM_HEADER_SIZE,
                                    body, 0, blen);
                        } else {
                            blen = len - AccessFixedHeader.FIXED_HEADER_SIZE;
                            body = new byte[blen];
                            System.arraycopy(pack, AccessFixedHeader.FIXED_HEADER_SIZE, body, 0,
                                    blen);
                        }

                        logger.info("\t>>>>> msgCode:" + fixedHdr.getMsgCode());
                        EsbAccess2TerminalSignal signal = null;
                        try {
                            // 处理在解码不合适的内容时出现的异常
                            signal = AccessSignalCodecUtils.decodeSignalBody(
                                    fixedHdr, body, esbBeanCodec, esbTypeMetainfo);
                        } catch (RuntimeException re) {
                            logger.error("\t>>>>> AccessSignalCodecUtils.decodeSignalBody RuntimeException:"
                                    + re.getMessage());
                            return;
                        } catch (Exception e) {
                            logger.error("\t>>>>> AccessSignalCodecUtils.decodeSignalBody Exception:"
                                    + e.getMessage());
                            return;
                        }
                        if (null != signal) {
                            // 设置发送的ACK值，该值为实际确认的响应的SEQNUM
                            endPoint.setAck(fixedHdr.getSeqNum());
                            // 设置心跳时间,目的是收到不能解析的消息时，不更新心跳时间和总的数据接收时间
                            endPoint.setLastHB2AccessTime(System.currentTimeMillis());
                            logger.debug("\t>>>>> sign:" + signal);
                            logger.debug("\t>>>>> current.seqNum:" + endPoint.getSeqNum()
                                    + "| recv.seqNum:" + fixedHdr.getSeqNum()
                                    + "| endPoint.getAck:" + endPoint.getAck());
                            // 处理不同消息的类
                            switch (fixedHdr.getMsgCode()) {
                                case 0x9804:
                                    // 注册到ACCESS
                                    RegisterToAccessResp registerToAccessResp = (RegisterToAccessResp) signal;
                                    if (0 == registerToAccessResp.getResult()) {
                                        // 客户端上线
                                        endPoint.online(registerToAccessResp);
                                        notify.onNetStateNotify(Integer.valueOf(1));
                                    }
                                    break;
                                case 0x98042:
                                    // 重定向响应
                                    RegisterToAccessRedirectResp redirect = (RegisterToAccessRedirectResp) signal;
                                    // 通知应用层保存新的端口
                                    notify.newAccessServerConfigNotify(null,
                                            redirect.getNewAccessPort());
                                    break;
                                case 0x9806:
                                    // 心跳响应
                                    HeartbeatToAccessResp resp = (HeartbeatToAccessResp) signal;
                                    endPoint.recvd(resp);
                                    break;
                                case 0x9808:
                                    // UA重连
                                    RetryToAccessResp retryToAccessResp = (RetryToAccessResp) signal;
                                    endPoint.retry2access(retryToAccessResp);
                                    if (retryToAccessResp.getResult() == 0x0) {
                                        // 修改重连返回的参数
                                        notify.onNetStateNotify(Integer.valueOf(2));
                                    }
                                    break;
                                case ResponseCodeConstants.ACCESS_APPLY_UID_CODE:
                                    // 向服务端申请UID响应
                                    GenerateUniqueIDResp uidResp = (GenerateUniqueIDResp) signal;
                                    endPoint.applyUID(uidResp);
                                    break;
                                case 0x9815:
                                    logger.info("\t>>>>>>> 服务端踢人通知");
                                    // ACCESS推送的踢人通知,收到该消息后，服务端就会关闭连接。
                                    endPoint.setRetryStatus(false);
                                    notify.ticketOutNotify();
                                    break;
                                case ResponseCodeConstants.PPA_GET_MOBILE_BY_USERNAME:
                                    GetUserBindedMobileResponse userBindMobileResponse = (GetUserBindedMobileResponse) signal;
                                    ShareBlock.add(GetUserBindedMobileRequest.class,
                                            userBindMobileResponse);
                                    break;
                                case ResponseCodeConstants.PPA_CHECK_BIND_STATUS_CODE:
                                    // 检查IMSI是否被绑定
                                    GetUserInfoByImsiResponse res = (GetUserInfoByImsiResponse) signal;
                                    ShareBlock.add(GetUserInfoByImsiRequest.class, res);
                                    break;
                                case ResponseCodeConstants.PPA_CHECK_BIND_CODE:
                                    // 检查IMSI与SKYID是否绑定
                                    JudgeMobileStatusResponse bind = (JudgeMobileStatusResponse) signal;
                                    ShareBlock.add(JudgeMobileStatusRequest.class, bind);
                                    break;
                                case ResponseCodeConstants.PPA_GET_USERNAME_BY_SKYID_CODE:
                                    // 根据SKYID获取用户名
                                    GetUsernameResp getUsernameResp = (GetUsernameResp) signal;
                                    ShareBlock.add(GetUsernameReq.class, getUsernameResp);
                                    break;
                                case ResponseCodeConstants.PPA_LOGIN_CODE:
                                    // 登录响应
                                    LoginPhoneResponse value = (LoginPhoneResponse) signal;
                                    if (value.getResult() == 160308) {
                                        // 等待统一认证返回超时,断开连接，该操作是补充客户端
                                        endPoint.setRetryStatus(false);
                                        session.close(true);
                                    } else {
                                        ShareBlock.add(LoginPhoneRequest.class, value);
                                    }
                                    break;
                                case ResponseCodeConstants.CHANGE_STATE_NOTIFY_CODE:
                                    // 状态更新通知
                                    ChangeStatNotify value1 = (ChangeStatNotify) signal;
                                    sendChangeStateAck(value1.getSeqId(), value1.getDstESBAddr(),
                                            value1.getFlags(), value1.getSrcESBAddr());
                                    notify.onChangeStatNotifyResp(value1);
                                    break;
                                case ResponseCodeConstants.CONTACT_LIST_CODE:
                                    // 获取联系人列表
                                    SxGetContactsListResp clist = (SxGetContactsListResp) signal;
                                    ShareBlock.add(SxGetContactsListReq.class, clist);
                                    break;
                                case ResponseCodeConstants.RECOMMEND_FRIENDS_CODE:
                                    // 获取推荐好友列表
                                    SxGetFriendsResp flist = (SxGetFriendsResp) signal;
                                    ShareBlock.add(SxGetFriendsReq.class, flist);
                                    break;
                                case ResponseCodeConstants.CALC_RECOMMEND_FRIENDS_CODE:
                                    // 计算推荐联系人响应
                                    SxCalcFriendsResp calcFriends = (SxCalcFriendsResp) signal;
                                    ShareBlock.add(SxCalcFriendsReq.class, calcFriends);
                                    break;
                                case ResponseCodeConstants.USER_INFO_CODE:
                                    // 从手信服务中获取好友详细信息
                                    SxGetUserInfoResp userInfoResp = (SxGetUserInfoResp) signal;
                                    ShareBlock.add(SxGetUserInfoReq.class, userInfoResp);
                                    break;
                                case ResponseCodeConstants.PPA_REGISTER_CODE:
                                    // 注册响应
                                    RegisterResp register = (RegisterResp) signal;
                                    ShareBlock.add(RegisterReq.class, register);
                                    break;
                                case ResponseCodeConstants.SEND_CHAT_MSG_CODE:
                                    // 发送消息响应
                                    SxSendChatMsgResp chatMsgResp = (SxSendChatMsgResp) signal;
                                    ShareBlock.add(SxSendChatMsgReq.class, chatMsgResp);
                                    break;
                                case ResponseCodeConstants.ADD_FRIENDS_CODE:
                                    // 处理添加好友响应
                                    SxAddFriendResp aFriend = (SxAddFriendResp) signal;
                                    ShareBlock.add(SxAddFriendReq.class, aFriend);
                                    break;
                                case ResponseCodeConstants.MSG_CHAT_NONTIFY_CODE:
                                    // 好友消息
                                    SxChatMsgNotify chatMsg = (SxChatMsgNotify) signal;
                                    sendAck(chatMsg.getSeqid(), chatMsg.getDstESBAddr(),
                                            chatMsg.getFlags(), chatMsg.getSrcESBAddr());
                                    notify.onChatMsgNotify(chatMsg);
                                    logger.debug(" >>>> 接收到聊天消息:[" + chatMsg.getSeqid() + "]|["
                                            + chatMsg.getMsgContent() + "]");
                                    break;
                                case ResponseCodeConstants.MSG_SYS_NOTIFY_CODE:
                                    // 系统消息
                                    SxSysMsgNotify sysMsg = (SxSysMsgNotify) signal;
                                    sendAck(sysMsg.getSeqid(), sysMsg.getDstESBAddr(),
                                            sysMsg.getFlags(), sysMsg.getSrcESBAddr());
                                    notify.onSysMsgNotify(sysMsg);
                                    break;
                                case ResponseCodeConstants.MSG_VCARD_NOTIFY_CODE:
                                    // 名片消息
                                    SxVCardNotify vcardMsg = (SxVCardNotify) signal;
                                    sendAck(vcardMsg.getSeqid(), vcardMsg.getDstESBAddr(),
                                            vcardMsg.getFlags(), vcardMsg.getSrcESBAddr());
                                    notify.onSxVCardNotify(vcardMsg);
                                    break;
                                case ResponseCodeConstants.MSG_ONLINE_NOTIFY_CODE:
                                    // 上线通知
                                    SxOnlineStateChangeNotify onlineNotify = (SxOnlineStateChangeNotify) signal;
                                    sendAck(onlineNotify.getSeqid(), onlineNotify.getDstESBAddr(),
                                            onlineNotify.getFlags(), onlineNotify.getSrcESBAddr());
                                    notify.onOnlineStateChangeNotify(onlineNotify);
                                    break;
                                case ResponseCodeConstants.CREATE_FASTCHAT_NOTIFY_CODE:
                                    // 建立快聊通知
                                    SxCreateFastTalkNotify createFastChatNotify = (SxCreateFastTalkNotify) signal;
                                    sendAck(createFastChatNotify.getSeqid(),
                                            createFastChatNotify.getDstESBAddr(),
                                            createFastChatNotify.getFlags(),
                                            createFastChatNotify.getSrcESBAddr());
                                    notify.createFastChatNotify(createFastChatNotify);
                                    break;
                                case ResponseCodeConstants.LEAVE_FASTCHAT_NOTIFY_CODE:
                                    // 离开快聊通知
                                    SxLeaveFastTalkNotify leaveFastChatNotify = (SxLeaveFastTalkNotify) signal;
                                    sendAck(leaveFastChatNotify.getSeqid(),
                                            leaveFastChatNotify.getDstESBAddr(),
                                            leaveFastChatNotify.getFlags(),
                                            leaveFastChatNotify.getSrcESBAddr());
                                    notify.leaveFastChatNotify(leaveFastChatNotify);
                                    break;
                                case ResponseCodeConstants.PPA_SET_USERINFO_CODE:
                                    // 设置用户信息
                                    SetUserInfoResp setUserInfo = (SetUserInfoResp) signal;
                                    ShareBlock.add(SetUserInfoReq.class, setUserInfo);
                                    break;
                                case ResponseCodeConstants.MARKETING_NOTIFY_CODE:
                                    // 营销消息
                                    SxMarketingMessageNotify marketingNotify = (SxMarketingMessageNotify) signal;
                                    // 营销消息不需要发送ACK回应
                                    notify.onMarketingMsgNotify(marketingNotify);
                                    break;
                                case ResponseCodeConstants.CONTACT_VERSION_CODE:
                                    SxGetUpdateTimeResp updateVersion = (SxGetUpdateTimeResp) signal;
                                    ShareBlock.add(SxGetUpdateTimeReq.class, updateVersion);
                                    // 获取联系人版本时间
                                    break;
                                case ResponseCodeConstants.CONTACT_STATUS_CODE:
                                    // 获取联系人状态
                                    SxGetContactsStatusResp getContactsStatusResp = (SxGetContactsStatusResp) signal;
                                    ShareBlock.add(SxGetContactsStatusReq.class,
                                            getContactsStatusResp);
                                    break;
                                case ResponseCodeConstants.CONTACT_SPECIFIED_CODE:
                                    // 获取指定联系人状态
                                    SxGetSpecifiedContactsStatusResp specifiedContactsStatus = (SxGetSpecifiedContactsStatusResp) signal;
                                    ShareBlock.add(SxGetSpecifiedContactsStatusReq.class,
                                            specifiedContactsStatus);
                                    break;
                                case ResponseCodeConstants.RECOMMENDS_FRIENDS_NOTIFY_CODE:
                                    // 推荐好友通知 目前注释掉该接口，不通知到UI层
                                    SxFriendsMsgNotify friendsMsgNotify = (SxFriendsMsgNotify) signal;
                                    sendAck(friendsMsgNotify.getSeqid(),
                                            friendsMsgNotify.getDstESBAddr(),
                                            friendsMsgNotify.getFlags(),
                                            friendsMsgNotify.getSrcESBAddr());
                                    // notify.onSxFriendsMsgNotify(friendsMsgNotify);
                                    break;
                                case ResponseCodeConstants.OPER_CONTACT_CODE:
                                    // 上传联系人响应
                                    SxOperateContactsResp operateContactsResp = (SxOperateContactsResp) signal;
                                    ShareBlock.add(SxOperateContactsReq.class, operateContactsResp);
                                    break;
                                case ResponseCodeConstants.SHOUXIN_INCREMENT_OPER_CONTACE_CODE:
                                    SxSyncContactsResp synContactsResp = (SxSyncContactsResp) signal;
                                    // 增量上传联系人
                                    ShareBlock.add(SxSyncContactsReq.class, synContactsResp);
                                    break;
                                case ResponseCodeConstants.ADD_FEEDBACK_CODE:
                                    // 添加反馈响应
                                    SxAddFeedBackResp feedBack = (SxAddFeedBackResp) signal;
                                    ShareBlock.add(SxAddFeedBackReq.class, feedBack);
                                    break;
                                case ResponseCodeConstants.PPA_GET_USERINFO_CODE:
                                    // 获取用户信息响应
                                    GetUserInfoWithTokenResp userInfoWithTokenResp = (GetUserInfoWithTokenResp) signal;
                                    ShareBlock.add(GetUserInfoWithTokenReq.class,
                                            userInfoWithTokenResp);
                                    break;
                                case ResponseCodeConstants.PPA_MODIFY_PASSWORD_CODE:
                                    // 修改用户密码响应
                                    ModifyPwdResponse modifyPwdResponse = (ModifyPwdResponse) signal;
                                    ShareBlock.add(ModifyPwdRequest.class, modifyPwdResponse);
                                    break;
                                case ResponseCodeConstants.PPA_FIND_PASSWORD_CODE:
                                    // 忘记密码响应
                                    GetMessage4SetPasswdResp forgetPwd = (GetMessage4SetPasswdResp) signal;
                                    ShareBlock.add(GetMessage4SetPasswdReq.class, forgetPwd);
                                    break;
                                case ResponseCodeConstants.PPA_LOGOUT_CODE:
                                    // 注销用户响应
                                    LogoutResponse logoutResponse = (LogoutResponse) signal;
                                    ShareBlock.add(LogoutRequest.class, logoutResponse);
                                    break;
                                case ResponseCodeConstants.SET_RECOMMENDED_CODE:
                                    // 是否推荐响应
                                    SxSetRecommendedResp setRecommendedResp = (SxSetRecommendedResp) signal;
                                    ShareBlock.add(SxSetRecommendedReq.class, setRecommendedResp);
                                    break;
                                case ResponseCodeConstants.GET_SET_RECOMMEND_CODE:
                                    // 获取推荐值响应
                                    SxGetRecommendedResp getSetRecommendedResp = (SxGetRecommendedResp) signal;
                                    ShareBlock
                                            .add(SxGetRecommendedReq.class, getSetRecommendedResp);
                                    break;
                                case ResponseCodeConstants.ADD_BLACK_CODE:
                                    // 添加黑名单响应
                                    SxAddBlackResp addBlackResp = (SxAddBlackResp) signal;
                                    ShareBlock.add(SxAddBlackReq.class, addBlackResp);
                                    break;
                                case ResponseCodeConstants.DEL_BLACK_CODE:
                                    // 删除黑名单响应
                                    SxDelBlackResp delBlackResp = (SxDelBlackResp) signal;
                                    ShareBlock.add(SxDelBlackReq.class, delBlackResp);
                                    break;
                                case ResponseCodeConstants.RECOMMEND_MSG_CODE:
                                    // 推荐短信响应
                                    SxGetRecommendMsgResp getRecommendMsgResp = (SxGetRecommendMsgResp) signal;
                                    ShareBlock.add(SxGetRecommendMsgReq.class, getRecommendMsgResp);
                                    break;
                                case ResponseCodeConstants.SEND_VCARD_MSG_CODE:
                                    // 发送名片消息响应
                                    SxSendVCardResp sendVCardResp = (SxSendVCardResp) signal;
                                    ShareBlock.add(SxSendVCardReq.class, sendVCardResp);
                                    break;
                                case ResponseCodeConstants.SHOUXIN_SET_USERINFO_CODE:
                                    // 调用手信的设置用户信息接口
                                    SxSetUserinfoResp setUserInfoResp = (SxSetUserinfoResp) signal;
                                    ShareBlock.add(SxSetUserinfoReq.class, setUserInfoResp);
                                    break;
                                case ResponseCodeConstants.SHOUXIN_GET_BLACK_LIST_CODE:
                                    // 获取联系人黑名单列表
                                    SxGetBlackListResp getBlackListResp = (SxGetBlackListResp) signal;
                                    ShareBlock.add(SxGetBlackListReq.class, getBlackListResp);
                                    break;
                                case ResponseCodeConstants.ONLINE_STATUS_CODE:
                                    // 获取指定人（陌生人）的在线状态
                                    SxGetOnlineStatusResp onlineStatusResp = (SxGetOnlineStatusResp) signal;
                                    ShareBlock.add(SxGetOnlineStatusReq.class, onlineStatusResp);
                                    break;
                                case ResponseCodeConstants.CONTACT_STATUS_CODE_2:
                                    // 获取联系人状态
                                    SxGetSimpleStatusResp simpleStatusResp = (SxGetSimpleStatusResp) signal;
                                    ShareBlock.add(SxGetSimpleStatusReq.class, simpleStatusResp);
                                    break;
                                case ResponseCodeConstants.CONTACT_LIST_CODE_2:
                                    // 获取联系人简单信息
                                    SxGetSimpleUserInfoResp simpleUserInfoResp = (SxGetSimpleUserInfoResp) signal;
                                    ShareBlock
                                            .add(SxGetSimpleUserInfoReq.class, simpleUserInfoResp);
                                    break;
                                case ResponseCodeConstants.GET_NEAR_BY_FRIEND_CODE:
                                    // 获取附近的人响应
                                    SxGetNearbyResp getNearByResp = (SxGetNearbyResp) signal;
                                    ShareBlock.add(SxGetNearbyReq.class, getNearByResp);
                                    break;
                                case ResponseCodeConstants.GET_USER_INFO_BY_USERNAME:
                                    // 根据用户名查找用户
                                    SxGetUserInfoByUserNameResp getUserInfoByUserName = (SxGetUserInfoByUserNameResp) signal;
                                    ShareBlock.add(SxGetUserInfoByUserNameReq.class,
                                            getUserInfoByUserName);
                                    break;
                                case ResponseCodeConstants.GET_RECOMMEND_MSG_TYPE:
                                    // 获取推荐短信类型列表
                                    SxGetRecommendMsgTypeResp getRecommendMsgType = (SxGetRecommendMsgTypeResp) signal;
                                    ShareBlock.add(SxGetRecommendMsgTypeReq.class,
                                            getRecommendMsgType);
                                    break;
                                case ResponseCodeConstants.GET_RECOMMEND_MSG_BY_TYPE_NEW:
                                    // 根据推荐短信类型ID获取推荐短信列表
                                    SxGetRecommendMsgByUpdateTimeResp recommendMsgByUpdate = (SxGetRecommendMsgByUpdateTimeResp) signal;
                                    ShareBlock.add(SxGetRecommendMsgByUpdateTimeReq.class,
                                            recommendMsgByUpdate);
                                    break;
                                case ResponseCodeConstants.GET_CONFIGURATION_CODE:
                                    // 获取服务端的配置列表
                                    SxGetConfigurationResp getConfigurationResp = (SxGetConfigurationResp) signal;
                                    ShareBlock.add(SxGetConfigurationReq.class,
                                            getConfigurationResp);
                                    break;
                                case ResponseCodeConstants.GET_RESTORABLE_CONTACTS_CODE:
                                    // 查看可恢复联系人列表
                                    SxGetRestorableContactsResp restorableContacts = (SxGetRestorableContactsResp) signal;
                                    ShareBlock.add(SxGetRestorableContactsReq.class,
                                            restorableContacts);
                                    break;
                                case ResponseCodeConstants.RESTORABLE_CONTACTS_CODE:
                                    // 批量恢复联系人
                                    SxRestoreContactsResp restoreContactsResp = (SxRestoreContactsResp) signal;
                                    ShareBlock.add(SxRestoreContactsReq.class, restoreContactsResp);
                                    break;
                                case ResponseCodeConstants.LCS_LOGSTATISTICS_CODE:
                                    // LCS 响应
                                    LcsLogStatisticsResponse lcsResponse = (LcsLogStatisticsResponse) signal;
                                    ShareBlock.add(LcsLogStatisticsRequest.class, lcsResponse);
                                    break;
                                case ResponseCodeConstants.APPLY_FASTCHAT_CODE:
                                    // 申请快聊响应
                                    SxApplyFastTalkResp applyFastChatResp = (SxApplyFastTalkResp) signal;
                                    ShareBlock.add(SxApplyFastTalkReq.class, applyFastChatResp);
                                    break;
                                case ResponseCodeConstants.LCS_COMPLEXMESSAGE_CODE:
                                    // 复合日志响应
                                    LcsAndroidComplexResponse complexResp = (LcsAndroidComplexResponse) signal;
                                    ShareBlock.add(LcsAndroidComplexRequest.class, complexResp);
                                    break;
                                case ResponseCodeConstants.COMPLETE_DELETE_CONTACTS:
                                    // 彻底删除联系人响应逻辑
                                    SxCompleteDeleteContactsResp completeDeleteContactsResp = (SxCompleteDeleteContactsResp) signal;
                                    ShareBlock.add(SxCompleteDeleteContactsReq.class,
                                            completeDeleteContactsResp);
                                    break;
                                case ResponseCodeConstants.BIND_CHANGE_NOTIFY:
                                    // 绑定/解绑通知
                                    SxBindChangeNotify bindChangeNotify = (SxBindChangeNotify) signal;
                                    sendAck(bindChangeNotify.getSeqid(),
                                            bindChangeNotify.getDstESBAddr(),
                                            bindChangeNotify.getFlags(),
                                            bindChangeNotify.getSrcESBAddr());
                                    notify.bindChangeNotify(bindChangeNotify);
                                    break;
                                case ResponseCodeConstants.COMPARE_TERMINAL_UID:
                                    // 比较终端UID响应
                                    SxCompareTerminalUIDResp compareTerminalUidResp = (SxCompareTerminalUIDResp) signal;
                                    ShareBlock.add(SxCompareTerminalUIDReq.class,
                                            compareTerminalUidResp);
                                    break;
                                case ResponseCodeConstants.UPLOAD_TERMINAL_UID:
                                    // 上传终端UID响应
                                    SxUploadTerminalUIDResp uploadTerminalUIDResp = (SxUploadTerminalUIDResp) signal;
                                    ShareBlock.add(SxUploadTerminalUIDReq.class,
                                            uploadTerminalUIDResp);
                                    break;
                                case ResponseCodeConstants.UPLOAD_ABILITY:
                                    // 上传终端能力值响应
                                    SxUploadAbilityResp uploadAbilityResp = (SxUploadAbilityResp) signal;
                                    ShareBlock.add(SxUploadAbilityReq.class,
                                            uploadAbilityResp);
                                    break;
                                default:
                                    logger.debug("\t>>>> 没有定义具体处理方法:[" + fixedHdr.getMsgCode()
                                            + "]");
                                    break;
                            }
                        } else {
                            logger.warn("\t>>>>> 消息码[" + fixedHdr.getMsgCode() + "]未定义 ");
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        logger.warn("\t>>>>> " + getClass().getSimpleName() + e.getMessage());
                    }
                }
            } else {
                logger.warn("\t>>>>> " + getClass().getSimpleName() + ".messageReceived:" + msg
                        + " is not byte[] ");
            }
        } catch (Exception e) {
            logger.warn("\t>>>>> " + getClass().getSimpleName() + ".exception:" + e);
        }

    }

    /**
     * 发送M2A消息回馈
     * 
     * @param seqid
     */
    private void sendAck(final int seqid, final int dstEsbAddr, final int flag, final int srcEsbAddr) {
        // 异步发送消息响应
        new Thread(new Runnable() {
            @Override
            public void run() {
                SxTLVNotifyAck ack = new SxTLVNotifyAck();
                ack.setSeqid(seqid);
                // 0xA400 srcEsbAddr
                ack.setDstESBAddr(srcEsbAddr);
                ack.setFlags((short) flag);
                ack.setSrcESBAddr(dstEsbAddr);
                logger.debug(" >>>> sendAck:" + ack);
                endPoint.send(ack);
            }
        }).start();
    }

    /**
     * 发送M2A消息回馈
     * 
     * @param seqid
     */
    private void sendChangeStateAck(final int seqid, final int dstEsbAddr, final int flag,
            final int srcEsbAddr) {
        // 异步发送消息确认
        new Thread(new Runnable() {
            @Override
            public void run() {
                ChangeStateAck ack = new ChangeStateAck();
                ack.setSeqid(seqid);
                ack.setDstESBAddr(srcEsbAddr);
                ack.setFlags((short) flag);
                ack.setSrcESBAddr(dstEsbAddr);
                endPoint.send(ack);
            }
        }).start();
    }

    public IServerNotify getNotify() {
        return notify;
    }

    public void setNotify(IServerNotify notify) {
        this.notify = notify;
    }

    /**
     * @return the tcpConnector
     */
    public TCPConnector getTcpConnector() {
        return tcpConnector;
    }

    /**
     * @param tcpConnector the tcpConnector to set
     */
    public void setTcpConnector(TCPConnector tcpConnector) {
        this.tcpConnector = tcpConnector;
    }

    /**
     * 二进制转十进制
     * 
     * @param b
     * @return
     */
    private int byteToInt2(byte[] b) {
        int mask = 0xff;
        int temp = 0;
        int n = 0;
        for (int i = 0; i < b.length; i++) {
            n <<= 8;
            temp = b[i] & mask;
            n |= temp;
        }
        return n;
    }
}
