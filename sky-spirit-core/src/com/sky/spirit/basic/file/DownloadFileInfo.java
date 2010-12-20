/**
 * �ļ�com.sky.spirit.basic.file.DownloadFileInfo.java ������2008 2008-9-4 ����09:38:57
 * ��Ȩ������ ˹������ SkySpirit��Ŀ
 * ������: ȫ��Ӫ
 * ����ʱ��: 2008 2008-9-4 ����09:38:57
 * Email:jonny_quan@hotmail.com
 * ��ע��
 * ����Ķ��ο�������������ʱ������Ȩ��Ϣ��лл������
 */
package com.sky.spirit.basic.file;

import java.util.List;

/**
 * 
 * ע��
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 ����01:30:02
 * @version 1.0.0<br>
 * ���¼�¼��ע �����ˣ�����ʱ�䣬�������ݣ����汾��
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
