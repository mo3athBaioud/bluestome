
package android.skymobi.messenger.net.client;

import android.skymobi.messenger.net.beans.commons.Audio;
import android.skymobi.messenger.net.beans.commons.FriendsList;
import android.skymobi.messenger.net.beans.commons.VCardContent;
import android.skymobi.messenger.net.client.bean.NetChangeStatNotify;
import android.skymobi.messenger.net.client.bean.NetChatNotify;
import android.skymobi.messenger.net.client.bean.NetFastTalkNotify;
import android.skymobi.messenger.net.client.bean.NetFriendsMsgNotify;
import android.skymobi.messenger.net.client.bean.NetMarketingMessageNotify;
import android.skymobi.messenger.net.client.bean.NetOnlineStateChangeNotify;
import android.skymobi.messenger.net.client.bean.NetStateNotify;
import android.skymobi.messenger.net.client.bean.NetSysMsgNotify;
import android.skymobi.messenger.net.client.bean.NetVCardNotify;

import com.skymobi.android.commons.logger.Logger;
import com.skymobi.android.commons.logger.LoggerFactory;

import java.util.ArrayList;
import java.util.Map;

/**
 * @ClassName: NetListener
 * @Description: TODO
 * @author bluestome.zhang
 * @date 2012-2-16 下午01:59:21
 */
public class NetListener implements INetListener {

    private static Logger logger = LoggerFactory.getLogger(NetListener.class);

    @Override
    public void onChangeStatNotifyResp(NetChangeStatNotify value) {
        logger.debug(" onChangeStatNotifyResp " + value);
    }

    @Override
    public void onChatNotify(NetChatNotify nfy) {
        if (null != nfy) {
            logger.debug(" onChatNotify " + nfy);
            if (null != nfy.getAudio()) {
                Audio audio = nfy.getAudio();
                logger.info(" > format:" + audio.getFormat());
                logger.info(" > md5:" + audio.getMd5());
                logger.info(" > len:" + audio.getAudioLen());
                logger.info(" > size:" + audio.getAudioSize());
            } else {
                logger.info(" > msgContent:" + nfy.getMsgContent());
            }
            logger.info("" + nfy.getAudio());
        }

    }

    @Override
    public void onOnlineStateChange(NetOnlineStateChangeNotify notify) {
        logger.debug(" onOnlineStateChange " + notify);

    }

    @Override
    public void onSysMsgNotify(NetSysMsgNotify notify) {
        logger.debug(" onSysMsgNotify " + notify);

    }

    @Override
    public void onVCardNotify(NetVCardNotify value) {
        if (null != value) {
            Map map = value.getvCardContentMap();
            String contactsName = (String) map.get(NetVCardNotify.CONTACT_NAME);
            logger.info(" > 联系人名称:" + contactsName);
            ArrayList<VCardContent> list = (ArrayList<VCardContent>) map
                    .get(NetVCardNotify.CONTACT_DETAIL_LIST);
            if (null != list && list.size() > 0) {
                for (VCardContent vc : list) {
                    logger.debug(" > 手机号码:" + vc.getPhone());
                    logger.debug(" > 手信号码:" + vc.getSkyid());
                    logger.debug(" > 昵称:" + vc.getNickname());
                    logger.debug(" > 用户类型:" + vc.getUsertype());
                    logger.debug(" > 用户头像:" + vc.getHeadicon());
                    logger.debug("\r\n");
                }
            }
        }
        logger.debug(" onVCardNotify " + value);

    }

    @Override
    public void onMarketingMsgNotify(NetMarketingMessageNotify value) {
        logger.debug(" onMarketingMsgNotify " + value);

    }

    @Override
    public void onFriendsMsgNotify(NetFriendsMsgNotify value) {
        logger.debug(" onFriendsMsgNotify " + value);
        ArrayList<FriendsList> list = value.getFriendsList();
        for (FriendsList friend : list) {
            System.out.println(" > nickName:" + friend.getNickname());
            System.out.println(" > recommendReason:" + friend.getRecommendReason());
            System.out.println(" > sex:" + friend.getSex());
        }

    }

    @Override
    public void onNetStateNotify(NetStateNotify value) {
        logger.debug(" onNetStateNotify " + value);
    }

    @Override
    public void onNewAccessConfigNotify(String ip, int port) {
        logger.debug("new access ip:" + ip + ",port:" + port);
    }

    @Override
    public void ticketOutNotify() {
        logger.debug("有人在其他地方登录了，您被踢下线.");
    }

    @Override
    public void trafficNotify(long pack) {
        logger.debug(" > 消耗[" + pack + "]流量:");
    }

    @Override
    public void onUpdateError() {
        logger.debug("> 应用更新失败!");
    }

    @Override
    public void createFastChatNotify(NetFastTalkNotify notify) {
        // TODO Auto-generated method stub
        logger.debug("> ");

    }

    @Override
    public void leaveFastChatNotify(NetFastTalkNotify notify) {
        // TODO Auto-generated method stub

    }

}
