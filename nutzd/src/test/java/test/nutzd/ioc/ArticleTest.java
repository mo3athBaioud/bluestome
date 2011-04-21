package test.nutzd.ioc;

import org.nutz.ioc.Ioc;
import org.nutz.ioc.Ioc2;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;

import com.nutzd.domain.Article;
public class ArticleTest {

	public static void main(String args[]){
		Ioc2 ioc = new NutIoc(new JsonLoader("conf/ioc.js"));
		Article article = ioc.get(Article.class,"article");
		System.out.println(" >> id:"+article.getId()+"| title:"+article.getTitle()+"|"+article.getItotal());
		
		Article article1 = ioc.get(null,"xiaohei");
		System.out.println(" >> id:"+article1.getId()+"| title:"+article1.getTitle()+"|"+article.getItotal());
		if(null != article1.getWebsite()){
			System.out.println(" >> website:"+article1.getWebsite().getId()+"|"+article1.getWebsite().getName());
		}
		
	}
}
