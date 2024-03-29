/**
 * 
 */
package org.bluestome.pcs.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.bluestome.pcs.data.DataProcess;
import org.bluestome.pcs.parser.BIZHIParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bluestome
 * 
 */
public class Main {
	private static Logger logger = LoggerFactory.getLogger(Main.class);

	private static ScheduledExecutorService scheduledThreadPool = Executors
			.newScheduledThreadPool(Runtime.getRuntime().availableProcessors(),new ThreadFactory (){

				public Thread newThread(Runnable r) {
					return new Thread(r,"parser-thread");
				}
				
	});

	private static ExecutorService mThreadPool = Executors
	.newFixedThreadPool(Runtime.getRuntime().availableProcessors(),new ThreadFactory (){
		public Thread newThread(Runnable r) {
			return new Thread(r,"parser-thread");
		}
		
});
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        
        if(args.length > 0){
            if(args[0].equals("processdata")){
                //处理数据
                DataProcess.getInstance().process();
            }
        } else {
            scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
                public void run() {
                    try {
                        BIZHIParser.update();
                    } catch (Exception e) {
                    }
                }
            }, 2, 8 * 60 * 60, TimeUnit.SECONDS);
        }
	}

}
