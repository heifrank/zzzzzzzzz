package com.servlet;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.io.FileReader;
import java.util.Properties;

/**
 * Created by heifrank on 16/5/12.
 */
public class TestServletBlocked {
    public static void main(String[] args) throws Exception {
        Server server = new Server(7070);

        ServletContextHandler rootContext = new ServletContextHandler();
        rootContext.setContextPath("/");
        rootContext.addServlet(new ServletHolder(new BlockServlet()), "/blocks/*");

        ContextHandlerCollection handlerCollection = new ContextHandlerCollection();
        handlerCollection.setHandlers(new Handler[]{rootContext});
        server.setHandler(handlerCollection);

        server.start();
        server.join();
    }
}
