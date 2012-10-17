package android.skymobi.messenger.net.beans.commons;

import com.skymobi.android.bean.esb.annotation.EsbField;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.ArrayList;

public class EsbSuccess implements EsbResult {
	@EsbField(index = 5, bytes = 2 )
	private int totalItems; //总记录数

	@EsbField(index = 6, bytes = 2 )
	private int totalPages; //总页数

	@EsbField(index = 7, bytes = 2 )
	private int pageNo; //当前返回的页号

	@EsbField(index = 8, bytes = 2 )
	private int pageSize; //每页长度

	@EsbField(index = 9, bytes = 2 )
	private int itemCount; //当前返回的记录数

	@EsbField(index = 10, length = "itemCount", bytes = 2 )
	private ArrayList<FriendItemSt> items; //好友列表

	public int getTotalItems() {
		return totalItems;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getPageNo() {
		return pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getItemCount() {
		if( itemCount>0 )
			return itemCount;
		if( items!=null )
			return items.size();
		return itemCount;
	}

	public ArrayList<FriendItemSt> getItems() {
		return items;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public void setItems(ArrayList<FriendItemSt> items) {
		this.items = items;
		if(items!=null)this.itemCount = items.size();
	}

	public String toString() {

        return  ToStringBuilder.reflectionToString(this,
                            ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
