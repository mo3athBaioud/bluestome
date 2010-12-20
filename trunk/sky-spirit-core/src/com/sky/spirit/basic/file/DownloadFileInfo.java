/**
 * 文件com.sky.spirit.basic.file.DownloadFileInfo.java 创建于2008 2008-9-4 下午09:38:57
 * 版权所属： 斯凯网络 SkySpirit项目
 * 创建者: 全佳营
 * 创建时间: 2008 2008-9-4 下午09:38:57
 * Email:jonny_quan@hotmail.com
 * 备注：
 * 免费阅读参考及拷贝，拷贝时附带版权信息，谢谢合作！
 */
package com.sky.spirit.basic.file;

import java.util.List;

/**
 * 
 * 注释
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 上午01:30:02
 * @version 1.0.0<br>
 * 更新记录备注 更新人，更新时间，更新内容，及版本号
 *
 */
public class DownloadFileInfo {
	private String innerGetFileName;
	private boolean isHint;
	private List<String> promptList;
	private String path;
	private int totalFileLength;
	private long appId;
	private long appVer;
	private long listAppCode;
	private String locationCode;

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public long getAppId() {
		return appId;
	}

	public void setAppId(long appId) {
		this.appId = appId;
	}

	public long getAppVer() {
		return appVer;
	}

	public void setAppVer(long appVer) {
		this.appVer = appVer;
	}

	public long getListAppCode() {
		return listAppCode;
	}

	public void setListAppCode(long listAppCode) {
		this.listAppCode = listAppCode;
	}

	public int getTotalFileLength() {
		return totalFileLength;
	}

	public void setTotalFileLength(int totalFileLength) {
		this.totalFileLength = totalFileLength;
	}

	public String getInnerGetFileName() {
		return innerGetFileName;
	}

	public void setInnerGetFileName(String innerGetFileName) {
		this.innerGetFileName = innerGetFileName;
	}

	public boolean isHint() {
		return isHint;
	}

	public void setHint(boolean isHint) {
		this.isHint = isHint;
	}

	public List<String> getPromptList() {
		return promptList;
	}

	public void setPromptList(List<String> promptList) {
		this.promptList = promptList;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
