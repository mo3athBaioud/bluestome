package com.sky.commons.monitor.file.listener;

import java.io.File;

/**
 * 文件变更监听接口
 * @author ibm
 *
 */
public interface FileListenerInterface {
	public void fileChanged(File file);
}
