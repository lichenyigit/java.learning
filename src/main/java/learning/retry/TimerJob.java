package learning.retry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.TimerTask;

/**Timer的重发机制
 * @author Lichenyi
 * @date 2017-9-8 0008
 */
public class TimerJob extends TimerTask {
    private  static final Logger logger = LogManager.getLogger(TimerJob.class);

    private String name;

    public TimerJob(String name){
        this.name = name;
        logger.trace("{} are ready for execution.", name);
    }

    @Override
    public void run() {
        logger.trace("{} is running.", name);
    }
}
