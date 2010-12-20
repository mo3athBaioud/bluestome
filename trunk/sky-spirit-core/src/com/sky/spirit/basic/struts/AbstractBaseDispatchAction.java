/**
 * �ļ�com.sky.spirit.basic.struts.AbstractBaseDispatchAction.java ������2008 2008-9-10 ����10:55:17
 * ��Ȩ������ ˹������ SkySpirit��Ŀ
 * ������: ȫ��Ӫ
 * ����ʱ��: 2008 2008-9-10 ����10:55:17
 * Email:jonny_quan@hotmail.com
 * ��ע��
 * ����Ķ��ο�������������ʱ������Ȩ��Ϣ��лл������
 */
package com.sky.spirit.basic.struts;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.sky.spirit.basic.database.util.DBUtil;

/**
 * ע��
 * 
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-10 ����10:55:17
 * @version 1.0.0<br>
 *          ���¼�¼��ע �����ˣ�����ʱ�䣬�������ݣ����汾��
 * 
 */
public class AbstractBaseDispatchAction extends DispatchAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward forward = null;

		try {
			forward = super.execute(mapping, form, request, response);
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			DBUtil.warn();
		}

		return forward;
	}

	protected String decode(String s) {
		try {
			s = new String(s.getBytes("iso-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return s;
	}

	protected String decodeFromRequest(String key, HttpServletRequest request) {
		String value = (String) request.getParameter(key);
		if (value == null)
			return null;
		try {
			value = new String(value.getBytes("iso-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		return value;
	}

	protected String decodeAndSave2Request(String key,
			HttpServletRequest request) {

		String value = request.getParameter(key);
		if (value == null)
			return null;
		try {
			value = new String(value.getBytes("iso-8859-1"), "UTF-8");
			request.setAttribute(key, value);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return value;
	}
}
