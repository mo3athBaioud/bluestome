/**
 * �ļ�com.sky.spirit.basic.struts.BaseActionMapping.java ������2008 2008-9-4 ����09:38:57
 * ��Ȩ������ ˹������ SkySpirit��Ŀ
 * ������: ȫ��Ӫ
 * ����ʱ��: 2008 2008-9-4 ����09:38:57
 * Email:jonny_quan@hotmail.com
 * ��ע��
 * ����Ķ��ο�������������ʱ������Ȩ��Ϣ��лл������
 */
package com.sky.spirit.basic.struts;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * ע��
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 ����02:35:07
 * @version 1.0.0<br>
 * ���¼�¼��ע �����ˣ�����ʱ�䣬�������ݣ����汾��
 *
 */
public class BaseActionMapping extends ActionMapping {
	
	public static final long serialVersionUID = 10000100002l;
	protected static Log log = LogFactory.getLog(BaseActionMapping.class);
	
	protected static BaseActionMapping DEFAULT_ACTION_MAPPING = new BaseActionMapping(true);

	private String processId;
	private String activityId;
	
	//access control
	private boolean restricted = true;
	private String permissionName = null;
	private String permissionActions = null;
	
	private boolean generateToken = false;
	private boolean checkToken = true;

	private String preloadFormBean = null;
	private String uowClass = null;
	
	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public BaseActionMapping() {
	}
	
	protected BaseActionMapping(boolean restricted) {
		this.restricted = restricted;
	}
	
	public boolean isRestricted() {
		return restricted;
	}

	public boolean getRestricted() {
		return restricted;
	}
	
	public void setRestricted(boolean restricted) {
		this.restricted = restricted;
	}

	public String getPreloadFormBean() {
		return preloadFormBean;
	}

	public void setPreloadFormBean(String preloadFormBean) {
		this.preloadFormBean = preloadFormBean;
	}

	public String getUowClass() {
		return uowClass;
	}

	public void setUowClass(String uowClass) {
		this.uowClass = uowClass;
	}

	public boolean isCheckToken() {
		return checkToken;
	}

	public void setCheckToken(boolean checkToken) {
		this.checkToken = checkToken;
	}

	public boolean isGenerateToken() {
		return generateToken;
	}

	public void setGenerateToken(boolean generateToken) {
		this.generateToken = generateToken;
	}

	/**
	 * @return
	 */
	public String getPermissionActions() {
		return permissionActions;
	}

	/**
	 * @param permissionActions
	 */
	public void setPermissionActions(String permissionActions) {
		this.permissionActions = permissionActions;
	}

	/**
	 * @return
	 */
	public String getPermissionName() {
		return permissionName;
	}

	/**
	 * @param permissionName
	 */
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

}
