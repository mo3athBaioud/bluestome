package com.sky.spirit.basic.hibernate.ext.type;

import java.io.Serializable;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;
import org.postgresql.util.PGobject;

/**
 * A Hibernate <b>UserType</b> for PostgreSQL's <b>inet</b> type.
 * 
 * @author Jesse Costello-Good
 * @version $Id$
 */
public class Inet4AddressType implements UserType {

	public int[] sqlTypes() {
		return new int[] { Hibernate.STRING.sqlType() };
	}

	public Class returnedClass() {
		return Inet4Address.class;
	}

	public boolean equals(Object o, Object o1) throws HibernateException {
		if (o == null && o1 == null)
			return true;
		else if (o == null || o1 == null)
			return false;
		return o.equals(o1);
	}

	public Object nullSafeGet(ResultSet resultSet, String[] strings, Object o)
			throws HibernateException, SQLException {

		if (strings.length != 1)
			throw new IllegalArgumentException(
					"strings.length != 1, strings = " + strings);

		String value = resultSet.getString(strings[0]);

		if (value == null) {
			return null;
		} else {
			try {
				StringTokenizer tokenizer = new StringTokenizer(value, ".");
				byte[] parts = new byte[4];
				for (int i = 0; tokenizer.hasMoreTokens(); i++) {
					parts[i] = (byte) Integer.parseInt(tokenizer.nextToken());
				}
				return Inet4Address.getByAddress(parts);
			} catch (Exception e) {
				throw new HibernateException("error parsing inet: " + value, e);
			}
		}
	}

	public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i)
			throws HibernateException, SQLException {

		if (o == null) {
			preparedStatement.setNull(i, Hibernate.STRING.sqlType());
		} else {
			PGobject object = new PGobject();

			InetAddress address = null;
			try {
				address = Inet4Address.getByName((String) o);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			object.setType("inet");
			object.setValue(address.getHostAddress());
			preparedStatement.setObject(i, object);
		}
	}

	public Object deepCopy(Object o) throws HibernateException {
		if (o == null)
			return null;
		try {
			return Inet4Address.getByName((String) o).getHostAddress();
		} catch (UnknownHostException e) {
			throw new AssertionError("this can't happen!");
		}
	}

	public boolean isMutable() {
		return false;
	}

	public Object assemble(Serializable arg0, Object arg1)
			throws HibernateException {

		return arg0;
	}

	public Serializable disassemble(Object arg0) throws HibernateException {
		return (Serializable) arg0;
	}

	public int hashCode(Object arg0) throws HibernateException {
		return arg0.hashCode();
	}

	public Object replace(Object arg0, Object arg1, Object arg2)
			throws HibernateException {
		return arg0;
	}
}
