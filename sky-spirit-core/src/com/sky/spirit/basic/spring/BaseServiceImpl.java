/**
 * �ļ�com.sky.spirit.basic.spring.BaseServiceImpl.java ������2008 2008-9-25 ����04:57:20
 * ��Ȩ������ ˹������ SkySpirit��Ŀ
 * ������: ȫ��Ӫ
 * ����ʱ��: 2008 2008-9-25 ����04:57:20
 * Email:jonny_quan@hotmail.com
 * ��ע��
 * ����Ķ��ο�������������ʱ������Ȩ��Ϣ��лл������
 */
package com.sky.spirit.basic.spring;

import com.sky.spirit.basic.hibernate.support.BaseEntity;
import com.sky.spirit.basic.hibernate.support.IBaseDAO;

/**
 * ע��
 * 
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-25 ����04:57:20
 * @version 1.0.0<br>
 *          ���¼�¼��ע �����ˣ�����ʱ�䣬�������ݣ����汾��
 * 
 */
public class BaseServiceImpl extends AbstractBaseService implements
		IBaseService {

	private IBaseDAO baseDAO;

	@SuppressWarnings("unchecked")
	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	public void saveOrUpdateEntity(BaseEntity baseEntity) {
		baseDAO.saveOrUpdate(baseEntity);
	}

	@Override
	public void setEhcachedEnable(boolean enable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMemcachedEnable(boolean enable) {
		// TODO Auto-generated method stub
		
	}

}
