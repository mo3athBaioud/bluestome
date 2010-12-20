/**
 * �ļ�com.sky.spirit.basic.jms.HibernateConsumer.java ������2008 2008-9-4 ����09:38:57
 * ��Ȩ������ ˹������ SkySpirit��Ŀ
 * ������: ȫ��Ӫ
 * ����ʱ��: 2008 2008-9-4 ����09:38:57
 * Email:jonny_quan@hotmail.com
 * ��ע��
 * ����Ķ��ο�������������ʱ������Ȩ��Ϣ��лл������
 */
package com.sky.spirit.basic.jms;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sky.spirit.basic.hibernate.support.BaseEntity;
import com.sky.spirit.basic.spring.IBaseService;

/**
 * 
 * ע��
 * 
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 ����02:21:00
 * @version 1.0.0<br>
 *          ���¼�¼��ע �����ˣ�����ʱ�䣬�������ݣ����汾��
 * 
 */
public class HibernateConsumer {
	private static Log log = LogFactory.getLog(HibernateConsumer.class);
	@SuppressWarnings("unchecked")
	private IBaseService baseService;

	public void setBaseService(IBaseService baseService) {
		this.baseService = baseService;
	}

	public void saveOrUpdateBaseEntity(final BaseEntity entity) {
		baseService.saveOrUpdateEntity(entity);
	}
}
