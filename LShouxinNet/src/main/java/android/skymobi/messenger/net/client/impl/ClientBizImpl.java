
package android.skymobi.messenger.net.client.impl;


/**
 * 客户端调用的业务实现类
 * 
 * @ClassName: ClientBizImpl
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-6 下午02:42:35
 */
public class ClientBizImpl  {

//    private IConnection connection = null;
//
//    private static IClientBiz instance = null;
//
//    private static IServerBiz serverBiz = null;
//
//    private IServerBizNotify serverBizNotify;
//
//    private ClientBizImpl(IConnection connection, IServerBizNotify serverBizNotify) {
//        this.connection = connection;
//        this.serverBizNotify = serverBizNotify;
//    }
//
//    public static IClientBiz getInstance(IConnection connection, IServerBizNotify serverBizNotify) {
//        if (null == instance) {
//            instance = new ClientBizImpl(connection, serverBizNotify);
//        }
//        if (null == serverBiz) {
//            serverBiz = ServerBiz.getInstance(connection, serverBizNotify);
//        }
//        return instance;
//    }
//
//    /**
//     * 检查绑定状态
//     */
//    @Override
//    public CheckBindStatus checkBindStatus2() {
//        CheckBindStatus response =  new CheckBindStatus();
//        GetUserInfoByImsiRequest request = new GetUserInfoByImsiRequest();
//        request.setSourceId(Constants.APP_SOURCE_ID);
//        setHeader(request, ESBAddrConstatns.PPA_ADDRESS);
//        GetUserInfoByImsiResponse status = serverBiz.checkBindStatus2(request);
//        if(null != status){
//            switch(status.getErrorCode()){
//                case 200:
//                    response.setBind(true);
//                    break;
//                case 140160:
//                case 160201:
//                default:
//                    response.setBind(false);
//                    break;
//            }
//            response.setCode(status.getErrorCode());
//            response.setMsg(status.getErrorMessage());
//        }else{
//            response.setCode(-1);
//            response.setBind(false);
//            response.setMsg("检查绑定状态失败,联网失败!");
//        }
//        return response;
//    }
//
//    /**
//     * 获取绑定状态
//     * 
//     * @param request
//     * @return 绑定状态响应对象
//     */
//    @Override
//    public BindStatus bind2() {
//        BindStatus response = new BindStatus();
//        JudgeMobileStatusRequest request = new JudgeMobileStatusRequest();
//        request.setSourceId(Constants.APP_SOURCE_ID);
//        setHeader(request, ESBAddrConstatns.PPA_ADDRESS);
//        JudgeMobileStatusResponse status = serverBiz.bind2(request);
//        if(null != status){
//            switch(status.getErrorCode()){
//                case 200:
//                    //成功
//                    response.setBind(status.getStatus());
//                    response.setRecvsmsmobile(status.getRecvsmsmobile());
//                    response.setSmscontent(status.getSmscontent());
//                    break;
//                default:
//                    //失败
//                    response.setBind(status.getStatus());
//                    break;
//            }
//        }else{
//            // 联网失败
//            response.setBind(-1);
//        }
//        return response;
//
//    }
//
//    @Override
//    public int bind() {
//        // TODO Auto-generated method stub
//        JudgeMobileStatusRequest request = new JudgeMobileStatusRequest();
//        request.setSourceId(Constants.APP_SOURCE_ID);
//        setHeader(request, ESBAddrConstatns.PPA_ADDRESS);
//        int status = serverBiz.bind(request);
//        return status;
//    }
//
//    @Override
//    public RegisterResponse qregister2() {
//        RegisterResponse response = new RegisterResponse();
//        RegisterReq request = new RegisterReq();
//        request.setSourceId(Constants.APP_SOURCE_ID);
//        setHeader(request, ESBAddrConstatns.PPA_ADDRESS);
//        RegisterResp status = serverBiz.register2(request);
//        if(null != status){
//            switch(status.getErrorCode()){
//                case 200:
//                    // 注册成功
//                    response.setUserInfo(status.getRegisterUserInfo());
//                    break;
//                default:
//                    // 注册失败
//                    response.setCode(status.getErrorCode());
//                    response.setMsg(status.getErrorMessage());
//                    break;
//            }
//        }else{
//            response.setCode(-1);
//        }
//        return response;
//    }
//
//    @Override
//    public int qregister() {
//        RegisterReq request = new RegisterReq();
//        request.setSourceId(Constants.APP_SOURCE_ID);
//        setHeader(request, ESBAddrConstatns.PPA_ADDRESS);
//        int status = serverBiz.register(request);
//        return status;
//    }
//
//    /**
//     * 登录方法2
//     * 
//     * @param request
//     * @return LoginPhoneResponse 登录响应对象
//     */
//    @Override
//    public LoginResponse login2(String username, String password) {
//        LoginResponse response = new LoginResponse();
//        LoginPhoneRequest request = new LoginPhoneRequest();
//        request.setSourceId(Constants.APP_SOURCE_ID);
//        request.setUsername(username);
//        request.setEncryptPasswd(SysUtils.pwdEncrypt(password));
//        setHeader(request, ESBAddrConstatns.PPA_ADDRESS);
//        LoginPhoneResponse respons = serverBiz.login2(request);
//        if(null != respons){
//            switch(respons.getErrorCode()){
//                case 200:
//                    //登录成功
//                    response.setAuthCodeId(respons.getAuthCodeId());
//                    response.setAuthCodeImg(respons.getAuthCodeImg());
//                    response.setCode(respons.getErrorCode());
//                    response.setEncryptPasswd(respons.getEncryptPasswd());
//                    response.setLastLogin(respons.getLastLogin());
//                    response.setPasswd(respons.getPasswd());
//                    response.setRecvsmsmobile(respons.getRecvsmsmobile());
//                    response.setSecretQTag(respons.getSecretQTag());
//                    response.setSkyId(respons.getSkyId());
//                    response.setSmscontent(respons.getSmscontent());
//                    response.setToken(respons.getToken());
//                    response.setUserInfo(respons.getUserInfo());
//                    break;
//                default:
//                    response.setCode(respons.getErrorCode());
//                    response.setMsg(respons.getErrorMessage());
//                    response.setSmscontent(respons.getSmscontent());
//                    response.setRecvsmsmobile(respons.getRecvsmsmobile());
//                    break;
//            }
//        }else{
//            //联网失败
//            response.setCode(-1);
//            response.setMsg("联网失败");
//        }
//        return response;
//    }
//
//    /**
//     * 用户登录，非同步方法
//     */
//    public int login(String username, String password) {
//        LoginPhoneRequest request = new LoginPhoneRequest();
//        request.setSourceId(Constants.APP_SOURCE_ID);
//        request.setUsername(username);
//        request.setEncryptPasswd(SysUtils.pwdEncrypt(password));
//        setHeader(request, ESBAddrConstatns.PPA_ADDRESS);
//        int status = serverBiz.login(request);
//        return status;
//    }
//
//    /**
//     * 注销用户,非同步方法
//     */
//    public int logout(String token) {
//        LogoutRequest request = new LogoutRequest();
//        request.setSourceId(Constants.APP_SOURCE_ID);
//        request.setToken(token);
//        request.setSource(Constants.APP_SOURCE);
//        setHeader(request, ESBAddrConstatns.PPA_ADDRESS);
//        int status = serverBiz.logout(request);
//        return status;
//    }
//
//    /**
//     * 修改密码
//     * @param token 授权码
//     * @param oldPwd 原密码
//     * @param newPwd 新密码
//     */
//    public int modifyPwd(String token, String oldPwd, String newPwd) {
//        ModifyPwdRequest request = new ModifyPwdRequest();
//        request.setToken(token);
//        request.setSourceId(Constants.APP_SOURCE_ID);
//        request.setEncryptOldPasswd(SysUtils.pwdEncrypt(oldPwd));
//        request.setEncryptNewPasswd(SysUtils.pwdEncrypt(newPwd));
//        setHeader(request, ESBAddrConstatns.PPA_ADDRESS);
//        int status = serverBiz.modifyPwd(request);
//        return status;
//    }
//
//    @Override
//    public int forgetPwd(String username, String newPwd) {
//        // TODO Auto-generated method stub
//        return 0;
//    }
//
//    @Override
//    public int setUserNickName(int skyid, String token, String nickName) {
//        SetUserInfoReq request = new SetUserInfoReq();
//        UserInfo user = new UserInfo();
//        user.setSkyId(skyid);
//        user.setNickname(nickName);
//        user.setPersonNickname(nickName);
//        request.setSourceId(Constants.APP_SOURCE_ID);
//        request.setUserInfo(user);
//        request.setToken(token);
//        setHeader(request, ESBAddrConstatns.PPA_ADDRESS);
//        return serverBiz.setUserInfo(request);
//    }
//    
//    @Override
//    public ResponseBean setUserNickName2(int skyid, String token, String nickName) {
//        SetUserInfoReq request = new SetUserInfoReq();
//        ResponseBean response = new ResponseBean();
//        UserInfo user = new UserInfo();
//        user.setSkyId(skyid);
//        user.setNickname(nickName);
//        user.setPersonNickname(nickName);
//        request.setSourceId(Constants.APP_SOURCE_ID);
//        request.setUserInfo(user);
//        request.setToken(token);
//        setHeader(request, ESBAddrConstatns.PPA_ADDRESS);
//        SetUserInfoResp ph = serverBiz.setUserInfo2(request);
//        if (ph==null){
//        	//联网失败        	
//        	response.setCode(-1);
//        	response.setMsg("联网失败");
//        }else{
//        	response.setCode(ph.getErrorCode());
//        	response.setMsg(ph.getErrorMessage());
//        }
//        return response;
//    }
//
//    /**
//     * 设置用户信息,完整
//     * @param skyid
//     * @param token
//     * @param userInfo
//     */
//    public void setUserInfo(int skyid,String token,UserInfo userInfo){
//        SetUserInfoReq request = new SetUserInfoReq();
//        request.setSourceId(Constants.APP_SOURCE_ID);
//        request.setUserInfo(userInfo);
//        request.setToken(token);
//        setHeader(request, ESBAddrConstatns.PPA_ADDRESS);
//        int status = serverBiz.setUserInfo(request);
//        if(status == 200){
//            //调用成功
//        }else{
//            //失败,可能存在联网失败
//        }
//    }
//    
//    @Override
//    public int getUserInfo(int skyid, String token, int loginTag) {
//        GetUserInfoWithTokenReq request = new GetUserInfoWithTokenReq();
//        request.setSourceId(Constants.APP_SOURCE_ID);
//        request.setSkyid(0);
//        request.setToken(token);
//        request.setLoginTag(0);
//        setHeader(request, ESBAddrConstatns.PPA_ADDRESS);
//        int status = serverBiz.getUserInfo(request);
//        if(status == 200){
//            return 200;
//        }else{
//            return status;
//        }
//    }
//
//    @Override
//    public int checkBindStatus() {
//        // TODO Auto-generated method stub
//        return 0;
//    }
//
//    @Override
//    public int uploadContactsList(ArrayList<Contacts> list) {
//        SxOperateContactsReq request = new SxOperateContactsReq();
//        ArrayList<Contacts> contactsList = new ArrayList<Contacts>();
//        for (Contacts cont : list) {
//            contactsList.add(cont);
//        }
//        request.setContactsList(contactsList);
//        setHeader(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
//        int status = serverBiz.uploadContactsList(request);
//        return status;
//    }
//
//    /**
//     * 增量同步联系人
//     */
//    public int syncContacts(long updateTime) {
//        SxSyncContactsReq  request = new SxSyncContactsReq();
//        request.setUpdateTime(updateTime);
//        setHeader(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
//        int status = serverBiz.synContacts(request);
//        return status;
//    }
//    
//    @Override
//    public int getContactsList(long updateTime, int start, int pageSize) {
//        SxGetContactsListReq request = new SxGetContactsListReq();
//        request.setSourceId(Constants.APP_SOURCE_ID);
//        request.setUpdateTime(updateTime);
//        request.setStart(start);
//        request.setPageSize(pageSize);
//        setHeader(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
//        int status = serverBiz.getContactsList(request);
//        return status;
//    }
//
//    @Override
//    public int getUpdateTimeReq() {
//        SxGetUpdateTimeReq request = new SxGetUpdateTimeReq();
//        request.setSourceId(Constants.APP_SOURCE_ID);
//        setHeader(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
//        int status = serverBiz.getContactVersion(request);
//        return status;
//    }
//
//    @Override
//    public int getRecommendFriendsList(int start, int pageSize) {
//        SxGetFriendsReq request = new SxGetFriendsReq();
//        request.setSourceId(Constants.APP_SOURCE_ID);
//        request.setStart(start);
//        request.setPageSize(pageSize);
//        setHeader(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
//        int status = serverBiz.getRecommendFriendsList(request);
//        return status;
//    }
//
//    @Override
//    public int getRecommendMsgList(int msgTypeId, int start, int pageSize, long updateTime) {
//        // TODO Auto-generated method stub
//        return 0;
//    }
//
//    @Override
//    public int getContactsStatus(int start, int pageSize) {
//        SxGetContactsStatusReq request = new SxGetContactsStatusReq();
//        request.setSourceId(Constants.APP_SOURCE_ID);
//        request.setStart(start);
//        request.setPageSize(pageSize);
//        setHeader(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
//        int status = serverBiz.getContactsStatus(request);
//        return status;
//    }
//
//    @Override
//    public int getSpecifiedContactsStatus(String contactSkyids, String contactPhones) {
//        SxGetSpecifiedContactsStatusReq request = new SxGetSpecifiedContactsStatusReq();
//        request.setSourceId(Constants.APP_SOURCE_ID);
//        request.setContactPhones(contactPhones);
//        request.setContactSkyids(contactSkyids);
//        setHeader(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
//        int status = serverBiz.getSpecifiedContactsStatus(request);
//        return status;
//    }
//
//    @Override
//    public int sendMessage(ArrayList<String> skyids, String sendNickname, String msgContent) {
//        if (skyids.size() == 0) {
//            return -1;
//        }
//        SxSendChatMsgReq request = new SxSendChatMsgReq();
//        String sts = SysUtils.list2String(skyids);
//        request.setSourceId(Constants.APP_SOURCE_ID);
//        request.setDestSkyids(sts);
//        request.setNickName(sendNickname);
//        request.setMsgContent(msgContent);
//        setHeader(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
//        int status = serverBiz.sendChatMessage(request);
//        return status;
//    }
//
//    @Override
//    public int sendVCardMsg(int destSkyids, String nickname, String vCardContent) {
//        // TODO Auto-generated method stub
//        return 0;
//    }
//
//    @Override
//    public int addBlackList(int contactId, int destSkyid) {
//        // TODO Auto-generated method stub
//        return 0;
//    }
//
//    @Override
//    public int delBlackList(int contactId, int destSkyid) {
//        // TODO Auto-generated method stub
//        return 0;
//    }
//
//    @Override
//    public int addFriends(int skyid, int contactType) {
//        SxAddFriendReq request = new SxAddFriendReq();
//        request.setDestSkyid(skyid);
//        request.setContactType((byte) contactType);
//        setHeader(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
//        int status = serverBiz.addFriends(request);
//        return status;
//    }
//
//    @Override
//    public int addFeedBack(String nickname, String content) {
//        // TODO Auto-generated method stub
//        SxAddFeedBackReq request = new SxAddFeedBackReq();
//        request.setSourceId(Constants.APP_SOURCE_ID);
//        request.setNickname(nickname);
//        request.setFeedContent(content);
//        setHeader(request, ESBAddrConstatns.SHOUXIN_ADDRESS);
//        int status = serverBiz.addFeedBack(request);
//        return status;
//    }
//
//    @Override
//    public int sendInvite(int buddyId) {
//        // TODO Auto-generated method stub
//        return 0;
//    }
//
//    @Override
//    public int getUserInfo(int buddyId) {
//        // TODO Auto-generated method stub
//        return 0;
//    }
//
//    @Override
//    public int setRecommended(boolean isRecommend) {
//        // TODO Auto-generated method stub
//        return 0;
//    }
//
//    @Override
//    public int checkUpdate(Object ua, int reqtype) {
//        // TODO Auto-generated method stub
//        return -1;
//    }
//
//    public IConnection getConnection() {
//        return connection;
//    }
//
//    public void setConnection(IConnection connection) {
//        this.connection = connection;
//    }
//
//    /**
//     * 设置请求头
//     * 
//     * @param request
//     * @param dstModuleId
//     */
//    private void setHeader(AbstractEsbT2ASignal request, int dstModuleId) {
//        request.setDstESBAddr(dstModuleId);
//    }
//
//    public IServerBizNotify getServerBizNotify() {
//        return serverBizNotify;
//    }
//
//    public void setServerBizNotify(IServerBizNotify serverBizNotify) {
//        this.serverBizNotify = serverBizNotify;
//    }

}
