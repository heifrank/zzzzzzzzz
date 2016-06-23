package com.servlet;

import org.apache.http.HttpResponse;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by heifrank on 16/5/12.
 */
public class BlockServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int count = 5;
        while(count > 0){
            count--;
            System.out.println("Wait count is " + count);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        resp.getWriter().println("FINISHED!");
    }
}
