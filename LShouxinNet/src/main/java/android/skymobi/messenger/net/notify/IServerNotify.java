
package android.skymobi.messenger.net.notify;

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

import com.skymobi.android.notify.INetClientNotify;

/**
 * 服务端响应以及通知接口
 * 
 * @author Bluestome.Zhang
 */
public interface IServerNotify extends INetClientNotify {

    /**
     * 状态通知响应
     * 
     * @param value
     */
    void onChangeStatNotifyResp(ChangeStatNotify value);

    /**
     * 在线状态通知
     * 
     * @param value 在线状态
     */
    void onOnlineStateChangeNotify(SxOnlineStateChangeNotify value);

    /**
     * 好友聊天消息
     * 
     * @param value 好友聊天消息
     */
    void onChatMsgNotify(SxChatMsgNotify value);

    /**
     * 系统消息通知
     * 
     * @param value 系统通知
     */
    void onSysMsgNotify(SxSysMsgNotify value);

    /**
     * 发送名片
     * 
     * @param value
     */
    void onSxVCardNotify(SxVCardNotify value);

    /**
     * 推荐营销消息
     * 
     * @param value
     */
    void onMarketingMsgNotify(SxMarketingMessageNotify marketingNotify);

    /**
     * 推荐好友通知
     * 
     * @param value
     */
    void onSxFriendsMsgNotify(SxFriendsMsgNotify value);

    /**
     * 网络状态通知
     * 
     * @param value
     */
    void onNetStateNotify(Integer value);

    /**
     * 注入NetListener
     * 
     * @param netListener
     */
    void setNetListener(INetListener netListener);

    /**
     * 新ACCESS配置通知
     * 
     * @param ip
     * @param port
     */
    void newAccessServerConfigNotify(String ip, int port);

    /**
     * ACCESS踢人通知，通常是1个帐号2次登录才触发该通知
     */
    void ticketOutNotify();

    /**
     * 建立快聊通知
     * 
     * @param notify
     */
    void createFastChatNotify(SxCreateFastTalkNotify notify);

    /**
     * 离开快聊通知
     * 
     * @param notify
     */
    void leaveFastChatNotify(SxLeaveFastTalkNotify notify);

    /**
     * 绑定/解绑通知
     * 
     * @param notify
     */
    void bindChangeNotify(SxBindChangeNotify notify);

}
