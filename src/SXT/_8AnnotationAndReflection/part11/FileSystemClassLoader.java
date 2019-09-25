package SXT._8AnnotationAndReflection.part11;

import java.io.*;
import java.lang.reflect.Field;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 自定义文件系统类加载器
 * @author: LiuDongMan
 * @createdDate: 2019-09-25 09:13
 **/
public class FileSystemClassLoader extends ClassLoader {
    private String rootDir;

    public FileSystemClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> aClass = findLoadedClass(name);    // 先看指定的类是否已经被加载

        if (aClass != null) {
            return aClass;
        } else {
            ClassLoader parent = this.getParent();  // 获取父加载器

            try {
                // 根据双亲委托机制，先用父加载器进行加载，如果不能加载，再使用子加载器进行加载，但是会出现父加载器无法加载而抛出异常的情况
                aClass = parent.loadClass(name);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (aClass != null) {
                return aClass;
            } else {
                // 如果父加载器无法加载，则最后由自定义的文件系统加载器进行加载
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

    public FileSystemClassLoader() {
    }

    private byte[] getClassData(String name) {
        String path = this.rootDir + "/" + name.replace('.', '/') + ".class";
        File file = new File(path);
        byte[] flush01 = new byte[1024];
        int length = 0;

        try (InputStream is = new BufferedInputStream(new FileInputStream(file));
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            while ((length = is.read(flush01)) != -1) {
                baos.write(flush01, 0, length);
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

    }
}
