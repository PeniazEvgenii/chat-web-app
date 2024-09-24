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
    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";

    private static final String ENV_CONFIG_URL = System.getenv("DATABASE_URL");
    private static final String ENV_CONFIG_USERNAME = System.getenv("DATABASE_USERNAME");
    private static final String ENV_CONFIG_PASSWORD = System.getenv("DATABASE_PASSWORD");

    private static DataSource dataSource;

    public ConnectionManager() {

    }

    static {
        load();
    }

    /**
     * Для получения доступа к базе данных используются параметры (URL, username, password)
     * из файла /resources/application.properties. Для изменения параметров воспользуйтесь
     * переменными среды DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD.
     */
    private static void load() {
        ComboPooledDataSource cpds = new ComboPooledDataSource();

        try {
            cpds.setDriverClass("org.postgresql.Driver");
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }

        cpds.setJdbcUrl(getUrl());
        cpds.setUser(getUsername());
        cpds.setPassword(getPassword());

        dataSource = cpds;
    }

    @Override
    public Connection open() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка подключения", e);
        }
    }

    private static String getUrl() {
        return isValidEnvVariables() ? ENV_CONFIG_URL : PropertiesUtil.get(URL_KEY);
    }

    private static String getUsername() {
        return isValidEnvVariables() ? ENV_CONFIG_USERNAME : PropertiesUtil.get(USERNAME_KEY);
    }

    private static String getPassword() {
        return isValidEnvVariables() ? ENV_CONFIG_PASSWORD : PropertiesUtil.get(PASSWORD_KEY);
    }

    private static boolean isValidEnvVariables() {
        return ENV_CONFIG_URL != null && ENV_CONFIG_USERNAME != null && ENV_CONFIG_PASSWORD != null;
    }
}
