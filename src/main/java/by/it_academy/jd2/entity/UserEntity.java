package by.it_academy.jd2.entity;

import java.time.LocalDate;

public class UserEntity {
    private Long id;
    private final String login;
    private final String password;
    private final String name;
    private final LocalDate birthDate;
    private final LocalDate registrationDate;
    private final String role;

    private UserEntity(Long id, String login, String password, String name, LocalDate birthDate, LocalDate registrationDate, String role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
        this.registrationDate = registrationDate;
        this.role = role;
    }

    public static UserEntityBuilder builder() {
        return new UserEntityBuilder();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public String getRole() {
        return role;
    }

    public Long getId() {
        return id;
    }

    public static class UserEntityBuilder {
        private Long id;
        private String login;
        private String password;
        private String name;
        private LocalDate birthDate;
        private LocalDate registrationDate;
        private String role;

        private UserEntityBuilder() {
        }

        public UserEntityBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public UserEntityBuilder setLogin(String login) {
            this.login = login;
            return this;
        }

        public UserEntityBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserEntityBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserEntityBuilder setBirthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public UserEntityBuilder setRegistrationDate(LocalDate registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }

        public UserEntityBuilder setRole(String role) {
            this.role = role;
            return this;
        }

        public UserEntity build() {
            return new UserEntity(id, login, password, name, birthDate, registrationDate, role);
        }
    }
}
