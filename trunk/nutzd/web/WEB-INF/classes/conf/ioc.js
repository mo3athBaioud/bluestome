/*
 * 开始写上 var ioc = { ， 是为了利用 eclipse 的  javascript 编辑器的自动格式化功能
 */
var ioc = {
        /*
         * 默认的，你仅仅需要直接声明每个字段的值即可，Nutz.Ioc 会为你转型
         */
        article : {
        	events : {
                fetch : 'onFetch'
	        },
	        fields:{
                id : 12,
                title : 'Ioc Injects'
	        }
        },
        
        website:{
	        	type : 'com.nutzd.domain.Website',
	        	singleton : false, // 是否为单件
	        	fields : {
	        		id:2,
	        		name: {env : "JAVA_HOME"}
	        	}
        },
        /*
         * 你当然也可以做更细致的设置
         */
        xiaohei : {
                type : 'com.nutzd.domain.Article', // 类型
                singleton : false, // 是否为单件
//                args : [ 'XiaoHei' ], // 构造函数参数
                fields : {
	                id : 13,
	                title : 'Ioc Injects With Detail',
	                website:{refer : 'website'} 
                }
        }
}