/**
 * 文件com.sky.spirit.basic.dto.DownloadFileDTO.java 创建于2008 2008-9-8 上午10:12:41
 * 版权所属： 斯凯网络 SkySpirit项目
 * 创建者: 全佳营
 * 创建时间: 2008 2008-9-8 上午10:12:41
 * Email:jonny_quan@hotmail.com
 * 备注：
 * 免费阅读参考及拷贝，拷贝时附带版权信息，谢谢合作！
 */
package com.sky.spirit.basic.cache;

import java.io.Serializable;

/**
 * Cache对象DTO，用于存储到Cache中的实体
 * 
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-8 上午10:12:41
 * @version 1.0.0<br>
 *          更新记录备注 更新人，更新时间，更新内容，及版本号
 * 
 */
public class CacheEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4605988848680404234L;
	private Object content;// 文件内容字节
	private long right;// 时间戳

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public long getRight() {
		return right;
	}

	public void setRight(long right) {
		this.right = right;
	}

//	public String toString() {
//		return ToStringBuilder.reflectionToString(this,
//				ToStringStyle.MULTI_LINE_STYLE);
//	}
}
