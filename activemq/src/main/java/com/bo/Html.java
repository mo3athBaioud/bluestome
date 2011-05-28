package com.bo;

import java.io.Serializable;

public class Html implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String TAG_HTML = "<html>{head}{body}</html>";
	public static String TAG_HEAD = "<head></head>";
	public static String TAG_BODY = "<body></body>";
	private String head;
	private String body;
	
	public Html() {
		this.head = "<head><title>No Title</title></head>";
		this.body = "No Content";
	}
	
	public Html(String head, String body) {
		this.head = head;
		this.body = body;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(TAG_HTML.replace("{head}",this.getHead()).replace("{body}", this.getBody()));
		return sb.toString();
	}
	
	public static void main(String[] args){
		boolean b =  false;
		Html html = null;
		try{
			if(b){
				html = new Html("<head><title>Test</title></head>","Hello Html");
				System.out.println(" >> toString:"+html.toString());
			}else{
				html = new Html();
				System.out.println(" >> toString:"+html.toString());
			}
		}catch(Exception e){
			
		}
	}
}
