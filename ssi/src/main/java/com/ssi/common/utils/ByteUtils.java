package com.ssi.common.utils;

import java.util.List;

public class ByteUtils {
	private ByteUtils() {
	}

	public static String bytesAsHexString(byte[] bytes, int maxShowBytes) {
		int idx = 0;
		StringBuilder body = new StringBuilder();
		body.append("bytes size is:[");
		body.append(bytes.length);
		body.append("]\r\n");

		for (byte b : bytes) {
			int hex = ((int) b) & 0xff;
			String shex = Integer.toHexString(hex).toUpperCase();
			if (1 == shex.length()) {
				body.append("0");
			}
			body.append(shex);
			body.append(" ");
			idx++;
			if (16 == idx) {
				body.append("\r\n");
				idx = 0;
			}
			maxShowBytes--;
			if (maxShowBytes <= 0) {
				break;
			}
		}
		if (idx != 0) {
			body.append("\r\n");
		}
		return body.toString();
	}

	public static byte[] union(List<byte[]> byteList) {
		int size = 0;
		for (byte[] bs : byteList) {
			size += bs.length;
		}
		byte[] ret = new byte[size];
		int pos = 0;
		for (byte[] bs : byteList) {
			System.arraycopy(bs, 0, ret, pos, bs.length);
			pos += bs.length;
		}
		return ret;
	}
}
