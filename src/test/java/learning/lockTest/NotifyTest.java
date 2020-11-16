package learning.lockTest;

import java.util.concurrent.TimeUnit;

public class NotifyTest {

    public static void main(String args[]) {
        Object co = new Object();
        System.out.println(co);

        for (int i = 0; i < 5; i++) {
            final String thredName = "测试线程"+i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("测试线程"+ thredName + " is waiting.");
                    try {
                        synchronized (co) {
                            co.wait();
                        }
                        System.out.println("测试线程"+ thredName + " has been notified.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }, thredName);
            thread.start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("-----Main Thread notify-----");
            synchronized (co) {
                //co.notifyAll();
                co.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /********************************结论******************************/
    /*
    运行环境jdk8，结论：
    notify唤醒一个等待的线程；notifyAll唤醒所有等待的线程。
    */
}
