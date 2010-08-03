判别缓存是否存在URL数据


武器大全页面分页URL，其中的参数含义如下：
URL:http://rtuku.club.china.com//user/channelPageTypeIndexAjaxAction.do?channelId=military&pageNum={1}&eachRowNum=4&eachPageNum={2}&typeId={3}&tagId=0
1.第几页
2.每页显示的数量
3.栏目ID 已知的栏目ID如下：
	1.中国武器
	2.空中武器
	3.海战武器
	4.陆地武器
	5.制导武器
	6.天军武器
	7.后勤装备
	8.军事通信
	9.军事历史
	10.台湾省武器
	11.其他武器

例如：
http://rtuku.club.china.com//user/channelPageTypeIndexAjaxAction.do?channelId=military&pageNum=3&eachRowNum=4&eachPageNum=20&typeId=2716&tagId=0

2010-01-25
新浪首页标题新闻
1.新闻  div id="news"
   li id="newsmenu_1"
   li id="newsmenu_2"

2.视频 div id="videoCount_2_1"

3.体育 div id="sports"

4.文化 div id="blog"

5.财经 div id="finance"

6.社会 div id="society"

7.科技 div id="tech"


2010-01-26 秀图网数据统计
总种类：8330
总图片数：208337

2010-02-03 秀图网分类统计
风光壁纸 (共有702个类别,30402张壁纸)
物壁纸   (共有202个类别,8450张壁纸)
动物壁纸 (共有327个类别,10849张壁纸)
影视壁纸 (共有914个类别,8839张壁纸)
动漫壁纸 (共有1321个类别,28135张壁纸)
汽车壁纸 (共有602个类别,16707张壁纸)
游戏壁纸 (共有1184个类别,16853张壁纸)
军事壁纸 (共有120个类别,1868张壁纸)
系统壁纸 (共有80个类别,3701张壁纸)
体育壁纸 (共有260个类别,8222张壁纸)
明星壁纸 (共有673个类别,14586张壁纸)
品牌壁纸 (共有454个类别,8106张壁纸)
手绘壁纸 (共有213个类别,5667张壁纸)
设计壁纸 (共有489个类别,18459张壁纸)
女性壁纸 (共有247个类别,7549张壁纸)
主题壁纸 (共有159个类别,5080张壁纸)
其它壁纸 (共有388个类别,15055张壁纸)


2010-08-03 随记
 性能瓶颈：
 1.数据库压力
   a.缓存 [取数据]
   b.异步线程 [处理数据更新，数据删除] 需要处理
 2.IO压力
   a.文件写入 异步/缓存