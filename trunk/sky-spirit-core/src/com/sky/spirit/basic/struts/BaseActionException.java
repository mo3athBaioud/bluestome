/**
 * 文件com.sky.spirit.basic.struts.BaseActionException.java 创建于2008 2008-9-4 下午09:38:57
 * 版权所属： 斯凯网络 SkySpirit项目
 * 创建者: 全佳营
 * 创建时间: 2008 2008-9-4 下午09:38:57
 * Email:jonny_quan@hotmail.com
 * 备注：
 * 免费阅读参考及拷贝，拷贝时附带版权信息，谢谢合作！
 */
package com.sky.spirit.basic.struts;


import org.apache.commons.lang.exception.NestableException;

/**
 * 
 * 注释
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 上午02:34:46
 * @version 1.0.0<br>
 * 更新记录备注 更新人，更新时间，更新内容，及版本号
 *
 */
public class BaseActionException extends NestableException {
	
	public static final long serialVersionUID = 10000100001l;
	/**
	 * Constructor for SiebreActionException.
	 */
	public BaseActionException() {
		super();
	}

	/**
	 * Constructor for SiebreActionException.
	 * @param arg0
	 */
	public BaseActionException(String message) {
		super(message);
	}

	/**
	 * Constructor for SiebreActionException.
	 * @param arg0
	 */
	public BaseActionException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor for SiebreActionException.
	 * @param arg0
	 * @param arg1
	 */
	public BaseActionException(String message, Throwable cause) {
		super(message, cause);
	}
}
