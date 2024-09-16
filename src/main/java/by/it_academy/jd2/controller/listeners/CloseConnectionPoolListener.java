package by.it_academy.jd2.controller.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class CloseConnectionPoolListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
