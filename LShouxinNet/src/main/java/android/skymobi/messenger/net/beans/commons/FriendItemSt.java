package android.skymobi.messenger.net.beans.commons;


import android.skymobi.messenger.net.utils.SysUtils;

import com.skymobi.android.bean.esb.annotation.EsbField;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class FriendItemSt{

	@EsbField(index = 0, bytes = 4)
	private int skyid;

	@EsbField(index = 1, bytes = 2)
	private int portraitpic;

	@EsbField(index = 2)
	private int level;

	@EsbField(index = 3, bytes = 1)
	private short state;

	@EsbField(index = 4, bytes = 1)
	private short nicknameLen;

	@EsbField(index = 5, length="nicknameLen" , charset=SysUtils.DEFAULT_CHARSET)
	private String nickname;

	public int getSkyid() {
		return skyid;
	}

	public void setSkyid(int skyid) {
		this.skyid = skyid;
	}

	public int getPortraitpic() {
		return portraitpic;
	}

	public void setPortraitpic(int portraitpic) {
		this.portraitpic = portraitpic;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public short getState() {
		return state;
	}

	public void setState(short state) {
		this.state = state;
	}

	public short getNicknameLen() {
		return nicknameLen;
	}

	public void setNicknameLen(short nicknameLen) {
		this.nicknameLen = nicknameLen;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
		this.nicknameLen = ((Integer)SysUtils.length(nickname)).shortValue();
	}

	public String toString() {

        return  ToStringBuilder.reflectionToString(this,
                            ToStringStyle.SHORT_PREFIX_STYLE);
    }

//	public static FriendItemSt toFriendItemSt(FriendItemSt item){
//		FriendItemSt fit = new FriendItemSt();
//		fit.setLevel( item.getLevel() );
//		fit.setNickname( item.getNickname() );
//		fit.setPortraitpic( item.getPortraitpic() );
////		fit.setSkyid( Long.valueOf(item.getfSkyId()) );
//		fit.setState( item.getState() );
//		return fit;
//	}
//	public static ArrayList<FriendItemSt> toFriendItemSt(ArrayList<FriendItemSt> items){
//		if( items==null )return null;
//		ArrayList<FriendItemSt> fits = new ArrayList<FriendItemSt>();
//		for(FriendItemSt item:items ){
//			fits.add( FriendItemSt.toFriendItemSt(item) );
//		}
//		return fits;
//	}
}
