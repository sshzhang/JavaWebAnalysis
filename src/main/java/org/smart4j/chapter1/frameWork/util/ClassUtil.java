package org.smart4j.chapter1.frameWork.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 类操作工具类
 */
public class ClassUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);

    /**
     * 获取类的加载器
     */

    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }


    /**
     * 加载类
     * isInitialized  true 表示初始化类（初始化静态变量,执行静态代码块）  false不初始化类
     */
    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> cls;
        try {
            cls = Class.forName(className, isInitialized, getClassLoader());
        } catch (Exception e) {
            LOGGER.error("load class failure ", e);
            throw new RuntimeException(e);
        }
        return cls;
    }

    /**
     * 默认会初始化， 执行动态代码块
     * @param className
     * @return
     */
    public static Class<?> loadClass(String className) {
        return loadClass(className, true);
    }


    /**
     * 获取指定报名下的所有类
     */

    public static Set<Class<?>> getClassSet(String packageName) {

        Set<Class<?>> classes = new HashSet<>();
        try {
            Enumeration<URL> urls =
                    getClassLoader().getResources(packageName.replace(".", "/"));

            while (urls.hasMoreElements()) {

                URL url = urls.nextElement();
                if (url != null) {

                    String protocol = url.getProtocol();
                    if (protocol.equals("file")) {
                        //包的全路径信息
                        String packagePath = url.getPath().replaceAll("%20", " ");

                        addClass(classes, packagePath, packageName);

                    } else if (protocol.equals("jar")) {
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        if (jarURLConnection != null) {
                            JarFile jarFile =
                                    jarURLConnection.getJarFile();

                            if (jarFile != null) {

                                Enumeration<JarEntry> jarEntries =
                                        jarFile.entries();

                                while (jarEntries.hasMoreElements()) {
                                    JarEntry jarEntry = jarEntries.nextElement();
                                    // jarEntryName 表示的是jar类的包名比如 org.apach.comms.xxx.class
                                    String jarEntryName = jarEntry.getName();
                                    if (jarEntryName.endsWith(".class")) {
                                        String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                                        doAddClass(classes, className);
                                    }
                                }

                            }
                        }
                    }
                }

            }

        } catch (Exception e) {
            LOGGER.error("get class set failure", e);
            throw new RuntimeException(e);
        }


        return classes;
    }


    /**
     * 添加类信息
     *
     * @param classSet    保存所有的类信息
     * @param packagePath 包的全路径
     * @param packageName 包名称
     */
    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {

        //过滤出目录或者.class文件
        File[] files = new File(packagePath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
            }

        });

        for (File file : files) {
            //文件名或者包最后一个名称
            String fileName = file.getName();
            //如果是文件，不是目录
            if (file.isFile()) {
                //移出文件扩展名 后的名称
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                if (StringUtil.isNotEmpty(packageName)) {
                    //类名称
                    className = packageName + "." + className;
                }
                //添加类信息到classSet
                doAddClass(classSet, className);
            } else {
                //子包的全路径名称
                String subPackagePath = fileName;
                //最总的子包全路径名称
                if (StringUtil.isNotEmpty(packagePath)) {
                    subPackagePath = packagePath + "/" + subPackagePath;
                }
                //子包名称
                String subPackageName = fileName;
                //子包名称
                if (StringUtil.isNotEmpty(packageName)) {
                    subPackageName = packageName + "." + subPackageName;
                }
                addClass(classSet, subPackagePath, subPackageName);
            }
        }
    }


    /**
     * 把类信息添加到集合中
     *
     * @param classSet
     * @param className
     */
    private static void doAddClass(Set<Class<?>> classSet, String className) {
        Class<?> aClass = loadClass(className, false);
        classSet.add(aClass);
    }

    public static void main(String... args) {
        Set<Class<?>> classSet = ClassUtil.getClassSet("org.smart4j.chapter1");
        System.out.println(classSet.size());
    }

}
