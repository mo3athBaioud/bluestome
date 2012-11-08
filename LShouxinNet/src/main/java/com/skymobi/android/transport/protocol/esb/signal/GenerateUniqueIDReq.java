
package com.skymobi.android.transport.protocol.esb.signal;

import com.skymobi.android.bean.esb.annotation.EsbSignal;

/**
 * 生成一个唯一的ID（MD5字符串)供终端使用 并记录日志供数据分析追踪 生成策略是MD5(platform+uuid)
 * 前提条件是终端已经通过UA注册请求。
 * 
 * @author Bluces.Wang@sky-mobi.com
 */
@EsbSignal(messageCode = 0x9823)
public class GenerateUniqueIDReq extends AbstractEsbT2ASignal {
}
