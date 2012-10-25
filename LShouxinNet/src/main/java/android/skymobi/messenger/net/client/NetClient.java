
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
import android.skymobi.messenger.net.beans.SxSendVCardReq;
import android.skymobi.messenger.net.beans.SxSendVCardResp;
import android.skymobi.messenger.net.beans.SxSetRecommendedReq;
import android.skymobi.messenger.net.beans.SxSetRecommendedResp;
import android.skymobi.messenger.net.beans.SxSetUserinfoReq;
import android.skymobi.messenger.net.beans.SxSetUserinfoResp;
import android.skymobi.messenger.net.beans.SxSyncContactsReq;
import android.skymobi.messenger.net.beans.SxSyncContactsResp;
import android.skymobi.messenger.net.beans.commons.Audio;
import android.skymobi.messenger.net.beans.commons.ConfigInfo;
import android.skymobi.messenger.net.beans.commons.Contacts;
import android.skymobi.messenger.net.beans.commons.Phone;
import android.skymobi.messenger.net.beans.commons.ResultInfo;
import android.skymobi.messenger.net.beans.fs.DownloadInfo;
import android.skymobi.messenger.net.beans.fs.FsDownloadReq;
import android.skymobi.messenger.net.beans.fs.FsDownloadResp;
import android.skymobi.messenger.net.beans.fs.FsDownloadRespInfo;
import android.skymobi.messenger.net.beans.fs.FsImageDownloadReq;
import android.skymobi.messenger.net.beans.fs.FsImageDownloadResp;
import android.skymobi.messenger.net.beans.fs.FsImageDownloadRespInfo;
import android.skymobi.messenger.net.beans.fs.FsImageUploadRequest;
import android.skymobi.messenger.net.beans.fs.FsImageUploadResponse;
import android.skymobi.messenger.net.beans.fs.FsUploadRequest;
import android.skymobi.messenger.net.beans.fs.FsUploadResponse;
import android.skymobi.messenger.net.beans.fs.ImageDownloadInfo;
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
import android.skymobi.messenger.net.beans.ppa.UserInfo;
import android.skymobi.messenger.net.beans.sup.SupRequest;
import android.skymobi.messenger.net.beans.sup.SupResponse;
import android.skymobi.messenger.net.client.bean.NetAddFriendResponse;
import android.skymobi.messenger.net.client.bean.NetApplyFastChatResponse;
import android.skymobi.messenger.net.client.bean.NetBindResponse;
import android.skymobi.messenger.net.client.bean.NetBindResponse.BindStatus;
import android.skymobi.messenger.net.client.bean.NetBlackResponse;
import android.skymobi.messenger.net.client.bean.NetCalcFriendsResponse;
import android.skymobi.messenger.net.client.bean.NetChatRequest;
import android.skymobi.messenger.net.client.bean.NetChatResponse;
import android.skymobi.messenger.net.client.bean.NetCompareTerminalUIDResp;
import android.skymobi.messenger.net.client.bean.NetContacts;
import android.skymobi.messenger.net.client.bean.NetContactsPhone;
import android.skymobi.messenger.net.client.bean.NetContactsResultInfo;
import android.skymobi.messenger.net.client.bean.NetContactsVersionResponse;
import android.skymobi.messenger.net.client.bean.NetDownloadImageRespInfo;
import android.skymobi.messenger.net.client.bean.NetDownloadResponse;
import android.skymobi.messenger.net.client.bean.NetForgetPwdResponse;
import android.skymobi.messenger.net.client.bean.NetFsDownloadFile;
import android.skymobi.messenger.net.client.bean.NetFsDownloadReq;
import android.skymobi.messenger.net.client.bean.NetFsDownloadResponse;
import android.skymobi.messenger.net.client.bean.NetGetBlakListResponse;
import android.skymobi.messenger.net.client.bean.NetGetConfigurationResponse;
import android.skymobi.messenger.net.client.bean.NetGetContactsList2Response;
import android.skymobi.messenger.net.client.bean.NetGetContactsListResponse;
import android.skymobi.messenger.net.client.bean.NetGetContactsStatus2Response;
import android.skymobi.messenger.net.client.bean.NetGetContactsStatusResponse;
import android.skymobi.messenger.net.client.bean.NetGetFriendstResponse;
import android.skymobi.messenger.net.client.bean.NetGetMobileByUsernameResponse;
import android.skymobi.messenger.net.client.bean.NetGetNearByFriendResponse;
import android.skymobi.messenger.net.client.bean.NetGetRecommendedMsgNewResponse;
import android.skymobi.messenger.net.client.bean.NetGetRecommendedMsgResponse;
import android.skymobi.messenger.net.client.bean.NetGetRecommendedMsgTypeResponse;
import android.skymobi.messenger.net.client.bean.NetGetSetRecommendResponse;
import android.skymobi.messenger.net.client.bean.NetGetUserInfoByUserNameResponse;
import android.skymobi.messenger.net.client.bean.NetGetUsernameResponse;
import android.skymobi.messenger.net.client.bean.NetImageDownloadResponse;
import android.skymobi.messenger.net.client.bean.NetLoginResponse;
import android.skymobi.messenger.net.client.bean.NetOnlineStatusResponse;
import android.skymobi.messenger.net.client.bean.NetOperateContactsResponse;
import android.skymobi.messenger.net.client.bean.NetRegResponse;
import android.skymobi.messenger.net.client.bean.NetResponse;
import android.skymobi.messenger.net.client.bean.NetRestorableContactsResponse;
import android.skymobi.messenger.net.client.bean.NetRestoreContactsResp;
import android.skymobi.messenger.net.client.bean.NetSpecifiedContactsStatusResponse;
import android.skymobi.messenger.net.client.bean.NetSupResponse;
import android.skymobi.messenger.net.client.bean.NetSyncContactsResponse;
import android.skymobi.messenger.net.client.bean.NetUploadResponse;
import android.skymobi.messenger.net.client.bean.NetUserInfo;
import android.skymobi.messenger.net.client.bean.NetUserInfoResponse;
import android.skymobi.messenger.net.client.bean.NetmodifyPwdResponse;
import android.skymobi.messenger.net.client.impl.ServerBiz;
import android.skymobi.messenger.net.connection.NetConfig;
import android.skymobi.messenger.net.connection.impl.NetConnection;
import android.skymobi.messenger.net.constants.Constants;
import android.skymobi.messenger.net.constants.ESBAddrConstatns;
import android.skymobi.messenger.net.notify.impl.ClientNotify;
import android.skymobi.messenger.net.utils.ParserUtils;
import android.skymobi.messenger.net.utils.SysUtils;

import com.skymobi.android.TerminalInfo;
import com.skymobi.android.bean.tlv.decode.decoders.BeanTLVDecoder;
import com.skymobi.android.bean.tlv.encode.TLVEncodeContext;
import com.skymobi.android.bean.tlv.encode.encoders.BeanTLVEncoder;
import com.skymobi.android.commons.logger.Logger;
import com.skymobi.android.commons.logger.LoggerFactory;
import com.skymobi.android.transport.protocol.esb.signal.AbstractEsbT2ASignal;
import com.skymobi.android.util.ByteUtils;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 客户端调用的接口实现类
 * 
 * @ClassName: NetClient
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-6 下午02:42:35
 */
public class NetClient implements INetClient {

    private static final Logger logger = LoggerFactory.getLogger(NetClient.class);

    // 分包文件最大下载大小
    private static final int MAX_FILE_LEN = 50 * 1024;

    // 最大的缓存字节数
    private static final int MAX_BUFFER = 2048;

    private static NetClient instance = null;

    private IServerBiz serverBiz = null;

    private INetListener listener = null;

    private NetConnection connection = null;

    private NetConfig config = null;

    // 重连计数器
    private static int RECONNECT_COUNT = 0;

    /**
     * 无参构造函数
     * <p>
     * Title:
     * </p>
     * <p>
     * Description:
     * </p>
     */
    private NetClient() {
    };

    /**
     * 带构造参数的构造函数
     * <p>
     * Title:
     * </p>
     * <p>
     * Description:
     * </p>
     * 
     * @param config
     * @param listener
     */
    private NetClient(NetConfig config, INetListener listener) {
        synchronized (this) {
            this.listener = listener;
            this.config = config;
            // 新增异步初始化网络状态
            long start = System.currentTimeMillis();
            connection = NetConnection.getInstance(config, clinetNotify);
            logger.debug("\t>>>>> NetConnection.getInstance:"
                    + (System.currentTimeMillis() - start));
            if (null != connection) {
                long start2 = System.currentTimeMillis();
                if (null != listener) {
                    config.getServerNotify().setNetListener(listener);
                } else {
                    logger.debug("\t>>>>> config.ServerNotify not set listener");
                }
                logger.debug("\t>>>>> config.getServerNotify().setNetListener:"
                        + (System.currentTimeMillis() - start2));
                start2 = System.currentTimeMillis();
                connection.connect();
                logger.debug("\t>>>>> connection.connect:" + (System.currentTimeMillis() - start2));
                start2 = System.currentTimeMillis();
                serverBiz = ServerBiz.getInstance(connection, config.getServerBizNotify(),
                        config.getRequestTimeout());
                logger.debug("\t>>>>> serverBiz init:" + (System.currentTimeMillis() - start2));
            }
            logger.debug("\t>>>>> NetClient.init:" + (System.currentTimeMillis() - start));
        }
    }

    /**
     * 客户端网络类初始化方法
     * 
     * @param config
     * @param listener
     * @return
     */
    public static INetClient init(NetConfig config, INetListener listener) {
        if (null == instance) {
            instance = new NetClient(config, listener);
        }
        return instance;
    }

    /**
     * 开启连接
     */
    @Override
    public void connect() {
        if (null != connection) {
            connection.connect();
        }

    }

    /**
     * 立即连接网络
     */
    @Override
    public void reconnect() {
        if (null != connection) {
            connection.reconnect();
        }
    }

    /**
     * 关闭连接
     */
    @Override
    public void close() {
        if (null != connection || connection.isConnect()) {
            connection.close();
        }
    }

    /**
     * 是否联网
     * 
     * @return
     */
    @Override
    public boolean isConnect() {
        if (null != connection) {
            return connection.isConnect();
        }
        return false;
    }

    /**
     * @Description:监听网络状态
     */
    private static ClientNotify clinetNotify = new ClientNotify() {
        @Override
        public void netStatusNotify(Object value) {
            super.netStatusNotify(value);
        }
    };

    /**
     * 设置请求头
     * 
     * @param request
     * @param dstModuleId
     */
    private void setDstModuleId(AbstractEsbT2ASignal request, int dstModuleId) {
        request.setDstESBAddr((short) dstModuleId);
    }

    /** INetClient Implements **/

    /**
     * 根据用户名获取绑定的手机号码
     * 
     * @param username 用户名
     * @return
     */
    public NetGetMobileByUsernameResponse getMobileByUsername(String username) {
        NetGetMobileByUsernameResponse response = new NetGetMobileByUsernameResponse();
        GetUserBindedMobileRequest request = new GetUserBindedMobileRequest();
        request.setUsername(username);
        request.setSourceId(Constants.APP_SOURCE_ID);
        setDstModuleId(request, ESBAddrConstatns.PPA_ADDRESS);
        GetUserBindedMobileResponse resp = serverBiz.getMobileByUsername(request);
        if (null != resp) {
            response.setResult(resp.getResponseCode(), resp.getResponseMsg());
            switch (resp.getResponseCode()) {
                case 200:
                    // 成功
                    response.setMobile(resp.getMobile());
                    break;
            }
        } else {
            // 联网失败
            response.setNetError();
        }
        return response;
    }

    /**
     * 检查绑定状态
     */
    @Override
    public NetBindResponse getBind() {
        NetBindResponse response = new NetBindResponse();
        GetUserInfoByImsiRequest request = new GetUserInfoByImsiRequest();
        request.setSourceId(Constants.APP_SOURCE_ID);
        setDstModuleId(request, ESBAddrConstatns.PPA_ADDRESS);
        GetUserInfoByImsiResponse resp = serverBiz.checkBindStatus(request);
        if (null != resp) {
            response.setResult(resp.getResponseCode(), resp.getResponseMsg());
            switch (resp.getResponseCode()) {
                case 200:
                    response.setBind(BindStatus.BOUND);
                    response.setNickname(resp.getNickname());
                    response.setUsername(resp.getUsername());
                    response.setPersonNickName(resp.getPersionNickname());
                    response.setMobile(resp.getMobile());
                    response.setSkyid(resp.getSkyid());
                    break;
                case 140160:
                    response.setBind(BindStatus.UNBOUND);
                    response.setSuccess();
                    break;
            }
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 根据SKYID获取用户名
     * 
     * @param skyid
     * @return
     */
    @Override
    public NetGetUsernameResponse getUsernameBySkyid(int skyid) {
        NetGetUsernameResponse response = new NetGetUsernameResponse();
        GetUsernameReq request = new GetUsernameReq();
        request.setSkyid(skyid);
        setDstModuleId(request, ESBAddrConstatns.PPA_ADDRESS);
        GetUsernameResp resp = serverBiz.getUsername(request);
        if (null != resp) {
            response.setResult(resp.getResponseCode(), resp.getResponseMsg());
            switch (resp.getResponseCode()) {
                case 200:
                    response.setUsername(resp.getUserName());
                    break;
                case 110101:
                    // SKYID不存在
                    response.setSkyidOk(false);
                    break;
                case 110102:
                    // 用户名不存在
                    response.setUsernameOk(false);
                    break;
            }
        } else {
            // 联网失败
            response.setNetError();
        }
        return response;
    }

    /**
     * 绑定
     */
    @Override
    public NetBindResponse bind() {
        NetBindResponse response = new NetBindResponse();
        JudgeMobileStatusRequest request = new JudgeMobileStatusRequest();
        request.setSourceId(Constants.APP_SOURCE_ID);
        setDstModuleId(request, ESBAddrConstatns.PPA_ADDRESS);
        JudgeMobileStatusResponse resp = serverBiz.bind(request);
        if (null != resp) {
            switch (resp.getResponseCode()) {
                case 200:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    // 成功
                    response.setRecvsmsmobile(resp.getRecvsmsmobile());
                    response.setSmscontent(resp.getSmscontent());
                    response.setStatus(resp.getStatus());
                    if (resp.getStatus() == 3 || resp.getStatus() == 2) {
                        response.setBind(BindStatus.BOUND);
                    }
                    break;
                default:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
            }
        } else {
            // 联网失败
            response.setNetError();
        }
        return response;
    }

    /**
     * 快速注册
     */
    @Override
    public NetRegResponse qregister() {
        NetRegResponse response = new NetRegResponse();
        RegisterReq request = new RegisterReq();
        request.setSourceId(Constants.APP_SOURCE_ID);
        setDstModuleId(request, ESBAddrConstatns.PPA_ADDRESS);
        RegisterResp resp = serverBiz.register(request);
        if (null != resp) {
            switch (resp.getResponseCode()) {
                case 200:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    // 注册成功
                    response.setNickname(resp.getRegisterUserInfo().getNickname());
                    response.setPasswd(resp.getRegisterUserInfo().getPasswd());
                    response.setSkyId(resp.getRegisterUserInfo().getSkyId());
                    response.setToken(resp.getRegisterUserInfo().getToken());
                    response.setUsername(resp.getRegisterUserInfo().getUsername());
                    break;
                default:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
            }
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 注册前判读绑定关系
     * 
     * @return 返回注册响应对象
     */
    @Override
    public NetRegResponse beforeCheckReg() {
        NetRegResponse response = new NetRegResponse();
        RegisterReq request = new RegisterReq();
        request.setSourceId(Constants.APP_SOURCE_ID);
        // 注册后判断绑定关系参数 【1】
        request.setCheckMobile((byte) 1);
        setDstModuleId(request, ESBAddrConstatns.PPA_ADDRESS);
        RegisterResp resp = serverBiz.register(request);
        if (null != resp) {
            switch (resp.getResponseCode()) {
                case 200:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    // 注册成功
                    response.setNickname(resp.getRegisterUserInfo().getNickname());
                    response.setPasswd(resp.getRegisterUserInfo().getPasswd());
                    response.setSkyId(resp.getRegisterUserInfo().getSkyId());
                    response.setToken(resp.getRegisterUserInfo().getToken());
                    response.setUsername(resp.getRegisterUserInfo().getUsername());
                    response.setRecvsmsmobile(resp.getRecvsmsmobile());
                    response.setSmscontent(resp.getSmscontent());
                    response.setAutoLoginPwd(resp.getEncryptPasswd());
                    break;
                case 160105:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    response.setAllowReg(false);
                    break;
                default:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
            }
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 带用户名和密码的注册方法
     * 
     * @param username 用户名
     * @param pwd 明文密码
     * @return
     */
    @Override
    public NetRegResponse register(String username, String pwd) {
        NetRegResponse response = new NetRegResponse();
        RegisterReq request = new RegisterReq();
        request.setSourceId(Constants.APP_SOURCE_ID);
        request.setUsername(username);
        request.setPassword(SysUtils.pwdEncrypt(pwd));
        request.setCheckMobile((byte) 1);
        setDstModuleId(request, ESBAddrConstatns.PPA_ADDRESS);
        RegisterResp resp = serverBiz.register(request);
        if (null != resp) {
            switch (resp.getResponseCode()) {
                case 200:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    // 注册成功
                    response.setNickname(resp.getRegisterUserInfo().getNickname());
                    response.setPasswd(resp.getRegisterUserInfo().getPasswd());
                    response.setSkyId(resp.getRegisterUserInfo().getSkyId());
                    response.setToken(resp.getRegisterUserInfo().getToken());
                    response.setUsername(resp.getRegisterUserInfo().getUsername());
                    response.setRecvsmsmobile(resp.getRecvsmsmobile());
                    response.setSmscontent(resp.getSmscontent());
                    response.setAutoLoginPwd(resp.getEncryptPasswd());
                    break;
                case 110109:
                    // 用户名被阻止
                    response.setResult(resp.getResponseCode(), username + "被禁止激活,请输入其他帐号!");
                    response.setUserNameBan(true);
                    break;
                case 110110:
                    // 用户名已存在
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    response.setUserNameExists(true);
                    break;
                case 160105:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    response.setAllowReg(false);
                default:
                    response.setResult(resp.getResponseCode(), "系统忙,请稍后重试!");
                    break;
            }
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 登录
     */
    @Override
    public NetLoginResponse login(String username, String password) {
        NetLoginResponse response = new NetLoginResponse();
        LoginPhoneRequest request = new LoginPhoneRequest();
        request.setSourceId(Constants.APP_SOURCE_ID);
        request.setUsername(username);
        request.setEncryptPasswd(SysUtils.pwdEncrypt(password));
        setDstModuleId(request, ESBAddrConstatns.PPA_ADDRESS);
        LoginPhoneResponse resp = serverBiz.login(request);
        if (null != resp) {
            System.out.println("\t>>>>>> seqId:" + resp.getSeqid());
            switch (resp.getResponseCode()) {
                case 200:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    // 登录成功
                    response.setAuthCodeId(resp.getAuthCodeId());
                    response.setAuthCodeImg(resp.getAuthCodeImg());
                    response.setEncryptPasswd(resp.getEncryptPasswd());
                    response.setLastLogin(resp.getLastLogin());
                    response.setPasswd(resp.getPasswd());
                    response.setRecvsmsmobile(resp.getRecvsmsmobile());
                    response.setSecretQTag(resp.getSecretQTag());
                    response.setSkyId(resp.getSkyId());
                    response.setSmscontent(resp.getSmscontent());
                    response.setToken(resp.getToken());
                    if (null != resp.getUserInfo()) {
                        // 新增用户名
                        response.setUserName(resp.getUserInfo().getUsername());
                        response.setMobile(resp.getUserInfo().getMobile());
                    }
                    break;
                default:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
            }
        } else {
            // 联网失败
            response.setNetError();
        }
        return response;
    }

    /**
     * 登录
     * 
     * @param username 用户名
     * @param password 编码过的密码
     */
    @Override
    public NetLoginResponse login(String username, byte[] password) {
        NetLoginResponse response = new NetLoginResponse();
        LoginPhoneRequest request = new LoginPhoneRequest();
        request.setSourceId(Constants.APP_SOURCE_ID);
        request.setUsername(username);
        request.setEncryptPasswd(password);
        setDstModuleId(request, ESBAddrConstatns.PPA_ADDRESS);
        LoginPhoneResponse resp = serverBiz.login(request);
        if (null != resp) {
            switch (resp.getResponseCode()) {
                case 200:
                    response.setResult(200, resp.getResponseMsg());
                    // 登录成功
                    response.setAuthCodeId(resp.getAuthCodeId());
                    response.setAuthCodeImg(resp.getAuthCodeImg());
                    response.setEncryptPasswd(resp.getEncryptPasswd());
                    response.setLastLogin(resp.getLastLogin());
                    response.setPasswd(resp.getPasswd());
                    response.setRecvsmsmobile(resp.getRecvsmsmobile());
                    response.setSecretQTag(resp.getSecretQTag());
                    response.setSkyId(resp.getSkyId());
                    response.setSmscontent(resp.getSmscontent());
                    response.setToken(resp.getToken());
                    if (null != resp.getUserInfo()) {
                        // 新增用户名
                        response.setUserName(resp.getUserInfo().getUsername());
                        response.setMobile(resp.getUserInfo().getMobile());
                    }
                    break;
                default:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
            }
        } else {
            // 联网失败
            response.setNetError();
        }
        return response;
    }

    /**
     * 设置用户个性昵称
     */
    @Override
    public NetResponse setUserNickName(int skyid, String token, String nickName) {
        SetUserInfoReq request = new SetUserInfoReq();
        NetResponse response = new NetResponse();
        UserInfo user = new UserInfo();
        user.setSkyId(skyid);
        // user.setNickname(nickName);
        user.setPersonNickname(nickName);
        request.setSourceId(Constants.APP_SOURCE_ID);
        request.setUserInfo(user);
        request.setToken(token);
        setDstModuleId(request, ESBAddrConstatns.PPA_ADDRESS);
        SetUserInfoResp resp = serverBiz.setUserInfo(request);
        if (null != resp) {
            response.setResult(resp.getResponseCode(), resp.getResponseMsg());
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 注销
     */
    @Override
    public NetResponse logout(String token) {
        NetResponse response = new NetResponse();
        LogoutRequest request = new LogoutRequest();
        request.setSourceId(Constants.APP_SOURCE_ID);
        request.setToken(token);
        request.setSource(Constants.APP_SOURCE);
        setDstModuleId(request, ESBAddrConstatns.PPA_ADDRESS);
        LogoutResponse resp = serverBiz.logout(request);
        if (null != resp) {
            response.setResult(resp.getResponseCode(), resp.getResponseMsg());
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 获取用户信息
     */
    @Override
    public NetUserInfoResponse getUserInfo(String token) {
        GetUserInfoWithTokenReq request = new GetUserInfoWithTokenReq();
        NetUserInfoResponse response = new NetUserInfoResponse();
        request.setSourceId(Constants.APP_SOURCE_ID);
        request.setSkyid(0);
        request.setToken(token);
        request.setLoginTag(0);
        setDstModuleId(request, ESBAddrConstatns.PPA_ADDRESS);
        GetUserInfoWithTokenResp resp = serverBiz.getUserInfo(request);
        if (null != resp) {
            switch (resp.getResponseCode()) {
                case 200:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    // 设置成功
                    NetUserInfo nui = new NetUserInfo();
                    nui.setSkyId(resp.getSkyId());
                    nui.setUserName(resp.getUserName());
                    nui.setNickname(resp.getNickname());
                    nui.setUemail(resp.getEmail());
                    nui.setUrealname(resp.getUrealname());
                    nui.setUsex(resp.getUsex());
                    nui.setUbirthday(resp.getUbirthday());
                    nui.setUanimals(resp.getUanimals());
                    nui.setUstar(resp.getUstar());
                    nui.setUblood(resp.getUblood());
                    nui.setUmarried(resp.getUmarried());
                    nui.setUportraitid(resp.getUportraitid());
                    nui.setUdefineportrait(resp.getUdefineportrait());
                    nui.setUcountry(resp.getUcountry());
                    nui.setUprovince(resp.getUprovince());
                    nui.setUcity(resp.getUcity());
                    nui.setUhometown(resp.getUhometown());
                    nui.setUsignature(resp.getUsignature());
                    nui.setUdesc(resp.getUdesc());
                    nui.setUemail(resp.getEmail());
                    nui.setUemailchecked(resp.getUemailchecked());
                    nui.setUmobile(resp.getUmobile());
                    nui.setUtelephone(resp.getUtelephone());
                    nui.setUvocation(resp.getUvocation());
                    nui.setUschoolgraduated(resp.getUschoolgraduated());
                    nui.setUprivacy(resp.getUprivacy());
                    nui.setIdcardno(resp.getIdcardno());
                    nui.setUindustry(resp.getUindustry());
                    nui.setUhobbies(resp.getUhobbies());
                    nui.setUcorporation(resp.getUcorporation());
                    nui.setPersonnickname(resp.getPersonnickname());

                    // 手信不存在的部分
                    nui.setHaspic(resp.getHaspic());
                    nui.setUlatitude(resp.getUlatitude());
                    nui.setUlongitude(resp.getUlongitude());
                    nui.setUmobile(resp.getUmobile());
                    nui.setUserName(resp.getUserName());
                    nui.setUuidPortrait(resp.getUuidPortrait());
                    response.setUserInfo(nui);
                    break;
                default:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
            }
        } else {
            // 联网失败
            response.setNetError();
        }
        return response;
    }

    /**
     * 设置用户信息
     */
    @Override
    public NetResponse setUserInfo(String token, NetUserInfo nui) {
        SxSetUserinfoReq request = new SxSetUserinfoReq();
        NetResponse response = new NetResponse();
        request.setToken(token);
        setDstModuleId(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
        UserInfo ui = new UserInfo();

        ui.setSkyId(nui.getSkyId());
        ui.setUserName(nui.getUserName());
        ui.setUemail(nui.getUemail());
        ui.setUrealname(nui.getUrealname());
        ui.setUsex(nui.getUsex());
        ui.setUbirthday(nui.getUbirthday());
        ui.setUanimals(nui.getUanimals());
        ui.setUstar(nui.getUstar());
        ui.setUblood(nui.getUblood());
        ui.setUmarried(nui.getUmarried());
        ui.setUportraitid(nui.getUportraitid());
        ui.setUdefineportrait(nui.getUdefineportrait());
        ui.setUcountry(nui.getUcountry());
        ui.setUprovince(nui.getUprovince());
        ui.setUcity(nui.getUcity());
        ui.setUhometown(nui.getUhometown());
        ui.setUsignature(nui.getUsignature());
        ui.setUdesc(nui.getUdesc());
        ui.setUemailchecked(nui.getUemailchecked());
        ui.setUmobile(nui.getUmobile());
        ui.setUtelephone(nui.getUtelephone());
        ui.setUvocation(nui.getUvocation());
        ui.setUschoolgraduated(nui.getUschoolgraduated());
        ui.setUprivacy(nui.getUprivacy());
        ui.setIdcardno(nui.getIdcardno());
        ui.setUindustry(nui.getUindustry());
        ui.setUhobbies(nui.getUhobbies());
        ui.setUcorporation(nui.getUcorporation());
        ui.setPersonNickname(nui.getPersonnickname());
        // 设置UUID的形式个性头像
        ui.setUuidPortrait(nui.getUuidPortrait());
        request.setUserinfo(ui);

        SxSetUserinfoResp resp = serverBiz.setUserInfo(request);
        if (null != resp) {
            switch (resp.getResponseCode()) {
                case 200:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
                default:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
            }
        } else {
            // 联网失败
            response.setNetError();
        }
        return response;
    }

    /**
     * 发送消息
     */
    @Override
    public NetChatResponse sendChatMsg(NetChatRequest chat) {
        SxSendChatMsgReq request = new SxSendChatMsgReq();
        NetChatResponse response = new NetChatResponse();
        request.setSourceId(Constants.APP_SOURCE_ID);
        request.setDestSkyids(chat.getDestSkyids());
        request.setMsgContent(chat.getMsgContent());
        request.setNickName(chat.getNickName());
        if (chat.getAudioLen() > 0 && chat.getAudioSize() > 0 && null != chat.getFormat()
                && null != chat.getMd5()) {
            Audio audio = new Audio();
            audio.setAudioLen(chat.getAudioLen());
            audio.setAudioSize(chat.getAudioSize());
            audio.setFormat(chat.getFormat());
            audio.setMd5(chat.getMd5());
            request.setAudio(audio);
        }
        if (null != chat.getTalkReason() && !chat.getTalkReason().equals("")) {
            request.setTalkReason(chat.getTalkReason());
        }
        setDstModuleId(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
        SxSendChatMsgResp resp = serverBiz.sendChatMessage(request);
        if (null != resp) {
            // TODO 现在改为只要服务端返回响应，就认为这个是正常的。至于离线消息提示，目前暂不考虑
            switch (resp.getResponseCode()) {
                case 200:
                    // 判断离线消息内容提示是否为空，如果不为空，则表明该消息将以离线消息形式发送
                    if (null != resp.getOfflineMsg()) {
                        response.setResult(200, resp.getOfflineMsg());
                    } else {
                        response.setResult(200, resp.getResponseMsg());
                    }

                    // 普通消息判断当前用户在线状态
                    if (resp.getNextRespCode() == 614) {
                        // 对方已离线
                        response.setUserOnline(false);
                    }

                    // 正对当前会话是快聊会话时
                    if (resp.getNextRespCode() == 615) {
                        // 对方已离开
                        response.setFastChatLeave(true);
                    }
                    break;
                default:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
            }
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 修改用户密码
     */
    @Override
    public NetmodifyPwdResponse modifyPwd(String token, String oldPwd, String newPwd) {
        ModifyPwdRequest request = new ModifyPwdRequest();
        NetmodifyPwdResponse response = new NetmodifyPwdResponse();
        request.setEncryptNewPasswd(SysUtils.pwdEncrypt(newPwd));
        request.setEncryptOldPasswd(SysUtils.pwdEncrypt(oldPwd));
        request.setToken(token);
        setDstModuleId(request, ESBAddrConstatns.PPA_ADDRESS);
        ModifyPwdResponse resp = serverBiz.modifyPwd(request);
        if (null != resp) {
            switch (resp.getResponseCode()) {
                case 200:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    response.setEncryptPasswd(resp.getEncryptpasswd());
                    break;
                default:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
            }
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 忘记密码
     */
    @Override
    public NetForgetPwdResponse forgetPwd(String username, String newPwd) {
        GetMessage4SetPasswdReq request = new GetMessage4SetPasswdReq();
        NetForgetPwdResponse response = new NetForgetPwdResponse();
        request.setUsername(username);
        if (null != newPwd && !"".equals(newPwd)) {
            request.setEncryptPasswd(SysUtils.pwdEncrypt(newPwd));
        }
        setDstModuleId(request, ESBAddrConstatns.PPA_ADDRESS);
        GetMessage4SetPasswdResp resp = serverBiz.forgetPwd(request);
        if (null != resp) {
            switch (resp.getResponseCode()) {
                case 200:
                    // 成功
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    response.setRecvsmsmobile(resp.getRecvsmsmobile());
                    response.setSmscontent(resp.getSmscontent());
                    break;
                default:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
            }
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 获取指定联系人状态
     * 
     * @param contactSkyids
     * @param contactPhones
     * @return
     */
    @Override
    public NetSpecifiedContactsStatusResponse getSpecifiedContactsStatus(String contactSkyids,
            String contactPhones, int contactsId) {
        NetSpecifiedContactsStatusResponse response = new NetSpecifiedContactsStatusResponse();
        SxGetSpecifiedContactsStatusReq request = new SxGetSpecifiedContactsStatusReq();
        request.setContactSkyids(contactSkyids);
        request.setContactPhones(contactPhones);
        request.setContactId(contactsId);
        setDstModuleId(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
        SxGetSpecifiedContactsStatusResp resp = serverBiz.getSpecifiedContactsStatus(request);
        if (null != resp) {
            switch (resp.getResponseCode()) {
                case 200:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    // 成功
                    response.setItems(resp.getContactsStatusItem());
                    response.setTotalSize(resp.getTotalSize());
                    response.setUpdateTime(resp.getUpdateTime());
                    break;
                default:
                    if (resp.getResponseCode() == 502) {
                        response.setResult(resp.getResponseCode(), "您还没有登录，请登录!");
                    } else {
                        response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    }
                    break;
            }
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 获取联系人版本
     * 
     * @return
     */
    @Override
    public NetContactsVersionResponse getContactsVersion() {
        NetContactsVersionResponse response = new NetContactsVersionResponse();
        SxGetUpdateTimeReq request = new SxGetUpdateTimeReq();
        setDstModuleId(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
        SxGetUpdateTimeResp resp = serverBiz.getContactVersion(request);
        if (null != resp) {
            switch (resp.getResponseCode()) {
                case 200:
                    // 成功
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    response.setUpdateTime(resp.getUpdateTime());
                    break;
                default:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
            }
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 获取好友的详细信息
     * 
     * @param skyid
     * @return
     */
    @Override
    public NetUserInfoResponse getBuddyUserInfo(int skyid) {
        NetUserInfoResponse response = new NetUserInfoResponse();
        SxGetUserInfoReq request = new SxGetUserInfoReq();
        request.setBuddyId(skyid);
        setDstModuleId(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
        SxGetUserInfoResp resp = serverBiz.getUserInfo(request);
        if (null != resp) {
            UserInfo userInfo = resp.getUserinfo();
            if (null != userInfo) {
                switch (resp.getResponseCode()) {
                    case 200:
                        response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                        // 成功
                        NetUserInfo nui = new NetUserInfo();
                        nui.setSkyId(userInfo.getSkyId());
                        nui.setUserName(userInfo.getUserName());
                        nui.setNickname(userInfo.getNickname());
                        nui.setUemail(userInfo.getUemail());
                        nui.setUrealname(userInfo.getUrealname());
                        nui.setUsex(userInfo.getUsex());
                        nui.setUbirthday(userInfo.getUbirthday());
                        nui.setUanimals(userInfo.getUanimals());
                        nui.setUstar(userInfo.getUstar());
                        nui.setUblood(userInfo.getUblood());
                        nui.setUmarried(userInfo.getUmarried());
                        nui.setUportraitid(userInfo.getUportraitid());
                        nui.setUdefineportrait(userInfo.getUdefineportrait());
                        nui.setUcountry(userInfo.getUcountry());
                        nui.setUprovince(userInfo.getUprovince());
                        nui.setUcity(userInfo.getUcity());
                        nui.setUhometown(userInfo.getUhometown());
                        nui.setUsignature(userInfo.getUsignature());
                        nui.setUdesc(userInfo.getUdesc());
                        nui.setUemailchecked(userInfo.getUemailchecked());
                        nui.setUmobile(userInfo.getUmobile());
                        nui.setUtelephone(userInfo.getUtelephone());
                        nui.setUvocation(userInfo.getUvocation());
                        nui.setUschoolgraduated(userInfo.getUschoolgraduated());
                        nui.setUprivacy(userInfo.getUprivacy());
                        nui.setIdcardno(userInfo.getIdcardno());
                        nui.setUindustry(userInfo.getUindustry());
                        nui.setUhobbies(userInfo.getUhobbies());
                        nui.setUcorporation(userInfo.getUcorporation());
                        if (null != userInfo.getPersonNickname()
                                && !"".equals(userInfo.getPersonNickname())) {
                            nui.setPersonnickname(userInfo.getPersonNickname());
                        } else {
                            nui.setPersonnickname("");
                        }

                        // 手信不存在的部分
                        nui.setHaspic(userInfo.getHaspic());
                        nui.setUlatitude(userInfo.getUlatitude());
                        nui.setUlongitude(userInfo.getUlongitude());
                        nui.setUmobile(userInfo.getUmobile());
                        nui.setUserName(userInfo.getUserName());
                        // 新增UUID形式的自定义头像字段数据
                        nui.setUuidPortrait(userInfo.getUuidPortrait());
                        response.setUserInfo(nui);
                        response.setStatus(resp.getStatus());
                        break;
                    default:
                        if (resp.getResponseCode() == 603) {
                            NetUserInfo nui2 = new NetUserInfo();
                            if (userInfo.getSkyId() > 0) {
                                nui2.setSkyId(userInfo.getSkyId());
                            }
                            if (null != userInfo.getUserName()
                                    && !"".equals(userInfo.getUserName())) {
                                nui2.setUserName(userInfo.getUserName());
                            }
                            if (null != userInfo.getNickname()
                                    && !"".equals(userInfo.getNickname())) {
                                nui2.setNickname(userInfo.getNickname());
                            }
                            response.setUserInfo(nui2);
                            response.setResult(200, "你在他黑名单中");
                            response.setBlack(true);
                        } else {
                            response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                        }
                        break;
                }
            }
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 设置是否推荐
     * 
     * @param isRecommend 是否推荐给其他用户
     * @param hideLBS LBS是否可见
     * @return
     */
    @Override
    public NetResponse setRecommend(boolean isRecommend, boolean hideLBS) {
        NetResponse response = new NetResponse();
        SxSetRecommendedReq request = new SxSetRecommendedReq();
        // 是否推荐给其他用户
        if (isRecommend) {
            request.setRecommended((byte) 1);
        } else {
            request.setRecommended((byte) 0);
        }
        // 是否隐藏LBS信息
        if (hideLBS) {
            request.setHideLbs((byte) 1);
        } else {
            request.setHideLbs((byte) 0);
        }
        setDstModuleId(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
        SxSetRecommendedResp resp = serverBiz.setRecommended(request);
        if (null != resp) {
            switch (resp.getResponseCode()) {
                case 200:
                    response.setResult(200, resp.getResponseMsg());
                    break;
                default:
                    if (resp.getResponseCode() == 502) {
                        // TODO 未登录
                    }
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
            }
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 设置是否推荐
     * 
     * @return
     */
    @Override
    public NetGetSetRecommendResponse getSetRecommendValue() {
        NetGetSetRecommendResponse response = new NetGetSetRecommendResponse();
        SxGetRecommendedReq request = new SxGetRecommendedReq();
        setDstModuleId(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
        SxGetRecommendedResp resp = serverBiz.getSetRecommendedValue(request);
        if (null != resp) {
            switch (resp.getResponseCode()) {
                case 200:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    // 获取是否推荐数据
                    switch (resp.getRecommended()) {
                        case 0:
                            response.setRecommend(false);
                            break;
                        case 1:
                            response.setRecommend(true);
                            break;
                    }
                    // 获取是否隐藏LBS数据
                    switch (resp.getHideLBS()) {
                        case 0:
                            response.setHideLBS(false);
                            break;
                        case 1:
                            response.setHideLBS(true);
                            break;
                    }
                    break;
                default:
                    if (resp.getResponseCode() == 502) {
                        response.setLogin(false);
                    }
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
            }
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 用户反馈接口
     * 
     * @param nickName
     * @param content
     * @return
     */
    @Override
    public NetResponse feedBack(String nickName, String content) {
        NetResponse response = new NetResponse();
        SxAddFeedBackReq request = new SxAddFeedBackReq();
        request.setNickname(nickName);
        request.setFeedContent(content);
        setDstModuleId(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
        SxAddFeedBackResp resp = serverBiz.addFeedBack(request);
        if (null != resp) {
            response.setResult(resp.getResponseCode(), resp.getResponseMsg());
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 添加黑名单
     * 
     * @param contactId
     * @param destSkyid
     * @return
     */
    @Override
    public NetBlackResponse addBlackList(int contactId, int destSkyid) {
        NetBlackResponse response = new NetBlackResponse();
        SxAddBlackReq request = new SxAddBlackReq();
        request.setContactId(contactId);
        request.setDestSkyid(destSkyid);
        setDstModuleId(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
        SxAddBlackResp resp = serverBiz.addBlackList(request);
        if (null != resp) {
            switch (resp.getResponseCode()) {
                case 200:
                    response.setResult(200, resp.getResponseMsg());
                    response.setUpdateTime(resp.getUpdateTime());
                    break;
                default:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
            }
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 从黑名单列表中删除
     * 
     * @param contactId
     * @param destSkyid
     * @return
     */
    @Override
    public NetBlackResponse delBlackList(int contactId, int destSkyid) {
        NetBlackResponse response = new NetBlackResponse();
        SxDelBlackReq request = new SxDelBlackReq();
        request.setContactId(contactId);
        request.setDestSkyid(destSkyid);
        setDstModuleId(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
        SxDelBlackResp resp = serverBiz.delBlackList(request);
        if (null != resp) {
            switch (resp.getResponseCode()) {
                case 200:
                    response.setResult(200, resp.getResponseMsg());
                    response.setUpdateTime(resp.getUpdateTime());
                    break;
                default:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
            }
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 获取推荐好友列表
     * 
     * @param start
     * @param pageSize
     * @return
     */
    @Override
    public NetGetFriendstResponse getFriendsList(int start, int pageSize, long updateTime) {
        NetGetFriendstResponse response = new NetGetFriendstResponse();
        SxGetFriendsReq request = new SxGetFriendsReq();
        request.setStart(start);
        request.setPageSize(pageSize);
        request.setUpdateTime(updateTime);
        setDstModuleId(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
        SxGetFriendsResp resp = serverBiz.getRecommendFriendsList(request);
        if (null != resp) {
            switch (resp.getResponseCode()) {
                case 200:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    response.setFiendList(resp.getFiendList());
                    response.setStart(resp.getStart());
                    response.setTotalSize(resp.getTotalSize());
                    response.setUpdateTime(resp.getUpdateTime());
                    break;
                case 607:
                    response.setResult(200, resp.getResponseMsg());
                    response.setUpdate(false);
                    break;
                default:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
            }
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 计算推荐联系人
     * 
     * @return NetCalcFriendsResponse
     */
    @Override
    public NetCalcFriendsResponse calcFriends(String token) {
        NetCalcFriendsResponse response = new NetCalcFriendsResponse();
        SxCalcFriendsReq request = new SxCalcFriendsReq();
        request.setToken(token);
        setDstModuleId(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
        SxCalcFriendsResp resp = serverBiz.calcFriendsList(request);
        if (null != resp) {
            switch (resp.getResponseCode()) {
                case 200:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
                default:
                    if (resp.getResponseCode() == 504) {
                        response.setAuth(false);
                    }
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
            }
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 获取指定联系人与登录用户的推荐关系
     * 
     * @param destSkyid
     * @param token
     * @return
     */
    @Override
    public NetCalcFriendsResponse getRecommendRelation(int destSkyid, String token) {
        NetCalcFriendsResponse response = new NetCalcFriendsResponse();
        SxCalcFriendsReq request = new SxCalcFriendsReq();
        if (destSkyid > 0) {
            request.setDestSkyid(destSkyid);
        }
        request.setToken(token);
        setDstModuleId(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
        SxCalcFriendsResp resp = serverBiz.getRecommendRelation(request);
        if (null != resp) {
            switch (resp.getResponseCode()) {
                case 200:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    response.setDestSkyid(resp.getDestSkyid());
                    response.setTalkReason(resp.getTalkReason());
                    break;
                default:
                    if (resp.getResponseCode() == 504) {
                        response.setAuth(false);
                    }
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
            }
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 批量操作联系人
     * 
     * @param ContactsList
     * @return
     */
    @Override
    public NetOperateContactsResponse operateContacts(ArrayList<NetContacts> contactsList) {
        NetOperateContactsResponse response = new NetOperateContactsResponse();
        if (null != contactsList && contactsList.size() > 0) {
            SxOperateContactsReq request = new SxOperateContactsReq();
            Contacts con = null;
            ArrayList<Contacts> clist = new ArrayList<Contacts>();
            for (NetContacts ncts : contactsList) {
                con = new Contacts();
                con.setAction(ncts.getAction());
                if (null != ncts.getContactId() && ncts.getContactId() > 0) {
                    con.setContactId(ncts.getContactId());
                }
                con.setContactName(ncts.getContactName());
                con.setContactType(ncts.getContactType());
                con.setSequenceId(ncts.getSequenceId());
                con.setMemo(ncts.getMemo());
                con.setGroup(ncts.getGroup());
                if (null != ncts.getPhoneList() && ncts.getPhoneList().size() > 0) {
                    ArrayList<Phone> phoneList = new ArrayList<Phone>();
                    Phone p = null;
                    for (NetContactsPhone phone : ncts.getPhoneList()) {
                        p = new Phone();
                        p.setBuddyId(phone.getBuddyId());
                        p.setIndex(phone.getIndex());
                        p.setNickname(phone.getNickname());
                        p.setPhone(phone.getPhone());
                        /**
                         * try { BeanUtils.copyProperties(p, phone); } catch
                         * (IllegalAccessException e) { } catch
                         * (InvocationTargetException e) { }
                         **/
                        phoneList.add(p);
                    }
                    con.setPhoneList(phoneList);
                }
                clist.add(con);
            }
            request.setContactsList(clist);
            setDstModuleId(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
            SxOperateContactsResp resp = serverBiz.uploadContactsList(request);
            if (null != resp) {
                switch (resp.getResponseCode()) {
                    case 200:
                        response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                        if (null != resp.getResultInfo() && resp.getResultInfo().size() > 0) {
                            ArrayList<NetContactsResultInfo> resultList = new ArrayList<NetContactsResultInfo>();
                            NetContactsResultInfo nri = null;
                            for (ResultInfo rInfo : resp.getResultInfo()) {
                                nri = new NetContactsResultInfo();
                                nri.setAction(rInfo.getAction());
                                nri.setCode(rInfo.getCode());
                                nri.setContactId(rInfo.getContactId());
                                nri.setSequenceId(rInfo.getSequenceId());
                                /**
                                 * try { BeanUtils.copyProperties(nri, rInfo); }
                                 * catch (IllegalAccessException e) { } catch
                                 * (InvocationTargetException e) { }
                                 **/
                                resultList.add(nri);
                            }
                            response.setResultInfo(resultList);
                        }
                        response.setUpdateTime(resp.getUpdateTime());
                        break;
                    default:
                        response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                        break;
                }
            } else {
                response.setNetError();
            }
        } else {
            response.setResult(-1, "请选择需要操作的联系人!");
        }
        return response;
    }

    /**
     * 增量同步联系人
     * 
     * @param updateTime
     * @param start
     * @param pageSize
     * @return
     */
    @Override
    public NetSyncContactsResponse syncContacts(long updateTime, int start, int pageSize) {
        NetSyncContactsResponse response = new NetSyncContactsResponse();
        SxSyncContactsReq request = new SxSyncContactsReq();
        request.setUpdateTime(updateTime);
        request.setStart(start);
        request.setPageSize(pageSize);
        setDstModuleId(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
        SxSyncContactsResp resp = serverBiz.synContacts(request);
        if (null != resp) {
            logger.debug("\t>>>> syncContacts:" + resp.getResponseCode() + "|"
                    + resp.getResponseMsg());
            switch (resp.getResponseCode()) {
                case 607:
                case 200:
                    response.setResult(200, resp.getResponseMsg());
                    if (resp.getResponseCode() == 607) {
                        // 没有更新
                        response.setHasUpdate(false);
                    } else {
                        response.setUpdateTime(resp.getUpdateTime());
                        if (null != resp.getContactsList() && resp.getContactsList().size() > 0) {
                            ArrayList<NetContacts> contactList = new ArrayList<NetContacts>();
                            NetContacts nContacts = null;
                            for (Contacts contacts : resp.getContactsList()) {
                                nContacts = new NetContacts();
                                nContacts.setContactId(contacts.getContactId());
                                nContacts.setContactName(contacts.getContactName());
                                nContacts.setContactType(contacts.getContactType());
                                nContacts.setSequenceId(contacts.getSequenceId());
                                nContacts.setGroup(contacts.getGroup());
                                nContacts.setMemo(contacts.getMemo());
                                nContacts.setAction(contacts.getAction());
                                if (null != contacts.getPhoneList()
                                            && contacts.getPhoneList().size() > 0) {
                                    ArrayList<NetContactsPhone> phoneList = new ArrayList<NetContactsPhone>();
                                    NetContactsPhone nphone = null;
                                    for (Phone phone : contacts.getPhoneList()) {
                                        nphone = new NetContactsPhone();
                                        nphone.setBuddyId(phone.getBuddyId());
                                        nphone.setIndex(phone.getIndex());
                                        nphone.setNickname(phone.getNickname());
                                        nphone.setPhone(phone.getPhone());
                                        phoneList.add(nphone);
                                    }
                                    nContacts.setPhoneList(phoneList);
                                }
                                contactList.add(nContacts);
                            }
                            response.setContactsList(contactList);
                        }
                        response.setTotalSize(resp.getTotalSize());
                        response.setStart(resp.getStart());
                    }
                    break;
                default:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
            }
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 添加联系人
     * 
     * @param destSkyid
     * @param contacType
     * @return
     */
    @Override
    public NetAddFriendResponse addFriend(int destSkyid, byte contacType) {
        NetAddFriendResponse response = new NetAddFriendResponse();
        SxAddFriendReq request = new SxAddFriendReq();
        request.setDestSkyid(destSkyid);
        request.setContactType(contacType);
        setDstModuleId(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
        SxAddFriendResp resp = serverBiz.addFriends(request);
        if (null != resp) {
            switch (resp.getResponseCode()) {
                case 200:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    response.setUpdateTime(resp.getUpdateTime());
                    response.setContactId(resp.getContactId());
                    break;
                default:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    if (resp.getResponseCode() == 605) {
                        // 被添加人已经存在与你的好友列表中
                        response.setFriendExists(true);
                    }
                    break;
            }
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 获取推荐短信
     * 
     * @param msgType
     * @param start 起始页数
     * @param pageSize 每页数量
     * @return
     */
    @Override
    public NetGetRecommendedMsgResponse getRecommendedMsg(int msgType, int start, int pageSize) {
        NetGetRecommendedMsgResponse response = new NetGetRecommendedMsgResponse();
        SxGetRecommendMsgReq request = new SxGetRecommendMsgReq();
        request.setMsgTypeId(msgType);
        request.setStart(start);
        request.setPageSize(pageSize);
        setDstModuleId(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
        SxGetRecommendMsgResp resp = serverBiz.getRecommendMsgList(request);
        if (null != resp) {
            switch (resp.getResponseCode()) {
                case 607:
                case 200:
                    response.setResult(200, resp.getResponseMsg());
                    if (resp.getResponseCode() == 200) {
                        response.setMsgTypeId(resp.getMsgTypeId());
                        response.setMsgTypeList(resp.getMsgTypeList());
                        response.setTotalSize(resp.getTotalSize());
                        response.setStart(resp.getStart());
                        response.setTextMessage(resp.getRecommendMsg());
                    } else {
                        response.setHasUpdate(false);
                    }
                    break;
                default:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
            }
        } else {
            response.setNetError();
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
    public NetGetRecommendedMsgTypeResponse getRecommendMsgType(long updateTime) {
        NetGetRecommendedMsgTypeResponse response = new NetGetRecommendedMsgTypeResponse();
        SxGetRecommendMsgTypeReq request = new SxGetRecommendMsgTypeReq();
        request.setUpdateTime(updateTime);
        setDstModuleId(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
        SxGetRecommendMsgTypeResp resp = serverBiz.getRecommendMsgType(request);
        if (null != resp) {
            switch (resp.getResponseCode()) {
                case 607:
                case 200:
                    response.setResult(200, resp.getResponseMsg());
                    if (resp.getResponseCode() == 200) {
                        response.setMsgTypeList(resp.getMsgTypeList());
                        response.setUpdateTime(resp.getUpdateTime());
                    } else {
                        response.setHasUpdate(false);
                    }
                    break;
                default:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
            }
        } else {
            response.setNetError();
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
    public NetGetRecommendedMsgNewResponse getRecommendMsgNew(int msgType, long updateTime,
            int start, int pageSize, int capacity) {
        NetGetRecommendedMsgNewResponse response = new NetGetRecommendedMsgNewResponse();
        SxGetRecommendMsgByUpdateTimeReq request = new SxGetRecommendMsgByUpdateTimeReq();
        request.setMsgTypeId(msgType);
        request.setUpdateTime(updateTime);
        request.setStart(start);
        request.setPageSize(pageSize);
        if (capacity <= 0) {
            request.setCapacity(pageSize);
        } else {
            request.setCapacity(capacity);
        }
        setDstModuleId(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
        SxGetRecommendMsgByUpdateTimeResp resp = serverBiz.getRecommendMsgNew(request);
        if (null != resp) {
            switch (resp.getResponseCode()) {
                case 607:
                case 200:
                    response.setResult(200, resp.getResponseMsg());
                    if (resp.getResponseCode() == 200) {
                        response.setUpdateTime(resp.getUpdateTime());
                        response.setTextMessage(resp.getRecommendMsg());
                        response.setTotalSize(resp.getTotalSize());
                        response.setStart(resp.getStart());
                    } else {
                        response.setHasUpdate(false);
                    }
                    break;
                default:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
            }
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 获取联系人列表
     * 
     * @param update
     * @param start 起始页数
     * @param pageSize 每页数量
     * @return
     */
    @Override
    public NetGetContactsListResponse getContactsList(long updateTime, int start, int pageSize) {
        NetGetContactsListResponse response = new NetGetContactsListResponse();
        SxGetContactsListReq request = new SxGetContactsListReq();
        request.setUpdateTime(updateTime);
        request.setStart(start);
        request.setPageSize(pageSize);
        setDstModuleId(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
        SxGetContactsListResp resp = serverBiz.getContactsList(request);
        if (null != resp) {
            switch (resp.getResponseCode()) {
                case 607:
                case 200:
                    response.setResult(200, resp.getResponseMsg());
                    if (resp.getResponseCode() == 607) {
                        // 无更新版本
                        response.setHasUpdate(false);
                    } else {
                        if (null != resp.getContactsList() && resp.getContactsList().size() > 0) {
                            ArrayList<NetContacts> contactList = new ArrayList<NetContacts>();
                            NetContacts nContacts = null;
                            for (Contacts contacts : resp.getContactsList()) {
                                nContacts = new NetContacts();
                                nContacts.setContactId(contacts.getContactId());
                                nContacts.setContactName(contacts.getContactName());
                                nContacts.setContactType(contacts.getContactType());
                                nContacts.setSequenceId(contacts.getSequenceId());
                                nContacts.setGroup(contacts.getGroup());
                                nContacts.setMemo(contacts.getMemo());
                                nContacts.setAction(contacts.getAction());
                                if (null != contacts.getPhoneList()
                                        && contacts.getPhoneList().size() > 0) {
                                    ArrayList<NetContactsPhone> phoneList = new ArrayList<NetContactsPhone>();
                                    NetContactsPhone nphone = null;
                                    for (Phone phone : contacts.getPhoneList()) {
                                        nphone = new NetContactsPhone();
                                        nphone.setBuddyId(phone.getBuddyId());
                                        nphone.setIndex(phone.getIndex());
                                        nphone.setNickname(phone.getNickname());
                                        nphone.setPhone(phone.getPhone());
                                        phoneList.add(nphone);
                                    }
                                    nContacts.setPhoneList(phoneList);
                                }
                                contactList.add(nContacts);
                            }
                            response.setContactsList(contactList);
                        }
                        response.setStart(resp.getStart());
                        response.setTotalSize(resp.getTotalSize());
                        response.setUpdateTime(resp.getUpdateTime());
                    }
                    break;
                default:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
            }
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 新获取联系人列表
     * 
     * @param start 起始页数
     * @param pageSize 每页数量
     * @param fetch 0：全部，默认；1：指定人
     * @param contactId 联系人ID
     * @return
     */
    @Override
    public NetGetContactsList2Response getContactsList(int start, int pageSize, int fetchFlag,
            int contactId) {
        NetGetContactsList2Response response = new NetGetContactsList2Response();
        SxGetSimpleUserInfoReq request = new SxGetSimpleUserInfoReq();
        request.setStart(start);
        request.setPageSize(pageSize);
        request.setFetchFlag((byte) fetchFlag);
        if (fetchFlag == 1 && contactId > 0) {
            request.setContactId(contactId);
        }
        setDstModuleId(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
        SxGetSimpleUserInfoResp resp = serverBiz.getContactsList(request);
        if (null != resp) {
            switch (resp.getResponseCode()) {
                case 200:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    response.setStart(resp.getStart());
                    response.setTotalSize(resp.getTotalSize());
                    response.setContactsList(resp.getSimpleUserItem());
                    break;
                default:
                    if (resp.getResponseCode() == 502) {
                        response.setResult(resp.getResponseCode(), "您还没有登录，请登录先!");
                    } else {
                        response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    }
                    break;
            }
        }
        return response;
    }

    /**
     * 获取黑名单列表接口
     * 
     * @param start 起始页数
     * @param pageSize 每页数量
     * @param fetchFlag 过滤类型[0：全部；1：陌生人；1：联系人]
     * @return
     */
    @Override
    public NetGetBlakListResponse getBlackList(int start, int pageSize, int fetchFlag) {
        NetGetBlakListResponse response = new NetGetBlakListResponse();
        SxGetBlackListReq request = new SxGetBlackListReq();
        request.setStart(start);
        request.setPageSize(pageSize);
        request.setFetchFlag((byte) fetchFlag);
        setDstModuleId(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
        SxGetBlackListResp resp = serverBiz.getBlackList(request);
        if (null != resp) {
            switch (resp.getResponseCode()) {
                case 200:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    if (null != resp.getBlackList() && resp.getBlackList().size() > 0) {
                        response.setBlackList(resp.getBlackList());
                    }
                    response.setStart(resp.getStart());
                    response.setTotalSize(resp.getTotalSize());
                    break;
                default:
                    // responseCode == 502 标识用户没有登录
                    if (resp.getResponseCode() == 502) {
                        response.setLogin(false);
                    }
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
            }
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 获取用户在线状态
     * 
     * @param destSkyids 目标用户的skid，多个skyid之间用,(英语键盘的逗号)分割
     * @return
     */
    @Override
    public NetOnlineStatusResponse getOnlineStatus(String destSkyids) {
        NetOnlineStatusResponse response = new NetOnlineStatusResponse();
        SxGetOnlineStatusReq request = new SxGetOnlineStatusReq();
        request.setDestSkyids(destSkyids);
        setDstModuleId(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
        SxGetOnlineStatusResp resp = serverBiz.getBuddyOnlineStatus(request);
        if (null != resp) {
            switch (resp.getResponseCode()) {
                case 200:
                    response.setResult(200, resp.getResponseMsg());
                    response.setOnlineStatusList(resp.getOnlineStatusList());
                    break;
                default:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
            }
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 获取联系人状态
     * 
     * @param start
     * @param pageSize
     * @return
     */
    @Override
    public NetGetContactsStatusResponse getContactsStatusList(int start, int pageSize) {
        NetGetContactsStatusResponse response = new NetGetContactsStatusResponse();
        SxGetContactsStatusReq request = new SxGetContactsStatusReq();
        request.setStart(start);
        request.setPageSize(pageSize);
        setDstModuleId(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
        SxGetContactsStatusResp resp = serverBiz.getContactsStatus(request);
        if (null != resp) {
            switch (resp.getResponseCode()) {
                case 200:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    response.setContactsStatusList(resp.getItems());
                    response.setStart(resp.getStart());
                    response.setTotalSize(resp.getTotalSize());
                    response.setUpdateTime(resp.getUpdateTime());
                    break;
                default:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
            }
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 新获取联系人状态
     * 
     * @param start 起始页数
     * @param pageSize 每页数量
     * @param fetch 0：全部，默认；1：指定人
     * @param contactId 联系人ID 如果fetch的值是 1 ,则需要指定contactId的值
     * @return
     */
    @Override
    public NetGetContactsStatus2Response getContactsStatusList(int start, int pageSize, int fetch,
            int contactId) {
        NetGetContactsStatus2Response response = new NetGetContactsStatus2Response();
        SxGetSimpleStatusReq request = new SxGetSimpleStatusReq();
        request.setStart(start);
        request.setPageSize(pageSize);
        request.setFetchFlag((byte) fetch);
        if (fetch == 1 && contactId > 0) {
            request.setContactId(contactId);
        }
        setDstModuleId(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
        SxGetSimpleStatusResp resp = serverBiz.getContactsStatus2(request);
        if (null != resp) {
            switch (resp.getResponseCode()) {
                case 200:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    response.setContactsStatusList(resp.getSimpleStatusItem());
                    response.setStart(resp.getStart());
                    response.setTotalSize(resp.getTotalSize());
                    break;
                default:
                    if (resp.getResponseCode() == 502) {
                        response.setResult(resp.getResponseCode(), "您还没有登录，请登录!");
                    } else {
                        response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    }
                    break;
            }
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 发送名片
     * 
     * @param destSkyids
     * @param nickname
     * @param vContent
     * @return
     */
    @Override
    public NetChatResponse sendVCard(String destSkyids, String nickname, Map map) {
        NetChatResponse response = new NetChatResponse();
        SxSendVCardReq request = new SxSendVCardReq();
        request.setDestSkyids(destSkyids);
        request.setNickName(nickname);
        if (null != map && map.size() == 2) {
            String vcard = ParserUtils.encodeVCard(map);
            request.setvCardContent(vcard);
        }
        setDstModuleId(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
        SxSendVCardResp resp = serverBiz.sendVCardMsg(request);
        if (null != resp) {
            switch (resp.getResponseCode()) {
                case 200:
                    // 判断离线消息内容提示是否为空，如果不为空，则表明该消息将以离线消息形式发送
                    // 只要服务端有响应，就当作发送成功处理。
                    if (null != resp.getOfflineMsg()) {
                        response.setUserOnline(false);
                        response.setResult(200, resp.getOfflineMsg());
                    } else {
                        response.setResult(200, resp.getResponseMsg());
                    }
                    break;
                default:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
            }
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 获取附近的人业务接口
     * 
     * @param request
     * @return NetGetNearByFriendResponse
     *         在返回成功的情况下，需要判断是否要重新计算，如果不需要重新计算，则直接获取用户列表，否则，按照逻辑走重新上传用户的位置信息。
     */
    @Override
    public NetGetNearByFriendResponse getNearByFriends(SxGetNearbyReq request) {
        NetGetNearByFriendResponse response = new NetGetNearByFriendResponse();
        setDstModuleId(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
        SxGetNearbyResp resp = serverBiz.getNearByFriends(request);
        if (null != resp) {
            switch (resp.getResponseCode()) {
                case 200:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    if (resp.getUsers().size() > 0) {
                        response.setUsers(resp.getUsers());
                    }
                    response.setStart(resp.getStart());
                    response.setTotalSize(resp.getTotalSize());
                    break;
                case 613:
                    response.setResult(200, resp.getResponseMsg());
                    // 提示需要客户端上传位置信息
                    response.setNeedRecalculated(true);
                    break;
                default:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
            }
        } else {
            response.setNetError();
        }
        return response;

    }

    /**
     * 根据用户名查找联系人
     * 
     * @param request
     * @return 在返回成功的状态下，判断是否找到了好友，如果没有找到好友，则进入没有找到好友的流程。
     */
    @Override
    public NetGetUserInfoByUserNameResponse getUserInfoByUserName(String userName) {
        NetGetUserInfoByUserNameResponse response = new NetGetUserInfoByUserNameResponse();
        SxGetUserInfoByUserNameReq request = new SxGetUserInfoByUserNameReq();
        request.setUserName(userName);
        setDstModuleId(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
        SxGetUserInfoByUserNameResp resp = serverBiz.getUserInfoByUserName(request);
        if (null != resp) {
            switch (resp.getResponseCode()) {
                case 200:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    if (null != resp.getUserInfo()) {
                        UserInfo userInfo = resp.getUserInfo();
                        // 成功
                        NetUserInfo nui = new NetUserInfo();
                        nui.setSkyId(userInfo.getSkyId());
                        nui.setUserName(userInfo.getUserName());
                        nui.setNickname(userInfo.getNickname());
                        nui.setUemail(userInfo.getUemail());
                        nui.setUrealname(userInfo.getUrealname());
                        nui.setUsex(userInfo.getUsex());
                        nui.setUbirthday(userInfo.getUbirthday());
                        nui.setUanimals(userInfo.getUanimals());
                        nui.setUstar(userInfo.getUstar());
                        nui.setUblood(userInfo.getUblood());
                        nui.setUmarried(userInfo.getUmarried());
                        nui.setUportraitid(userInfo.getUportraitid());
                        nui.setUdefineportrait(userInfo.getUdefineportrait());
                        nui.setUcountry(userInfo.getUcountry());
                        nui.setUprovince(userInfo.getUprovince());
                        nui.setUcity(userInfo.getUcity());
                        nui.setUhometown(userInfo.getUhometown());
                        nui.setUsignature(userInfo.getUsignature());
                        nui.setUdesc(userInfo.getUdesc());
                        nui.setUemailchecked(userInfo.getUemailchecked());
                        nui.setUmobile(userInfo.getUmobile());
                        nui.setUtelephone(userInfo.getUtelephone());
                        nui.setUvocation(userInfo.getUvocation());
                        nui.setUschoolgraduated(userInfo.getUschoolgraduated());
                        nui.setUprivacy(userInfo.getUprivacy());
                        nui.setIdcardno(userInfo.getIdcardno());
                        nui.setUindustry(userInfo.getUindustry());
                        nui.setUhobbies(userInfo.getUhobbies());
                        nui.setUcorporation(userInfo.getUcorporation());
                        if (null != userInfo.getPersonNickname()
                                && !"".equals(userInfo.getPersonNickname())) {
                            nui.setPersonnickname(userInfo.getPersonNickname());
                        } else {
                            nui.setPersonnickname("");
                        }

                        // 手信不存在的部分
                        nui.setHaspic(userInfo.getHaspic());
                        nui.setUlatitude(userInfo.getUlatitude());
                        nui.setUlongitude(userInfo.getUlongitude());
                        nui.setUmobile(userInfo.getUmobile());
                        nui.setUserName(userInfo.getUserName());
                        // 新增UUID形式的自定义头像字段数据
                        nui.setUuidPortrait(userInfo.getUuidPortrait());
                        response.setUserInfo(nui);
                    }
                    response.setUserType(resp.getUserType());
                    break;
                case 612:
                    response.setResult(200, resp.getResponseMsg());
                    response.setFinded(false);
                    break;
                default:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
            }
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 获取配置信息
     * 
     * @param configType 配置类型
     * @param updateTime 版本号
     * @return
     */
    @Override
    public NetGetConfigurationResponse getConfiguration(ArrayList<ConfigInfo> configInfos) {
        NetGetConfigurationResponse response = new NetGetConfigurationResponse();
        SxGetConfigurationReq request = new SxGetConfigurationReq();
        request.setConfigInfos(configInfos);
        setDstModuleId(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
        SxGetConfigurationResp resp = serverBiz.getConfiguration(request);
        if (null != resp) {
            switch (resp.getResponseCode()) {
                case 200:
                case 607:
                    response.setResult(200, resp.getResponseMsg());
                    if (resp.getResponseCode() == 607) {
                        response.setHasUpdate(false);
                    } else {
                        response.setConfigInfo(resp.getConfigInfos());
                    }
                    break;
                default:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
            }
        } else {
            response.setNetError();
        }

        return response;
    }

    /**
     * 查看可恢复联系人接口
     * 
     * @param start 起始页码
     * @param pageSize 每页显示的记录数量
     * @return
     */
    @Override
    public NetRestorableContactsResponse getRestorableConacts(int start, int pageSize) {
        NetRestorableContactsResponse response = new NetRestorableContactsResponse();
        SxGetRestorableContactsReq request = new SxGetRestorableContactsReq();
        request.setStart(start);
        request.setPageSize(pageSize);
        setDstModuleId(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
        SxGetRestorableContactsResp resp = serverBiz.getRestorableContacts(request);
        if (null != resp) {
            switch (resp.getResponseCode()) {
                case 200:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    response.setStart(resp.getStart());
                    response.setTotalSize(resp.getTotalSize());
                    response.setRestorableContacts(resp.getRestorableContacts());
                    break;
                default:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
            }
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 批量恢复联系人
     * 
     * @param ids
     * @return
     */
    @Override
    public NetRestoreContactsResp restoreConacts(List<Integer> ids) {
        NetRestoreContactsResp response = new NetRestoreContactsResp();
        SxRestoreContactsReq request = new SxRestoreContactsReq();
        request.setRestoreIds(ids);
        setDstModuleId(request, ESBAddrConstatns.SHOUXIN_ADDRESS);

        SxRestoreContactsResp resp = serverBiz.restoreContacts(request);
        if (null != resp) {
            switch (resp.getResponseCode()) {
                case 200:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
                default:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
            }
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 上传图片
     * 
     * @param url 图片服务器地址
     * @param appid 应用ID
     * @param body 图片内容
     * @param skyid 上传者ID
     */
    @Override
    public synchronized NetUploadResponse uploadImage(String url, int appid, byte[] body, int skyid) {
        NetUploadResponse response = new NetUploadResponse();
        String reqCode = "?skyid=" + skyid + "&do=upload&from=" + appid;
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost postMethod = new HttpPost(url + reqCode);

        // 记录上传时的流量统计
        listener.trafficNotify(body.length);

        HttpEntity entity = new ByteArrayEntity(body);
        postMethod.setEntity(entity);

        HttpResponse resp;
        try {
            resp = httpClient.execute(postMethod);
            if (null != resp) {
                String content = IOUtils.toString(resp.getEntity().getContent());
                if (null != content) {
                    // 记录上传响应传时的流量统计
                    listener.trafficNotify(content.length());
                    String[] tmp = content.split("\r\n");
                    if (null != tmp && tmp.length >= 3) {
                        response.setResult(200, "上传图片成功");
                        response.setId(Integer.valueOf(tmp[0]));
                        response.setImageName(tmp[1]);
                        response.setImageUrl(tmp[2]);
                    } else {
                        response.setResult(100, "服务端返回内容格式不对");
                    }
                } else {
                    response.setResult(100, "服务端返回内容为空");
                }
            } else {
                response.setResult(100, "上传失败");
            }
        } catch (ClientProtocolException e) {
            response.setResult(-1, "网络连接失败");
        } catch (IOException e) {
            response.setResult(-1, "网络连接失败");
        } catch (Exception e) {
            response.setResult(-1, "网络连接失败");
        }
        return response;
    }

    /**
     * 向文件服务器上传图片
     * 
     * @param url 图片服务器URL
     * @param body 图片流
     * @param fileExtName 图片扩展名
     * @param skyid 上传者ID
     * @param token 用户登录TOKEN
     * @return
     */
    @Override
    public NetUploadResponse sfsUploadImage(String url, byte[] body, String fileExtName, int skyid,
            String token) {
        long start = System.currentTimeMillis();
        String reqCode = "uploadLogo";
        NetUploadResponse response = new NetUploadResponse();
        InputStream is = null;
        OutputStream os = null;

        try {
            FsImageUploadRequest request = new FsImageUploadRequest();
            request.setBody(body);
            request.setFileExtName(fileExtName);
            request.setSkyId(skyid);
            request.setToken(token);

            // 对上传请求对象内容进行编码
            BeanTLVEncoder beanEncoder = new BeanTLVEncoder();
            TLVEncodeContext encode = beanEncoder.getEncodeContextFactory()
                     .createEncodeContext(request.getClass(), null);
            byte[] sendBody = ByteUtils.union(beanEncoder.encode(request, encode));
            logger.debug("\t>>>>> [SFS上传图片] 请求的二进制内容:"
                    + ByteUtils.bytesAsHexString(sendBody, sendBody.length));
            // 将上传的文件大小也算到流量统计中
            listener.trafficNotify(sendBody.length);
            // 构造 URLConnection对象
            URL get = new URL(url + reqCode);
            HttpURLConnection connection = (HttpURLConnection) get.openConnection();
            connection.setConnectTimeout(25 * 1000);
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            // 新增请求超时时间
            connection.setRequestProperty("Content-Length", String.valueOf(sendBody.length));
            connection.setRequestProperty("Content-Type", "application/x-tar");
            connection.setRequestProperty("Connection", "close");

            start = System.currentTimeMillis();
            os = connection.getOutputStream();
            os.write(sendBody);
            os.flush();
            os.close();
            logger.debug("\t>>>>>> [SFS上传图片] 执行[os.write]耗时:"
                    + (System.currentTimeMillis() - start) + " ms | 上传文件长度:" + body.length);
            start = System.currentTimeMillis();
            int code = connection.getResponseCode();
            logger.debug("\t>>>>>> [SFS上传图片] 获取响应码耗时:" + (System.currentTimeMillis() - start)
                    + " ms");
            start = System.currentTimeMillis();
            switch (code) {
                case HttpURLConnection.HTTP_OK:
                    int length = connection.getContentLength();
                    if (length > 0) {
                        // 获取输入流
                        is = connection.getInputStream();

                        // 利用流输出缓存来保存输入流中的数据
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        int be = 0;
                        while ((be = is.read()) != -1) {
                            baos.write(be);
                        }
                        // 从byte缓存中获取所有的二进制数据
                        body = baos.toByteArray();
                        logger.debug("\t>>>>>> [SFS上传图片] 执行二进制流操作耗时:"
                                + (System.currentTimeMillis() - start) + " ms | 流长度为:"
                                + body.length);
                        logger.debug("\t>>>>>> [SFS上传图片] 二进制内容:"
                                + ByteUtils.bytesAsHexString(body, body.length));

                        // 对响应内容进行解码
                        BeanTLVDecoder beanDecoder = new BeanTLVDecoder();
                        Object obj = beanDecoder.decode(body.length, body,
                                 beanDecoder.getDecodeContextFactory()
                                         .createDecodeContext(FsImageUploadResponse.class, null));
                        if (null != obj) {
                            FsImageUploadResponse subResp = (FsImageUploadResponse) obj;
                            if (null != subResp.getResponseInfo()) {
                                switch (subResp.getResponseInfo().getResponseCode()) {
                                    case 200:
                                        response.setResult(subResp.getResponseInfo()
                                                .getResponseCode(), subResp.getResponseInfo()
                                                .getResponseMsg());
                                        response.setUuid(subResp.getUuid());
                                        break;
                                    default:
                                        response.setResult(subResp.getResponseInfo()
                                                .getResponseCode(), subResp.getResponseInfo()
                                                .getResponseMsg());
                                        break;
                                }
                            }
                        } else {
                            logger.debug("\t>>>>>> [SFS上传图片] 解码失败,解码后的对象为空");
                            response.setResult(code, "上传图片失败!");
                        }
                    } else {
                        logger.debug("\t>>>>>> [SFS上传图片] 服务端响应内容的正文长度为:0");
                        response.setResult(code, "上传图片失败!");
                    }
                    break;
                default:
                    logger.debug("\t>>>>>> [SFS上传图片] 失败，HTTP响应错误:" + code);
                    response.setResult(code, "上传图片失败!");
                    break;
            }
            if (null != connection)
                connection.disconnect();
        } catch (ClientProtocolException e) {
            logger.debug("\t>>>>>> [SFS上传图片] 失败，出现异常:[ClientProtocolException]");
            response.setResult(-1, "网络连接失败");
        } catch (IOException e) {
            logger.debug("\t>>>>>> [SFS上传图片] 失败，出现异常:[IOException]");
            response.setResult(-1, "网络连接失败");
        } catch (Exception e) {
            logger.debug("\t>>>>>> [SFS上传图片] 失败，出现异常:[Exception]");
            response.setResult(100, "上传图片失败!");
        }
        return response;
    }

    /**
     * 下载图片
     * 
     * @param url 文件服务器URL
     * @param fileName 文件名
     * @param width 图片宽度
     * @param forceGif 是否强制转为GIF
     * @return
     */
    @Override
    public NetImageDownloadResponse sfsDownloadImage(String url, int skyid, String token,
            ArrayList<NetDownloadImageRespInfo> reqList) {
        String reqCode = "downloadLogo";
        long start = System.currentTimeMillis();

        // 构造文件下载响应对象
        NetImageDownloadResponse response = new NetImageDownloadResponse();

        // 构造文件下载请求对象
        FsImageDownloadReq request = new FsImageDownloadReq();

        InputStream is = null;
        OutputStream os = null;
        try {

            // 构造下载请求队列
            ArrayList<ImageDownloadInfo> list = new ArrayList<ImageDownloadInfo>();
            for (NetDownloadImageRespInfo netReq : reqList) {
                ImageDownloadInfo req = new ImageDownloadInfo();
                if (null != netReq.getUuid() && !"".equals(netReq.getUuid())) {
                    req.setUuid(netReq.getUuid());
                }
                req.setFileExtName(netReq.getFileExtName());
                req.setStartPos(netReq.getStartPos());
                req.setWidth(netReq.getWidth());
                req.setHeight(netReq.getHeight());
                list.add(req);
            }
            request.setReqList(list);
            request.setSkyId(skyid);
            request.setToken(token);

            // 对下载请求进行编码
            BeanTLVEncoder beanEncoder = new BeanTLVEncoder();
            TLVEncodeContext encode = beanEncoder.getEncodeContextFactory()
                     .createEncodeContext(request.getClass(), null);
            byte[] body = ByteUtils.union(beanEncoder.encode(request, encode));

            // 将上传的文件大小也算到流量统计中
            listener.trafficNotify(body.length);
            // 构造 URLConnection对象
            URL get = new URL(url + reqCode);
            HttpURLConnection connection = (HttpURLConnection) get.openConnection();
            connection.setConnectTimeout(25 * 1000);
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            // 新增请求超时时间
            connection.setRequestProperty("Content-Length", String.valueOf(body.length));
            connection.setRequestProperty("Content-Type", "application/x-tar");
            connection.setRequestProperty("Connection", "close");

            start = System.currentTimeMillis();
            os = connection.getOutputStream();
            os.write(body);
            os.flush();
            os.close();
            logger.debug("\t>>>>>> [SFS下载图片] 执行[os.write]耗时:"
                    + (System.currentTimeMillis() - start) + " ms");
            start = System.currentTimeMillis();
            int code = connection.getResponseCode();
            logger.debug("\t>>>>>> [SFS下载图片] 获取响应码耗时:" + (System.currentTimeMillis() - start)
                    + " ms");
            start = System.currentTimeMillis();
            switch (code) {
                case HttpURLConnection.HTTP_OK:
                    int length = connection.getContentLength();
                    if (length > 0) {
                        // 获取输入流
                        is = connection.getInputStream();

                        // 利用流输出缓存来保存输入流中的数据
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        int be = 0;
                        while ((be = is.read()) != -1) {
                            baos.write(be);
                        }
                        // 从byte缓存中获取所有的二进制数据
                        body = baos.toByteArray();
                        logger.debug("\t>>>>>> [SFS下载图片] 执行二进制流操作耗时:"
                                + (System.currentTimeMillis() - start) + " ms | 流长度为:"
                                + body.length);
                        // logger.debug("\t>>>>>> [SFS下载图片] 二进制内容:"+ByteUtils.bytesAsHexString(body,
                        // body.length));

                        // 对响应内容进行解码
                        BeanTLVDecoder beanDecoder = new BeanTLVDecoder();
                        Object obj = beanDecoder.decode(body.length, body,
                                 beanDecoder.getDecodeContextFactory()
                                         .createDecodeContext(FsImageDownloadResp.class, null));
                        if (null != obj) {
                            FsImageDownloadResp fsResponse = (FsImageDownloadResp) obj;
                            switch (fsResponse.getResponseInfo().getResponseCode()) {
                                case 200:
                                    if (fsResponse.getFileNum() > 0) {
                                        response.setFileNum(fsResponse.getFileNum());
                                        ArrayList<FsImageDownloadRespInfo> dilist = fsResponse
                                                .getDownloadresp();
                                        ArrayList<NetDownloadImageRespInfo> nlist = new ArrayList<NetDownloadImageRespInfo>();
                                        // 循环遍历响应内容
                                        for (FsImageDownloadRespInfo b : dilist) {
                                            NetDownloadImageRespInfo bean = new NetDownloadImageRespInfo();
                                            bean.setFileData(b.getFileData());
                                            bean.setFileExtName(b.getFileExtName());
                                            bean.setFileSize(b.getFileSize());
                                            bean.setHeight(b.getHeight());
                                            bean.setStartPos(b.getStartPos());
                                            bean.setUuid(b.getUuid());
                                            bean.setWidth(b.getWidth());
                                            nlist.add(bean);
                                        }
                                        response.setFileList(nlist);
                                        response.setResult(200, "下载图片成功");
                                    } else {
                                        response.setResult(100, "下载图片失败");
                                    }
                                    break;
                                default:
                                    logger.debug("\t>>>>>> [SFS下载图片] 下载失败,解码后的响应码:"
                                            + fsResponse.getResponseInfo().getResponseCode()
                                            + "| 响应文字:"
                                            + fsResponse.getResponseInfo().getResponseMsg());
                                    response.setResult(fsResponse.getResponseInfo()
                                            .getResponseCode(), fsResponse.getResponseInfo()
                                            .getResponseMsg());
                                    break;
                            }
                        } else {
                            logger.debug("\t>>>>>> [SFS下载图片] 响应的内容不能解析");
                            response.setResult(101, "下载失败");
                        }
                    } else {
                        logger.debug("\t>>>>>> [SFS下载图片] 响应的内容长度为 0");
                        response.setResult(102, "下载失败");
                    }
                    break;
                default:
                    logger.debug("\t>>>>>> [SFS下载图片] HTTP响应错误码:" + code);
                    response.setResult(code, "下载失败");
                    break;
            }
        } catch (ClientProtocolException e) {
            logger.debug("\t>>>>>> [SFS下载图片] 异常:[ClientProtocolException]");
            response.setResult(-1, "网络连接失败");
        } catch (IOException e) {
            logger.debug("\t>>>>>> [SFS下载图片] 异常:[IOException]");
            response.setResult(-1, "网络连接失败");
        } catch (Exception e) {
            logger.debug("\t>>>>>> [SFS下载图片] 异常:[Exception]");
            response.setResult(-1, "网络连接失败");
        }
        return response;
    }

    /**
     * 下载图片
     * 
     * @param url 图片服务器地址
     * @param id 图片ID
     * @param width 图片宽度
     * @param forceGif 是否强转GIF 0:按照原始逻辑走[注意中的会强转为GIF] 1:所有大小（除了20以外）都不强制转换为GIF
     *            2:所有的都强制转化为GIF
     */
    @Override
    public synchronized NetDownloadResponse downloadImage(String url, String fileName, int width,
            int forceGif) {
        NetDownloadResponse response = new NetDownloadResponse();
        long start = System.currentTimeMillis();
        byte[] body = null;
        String reqCode = "/file?id=" + fileName + "&s=" + width + "&c=" + forceGif;
        InputStream is = null;
        try {

            // 记录上传时的流量统计
            listener.trafficNotify(1);
            // 构造 URLConnection对象
            URL get = new URL(url + reqCode);
            HttpURLConnection connection = (HttpURLConnection) get.openConnection();
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            int code = connection.getResponseCode();
            switch (code) {
                case HttpURLConnection.HTTP_OK:
                    int length = connection.getContentLength();
                    if (length > 0) {
                        start = System.currentTimeMillis();
                        // 获取输入流
                        is = connection.getInputStream();

                        // 利用流输出缓存来保存输入流中的数据
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        int b = 0;
                        while ((b = is.read()) != -1) {
                            baos.write(b);
                        }
                        // 从byte缓存中获取所有的二进制数据
                        body = baos.toByteArray();
                        logger.debug("\t>>>>>> [SFS下载图片] 执行二进制流操作耗时:"
                                + (System.currentTimeMillis() - start) + " ms | 流长度为:"
                                + body.length);
                        logger.debug("\t>>>>>> [SFS下载图片] 二进制内容:"
                                + ByteUtils.bytesAsHexString(body, body.length));

                        if (null != body && body.length > 0) {
                            // 记录下载图片时的流量
                            listener.trafficNotify(body.length);
                            response.setResult(200, "下载文件成功");
                            response.setBody(body);
                            response.setLength(body.length);
                            logger.debug("\t>>>>>> [SFS下载图片] 下载图片成功,内容:"
                                    + ByteUtils.bytesAsHexString(body, body.length));
                        } else {
                            logger.debug("\t>>>>>> [SFS下载图片] 下载文件失败");
                            response.setResult(100, "下载文件失败");
                        }
                    }
                    break;
                default:
                    logger.debug("\t>>>>>> [SFS下载图片] 下载文件失败");
                    response.setResult(100, "下载文件失败");
                    break;
            }
            if (null != connection)
                connection.disconnect();
        } catch (IOException e) {
            response.setResult(-1, "连接服务器失败");
        } catch (Exception e) {
            response.setResult(-1, "连接服务器失败");
        }
        return response;
    }

    /**
     * 上传文件
     * 
     * @param url 文件服务器地址
     * @param body 文件内容
     */
    @Override
    public synchronized NetUploadResponse uploadFs(String url, int skyid, String token,
            String fileExtName, byte[] body) {
        long start = System.currentTimeMillis();
        String reqCode = "upload";
        NetUploadResponse response = new NetUploadResponse();
        OutputStream os = null;
        InputStream is = null;
        try {

            FsUploadRequest request = new FsUploadRequest();
            request.setBody(body);
            request.setLength(body.length);
            request.setFileExtName(fileExtName);
            request.setSkyId(skyid);
            request.setToken(token);

            // 对上传请求对象内容进行编码
            BeanTLVEncoder beanEncoder = new BeanTLVEncoder();
            TLVEncodeContext encode = beanEncoder.getEncodeContextFactory()
                    .createEncodeContext(request.getClass(), null);
            byte[] sendBody = ByteUtils.union(beanEncoder.encode(request, encode));

            // 将上传的文件大小也算到流量统计中
            listener.trafficNotify(sendBody.length);
            // 构造 URLConnection对象
            URL get = new URL(url + reqCode);
            HttpURLConnection connection = (HttpURLConnection) get.openConnection();
            // 连接超时时间
            connection.setConnectTimeout(25 * 1000);
            // 读取超时时间
            connection.setReadTimeout(10 * 1000);
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            // 新增请求超时时间
            connection.setRequestProperty("Connection", "close");
            connection.setRequestProperty("Content-Length", String.valueOf(sendBody.length));
            connection.setRequestProperty("Content-Type", "application/x-tar");

            start = System.currentTimeMillis();
            os = connection.getOutputStream();
            os.write(sendBody);
            os.flush();
            os.close();

            logger.debug("\t>>>>>> [SFS上传] 执行[os.write]耗时:" + (System.currentTimeMillis() - start)
                    + " ms,上传文件大小:" + body.length);
            start = System.currentTimeMillis();
            int code = connection.getResponseCode();
            logger.debug("\t>>>>>> [SFS上传] 获取响应码耗时:" + (System.currentTimeMillis() - start) + " ms");
            start = System.currentTimeMillis();
            switch (code) {
                case HttpURLConnection.HTTP_OK:
                    int length = connection.getContentLength();
                    if (length > 0) {
                        start = System.currentTimeMillis();
                        // 获取输入流
                        is = connection.getInputStream();
                        // 利用流输出缓存来保存输入流中的数据
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        int b = 0;
                        while ((b = is.read()) != -1) {
                            baos.write(b);
                        }
                        baos.flush();
                        // 从byte缓存中获取所有的二进制数据
                        byte[] result = baos.toByteArray();
                        logger.debug("\t>>>>>> [SFS上传] 执行二进制流操作耗时:"
                                + (System.currentTimeMillis() - start) + " ms | 响应流长度为:"
                                + result.length);
                        logger.debug("\t>>>>>> [SFS上传] 二进制内容:"
                                + ByteUtils.bytesAsHexString(result, result.length));

                        // 将上传响应的内容大小也算到流量统计中
                        listener.trafficNotify(length);
                        // 对响应内容进行解码
                        BeanTLVDecoder beanDecoder = new BeanTLVDecoder();
                        Object obj = beanDecoder.decode(result.length, result,
                                beanDecoder.getDecodeContextFactory()
                                        .createDecodeContext(FsUploadResponse.class, null));
                        if (null != obj) {
                            FsUploadResponse subResp = (FsUploadResponse) obj;
                            if (null != subResp.getResponseInfo()) {
                                switch (subResp.getResponseInfo().getResponseCode()) {
                                    case 200:
                                        logger.debug("\t>>>>>> [SFS上传] 上传文件成功,文件大小为:" + body.length
                                                + ",返回码为:"
                                                + subResp.getResponseInfo().getResponseCode()
                                                + ",返回提示为:"
                                                + subResp.getResponseInfo().getResponseMsg());
                                        response.setResult(subResp.getResponseInfo()
                                                .getResponseCode(), subResp.getResponseInfo()
                                                .getResponseMsg());
                                        response.setMd5(SysUtils.byteArray2Md5(subResp.getMd5()));
                                        break;
                                    default:
                                        logger.debug("\t>>>>>> [SFS上传] 上传文件失败,文件大小为:" + body.length
                                                + ",返回码为:"
                                                + subResp.getResponseInfo().getResponseCode()
                                                + ",返回提示为:"
                                                + subResp.getResponseInfo().getResponseMsg());
                                        response.setResult(subResp.getResponseInfo()
                                                .getResponseCode(), subResp.getResponseInfo()
                                                .getResponseMsg());
                                        break;
                                }
                            } else {
                                logger.debug("\t>>>>>> [SFS上传] 返回内容为空,文件大小为:" + body.length);
                                response.setResult(103, "上传失败");
                            }
                        } else {
                            logger.debug("\t>>>>>> [SFS上传] 返回二进制流解码失败,文件大小为:" + body.length);
                            response.setResult(101, "上传失败");
                        }
                    } else {
                        logger.debug("\t>>>>>> [SFS上传] 返回二进制流解码失败,文件大小为:" + body.length);
                        response.setResult(102, "上传失败");
                    }
                    break;
                default:
                    logger.debug("\t>>>>>> [SFS上传] 上传文件失败,文件大小为:" + body.length + ",错误码为:" + code);
                    response.setResult(code, "上传文件失败!");
                    break;
            }
            if (null != connection) {
                connection.disconnect();
            }
        } catch (ClientProtocolException e) {
            logger.debug("\t>>>>>> [SFS上传]网络连接失败[ClientProtocolException],文件大小为:" + body.length);
            response.setResult(-1, "网络连接失败");
        } catch (IOException e) {
            logger.debug("\t>>>>>> [SFS上传]网络连接失败[IOException],语音文件大小为:" + body.length);
            response.setResult(-1, "网络连接失败");
        } catch (Exception e) {
            logger.debug("\t>>>>>> [SFS上传]上传文件失败![Exception],语音文件大小为:" + body.length);
            response.setResult(-1, "上传文件失败!");
        }
        return response;
    }

    /**
     * 下载文件
     * 
     * @param url 文件服务器地址
     * @param md5 文件MD5值
     */
    @Override
    public synchronized NetFsDownloadResponse downloadFs(String url, int skyid, String token,
            ArrayList<NetFsDownloadReq> downloadList) {
        long start = System.currentTimeMillis();
        String reqCode = "download";
        OutputStream os = null;
        InputStream is = null;

        // 构造文件下载响应对象
        NetFsDownloadResponse response = new NetFsDownloadResponse();

        // 构造文件下载请求对象
        FsDownloadReq request = new FsDownloadReq();
        try {

            // 构造下载请求队列
            ArrayList<DownloadInfo> list = new ArrayList<DownloadInfo>();
            for (NetFsDownloadReq netReq : downloadList) {
                DownloadInfo req = new DownloadInfo();
                if (null != netReq.getMd5() && !"".equals(netReq.getMd5())) {
                    req.setMd5(SysUtils.md5Encrypt(netReq.getMd5()));
                }
                req.setStartPos(netReq.getStartPos());
                list.add(req);
            }
            request.setReqList(list);
            request.setSkyId(skyid);
            request.setToken(token);

            // 对下载请求进行编码
            BeanTLVEncoder beanEncoder = new BeanTLVEncoder();
            TLVEncodeContext encode = beanEncoder.getEncodeContextFactory()
                    .createEncodeContext(request.getClass(), null);
            byte[] body = ByteUtils.union(beanEncoder.encode(request, encode));

            // 将上传的文件大小也算到流量统计中
            listener.trafficNotify(body.length);

            // 构造 URLConnection对象
            URL get = new URL(url + reqCode);
            HttpURLConnection connection = (HttpURLConnection) get.openConnection();
            connection.setConnectTimeout(25000);
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            // 新增请求超时时间
            connection.setRequestProperty("Connection", "close");
            connection.setRequestProperty("Content-Length", String.valueOf(body.length));
            connection.setRequestProperty("Content-Type", "application/x-tar");

            start = System.currentTimeMillis();
            os = connection.getOutputStream();
            os.write(body);
            os.flush();
            os.close();
            logger.debug("\t>>>>>> [SFS下载] 执行[os.write]耗时:" + (System.currentTimeMillis() - start)
                    + " ms");
            start = System.currentTimeMillis();
            int code = connection.getResponseCode();
            logger.debug("\t>>>>>> [SFS下载] 响应码为:" + code + " 耗时:"
                    + (System.currentTimeMillis() - start) + " ms");
            start = System.currentTimeMillis();
            switch (code) {
                case HttpURLConnection.HTTP_OK:
                    int length = connection.getContentLength();
                    logger.debug("\t>>>>>> [SFS下载] HTTP内容长度:" + length);
                    if (length > 0) {
                        start = System.currentTimeMillis();
                        // 获取输入流
                        is = connection.getInputStream();
                        // 利用流输出缓存来保存输入流中的数据
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        int b = 0;
                        while ((b = is.read()) != -1) {
                            baos.write(b);
                        }
                        baos.flush();
                        // 从byte缓存中获取所有的二进制数据
                        byte[] result = baos.toByteArray();
                        logger.debug("\t>>>>>> [SFS下载] 执行二进制流操作耗时:"
                                + (System.currentTimeMillis() - start) + " ms | 流长度为:"
                                + result.length);
                        // 将上传响应的内容大小也算到流量统计中
                        listener.trafficNotify(length);
                        // 对响应内容进行解码
                        BeanTLVDecoder beanDecoder = new BeanTLVDecoder();
                        Object obj = beanDecoder.decode(result.length, result,
                                beanDecoder.getDecodeContextFactory()
                                        .createDecodeContext(FsDownloadResp.class, null));
                        if (null != obj) {
                            FsDownloadResp fsResponse = (FsDownloadResp) obj;
                            if (null != fsResponse.getResponseInfo()) {
                                switch (fsResponse.getResponseInfo().getResponseCode()) {
                                    case 200:
                                        if (fsResponse.getFileNum() > 0) {
                                            response.setFileNum(fsResponse.getFileNum());
                                            for (FsDownloadRespInfo fsRespInfo : fsResponse
                                                    .getDownloadresp()) {
                                                NetFsDownloadFile file = new NetFsDownloadFile();
                                                file.setFileData(fsRespInfo.getFileData());
                                                file.setFileSize(fsRespInfo.getFileSize());
                                                file.setMd5(fsRespInfo.getMd5());
                                                file.setStartPos(fsRespInfo.getStartPos());
                                                response.getFileList().add(file);
                                                logger.debug("\t>>>>>> [SFS下载] 文件下载,文件MD5:"
                                                        + fsRespInfo.getMd5() + "|文件大小为:"
                                                        + fsRespInfo.getFileSize());
                                            }
                                            response.setResult(200, fsResponse.getResponseInfo()
                                                    .getResponseMsg());
                                            logger.debug("\t>>>>>> [SFS下载] 下载成功,文件数量为"
                                                    + fsResponse.getFileNum());
                                        } else {
                                            logger.debug("\t>>>>>> [SFS下载] 下载失败,文件数量为0,与预期的文件数量["
                                                    + list.size() + "]不符");
                                            response.setResult(100, "下载失败");
                                        }
                                        break;
                                    default:
                                        logger.debug("\t>>>>>> [SFS下载] 下载失败,响应码为:"
                                                + fsResponse.getResponseInfo().getResponseCode()
                                                + "|响应内容为:"
                                                + fsResponse.getResponseInfo().getResponseMsg());
                                        response.setResult(100, fsResponse.getResponseInfo()
                                                .getResponseMsg());
                                        break;
                                }
                            } else {
                                logger.debug("\t>>>>>> [SFS下载] 响应内容为空,语音文件大小为:" + body.length);
                                response.setResult(103, "下载失败");
                            }
                        } else {
                            logger.debug("\t>>>>>> [SFS下载] 返回二进制流解码失败,语音文件大小为:" + body.length);
                            response.setResult(101, "下载失败");
                        }
                    } else {
                        logger.debug("\t>>>>>> [SFS下载] 返回的内容长度为:" + length + " 不满足要求");
                        response.setResult(102, "下载失败");
                    }
                    break;
                default:
                    logger.debug("\t>>>>>> [SFS下载] 下载失败,HTTP响应码为:" + code);
                    response.setResult(code, "下载失败");
                    break;
            }
            if (null != connection) {
                connection.disconnect();
            }
        } catch (ClientProtocolException e) {
            logger.debug("\t>>>>>> [SFS下载]下载失败,出现异常[ClientProtocolException]");
            response.setResult(-1, "网络连接失败");
            e.printStackTrace();
        } catch (IOException e) {
            logger.debug("\t>>>>>> [SFS下载]下载失败,出现异常[IOException]");
            response.setResult(-1, "网络连接失败");
            e.printStackTrace();
        } catch (Exception e) {
            logger.debug("\t>>>>>> [SFS下载]下载失败,出现异常[Exception]");
            response.setResult(-1, "网络连接失败");
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 检查是否需要更新 方法实现需要对发送内容进行编码，以及响应内容进行解码
     */
    @Override
    public NetSupResponse checkSupupdate(String url, TerminalInfo info) throws IOException {
        long start = System.currentTimeMillis();
        logger.debug(">>>>> checkSupupdate.start.time:" + start);
        NetSupResponse response = new NetSupResponse();
        OutputStream os = null;
        InputStream is = null;
        // 更新访问路径
        String reqCode = "shouxinUpdate";
        try {
            long ss = System.currentTimeMillis();
            byte[] body = encodeSup(info, 0, 1, null, 0, 0);
            // 构造HTTP请求对象
            URL get = new URL(url + reqCode);
            HttpURLConnection connection = (HttpURLConnection) get.openConnection();
            logger.debug(">>>>> checkSupupdate.connectionTimeout:" + connection.getConnectTimeout());
            logger.debug(">>>>> checkSupupdate.readTimeout:" + connection.getReadTimeout());
            // 设置连接主机超时
            connection.setConnectTimeout(15 * 1000);
            // 设置从主机读取数据超时
            connection.setReadTimeout(20 * 1000);
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            // 新增请求超时时间
            connection.setRequestProperty("Connection", "close");
            connection.setRequestProperty("Content-Length", String.valueOf(body.length));
            connection.connect();
            logger.debug("\t>>>>> checkSupupdate.setConnectionParas:["
                    + (System.currentTimeMillis() - ss) + "]ms");
            ss = System.currentTimeMillis();
            // 将编码后的内容提交到服务端
            os = connection.getOutputStream();
            os.write(body);
            os.flush();
            os.close();
            logger.debug("\t>>>>> SUP 请求的二进制内容:" + ByteUtils.bytesAsHexString(body, body.length));
            logger.debug("\t>>>>> checkSupupdate.outputStream.body:["
                    + (System.currentTimeMillis() - ss) + "]ms");
            long httpReqEnd = System.currentTimeMillis();
            logger.debug("\t>>>>> checkSupupdate.spend.time:[" + (httpReqEnd - start) + "]ms");
            ss = System.currentTimeMillis();
            int code = connection.getResponseCode();
            logger.debug("\t>>>>> checkSupupdate.getResponseCode:["
                    + (System.currentTimeMillis() - ss) + "]ms");
            logger.debug("\t>>>>> checkSupupdate.response.code:" + code);
            switch (code) {
                case HttpURLConnection.HTTP_OK:
                    ss = System.currentTimeMillis();
                    // 请求正常
                    int length = connection.getContentLength();
                    byte[] result = new byte[length];
                    logger.debug("\t>>>>> checkSupupdate.HTTPOK.length:["
                            + (System.currentTimeMillis() - ss) + "]ms");
                    ss = System.currentTimeMillis();
                    // 获取输入流
                    is = connection.getInputStream();
                    is.read(result);
                    logger.debug("\t>>>>> SUP 响应的二进制内容:"
                            + ByteUtils.bytesAsHexString(result, result.length));
                    logger.debug("\t>>>>> checkSupupdate.HTTPOK.getInputStream:["
                            + (System.currentTimeMillis() - ss) + "]ms");
                    if (null != result) {
                        ss = System.currentTimeMillis();
                        BeanTLVDecoder beanDecoder = new BeanTLVDecoder();
                        Object obj = beanDecoder.decode(result.length, result,
                                beanDecoder.getDecodeContextFactory()
                                        .createDecodeContext(SupResponse.class, null));
                        logger.debug("\t>>>>> checkSupupdate.HTTPOK.decoder:["
                                + (System.currentTimeMillis() - ss) + "]ms");
                        ss = System.currentTimeMillis();
                        if (null != obj) {
                            SupResponse subResp = (SupResponse) obj;
                            switch (subResp.getResponseCode()) {
                                case 204:
                                    response.setResult(200, "有最新版本!");
                                    if (null != subResp.getFeature()
                                            && !"".equals(subResp.getFeature())) {
                                        response.setFeature(subResp.getFeature());
                                    }
                                    if (null != subResp.getMd5()) {
                                        response.setMd5(SysUtils.byteArray2Md5(subResp.getMd5()));
                                    }
                                    if (subResp.getCheckAfterTimes() > 0) {
                                        response.setCheckAfterTimes(subResp.getCheckAfterTimes());
                                    }
                                    if (subResp.getCheckInterval() > 0) {
                                        response.setCheckInterval(subResp.getCheckInterval());
                                    }
                                    if (null != subResp.getAppOutVersion()) {
                                        response.setAppOutVersion(subResp.getAppOutVersion());
                                    }
                                    if (subResp.getFilelen() > 0) {
                                        response.setFileLength(subResp.getFilelen());
                                    }
                                    // 推荐更新
                                    response.setNeedUpdate(true);
                                    break;
                                case 203:
                                    response.setResult(200, "有最新版本!");
                                    if (null != subResp.getFeature()
                                            && !"".equals(subResp.getFeature())) {
                                        response.setFeature(subResp.getFeature());
                                    }
                                    if (null != subResp.getMd5()) {
                                        response.setMd5(SysUtils.byteArray2Md5(subResp.getMd5()));
                                    }
                                    if (subResp.getCheckAfterTimes() > 0) {
                                        response.setCheckAfterTimes(subResp.getCheckAfterTimes());
                                    }
                                    if (subResp.getCheckInterval() > 0) {
                                        response.setCheckInterval(subResp.getCheckInterval());
                                    }
                                    if (null != subResp.getAppOutVersion()) {
                                        response.setAppOutVersion(subResp.getAppOutVersion());
                                    }
                                    if (subResp.getFilelen() > 0) {
                                        response.setFileLength(subResp.getFilelen());
                                    }
                                    // 强制更新
                                    response.setfUpdate(true);
                                    break;
                                case 404:
                                    response.setResult(subResp.getResponseCode(), "暂无更新!");
                                    break;
                                default:
                                    response.setResult(subResp.getResponseCode(), "更新失败!");
                                    break;
                            }
                        }
                        logger.debug("\t>>>>> checkSupupdate.HTTPOK.file_response:["
                                + (System.currentTimeMillis() - ss) + "]ms");
                    }
                    break;
                default:
                    // 请求异常
                    response.setResult(code, "检查更新失败");
                    break;
            }
        } catch (SocketTimeoutException e) {
            response.setResult(-1, "网络连接超时");
        } catch (Exception e) {
            response.setResult(-1, "网络连接异常");
        } finally {
            if (null != is) {
                is.close();
            }
        }
        long end = System.currentTimeMillis();
        logger.debug("\t>>>>> checkSupupdate.total.time:[" + (end - start) + "]ms");
        return response;
    }

    /**
     * 更新 方法实现需要对发送内容进行编码，以及响应内容进行解码
     */
    @Override
    public NetSupResponse update(TerminalInfo info, String url, String md5, int startPos) {
        NetSupResponse response = new NetSupResponse();
        SupRequest request = new SupRequest();
        setSupRequest(info, request);
        request.setReqType(1);
        request.setDownloadPolicy(0);
        if (null != md5 && !"".equals(md5)) {
            request.setMd5(SysUtils.md5Encrypt(md5));
        }
        if (startPos > 0) {
            request.setStartPos(startPos);
        }
        String reqCode = "shouxinUpdate";
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost postMethod = new HttpPost(url + reqCode);

        BeanTLVEncoder beanEncoder = new BeanTLVEncoder();
        TLVEncodeContext encode = beanEncoder.getEncodeContextFactory()
                .createEncodeContext(request.getClass(), null);
        byte[] body = ByteUtils.union(beanEncoder.encode(request, encode));

        // 将请求的大小也算到流量统计中
        listener.trafficNotify(body.length);

        HttpEntity entity = new ByteArrayEntity(body);
        postMethod.setEntity(entity);

        HttpResponse resp;
        try {
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 15000);
            resp = httpClient.execute(postMethod);
            if (null != resp) {
                StatusLine statusLine = resp.getStatusLine();
                switch (statusLine.getStatusCode()) {
                    case 200:
                        InputStream is = resp.getEntity().getContent();
                        // is.skip(84+8);
                        byte[] result = IOUtils.toByteArray(is);
                        logger.debug("\t>>>>>>文件长度：" + result.length);

                        // 将响应的内容大小也算到流量统计中
                        listener.trafficNotify(result.length);

                        BeanTLVDecoder beanDecoder = new BeanTLVDecoder();
                        Object obj = beanDecoder.decode(result.length, result,
                                beanDecoder.getDecodeContextFactory()
                                        .createDecodeContext(SupResponse.class, null));
                        if (null != obj) {
                            SupResponse subResp = (SupResponse) obj;
                            switch (subResp.getResponseCode()) {
                                case 200:
                                    response.setResult(200, "有最新版本!");
                                    response.setFileLength(subResp.getFilelen());
                                    if (null != subResp.getMd5()) {
                                        response.setMd5(SysUtils.byteArray2Md5(subResp.getMd5()));
                                    }
                                    if (subResp.getCheckAfterTimes() > 0) {
                                        response.setCheckAfterTimes(subResp.getCheckAfterTimes());
                                    }
                                    if (subResp.getCheckInterval() > 0) {
                                        response.setCheckInterval(subResp.getCheckInterval());
                                    }
                                    if (null != subResp.getBody()) {
                                        response.setBody(subResp.getBody());
                                    }
                                    response.setStartPos(subResp.getStartPos());
                                    if (null != subResp.getFeature()
                                            && !subResp.getFeature().equals("")) {
                                        response.setFeature(subResp.getFeature());
                                    }
                                    break;
                                default:
                                    response.setResult(100, "更新失败!");
                                    break;
                            }
                        }
                        break;
                    default:
                        response.setResult(100, "检查更新失败");
                        break;
                }
            } else {
                response.setResult(100, "检查更新失败");
            }
        } catch (ClientProtocolException e) {
            response.setResult(-1, "网络连接失败");
        } catch (IOException e) {
            response.setResult(-1, "网络连接失败");
        } catch (Exception e) {
            response.setResult(-1, "网络连接失败");
        }
        return response;
    }

    /**
     * 新SUP服务端下载最新版本文件
     * 
     * @param info 终端属性
     * @param url SUP服务器地址
     * @param path 文件保存路径
     * @param md5 下载文件的MD5值
     * @param startPos 下载起始值
     */
    @Override
    public NetSupResponse nupdate(final TerminalInfo info, String url, final String path,
            final String md5, final int startPos, final int fileTotalSize) throws IOException {
        long start = System.currentTimeMillis();
        logger.debug(">>>>> nupdate.start.time:" + start);
        boolean isAppend = true;
        OutputStream os = null;
        NetSupResponse response = new NetSupResponse();

        try {
            if (startPos >= fileTotalSize) {
                // TODO 起始长度大于等于实际文件大小，不下发
                response.setResult(100, "文件起始位置大于实际文件大小，不需要下载");
                return response;
            }
            // 构造URL
            String reqCode = "shouxinUpdate";
            url = url + reqCode;
            long ss = System.currentTimeMillis();
            // 对SUP请求进行编码
            byte[] body = encodeSup(info, 1, 1, md5, startPos, startPos + MAX_FILE_LEN);
            logger.debug("\t>>>>> nupdate.encodeSup:[" + (System.currentTimeMillis() - ss) + "]ms");
            ss = System.currentTimeMillis();
            // 构造HTTP请求对象
            URL get = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) get.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            // 设置连接主机超时
            connection.setConnectTimeout(15 * 1000);
            // 设置从主机读取数据超时
            connection.setReadTimeout(20 * 1000);
            connection.setRequestProperty("Connection", "close");
            connection.setRequestProperty("Content-Length", String.valueOf(body.length));
            logger.debug("\t>>>>> nupdate.setConnectionParams:["
                    + (System.currentTimeMillis() - ss) + "]ms");
            connection.connect();
            ss = System.currentTimeMillis();
            // 将编码后的内容提交到服务端
            os = connection.getOutputStream();
            os.write(body);
            os.flush();
            os.close();
            logger.debug("\t>>>>> nupdate.getOutputStream:[" + (System.currentTimeMillis() - ss)
                    + "]ms");
            long httpReqEnd = System.currentTimeMillis();
            logger.debug("\t>>>>> nupdate.spend.time:[" + (httpReqEnd - start) + "]ms");

            ss = System.currentTimeMillis();
            int code = connection.getResponseCode();
            logger.debug("\t>>>>> nupdate.getResponseCode:[" + (System.currentTimeMillis() - ss)
                    + "]ms");
            logger.debug("\t>>>>> nupdate.response.code:" + code);
            switch (code) {
                case HttpURLConnection.HTTP_OK:
                    // 统计发送的流量
                    listener.trafficNotify(body.length);
                    ss = System.currentTimeMillis();
                    logger.debug("\t>>>>> nupdate[HTTP响应长度]:" + connection.getContentLength());
                    // 获取输入流
                    final InputStream is = connection.getInputStream();
                    logger.debug("\t>>>>> nupdate.getInputStream:["
                            + (System.currentTimeMillis() - ss) + "]ms");
                    // 获取指定字节的内容,92个字节是不包含文件内容在内的TLV数据,需要再增加14个字节内容
                    // ,这14个字节的内容为tag=715的字符串内容
                    byte[] result = new byte[92];
                    is.read(result);
                    if (null != result) {
                        logger.debug("\t>>>>>>result:"
                                + ByteUtils.bytesAsHexString(result, result.length));
                        ss = System.currentTimeMillis();
                        Object obj = decodeSup(result, SupResponse.class);
                        logger.debug("\t>>>>> nupdate.decodeSup:["
                                + (System.currentTimeMillis() - ss) + "]ms");
                        ss = System.currentTimeMillis();
                        if (null != obj) {
                            // 统计接收的流量
                            listener.trafficNotify(result.length);
                            SupResponse subResp = (SupResponse) obj;
                            if (subResp.getFilelen() > 0) {
                                switch (subResp.getResponseCode()) {
                                    case 200:
                                        response.setResult(200, "有最新版本!");
                                        // 设置下发的文件大小
                                        if (subResp.getFilelen() > 0) {
                                            response.setFileLength(subResp.getFilelen());
                                        }
                                        // 设置MD5值
                                        if (null != subResp.getMd5()) {
                                            response.setMd5(SysUtils.byteArray2Md5(subResp.getMd5()));
                                        }
                                        // 设置下次检查时间
                                        if (subResp.getCheckAfterTimes() > 0) {
                                            response.setCheckAfterTimes(subResp
                                                    .getCheckAfterTimes());
                                        }
                                        // 设置检查间隔时间
                                        if (subResp.getCheckInterval() > 0) {
                                            response.setCheckInterval(subResp.getCheckInterval());
                                        }
                                        // 设置下载起始位置
                                        if (subResp.getStartPos() > 0) {
                                            response.setStartPos(subResp.getStartPos());
                                        }
                                        logger.debug("\t>>>>>> local.md5:" + md5);
                                        logger.debug("\t>>>>>> remote.md5:" + response.getMd5());
                                        // 为了确认是否需要重新下载，需要比较MD5值是否一致
                                        if (null != md5 && !md5.equals("")) { // 先判断本地参数MD5是否为空
                                            if (null != response.getMd5()
                                                    && !response.getMd5().equals(md5)) { // 如果服务端MD5和本地MD5不一致，则说明文件不一样，需要重新下载文件
                                                // TODO
                                                // 执行重新下载，同时设置重新下载标记，并返回调用方当前下载为完整下载。
                                                isAppend = false;
                                                response.setCompleateDownload(true);
                                            }
                                        }
                                        final boolean isAppendd = isAppend;
                                        final String rURL = url;
                                        final String rMD5 = response.getMd5();
                                        final int fileLen = subResp.getFilelen();
                                        logger.debug("\t>>>>> nupdate.app.fileLen:[" + fileLen
                                                + "]ms");
                                        // 异步分包下载文件
                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    // 写第一次请求中返回的内容大小
                                                    writeFile(is, path, isAppendd);
                                                    // 剩余需要下载的大小，要减去上面已经写入的大小
                                                    int rest = fileTotalSize - startPos - fileLen;
                                                    final int packLen = ((rest / fileLen) + (rest
                                                            % fileLen > 0 ? 1 : 0));
                                                    for (int i = 0; i < packLen; i++) { // 分包请求更新
                                                        // 开始位置
                                                        int s = i * fileLen + startPos + fileLen;
                                                        // 结束位置
                                                        int e = (s + fileLen > (fileTotalSize) ? (fileTotalSize)
                                                                : (s + fileLen));
                                                        // TODO 请求下载
                                                        continueUpdate(info, rURL, path, rMD5, s, e);
                                                        logger.debug(" -------------------> i=["
                                                                + i + "]|\tstart=" + s + "|\tend="
                                                                + e + "<------------------- ");
                                                    }
                                                } catch (Exception e) {
                                                    // 重新续传
                                                    System.err
                                                            .print("\t>>>>>>writeFile.IOException:"
                                                                    + e);
                                                    int fs = getFileLength(path);
                                                    try {
                                                        continueUpdate(info, rURL, path, rMD5, fs,
                                                                fs + fileLen);
                                                    } catch (Exception e2) {
                                                        listener.onUpdateError();
                                                    }
                                                }
                                            }
                                        }).start();

                                        // 监控当前文件大小变化，是否在允许时间内没有数据
                                        final File file = new File(path);
                                        if (null != file) {
                                            // TODO 如果一段时间内没有数据流入则提示用户重试
                                            new Thread(new Runnable() {
                                                // 线程运行标识
                                                boolean isOk = true;
                                                private int fileSize = startPos;
                                                private int times = 0;

                                                @Override
                                                public void run() {
                                                    while (isOk) {
                                                        if (fileSize > startPos
                                                                && fileSize == fileTotalSize) {
                                                            // TODO 已经下载完成
                                                            isOk = false;
                                                            logger.debug("\t>>>>> download_file.finish:"
                                                                    + fileSize);
                                                            break;
                                                        } else {
                                                            // 正在下载
                                                            if (fileSize != file.length()) {
                                                                if (times > 0) {
                                                                    times = 0;
                                                                }
                                                                // 获取当前文件大小，并且赋值给变量fileSize，作为下次判断的条件
                                                                fileSize = (int) file.length();
                                                                logger.debug("\t>>>>> download_file.fileSize:"
                                                                        + fileSize);
                                                            } else {
                                                                if (fileSize == (int) file.length()) {
                                                                    // TODO
                                                                    if (times < 4 * 2) {
                                                                        logger.debug("\t>>>>> download_file.fileSize.timeout:"
                                                                                + times + ",retry!");
                                                                        times++;
                                                                    } else {
                                                                        logger.debug("\t>>>>> download_file.fileSize.timeout:"
                                                                                + times + ",break;");
                                                                        isOk = false;
                                                                        listener.onUpdateError();
                                                                        break;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        try {
                                                            Thread.sleep(2500L);
                                                        } catch (InterruptedException e) {
                                                            logger.error("\t>>>>> download_file.exception:"
                                                                    + e);
                                                        }
                                                    }
                                                }
                                            }, "do_check_download_filesize").start();
                                        }
                                        break;
                                    case 404:
                                        // 没有找到文件
                                        response.setResult(subResp.getResponseCode(), "找不到文件");
                                        break;
                                    default:
                                        response.setResult(subResp.getResponseCode(), "更新失败!");
                                        break;
                                }
                            }
                        }
                        logger.debug("\t>>>>> nupdate.download:["
                                + (System.currentTimeMillis() - ss) + "]ms");
                    }
                    break;
                default:
                    response.setResult(code, "更新应用失败!");
                    break;
            }
        } catch (SocketTimeoutException e) {
            response.setResult(-1, "网络连接超时!");
        } catch (Exception e) {
            response.setResult(-1, "网络连接失败!");
        }
        return response;
    }

    /**
     * 续传下载
     * 
     * @param info 终端属性
     * @param url SUP服务器地址
     * @param path 文件保存路径
     * @param md5 下载文件的MD5值
     * @param startPos 下载起始值
     */
    public void continueUpdate(final TerminalInfo info, final String url, final String path,
            final String md5, final int startPos, final int endPos) throws IOException, Exception {
        boolean isAppend = true;
        OutputStream os = null;
        byte[] body = encodeSup(info, 1, 1, md5, startPos, endPos);
        // 构造HTTP请求对象
        URL get = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) get.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        // 连接到服务端超时时间
        connection.setConnectTimeout(15 * 1000);
        // 新增请求超时时间
        connection.setReadTimeout(10 * 1000);
        connection.setRequestProperty("Connection", "close");
        connection.setRequestProperty("Content-Length", String.valueOf(body.length));

        // 将编码后的内容提交到服务端
        os = connection.getOutputStream();
        os.write(body);
        os.flush();
        os.close();

        int code = connection.getResponseCode();
        switch (code) {
            case HttpURLConnection.HTTP_OK:
                logger.debug("\t>>>>> continueUpdate[HTTP响应长度]:" + connection.getContentLength());
                // 获取输入流
                final InputStream is = connection.getInputStream();
                // 获取指定字节的内容,92个字节是不包含文件内容在内的TLV数据,需要再增加14个字节内容
                // ,这14个字节的内容为tag=715的字符串内容
                byte[] result = new byte[92];
                is.read(result);
                if (null != result) {
                    Object obj = decodeSup(result, SupResponse.class);
                    if (null != obj) {
                        // 统计发送的流量
                        listener.trafficNotify(body.length);
                        // 统计接收的流量
                        listener.trafficNotify(result.length);
                        SupResponse subResp = (SupResponse) obj;
                        if (subResp.getFilelen() > 0) {
                            switch (subResp.getResponseCode()) {
                                case 200:
                                    String sMd5 = null;
                                    // 设置MD5值
                                    if (null != subResp.getMd5()) {
                                        sMd5 = SysUtils.byteArray2Md5(subResp.getMd5());
                                    }
                                    writeFile(url, info, path, sMd5, startPos, endPos, is, isAppend);
                                    break;
                                default:
                                    listener.onUpdateError();
                                    break;
                            }
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * 离线消息推送接口
     */
    @Override
    public void offLineMsgPushConfirm(int appid) {
        SxGetoffmessageReq request = new SxGetoffmessageReq();
        request.setAppid(appid);
        setDstModuleId(request, ESBAddrConstatns.IMCENTER_ADDRESS);
        serverBiz.offlineMsgPushConfirm(request);
    }

    /**
     * 写文件
     * 
     * @param is 输入流
     * @param path 文件保存路径
     * @param override 是否追加文件结尾 false:否[重新生成文件] true:是[]
     * @throws IOException
     */
    private void writeFile(InputStream is, String path, boolean override) throws IOException {
        OutputStream os = null;
        byte[] buffer = new byte[MAX_BUFFER];
        // 已经获取的文件大小
        int c = 0;
        logger.debug((override ? "续写文件" : "重头写"));
        try {
            os = new FileOutputStream(new File(path), override);
            int pos;
            while ((pos = is.read(buffer)) != -1) {
                os.write(buffer, 0, pos);
                c += pos;
            }
        } catch (Exception e) {
            System.err.println("\t>>>>>>ERROR:" + e);
            // 重连下载
        } finally {
            if (null != os) {
                os.flush();
                os.close();
            }
            // 计算流量消耗
            listener.trafficNotify(c);
            IOUtils.closeQuietly(is);
            logger.debug("\t>>>>>>已经下载的文件大小:" + c);
        }
    }

    /**
     * 写文件
     * 
     * @param url SUP服务器地址
     * @param tInfo 终端信息
     * @param path 文件保存路径
     * @param md5 文件MD5
     * @param startPos 起始位置
     * @param endPos 结束位置
     * @param is 输入流
     * @param override 是否追加文件结尾 false:否[重新生成文件] true:是[]
     * @throws IOException
     */
    void writeFile(final String url, final TerminalInfo tInfo, final String path, final String md5,
            final int startPos, final int endPos, final InputStream is, final boolean override)
            throws IOException, Exception {
        OutputStream os = null;
        byte[] buffer = new byte[MAX_BUFFER];
        // 已经获取的文件大小
        int c = 0;
        logger.debug((override ? "续写文件" : "重头写"));
        final File file = new File(path);
        try {
            os = new FileOutputStream(file, true);
            int pos;
            while ((pos = is.read(buffer)) != -1) {
                os.write(buffer, 0, pos);
                c += pos;
            }
        } catch (Exception e) {
            System.err.println("\t>>>>>>ERROR:" + e);
            // 重连下载 发起新的请求
            if (RECONNECT_COUNT < 2) {
                logger.debug("\t>>>>>>ERROR.RETRY:" + RECONNECT_COUNT);
                RECONNECT_COUNT++;
                // TODO 重试 1.当前文件大小 2.MD5值
                continueUpdate(tInfo, url, path, md5, getFileLength(path), endPos);
            } else {
                logger.debug("\t>>>>>>ERROR.RETRY.FAILURE:" + RECONNECT_COUNT
                        + ",Notify to UI Client!");
                // TODO 通知客户端当前更新已经失败
                RECONNECT_COUNT = 0;
                listener.onUpdateError();
            }
        } finally {
            if (null != os) {
                os.flush();
                os.close();
            }
            // 计算流量消耗
            listener.trafficNotify(c);
            IOUtils.closeQuietly(is);
            logger.debug("\t>>>>>>已经下载的文件大小:" + c);
        }
    }

    /**
     * 对SUP请求内容进行编码
     * 
     * @param info 终端对象信息
     * @param reqType 请求类型 0：查询更新 1：更新
     * @param downloadPolicy 0：强制更新下发内容 1：强制更新不下发内容
     * @param md5 文件MD5值
     * @param startPos 起始位置
     * @param endPos 文件结束位置
     * @return
     */
    private byte[] encodeSup(TerminalInfo info, int reqType, int downloadPolicy, String md5,
            int startPos, int endPos) {
        // 设置请求对象参数
        SupRequest request = new SupRequest();
        setSupRequest(info, request);
        request.setReqType(reqType);
        request.setDownloadPolicy(downloadPolicy);
        if (null != md5 && !"".equals(md5)) {
            try {
                request.setMd5(SysUtils.md5Encrypt(md5));
            } catch (StringIndexOutOfBoundsException e) {
                System.err.println("\t>>>>>>SysUtils.md5Encrypt.exception:" + e);
            }
        }
        if (startPos > 0) {
            request.setStartPos(startPos);
        }

        if (endPos > 0) {
            request.setEndPos(endPos);
        }
        // 编码后的内容,实际请求的内容
        BeanTLVEncoder beanEncoder = new BeanTLVEncoder();
        TLVEncodeContext encode = beanEncoder.getEncodeContextFactory()
                .createEncodeContext(request.getClass(), null);
        byte[] body = ByteUtils.union(beanEncoder.encode(request, encode));
        return body;
    }

    /**
     * 对SUP响应体进行解码
     * 
     * @param body
     * @param type
     * @return
     */
    private Object decodeSup(byte[] body, Class<?> type) {
        BeanTLVDecoder beanDecoder = new BeanTLVDecoder();
        Object obj = beanDecoder.decode(body.length, body,
                beanDecoder.getDecodeContextFactory()
                        .createDecodeContext(type, null));
        return obj;
    }

    int getFileLength(String path) {
        int length = 0;
        File file = null;
        try {
            file = new File(path);
            length = (int) file.length();
        } catch (Exception e) {
        }
        return length;
    }

    void setSupRequest(TerminalInfo info, SupRequest request) {
        request.setAppId(info.getAppid());
        request.setAppver(info.getAppver());
        request.setBackgroup(info.getBackground());
        request.setDirectionKey(info.getDirectionKey());
        request.setFontSize(info.getFontSize());
        request.setHeight(info.getHeight());
        request.setHsman(info.getHsman());
        request.setHstype(info.getHstype());
        request.setImei(info.getImei());
        request.setImsi(info.getImsi());
        request.setNumberKey(info.getNumberKey());
        request.setPlat(info.getPlat());
        request.setShortname(info.getShortName());
        request.setTouch(info.getIsTouch());
        request.setVideoSupport(info.getVideoSupport());
        request.setWidth(info.getWidth());
        request.setWifi(info.getWifi());
    }

    /**
     * 日志统计接口
     */
    @Override
    public NetResponse logStatistics(LcsLogStatisticsRequest request) {
        NetResponse response = new NetResponse();
        request.setFlags((short) 0x0120);
        setDstModuleId(request, ESBAddrConstatns.LCS_ADDRESS);
        LcsLogStatisticsResponse resp = serverBiz.logStatistics(request);
        if (null != resp) {
            response.setResult(resp.getResponseCode(), resp.getResponseMsg());
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 申请快聊
     * 
     * @param usex 性别
     * @return
     */
    @Override
    public NetApplyFastChatResponse applyFastChat(String usex) {
        NetApplyFastChatResponse response = new NetApplyFastChatResponse();
        SxApplyFastTalkReq request = new SxApplyFastTalkReq();
        request.setUsex(usex);
        setDstModuleId(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
        SxApplyFastTalkResp resp = serverBiz.applyFastChat(request);
        if (null != resp) {
            switch (resp.getResponseCode()) {
                case 200:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    if (resp.getNextRespCode() == 616) {
                        response.setNew(true);
                    }
                    response.setDestSkyid(resp.getDestSkyid());
                    response.setCreateQueueWaitTime(resp.getCreateQueueWaitTime());
                    break;
                default:
                    response.setResult(resp.getResponseCode(), resp.getResponseMsg());
                    break;
            }
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 离开快聊
     * 
     * @param destSkyid
     * @return
     */
    @Override
    public NetResponse leaveFastChat(int destSkyid) {
        NetResponse response = new NetResponse();
        SxLeaveFastTalkReq request = new SxLeaveFastTalkReq();
        request.setDestSkyid(destSkyid);
        setDstModuleId(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
        SxLeaveFastTalkResp resp = serverBiz.leaveFastChat(request);
        if (null != resp) {
            response.setResult(resp.getResponseCode(), resp.getResponseMsg());
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 基于复合数据日志接口
     * 
     * @param req
     * @return
     */
    @Override
    public NetResponse complexRequest(String complexMessage) {
        NetResponse response = new NetResponse();
        LcsAndroidComplexRequest request = new LcsAndroidComplexRequest();
        request.setComplexMessage(complexMessage);
        setDstModuleId(request, ESBAddrConstatns.LCS_ADDRESS);
        LcsAndroidComplexResponse resp = serverBiz.complexMessage(request);
        if (null != resp) {
            response.setResult(resp.getResponseCode(), resp.getResponseMsg());
        } else {
            response.setNetError();
        }
        return response;
    }

    /**
     * 彻底删除联系人
     * 
     * @param restoreIds 可恢复联系人ID
     * @return
     */
    @Override
    public NetResponse completeDeleteContacts(ArrayList<Integer> restoreIds) {
        NetResponse response = new NetResponse();
        SxCompleteDeleteContactsReq request = new SxCompleteDeleteContactsReq();
        request.setRestoreIds(restoreIds);
        setDstModuleId(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
        SxCompleteDeleteContactsResp resp = serverBiz.completeDeleteContacts(request);
        if (null != resp) {
            response.setResult(resp.getResponseCode(), resp.getResponseMsg());
        } else {
            response.setNetError();
        }
        return response;

    }

    /**
     * 比较终端的UID
     * 
     * @param tuid
     * @return
     */
    public NetCompareTerminalUIDResp compareTerminalUID(byte[] tuid) {
        NetCompareTerminalUIDResp response = new NetCompareTerminalUIDResp();
        SxCompareTerminalUIDReq request = new SxCompareTerminalUIDReq();
        request.setTuid(tuid);
        setDstModuleId(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
        SxCompareTerminalUIDResp resp = serverBiz.compareTerminalUID(request);
        if (null != resp) {
            response.setResult(resp.getResponseCode(), resp.getResponseMsg());
            switch (resp.getResponseCode()) {
                case 200:
                    response.setResult(resp.getCompareResult());
                    break;
                default:
                    break;
            }
        } else {
            response.setNetError();
        }
        return response;
    }
}
