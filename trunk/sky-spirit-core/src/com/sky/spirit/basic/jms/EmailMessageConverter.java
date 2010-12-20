/**
 * �ļ�com.sky.spirit.basic.jms.EmailConsumer.java ������2008 2008-9-4 ����09:38:57
 * ��Ȩ������ ˹������ SkySpirit��Ŀ
 * ������: ȫ��Ӫ
 * ����ʱ��: 2008 2008-9-4 ����09:38:57
 * Email:jonny_quan@hotmail.com
 * ��ע��
 * ����Ķ��ο�������������ʱ������Ȩ��Ϣ��лл������
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
 * ע��
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 ����02:19:17
 * @version 1.0.0<br>
 * ���¼�¼��ע �����ˣ�����ʱ�䣬�������ݣ����汾��
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
