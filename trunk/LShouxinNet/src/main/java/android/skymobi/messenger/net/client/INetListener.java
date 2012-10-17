
package android.skymobi.messenger.net.client;

import android.skymobi.messenger.net.client.bean.NetChangeStatNotify;
import android.skymobi.messenger.net.client.bean.NetChatNotify;
import android.skymobi.messenger.net.client.bean.NetFastTalkNotify;
import android.skymobi.messenger.net.client.bean.NetFriendsMsgNotify;
import android.skymobi.messenger.net.client.bean.NetMarketingMessageNotify;
import android.skymobi.messenger.net.client.bean.NetOnlineStateChangeNotify;
import android.skymobi.messenger.net.client.bean.NetStateNotify;
import android.skymobi.messenger.net.client.bean.NetSysMsgNotify;
import android.skymobi.messenger.net.client.bean.NetVCardNotify;

/**
 * 针对客户端service的客户端接口类
 * 
 * @author Bluestome.Zhang
 */
public interface INetListener {

    /**
     * 状态通知响应
     * 
     * @param value
     */
    void onChangeStatNotifyResp(NetChangeStatNotify value);

    /**
     * 接收到聊天消息
     * 
     * @return
     */
    void onChatNotify(NetChatNotify nfy);

    /**
     * 在线状态通知
     * 
     * @param notify
     */
    void onOnlineStateChange(NetOnlineStateChangeNotify notify);

    /**
     * 系统消息通知
     * 
     * @param notify
     */
    void onSysMsgNotify(NetSysMsgNotify notify);

    /**
     * 接收名片通知
     * 
     * @param value
     */
    void onVCardNotify(NetVCardNotify value);

    /**
     * 推荐营销消息
     * 
     * @param value
     */
    void onMarketingMsgNotify(NetMarketingMessageNotify value);

    /**
     * 推荐好友通知
     * 
     * @param value
     */
    void onFriendsMsgNotify(NetFriendsMsgNotify value);

    /**
     * 网络状态通知
     * 
     * @param value
     */
    void onNetStateNotify(NetStateNotify value);

    /**
     * 新服务端配置通知
     * 
     * @param ip
     * @param port
     */
    void onNewAccessConfigNotify(String ip, int port);

    /**
     * 踢人通知，同一帐号在不同客户端登陆后，系统会提示该通知，接收到该通知的 终端应该友好提示用户，并且做出对应的业务处理。
     */
    void ticketOutNotify();

    /**
     * 流量通知
     * 
     * @param pack
     */
    void trafficNotify(long pack);

    /**
     * 更新失败
     */
    void onUpdateError();

    /**
     * 建立快聊通知
     * 
     * @param notify
     */
    void createFastChatNotify(NetFastTalkNotify notify);

    /**
     * 离开快聊通知
     * 
     * @param notify
     */
    void leaveFastChatNotify(NetFastTalkNotify notify);

}
