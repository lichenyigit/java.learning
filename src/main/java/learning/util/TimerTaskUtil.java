package learning.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lichenyi
 * @date 2017-12-6-0006.
 */
public class TimerTaskUtil {

    public static void execute(int count, Callable<Integer> callable){
        ExecutorService executorService= Executors.newFixedThreadPool(10);
        Collection<Callable<Integer>> tasks = new ArrayList<Callable<Integer>>();
        for(int i = 0;i < count;i++){
            tasks.add(callable);
        }
        try {
            executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //executorService.shutdown();
    }

}
