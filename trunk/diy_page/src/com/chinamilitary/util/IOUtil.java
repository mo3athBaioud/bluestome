package com.chinamilitary.util;
import java.io.*;
import java.net.URL;
public class IOUtil {
	public synchronized static void createFile(String content){
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
	 * 创建文件
	 * @param content 需要创建文件的内容
	 * @param ext   创建文件的扩展名
	 */
	public static void createFileWithExt(String content,String ext){
		File file = null;
		OutputStream out = null;
		try{
			if(null == ext || ext.equals("")){
				createFile(content);	
			}
			try{
				file = new File(System.getProperty("user.dir")+"/"+CommonUtil.GenerateSequence(0)+"."+ext);
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
	public static synchronized void createPicFile(String url,String fileName) throws IOException{ //File src, File dst, int BUFFER_SIZE
			InputStream in = null;
			OutputStream out = null;
			URL urls = null;
			File file = null;
			try {
				url = url.replace(" ", "%20");
			    urls = new URL(url);
				in = urls.openStream();

				file = new File(fileName);
				
				
				if(file.exists()){
					System.out.println("存在文件["+file.getAbsolutePath()+"]");
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

	public static synchronized void createFile(String content,String fileName){
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
	
	public static synchronized String createFile(String content,String fileName,String ext){
		String filePath = System.getProperty("user.dir")+"/"+fileName+ext;
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
		return filePath;
	}

	/**
	 * 将二进制内容写入指定文件
	 * @param bytes
	 * @param fileName
	 */
	public synchronized static void createFile(byte[] bytes,String fileName) throws IOException{
		File file = null;
		OutputStream out = null;
		try{
			try{
				file = new File(fileName);
				
				if(!file.exists()){
					file.getParentFile().mkdirs();
				}
				
				out = new BufferedOutputStream(new FileOutputStream(file),1024);
				out.write(bytes, 0, bytes.length);
				out.close();
			}finally{
				if(out != null)
					out.close();
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new IOException();
		}

	}
	
	/**
	 *获取文件内容
	 *@param filePath 文件路径
	 *@param encoding 编码格式
	 *@param ext 文件扩展名
	 */
	public static String readFile(String filePath,String encoding,String ext){
		File file = null;
		InputStreamReader read = null;
		BufferedReader is = null;
		StringBuffer sb = new StringBuffer();
		try{
		 file = new File(filePath);
		 //判断是否为文件夹
		 if(file.isDirectory()){
			 return null;
		 }else if(file.isFile() && file.getName().endsWith(ext)){ //单个文件且文件名义为文本文件
			 read = new InputStreamReader(new FileInputStream(file),encoding);
			 is=new BufferedReader(read);
			 String line;
			 while ((line = is.readLine()) != null) {
				sb.append(line);
				sb.append("\r\n");
			 }
		 }
		}catch(Exception e){
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * 获取当前CLASSPATH下的文件
	 */
	public static String readFile(String fileName){
		InputStreamReader read = null;
		BufferedReader is = null;
		StringBuffer sb = new StringBuffer();
		ClassLoader classLoader=IOUtil.class.getClassLoader(); 
		try{
			 read = new InputStreamReader(classLoader.getResourceAsStream(fileName));
			 is=new BufferedReader(read);
			 String line;
			 while ((line = is.readLine()) != null) {
				sb.append(line);
				sb.append("\r\n");
			 }
		}catch(IOException e){
			System.err.println(" >> 找不到文件");
		}catch(Exception e){
			System.err.println(" >> 异常");
			e.printStackTrace();
		}finally{
			try{
				if(null != is){
					is.close();
				}
			}catch(Exception e){
			
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args){
		try{
		System.out.println("Hello World!");
		createFile("Fuck!"+new java.sql.Timestamp(System.currentTimeMillis()).toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void createFile(String content, String filePath, String fileName, Object object) {
		String savePath = filePath+"\\"+fileName;
		File file = null;
		OutputStream out = null;
		try{
			try{
				file = new File(savePath);
				if(file.exists()){
					return;
				}
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
}
