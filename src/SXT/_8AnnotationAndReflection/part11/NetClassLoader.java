package SXT._8AnnotationAndReflection.part11;

import jdk.internal.util.xml.impl.Input;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单实现自定义网络类加载器，和自定义文件系统类加载器的区别不是很大
 * @author: LiuDongMan
 * @createdDate: 2019-09-26 08:42
 **/
public class NetClassLoader extends ClassLoader {
    private String rootURL;

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> aClass = findLoadedClass(name);

        if (aClass != null) {
            return aClass;
        } else {
            ClassLoader parent = this.getParent();

            try {
                aClass = parent.loadClass(name);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (aClass != null) {
                return aClass;
            } else {
                byte[] classData = getClassData(name);

                if (classData == null) {
                    throw new ClassNotFoundException();
                } else {
                    aClass = defineClass(name, classData, 0, classData.length);
                }
            }
        }

        return aClass;
    }

    private byte[] getClassData(String name) {
        String path = this.rootURL + "/" + name.replace('.', '/') + ".class";
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            URL url = new URL(path);
            is = url.openStream();
            baos = new ByteArrayOutputStream();
            byte[] flush01 = new byte[1024];
            int length = -1;

            while ((length = is.read(flush01)) != -1) {
                baos.write(flush01, 0, length);
            }

            baos.flush();

            return baos.toByteArray();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public NetClassLoader(String rootURL) {
        this.rootURL = rootURL;
    }

    public NetClassLoader() {
    }
}
