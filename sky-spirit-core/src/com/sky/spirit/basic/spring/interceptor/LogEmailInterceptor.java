/**
 * 文件com.sky.spirit.basic.spring.interceptor.LogEmailInterceptor.java 创建于2008 2008-9-4 下午09:38:57
 * 版权所属： 斯凯网络 SkySpirit项目
 * 创建者: 全佳营
 * 创建时间: 2008 2008-9-4 下午09:38:57
 * Email:jonny_quan@hotmail.com
 * 备注：
 * 免费阅读参考及拷贝，拷贝时附带版权信息，谢谢合作！
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
 * 注释
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 上午02:31:27
 * @version 1.0.0<br>
 * 更新记录备注 更新人，更新时间，更新内容，及版本号
 *
 */
public class LogEmailInterceptor implements AfterReturningAdvice {
	private static Log log = LogFactory.getLog(LogEmailInterceptor.class);

	public void afterReturning(Object returnValue, Method method,
			Object[] args, Object target) throws Throwable {
		IEmailService emailService=(IEmailService) SpringBeanUtils.getBean(IEmailService.class.getName());
		emailService.sendSystemErrorNotice("蒋德华发送测试邮件！");
	}

}
