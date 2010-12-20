package com.sky.commons.monitor.file.listener.impl;

import java.io.File;

import com.sky.commons.monitor.file.listener.FileListenerInterface;

public class DefaultFileReloadingListener implements FileListenerInterface {

	public void fileChanged(File file) {
		//reloading springxml
		if("registeSystemConfig.xml".equals(file.getName())){
			//reloading registeSystemConfig.xml
		}
	}

}
