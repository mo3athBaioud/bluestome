/**
 * 文件com.sky.spirit.basic.spring.IBaseService.java 创建于2008 2008-9-25 下午04:37:33
 * 版权所属： 斯凯网络 SkySpirit项目
 * 创建者: 全佳营
 * 创建时间: 2008 2008-9-25 下午04:37:33
 * Email:jonny_quan@hotmail.com
 * 备注：
 * 免费阅读参考及拷贝，拷贝时附带版权信息，谢谢合作！
 */
package com.sky.spirit.basic.spring;

import com.sky.spirit.basic.hibernate.support.BaseEntity;

/**
 * 注释
 * 
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-25 下午04:37:33
 * @version 1.0.0<br>
 *          更新记录备注 更新人，更新时间，更新内容，及版本号
 * 
 */
public interface IBaseService {
	public void saveOrUpdateEntity(BaseEntity baseEntity);
}
