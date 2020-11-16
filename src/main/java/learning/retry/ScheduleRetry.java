package learning.retry;

import learning.listener.Listener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

/**重发机制Schedule
 * @author lichenyi
 * @date 2017-9-10-0010.
 */
public class ScheduleRetry {
    private final static Logger logger = LogManager.getLogger(ScheduleRetry.class);

    public static void execute(Runnable runnable) {
        execute(runnable, 0L);
    }

    public static void execute(Runnable runnable, long delay) {
        Listener.schedulerService.schedule(runnable, delay, TimeUnit.MILLISECONDS);
    }

}
