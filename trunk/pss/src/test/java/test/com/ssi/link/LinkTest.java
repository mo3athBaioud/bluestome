package test.com.ssi.link;

import com.ssi.common.htmlparser.bean.LinkBean;
import com.ssi.common.htmlparser.bean.LinkPage;

public class LinkTest {

	public static void main(String args[]){
		LinkPage page = LinkPage.getInstance();
		for(int i=0;i<10;i++){
			if(i < 5){
				page.add(new LinkBean("http://photo.fengniao.com/pic_7459777.html","端午节青海行纪实-之四",true,"http://img3.fengniao.com/forum/attachpics/187/99/7459777_160.jpg"));
			}
			page.put(String.valueOf(i), new LinkBean("http://photo.fengniao.com/pic_7459777.html","端午节青海行纪实-之四",true,"http://img3.fengniao.com/forum/attachpics/187/99/7459777_160.jpg"));
		}
		if(page.getPageCount() > 0){
			page.setHasPage(true);
		}
		if(page.isHasPage()){
			for(LinkBean link:page.getPageList()){
				System.out.println(" >> "+link.getTitle()+"|"+link.getUrl());
			}
		}
		page.clear();
		
	}

}
