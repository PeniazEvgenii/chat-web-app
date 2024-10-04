package by.it_academy.jd2.dao.connection;

import by.it_academy.jd2.config.properties.ConnectionProperty;
import by.it_academy.jd2.dao.connection.api.IConnectionManager;
import by.it_academy.jd2.util.PropertiesUtil;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager implements IConnectionManager {

    private final ComboPooledDataSource cpds;
    private DataSource dataSource;

    public ConnectionManager(ConnectionProperty connectionProperty) {
        cpds = new ComboPooledDataSource();
        loadDriver(connectionProperty.getDriver());
        configureDataSource(connectionProperty);
    }

    private void configureDataSource(ConnectionProperty connectionProperty) {
        cpds.setJdbcUrl(connectionProperty.getUrl());
        cpds.setUser(connectionProperty.getUsername());
        cpds.setPassword(connectionProperty.getPassword());
        cpds.setMinPoolSize(connectionProperty.getMinPoolSize());
        cpds.setAcquireIncrement(connectionProperty.getAcquireIncrement());
        cpds.setMaxPoolSize(connectionProperty.getMaxPoolSize());
        dataSource = cpds;
    }

    private void loadDriver(String driver) {
        try {
           // cpds.setDriverClass("org.postgresql.Driver");
            cpds.setDriverClass(driver);
        } catch (PropertyVetoException e) {
            throw new RuntimeException("Ошибка при загрузке драйвера базы данных",e);
        }
    }

    @Override
    public Connection open() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при открытии соединения с базой данных", e);
        }
    }

    @Override
    public void close() {
        if (cpds != null) {
            cpds.close();
        }
    }

}
