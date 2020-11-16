package learning.util;

import javax.servlet.http.Cookie;

public class CookieUtil {

    public static String getCookie(Cookie[] cookies, String name){
        if(cookies == null)
            return null;
        for (Cookie cookie : cookies){
            if(!name.equals(cookie.getName())){
                continue;
            }
            return cookie.getValue();
        }
        return null;
    }

    public static Cookie setCookie(String name, String value, String domain){
        Cookie cookie = new Cookie(name, value);
        cookie.setDomain(domain);
        return cookie;
    }

}
