package by.it_academy.jd2.dao.connection;

import by.it_academy.jd2.config.properties.ConnectionProperty;
import by.it_academy.jd2.dao.connection.api.IConnectionManager;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager implements IConnectionManager {
    private static DataSource dataSource;
    private static final ComboPooledDataSource cpds = new ComboPooledDataSource();;

    public ConnectionManager(ConnectionProperty connectionProperty) {
        configureDataSource(connectionProperty);
    }

    static {
        loadDriver();
    }

    private void configureDataSource(ConnectionProperty connectionProperty) {
        cpds.setJdbcUrl(connectionProperty.getUrl());
        cpds.setUser(connectionProperty.getUsername());
        cpds.setPassword(connectionProperty.getPassword());
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        dataSource = cpds;
    }

    private static void loadDriver() {
        try {
            cpds.setDriverClass("org.postgresql.Driver");
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
