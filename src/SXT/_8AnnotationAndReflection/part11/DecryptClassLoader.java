package SXT._8AnnotationAndReflection.part11;

import jdk.internal.util.xml.impl.Input;

import java.io.*;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单实现加密解密类加载器
 * @author: LiuDongMan
 * @createdDate: 2019-09-26 09:04
 **/
public class DecryptClassLoader extends ClassLoader {
    private String rootDir;

    public DecryptClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }

    public DecryptClassLoader() {
    }

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
//                e.printStackTrace();
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
        String path = this.rootDir + "/" + name.replace('.', '/') + ".class";

        try (InputStream is = new FileInputStream(path);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            int length = -1;

            while ((length = is.read()) != -1) {
                baos.write(length ^ 0xff);  // 取反操作
            }

            baos.flush();

            return baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        EncryptUtils.encryptClassFile("D:/JavaTest/HelloWorld.class", "D:/JavaTest/Temp/HelloWorld.class");
        FileSystemClassLoader fscl = new FileSystemClassLoader("D:/JavaTest/Temp");
        DecryptClassLoader dcl = new DecryptClassLoader("D:/JavaTest/Temp");

        try {
            // 由于字节码文件进行了加密操作，因此之前的自定义文件系统类加载器不能使用
//            Class<?> aClass = fscl.loadClass("HelloWorld");
//
//            System.out.println(aClass);

            Class<?> bClass = dcl.loadClass("SXT._8AnnotationAndReflection.part11.HelloWorld");

            System.out.println(bClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class EncryptUtils {
    public static void encryptClassFile(String src, String dest) {
        InputStream is = null;
        OutputStream os = null;

        try {
            is = new BufferedInputStream(new FileInputStream(src));
            os = new BufferedOutputStream(new FileOutputStream(dest));
            int length = -1;

            while ((length = is.read()) != -1) {
                os.write(length ^ 0xff);
            }

            os.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
