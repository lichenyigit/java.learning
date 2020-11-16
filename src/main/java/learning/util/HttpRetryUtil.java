package learning.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
*
* @param:
* @return
* @author lichenyi
* @date 2017/11/29 17:11
*/
public abstract class HttpRetryUtil<T> {
    Logger logger = LogManager.getLogger(HttpRetryUtil.class);

    private T result;

    public HttpRetryUtil start(){
        int count = 1;
        retry(count);
        return this;
    }

    private void retry(int count){
        count++;
        try {
            this.setResult(execute());
        }catch (Exception e){
            logger.info("进行第【%s】次重试. url-->%s\r\n"+"parameter-->%s", count, getUrl(), getParameter());
            e.printStackTrace();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            if(count < 5)
                retry(count);
            else
                return;
        }
    }

    protected abstract T execute();

    protected abstract String getUrl();

    protected abstract String getParameter();

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
