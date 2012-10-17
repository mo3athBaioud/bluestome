package android.skymobi.messenger.net.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @ClassName: FileUtils
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-3-22 下午09:31:09
 */
public class FileUtils {

    /**
     * 将字节数组保存在指定文件中
     * 
     * @param pathname
     *            需要保存的文件路径
     * @param buffer
     *            需要被保存的内容
     * @param append
     *            是否采用追加的方式
     * @return
     */
    public static int saveFile(String pathname, byte[] buffer, boolean append) {
        String path = null;
        pathname = pathname.replaceAll("\\\\", "/");
        path = pathname.substring(0, pathname.lastIndexOf("/"));
        java.io.File dir = new java.io.File(path);
        if (!dir.isDirectory())
            dir.mkdirs();// 创建不存在的目录
        try {
            // writeln the file to the file specified
            OutputStream bos = new FileOutputStream(pathname, append);
            bos.write(buffer);
            bos.close();
        } catch (FileNotFoundException fnfe) {
            return -1;
        } catch (IOException ioe) {
            return -2;
        }
        return 0;
    }

}
