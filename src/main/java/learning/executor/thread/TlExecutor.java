package learning.executor.thread;

import java.util.concurrent.Executor;

/**
 *
 */
public class TlExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
         new Thread(command).start();
    }

    public static void main(String[] args) {
        new TlExecutor().execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("悟空是只猴子");

            }
        });
    }
}
