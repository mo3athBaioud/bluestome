/**
 * @ net.sinotec.kwork.components.cache.annotations.CacheConcurrencyStrategy.java Created on 2007-10-26上午08:57:33
 *
 * Copyright (c) 2007 by sinotec.
 */
package com.sky.spirit.basic.cache.annotations;

/**
 * 
 * Cache ���Զ���
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 ����12:05:29
 * @version 1.0.0<br>
 * ���¼�¼��ע �����ˣ�����ʱ�䣬�������ݣ����汾��
 *
 */
public enum CacheConcurrencyStrategy {
	NONE, READ_ONLY, NONSTRICT_READ_WRITE, READ_WRITE, TRANSACTIONAL
}