
package android.skymobi.messenger.net.client.impl;

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
import android.skymobi.messenger.net.client.IServerBiz;
import android.skymobi.messenger.net.connection.IConnection;
import android.skymobi.messenger.net.constants.ShareBlock;
import android.skymobi.messenger.net.notify.IServerBizNotify;

import com.skymobi.android.commons.logger.Logger;
import com.skymobi.android.commons.logger.LoggerFactory;

public class ServerBiz implements IServerBiz {

    private static Logger logger = LoggerFactory.getLogger(ServerBiz.class);

    private IConnection connection = null;

    private static ServerBiz instance = null;

    private Long timeOut = 30 * 1000L;

    private ServerBiz(IConnection connection, Long timeOut) {
        this.connection = connection;
        this.timeOut = timeOut;
    }

    public synchronized static IServerBiz getInstance(IConnection connection,
            IServerBizNotify serverBizNotify, Long timeOut) {
        if (null == instance) {
            instance = new ServerBiz(connection, timeOut);
        }
        return instance;
    }

    /**
     * 根据用户名获取用户绑定的手机号码
     * 
     * @param request
     * @return
     */
    @Override
    synchronized public GetUserBindedMobileResponse getMobileByUsername(
            GetUserBindedMobileRequest request) {
        GetUserBindedMobileResponse response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            long start = System.currentTimeMillis();
            int status = connection.send(request);
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(GetUserBindedMobileRequest.class);
                        if (null != obj) {
                            if (obj instanceof GetUserBindedMobileResponse) {
                                response = (GetUserBindedMobileResponse) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        logger.debug(" >>>> 根据用户名获取用户绑定的手机号码请求超时");
                        break;
                    }
                }
            } else {
                // 网络连接失败
                logger.debug(" >>>> getMobileByUsername server no response");
            }
        }
        return response;
    }

    /**
     * 检查绑定状态
     * 
     * @return
     */
    @Override
    synchronized public GetUserInfoByImsiResponse checkBindStatus(GetUserInfoByImsiRequest request) {
        GetUserInfoByImsiResponse response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            long start = System.currentTimeMillis();
            int status = connection.send(request);
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(GetUserInfoByImsiRequest.class);
                        if (null != obj) {
                            if (obj instanceof GetUserInfoByImsiResponse) {
                                response = (GetUserInfoByImsiResponse) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 超时标记
                        logger.debug(" >>>> 检查绑定状态请求超时");
                        break;
                    }
                }
            } else {
                // 网络连接失败
                logger.debug(" >>>> checkBindStatus server no response");
            }
        }
        return response;
    }

    /**
     * 绑定
     */
    @Override
    synchronized public JudgeMobileStatusResponse bind(JudgeMobileStatusRequest request) {
        JudgeMobileStatusResponse response = null;
        int i = 0;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(JudgeMobileStatusRequest.class);
                        if (null != obj) {
                            if (obj instanceof JudgeMobileStatusResponse) {
                                response = (JudgeMobileStatusResponse) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 绑定请求超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> bind server no response");
            }
        }
        return response;
    }

    /**
     * 根据SKYID获取用户名
     * 
     * @param request
     * @return
     */
    @Override
    synchronized public GetUsernameResp getUsername(GetUsernameReq request) {
        GetUsernameResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(GetUsernameReq.class);
                        if (null != obj) {
                            if (obj instanceof GetUsernameResp) {
                                response = (GetUsernameResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 注册请求超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> getUsername server no response");
            }
        }
        return response;
    }

    /**
     * 注册
     */
    @Override
    synchronized public RegisterResp register(RegisterReq request) {
        RegisterResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(RegisterReq.class);
                        if (null != obj) {
                            if (obj instanceof RegisterResp) {
                                response = (RegisterResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 注册请求超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>>>>>>>>>>>>>> register server no response");
            }
        }
        return response;
    }

    /**
     * 登录
     */
    @Override
    synchronized public LoginPhoneResponse login(LoginPhoneRequest request) {
        LoginPhoneResponse response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(LoginPhoneRequest.class);
                        if (null != obj) {
                            if (obj instanceof LoginPhoneResponse) {
                                response = (LoginPhoneResponse) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>>>>>>>>>>>>>> 登录请求超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>>>>>>>>>>>>>> login server no response");
            }
        }
        return response;
    }

    /**
     * 注销
     */
    @Override
    synchronized public LogoutResponse logout(LogoutRequest request) {
        LogoutResponse response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(LogoutRequest.class);
                        if (null != obj) {
                            if (obj instanceof LogoutResponse) {
                                response = (LogoutResponse) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>>>>>>>>>>>>>> 注销请求超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>>>>>>>>>>>>>> logout server no response");
            }
        }
        return response;
    }

    /**
     * 调用PPA的设置用户信息
     */
    @Override
    synchronized public SetUserInfoResp setUserInfo(SetUserInfoReq request) {
        SetUserInfoResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(SetUserInfoReq.class);
                        if (null != obj) {
                            if (obj instanceof SetUserInfoResp) {
                                response = (SetUserInfoResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>>>>>>>>>>>>>> 设置用户信息请求超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> setUserInfo server no response");
            }
        }
        return response;
    }

    /**
     * 调用手信服务的设置用户信息
     */
    @Override
    synchronized public SxSetUserinfoResp setUserInfo(SxSetUserinfoReq request) {
        SxSetUserinfoResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(SxSetUserinfoReq.class);
                        if (null != obj) {
                            if (obj instanceof SxSetUserinfoResp) {
                                response = (SxSetUserinfoResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 设置用户信息请求超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> setUserInfo server no response");
            }
        }
        return response;
    }

    /**
     * 获取用户信息
     */
    @Override
    synchronized public GetUserInfoWithTokenResp getUserInfo(GetUserInfoWithTokenReq request) {
        GetUserInfoWithTokenResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(GetUserInfoWithTokenReq.class);
                        if (null != obj) {
                            if (obj instanceof GetUserInfoWithTokenResp) {
                                response = (GetUserInfoWithTokenResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 获取用户信息请求超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> getUserInfo server no response");
            }
        }
        return response;
    }

    /**
     * 忘记密码
     */
    @Override
    synchronized public GetMessage4SetPasswdResp forgetPwd(GetMessage4SetPasswdReq request) {
        GetMessage4SetPasswdResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(GetMessage4SetPasswdReq.class);
                        if (null != obj) {
                            if (obj instanceof GetMessage4SetPasswdResp) {
                                response = (GetMessage4SetPasswdResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 忘记密码请求超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> forgetPwd server no response");
            }
        }
        return response;
    }

    /**
     * 修改密码
     * 
     * @param request
     * @return
     */
    @Override
    synchronized public ModifyPwdResponse modifyPwd(ModifyPwdRequest request) {
        ModifyPwdResponse response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(ModifyPwdRequest.class);
                        if (null != obj) {
                            if (obj instanceof ModifyPwdResponse) {
                                response = (ModifyPwdResponse) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 修改密码请求超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> modifyPwd server no response");
            }
        }
        return response;
    }

    /**
     * 上传联系人列表
     */
    @Override
    synchronized public SxOperateContactsResp uploadContactsList(SxOperateContactsReq request) {
        SxOperateContactsResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(SxOperateContactsReq.class);
                        if (null != obj) {
                            if (obj instanceof SxOperateContactsResp) {
                                response = (SxOperateContactsResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 上传联系人列表请求超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> uploadContactsList server no response");
            }
        }
        return response;
    }

    /**
     * 获取联系人列表
     */
    @Override
    synchronized public SxGetContactsListResp getContactsList(SxGetContactsListReq request) {
        SxGetContactsListResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(SxGetContactsListReq.class);
                        if (null != obj) {
                            if (obj instanceof SxGetContactsListResp) {
                                response = (SxGetContactsListResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 获取联系人列表请求超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> getContactsList server no response");
            }
        }
        return response;
    }

    /**
     * 获取联系人列表
     */
    @Override
    synchronized public SxGetSimpleUserInfoResp getContactsList(SxGetSimpleUserInfoReq request) {
        SxGetSimpleUserInfoResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(SxGetSimpleUserInfoReq.class);
                        if (null != obj) {
                            if (obj instanceof SxGetSimpleUserInfoResp) {
                                response = (SxGetSimpleUserInfoResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 获取联系人列表请求超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> getContactsList[SxGetSimpleUserInfoReq] server no response");
            }
        }
        return response;
    }

    /**
     * 获取联系人版本
     */
    @Override
    synchronized public SxGetUpdateTimeResp getContactVersion(SxGetUpdateTimeReq request) {
        SxGetUpdateTimeResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(SxGetUpdateTimeReq.class);
                        if (null != obj) {
                            if (obj instanceof SxGetUpdateTimeResp) {
                                response = (SxGetUpdateTimeResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 获取联系人版本请求超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> getContactVersion server no response");
            }
        }
        return response;
    }

    /**
     * 增量同步联系人
     * 
     * @param request
     * @return
     */
    @Override
    synchronized public SxSyncContactsResp synContacts(SxSyncContactsReq request) {
        SxSyncContactsResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(SxSyncContactsReq.class);
                        if (null != obj) {
                            if (obj instanceof SxSyncContactsResp) {
                                response = (SxSyncContactsResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 增量同步联系人请求超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> synContacts[SxSyncContactsReq] server no response");
            }
        }
        return response;
    }

    /**
     * 获取推荐联系人列表
     */
    @Override
    synchronized public SxGetFriendsResp getRecommendFriendsList(SxGetFriendsReq request) {
        SxGetFriendsResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(SxGetFriendsReq.class);
                        if (null != obj) {
                            if (obj instanceof SxGetFriendsResp) {
                                response = (SxGetFriendsResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 获取推荐联系人列表请求超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> getRecommendFriendsList server no response");
            }
        }
        return response;
    }

    /**
     * 获取推荐短信列表
     */
    @Override
    synchronized public SxGetRecommendMsgResp getRecommendMsgList(SxGetRecommendMsgReq request) {
        SxGetRecommendMsgResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(SxGetRecommendMsgReq.class);
                        if (null != obj) {
                            if (obj instanceof SxGetRecommendMsgResp) {
                                response = (SxGetRecommendMsgResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 获取推荐短信列表请求超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> getRecommendMsgList server no response");
            }
        }
        return response;
    }

    /**
     * 获取推荐短信类型
     * 
     * @param updateTime 服务端短信类型版本
     * @return
     */
    @Override
    public SxGetRecommendMsgTypeResp getRecommendMsgType(SxGetRecommendMsgTypeReq request) {
        SxGetRecommendMsgTypeResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(SxGetRecommendMsgTypeReq.class);
                        if (null != obj) {
                            if (obj instanceof SxGetRecommendMsgTypeResp) {
                                response = (SxGetRecommendMsgTypeResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 获取推荐短信类型请求超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> getRecommendMsgType server no response");
            }
        }
        return response;
    }

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
    @Override
    public SxGetRecommendMsgByUpdateTimeResp getRecommendMsgNew(
            SxGetRecommendMsgByUpdateTimeReq request) {
        SxGetRecommendMsgByUpdateTimeResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(SxGetRecommendMsgByUpdateTimeReq.class);
                        if (null != obj) {
                            if (obj instanceof SxGetRecommendMsgByUpdateTimeResp) {
                                response = (SxGetRecommendMsgByUpdateTimeResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 根据短信类别获取短信列表请求超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> getRecommendMsgNew server no response");
            }
        }
        return response;
    }

    /**
     * 获取联系人状态
     */
    @Override
    synchronized public SxGetContactsStatusResp getContactsStatus(SxGetContactsStatusReq request) {
        SxGetContactsStatusResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(SxGetContactsStatusReq.class);
                        if (null != obj) {
                            if (obj instanceof SxGetContactsStatusResp) {
                                response = (SxGetContactsStatusResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 获取联系人状态请求超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> getContactsStatus server no response");
            }
        }
        return response;
    }

    /**
     * 新分页获取联系人状态
     * 
     * @param request
     * @return
     */
    @Override
    public SxGetSimpleStatusResp getContactsStatus2(SxGetSimpleStatusReq request) {
        SxGetSimpleStatusResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(SxGetSimpleStatusReq.class);
                        if (null != obj) {
                            if (obj instanceof SxGetSimpleStatusResp) {
                                response = (SxGetSimpleStatusResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 获取联系人状态请求超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> getContactsStatus2[SxGetSimpleStatusReq] server no response");
            }
        }
        return response;
    }

    @Override
    public SxGetSpecifiedContactsStatusResp getSpecifiedContactsStatus(String contactSkyids,
            String contactPhones) {
        SxGetSpecifiedContactsStatusResp response = null;
        return response;
    }

    /**
     * 获取指定联系人状态
     * 
     * @param request
     * @return
     */
    @Override
    synchronized public SxGetSpecifiedContactsStatusResp getSpecifiedContactsStatus(
            SxGetSpecifiedContactsStatusReq request) {
        SxGetSpecifiedContactsStatusResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(SxGetSpecifiedContactsStatusReq.class);
                        if (null != obj) {
                            if (obj instanceof SxGetSpecifiedContactsStatusResp) {
                                response = (SxGetSpecifiedContactsStatusResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 获取指定联系人状态请求超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> getSpecifiedContactsStatus server no response");
            }
        }
        return response;
    }

    @Override
    synchronized public SxSendChatMsgResp sendChatMessage(SxSendChatMsgReq request) {
        SxSendChatMsgResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    // 修改当前聊天消息的响应时间的超时时间为40s.
                    if (end <= 40000L) {
                        Object obj = ShareBlock.get(SxSendChatMsgReq.class);
                        if (null != obj) {
                            if (obj instanceof SxSendChatMsgResp) {
                                response = (SxSendChatMsgResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 发送聊天消息请求超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> sendChatMessage server no response");
            }
        }
        return response;
    }

    /**
     * 发送名片
     */
    @Override
    synchronized public SxSendVCardResp sendVCardMsg(SxSendVCardReq request) {
        SxSendVCardResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    // 修改发送名片消息时接收响应的时间为40s.
                    if (end <= 40000L) {
                        Object obj = ShareBlock.get(SxSendVCardReq.class);
                        if (null != obj) {
                            if (obj instanceof SxSendVCardResp) {
                                response = (SxSendVCardResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 发送名片请求超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> sendVCardMsg server no response");
            }
        }
        return response;
    }

    /**
     * 添加黑名单
     */
    @Override
    synchronized public SxAddBlackResp addBlackList(SxAddBlackReq request) {
        SxAddBlackResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(SxAddBlackReq.class);
                        if (null != obj) {
                            if (obj instanceof SxAddBlackResp) {
                                response = (SxAddBlackResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 添加黑名单请求超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> addBlackList server no response");
            }
        }
        return response;
    }

    /**
     * 从黑名单中删除
     */
    @Override
    synchronized public SxDelBlackResp delBlackList(SxDelBlackReq request) {
        SxDelBlackResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(SxDelBlackReq.class);
                        if (null != obj) {
                            if (obj instanceof SxDelBlackResp) {
                                response = (SxDelBlackResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 从黑名单中删除请求超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> delBlackList server no response");
            }
        }
        return response;
    }

    /**
     * 添加联系人
     */
    @Override
    synchronized public SxAddFriendResp addFriends(SxAddFriendReq request) {
        SxAddFriendResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(SxAddFriendReq.class);
                        if (null != obj) {
                            if (obj instanceof SxAddFriendResp) {
                                response = (SxAddFriendResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 添加联系人请求超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> addFriends server no response");
            }
        }
        return response;
    }

    /**
     * 发送反馈
     */
    @Override
    synchronized public SxAddFeedBackResp addFeedBack(SxAddFeedBackReq request) {
        SxAddFeedBackResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(SxAddFeedBackReq.class);
                        if (null != obj) {
                            if (obj instanceof SxAddFeedBackResp) {
                                response = (SxAddFeedBackResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 发送反馈请求超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> addFeedBack server no response");
            }
        }
        return response;
    }

    @Override
    public int sendInvite(SxSendInviteReq request) {
        // TODO Auto-generated method stub
        int status = connection.send(request);
        return status;
    }

    /**
     * 获取其他好友的用户信息
     */
    @Override
    synchronized public SxGetUserInfoResp getUserInfo(SxGetUserInfoReq request) {
        SxGetUserInfoResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(SxGetUserInfoReq.class);
                        if (null != obj) {
                            if (obj instanceof SxGetUserInfoResp) {
                                response = (SxGetUserInfoResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 获取其他好友的用户信息请求超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> getUserInfo server no response");
            }
        }
        return response;
    }

    /**
     * 设置是否被推荐
     */
    @Override
    synchronized public SxSetRecommendedResp setRecommended(SxSetRecommendedReq request) {
        SxSetRecommendedResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(SxSetRecommendedReq.class);
                        if (null != obj) {
                            if (obj instanceof SxSetRecommendedResp) {
                                response = (SxSetRecommendedResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 设置是否被推荐请求超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> setRecommended server no response");
            }
        }
        return response;
    }

    /**
     * 获取设置是否被推荐值
     */
    @Override
    synchronized public SxGetRecommendedResp getSetRecommendedValue(SxGetRecommendedReq request) {
        SxGetRecommendedResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(SxGetRecommendedReq.class);
                        if (null != obj) {
                            if (obj instanceof SxGetRecommendedResp) {
                                response = (SxGetRecommendedResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 获取设置是否被推荐请求超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> getSetRecommendedValue server no response");
            }
        }
        return response;
    }

    /**
     * 获取黑名单列表接口
     * 
     * @param request
     * @return
     */
    @Override
    synchronized public SxGetBlackListResp getBlackList(SxGetBlackListReq request) {
        SxGetBlackListResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(SxGetBlackListReq.class);
                        if (null != obj) {
                            if (obj instanceof SxGetBlackListResp) {
                                response = (SxGetBlackListResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 获取黑名单列表超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> getBlackList server no response");
            }
        }
        return response;
    }

    /**
     * 获取指定人（陌生人）的在线状态
     * 
     * @param request
     * @return
     */
    @Override
    public SxGetOnlineStatusResp getBuddyOnlineStatus(SxGetOnlineStatusReq request) {
        SxGetOnlineStatusResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(SxGetOnlineStatusReq.class);
                        if (null != obj) {
                            if (obj instanceof SxGetOnlineStatusResp) {
                                response = (SxGetOnlineStatusResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 获取黑名单列表超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> getBuddyOnlineStatus server no response");
            }
        }
        return response;
    }

    /**
     * 计算推荐联系人
     * 
     * @param request
     * @return
     */
    @Override
    public synchronized SxCalcFriendsResp calcFriendsList(SxCalcFriendsReq request) {
        SxCalcFriendsResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(SxCalcFriendsReq.class);
                        if (null != obj) {
                            if (obj instanceof SxCalcFriendsResp) {
                                response = (SxCalcFriendsResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 计算推荐联系人超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> calcFriendsList server no response");
            }
        }
        return response;
    }

    /**
     * 获取好友和推荐联系人之间的关系
     * 
     * @param request
     * @return
     */
    @Override
    public synchronized SxCalcFriendsResp getRecommendRelation(SxCalcFriendsReq request) {
        SxCalcFriendsResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(SxCalcFriendsReq.class);
                        if (null != obj) {
                            if (obj instanceof SxCalcFriendsResp) {
                                response = (SxCalcFriendsResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 计算推荐联系人超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> getRecommendRelation server no response");
            }
        }
        return response;
    }

    /**
     * 获取附近的人业务接口
     * 
     * @param request
     * @return
     */
    @Override
    public SxGetNearbyResp getNearByFriends(SxGetNearbyReq request) {
        SxGetNearbyResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(SxGetNearbyReq.class);
                        if (null != obj) {
                            if (obj instanceof SxGetNearbyResp) {
                                response = (SxGetNearbyResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 获取附近的人超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> getNearByFriends server no response");
            }
        }
        return response;
    }

    /**
     * 根据用户名查找联系人
     * 
     * @param request
     * @return
     */
    @Override
    public SxGetUserInfoByUserNameResp getUserInfoByUserName(SxGetUserInfoByUserNameReq request) {
        SxGetUserInfoByUserNameResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(SxGetUserInfoByUserNameReq.class);
                        if (null != obj) {
                            if (obj instanceof SxGetUserInfoByUserNameResp) {
                                response = (SxGetUserInfoByUserNameResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 根据用户名查找联系人超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> getUserInfoByUserName server no response");
            }
        }
        return response;
    }

    /**
     * 获取服务端最新的配置内容
     * 
     * @param request
     * @return
     */
    @Override
    public SxGetConfigurationResp getConfiguration(SxGetConfigurationReq request) {
        SxGetConfigurationResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(SxGetConfigurationReq.class);
                        if (null != obj) {
                            if (obj instanceof SxGetConfigurationResp) {
                                response = (SxGetConfigurationResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 获取服务端最新的配置内容超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> getConfiguration server no response");
            }
        }
        return response;

    }

    /**
     * 查看可恢复联系人列表
     * 
     * @param request
     * @return
     */
    @Override
    public SxGetRestorableContactsResp getRestorableContacts(SxGetRestorableContactsReq request) {
        SxGetRestorableContactsResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(SxGetRestorableContactsReq.class);
                        if (null != obj) {
                            if (obj instanceof SxGetRestorableContactsResp) {
                                response = (SxGetRestorableContactsResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 查看可恢复联系人列表超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> getRestorableContacts server no response");
            }
        }
        return response;
    }

    /**
     * 批量恢复联系人
     * 
     * @param request
     * @return
     */
    @Override
    public SxRestoreContactsResp restoreContacts(SxRestoreContactsReq request) {
        SxRestoreContactsResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(SxRestoreContactsReq.class);
                        if (null != obj) {
                            if (obj instanceof SxRestoreContactsResp) {
                                response = (SxRestoreContactsResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 批量恢复联系人超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> restoreContacts server no response");
            }
        }
        return null;

    }

    /**
     * 申请快聊
     * 
     * @param request
     * @return
     */
    @Override
    public SxApplyFastTalkResp applyFastChat(SxApplyFastTalkReq request) {
        SxApplyFastTalkResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(SxApplyFastTalkReq.class);
                        if (null != obj) {
                            if (obj instanceof SxApplyFastTalkResp) {
                                response = (SxApplyFastTalkResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 申请快聊超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> applyFastChat server no response");
            }
        }
        return null;
    }

    /**
     * 结束快聊
     * 
     * @param request
     * @return
     */
    @Override
    public SxLeaveFastTalkResp leaveFastChat(SxLeaveFastTalkReq request) {
        SxLeaveFastTalkResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(SxLeaveFastTalkReq.class);
                        if (null != obj) {
                            if (obj instanceof SxLeaveFastTalkResp) {
                                response = (SxLeaveFastTalkResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 离开快聊超时");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> leaveFastChat server no response");
            }
        }
        return null;
    }

    /**
     * 复杂接口
     * 
     * @param request
     * @return
     */
    @Override
    public LcsAndroidComplexResponse complexMessage(LcsAndroidComplexRequest request) {
        LcsAndroidComplexResponse response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(LcsAndroidComplexRequest.class);
                        if (null != obj) {
                            if (obj instanceof LcsAndroidComplexResponse) {
                                response = (LcsAndroidComplexResponse) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 日志收集系统复合接口调用失败");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> complexMessage server no response");
            }
        }
        return null;

    }

    /**
     * 彻底删除联系人
     * 
     * @param request
     * @return
     */
    public SxCompleteDeleteContactsResp completeDeleteContacts(SxCompleteDeleteContactsReq request) {
        SxCompleteDeleteContactsResp response = null;
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(SxCompleteDeleteContactsReq.class);
                        if (null != obj) {
                            if (obj instanceof SxCompleteDeleteContactsResp) {
                                response = (SxCompleteDeleteContactsResp) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 彻底删除联系人接口调用失败");
                        break;
                    }
                }
            } else {
                // 联网失败
                logger.debug(" >>>> completeDeleteContacts server no response");
            }
        }
        return null;
    }

    /**
     * 离线消息确认推送接口
     */
    @Override
    public void offlineMsgPushConfirm(SxGetoffmessageReq request) {
        // TODO Auto-generated method stub
        if (connection.isConnect()) {
            int status = connection.send(request);
            if (status == 200) {
                logger.debug(" >>>> 离线消息推送请求发送成功.");
            }
        }

    }

    /**
     * 日志数据统计接口
     * 
     * @param request
     */
    @Override
    public LcsLogStatisticsResponse logStatistics(LcsLogStatisticsRequest request) {
        LcsLogStatisticsResponse response = null;
        // TODO Auto-generated method stub
        if (connection.isConnect()) {
            boolean isOk = false;
            int status = connection.send(request);
            long start = System.currentTimeMillis();
            if (status == 200) {
                logger.debug(" >>>> 日志数据统计请求发送成功.");
                while (!isOk) {
                    long end = System.currentTimeMillis() - start;
                    if (end <= timeOut) {
                        Object obj = ShareBlock.get(LcsLogStatisticsRequest.class);
                        if (null != obj) {
                            if (obj instanceof SxRestoreContactsResp) {
                                response = (LcsLogStatisticsResponse) obj;
                                isOk = true;
                                return response;
                            }
                        }
                    } else {
                        // timeout 标记超时
                        logger.debug(" >>>> 日志数据统计响应超时");
                        break;
                    }
                }
            } else {
                logger.debug("\t>>> 日志数据统计请求发送失败【" + status + "】");
            }
        }
        return response;
    }

    /**
     * @return the connection
     */
    public IConnection getConnection() {
        return connection;
    }

    /**
     * @param connection the connection to set
     */
    public void setConnection(IConnection connection) {
        this.connection = connection;
    }

    /**
     * @return the timeOut
     */
    public Long getTimeOut() {
        return timeOut;
    }

    /**
     * @param timeOut the timeOut to set
     */
    public void setTimeOut(Long timeOut) {
        this.timeOut = timeOut;
    }

}
