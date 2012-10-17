
package test.android.skymobi.messenger.net;

import android.skymobi.messenger.net.client.INetClient;
import android.skymobi.messenger.net.client.NetClient;
import android.skymobi.messenger.net.client.NetListener;
import android.skymobi.messenger.net.client.bean.NetLoginResponse;
import android.skymobi.messenger.net.client.bean.NetResponse;
import android.skymobi.messenger.net.connection.IConnection;
import android.skymobi.messenger.net.connection.NetConfig;
import android.skymobi.messenger.net.notify.IServerBizNotify;
import android.skymobi.messenger.net.notify.impl.ServerBizNotify;

import com.skymobi.android.commons.logger.LogConfig;

import org.junit.After;
import org.junit.Before;

public abstract class AbsClientNetTestCase {

    protected IConnection connection = null;

    protected NetConfig config = new NetConfig();

    protected INetClient clientBiz = null;

    protected NetLoginResponse response = null;

    protected String token = "";

    protected int skyid = -1;

    protected IServerBizNotify serverBizNotify = new ServerBizNotify();

    // 文件服务地址
    protected static final String FS_URL = "http://sfs.skymobiapp.com:7002/";
    // 图片服务地址
    protected static final String IS_URL = "http://172.16.4.25/webfile";

    protected static final String TEST_FS_URL = "http://172.16.3.138:6010/";

    // SUP服务器地址
    protected static final String SUP_URL = "http://172.16.3.35:6011/";

    @Before
    public void init() {
        // config.setIp("aa.skymobiapp.com");
        // config.setPort(20000);
        // config.setIp("111.1.17.201");
        // config.setPort(32000);
        // config.setIp("172.16.3.147");
        // config.setPort(32000);
        config.setIp("172.16.3.35");
        config.setPort(6012);
        // config.setIp("115.238.91.240");
        // config.setPort(6012);
        // config.setIp("115.238.91.229");
        // config.setPort(6015);
        // config.setIp("115.238.91.226");
        // config.setPort(6012);
        // config.setIp("172.16.4.26");
        // config.setPort(20000);

        // Pt.setProperty("access_ip", "115.238.91.229");
        // Pt.setProperty("access_port", "6015");
        // Pt.setProperty("file_url", "http://115.238.91.229:16010/");
        // Pt.setProperty("sup_url", "http://115.238.91.229:16012/");
        // config.setIp("115.238.91.229");
        // config.setPort(6015);

        config.setHeartBeatTime(15 * 1000);
        config.setEncryptProtocol(false);
        // 日志配置
        LogConfig logConfig = LogConfig.getInstance();
        // 是否需要显示日志，将该参数设置为true即可
        logConfig.setLog(true);
        logConfig.setWrite(false);
        logConfig.setShowLog(true);
        clientBiz = NetClient.init(config, new NetListener());
        try {
            Thread.sleep(2000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录初始化方法 skyId=405211060, username=mp612320194, passwd=111111,
     * nickname=玩家227249179, skyId=405211061, username=mp613053543,
     * passwd=111111, nickname=玩家221546326 skyId=405212152 username=mp173724244
     * passwd=615590 skyId=405212476 username=mp447565669, passwd=111518,
     * nickname=玩家215326942 skyId=405213391, username=mp752869996,
     * passwd=972383, autoLoginPwd=<null>, nickname=玩家665171851 skyId=405213397,
     * token=<null>, username=mp776548570, passwd=630378 skyId=405213401,
     * token=<null>, username=mp778447676, passwd=251072 skyId=405213404,
     * token=<null>, username=mp745628517, passwd=010874 skyId=405213429,
     * token=<null>, username=mp712937117, passwd=994762, username=mp411750570
     * passwd=111111 skyId=405213646, token=<null>, username=mp681744166
     * ,passwd=296088 ***** TestServer ****** skyId=405213773, token=<null>,
     * username=mp085145281, passwd=265644, skyId=405213781, token=<null>,
     * username=mp083370434, passwd=174146, 2012-04-17 skyId=405213984,
     * username=mp407285475, passwd=111111,
     * skyId=405214333,token=<null>,username=mp545894646,passwd=950240,
     * skyId=405214379,token=<null>,username=mp580032340,passwd=541538,
     * skyId=405214454,token=<null>,username=mp718265540,passwd=874239,
     * skyId=405214456,token=<null>,username=mp790848787,passwd=175651,
     * skyId=405214764,token=<null>,username=mp069378223,passwd=693204
     * skyId=405214773,token=<null>,username=mp019491394,passwd=640824
     * skyId=405214775,token=<null>,username=mp018694551,passwd=987247,
     * skyId=405214795,token=<null>,username=mp023646941,passwd=225449
     * skyId=405214815,token=<null>,username=mp500609511,passwd=371699
     * skyId=405215510,token=<null>,username=bluestome032,passwd=123456
     * skyId=405214471,username=jane6,passwd:111111
     * skyId=405215906,token=<null>,username=zhangxiao918,passwd=123456 现网注册帐号
     * skyId=281433201,token=<null>,username=mp553048297,passwd=076532,
     * skyId=282044543,token=<null>,username=zhangxiao917,passwd=123456,
     * skyId=168497011,token=<null>,username=bluestome,passwd=123456
     * skyId=282620454,token=<null>,username=bluestome001,passwd=123456
     */
    protected void logintInit() {
        // response = clientBiz.login("chuntian","111111");
        // response = clientBiz.login("mp612320194", "111111");
        response = clientBiz.login("mp613053543", "111111");
        // response = clientBiz.login("mp419718935","346933");
        // response = clientBiz.login("mp714638766","449284");
        // response = clientBiz.login("mp752869996","111111");
        // response = clientBiz.login("mp712937117","994762");
        // response = clientBiz.login("mp681744166","296088");
        // response = clientBiz.login("mp411750570","111111");
        // response = clientBiz.login("mp083370434","111111");
        // response = clientBiz.login("mp790848787","111111");
        // response = clientBiz.login("jane6","441078");
        // response = clientBiz.login("zhangxiao918","123456");
        // response = clientBiz.login("zhangxiao919","123456");
        // response = clientBiz.login("mp010305591","123456");
        // response = clientBiz.login("mp500609511","371699");
        // response = clientBiz.login("mp023646941","225449");
        // response = clientBiz.login("mp553048297","076532");
        // response = clientBiz.login("zhangxiao917","123456");
        // response = clientBiz.login("mp075297319","111111");
        // response = clientBiz.login("mp032790678","423067");
        // response = clientBiz.login("bluestome", "123456");
        // response = clientBiz.login("zhangxiao917", "123456");
        // response = clientBiz.login("13616557709", "123456");
        // response = clientBiz.login("18621839690","123456");
        // response = clientBiz.login("13605808937","123456");
        // response = clientBiz.login("mp109076027","123456");
        // response = clientBiz.login("18768469457", "123456");
        // response = clientBiz.login("13777583939","898093");
        if (null != response) {
            setResponse(response);
            if (response.isSuccess()) {
                token = response.getToken();
                skyid = response.getSkyId();
            } else {
                System.out.println(" > " + response.getResultCode() + "|"
                        + response.getResultHint());
            }
            try {
                Thread.sleep(5 * 1000L);
            } catch (Exception e) {

            }
        }
    }

    /**
     * 离线消息推送确认
     */
    protected void pushOfflineMsgConfirm() {
        clientBiz.offLineMsgPushConfirm(config.getTerminal().getAppid());
        try {
            Thread.sleep(500L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 注销
     */
    protected void logout() {
        if (null != token && !"".equals(token)) {
            NetResponse response = clientBiz.logout(token);
            if (null != response && response.isSuccess()) {
                System.out.println(" > 注销: OK!");
            } else {
                if (null != response) {
                    System.out.println(" > " + response.getResultHint());
                } else {
                    System.out.println(" > response is null!");
                }
            }
        }
        try {
            Thread.sleep(5000l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void destory() {
        logout();
        if (null != connection && connection.isConnect()) {
            connection.close();
        }
    }

    public IConnection getConnection() {
        return connection;
    }

    public void setConnection(IConnection connection) {
        this.connection = connection;
    }

    public NetConfig getConfig() {
        return config;
    }

    public void setConfig(NetConfig config) {
        this.config = config;
    }

    /**
     * @return the response
     */
    public NetLoginResponse getResponse() {
        return response;
    }

    /**
     * @param response the response to set
     */
    public void setResponse(NetLoginResponse response) {
        this.response = response;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the skyid
     */
    public int getSkyid() {
        return skyid;
    }

    /**
     * @param skyid the skyid to set
     */
    public void setSkyid(int skyid) {
        this.skyid = skyid;
    }

}
