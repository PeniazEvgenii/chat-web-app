package by.it_academy.jd2.config.properties;

public class ConnectionProperty {
    private final String url;
    private final String username;
    private final String password;
    private final String driver;
    private final int minPoolSize;
    private final int acquireIncrement;
    private final int maxPoolSize;

    private ConnectionProperty(String url, String username, String password, String driver, int minPoolSize, int acquireIncrement, int maxPoolSize) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.driver = driver;
        this.minPoolSize = minPoolSize;
        this.acquireIncrement = acquireIncrement;
        this.maxPoolSize = maxPoolSize;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDriver() {
        return driver;
    }

    public int getMinPoolSize() {
        return minPoolSize;
    }

    public int getAcquireIncrement() {
        return acquireIncrement;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public static ConnectionPropertyBuilder builder() {
        return new ConnectionPropertyBuilder();
    }

    public static class ConnectionPropertyBuilder {
        private String url;
        private String username;
        private String password;
        private String driver;
        private int minPoolSize;
        private int acquireIncrement;
        private int maxPoolSize;

        private ConnectionPropertyBuilder() {}

        public ConnectionPropertyBuilder setUrl(String url) {
            this.url = url;
            return this;
        }

        public ConnectionPropertyBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public ConnectionPropertyBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public ConnectionPropertyBuilder setDriver(String driver) {
            this.driver = driver;
            return this;
        }

        public ConnectionPropertyBuilder setMinPoolSize(int minPoolSize) {
            this.minPoolSize = minPoolSize;
            return this;
        }

        public ConnectionPropertyBuilder setAcquireIncrement(int acquireIncrement) {
            this.acquireIncrement = acquireIncrement;
            return this;
        }

        public ConnectionPropertyBuilder setMaxPoolSize(int maxPoolSize) {
            this.maxPoolSize = maxPoolSize;
            return this;
        }

        public ConnectionProperty build() {
            return new ConnectionProperty(url, username, password, driver,
                    minPoolSize, acquireIncrement, maxPoolSize);
        }
    }
}
