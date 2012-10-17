
package test.android.skymobi.messenger.net;

import android.skymobi.messenger.net.connection.IConnection;
import android.skymobi.messenger.net.connection.NetConfig;
import android.skymobi.messenger.net.connection.impl.NetConnection;

import org.junit.After;
import org.junit.Before;

public class ConnectionTest {

    public IConnection connection = null;

    public NetConfig config = new NetConfig();

    @Before
    public void init() {
        if (null == connection) {
            connection = NetConnection.getInstance(config, null);
            connection.connect();
        }
    }

    @After
    public void destory() {
        if (null != connection) {
            connection.close();
        }
    }

    public void connect() {
        connection.connect();
        try {
            Thread.sleep(60 * 1000l);
        } catch (Exception e) {

        }
    }

    public void testconnect() {
        connection.connect();
        try {
            Thread.sleep(60 * 1000l);
        } catch (Exception e) {

        }
        connection.close();
        try {
            Thread.sleep(1 * 1000l);
        } catch (Exception e) {

        }
        connection.reconnect();
        try {
            Thread.sleep(60 * 1000l);
        } catch (Exception e) {

        }
    }

}
