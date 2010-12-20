/**
 * �ļ�com.sky.spirit.basic.exception.SkyException.java ������2008 2008-9-4 ����09:38:57
 * ��Ȩ������ ˹������ SkySpirit��Ŀ
 * ������: ȫ��Ӫ
 * ����ʱ��: 2008 2008-9-4 ����09:38:57
 * Email:jonny_quan@hotmail.com
 * ��ע��
 * ����Ķ��ο�������������ʱ������Ȩ��Ϣ��лл������
 */
package com.sky.spirit.basic.exception;


/**
 * 
 * ע��
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 ����01:27:53
 * @version 1.0.0<br>
 * ���¼�¼��ע �����ˣ�����ʱ�䣬�������ݣ����汾��
 *
 */
public class SkyException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3834982749562359234L;
	public SkyException() {
		super();
	}

	public SkyException(String message, Throwable cause) {
		super(message, cause);
	}

	public SkyException(String message) {
		super(message);
	}

	public SkyException(Throwable cause) {
		super(cause);
	}

}
