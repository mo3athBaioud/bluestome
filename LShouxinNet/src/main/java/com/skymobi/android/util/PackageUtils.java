package com.skymobi.android.util;

import com.skymobi.android.commons.logger.Logger;
import com.skymobi.android.commons.logger.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Pattern;

/**
 * Utility class that finds all the classes in a given package.
 * 
 * Created on Feb 24, 2006
 * 
 * @author <a href="mailto:cedric@beust.com">Cedric Beust</a>
 */
public class PackageUtils {

    private static final Logger logger = LoggerFactory
            .getLogger(PackageUtils.class);

    private static final List<String> EMPTY_LIST = new ArrayList<String>();
    
    //	此处实现 对于 非recursive 情况没有处理，在"xxx.yyy"情况下均会获取包括子包下的所有resource
    //	与参数为"xxx.yyy.*"无区�?
    //	TODO fix bug
    public static String[] getResourceInPackage(String packageName) throws IOException {
        String packageOnly = packageName;
        boolean recursive = false;
        if (packageName.endsWith(".*")) {
            packageOnly = packageName.substring(0, packageName
                    .lastIndexOf(".*"));
            recursive = true;
        }
        
        if (packageOnly.endsWith("/")) {
        	packageOnly = packageOnly.substring(0, packageName.length() - 1 );
        }

        List<String> vResult = new ArrayList<String>();
        String packageDirName = packageOnly.replace('.', '/');
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        if ( logger.isDebugEnabled() ) {
            logger.debug("using classloader: " + cl);
        }
        Enumeration<URL> dirs = cl.getResources(packageDirName);
        if ( logger.isDebugEnabled() ) {
            logger.debug("PackageUtils: getResources: " + dirs
            		+", hasMoreElements:" + dirs.hasMoreElements() );
        }
        while (dirs.hasMoreElements()) {
            URL url = dirs.nextElement();
            String protocol = url.getProtocol();
            if ( logger.isDebugEnabled() ) {
                logger.debug("PackageUtils: url: " + url);
            }
            
            if ("file".equals(protocol)) {
                getResourceInDirPackage(packageOnly,
                        URLDecoder.decode(url.getFile(), "UTF-8"), recursive,
                        vResult);
            } 
            else if ("jar".equals(protocol)) {
                JarFile jar = ((JarURLConnection) url.openConnection())
                        .getJarFile();
                Enumeration<JarEntry> entries = jar.entries();
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    String name = entry.getName();
                    if (name.charAt(0) == '/') {
                        name = name.substring(1);
                    }
                    if (name.startsWith(packageDirName)) {
                        int idx = name.lastIndexOf('/');
                        if (idx != -1) {
                            packageName = name.substring(0, idx).replace('/',
                                    '.');
                        }

                        if ( logger.isDebugEnabled() ) {
                            logger.debug("PackageUtils: Package name is " + packageName);
                        }

                        if ((idx != -1) || recursive) {
                            // it's not inside a deeper dir
                            if (!entry.isDirectory()) {
                                String resName = name.substring(packageName
                                        .length() + 1);
                            	vResult.add(packageName + "." + resName);
                            }
                        }
                    }
                }
            }
            else {
            	String path = url.getPath();
            	if ( path.startsWith(packageDirName) ) {
            		vResult.add(path.replace('/', '.'));
            	}
            }
        }

        String[] result = vResult.toArray(new String[vResult.size()]);
        return result;
    }
    
    private static void getResourceInDirPackage(String packageName, String packagePath,
            final boolean recursive, List<String> classes) {
        File dir = new File(packagePath);

        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }

        File[] dirfiles = dir.listFiles();

//        if ( logger.isDebugEnabled() ) {
//            logger.debug("PackageUtils: Looking for test classes in the directory: "
//                    + dir);
//        }
        for (File file : dirfiles) {
            if (file.isDirectory()) {
            	getResourceInDirPackage(packageName + "." + file.getName(),
                		file.getAbsolutePath(), recursive,
                        classes);
            } else {
            	classes.add(packageName + "." + file.getName());
            }
        }
    }
    
    /**
     * 
     * @param packageName
     * @return The list of all the classes inside this package
     * @throws IOException
     */
    public static String[] findClassesInPackage(String packageName,
            List<String> included, List<String> excluded) throws IOException {
        String packageOnly = packageName;
        boolean recursive = false;
        if (packageName.endsWith(".*")) {
            packageOnly = packageName.substring(0, packageName
                    .lastIndexOf(".*"));
            recursive = true;
        }

        List<String> vResult = new ArrayList<String>();
        String packageDirName = packageOnly.replace('.', '/');
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        if ( logger.isDebugEnabled() ) {
            logger.debug("using classloader: " + cl);
        }
        Enumeration<URL> dirs = cl.getResources(packageDirName);
        if ( logger.isDebugEnabled() ) {
            logger.debug("PackageUtils: getResources: " + dirs
            		+", hasMoreElements:" + dirs.hasMoreElements() );
        }
        while (dirs.hasMoreElements()) {
            URL url = dirs.nextElement();
            String protocol = url.getProtocol();
            // if(!matchTestClasspath(url, packageDirName, recursive)) {
            // continue;
            // }
            if ( logger.isDebugEnabled() ) {
                logger.debug("PackageUtils: url: " + url);
            }
            
            if ("file".equals(protocol)) {
                findClassesInDirPackage(packageOnly, included, excluded,
                        URLDecoder.decode(url.getFile(), "UTF-8"), recursive,
                        vResult);
            } 
            else if ("jar".equals(protocol)) {
                JarFile jar = ((JarURLConnection) url.openConnection())
                        .getJarFile();
                Enumeration<JarEntry> entries = jar.entries();
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    String name = entry.getName();
                    if (name.charAt(0) == '/') {
                        name = name.substring(1);
                    }
                    if (name.startsWith(packageDirName)) {
                        int idx = name.lastIndexOf('/');
                        if (idx != -1) {
                            packageName = name.substring(0, idx).replace('/',
                                    '.');
                        }

                        if ( logger.isDebugEnabled() ) {
                            logger.debug("PackageUtils: Package name is " + packageName);
                        }

                        // Utils.log("PackageUtils", 4, "Package name is " +
                        // packageName);
                        if ((idx != -1) || recursive) {
                            // it's not inside a deeper dir
                            if (name.endsWith(".class") && !entry.isDirectory()) {
                                String className = name.substring(packageName
                                        .length() + 1, name.length() - 6);
                                
                                if ( logger.isDebugEnabled() ) {
                                    logger.debug("PackageUtils: Found class "
                                                + className
                                                + ", seeing it if it's included or excluded");
                                }
                                includeOrExcludeClass(packageName, className,
                                        included, excluded, vResult);
                            }
                        }
                    }
                }
            }
            else {
            	String path = url.getPath();
            	if ( path.startsWith(packageDirName) && path.endsWith(".class") ) {
                    String pkg = "";
                    int idx = path.lastIndexOf('/');
                    if (idx != -1) {
                        pkg = path.substring(0, idx).replace('/', '.');
                    }
                    String className = path.substring(pkg.length() + 1, path.length() - 6);
                    includeOrExcludeClass(pkg, className, included, excluded, vResult);
            	}
            }
        }

        String[] result = vResult.toArray(new String[vResult.size()]);
        return result;
    }

    // private static String[] getTestClasspath() {
    // if(null != s_testClassPaths) {
    // return s_testClassPaths;
    // }
    //    
    // String testClasspath=
    // System.getProperty(TestNGCommandLineArgs.TEST_CLASSPATH);
    // if(null == testClasspath) {
    // return null;
    // }
    //    
    // String[] classpathFragments= Utils.split(testClasspath,
    // File.pathSeparator);
    // s_testClassPaths= new String[classpathFragments.length];
    //    
    // for(int i= 0; i < classpathFragments.length; i++) {
    // String path= null;
    // if(classpathFragments[i].toLowerCase().endsWith(".jar") ||
    // classpathFragments[i].toLowerCase().endsWith(".zip")) {
    // path= classpathFragments[i] + "!/";
    // }
    // else {
    // if(classpathFragments[i].endsWith(File.separator)) {
    // path= classpathFragments[i];
    // }
    // else {
    // path= classpathFragments[i] + "/";
    // }
    // }
    //      
    // s_testClassPaths[i]= path.replace('\\', '/');
    // }
    //    
    // return s_testClassPaths;
    // }

    /**
     * @param url
     * @return
     */
    // private static boolean matchTestClasspath(URL url, String lastFragment,
    // boolean recursive) {
    // String[] classpathFragments= getTestClasspath();
    // if(null == classpathFragments) {
    // return true;
    // }
    //    
    // String protocol = url.getProtocol();
    // String fileName= null;
    // try {
    // fileName= URLDecoder.decode(url.getFile(), "UTF-8");
    // }
    // catch(UnsupportedEncodingException ueex) {
    // ; // ignore. should never happen
    // }
    //    
    // for(String classpathFrag: classpathFragments) {
    // String path= classpathFrag + lastFragment;
    // int idx= fileName.indexOf(path);
    // if((idx == -1) || (idx > 0 && fileName.charAt(idx-1) != '/')) {
    // continue;
    // }
    //      
    // if(fileName.endsWith(classpathFrag + lastFragment)
    // || (recursive && fileName.charAt(idx + path.length()) == '/')) {
    // return true;
    // }
    // }
    //
    // return false;
    // }
    private static void findClassesInDirPackage(String packageName,
            List<String> included, List<String> excluded, String packagePath,
            final boolean recursive, List<String> classes) {
        File dir = new File(packagePath);

        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }

        File[] dirfiles = dir.listFiles(new FileFilter() {
            @Override
			public boolean accept(File file) {
                return (recursive && file.isDirectory())
                        || (file.getName().endsWith(".class"));
            }
        });

        if ( logger.isDebugEnabled() ) {
            logger.debug("PackageUtils: Looking for test classes in the directory: "
                    + dir);
        }
        for (File file : dirfiles) {
            if (file.isDirectory()) {
                findClassesInDirPackage(packageName + "." + file.getName(),
                        included, excluded, file.getAbsolutePath(), recursive,
                        classes);
            } else {
                String className = file.getName().substring(0,
                        file.getName().length() - 6);
                
                if ( logger.isDebugEnabled() ) {
                    logger.debug("PackageUtils: Found class " + className
                            + ", seeing it if it's included or excluded");
                }
                includeOrExcludeClass(packageName, className, included,
                        excluded, classes);
            }
        }
    }

    private static void includeOrExcludeClass(String packageName,
            String className, List<String> included, List<String> excluded,
            List<String> classes) {
        if (isIncluded(className, included, excluded)) {
            if ( logger.isDebugEnabled() ) {
                logger.debug("PackageUtils: ... Including class " + className);
            }
            classes.add(packageName + '.' + className);
        } else {
            if ( logger.isDebugEnabled() ) {
                logger.debug("PackageUtils: ... Excluding class " + className);
            }
        }
    }

    /**
     * @return true if name should be included.
     */
    private static boolean isIncluded(String name, List<String> included,
            List<String> excluded) {
        boolean result = false;

        //
        // If no includes nor excludes were specified, return true.
        //
        if ( null == included ) {
        	included = EMPTY_LIST;
        }
        
        if ( null == excluded ) {
        	excluded = EMPTY_LIST;
        }
        
        if (included.size() == 0 && excluded.size() == 0) {
            result = true;
        } else {
            boolean isIncluded = PackageUtils.find(name, included);
            boolean isExcluded = PackageUtils.find(name, excluded);
            if (isIncluded && !isExcluded) {
                result = true;
            } else if (isExcluded) {
                result = false;
            } else {
                result = included.size() == 0;
            }
        }
        return result;
    }

    private static boolean find(String name, List<String> list) {
        for (String regexpStr : list) {
            if (Pattern.matches(regexpStr, name))
                return true;
        }
        return false;
    }
    
}