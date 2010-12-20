/**
 * 文件com.sky.spirit.common.util.BeanUtils.java 创建于2008 2008-9-4 下午09:38:57
 * 版权所属： 斯凯网络 SkySpirit项目
 * 创建者: 全佳营
 * 创建时间: 2008 2008-9-4 下午09:38:57
 * Email:jonny_quan@hotmail.com
 * 备注：
 * 免费阅读参考及拷贝，拷贝时附带版权信息，谢谢合作！
 */
package com.sky.spirit.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

/**
 * 
 * 注释
 * @author <a href="jonny_quan@hotmail.com">jonny</a>
 * @date 2008 2008-9-6 上午02:54:28
 * @version 1.0.0<br>
 * 更新记录备注 更新人，更新时间，更新内容，及版本号
 *
 */
public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {
	private static Log log = LogFactory.getLog(BeanUtils.class);

	public static Field getDeclaredField(Object object, String propertyName)
			throws NoSuchFieldException {
		Assert.notNull(object);
		Assert.hasText(propertyName);
		return getDeclaredField(object.getClass(), propertyName);
	}

	public static Field getDeclaredField(Class clazz, String propertyName)
			throws NoSuchFieldException {
		Assert.notNull(clazz);
		Assert.hasText(propertyName);
		for (Class superClass = clazz; superClass != Object.class;)
			try {
				return superClass.getDeclaredField(propertyName);
			} catch (NoSuchFieldException nosuchfieldexception) {
				superClass = superClass.getSuperclass();
			}

		throw new NoSuchFieldException((new StringBuilder("No such field: "))
				.append(clazz.getName()).append('.').append(propertyName)
				.toString());
	}

	public static Object forceGetProperty(Object object, String propertyName)
			throws NoSuchFieldException {
		Assert.notNull(object);
		Assert.hasText(propertyName);
		Field field = getDeclaredField(object, propertyName);
		boolean accessible = field.isAccessible();
		field.setAccessible(true);
		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			log.info("error wont' happen");
		}
		field.setAccessible(accessible);
		return result;
	}

	public static void forceSetProperty(Object object, String propertyName,
			Object newValue) throws NoSuchFieldException {
		Assert.notNull(object);
		Assert.hasText(propertyName);
		Field field = getDeclaredField(object, propertyName);
		boolean accessible = field.isAccessible();
		field.setAccessible(true);
		try {
			field.set(object, newValue);
		} catch (IllegalAccessException e) {
			log.info("Error won't happen");
		}
		field.setAccessible(accessible);
	}

	public static Object invokePrivateMethod(Object object, String methodName,
			Object params[]) throws NoSuchMethodException {
		Assert.notNull(object);
		Assert.hasText(methodName);
		Class types[] = new Class[params.length];
		for (int i = 0; i < params.length; i++)
			types[i] = params[i].getClass();

		Class clazz = object.getClass();
		Method method = null;
		for (Class superClass = clazz; superClass != Object.class;)
			try {
				method = superClass.getDeclaredMethod(methodName, types);
				break;
			} catch (NoSuchMethodException nosuchmethodexception) {
				superClass = superClass.getSuperclass();
			}

		if (method == null)
			throw new NoSuchMethodException((new StringBuilder(
					"No Such Method:")).append(clazz.getSimpleName()).append(
					methodName).toString());
		boolean accessible = method.isAccessible();
		method.setAccessible(true);
		Object result = null;
		try {
			result = method.invoke(object, params);
		} catch (Exception e) {
			ReflectionUtils.handleReflectionException(e);
		}
		method.setAccessible(accessible);
		return result;
	}

	public static List<Field> getFieldsByType(Object object, Class type) {
		List<Field> list = new ArrayList<Field>();
		Field fields[] = object.getClass().getDeclaredFields();
		Field afield[];
		int j = (afield = fields).length;
		for (int i = 0; i < j; i++) {
			Field field = afield[i];
			if (field.getType().isAssignableFrom(type))
				list.add(field);
		}

		return list;
	}

	public static Class getPropertyType(Class type, String name)
			throws NoSuchFieldException {
		return getDeclaredField(type, name).getType();
	}

	public static String getGetterName(Class type, String fieldName) {
		Assert.notNull(type, "Type required");
		Assert.hasText(fieldName, "FieldName required");
		if (type.getName().equals("boolean"))
			return (new StringBuilder("is")).append(
					StringUtils.capitalize(fieldName)).toString();
		else
			return (new StringBuilder("get")).append(
					StringUtils.capitalize(fieldName)).toString();
	}

	public static Method getGetterMethod(Class type, String fieldName) {
		try {
			return type.getMethod(getGetterName(type, fieldName), new Class[0]);
		} catch (NoSuchMethodException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
}
