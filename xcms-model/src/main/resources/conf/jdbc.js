/*
 * 开始写上 var ioc = { ， 是为了利用 eclipse 的  javascript 编辑器的自动格式化功能
 */
var jdbc = {
		/* 定义业务库 */
		dataSource : {
//                type : "org.apache.commons.dbcp.BasicDataSource",
				type: "com.mchange.v2.c3p0.ComboPooledDataSource",
                events : {
                        depose : 'close'
                },
                /**
                fields : {
                        driverClassName : 'com.mysql.jdbc.Driver',
                        url : 'jdbc:mysql://127.0.0.1:3306/wnxcms?useUnicode=true&characterEncoding=UTF-8',
                        username : 'root',
                        password : '123456'
                }
                **/
                fields : {
                        driverClass : 'com.mysql.jdbc.Driver',
                        jdbcUrl : 'jdbc:mysql://127.0.0.1:3306/wnxcms?useUnicode=true&characterEncoding=UTF-8',
                        user : 'root',
                        password : '123456',
                        minPoolSize:5,
                        maxIdleTime:150,
                        initialPoolSize:10
                }
        },
        /* 定义NutDao */
        dao : {
        		type : "org.nutz.dao.impl.NutDao",
        		fields : {
        				dataSource : {refer : 'dataSource'}
        		}
        },
        /* 定义日志库 */
		logdataSource : {
//                type : "org.apache.commons.dbcp.BasicDataSource",
				type: "com.mchange.v2.c3p0.ComboPooledDataSource",
                events : {
                        depose : 'close'
                },
                /**
                fields : {
                        driverClassName : 'com.mysql.jdbc.Driver',
                        url : 'jdbc:mysql://127.0.0.1:3306/wnxcms?useUnicode=true&characterEncoding=UTF-8',
                        username : 'root',
                        password : '123456'
                }
                **/
                fields : {
                        driverClass : 'com.mysql.jdbc.Driver',
                        jdbcUrl : 'jdbc:mysql://127.0.0.1:3306/wnxcms?useUnicode=true&characterEncoding=UTF-8',
                        user : 'root',
                        password : '123456',
                        minPoolSize:5,
                        maxIdleTime:150,
                        initialPoolSize:10
                }
        },        
        /* 定义日志NutDao */
        logdao : {
        		type : "org.nutz.dao.impl.NutDao",
        		fields : {
        				dataSource : {refer : 'dataSource'}
        		}
        }	
}