package com.sky.commons.cache;

import java.util.Date;

/**
 * 
 * 	@description 	:	���潻��ͳһ�ӿ�
 * 	@create author :<a href="yizhiju@hotmail.com">Benny</a>
 *	@create date:Oct 23, 2008  4:03:57 PM
 *	@edit author:
 * 	@edit date:Oct 23, 2008
 * 	@����޸�˵��:
 */
public interface ICache {

	/**
	 * ========��keyΪ������value,���۴���������򱣴棬�����滻==========
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(String key, Object value);

	/**
	 * ========��keyΪ������value,���۴���������򱣴棬�����滻==========
	 * 
	 * @param key
	 * @param value
	 * @param time
	 *            ������ʱ��
	 * @return
	 */
	public boolean set(String key, Object value, Long time);
	public boolean set(String key, Object value, int seconds);
	/**
	 * ========��keyΪ������value,���۴���������򱣴棬�����滻==========
	 * @param key
	 * @param value
	 * @param expiry ����ʧЧʱ���
	 * @return ����ɹ�����true
	 */
	public boolean set(String key, Object value, Date expiry);
	/**
	 * ���һ��ָ����ֵ��������.���Ѿ������򲻱���
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean add(String key, Object value);

	/**
	 * ���һ��ֵ,����Ѿ����������
	 * @param key
	 * @param value
	 * @param time ��Чʱ������λΪ������
	 * @return
	 */
	public boolean add(String key, Object value, Long time);

	/**
	 * ���һ��ֵ,����Ѿ����������
	 * @param key
	 * @param value
	 * @param expiry ʧЧʱ���
	 * @return
	 */
	public boolean add(String key, Object value, Date expiry);
	/**
	 * ===========�滻��ֵ�������������滻===========
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean replace(String key, Object value);

	
//	public boolean replace(String key, Object value, Date expiry);

	/**
	 * ����keyɾ��ĳһ������
	 * 
	 * @param key
	 * @return �����ڷ���false�����ڷ���true
	 */
	public boolean delete(String key);

	/**
	 * ����ָ���Ĺؼ��ֻ�ȡ����.
	 * 
	 * @param key
	 * @return ���ڷ��ض��󣬷��򷵻�null
	 */
	public Object get(String key);
	
	/**
	 * �жϼ��Ƿ����
	 * @param key
	 * @return
	 */
	public boolean keyExists(String key);
	
	public boolean flushAll();
	
	public boolean remove(String key);
}
