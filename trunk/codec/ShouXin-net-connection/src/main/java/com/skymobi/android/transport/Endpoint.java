/**
 * 
 */
package com.skymobi.android.transport;

/**
 * @author hp
 *
 */
public interface Endpoint extends Sender, Receiver {
	public void stop();
	public int getStatus();
	public void setStatus(int status);
}
