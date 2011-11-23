package com.xian.support.cms.common;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
/**
 * 生成图片缩略图
 * @author ToFishes
 *
 */
public class ReduceImage {

	/**
	 * 自定义宽高生成缩略图
	 * @param imgSrc 原图片的绝对路径名
	 * @param imgDist 生成缩略图后的存放绝对路径名
	 * @param widthDist 缩略图的宽度
	 * @param heightDist 缩略图的高度
	 */
	public static boolean reduceImg(String imgSrc, String imgDist, int widthDist,
			int heightDist) {
		boolean b = false;
		try {
			File srcfile = new File(imgSrc);
			if (!srcfile.exists()) {
				return b;
			}
			Image src = javax.imageio.ImageIO.read(srcfile);
			reduceImg(src, imgDist, widthDist, heightDist);
			b = true;
		} catch (IOException ex) {
			b = false;
			System.err.println(ex);
		}
		return b;
	}
	/**
	 * 按照图片原有比例生成图片缩略图，maxSize为图片高度、宽度的最大值。
	 * @param imgSrc 原图片的绝对路径
	 * @param imgDist 生成缩略图后的存放绝对路径名
	 * @param maxSize 图片高度、宽度的最大值。
	 * @return boolean true:成功   false:失败
	 */
	public static boolean reduceImg(String imgSrc, String imgDist, int maxSize) {
		boolean b = false;
		File srcfile = new File(imgSrc);
		if (!srcfile.exists()) {
			return b;
		}
		File destFile = new File(imgDist);
		if(!destFile.getParentFile().exists()){
			System.out.println(" >> 创建生成缩略图后的存放绝对路径["+imgDist+"]");
			destFile.getParentFile().mkdir();
		}
		try {
			Image src = javax.imageio.ImageIO.read(srcfile);
			
			float width = src.getWidth(null);
			float height = src.getHeight(null);
			
			/* 获得图片缩放的比例，哪个大就以哪个为标准
			 * 前面的width或height得设置为float类型，否则相除得整型0
			 *  */
			float proportion = width > height ? maxSize/width : maxSize/height;

			int widthDist = Math.round(width*proportion);
			int heightDist = Math.round(height*proportion);
			
			reduceImg(src, imgDist, widthDist, heightDist);
			b = true;
		} catch (IOException e) {
			e.printStackTrace();
			return b;
		}
		
		return b;
	}

	public static void reduceImg(Image src, String imgDist, int widthDist,
			int heightDist) throws ImageFormatException, IOException {
			File destFile = new File(imgDist);
			if(!destFile.getParentFile().exists()){
				System.err.println("不存在文件夹["+destFile.getParentFile().getAbsolutePath()+"]，创建它!");
				destFile.getParentFile().mkdirs();
			}
			
			if (widthDist <= 0 || heightDist <= 0 ) {
				throw new ImageFormatException("缩略图高宽不能等于小于0");
			}
			// TYPE_INT_RGB TYPE_BYTE_BINARY  BufferedImage.TYPE_INT_RGB
			BufferedImage tag = new BufferedImage(widthDist, heightDist,
					BufferedImage.TYPE_INT_RGB);

			// tag.getGraphics().drawImage(src.getScaledInstance(widthDist,
			// heightDist, Image.SCALE_SMOOTH), 0, 0, null);
			tag.getGraphics().drawImage(
					src.getScaledInstance(widthDist, heightDist,
							Image.SCALE_SMOOTH), 0, 0, null); //SCALE_AREA_AVERAGING

			FileOutputStream out = new FileOutputStream(imgDist);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			
			//设置图片清晰度
			JPEGEncodeParam   param   =   encoder.getDefaultJPEGEncodeParam(tag); 
			param.setQuality(10F,   false);
			
			encoder.setJPEGEncodeParam(param); 
			encoder.encode(tag);
			out.close();
	}

	/**
	 * 
	 * @param imgSrc
	 * @return
	 */
	public static float[] getImageWH(String imgSrc){
		float[] config = {0.0f,0.0f};
		try{
	        File srcfile = new File(imgSrc);   
	        if (!srcfile.exists()) {   
	            return config;   
	        }
	        Image src = ImageIO.read(srcfile); 
	        
	        //原始图像高和宽
	        float width  = src.getWidth(null);
	        float height  = src.getHeight(null);
			
	        if(width > 0){
	        	config[0] = width;
	        }
	        if(height > 0){
	        	config[1] = height;
	        }
		}catch(Exception e){
			System.err.print(" >> ReduceImage.Exception:"+e);
		}
		return config;
	}
	
	/**
	 * 从字节数组中获取图片对象
	 * @param body
	 * @return
	 */
	public static float[] getImageWH(byte[] body){
		 float[] config = {0.0f,0.0f};
		 try{
			 InputStream sbs = new ByteArrayInputStream(body);		 
	         Image src = ImageIO.read(sbs); 
	        
	        //原始图像高和宽
	        float width  = src.getWidth(null);
	        float height  = src.getHeight(null);
			
	        if(width > 0){
	        	config[0] = width;
	        }
	        if(height > 0){
	        	config[1] = height;
	        }
		 }catch(Exception e){
			 System.err.print(" >> ReduceImage.Exception:"+e);
		 }
		 return config;
	}
	
	/**
	 * 按原始比例缩小图片至targetLength大小，并写入源文件（覆盖）。如果图片目标实际小于targetLength，则保持图像不变。
	 * isWidth参数表示targetLength 指的是宽度还是高度，true为宽度。
	 * 此方法在WEB应用中，可以方便裁剪提交上来的过大的图像，而不失真。
	 * @param imgsrc
	 * @param targetLength
	 * @param isWidth
	 */
	
	public static void reduceImg(String imgsrc, int targetLength,boolean isWidth) {   
	    try {   
	        File srcfile = new File(imgsrc);   
	        if (!srcfile.exists()) {   
	            return;   
	        }
	        Image src = ImageIO.read(srcfile);   
	        
	        //原始图像高和宽
	        int width  = src.getWidth(null);
	        int height  = src.getHeight(null);
	        
	        int widthdist = 0;
	        int heightdist = 0;
	        
	        //确定图像的缩放后的高和宽
	        if(isWidth){
	        	if(targetLength >= width) return;
	        	double scale = targetLength * 1.0/ width;
	        	widthdist = targetLength;
	        	heightdist = (int) (height*scale);
	        }else{
	        	if(targetLength >= height) return;
	        	double scale = targetLength * 1.0/ height;
	        	widthdist = (int) (width*scale);
	        	heightdist = targetLength;
	        }
	        BufferedImage tag= new BufferedImage((int) widthdist, (int) heightdist,   
	                BufferedImage.TYPE_INT_RGB);   
	  
	        tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist,  Image.SCALE_FAST), 0, 0,  null);  //根据缩略图要求品质可以选择 Image.SCALE_SMOOTH
	        String formatName = getFormatName(srcfile);//此句必须在new FileOutputStream之前，因为是替换原图，FileOutputStream对象未关闭之前，ImageInputStream无法获得文件格式。
	        FileOutputStream out = new FileOutputStream(srcfile);
	        ImageIO.write(tag, formatName, out);
	        out.flush();
	        out.close();   
	  
	    } catch (IOException ex) {   
	        ex.printStackTrace();   
	    }   
	}  
	
	/**
	 * 获取格式化的图片
	 * @param o
	 * @return
	 */
	public static String getFormatName(File o) {
	    try {
	        // Create an image input stream on the image
	        ImageInputStream iis = ImageIO.createImageInputStream(o);

	        // Find all image readers that recognize the image format
	        Iterator iter = ImageIO.getImageReaders(iis);
	        if (!iter.hasNext()) {
	            // No readers found
	            return null;
	        }
	        // Use the first reader
	        ImageReader reader = (ImageReader)iter.next();

	        // Close stream
	        iis.close();

	        // Return the format name
	        return reader.getFormatName();
	    } catch (IOException e) {
	    }
	    // The image could not be read
	    return null;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		float width = 420.0f;
		float height = 610.0f;

		String imageSrc = "D:/TMP/43502_1.jpg";
		String imgDist = "D:/TMP/43502_1_ss.jpg";
		long start = System.currentTimeMillis();
//		ReduceImage.reduceImg(imageSrc, imgDist, 85,85);
//		boolean b = ReduceImage.reduceImg(imageSrc, imgDist, 85);
//		if(b){
//			System.out.println("成功!");
//		}else{
//			System.out.println("失败!");
//		}
//		reduceImg("D:/TMP/trans/7c681bd8-10fc-4cc8-9f9f-c2d11c909aac.jpg", "D:/TMP/trans/l_7c681bd8-10fc-4cc8-9f9f-c2d11c909aac.jpg",320);
//		long end = System.currentTimeMillis();
//		System.out.println("耗用时间为:  --  " + (end - start) + "毫秒");
//		System.out.println("耗用时间为:  --  " + (end - start) / 1000.0 + "秒");
		float pr = width > height ? 320/width : 320/height;		
		System.out.println("(610/320) = "+(pr > 0));
	}

}

