
package test.android.skymobi.messenger.net;

import android.skymobi.messenger.net.beans.SxGetNearbyReq;
import android.skymobi.messenger.net.beans.commons.BlackList;
import android.skymobi.messenger.net.beans.commons.ConfigInfo;
import android.skymobi.messenger.net.beans.commons.ContactsStatus;
import android.skymobi.messenger.net.beans.commons.ContactsStatusItem;
import android.skymobi.messenger.net.beans.commons.DataInfo;
import android.skymobi.messenger.net.beans.commons.FriendsList;
import android.skymobi.messenger.net.beans.commons.Location;
import android.skymobi.messenger.net.beans.commons.MsgType;
import android.skymobi.messenger.net.beans.commons.NearUser;
import android.skymobi.messenger.net.beans.commons.OnlineStatus;
import android.skymobi.messenger.net.beans.commons.RecommendMsg;
import android.skymobi.messenger.net.beans.commons.RestorableContacts;
import android.skymobi.messenger.net.beans.commons.SimpleStatus;
import android.skymobi.messenger.net.beans.commons.SimpleStatusItem;
import android.skymobi.messenger.net.beans.commons.SimpleUserInfo;
import android.skymobi.messenger.net.beans.commons.SimpleUserInfoItem;
import android.skymobi.messenger.net.beans.lcs.LcsLogStatisticsRequest;
import android.skymobi.messenger.net.beans.lcs.LcsPreferences;
import android.skymobi.messenger.net.client.bean.NetAddFriendResponse;
import android.skymobi.messenger.net.client.bean.NetApplyFastChatResponse;
import android.skymobi.messenger.net.client.bean.NetBindResponse;
import android.skymobi.messenger.net.client.bean.NetBlackResponse;
import android.skymobi.messenger.net.client.bean.NetCalcFriendsResponse;
import android.skymobi.messenger.net.client.bean.NetChatRequest;
import android.skymobi.messenger.net.client.bean.NetChatResponse;
import android.skymobi.messenger.net.client.bean.NetContacts;
import android.skymobi.messenger.net.client.bean.NetContactsPhone;
import android.skymobi.messenger.net.client.bean.NetContactsResultInfo;
import android.skymobi.messenger.net.client.bean.NetContactsVersionResponse;
import android.skymobi.messenger.net.client.bean.NetDownloadImageRespInfo;
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
import android.skymobi.messenger.net.utils.ParserUtils;
import android.skymobi.messenger.net.utils.SysUtils;

import com.skymobi.android.TerminalInfo;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientBizTest extends AbsClientNetTestCase {

    @Test
    public void checkAccess() {
        // 仅仅检查连接是否成功
        try {
            System.out.println(" > 仅仅检查UA连接是否成功 ");
            logintInit();
            Thread.sleep(5 * 60 * 1000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkAccessStatus() {
        logintInit();
        System.out.println(" > 仅仅检查UA连接是否成功 ");
        if (!response.isSuccess()) {
            return;
        }
        try {
            NetUserInfoResponse ph = clientBiz.getUserInfo(this.token);
            if (ph.isSuccess()) {
                System.out.println(" > response:" + ph);
                NetUserInfo nui = ph.getUserInfo();
                // System.out.println(" >> 自定义头像:"+(nui.getUdefineportrait() ==
                // null ? "Null":"null"));
                System.out.println(" >> 斯凯ID:" + nui.getSkyId());
                System.out.println(" >> 城市:" + nui.getUcity());
                System.out.println(" >> 省份:" + nui.getUprovince());
                System.out.println(" >> 签名:" + nui.getUsignature());
                System.out.println(" >> 昵称:" + nui.getPersonnickname());
                System.out.println(" >> 性别:" + nui.getUsex());
                clientBiz.offLineMsgPushConfirm(2934);
            } else {
                System.out.println(" >> 错误提示:" + ph.getResultHint());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据SKYID获取用户名
     */
    @Test
    public void getUserNamebySkyid() {
        // 405211060,405211061,405212152,405211377,405211553,405210963,405212475
        NetGetUsernameResponse response = clientBiz.getUsernameBySkyid(995211077);
        if (null != response) {
            if (response.isSuccess()) {
                System.out.println(" > 用户名:" + response.getUsername());
            } else if (response.isSkyidOk()) {
                System.out.println(" SKYID 不存在");
            } else if (response.isUsernameOk()) {
                System.out.println(" 用户名不存在");
            } else {
                System.out.println(" > response.hit:" + response.getResultHint());
            }
        }
    }

    /**
     * 检查是否绑定
     */
    @Test
    public void checkResponse() {
        NetBindResponse response =
                clientBiz.getBind();
        System.out.println("checkResponse() > " + response);
        if (response.isSuccess()) {
            System.out.println("\t>>>>>> 请求成功");
        } else {
            System.out.println("\t>>>>>>" + response.getResultCode() + "|"
                    + response.getResultHint());
        }
        try {
            Thread.sleep(10 * 1000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void bind() {
        // logintInit();
        NetBindResponse response = clientBiz.bind();
        if (null != response) {
            System.out.println(" > bind:" + response);
            if (response.isBound()) {
                if (response.isImsiSame()) {
                    System.out.println(" > 绑定,IMSI一致");
                } else {
                    System.out.println(" > 绑定,IMSI不一致");
                }
            } else {
                System.out.println(" > 未绑定");
            }
        } else {
            System.out.println(" > 网络失败");
        }
        try {
            Thread.sleep(10000l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 注册
     */
    @Test
    public void qregister() {
        NetRegResponse response = clientBiz.qregister();
        if (null != response) {
            System.out.println(" > qregister.response:" + response);
        }
    }

    /**
     * 带用户名的注册
     */
    @Test
    public void register() {
        // logintInit();
        // if(!response.isUsernameExists()){
        NetRegResponse response = clientBiz.register("zhangxiao919", "123456");
        if (response.isSuccess()) {
            System.out.println(" > register.response:" + response);
        } else {
            System.out.println(" > " + response.getResultCode() + "|" + response.getResultHint());
        }
        // }
    }

    @Test
    public void login2() {
        NetLoginResponse response = clientBiz.login("bluestome", "123456");
        if (response.isSuccess()) {
            System.out.println(" > response:" + response);
            byte[] pwd = response.getEncryptPasswd();
            if (null != pwd) {
                response = clientBiz.login("bluestome", pwd);
                System.out.println(" > 调用返回密码登录响应:" + response);
                if (response.isSuccess()) {
                    System.out.println(" > 调用返回密码登录成功 ");
                } else {
                    System.out.println(" > 调用返回密码登录失败 ");
                }
            }
        }
        try {
            Thread.sleep(5 * 1000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改用户名
     */
    @Test
    public void setNickname() {
        logintInit();
        if (response.isSuccess()) {
            NetResponse ph = clientBiz.setUserNickName(this.skyid, this.token, "bluestomes");
            System.out.println(" > response:" + ph);
        }
        try {
            Thread.sleep(10000l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取用户信息
     */
    @Test
    public void getUserinfo() {
        logintInit();
        if (response.isSuccess()) {
            NetUserInfoResponse ph = clientBiz.getUserInfo(this.token);
            System.out.println(" > response:" + ph);
            NetUserInfo nui = ph.getUserInfo();
            System.out.println(" >> 自定义头像:"
                    + (nui.getUdefineportrait() == null ? nui.getUuidPortrait() : nui
                            .getUdefineportrait()));
            System.out.println(" >> 昵称:" + nui.getNickname());
            System.out.println(" >> 城市:" + nui.getUcity());
            System.out.println(" >> 省份:" + nui.getUprovince());
            System.out.println(" >> 签名:" + nui.getUsignature());
            System.out.println(" >> 个性昵称:" + nui.getPersonnickname());
            System.out.println(" >> 性别:" + nui.getUsex());
            System.out.println(" >> 手机号码:" + nui.getUtelephone());
            // NetDownloadResponse downloadResp =
            // clientBiz.downloadImage(IS_URL, nui.getUdefineportrait(), 90, 0);
            // if(downloadResp.isSuccess()){
            // //TODO 下载成功
            // System.out.println(" > file.length:"+downloadResp.getLength());
            // }
        } else {
            System.out.println(response.getResultHint());
        }
        try {
            Thread.sleep(10 * 1000l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送消息
     */
    @Test
    public void sendChatMsg() {
        logintInit();
        if (response.isSuccess()) {
            for (int i = 0; i < 1; i++) {
                NetChatRequest chat = new NetChatRequest();
                // chat.setDestSkyids("405211060,405211061,405212152,405211377,405211553,405210963,405212475");
                chat.setDestSkyids("312545762");
                // chat.setMsgContent(" 我是杭州的");
                // chat.setDestSkyids("295956857,273966849,295236494,274921564,293720649,271808050,275603914,271321939,296227529,299029233,296712228,298066698,299179078,283788827,298606443,298713330,272836796,150350216,299151626,267226107,294118342,294338251,297392775,292699587,286146126,294715744,282497586,270082834,273597623,289582979,296349454,298198914,276302059,298447798,271397952,298794132,286599131,288319414,298499924,278456945,295011568,281322235,292929499,275120830,296040917,297541766,280670996,293614225,298572476,285615806,293608181,295298080,271627200,298428084,298825357,277384411,282737254,289041312,292813659,261074053,297683239,295985442,293950698,296222242,293061465,278456152,270005415,294938985,273965191,298134040,298628841,298116179,283266408,292832954,290909659,281223230,297527763,294563478,294889105,225988264,292265748,298446420,246568783,286972826,263012391,296356180,285774354,283369017,291756173,285713039,293612638,160551250,280723981,295787086,295861171,296107539,295831613,294393340,251353048,295772106,298758760,284758200,294641316,273236484,299047195,295816731,298442377,294651264,297249175,299046853,280968580,294343593,286561203,294658487,297300622,294195680,293383017,298564184,295016259,276892260");
                chat.setMsgContent("你好【" + i + "】，很高兴认识你!");
                // chat.setMsgContent("今天晴，明天多云，后天继续晴!");
                // chat.setNickName("天气预报小助手");
                chat.setChatMsgType((byte) 1);
                NetChatResponse ph = clientBiz.sendChatMsg(chat);
                System.out.println(" > response:" + ph);
                try {
                    if (ph.isSuccess()) {
                        if (ph.isUserOnline()) {
                            System.out.println(" > " + ph.getResultHint());
                        } else {
                            System.out.println(" > " + ph.getResultHint());
                        }
                    }
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        try {
            Thread.sleep(5 * 60 * 1000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改密码
     */
    @Test
    public void modifyPwd() {
        logintInit();
        if (response.isSuccess()) {
            NetLoginResponse loginResp = response;
            System.out.println(" > loginResp:" + loginResp);
            NetmodifyPwdResponse ph = clientBiz.modifyPwd(loginResp.getToken(), "175651", "111111");
            if (ph.isSuccess()) {
                System.out.println(" > newPwd:" + ph.getEncryptPasswd());
            } else {
                System.out.println(" > login ERROR!");
                System.out.println(" > login MSG");
            }
        }
        try {
            Thread.sleep(10000l);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 忘记密码
     */
    @Test
    public void forgetPwd() {
        // logintInit();
        // if (response.isSuccess()) {
        NetLoginResponse loginResp = response;
        System.out.println(" > loginResp:" + loginResp);
        NetForgetPwdResponse ph = clientBiz.forgetPwd("bluestome", "111111");
        System.out.println(" > response:" + ph);
        if (ph.isSuccess()) {
            System.out.println(" > forgetPwd success!");
        } else {
            System.out.println(" > isBound ERROR!" + ph.isBound());
            System.out.println(" > isUplinkShutdown ERROR!" + ph.isUplinkShutdown());
            System.out.println(" > isUsernameNotExists ERROR!" + ph.isUsernameNotExists());
        }
        // }
        try {
            Thread.sleep(10000l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取指定联系人状态
     */
    @Test
    public void getSpecifiedContactsStatus() {
        logintInit();
        // String contactsSkyids =
        // "182335457,295956857|182349318,312563862|186906250,282583953|186906252,291761053|186906253,293805761|186906254,315277803|186906261,293236971|186906264,293236971|186906265,306668754|186921812,168497011|187634501,306668754|187634502,293805761|187634512,293236971|187634513,282583953|187634514,293236971|187634515,291761053|187634516,315277803|188920133,168497011|188920135,168497011|189065662,312545762|189082018,310586193|189438507,168497011|182335457,295956857|182349318,312563862|186906250,282583953|186906252,291761053|186906253,293805761|186906254,315277803|186906261,293236971|186906264,293236971|186906265,306668754|186921812,168497011|187634501,306668754|187634502,293805761|187634512,293236971|187634513,282583953|187634514,293236971|187634515,291761053|187634516,315277803|188920133,168497011|188920135,168497011|189065662,312545762|189082018,310586193|189438507,168497011";
        String contactsSkyids = "|";
        if (response.isSuccess()) {
            NetLoginResponse loginResp = response;
            System.out.println(" > loginResp:" + loginResp);
            // "11942,405193442", "11950,13989899550"
            NetSpecifiedContactsStatusResponse ph = clientBiz.getSpecifiedContactsStatus(
                    contactsSkyids, null, 0);
            System.out.println(" > response:" + ph);
            if (ph.isSuccess()) {
                System.out.println(" > getSpecifiedContactsStatus success!");
                System.out.println(" > totalSize:" + ph.getTotalSize());
                System.out.println(" > updateTime:" + ph.getUpdateTime());
                for (ContactsStatusItem obj : ph.getItems()) {
                    System.out.println("\t\t>" + obj.getContactId());
                    for (ContactsStatus sobj : obj.getItems()) {
                        System.out.println("\t\t\t>" + sobj.getNickname() + "|"
                                + sobj.getImageHead() + "|" + sobj.getStatus() + "|"
                                + sobj.getUsignature());
                    }
                }
            } else {
                System.out.println(" > " + ph.getResultHint());
            }
        }
        try {
            Thread.sleep(10000l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取联系人版本
     */
    @Test
    public void getContactsVersion() {
        logintInit();
        if (response.isSuccess()) {
            NetLoginResponse loginResp = response;
            System.out.println(" > loginResp:" + loginResp);
            NetContactsVersionResponse ph = clientBiz.getContactsVersion();
            System.out.println(" > response:" + ph);
            if (ph.isSuccess()) {
                System.out.println(" > getSpecifiedContactsStatus success!");
                System.out.println(" > updateTime:" + ph.getUpdateTime());
            } else {
                System.out.println(" > " + ph.getResultHint());
            }
        }
        try {
            Thread.sleep(10000l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取好友联系人信息
     */
    @Test
    public void getBuddyUserinfo() {
        logintInit();
        if (response.isSuccess()) {
            NetLoginResponse loginResp = response;
            System.out.println(" > loginResp:" + loginResp);
            NetUserInfoResponse ph = clientBiz.getBuddyUserInfo(405227758);
            System.out.println(" > response:" + ph);
            if (ph.isSuccess()) {
                System.out.println(" > getSpecifiedContactsStatus success!");
                System.out.println(" > userInfo:" + ph.getUserInfo());
                if (null != ph.getUserInfo()) {
                    System.out.println(" > SKYID: " + ph.getUserInfo().getSkyId());
                    System.out.println(" > 个性昵称" + ph.getUserInfo().getPersonnickname());
                    System.out.println(" > 昵称" + ph.getUserInfo().getNickname());
                    System.out.println(" > 手机号码:" + ph.getUserInfo().getUmobile());
                    System.out.println(" > 手机号码2:" + ph.getUserInfo().getUtelephone());
                    System.out.println(" >> 头像字段：" + ph.getUserInfo().getUuidPortrait());
                }
                System.out.println(" > user.status:" + ph.getStatus());
            } else {
                System.out.println(" > " + ph.getResultHint());
            }
        }
        try {
            Thread.sleep(10000l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置是否可推荐
     */
    @Test
    public void setRecommend() {
        logintInit();
        if (response.isSuccess()) {
            NetLoginResponse loginResp = response;
            System.out.println(" > loginResp:" + loginResp);
            NetResponse ph = clientBiz.setRecommend(false, false);
            System.out.println(" > response:" + ph);
            if (ph.isSuccess()) {
                System.out.println(" > setRecommend success!");
            } else {
                System.out.println(" > " + ph.getResultHint());
            }
        }
        try {
            Thread.sleep(10000l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取是否推荐的值
     */
    @Test
    public void getSetRecommend() {
        logintInit();
        if (response.isSuccess()) {
            NetLoginResponse loginResp = response;
            System.out.println(" > loginResp:" + loginResp);
            NetGetSetRecommendResponse ph = clientBiz.getSetRecommendValue();
            if (ph.isSuccess()) {
                System.out.println(" > getRecommend success! value:"
                        + (ph.isRecommend() ? "推荐" : "不推荐"));
                System.out.println(" > getRecommend success! value:"
                        + (ph.isHideLBS() ? "隐藏LBS" : "不隐藏LBS"));
            } else {
                System.out.println(" > " + ph.getResultHint());
            }
        }
        try {
            Thread.sleep(10000l);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 用户反馈
     */
    @Test
    public void addFeedBack() {
        logintInit();
        if (response.isSuccess()) {
            NetLoginResponse loginResp = response;
            System.out.println(" > loginResp:" + loginResp);
            for (int i = 0; i < 10; i++) {
                NetResponse ph = clientBiz.feedBack("小黄牛",
                        "你好,测试添加反馈记录," + System.currentTimeMillis());
                System.out.println(" > response:" + ph);
                if (ph.isSuccess()) {
                    System.out.println(" > getSpecifiedContactsStatus success!");
                    try {
                        Thread.sleep(500l);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println(" > " + ph.getResultHint());
                    break;
                }
            }
        }
        try {
            Thread.sleep(10000l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加黑名单用户
     */
    @Test
    public void addBlackList() {
        logintInit();
        if (response.isSuccess()) {
            NetLoginResponse loginResp = response;
            System.out.println(" > loginResp:" + loginResp);
            NetBlackResponse ph = clientBiz.addBlackList(0, 405193442);
            System.out.println(" > response:" + ph);
            if (ph.isSuccess()) {
                System.out.println(" > addBlackList success!");
            } else {
                System.out.println(" > " + ph.getResultHint());
            }
        }
        try {
            Thread.sleep(10000l);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 删除黑名单列表用户
     */
    @Test
    public void delBlackList() {
        logintInit();
        if (response.isSuccess()) {
            NetLoginResponse loginResp = response;
            System.out.println(" > loginResp:" + loginResp);
            NetBlackResponse ph = clientBiz.delBlackList(0, 405193442);
            System.out.println(" > response:" + ph);
            if (ph.isSuccess()) {
                System.out.println(" > delBlackList success!");
            } else {
                System.out.println(" > " + ph.getResultHint());
            }
        }
        try {
            Thread.sleep(10000l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取推荐好友
     */
    @Test
    public void getFriendsList() {
        logintInit();
        if (response.isSuccess()) {
            NetLoginResponse loginResp = response;
            System.out.println(" > loginResp:" + loginResp);
            NetGetFriendstResponse ph = clientBiz.getFriendsList(0, 50, 0L);
            System.out.println(" > response:" + ph);
            if (ph.isSuccess()) {
                System.out.println(" > delBlackList success!");
                System.out.println(" > start:" + ph.getStart());
                System.out.println(" > totalSize:" + ph.getTotalSize());
                // System.out.println(" > friends:"+ph.getFiendList());
                System.out.println(" > friends:" + ph.getFiendList().size());
                System.out.println(" > update:" + ph.getUpdateTime());
                for (FriendsList fl : ph.getFiendList()) {
                    // NetCalcFriendsResponse response =
                    // clientBiz.getRecommendRelation(fl.getSkyId(), token);
                    // if(null != response && response.isSuccess()){
                    // System.out.println(" > destSkyid:" +
                    // response.getDestSkyid());
                    // System.out.println(" > talkReason:" +
                    // response.getTalkReason());
                    // }
                    System.out.println(" \t> skyId:" + fl.getSkyId());
                    System.out.println(" \t> nickName:" + fl.getNickname());
                    System.out.println(" \t> detailReason:" + fl.getDetailReason());
                    System.out.println(" \t> RecommendReason:" + fl.getRecommendReason());
                    System.out.println(" \t> TalkReason:" + fl.getTalkReason());
                    System.out.println(" \t> Sex:" + fl.getSex());
                    NetUserInfoResponse userInfoResp = clientBiz.getBuddyUserInfo(fl.getSkyId());
                    if (null != userInfoResp && userInfoResp.isSuccess()) {
                        System.out.println("userInfoResp.userInfo:" + userInfoResp.getUserInfo());
                    }
                    /**
                    **/
                }
                /**
                **/
                System.out.println(" > updateTime:" + ph.getUpdateTime());
            } else {
                System.out.println(" > " + ph.getResultHint());
            }

        }
        try {
            Thread.sleep(1000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 计算推荐联系人
     */
    @Test
    public void calsRecommendsFriends() {
        logintInit();
        if (response.isSuccess()) {
            NetCalcFriendsResponse response = clientBiz.calcFriends(token);
            if (null != response && response.isSuccess()) {
                System.out.println(" >> 计算推荐联系人请求成功 ");
            }
        }
        try {
            Thread.sleep(15 * 1000L);
        } catch (Exception e) {

        }
    }

    /**
     * 批量操作联系人
     */
    @Test
    public void operatorContacts() {
        logintInit();
        if (response.isSuccess()) {

            for (int i = 1; i < 11; i++) {
                clientBiz.getContactsList(1348055985793L, 1, 100);
            }
            try {
                Thread.sleep(10 * 1000L);
            } catch (Exception e) {

            }
            List<Integer> cids = new ArrayList<Integer>();
            cids.add(188920134);
            NetLoginResponse loginResp = response;
            System.out.println(" > loginResp:" + loginResp);
            ArrayList<NetContacts> list = new ArrayList<NetContacts>();
            NetContacts contacts = new NetContacts();
            contacts.setContactName("测试3");
            contacts.setContactType((byte) 0);
            ArrayList<NetContactsPhone> phoneList = new ArrayList<NetContactsPhone>();
            NetContactsPhone phone = new NetContactsPhone();
            phone.setPhone("18621839695");
            phoneList.add(phone);
            contacts.setAction((byte) 1);
            contacts.setPhoneList(phoneList);
            list.add(contacts);

            NetOperateContactsResponse ph = clientBiz.operateContacts(list);
            System.out.println(" > response:" + ph);
            if (ph.isSuccess()) {
                System.out.println(" > operatorContacts success!");
                ArrayList<NetContactsResultInfo> resultList = ph.getResultInfo();
                for (NetContactsResultInfo ri : resultList) {
                    System.out.println(" > contactId:" + ri.getContactId());
                    System.out.println(" > sequenceId:" + ri.getSequenceId());
                    System.out.println(" > code:" + ri.getCode());
                    System.out.println(" > action:" + ri.getAction());
                }
                System.out.println(" > 联系人版本号:" + ph.getUpdateTime());
            } else {
                System.out.println(" > " + ph.getResultHint());
            }

            try {
                Thread.sleep(10 * 1000L);
            } catch (Exception e) {
            }

            NetGetContactsListResponse ncl = clientBiz.getContactsList(0L, 1, 100);
            if (ph.isSuccess()) {
                System.out.println(" > getContactsList success!");
                System.out.println(" > start:" + ncl.getStart());
                System.out.println(" > totalSize:" + ncl.getTotalSize());
                System.out.println(" > update:" + ncl.getUpdateTime());
            }

        }
        try {
            Thread.sleep(10000l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 增量同步联系人
     */
    @Test
    public void synchContacts() {
        logintInit();
        if (response.isSuccess()) {
            NetLoginResponse loginResp = response;
            System.out.println(" > loginResp:" + loginResp);
            NetSyncContactsResponse ph = clientBiz.syncContacts(0, 0, 10);
            System.out.println(" > response:" + ph);
            if (ph.isSuccess()) {
                System.out.println(" > synchContacts success!");
                System.out.println(" > start:" + ph.getStart());
                System.out.println(" > totalSize:" + ph.getTotalSize());
                ArrayList<NetContacts> cList = ph.getContactsList();
                for (NetContacts nc : cList) {
                    System.out.println(" > contactId:" + nc.getContactId());
                    System.out.println(" > contactName:" + nc.getContactName());
                    System.out.println(" > contactType:" + nc.getContactType());
                    System.out.println(" > sequenceId:" + nc.getSequenceId());
                    System.out.println(" > group:" + nc.getGroup());
                    System.out.println(" > memo:" + nc.getMemo());
                    ArrayList<NetContactsPhone> phoneList = nc.getPhoneList();
                    for (NetContactsPhone nphone : phoneList) {
                        System.out.println(" \t" + nphone.getIndex());
                        System.out.println(" \t" + nphone.getBuddyId());
                        System.out.println(" \t" + nphone.getNickname());
                        System.out.println(" \t" + nphone.getPhone());
                    }
                }
            } else {
                System.out.println(" > " + ph.getResultHint());
            }

        }
        try {
            Thread.sleep(10000l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加联系人
     */
    @Test
    public void addFriend() {
        logintInit();
        if (response.isSuccess()) {
            NetLoginResponse loginResp = response;
            System.out.println(" > loginResp:" + loginResp);
            // int[] skyids = new
            // int[]{405211060,405211061,405212152,405211377,405211553,405210963,405212475};
            int[] skyids = new int[] {
                    284263994, 284255140, 284775992, 284777039
            };
            for (int skyid : skyids) {
                // 1 运营帐号 2推荐联系人
                NetAddFriendResponse ph = clientBiz.addFriend(skyid, (byte) 2);
                System.out.println(" > response:" + ph);
                if (ph.isSuccess()) {
                    System.out.println(" > addFriend success!");
                    System.out.println(" > updateTime:" + ph.getUpdateTime());
                    System.out.println(" > contactId:" + ph.getContactId());
                } else {
                    System.out.println(" > " + ph.getResultHint());
                }
                try {
                    Thread.sleep(1000l);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            Thread.sleep(5000l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取推荐短信
     */
    @Test
    public void getRecommendedMsg() {
        logintInit();
        if (response.isSuccess()) {
            NetLoginResponse loginResp = response;
            System.out.println(" > loginResp:" + loginResp);
            NetGetRecommendedMsgResponse ph = clientBiz.getRecommendedMsg(0, 0, 10);
            System.out.println(" > response:" + ph);
            if (ph.isSuccess()) {
                System.out.println(" > getRecommendedMsg success!");
                System.out.println(" > msgTypeId:" + ph.getMsgTypeId());
                System.out.println(" > start:" + ph.getStart());
                System.out.println(" > totalSize:" + ph.getTotalSize());
                System.out.println(" > msgTypeList:" + ph.getMsgTypeList());
                System.out.println(" > textMessage:" + ph.getTextMessage());
            } else {
                System.out.println(" > " + ph.getResultHint());
            }

        }
        try {
            Thread.sleep(10000l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取联系人
     */
    @Test
    public void getContactsList() {
        StringBuilder sb = new StringBuilder();
        logintInit();
        if (response.isSuccess()) {
            NetLoginResponse loginResp = response;
            System.out.println(" > loginResp:" + loginResp);
            int totalSize = 716;
            int totalPage = (totalSize / 100 + (totalSize % 100 > 1 ? 1 : 0));
            for (int i = 0; i < totalPage; i++) {
                NetGetContactsListResponse ph = clientBiz.getContactsList(0, i, 100);
                System.out.println(" > response:" + ph);
                ArrayList<NetContacts> catList = new ArrayList<NetContacts>();
                if (ph.isSuccess()) {
                    System.out.println(" > getContactsList success!");
                    System.out.println(" > start:" + ph.getStart());
                    System.out.println(" > totalSize:" + ph.getTotalSize());
                    System.out.println(" > update:" + ph.getUpdateTime());
                    for (NetContacts contacts : ph.getContactsList()) {
                        System.out.println(" \t> contacts.contactid:" + contacts.getContactId());
                        System.out
                                .println(" \t> contacts.contactName:" + contacts.getContactName());
                        System.out
                                .println(" \t> contacts.contactType:" + contacts.getContactType());
                        for (NetContactsPhone phone : contacts.getPhoneList()) {
                            if (phone.getBuddyId() > 0) {
                                System.out.println("\t\t " + contacts.getContactId() + ","
                                        + phone.getBuddyId());
                                sb.append(contacts.getContactId()).append(",")
                                        .append(phone.getBuddyId()).append("|");
                            }
                        }
                        // contacts.setAction((byte)2);
                        // catList.add(contacts);
                    }
                    if (catList.size() > 0) {
                        NetOperateContactsResponse catResp = clientBiz.operateContacts(catList);
                        if (!catResp.isNetError()) {
                            if (catResp.isSuccess()) {
                                System.out.println(" > 删除联系人:" + catResp.getResultHint());
                            }
                        }
                    }

                    ph = clientBiz.getContactsList(0, 1, 100);
                    if (ph.isSuccess()) {
                        System.out.println(" > getContactsList success!");
                        System.out.println(" > start:" + ph.getStart());
                        System.out.println(" > totalSize:" + ph.getTotalSize());
                        System.out.println(" > update:" + ph.getUpdateTime());
                    }
                } else {
                    System.out.println(" > " + ph.getResultHint());
                }
            }
        }
        System.out.println(sb.toString());
    }

    /**
     * 获取黑名单列表
     */
    @Test
    public void getBlackList() {
        logintInit();
        if (response.isSuccess()) {
            NetLoginResponse loginResp = response;
            System.out.println(" > loginResp:" + loginResp);
            NetGetBlakListResponse resp = clientBiz.getBlackList(1, 20, 1);
            if (resp.isSuccess()) {
                System.out.println(" > getBlackList success!");
                System.out.println(" > start:" + resp.getStart());
                System.out.println(" > totalSize:" + resp.getTotalSize());
                ArrayList<BlackList> list = resp.getBlackList();
                for (BlackList black : list) {
                    System.out.println(" > skyid:" + black.getSkyId());
                    System.out.println(" > contactId:" + black.getContactId());
                    System.out.println(" > contanctName:" + black.getContactName());
                    System.out.println(" > nickName:" + black.getNickname());
                    System.out.println(" > usignature:" + black.getUsignature());
                    System.out.println(" > blackType:" + black.getBlackType());

                }
            }
        }
    }

    /**
     * 获取联系人状态
     */
    @Test
    public void getContactsStatusList() {
        logintInit();
        if (response.isSuccess()) {
            NetLoginResponse loginResp = response;
            System.out.println(" > loginResp:" + loginResp);
            for (int i = 1; i < 9; i++) {
                NetGetContactsStatusResponse ph = clientBiz.getContactsStatusList(i, 10);
                System.out.println(" > response:" + ph);
                if (ph.isSuccess()) {
                    System.out.println(" > getContactsStatusList success!");
                    System.out.println(" > start:" + ph.getStart());
                    System.out.println(" > totalSize:" + ph.getTotalSize());
                    for (ContactsStatusItem csi : ph.getContactsStatusList()) {
                        System.out.println(" > contactsStatusList:" + csi.getContactId());
                        for (ContactsStatus cs : csi.getItems()) {
                            System.out.println(" > \tcontactsStatus.Nickname:" + cs.getNickname());
                            System.out.println(" > \tcontactsStatus.Phone:" + cs.getPhone());
                            System.out.println(" > \tcontactsStatus.Relation:" + cs.getRelation());
                            System.out.println(" > \tcontactsStatus.SkyId:" + cs.getSkyId());
                            System.out.println(" > \tcontactsStatus.Status:" + cs.getStatus());
                            System.out.println(" > \tcontactsStatus.UserType:" + cs.getUserType());
                            System.out.println(" > \tcontactsStatus.userName:" + cs.getUserName());
                        }
                    }
                    System.out.println(" > update:" + ph.getUpdateTime());
                } else {
                    System.out.println(" > " + ph.getResultHint());
                }
            }
        }
        try {
            Thread.sleep(5000l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送名片
     */
    @Test
    public void sendVCard() {
        logintInit();
        if (response.isSuccess()) {
            NetLoginResponse loginResp = response;
            System.out.println(" > loginResp:" + loginResp);
            // String detail =
            // "<ssvd dat=李四,15158180876,,,,15158180890,[405211060,玩家227249179,0,256,],[,,,,],[,,,,],[,,,,],[405211061,玩家221546326,0,256,],/>张晓";
            String detail = "<ssvd dat=解雷,,,,,,[405210963,,1,0,], />解雷";
            Map map = ParserUtils.decoderVCard(detail);
            // 405213884
            NetChatResponse ph = clientBiz.sendVCard("405213884", "小黄牛", map);
            System.out.println(" > response:" + ph);
            if (ph.isSuccess()) {
                if (ph.isUserOnline()) {
                    System.out.println(" > sendVCard success!" + ph.getResultHint());
                } else {
                    // 提示发送离线消息
                    System.out.println(" > " + ph.getResultHint());
                }
            } else {
                System.out.println(" > " + ph.getResultHint());
            }

        }
        try {
            Thread.sleep(10000l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送语音消息
     */
    @Test
    public void sendAudioMsg() {
        logintInit();
        if (response.isSuccess()) {
            NetLoginResponse loginResp = response;
            System.out.println(" > loginResp:" + loginResp);
            int ci = 0;
            while (ci++ < 1) {
                NetChatRequest chat = new NetChatRequest();
                // chat.setDestSkyids("405211060,405211061,405212152,405211377,405211553,405212177,405213884,405213375,405213787");
                chat.setDestSkyids("168497011,315436348");
                chat.setAudioLen(14);
                chat.setAudioSize(13318);
                chat.setChatMsgType((byte) 2);
                // chat.setMd5("981426114602957f146c4291cb0888d1");
                chat.setMd5("981426114602957f146c4291cb09f0d1");
                chat.setFormat("AMR");
                NetResponse ph = clientBiz.sendChatMsg(chat);
                // System.out.println(" > response:"+ph);
                if (ph.isSuccess()) {
                    System.out.println(" > [" + ci + "] sendAudioMsg success!");
                } else {
                    System.out.println(" > " + ph.getResultHint());
                }
                try {
                    Thread.sleep(5000L);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            Thread.sleep(10000l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置用户信息
     */
    @Test
    public void setUserInfo() {
        logintInit();
        if (response.isSuccess()) {
            NetUserInfo nui = new NetUserInfo();
            // nui.setPersonnickname("不吃猫的鱼");
            // nui.setUprovince("江西");
            // nui.setUsignature("");
            // nui.setUsex("2");
            nui.setUsignature("a b c d e f g 我 的 签 名 都 有 空 格");
            // nui.setUdefineportrait("8ff53410bcd640f08dc3277d5083533a");
            NetResponse resp2 = clientBiz.setUserInfo(token, nui);
            if (resp2.isSuccess()) {
                System.out.println(" > 设置用户信息成功:" + resp2.getResultHint());
            } else {
                System.out.println(" > " + resp2.getResultHint());
            }

        }
    }

    /**
     * 上传文件
     */
    @Test
    public void uploadFs() {
        logintInit();
        int i = 1;
        while (i < 2) {
            FileInputStream fis = null;
            byte[] body = null;
            try {
                fis = new FileInputStream(new File("d:\\1345632331000.amr"));
                if (null != fis) {
                    body = new byte[fis.available()];
                    fis.read(body);
                    if (null != body && body.length > 0) {
                        NetUploadResponse fileResp = clientBiz.uploadFs(FS_URL, skyid, token,
                                "ARM", body);
                        if (null != fileResp) {
                            System.out.println(" > fileResp:" + fileResp);
                            if (fileResp.isNetError()) {
                                System.out.println(fileResp.getResultHint());
                            } else {
                                if (fileResp.isSuccess()) {
                                    System.out.println(" > 文件MD5值:" + fileResp.getMd5());
                                } else {
                                    System.out.println(fileResp.getResultHint());
                                }
                            }
                        } else {
                            System.out.println("上传失败 ");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            i++;
        }
        try {
            Thread.sleep(1000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从文件服务下载文件
     */
    @Test
    public void downloadFs() {
        logintInit();
        if (response.isSuccess()) {
            OutputStream out = null;
            String md5 = "48befb8021f8c716ccf37657c11a6374";
            ArrayList<NetFsDownloadReq> rlist = new ArrayList<NetFsDownloadReq>();
            NetFsDownloadReq dq = new NetFsDownloadReq(md5, 0);
            rlist.add(dq);

            NetFsDownloadResponse response = clientBiz.downloadFs(FS_URL, skyid, token, rlist);
            if (null != response) {
                try {
                    if (response.isNetError()) {
                        System.out.println(response.getResultHint());
                    } else {
                        if (response.isSuccess()) {
                            for (NetFsDownloadFile file : response.getFileList()) {
                                if (file.getFileSize() > 0) {
                                    out = new FileOutputStream(new File("d:" + File.separator
                                            + "tmp" + File.separator + "image" + File.separator
                                            + "FS_" + System.currentTimeMillis() + ".jpg"));
                                    IOUtils.write(file.getFileData(), out);
                                    out.close();
                                }
                                try {
                                    Thread.sleep(2000);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                System.out.println("\t 上传的MD5："
                                        + md5);
                                System.out.println("\t 下发的MD5："
                                        + SysUtils.byteArray2Md5(file.getMd5()));
                            }
                            System.out.println(" > " + response.getResultHint());
                        } else {
                            System.out.println(response.getResultHint());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 向图片服务上传图片文件
     */
    @Test
    public void uploadImage() {
        logintInit();
        if (response.isSuccess()) {
            int skyid = response.getSkyId();
            FileInputStream fis = null;
            byte[] body = null;
            try {
                fis = new FileInputStream(new File("d:\\test_trans.png"));
                if (null != fis) {
                    body = new byte[fis.available()];
                    fis.read(body);
                    if (null != body && body.length > 0) {
                        NetUploadResponse response = clientBiz.uploadImage(
                                "http://115.238.91.229:2580/webfile", 2934, body, skyid);
                        if (response.isNetError()) {
                            System.out.println(response.getResultHint());
                        } else {
                            if (response.isSuccess()) {
                                System.out.println(" > id:" + response.getId());
                                System.out.println(" > imageName:" + response.getImageName());
                                System.out.println(" > imageUrl:" + response.getImageUrl());
                                NetUserInfo nui = new NetUserInfo();
                                nui.setUuidPortrait(response.getImageName());
                                NetResponse resp2 = clientBiz.setUserInfo(token, nui);
                                if (resp2.isSuccess()) {
                                    System.out.println(" > 设置用户信息成功:" + resp2.getResultHint());
                                }
                            } else {
                                System.out.println(response.getResultHint());
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != fis) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (null != body) {
                    body = null;
                }
            }
            try {
                Thread.sleep(1000L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 向图片服务上传图片文件
     */
    @Test
    public void sfsUploadImage() {
        logintInit();
        if (response.isSuccess()) {
            FileInputStream fis = null;
            byte[] body = null;
            try {
                fis = new FileInputStream(new File("d:\\Icon-72.png"));
                if (null != fis) {
                    body = new byte[fis.available()];
                    fis.read(body);
                    if (null != body && body.length > 0) {
                        NetUploadResponse response = clientBiz.sfsUploadImage(TEST_FS_URL, body,
                                "png", skyid, token);
                        if (response.isSuccess()) {
                            System.out.println(" > 上传文件的UUID:" + response.getUuid());
                        } else {
                            System.out.println(" > 上传图片错误提示：" + response.getResultHint());
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (null != fis) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (null != body) {
                    body = null;
                }
            }
        } else {
            System.out.println(" > 登录失败!");
        }
        try {
            Thread.sleep(10000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载图片
     */
    @Test
    public void sfsDownloadImage() {
        // String uuid = "0d31610d28b2423a93da2c2359e56780";
        String uuid = "04be7d3a4f69419bac9c608b19a4829d";
        logintInit();
        if (response.isSuccess()) {
            OutputStream out = null;
            ArrayList<NetDownloadImageRespInfo> rlist = new ArrayList<NetDownloadImageRespInfo>();
            NetDownloadImageRespInfo req = new NetDownloadImageRespInfo();
            req.setUuid(uuid);
            req.setStartPos(0);
            req.setWidth(72);
            req.setFileExtName("jpg");
            rlist.add(req);
            NetImageDownloadResponse resp = clientBiz.sfsDownloadImage(TEST_FS_URL, skyid, token,
                    rlist);
            if (resp.isSuccess()) {
                System.out.println(" > 文件数量:" + resp.getFileNum());
                ArrayList<NetDownloadImageRespInfo> list = resp.getFileList();
                for (NetDownloadImageRespInfo nd : list) {
                    System.out.println(" > nd.width:" + nd.getWidth());
                    System.out.println(" > nd.height:" + nd.getHeight());
                    System.out.println(" > nd.fileSize:" + nd.getFileSize());
                    System.out.println(" > nd.uuid:" + nd.getUuid());
                    System.out.println(" > nd.extName:" + nd.getFileExtName());
                    try {
                        out = new FileOutputStream(new File("d:" + File.separator + "tmp"
                                + File.separator + "image" + File.separator + nd.getUuid() + "."
                                + nd.getFileExtName()));
                        IOUtils.write(nd.getFileData(), out);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            out.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                System.out.println(" > ERROR:" + resp.getResultCode() + " | "
                        + resp.getResultHint());
            }
        }
        try {
            Thread.sleep(1000L);
        } catch (Exception e) {

        }
    }

    /**
     * 检查版本更新
     */
    @Test
    public void checkSupupdate() throws IOException {
        NetSupResponse response = clientBiz.checkSupupdate(SUP_URL, new TerminalInfo());
        System.out.println(" > code:" + response.getResultCode());
        if (!response.isNetError()) {
            if (response.isSuccess()) {
                if (response.isNeedUpdate()) {
                    System.out.println("**************>可选更新<**************");
                    System.out.println(" > 延迟时间：" + response.getCheckAfterTimes());
                    System.out.println(" > 间隔时间：" + response.getCheckInterval());
                    System.out.println(" > 更新内容:" + response.getFeature());
                    System.out.println(" > md5:" + response.getMd5());
                    System.out.println(" > 提示：" + response.getResultHint());
                    System.out.println(" > 应用对外版本号：" + response.getAppOutVersion());
                    System.out.println(" > 文件长度:" + response.getFileLength());
                } else {
                    System.out.println("**************>强制更新<**************");
                    System.out.println(" > 延迟时间：" + response.getCheckAfterTimes());
                    System.out.println(" > 间隔时间：" + response.getCheckInterval());
                    System.out.println(" > 更新内容:" + response.getFeature());
                    System.out.println(" > 提示:" + response.getResultHint());
                    System.out.println(" > md5:" + response.getMd5());
                }
            }
        } else {
            System.out.println(" > " + response.getResultHint());
        }
    }

    /**
     * 更新版本
     */
    @Test
    public void update() {
        String md5 = "2eac1c54c9e230393d93794b45cae805";
        // 断点续传样例
        NetSupResponse response = clientBiz.update(new TerminalInfo(), SUP_URL, null, 0);
        // 下载样例
        if (response.isSuccess()) {
            System.out.println(" > 提示：" + response.getResultHint());
            System.out.println(" > 文件MD5值:" + response.getMd5());
            System.out.println(" > 文件大小:" + response.getFileLength());
            System.out.println(" > 更新内容:" + response.getFeature());
            System.out.println(" > startPos:" + response.getStartPos());
            System.out.println(" > 文件长度:" + response.getBody().length);
        } else {
            System.out.println(" > 失败 :" + response.sprintResult());
            System.out.println(" > 提示:" + response.getResultHint());
            System.out.println(" > 更新内容:" + response.getFeature());
            System.out.println(" > startPos:" + response.getStartPos());
        }
    }

    /**
     * 在线测试
     */
    @Test
    public void onLine() {
        try {
            Thread.sleep(10 * 60 * 1000L);
        } catch (Exception e) {

        }
    }

    @Test
    public void update2() {
        final long fileSize = 9633606;
        final String path = "d:\\tmp\\download\\shouxin.apk";
        String md5 = "5c7a2d7d52fa54005bdba1a34e9a3567";
        File file = null;
        try {
            // new Thread(new Runnable() {
            // private File file = null;
            // @Override
            // public void run() {
            // while(true){
            // try{
            // file = new File(path);
            // if(file.exists()){
            // long fileS = file.length();
            // System.out.println(" ---<已下载的文件大小:"+fileS + "> ---");
            // if(fileS == fileSize ){
            // break;
            // }
            // }
            // Thread.sleep(50);
            // }catch(Exception e){
            // }
            // }
            // }
            // }).start();
            // d:4194380 t:9633606
            file = new File(path);
            NetSupResponse response = clientBiz.nupdate(new TerminalInfo(), SUP_URL, path, md5,
                    586256, 2417793);
            if (!response.isNetError()) {
                if (response.isSuccess()) {
                    System.out.println(" > 提示：" + response.getResultHint());
                    System.out.println(" > 文件MD5值:" + response.getMd5());
                    System.out.println(" > 文件大小:" + response.getFileLength());
                    System.out.println(" > 更新内容:" + response.getFeature());
                    System.out.println(" > startPos:" + response.getStartPos());
                    System.out.println(" > 应用对外版本号：" + response.getAppOutVersion());
                } else {
                    System.out.println(" > 错误信息:" + response.getResultHint());
                }
            } else {
                System.out.println(" > 网络连接失败!");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 立即重连
     */
    @Test
    public void reconnect() {
        clientBiz.close();
        clientBiz.reconnect();
        try {
            Thread.sleep(15 * 1000L);
        } catch (Exception e) {

        }
    }

    /**
     * 获取用户在线状态
     */
    @Test
    public void getOnlineStatus() {
        logintInit();
        // String destSkyid =
        // "405211060,405211061,405212152,405211377,405211553,405210963,405212475";
        String destSkyid = "295956857,273966849,295236494,274921564,293720649,271808050,275603914,271321939,296227529,299029233,296712228,298066698,299179078,283788827,298606443,298713330,272836796,150350216,299151626,267226107,294118342,294338251,297392775,292699587,286146126,294715744,282497586,270082834,273597623,289582979,296349454,298198914,276302059,298447798,271397952,298794132,286599131,288319414,298499924,278456945,295011568,281322235,292929499,275120830,296040917,297541766,280670996,293614225,298572476,285615806,293608181,295298080,271627200,298428084,298825357,277384411,282737254,289041312,292813659,261074053,297683239,295985442,293950698,296222242,293061465,278456152,270005415,294938985,273965191,298134040,298628841,298116179,283266408,292832954,290909659,281223230,297527763,294563478,294889105,225988264,292265748,298446420,246568783,286972826,263012391,296356180,285774354,283369017,291756173,285713039,293612638,160551250,280723981,295787086,295861171,296107539,295831613,294393340,251353048,295772106,298758760,284758200,294641316,273236484,299047195,295816731,298442377,294651264,297249175,299046853,280968580,294343593,286561203,294658487,297300622,294195680,293383017,298564184,295016259,276892260";
        NetOnlineStatusResponse response = clientBiz.getOnlineStatus(destSkyid);
        if (response.isSuccess()) {
            List<OnlineStatus> list = response.getOnlineStatusList();
            for (OnlineStatus status : list) {
                System.out.println(" > " + status.getBuddyId() + "|"
                        + (status.getStatus() == 1 ? "在线" : "离线"));
            }
        }
    }

    /**
     * 获取用户状态
     */
    @Test
    public void getContactsStatusList2() {
        logintInit();
        if (response.isSuccess()) {
            int indexPage = 1;
            int totalPage = 2;
            do {
                NetGetContactsStatus2Response resp = clientBiz.getContactsStatusList(indexPage, 20,
                        (byte) 0,
                        63967);
                if (resp.isSuccess()) {
                    int total = resp.getTotalSize();
                    totalPage = (total / 20) + ((total % 20 == 0) ? 0 : 1);
                    System.out.println("\ttotal:" + total);
                    ArrayList<SimpleStatusItem> list = resp.getContactsStatusList();
                    if (null != list && list.size() > 0) {
                        for (SimpleStatusItem item : list) {
                            System.out.println(" > contactId:" + item.getContactId());
                            ArrayList<SimpleStatus> slist = item.getList();
                            if (null != slist && slist.size() > 0) {
                                for (SimpleStatus ss : slist) {
                                    System.out.println(" \t>> SKYID:" + ss.getSkyId());
                                    System.out.println(" \t>> 号码:" + ss.getPhone());
                                    System.out.println(" \t>> 状态:" + ss.getStatus());
                                    System.out.println(" \t>> 用户类型:" + ss.getUserType());
                                }
                            }
                        }
                    }
                }
                indexPage++;
                System.out.println("\tindexPage:" + indexPage);
                System.out.println("\ttotalPage:" + totalPage);
            } while (indexPage < (totalPage + 1));
            try {
                Thread.sleep(1000L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取用户简单信息
     */
    @Test
    public void getContactsList2() {
        logintInit();
        if (response.isSuccess()) {
            int indexPage = 1;
            int totalPage = 2;
            do {
                NetGetContactsList2Response resp = clientBiz.getContactsList(indexPage, 20, 0, 0);
                if (resp.isSuccess()) {
                    int total = resp.getTotalSize();
                    totalPage = (total / 20) + ((total % 20 == 0) ? 0 : 1);
                    System.out.println("\ttotal:" + total);
                    ArrayList<SimpleUserInfoItem> list = resp.getContactsList();
                    if (null != list && list.size() > 0) {
                        for (SimpleUserInfoItem item : list) {
                            System.out.println(" > contactId:" + item.getContactId());
                            ArrayList<SimpleUserInfo> slist = item.getList();
                            if (null != slist && slist.size() > 0) {
                                int i = 1;
                                for (SimpleUserInfo ss : slist) {
                                    System.out.println(" \t>> 第" + i + "条记录");
                                    System.out.println(" \t>> SKYID:" + ss.getSkyId());
                                    System.out.println(" \t>> 用户名:" + ss.getUserName());
                                    System.out.println(" \t>> 昵称:" + ss.getNickname());
                                    System.out.println(" \t>> 手机号码:" + ss.getPhone());
                                    System.out.println(" \t>> 签名:" + ss.getUsignature());
                                    // if(ss.getSkyId() == 310429802){
                                    // NetUserInfoResponse ph =
                                    // clientBiz.getBuddyUserInfo(ss.getSkyId());
                                    // System.out.println(" > response:"+ph);
                                    // if(ph.isSuccess()){
                                    // if(null != ph.getUserInfo()){
                                    // System.out.println(" > SKYID: "+ph.getUserInfo().getSkyId());
                                    // System.out.println(" > 个性昵称" +
                                    // ph.getUserInfo().getPersonnickname());
                                    // System.out.println(" > 昵称" +
                                    // ph.getUserInfo().getNickname());
                                    // System.out.println(" > 手机号码:" +
                                    // ph.getUserInfo().getUmobile());
                                    // System.out.println(" > 手机号码2:" +
                                    // ph.getUserInfo().getUtelephone());
                                    // System.out.println(" >> 头像字段："+ph.getUserInfo().getUuidPortrait());
                                    // }
                                    // System.out.println(" > user.status:"+ph.getStatus());
                                    // }else{
                                    // System.out.println(" > "+ph.getResultHint());
                                    // }
                                    // }
                                    i++;
                                }
                            }
                        }
                    }
                }
                indexPage++;
                System.out.println("\tindexPage:" + indexPage);
                System.out.println("\ttotalPage:" + totalPage);
            } while (indexPage < (totalPage + 1));
        }
    }

    /**
     * 获取附近的人列表
     */
    @Test
    public void getNearByFriendsList() {
        logintInit();
        if (response.isSuccess()) {
            try {
                SxGetNearbyReq request = new SxGetNearbyReq();
                Location location = new Location();
                location.setLongitude("120.1016308");
                location.setLatitude("30.2754137");
                request.setLocation(location);
                request.setRecalculated((byte) 1);
                request.setAccurateLevel((byte) 1);
                NetGetNearByFriendResponse resp = clientBiz.getNearByFriends(request);
                if (resp.isSuccess()) {
                    if (null != resp.getUsers() && resp.getUsers().size() > 0) {
                        for (NearUser user : resp.getUsers()) {
                            System.out.println("\t>> Nickname:" + user.getNickname());
                            System.out.println("\t>> Skyid:" + user.getSkyId());
                            System.out.println("\t>> Sex:" + user.getUsex());
                            System.out.println("\t>> Signature:" + user.getUsignature());
                            System.out.println("\r\n");
                        }
                    }
                } else {
                    System.err.println(resp.getResultHint());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据用户名查找联系人
     */
    @Test
    public void getUserInfoByUserName() {
        logintInit();
        if (response.isSuccess()) {
            try {
                String userName = "zhangxiao910";
                NetGetUserInfoByUserNameResponse resp = clientBiz.getUserInfoByUserName(userName);
                if (null != resp) {
                    if (resp.isSuccess()) {
                        if (resp.isFinded()) {
                            NetUserInfo userInfo = resp.getUserInfo();
                            System.out.println("\t>> userName:" + userInfo.getUserName());
                            System.out.println("\t>> userType:" + resp.getUserType());
                            System.out.println("\t>> Skyid:" + userInfo.getSkyId());
                            System.out.println("\t>> nickName:" + userInfo.getPersonnickname());
                            System.out.println("\t>> 自定义头像:" + userInfo.getUuidPortrait());
                        } else {
                            System.out.println("未找到该[" + userName + "]用户");
                        }
                    } else {
                        System.out.println("请求失败");
                    }
                } else {
                    System.out.println("\t>> 网络异常");
                }
            } catch (Exception e) {

            }
        }

    }

    @Test
    public void getRecommendMsgType() {
        logintInit();
        if (response.isSuccess()) {
            NetGetRecommendedMsgTypeResponse resp = clientBiz.getRecommendMsgType(0L);
            if (resp.isSuccess()) {
                System.out.println("短信类型版本号:" + resp.getUpdateTime());
                if (resp.isHasUpdate()) {
                    ArrayList<MsgType> list = resp.getMsgTypeList();
                    for (MsgType type : list) {
                        System.out.println("\t>>>" + type.getMsgTypeId() + ""
                                + type.getMsgTypeName() + "|" + type.getAction() + "|"
                                + type.getUpdate());
                    }
                } else {
                    System.out.println("暂无更新");
                }
            }
        }
    }

    @Test
    public void getRecommendMsgByUpdatetime() {
        logintInit();
        if (response.isSuccess()) {
            int pageIndex = 0;
            boolean isContinue = true;
            do {
                NetGetRecommendedMsgNewResponse resp = clientBiz.getRecommendMsgNew(1004, 0L,
                        pageIndex, 50, 300);
                if (resp.isSuccess()) {
                    if (resp.isHasUpdate()) {
                        int totalSize = resp.getTotalSize();
                        int totalPage = totalSize / 50 + ((totalSize % 50 > 0) ? 1 : 0);
                        System.out.println("\t totalPage:" + totalPage + "|pageIndex:" + pageIndex);
                        System.out.println("\t resultCode:" + resp.getResultCode());
                        System.out.println("\t updateTime:" + resp.getUpdateTime());
                        System.out.println("\t totalSize:" + resp.getTotalSize());
                        System.out.println("\t Start:" + resp.getStart());
                        ArrayList<RecommendMsg> list = resp.getTextMessage();
                        for (RecommendMsg msg : list) {
                            if (!msg.isDelete()) {
                                System.out.println("\t>>>" + msg.getMsgId() + "|"
                                        + msg.getTextMessage() + "|" + msg.getAction());
                            } else {
                                System.out.println("\t 短信被删除");
                            }
                        }
                        if (totalPage >= pageIndex) {
                            pageIndex++;
                        } else {
                            isContinue = false;
                        }
                    } else {
                        System.out.println("暂无更新");
                        isContinue = false;
                    }
                } else {
                    isContinue = false;
                }
            } while (isContinue);
        }
    }

    @Test
    public void getConfiguration() {
        ArrayList<ConfigInfo> list = new ArrayList<ConfigInfo>();
        ConfigInfo info = new ConfigInfo();
        info.setConfigType((byte) 1);
        info.setUpdateTime(0L);
        list.add(info);
        info = new ConfigInfo();
        info.setConfigType((byte) 2);
        info.setUpdateTime(0L);
        list.add(info);
        logintInit();
        if (response.isSuccess()) {
            NetGetConfigurationResponse resp = clientBiz.getConfiguration(list);
            if (resp.isSuccess() && resp.isHasUpdate()) {
                list = resp.getConfigInfo();
                if (list.size() > 0) {
                    for (ConfigInfo tinfo : list) {
                        System.out.println(tinfo.getConfigType() + "|\r\n"
                                + tinfo.getConfigContent() + "|\r\n" + tinfo.getUpdateTime());
                    }
                } else {
                    System.out.println("响应体无内容");
                }
            } else {
                System.out.println("无更新 [" + resp.getResultHint() + "|" + resp.getResultCode()
                        + "]");
            }
        }
    }

    @Test
    public void getRestorableContacts() {
        logintInit();
        int pageSize = 100;
        if (response.isSuccess()) {
            int pageNum = 1;
            int pageIndex = 0;
            while (pageIndex++ < pageNum) {
                NetRestorableContactsResponse resp = clientBiz.getRestorableConacts(pageIndex, 100);
                if (resp.isSuccess()) {
                    System.out.println("start:" + resp.getStart() + "|totalSize:"
                            + resp.getTotalSize());
                    pageNum = resp.getTotalSize() / pageSize
                            + (resp.getTotalSize() % pageSize == 0 ? 0 : 1);
                    ArrayList<RestorableContacts> rcontacts = resp.getRestorableContacts();
                    if (null != rcontacts && rcontacts.size() > 0) {
                        for (RestorableContacts c : rcontacts) {
                            System.out.println("\tRID:" + c.getRestoreId());
                            System.out.println("\t联系人名称:" + c.getContactName());
                            System.out.println("\t用户名:" + c.getUserName());
                            System.out.println("\tSKYID:" + c.getSkyId());
                            System.out.println("\t头像:" + c.getImageHead());
                        }
                    } else {
                        System.out.println("\t没有可恢复的列表");
                    }
                } else {
                    System.out.println(resp.getResultHint() + ":" + resp.getResultCode());
                }
            }
        }
    }

    @Test
    public void restoreContacts() {
        logintInit();
        if (response.isSuccess()) {
            List<Integer> ids = new ArrayList<Integer>();
            ids.add(550);
            ids.add(551);
            ids.add(552);
            ids.add(553);
            ids.add(554);
            ids.add(555);
            ids.add(556);
            ids.add(557);
            NetRestoreContactsResp resp = clientBiz.restoreConacts(ids);
            if (resp.isSuccess()) {
                System.out.println(resp.getResultHint());
            } else {
                System.out.println("\t>>>" + resp.getResultCode() + "|" + resp.getResultHint());
            }
        }

    }

    @Test
    public void lcs() {
        logintInit();
        if (response.isSuccess()) {
            LcsLogStatisticsRequest request = new LcsLogStatisticsRequest();
            ArrayList<DataInfo> list = new ArrayList<DataInfo>();
            // 短信统计
            int smsCount = 230;
            int which_hour = (int) (System.currentTimeMillis() / 3600000);
            if (smsCount > 0) {
                DataInfo info = new DataInfo();
                info.setCount((short) smsCount);
                info.setData_code(LcsPreferences.MSGCODE_DATA_STATISTIC);
                info.setMsg_dest(LcsPreferences.MSG_DEST_SINGLE_SMS);
                info.setMsg_type(LcsPreferences.MSG_TYPE_TEXT);
                info.setWhich_hour(which_hour);
                list.add(info);
            }
            // 名片统计
            int netCardCount = 30;
            if (netCardCount > 0) {
                DataInfo info = new DataInfo();
                info.setCount((short) netCardCount);
                info.setData_code(LcsPreferences.MSGCODE_DATA_STATISTIC);
                info.setMsg_dest(LcsPreferences.MSG_DEST_SINGLE_NET);
                info.setMsg_type(LcsPreferences.MSG_TYEP_CARD);
                info.setWhich_hour(which_hour);
                list.add(info);
            }
            // 网络消息统计
            int netTextcount = 40;
            if (netTextcount > 0) {
                DataInfo info = new DataInfo();
                info.setCount((short) netTextcount);
                info.setData_code(LcsPreferences.MSGCODE_DATA_STATISTIC);
                info.setMsg_dest(LcsPreferences.MSG_DEST_SINGLE_NET);
                info.setMsg_type(LcsPreferences.MSG_TYPE_TEXT);
                info.setWhich_hour(which_hour);
                list.add(info);
            }
            // 网络语言消息统计
            int netVoicecount = 40;
            if (netVoicecount > 0) {
                DataInfo info = new DataInfo();
                info.setCount((short) netVoicecount);
                info.setData_code(LcsPreferences.MSGCODE_DATA_STATISTIC);
                info.setMsg_dest(LcsPreferences.MSG_DEST_SINGLE_NET);
                info.setMsg_type(LcsPreferences.MSG_TYPE_VOICE);
                info.setWhich_hour(which_hour);
                list.add(info);
            }
            // 群发消息统计
            int massMultiCount = 40;
            if (massMultiCount > 0) {
                DataInfo info = new DataInfo();
                info.setCount((short) massMultiCount);
                info.setData_code(LcsPreferences.MSGCODE_DATA_STATISTIC);
                info.setMsg_dest(LcsPreferences.MSG_DEST_MASS_MULTI);
                info.setMsg_type(LcsPreferences.MSG_TYPE_TEXT);
                info.setWhich_hour(which_hour);
                list.add(info);
            }

            // 对附近的人打招呼的次数
            int clickBuddyCount = 40;
            if (clickBuddyCount > 0) {
                DataInfo info = new DataInfo();
                info.setCount((short) clickBuddyCount);
                info.setData_code(LcsPreferences.MSGCODE_DATA_LBSSTATIC);
                info.setMsg_dest(LcsPreferences.MSG_DEST_SINGLE_SMS);
                info.setMsg_type(LcsPreferences.MSG_TYPE_CLICK_BUDDY);
                info.setWhich_hour(which_hour);
                list.add(info);
            }

            // 附近的人点击次数
            int clickLbsCount = 40;
            if (clickLbsCount > 0) {
                DataInfo info = new DataInfo();
                info.setCount((short) clickLbsCount);
                info.setData_code(LcsPreferences.MSGCODE_DATA_LBSSTATIC);
                info.setMsg_dest(LcsPreferences.MSG_DEST_DEFAULT);
                info.setMsg_type(LcsPreferences.MSG_TYPE_CLICK_LBS);
                info.setWhich_hour(which_hour);
                list.add(info);
            }

            // 跟几个人打了招呼
            int buddyPeopleCount = 40;
            if (buddyPeopleCount > 0) {
                DataInfo info = new DataInfo();
                info.setCount((short) buddyPeopleCount);
                info.setData_code(LcsPreferences.MSGCODE_DATA_LBSSTATIC);
                info.setMsg_dest(LcsPreferences.MSG_DEST_DEFAULT);
                info.setMsg_type(LcsPreferences.MSG_TYPE_BUDDY_PEOPLE);
                info.setWhich_hour(which_hour);
                list.add(info);
            }

            if (list.size() > 0) {
                request.setSkyid(315436348);
                request.setAppid(2934);
                request.setApp_ver(2506);
                request.setDataInfoList(list);
            }
            NetResponse resp = clientBiz.logStatistics(request);
            if (resp.isSuccess()) {
                System.out.println("\t 发送日志数据成功!");
            }
        }
    }

    @Test
    public void applyFastChat() {
        logintInit();
        if (response.isSuccess()) {
            String usex = "1";
            NetApplyFastChatResponse resp = clientBiz.applyFastChat(usex);
            if (null != resp && resp.isSuccess()) {
                if (resp.isNew()) {
                    System.out.println("\t 老朋友还在:" + resp.getDestSkyid() + ",需要等待["
                            + resp.getCreateQueueWaitTime() + "s] 建立会话");
                } else {
                    System.out.println("\t 和新朋友聊天了" + ",需要等待[" + resp.getCreateQueueWaitTime()
                            + "s] 建立会话");
                }
                try {
                    Thread.sleep(resp.getCreateQueueWaitTime() * 1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                System.out.println("\t 申请快聊失败," + resp.getResultCode());
            }
        }
    }

    @Test
    public void complexMessage() {
        logintInit();
        if (response.isSuccess()) {
            for (int i = 100; i < 10100; i++) {
                NetResponse resp = clientBiz
                        .complexRequest(" {\"tempid\":"
                                + i
                                + ",\"exceptionsDetail\":{},\"hardware\":{\"camera\":{\"cameraCount\":1},\"cpu\":{\"cpuFrequency\":\"299.11\",\"cpuType\":\"ARMv7 Processor rev 2 (v7l) \"},\"network\":{\"activiteNetwork\":\"TYPE_WIFI\",\"phoneNetworkType\":\"GSM\"},\"sd\":{\"availableSize\":\"6.85GB\",\"internalMemorySize\":\"1.24GB\",\"totalSize\":\"14.81GB\"},\"sersor\":{\"list\":[{\"typeVersion\":\"1\",\"typeName\":\"KXTF9 3-axis Accelerometer\",\"typeId\":1},{\"typeVersion\":\"1\",\"typeName\":\"AK8973 3-axis Magnetic field sensor\",\"typeId\":2},{\"typeVersion\":\"1\",\"typeName\":\"AK8973 Temperature sensor\",\"typeId\":7},{\"typeVersion\":\"1\",\"typeName\":\"ISL29030 Proximity sensor\",\"typeId\":8},{\"typeVersion\":\"1\",\"typeName\":\"Orientation sensor\",\"typeId\":3},{\"typeVersion\":\"1\",\"typeName\":\"ISL29030 Light sensor\",\"typeId\":5},{\"typeVersion\":\"1\",\"typeName\":\"Gravity Sensor\",\"typeId\":9},{\"typeVersion\":\"1\",\"typeName\":\"Linear Acceleration Sensor\",\"typeId\":10},{\"typeVersion\":\"1\",\"typeName\":\"Rotation Vector Sensor\",\"typeId\":11}],\"total\":9}},\"software\":{\"db\":{\"contact\":{\"isOk\":true,\"structure\":\"times_contacted|contact_status|custom_ringtone|has_phone_number|phonetic_name|phonetic_name_style|contact_status_label|lookup|contact_status_icon|last_time_contacted|display_name|sort_key_alt|in_visible_group|_id|starred|sort_key|display_name_alt|contact_presence|display_name_source|contact_status_res_package|contact_chat_capability|contact_status_ts|photo_id|send_to_voicemail\"},\"lanuncher\":{\"uri\":\"content://com.android.launcher.settings/favorites?notify\u003dtrue\",\"isOk\":true},\"rawContact\":{\"isOk\":true,\"structure\":\"times_contacted|phonetic_name|phonetic_name_style|contact_id|version|last_time_contacted|aggregation_mode|_id|name_verified|display_name_source|dirty|send_to_voicemail|account_type|custom_ringtone|sync4|sync3|sync2|sync1|deleted|account_name|display_name|sort_key_alt|starred|sort_key|display_name_alt|sourceid\"}},\"exceptions\":[],\"hsman\":\"motorola\",\"hstype\":\"ME525+\",\"imei\":\"354707041147044\",\"imsi\":\"460027684106522\",\"screenSize\":\"480x854\",\"sim\":{\"exceptions\":[],\"simState\":\"良好\",\"isOk\":true,\"structure\":\"name|number|emails|number2|_id\"},\"sysVersion\":\"10\"},\"currentTime\":\"2012-10-17 10:16:10\"}");
                if (null != resp && resp.isSuccess()) {
                    System.out.println("\t " + resp.getResultCode() + "|" + resp.getResultHint());
                } else {
                    System.out.println("\t 响应失败..没有响应");
                }
                try {
                    Thread.sleep(200L);
                } catch (InterruptedException e) {
                    System.out.println("\t 异常:" + e.getMessage());
                }
            }
        }
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
