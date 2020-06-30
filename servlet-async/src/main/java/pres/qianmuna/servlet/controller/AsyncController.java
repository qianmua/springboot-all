package pres.qianmuna.servlet.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/30  13:06
 * @description :
 */
@WebServlet( asyncSupported = true , name = "async" , urlPatterns = "/async")
public class AsyncController extends HttpServlet {

    private static final long serialVersionUID = 2555348335777708250L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.isAsyncSupported()){
            AsyncContext context = req.startAsync();
            context.addListener(new AsyncListener() {
                @Override
                public void onComplete(AsyncEvent asyncEvent) throws IOException {
                    System.out.println("ok");
                }

                @Override
                public void onTimeout(AsyncEvent asyncEvent) throws IOException {
                    System.out.println("time out");
                }

                @Override
                public void onError(AsyncEvent asyncEvent) throws IOException {
                    System.out.println("err");
                }

                @Override
                public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
                    System.out.println("start");
                }
            });
            ServletResponse response = context.getResponse();
            response.setContentType("text/plain;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.println("hello world");
            writer.flush();
            writer.close();
        }
    }
}
