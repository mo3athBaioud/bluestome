package android.skymobi.messenger.net.client.bean;

public class NetResponse  extends NetBean{
    //状态码
	protected int resultCode = 200;
	//提示语
	protected String resultHint = "";
    
	public String getResultHint() {
		return resultHint;
	}
	
	public void setNetError(){
		this.resultCode=-1;
		this.resultHint="请求超时";
	}
	
	public void setSuccess(){
		this.resultCode=200;
		this.resultHint="成功";
	}
	
	public void setResult(int code, String hint){
		this.resultCode = code;
		this.resultHint = hint;
	}
	
    public boolean isSuccess(){
		return resultCode==200?true:false;
	}
	
	public boolean isNetError(){
		return resultCode==-1?true:false;
	}
	
	public boolean isFailed(){
		return resultCode==200?false:true;
	}
	
	/**
     * @return the resultCode
     */
    public int getResultCode() {
        return resultCode;
    }

    public String sprintResult(){
		return "("+resultCode+", "+resultHint+")";
	}
}
