package learning.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class SessionUtil {
    private static Logger logger = Logger.getLogger(SessionUtil.class);

    //session 属性map
    private static JSONObject sessionJSONObject = null;

    public static JSONObject init(){
        if(sessionJSONObject == null)
            sessionJSONObject = new JSONObject();
        return sessionJSONObject;
    }

    /**
     * 将sessionJSONObject中的key，value写入session对象
     * @param request
     */
    public static void setSessionAttrs(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        String sessionId = session.getId();

        //获取sessionJSONObject 中的内容
        JSONObject jsonObjectSession = init().getJSONObject(sessionId);
        if(jsonObjectSession == null){
            return;
        }

        for (Map.Entry entry : jsonObjectSession.entrySet()){
            session.setAttribute(entry.getKey().toString(), entry.getValue());
        }
    }

    /**
     * 操作session值,用此方法
     * @param request
     * @param key
     * @param value
     */
    public static void setSessionAttr(HttpServletRequest request, String key, Object value){
        logger.info(request.getRequestURI());

        HttpSession session = request.getSession(false);
        String sessionId = session.getId();

        //获取sessionJSONObject 中的内容
        JSONObject jsonSession = init().getJSONObject(sessionId);
        if(jsonSession == null){
            jsonSession = new JSONObject();
        }

        jsonSession.put(key, value);
        init().put(sessionId, jsonSession);

        //将key，value写入session
        for (Map.Entry entry : jsonSession.entrySet()){
            session.setAttribute(entry.getKey().toString(), entry.getValue());
        }
        logger.info(init().toJSONString());
    }

}
