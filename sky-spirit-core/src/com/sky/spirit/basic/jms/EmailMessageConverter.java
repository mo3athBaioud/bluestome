/**
 * 文件com.sky.spirit.basic.jms.EmailConsumer.java 创建于2008 2008-9-4 下午09:38:57
 * 版权所属： 斯凯网络 SkySpirit项目
 * 创建者: 全佳营
 * 创建时间: 2008 2008-9-4 下午09:38:57
 * Email:jonny_quan@hotmail.com
 * 备注：
 * 免费阅读参考及拷贝，拷贝时附带版权信息，谢谢合作！
 */
package com.sky.spirit.basic.jms;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.support.converter.MessageConverter;

import com.sky.spirit.basic.mail.Email;
import com.sky.spirit.common.Constants;

/**
 * 
 * 注释
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 上午02:19:17
 * @version 1.0.0<br>
 * 更新记录备注 更新人，更新时间，更新内容，及版本号
 *
 */
public class EmailMessageConverter implements MessageConverter {
	private static Log log=LogFactory.getLog(EmailMessageConverter.class);
	public Message toMessage(Object obj, Session session) throws JMSException {
		if (obj instanceof Email) {
			ActiveMQObjectMessage objMsg = (ActiveMQObjectMessage) session
					.createObjectMessage();
			Map<String,Object> map = new HashMap<String,Object>();
			try {
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(bos);
				oos.writeObject(obj);
				bos.close();
				map.put("Email", bos.toByteArray());
				objMsg.setObjectProperty("Map", map);
			} catch (IOException e) {
				log.error(e);
			}
			return objMsg;
		} else {
			throw new JMSException("Object:[" + obj + "] is not Email");
		}

	}

	@SuppressWarnings("unchecked")
	public Object fromMessage(Message msg) throws JMSException {
		if (msg instanceof ObjectMessage) {
			Map<String,Object> map = (HashMap<String,Object>) ((ObjectMessage) msg)
					.getObjectProperty("Map");
			try {
				ByteArrayInputStream bis = new ByteArrayInputStream(
						(byte[]) map.get(Constants.SKY_JMS_MAP_KEY_EMAIL));
				ObjectInputStream ois = new ObjectInputStream(bis);
				return ois.readObject();
			} catch (IOException e) {
				log.error(e);
			} catch (ClassNotFoundException e) {
				log.error(e);
			}
			return null;
		} else {
			throw new JMSException("Msg:[" + msg + "] is not Map");
		}

	}

}
