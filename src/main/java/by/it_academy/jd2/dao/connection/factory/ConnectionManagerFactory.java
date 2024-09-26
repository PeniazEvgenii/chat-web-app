package by.it_academy.jd2.dao.connection.factory;

import by.it_academy.jd2.config.properties.ConnectionProperty;
import by.it_academy.jd2.dao.connection.ConnectionManager;
import by.it_academy.jd2.dao.connection.api.IConnectionManager;
import by.it_academy.jd2.util.PropertiesUtil;

/**
 * Для получения доступа к базе данных используются параметры (URL, username, password)
 * из файла /resources/application.properties. Для изменения параметров воспользуйтесь
 * переменными среды DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD.
 */
public class ConnectionManagerFactory {
    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";

    private static final String ENV_CONFIG_URL = System.getenv("DATABASE_URL");
    private static final String ENV_CONFIG_USERNAME = System.getenv("DATABASE_USERNAME");
    private static final String ENV_CONFIG_PASSWORD = System.getenv("DATABASE_PASSWORD");

    private static final IConnectionManager instance = new ConnectionManager(getConnectionProperty());

    private ConnectionManagerFactory() {
    }

    private static ConnectionProperty getConnectionProperty() {
        if (isValidEnvVariables()) {
            return new ConnectionProperty(
                    ENV_CONFIG_URL,
                    ENV_CONFIG_USERNAME,
                    ENV_CONFIG_PASSWORD);
        } else {
            return new ConnectionProperty(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USERNAME_KEY),
                    PropertiesUtil.get(PASSWORD_KEY));
        }
    }

    private static boolean isValidEnvVariables() {
        return ENV_CONFIG_URL != null && ENV_CONFIG_USERNAME != null && ENV_CONFIG_PASSWORD != null;
    }

    public static IConnectionManager getInstance() {
        return instance;
    }
}
