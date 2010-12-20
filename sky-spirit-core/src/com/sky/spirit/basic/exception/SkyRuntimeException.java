/**
 * �ļ�com.sky.spirit.basic.exception.SkyRuntimeException.java ������2008 2008-9-4 ����09:38:57
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
 * @date 2008 2008-9-6 ����01:28:20
 * @version 1.0.0<br>
 * ���¼�¼��ע �����ˣ�����ʱ�䣬�������ݣ����汾��
 *
 */
public class SkyRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5156256596106904953L;

	public SkyRuntimeException() {
		super();
	}

	public SkyRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public SkyRuntimeException(String message) {
		super(message);
	}

	public SkyRuntimeException(Throwable cause) {
		super(cause);
	}

}
