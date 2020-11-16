package learning.session.listener;

import learning.util.SessionUtil;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListerner implements HttpSessionListener {
    private Logger logger = Logger.getLogger(SessionListerner.class);

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        String sessoinId = httpSessionEvent.getSession().getId();
        SessionUtil.init().put(sessoinId, "");
        logger.info("session创建---> "+sessoinId);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        String sessoinId = httpSessionEvent.getSession().getId();
        SessionUtil.init().remove(sessoinId);
        logger.info("session销毁---> "+sessoinId);
    }
}
