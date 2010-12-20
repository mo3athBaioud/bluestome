/**
 * 文件com.sky.spirit.basic.message.i18n.Message.java 创建于2008 2008-9-4 下午09:38:57
 * 版权所属： 斯凯网络 SkySpirit项目
 * 创建者: 全佳营
 * 创建时间: 2008 2008-9-4 下午09:38:57
 * Email:jonny_quan@hotmail.com
 * 备注：
 * 免费阅读参考及拷贝，拷贝时附带版权信息，谢谢合作！
 */
package com.sky.spirit.basic.message.i18n;

import java.io.Serializable;

/**
 * 
 * 注释
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 上午02:26:50
 * @version 1.0.0<br>
 * 更新记录备注 更新人，更新时间，更新内容，及版本号
 *
 */
public class Message implements Serializable {
	/**
	 * Serial version
	 */
	private static final long serialVersionUID = -6109760447384477924L;

	private String message;
	private int code = 0;
	private Object[] args;
	private Message nextMessage;

	protected Message(String message, int code, Object[] args) {
		super();
		this.message = message;
		this.code = code;
		this.args = args;
	}

	public int getCode() {
		return code;
	}

	public Object[] getArgs() {
		return args;
	}

	public String getMessage() {
		return message
				+ (nextMessage != null ? ". " + nextMessage.getMessage() : "");
	}

	public Message setNextMessage(Message nextMessage) {
		this.nextMessage = nextMessage;
		return this;
	}

	public Message getNextMessage() {
		return nextMessage;
	}

	public String toString() {
		return this.getMessage();
	}
}
