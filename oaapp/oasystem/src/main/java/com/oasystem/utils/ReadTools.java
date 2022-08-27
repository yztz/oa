package com.oasystem.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;


public class ReadTools {

	public static byte[] read(InputStream inputStream) throws Exception
	{
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while((len = inputStream.read(buffer)) != -1)
			{
				outputStream.write(buffer, 0, len);
			}
			inputStream.close();
			outputStream.close();
			return outputStream.toByteArray();
	}

	public static byte[] read1(FileInputStream inputStream) throws Exception
	{
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while((len = inputStream.read(buffer)) != -1)
		{
			outputStream.write(buffer, 0, len);
		}
		inputStream.close();
		outputStream.close();
		return outputStream.toByteArray();
	}
}
