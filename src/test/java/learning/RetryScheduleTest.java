package learning;

import learning.retry.RetryRunable;
import learning.retry.ScheduleRetry;
import org.junit.Test;

/**
 * @author lichenyi
 * @date 2017-9-10-0010.
 */
public class RetryScheduleTest {

    @Test
    public void ScheduleRetryTest(){
        RetryRunable runable = new RetryRunable("你好");
        ScheduleRetry.execute(runable, 5*1000);
    }

}
