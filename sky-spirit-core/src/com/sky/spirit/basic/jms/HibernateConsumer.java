/**
 * 文件com.sky.spirit.basic.jms.HibernateConsumer.java 创建于2008 2008-9-4 下午09:38:57
 * 版权所属： 斯凯网络 SkySpirit项目
 * 创建者: 全佳营
 * 创建时间: 2008 2008-9-4 下午09:38:57
 * Email:jonny_quan@hotmail.com
 * 备注：
 * 免费阅读参考及拷贝，拷贝时附带版权信息，谢谢合作！
 */
package com.sky.spirit.basic.jms;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sky.spirit.basic.hibernate.support.BaseEntity;
import com.sky.spirit.basic.spring.IBaseService;

/**
 * 
 * 注释
 * 
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 上午02:21:00
 * @version 1.0.0<br>
 *          更新记录备注 更新人，更新时间，更新内容，及版本号
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
