
package android.skymobi.messenger.net.notify.impl;

import android.skymobi.messenger.net.beans.SxBindChangeNotify;
import android.skymobi.messenger.net.beans.SxChatMsgNotify;
import android.skymobi.messenger.net.beans.SxCreateFastTalkNotify;
import android.skymobi.messenger.net.beans.SxFriendsMsgNotify;
import android.skymobi.messenger.net.beans.SxLeaveFastTalkNotify;
import android.skymobi.messenger.net.beans.SxMarketingMessageNotify;
import android.skymobi.messenger.net.beans.SxOnlineStateChangeNotify;
import android.skymobi.messenger.net.beans.SxSysMsgNotify;
import android.skymobi.messenger.net.beans.SxVCardNotify;
import android.skymobi.messenger.net.beans.commons.ChangeStatNotify;
import android.skymobi.messenger.net.client.INetListener;
import android.skymobi.messenger.net.client.bean.NetChangeStatNotify;
import android.skymobi.messenger.net.client.bean.NetChatNotify;
import android.skymobi.messenger.net.client.bean.NetFastTalkNotify;
import android.skymobi.messenger.net.client.bean.NetFriendsMsgNotify;
import android.skymobi.messenger.net.client.bean.NetMarketingMessageNotify;
import android.skymobi.messenger.net.client.bean.NetOnlineStateChangeNotify;
import android.skymobi.messenger.net.client.bean.NetStateNotify;
import android.skymobi.messenger.net.client.bean.NetStateNotify.ConnectStatus;
import android.skymobi.messenger.net.client.bean.NetSysMsgNotify;
import android.skymobi.messenger.net.client.bean.NetVCardNotify;
import android.skymobi.messenger.net.notify.IServerNotify;
import android.skymobi.messenger.net.utils.ParserUtils;

import com.skymobi.android.commons.logger.Logger;
import com.skymobi.android.commons.logger.LoggerFactory;

import java.util.Map;

/**
 * 客户端通知接口 由调用方实现
 * 
 * @ClassName: ServerNotify
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-2-8 下午08:03:18
 */
public class ServerNotify implements IServerNotify {

    public static Logger logger = LoggerFactory.getLogger(ServerNotify.class);

    private INetListener netListener;

    public ServerNotify(INetListener netListener) {
        this.netListener = netListener;
    }

    @Override
    public void onChangeStatNotifyResp(ChangeStatNotify value) {
        if (null != value) {
            NetChangeStatNotify notify = new NetChangeStatNotify();
            notify.setCount(value.getCount());
            notify.setDetailState(value.getDetailState());
            notify.setFrdSkyId(value.getFrdSkyId());
            notify.setSeqId(value.getSeqId());
            notify.setSkyid(value.getSkyid());
            notify.setState(value.getState());
            notify.setStateSts(value.getStateSts());
            netListener.onChangeStatNotifyResp(notify);
        }
    }

    /**
     * 上线状态通知
     */
    @Override
    public void onOnlineStateChangeNotify(SxOnlineStateChangeNotify value) {
        if (null != value) {
            NetOnlineStateChangeNotify notify = new NetOnlineStateChangeNotify();
            notify.setNickname(value.getNickname());
            notify.setSeqid(value.getSeqid());
            notify.setSkyid(value.getSkyid());
            notify.setStatus(value.getStatus());
            notify.setTimestamp(value.getTimestamp());
            netListener.onOnlineStateChange(notify);
        }

    }

    /**
     * 网络消息
     */
    @Override
    public void onChatMsgNotify(SxChatMsgNotify value) {
        if (null != value) {
            NetChatNotify chat = new NetChatNotify();
            chat.setMsgContent(value.getMsgContent());
            chat.setNickname(value.getNickname());
            chat.setSkyid(value.getSkyid());
            chat.setTimestamp(value.getTimestamp());
            chat.setTalkReason(value.getTalkReason());
            chat.setChatMsgType(value.getChatMsgType());
            if (null != value.getAudio()) {
                chat.setAudio(value.getAudio());
            }
            netListener.onChatNotify(chat);
        }
    }

    /**
     * 系统消息通知
     */
    @Override
    public void onSysMsgNotify(SxSysMsgNotify value) {
        if (null != value) {
            NetSysMsgNotify notify = new NetSysMsgNotify();
            notify.setNickname(value.getNickname());
            notify.setSeqid(value.getSeqid());
            notify.setSkyid(value.getSkyid());
            notify.setMsgContent(value.getMsgContent());
            notify.setTimestamp(value.getTimestamp());
            notify.setResultType(value.getResultType());
            netListener.onSysMsgNotify(notify);
        }

    }

    /**
     * 名片通知
     */
    @Override
    public void onSxVCardNotify(SxVCardNotify value) {
        if (null != value) {
            NetVCardNotify notify = new NetVCardNotify();
            notify.setNickname(value.getNickname());
            notify.setSeqid(value.getSeqid());
            notify.setSkyid(value.getSkyid());
            if (null != value.getvCardContent() && !value.getvCardContent().equals("")) {
                Map map = ParserUtils.decoderVCard(value.getvCardContent());
                notify.setvCardContentMap(map);
            }
            notify.setTimestamp(value.getTimestamp());
            netListener.onVCardNotify(notify);
        }
    }

    @Override
    public void onMarketingMsgNotify(SxMarketingMessageNotify value) {
        if (null != value) {
            NetMarketingMessageNotify notify = new NetMarketingMessageNotify();
            notify.setUrl(value.getUrl());
            notify.setContent(value.getContent());
            notify.setTitle(value.getTitle());
            notify.setUrlType(value.getUrlType());
            netListener.onMarketingMsgNotify(notify);
        }

    }

    /**
     * 推荐好友通知
     */
    @Override
    public void onSxFriendsMsgNotify(SxFriendsMsgNotify value) {
        if (null != value) {
            NetFriendsMsgNotify notify = new NetFriendsMsgNotify();
            notify.setFriendsList(value.getFriendsList());
            notify.setSeqid(value.getSeqid());
            notify.setTimestamp(value.getTimestamp());
            netListener.onFriendsMsgNotify(notify);
        }

    }

    /**
     * 网络状态通知
     * 
     * @param value
     */
    @Override
    public void onNetStateNotify(Integer value) {
        NetStateNotify notify = new NetStateNotify();
        if (null != value) {
            if (-1 == value) {
                notify.setState(ConnectStatus.UNCONNECTED);
            } else if (1 == value) {
                notify.setState(ConnectStatus.CONNECTED);
            } else if (2 == value) {
                notify.setState(ConnectStatus.RECONNECTED);
            }
        }
        logger.info(" > onNetStateNotify:" + notify.getState());
        netListener.onNetStateNotify(notify);
    }

    @Override
    public void createFastChatNotify(SxCreateFastTalkNotify notify) {
        NetFastTalkNotify ftn = new NetFastTalkNotify();
        if (notify.getResponseCode() == 200) {
            ftn.setSuccess(true);
            ftn.setDestSkyid(notify.getDestSkyid());
        } else {
            ftn.setSuccess(false);
        }
        netListener.createFastChatNotify(ftn);
    }

    @Override
    public void leaveFastChatNotify(SxLeaveFastTalkNotify notify) {
        NetFastTalkNotify ftn = new NetFastTalkNotify();
        ftn.setSuccess(true);
        ftn.setDestSkyid(notify.getDestSkyid());
        netListener.leaveFastChatNotify(ftn);
    }

    @Override
    public void bindChangeNotify(SxBindChangeNotify notify) {
        // TODO　绑定/解绑通知
    }

    /**
     * 新ACCESS配置通知
     * 
     * @param ip
     * @param port
     */
    @Override
    public void newAccessServerConfigNotify(String ip, int port) {
        netListener.onNewAccessConfigNotify(ip, port);
    }

    /**
     * 服务端踢人通知
     */
    @Override
    public void ticketOutNotify() {
        netListener.ticketOutNotify();
    }

    /**
     * 添加发送的字节数，用于流量统计
     */
    @Override
    public void addByteLength(long packLen) {
        if (packLen > 0) {
            netListener.trafficNotify(packLen);
        }
    }

    /**
     * @return the netListener
     */
    public INetListener getNetListener() {
        return netListener;
    }

    /**
     * @param netListener the netListener to set
     */
    @Override
    public void setNetListener(INetListener netListener) {
        this.netListener = netListener;
    }

}
