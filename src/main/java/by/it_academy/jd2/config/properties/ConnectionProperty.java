package by.it_academy.jd2.config.properties;

public class ConnectionProperty {
    private final String url;
    private final String username;
    private final String password;

    public ConnectionProperty(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
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
}
