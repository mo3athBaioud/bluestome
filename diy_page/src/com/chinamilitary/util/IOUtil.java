package com.chinamilitary.util;
import java.io.*;
import java.net.URL;
public class IOUtil {
	public static void createFile(String content){
		File file = null;
		OutputStream out = null;
		try{
			try{
				file = new File(System.getProperty("user.dir")+"/"+CommonUtil.GenerateSequence(0)+".txt");
				out = new BufferedOutputStream(new FileOutputStream(file),1024);
				out.write(content.getBytes(), 0, content.getBytes().length);
				out.close();
			}finally{
				if(out != null)
					out.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	/**
	 * 复制文件到指定目录
	 * 
	 * @param src
	 * @param dst
	 * @param BUFFER_SIZE
	 */
	public static void createPicFile(String url,String fileName) throws IOException{ //File src, File dst, int BUFFER_SIZE
			InputStream in = null;
			OutputStream out = null;
			URL urls = null;
			File file = null;
			try {
			   urls = new URL(url);

				in = urls.openStream();

				file = new File(fileName);
				
				
				if(file.exists()){
					System.out.println("存在文件");
					return;
				}
				
				if(!file.exists()){
					file.getParentFile().mkdirs();
				}
				
				out = new BufferedOutputStream(new FileOutputStream(file),
						1024);
				byte[] buffer = new byte[1024];

				int bytesRead = 0;

			    while((bytesRead = in.read(buffer,0,1024))!=-1){
				  out.write(buffer,0,bytesRead);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
	}

	public static void createFile(String content,String fileName){
		File file = null;
		OutputStream out = null;
		try{
			try{
				file = new File(System.getProperty("user.dir")+"/"+fileName+".txt");
				out = new BufferedOutputStream(new FileOutputStream(file),1024);
				out.write(content.getBytes(), 0, content.getBytes().length);
				out.close();
			}finally{
				if(out != null)
					out.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	public static void createFile(String content,String fileName,String ext){
		File file = null;
		OutputStream out = null;
		try{
			try{
				file = new File(fileName+ext);
				out = new BufferedOutputStream(new FileOutputStream(file),1024);
				out.write(content.getBytes(), 0, content.getBytes().length);
				out.close();
			}finally{
				if(out != null)
					out.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	public static void main(String[] args){
		try{
		System.out.println("Hello World!");
		createFile("Fuck!"+new java.sql.Timestamp(System.currentTimeMillis()).toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
