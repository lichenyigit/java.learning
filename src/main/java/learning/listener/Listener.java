package learning.listener;

import learning.retry.RetryRunable;
import learning.retry.ScheduleRetry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author lichenyi
 * @date 2017-9-10-0010.
 */
@WebListener
public class Listener implements ServletContextListener {
    private final Logger logger = LogManager.getLogger(Listener.class);
    public static ScheduledExecutorService schedulerService = Executors.newScheduledThreadPool(2);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        RetryRunable runable = new RetryRunable("熊本熊", 5);
        ScheduleRetry.execute(runable, 5*1000);

        RetryRunable runable2 = new RetryRunable("路飞", 4);
        ScheduleRetry.execute(runable2, 2*1000);

        RetryRunable runable3 = new RetryRunable("拓海", 8);
        ScheduleRetry.execute(runable3, 2*1000);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        schedulerService.shutdown();
    }

}
