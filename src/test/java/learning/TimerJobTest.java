package learning;

import learning.retry.TimerJob;
import org.junit.Test;

import java.util.Timer;

/**
 * @author lichenyi
 * @date 2017-9-10-0010.
 */
public class TimerJobTest {

    Timer timer = new Timer();

    @Test
    public void test(){
        for(int i = 0;i < 100; i++){
            timer.schedule(new TimerJob("小明"+i), (5+i)*1000);
        }
        //timer.cancel();
        //timer.purge();
    }

}
