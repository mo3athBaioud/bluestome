/**
 * �ļ�com.sky.spirit.basic.spring.IBaseService.java ������2008 2008-9-25 ����04:37:33
 * ��Ȩ������ ˹������ SkySpirit��Ŀ
 * ������: ȫ��Ӫ
 * ����ʱ��: 2008 2008-9-25 ����04:37:33
 * Email:jonny_quan@hotmail.com
 * ��ע��
 * ����Ķ��ο�������������ʱ������Ȩ��Ϣ��лл������
 */
package com.sky.spirit.basic.spring;

import com.sky.spirit.basic.hibernate.support.BaseEntity;

/**
 * ע��
 * 
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-25 ����04:37:33
 * @version 1.0.0<br>
 *          ���¼�¼��ע �����ˣ�����ʱ�䣬�������ݣ����汾��
 * 
 */
public interface IBaseService {
	public void saveOrUpdateEntity(BaseEntity baseEntity);
}
