package com.skymobi.android.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class Zip {

	public Zip() {
	}

	public static byte[] zip(byte[] input) {
		if (null == input) {
			return new byte[0];
		}

		// Create the compressor with highest level of compression
		Deflater deflater = new Deflater(Deflater.DEFAULT_COMPRESSION);
		deflater.setInput(input);
		deflater.finish();

		ByteArrayOutputStream bos = new ByteArrayOutputStream(input.length);
		// Compress the data
		byte[] buf = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buf);
			bos.write(buf, 0, count);
		}
		// Get the compressed data
		byte[] ret = bos.toByteArray();
		try {
			bos.close();
		} catch (IOException e) {
		}

		bos = null;
		deflater = null;

		return ret;
	}

	public static byte[] unzip(byte[] input) {
		if (null == input) {
			return new byte[0];
		}
		Inflater inflater = new Inflater();
		inflater.setInput(input, 0, input.length);

		ByteArrayOutputStream bos = new ByteArrayOutputStream(input.length);

		// deCompress the data
		byte[] buf = new byte[1024];
		while (!inflater.finished()) {
			try {
				int count = inflater.inflate(buf);
				bos.write(buf, 0, count);
			} catch (DataFormatException e) {
				System.err.print(e);
			}
		}
		byte[] ret = bos.toByteArray();
		try {
			bos.close();
		} catch (IOException e) {
		}

		bos = null;
		inflater = null;
		return ret;
	}

}
