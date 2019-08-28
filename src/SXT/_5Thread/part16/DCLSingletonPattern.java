package SXT._5Thread.part16;

/**
 * @program: JavaStudy
 * @description:
 * @chineseDescription: 简单理解DCL单例模式：懒汉式（懒得创建对象）的基础上加入并发控制，保证多线程的情况下，对外只存在一个对象
 * 1.构造器私有化，避免外部随意创建对象；
 * 2.提供私有的静态属性，存储内部生成对象的地址；
 * 3.提供公共的静态方法，获取相应的对象；
 * @author: LiuDongMan
 * @createdDate: 2019-07-31 09:45
 **/
public class DCLSingletonPattern {
    /**
     * 如果未加入volatile修饰，则可能A线程在初始化对象的过程中，由于受到CPU指令重排的影响，导致提前返回一个还未初始化的对象，使得其他线程访
     * 问一个没有初始化的对象
     */
    private static volatile DCLSingletonPattern instance;

    // 为保证对外有且只有一个对象，将构造器私有化
    private DCLSingletonPattern() {
    }

    /**
     * 由于外部不能创建对象，因此，需要提供静态方法来获取对象信息
     * @return
     */
    public static DCLSingletonPattern getInstance() {
        // 加入double checking进行再次检测，防止出现不必要的同步阻塞
        if (null != instance) {
            return instance;
        }

        /**
         * 使用同步块而不是同步方法的原因：同步方法涉及的范围太广
         * 由于是通过静态方法获取相应的对象信息，因此，监视器对象选用的是类的模子，即类对象，class
         */
        synchronized (DCLSingletonPattern.class) {
            /**
             * 对象初始化目前的理解是有三个步骤，1：开辟空间；2.初始化对象信息；3.返回对象的地址给引用变量；
             * 在多线程的情况下，假设还未有对象生成，此时A线程进入方法，判断对象为空，于是进行对象初始化的操作，而这个过程可能耗时很长，导致B
             * 线程进入的时候发现对象仍为空，因此，也进行对象初始化的操作，这个时候就生成了多个对象
             */
            if (null == instance) {
                //return new DCLSingletonPattern(); // 有问题的代码，不能这样写
                instance = new DCLSingletonPattern();
            }
        }

        return instance;
    }

    /**
     * 缺少同步操作，获取的不是同一个对象
     * @param delayTime
     * @return
     */
    public static DCLSingletonPattern getWrongInstance(long delayTime) {
        if (instance == null) {
            try {
                Thread.sleep(delayTime);    // 模拟初始化对象的延时操作，也可以加入到上面到正确获取对象的方法中
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            instance = new DCLSingletonPattern();
        }

        return instance;
    }

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(DCLSingletonPattern.getInstance());
        }).start();

        System.out.println(DCLSingletonPattern.getInstance());
    }
}
