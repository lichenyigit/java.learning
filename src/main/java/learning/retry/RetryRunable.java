package learning.retry;

import learning.listener.Listener;
import learning.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

/** 重发机制Runable
 * @author lichenyi
 * @date 2017-9-10-0010.
 */
public class RetryRunable implements Runnable {
    private final Logger logger = LogManager.getLogger(RetryRunable.class);

    private int count = 0;

    private int getCount(){ return count;}
    private void addCount(){ count++;}

    private String name;
    public RetryRunable(String name){
        this.name = name;
        logger.trace("{}-{} are ready execute.", name, getCount());
    }
    public RetryRunable(String name, int count){
        this.name = name;
        this.count = count;
        logger.trace("{}-{} are ready execute.", name, getCount());
    }

    @Override
    public void run() {
        if(getCount() < Constants.retryFrequency.length){
            logger.trace("{}-{} is running.", name, getCount());
            addCount();
            Listener.schedulerService.schedule(this, Constants.retryFrequency[getCount()], TimeUnit.MILLISECONDS);
        }
    }
}
