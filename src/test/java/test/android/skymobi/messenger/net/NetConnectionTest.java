package test.android.skymobi.messenger.net;

import org.junit.Test;

/**
 * @ClassName: NetConnectionTest
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-5-8 下午02:11:28
 */
public class NetConnectionTest extends AbsClientNetTestCase {

    @Test
    public void mutiConnect(){
//        new Thread(new Runnable(){
//            public void run(){
//                System.out.println(" >>>>>>>>>>>>>> 111111 <<<<<<<<<<<<<<");
//                clientBiz.connect();
//                try{
//                    Thread.sleep(2000L);
//                }catch(Exception e){
//                    
//                }
//                if(clientBiz.isConnect()){
//                    clientBiz.close();
//                }
//            }
//        }).start();
//        new Thread(new Runnable(){
//            public void run(){
//                System.out.println(" >>>>>>>>>>>>>> 222222 <<<<<<<<<<<<<<");
//                clientBiz.connect();
//                try{
//                    Thread.sleep(2000L);
//                }catch(Exception e){
//                    
//                }
//                if(clientBiz.isConnect()){
//                    clientBiz.close();
//                }
//            }
//        }).start();
//        clientBiz.connect();
        try {
            Thread.sleep(6000L);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        clientBiz.close();
        try {
            Thread.sleep(10*6000L);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
