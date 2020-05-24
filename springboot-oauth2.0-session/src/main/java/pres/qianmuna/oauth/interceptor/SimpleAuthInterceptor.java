package pres.qianmuna.oauth.interceptor;

import com.sun.net.httpserver.HttpContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import pres.qianmuna.oauth.model.UserDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/24
 * @time 22:44
 */
@Component
public class SimpleAuthInterceptor implements HandlerInterceptor {

    /**
     * 校验
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object user = request.getSession().getAttribute("USER");

        if (user == null){
            viewResponse(response, "请登录");
        }

        UserDto userDto = (UserDto) user;

        String requestURI = request.getRequestURI();
        if (Objects.requireNonNull(userDto).getAuth().contains("1") && requestURI.contains("/r/1")) {
            return true;
        }

        viewResponse( response , "403");

        return false;
    }

    private void viewResponse(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();

        writer.println(msg);

        writer.flush();
        writer.close();

        response.resetBuffer();
    }
}
