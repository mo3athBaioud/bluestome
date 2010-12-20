/**
 * �ļ�com.sky.spirit.basic.spring.AbstractBaseService.java ������2008 2008-9-4 ����09:38:57
 * ��Ȩ������ ˹������ SkySpirit��Ŀ
 * ������: ȫ��Ӫ
 * ����ʱ��: 2008 2008-9-4 ����09:38:57
 * Email:jonny_quan@hotmail.com
 * ��ע��
 * ����Ķ��ο�������������ʱ������Ȩ��Ϣ��лл������
 */
package com.sky.spirit.basic.spring;

import com.sky.spirit.basic.cache.AbstractBaseCache;
import com.sky.spirit.basic.cache.ICacheable;
import com.sky.spirit.basic.jms.HibernateProducer;

/**
 * 
 * ע��
 * 
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-5 ����09:53:09
 * @version 1.0.0<br>
 *          ���¼�¼��ע �����ˣ�����ʱ�䣬�������ݣ����汾��
 * 
 */
public abstract class AbstractBaseService extends AbstractBaseCache implements
		ICacheable {
	protected HibernateProducer hibernateProducer;

	public void setHibernateProducer(HibernateProducer hibernateProducer) {
		this.hibernateProducer = hibernateProducer;
	}
}
