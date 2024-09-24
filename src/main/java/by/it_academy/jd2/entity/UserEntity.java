package by.it_academy.jd2.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class UserEntity {
    private UUID id;
    private final String login;
    private final String password;
    private final String name;
    private final LocalDate birthDate;
    private final LocalDateTime registrationAt;
    private final LocalDateTime updateAt;
    private final String role;

    private UserEntity(UUID id, String login, String password, String name, LocalDate birthDate,
                       LocalDateTime registrationAt, LocalDateTime updateAt, String role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
        this.registrationAt = registrationAt;
        this.updateAt = updateAt;
        this.role = role;
    }

    public static UserEntityBuilder builder() {
        return new UserEntityBuilder();
    }

    public void setId(UUID id) {
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

    public LocalDateTime getRegistrationAt() {
        return registrationAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public String getRole() {
        return role;
    }

    public UUID getId() {
        return id;
    }

    public static class UserEntityBuilder {
        private UUID id;
        private String login;
        private String password;
        private String name;
        private LocalDate birthDate;
        private LocalDateTime registrationAt;
        private LocalDateTime updateAt;
        private String role;

        private UserEntityBuilder() {
        }

        public UserEntityBuilder setId(UUID id) {
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

        public UserEntityBuilder setRegistrationAt(LocalDateTime registrationAt) {
            this.registrationAt = registrationAt;
            return this;
        }

        public UserEntityBuilder setUpdateAt(LocalDateTime updateAt) {
            this.updateAt = updateAt;
            return this;
        }

        public UserEntityBuilder setRole(String role) {
            this.role = role;
            return this;
        }

        public UserEntity build() {
            return new UserEntity(id, login, password, name, birthDate, registrationAt, updateAt,role);
        }
    }
}
