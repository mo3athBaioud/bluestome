/**
 * �ļ�com.sky.spirit.basic.spring.interceptor.LogEmailInterceptor.java ������2008 2008-9-4 ����09:38:57
 * ��Ȩ������ ˹������ SkySpirit��Ŀ
 * ������: ȫ��Ӫ
 * ����ʱ��: 2008 2008-9-4 ����09:38:57
 * Email:jonny_quan@hotmail.com
 * ��ע��
 * ����Ķ��ο�������������ʱ������Ȩ��Ϣ��лл������
 */
package com.sky.spirit.basic.spring.interceptor;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AfterReturningAdvice;

import com.sky.spirit.basic.mail.IEmailService;
import com.sky.spirit.common.util.spring.SpringBeanUtils;

/**
 * 
 * ע��
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 ����02:31:27
 * @version 1.0.0<br>
 * ���¼�¼��ע �����ˣ�����ʱ�䣬�������ݣ����汾��
 *
 */
public class LogEmailInterceptor implements AfterReturningAdvice {
	private static Log log = LogFactory.getLog(LogEmailInterceptor.class);

	public void afterReturning(Object returnValue, Method method,
			Object[] args, Object target) throws Throwable {
		IEmailService emailService=(IEmailService) SpringBeanUtils.getBean(IEmailService.class.getName());
		emailService.sendSystemErrorNotice("���»����Ͳ����ʼ���");
	}

}
