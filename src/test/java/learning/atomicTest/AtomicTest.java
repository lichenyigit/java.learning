package learning.atomicTest;

import learning.util.TimerTaskUtil;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lichenyi
 * @date 2017-12-6-0006.
 */
public class AtomicTest {


    AtomicInteger atomicInteger = new AtomicInteger();

    @Test
    public void test1(){
        TimerTaskUtil.execute(100, new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                atomicInteger.addAndGet(1);
                atomicInteger.addAndGet(2);
                atomicInteger.addAndGet(3);
                atomicInteger.addAndGet(4);
                return null;
            }
        });
        System.out.println(atomicInteger.get());

    }


}
