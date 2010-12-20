/**
 * �ļ�com.sky.spirit.basic.cache.annotations.Cacheable.java ������2008 2008-9-4 ����09:38:57
 * ��Ȩ������ ˹������ SkySpirit��Ŀ
 * ������: ȫ��Ӫ
 * ����ʱ��: 2008 2008-9-4 ����09:38:57
 * Email:jonny_quan@hotmail.com
 * ��ע��
 * ����Ķ��ο�������������ʱ������Ȩ��Ϣ��лл������
 */
package com.sky.spirit.basic.cache.annotations;

/**
 * 
 * Cache annotation
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 ����12:03:37
 * @version 1.0.0<br>
 * ���¼�¼��ע �����ˣ�����ʱ�䣬�������ݣ����汾��
 *
 */
public @interface Cacheable {
	public abstract CacheConcurrencyStrategy usage();

	public abstract String region();

	public abstract String include();
}
