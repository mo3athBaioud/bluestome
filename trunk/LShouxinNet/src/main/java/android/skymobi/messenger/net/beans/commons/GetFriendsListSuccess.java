package android.skymobi.messenger.net.beans.commons;

import com.skymobi.android.bean.esb.annotation.EsbField;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.ArrayList;

public class GetFriendsListSuccess implements EsbResult {
	@EsbField(index = 5,bytes=1)
	private short groupCount; //总记录数

	@EsbField(index = 6, length="groupCount" )
	private ArrayList<FriendGroupSt> groups = new ArrayList<FriendGroupSt>(); //好友列表

	public void setGroupCount(short groupCount) {
		this.groupCount = groupCount;
	}

	public int getGroupCount() {
		if( groupCount>0 )
			return groupCount;
		if( groups!=null )
			return groups.size();
		return groupCount;
	}

	public ArrayList<FriendGroupSt> getGroups() {
		return groups;
	}


	public void setGroups(ArrayList<FriendGroupSt> groups) {
		this.groups = groups;
//		if(null != groups)
//			this.groupCount = ((Integer)groups.size()).shortValue();
	}

	public String toString() {
        return  ToStringBuilder.reflectionToString(this,
                            ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
