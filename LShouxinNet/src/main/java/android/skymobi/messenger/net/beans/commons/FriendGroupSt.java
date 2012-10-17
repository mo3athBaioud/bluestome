package android.skymobi.messenger.net.beans.commons;

import android.skymobi.messenger.net.utils.SysUtils;

import com.skymobi.android.bean.esb.annotation.EsbField;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.ArrayList;

public class FriendGroupSt{
	@EsbField(index = 0, bytes = 1)
	private int groupType; //组类型

	@EsbField(index = 1, bytes = 1)
	private int nameLen;

	@EsbField(index = 2, length="nameLen", charset="UTF-16BE")
	private String groupName;

	@EsbField(index = 3, bytes = 2)
	private int memberCount;

	@EsbField(index = 4, length="memberCount" , charset="UTF-16BE")
	private ArrayList<FriendItemSt> members = new ArrayList<FriendItemSt>();

	public int getGroupType() {
		return groupType;
	}

	public int getNameLen() {
		return nameLen;
	}

	public String getGroupName() {
		return groupName;
	}

	public int getMemberCount() {
		if( memberCount == 0 ){
			if( members == null )
				return memberCount;
			else
				return members.size();
		}
		return memberCount;
	}

	public ArrayList<FriendItemSt> getMembers() {
		return members;
	}

	public void setGroupType(int groupType) {
		this.groupType = groupType;
	}

	public void setNameLen(int nameLen) {
		this.nameLen = nameLen;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
		this.nameLen=((Integer)SysUtils.length(groupName)).shortValue();
	}

	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}

	public void setMembers(ArrayList<FriendItemSt> members) {
		this.members = members;
		if( members!=null) 
			this.memberCount=members.size();
	}

	public String toString() {

        return  ToStringBuilder.reflectionToString(this,
                            ToStringStyle.SHORT_PREFIX_STYLE);
    }

//	public static FriendGroupSt toFriendGroupSt(FriendGroupSt group){
//		FriendGroupSt fg = new FriendGroupSt();
//		fg.setGroupName( group.getGroupName() );
//		fg.setGroupType( group.getGroupType() );
//		fg.setMembers( FriendItemSt.toFriendItemSt(group.getMembers()) );
//		return fg;
//	}
//	public static ArrayList<FriendGroupSt> toFriendGroupSt(ArrayList<FriendGroupSt> groups){
//		if( groups==null )return null;
//		ArrayList<FriendGroupSt> fgs = new ArrayList<FriendGroupSt>();
//
//		for(FriendGroupSt group:groups){
//			fgs.add(FriendGroupSt.toFriendGroupSt(group));
//		}
//		return fgs;
//	}
}
