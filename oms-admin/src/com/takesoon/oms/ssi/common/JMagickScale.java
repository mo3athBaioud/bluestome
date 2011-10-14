package com.takesoon.oms.ssi.common;

import java.awt.Dimension;
import java.io.File;

import com.ssi.common.utils.FileUtils;
import com.ssi.common.utils.HttpClientUtils;

import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;

public class JMagickScale {
	
	private static String URL = "http://bizhi.zhuoku.com/2011/07/26/jingxuan/jingxuan021.jpg";
	
	private static JMagickScale instance = null;
	
	//输入的数据源 本地/外部
	private String in;
	
	//外部引用的地址
	private String refer = null;
	
	private String savePath = System.getProperty("java.io.tmpdir");
	
	private Double width = 0.0;
	
	private Double height = 0.0;
	
	private int maxSize = 500;
	
	//是否需要缩放
	private boolean isScale = true;
	
	private JMagickScale(){
		System.setProperty("jmagick.systemclassloader", "no");
	}
	
	public JMagickScale(String in) throws Exception{
		System.setProperty("jmagick.systemclassloader", "no");
		this.in = in;
		adapter();
	}
	
	/**
	 * 适配输入参数
	 * @throws Exception
	 */
	private void adapter() throws Exception{
		if(null == in){
			throw new Exception("请输入需要处理的图片地址");
		}
		if(in.startsWith("http://") || in.startsWith("ftp://") || in.startsWith("https://")){
			//TODO 下载到本地
			in = getFileFromURL(refer,in);
		}
		if(null != in){
			//TODO 获取宽高
			wh(in);
		}
		clear(in);
	}
	
	public void process(String refer,String in) throws Exception{
		if(null == in)
		{
			throw new Exception("请输入需要处理的图片地址");
		}
		if(in.startsWith("http://") || in.startsWith("ftp://") || in.startsWith("https://"))
		{
			//TODO 下载到本地
			in = getFileFromURL(refer,in);
		}
		if(null != in)
		{
			//TODO 获取宽高
			wh2(in);
		}
		clear(in);
	}
	
	/**
	 * 从字节流中获取图片属性
	 * @param body
	 */
	public void process(byte[] body){
		if(null != body && body.length > 0)
		{
			wh2(body);
		}
	}
	
	public static JMagickScale getInstance(){
		if(null == instance){
			instance = new JMagickScale();
		}
		return instance;
	}
	/**
	 * Description:
	 * 
	 * @param args
	 * @throws MagickException
	 */
	public static void main(String[] args) throws MagickException {
		try {
			JMagickScale jm = new JMagickScale(URL);
			System.out.println(" > width:" + jm.getWidth());
			System.out.println(" > height:" +jm.getHeight());
			
			JMagickScale tp = JMagickScale.getInstance();
			tp.process(null,"http://bizhi.zhuoku.com/2011/07/25/nationalgeographic/nationalgeographic18.jpg");
			System.out.println(" > tp.width:" + tp.getWidth());
			System.out.println(" > tp.height:" +tp.getHeight());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * // resize image MagickImage scaleImg = image.scaleImage(95, 80);
		 *  // write image to file
		 * scaleImg.setFileName("d:/15483551_2009051516261356866811.jpg");
		 * scaleImg.writeImage(info);
		 */
	}
	
	/**
	 * 获取宽高参数
	 * @throws MagickException 
	 *
	 */
	public void wh(String in) throws MagickException{
		System.out.println(" > in:"+in);
		ImageInfo info = new ImageInfo(in);
		if(null != info)
		{
			MagickImage image = new MagickImage(info);
			if(null != image)
			{
				Dimension dim = image.getDimension();
				if(null != dim)
				{
					width = dim.getWidth();
					height = dim.getHeight();
					
					System.out.println(" > o.width:" + width);
					System.out.println(" > o.height:" + height);
					//TODO 是否需要等比算出宽和高
					if(isScale)
					{
						//缩放比率
						double pr = width > height ? maxSize/width : maxSize/height;
						//缩放后的宽高
						width = pr*width;
						height = pr*height;
					}
				}
				System.out.println(" > img.type:"+image.getImageType());
			}
		}
	}
	
	/**
	 * 另一种获取图片宽高的算法
	 * @param in
	 */
	public void wh2(String in){
		float[] wh = ReduceImage.getImageWH(in);
		width = Double.valueOf(wh[0]);
		height = Double.valueOf(wh[1]);
		if(isScale)
		{
			//缩放比率
			double pr = width > height ? maxSize/width : maxSize/height;
			//缩放后的宽高
			width = pr*width;
			height = pr*height;
		}
	}
	
	/**
	 * 从字节数组中获取图片宽高
	 * @param body
	 */
	public void wh2(byte[] body){
		float[] wh = ReduceImage.getImageWH(body);
		width = Double.valueOf(wh[0]);
		height = Double.valueOf(wh[1]);
		System.out.println("o.width:"+width+"|o.height:"+height);
		if(isScale)
		{
			//缩放比率
			double pr = width > height ? maxSize/width : maxSize/height;
			//缩放后的宽高
			width = pr*width;
			height = pr*height;
			System.out.println("pa.width:"+width+"|pa.height:"+height);
		}
	}
	
	/**
	 * 清理生成的临时文件
	 */
	public void clear(String in){
		if(null != in){
			if(FileUtils.deleteFile(in)){
				System.out.println("删除["+in+"]成功!");
			}
		}
	}

	/**
	 * 从网络上获取文件
	 * @param url
	 * @return
	 */
	public synchronized String getFileFromURL(String refer,String url){
		String path = savePath + File.separator+System.currentTimeMillis() + getFileExt(url);
		try{
			byte[] body = HttpClientUtils.getResponseBodyAsByte(refer, null, url);
			if(null != body && body.length > 0){
				int c = FileUtils.saveFile(path, body, false);
				System.out.println(c);
				if(c != 0){
					path = null;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return path;
	}
	
	/**
	 * 获取文件扩展名
	 * @param in
	 * @return
	 */
	public static String getFileExt(String in){
		String ext = null;
		try{
			int start = in.lastIndexOf(".");
			if(start != -1){
				ext = in.substring(start);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return ext;
	}
	
	public boolean isScale() {
		return isScale;
	}

	public void setScale(boolean isScale) {
		this.isScale = isScale;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public String getRefer() {
		return refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
	}

	public String getIn() {
		return in;
	}

	public void setIn(String in) {
		this.in = in;
	}
	
	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	
	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}
	
}
