package Other.Thread;

/* @program: JavaStudy
 * @description:
 * @chineseDescription: 三线程打印ABC，A线程打印10次A，B线程打印10次B，C线程打印10次C，要求线程同时运行，交替打印10次ABC
 * @author: LiuDongMan
 * @createdDate: 2019-08-04 21:25
 */
public class LoopPrint implements Runnable{
    private String name;
    private Object prev;
    private Object self;

    public LoopPrint(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    @Override
    public void run() {
        int count = 10;

        while (count > 0) {
            synchronized (prev) {
                synchronized (self) {
                    System.out.print(name);

                    count--;

                    self.notify();
                }

                try {
                    prev.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();

        LoopPrint lp01 = new LoopPrint("A", c, a);
        LoopPrint lp02 = new LoopPrint("B", a, b);
        LoopPrint lp03 = new LoopPrint("C", b, c);

        new Thread(lp01).start();

        Thread.sleep(100);

        new Thread(lp02).start();

        Thread.sleep(100);

        new Thread(lp03).start();

        Thread.sleep(100);
    }
}
