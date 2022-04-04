package pri.tangjiang.graduationdesign.configuration.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import pri.tangjiang.graduationdesign.util.JwtManage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("OPTINS".equals(request.getMethod().toUpperCase())) {
            return true;
        }
        String token = request.getHeader("token");
        try {
            JwtManage.verifyToken(token);
        }catch (Exception e){
            response.setStatus(500);
            PrintWriter writer = response.getWriter();
            writer.print("{\"status\":\"500\",\n\"eorrMsg\":\"login invalid!\"\n}");
            writer.flush();
            writer.close();
            e.printStackTrace();
            return false;
        }
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
