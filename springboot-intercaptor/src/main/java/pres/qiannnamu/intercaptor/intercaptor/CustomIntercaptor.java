package pres.qiannnamu.intercaptor.intercaptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/15
 * @time 12:46
 */
public class CustomIntercaptor implements HandlerInterceptor {

    /**
     * 执行之前拦截
     * // 还要web mvc注册。。
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("=========================================");
        Object user = request.getSession().getAttribute("user");
        if (user == null){
            request.setAttribute("msg" , " error");
            request.getRequestDispatcher("/error.html").forward(request,response);
            return false;
        }else {
            return true;
        }
    }

}
