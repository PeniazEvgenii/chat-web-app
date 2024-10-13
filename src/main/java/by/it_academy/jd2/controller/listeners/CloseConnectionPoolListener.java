package by.it_academy.jd2.controller.listeners;

import by.it_academy.jd2.dao.connection.api.IConnectionManager;
import by.it_academy.jd2.dao.connection.factory.ConnectionManagerFactory;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class CloseConnectionPoolListener implements ServletContextListener {

    private final IConnectionManager connectionManager = ConnectionManagerFactory.getInstance();

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        connectionManager.close();
    }
}
