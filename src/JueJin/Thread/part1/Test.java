package JueJin.Thread.part1;

public class Test {
    public static void main(String[] args) {
        Runnable runnable = new MyRunnable();
        new Thread(runnable).start();
        System.out.println("运行结束!");
    }
}
