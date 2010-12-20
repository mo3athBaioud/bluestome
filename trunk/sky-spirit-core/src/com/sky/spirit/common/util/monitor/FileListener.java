/**
 * 文件com.sky.spirit.common.util.monitor.FileListener.java 创建于2008 2008-9-4 下午09:38:57
 * 版权所属： 斯凯网络 SkySpirit项目
 * 创建者: 全佳营
 * 创建时间: 2008 2008-9-4 下午09:38:57
 * Email:jonny_quan@hotmail.com
 * 备注：
 * 免费阅读参考及拷贝，拷贝时附带版权信息，谢谢合作！
 */
package com.sky.spirit.common.util.monitor;

import java.io.File;
import java.io.IOException;

/**
 * 
 * 注释
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 上午02:40:50
 * @version 1.0.0<br>
 * 更新记录备注 更新人，更新时间，更新内容，及版本号
 *
 */
public interface FileListener {
	/**
	 * Called when one of the monitored files are created, deleted or modified.
	 * 
	 * @param file
	 *            File which has been changed.
	 */
	void fileChanged(File file) throws IOException;
}
