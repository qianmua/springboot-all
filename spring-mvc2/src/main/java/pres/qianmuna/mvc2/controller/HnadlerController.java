package pres.qianmuna.mvc2.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/17  22:10
 * @description :
 */
@Component("/test1")
public class HnadlerController implements HttpRequestHandler {


    //@since 2.0
    // spring 2.0 的 方法了
    // 古老~~


    @Override
    public void handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        System.out.println("666");
    }
}
