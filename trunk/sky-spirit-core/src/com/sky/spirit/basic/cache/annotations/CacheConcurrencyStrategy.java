/**
 * @ net.sinotec.kwork.components.cache.annotations.CacheConcurrencyStrategy.java Created on 2007-10-26涓婂崍08:57:33
 *
 * Copyright (c) 2007 by sinotec.
 */
package com.sky.spirit.basic.cache.annotations;

/**
 * 
 * Cache 策略定义
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 上午12:05:29
 * @version 1.0.0<br>
 * 更新记录备注 更新人，更新时间，更新内容，及版本号
 *
 */
public enum CacheConcurrencyStrategy {
	NONE, READ_ONLY, NONSTRICT_READ_WRITE, READ_WRITE, TRANSACTIONAL
}