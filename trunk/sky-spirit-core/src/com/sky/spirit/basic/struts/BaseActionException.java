/**
 * �ļ�com.sky.spirit.basic.struts.BaseActionException.java ������2008 2008-9-4 ����09:38:57
 * ��Ȩ������ ˹������ SkySpirit��Ŀ
 * ������: ȫ��Ӫ
 * ����ʱ��: 2008 2008-9-4 ����09:38:57
 * Email:jonny_quan@hotmail.com
 * ��ע��
 * ����Ķ��ο�������������ʱ������Ȩ��Ϣ��лл������
 */
package com.sky.spirit.basic.struts;


import org.apache.commons.lang.exception.NestableException;

/**
 * 
 * ע��
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 ����02:34:46
 * @version 1.0.0<br>
 * ���¼�¼��ע �����ˣ�����ʱ�䣬�������ݣ����汾��
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
