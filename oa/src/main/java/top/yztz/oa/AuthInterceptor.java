package top.yztz.oa;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import top.yztz.oa.utils.AuthUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("X-Token");
        if(token != null) return token;
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(Cookie cookie : request.getCookies()) {
                if("X-Token".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if("OPTIONS".equalsIgnoreCase(request.getMethod()))
            return true;

        String token = getToken(request);
        System.out.println("token is " + token);
        if (token == null)
            return false;
        Long basic = AuthUtils.getAppUID(token);
        if (basic == null)
            return false;
        request.setAttribute("userID", basic);
        return true;
    }
}
