package by.it_academy.jd2.dto;

import by.it_academy.jd2.entity.ERole;

import java.time.LocalDate;

public class UserCreateDto {
    private final String login;
    private final String password;
    private final String name;
    private final String birthDate;
    private final LocalDate registrationDate;
    private final ERole role;

    private UserCreateDto(String login, String password, String name, String birthDate, LocalDate registrationDate, ERole role) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
        this.registrationDate = registrationDate;
        this.role = role;
    }

    public static UserDtoBuilder builder() {
        return new UserDtoBuilder();
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

    public String getBirthDate() {
        return birthDate;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public ERole getRole() {
        return role;
    }

    public static class UserDtoBuilder {
        private String login;
        private String password;
        private String name;
        private String birthDate;
        private LocalDate registrationDate;
        private ERole role;

        private UserDtoBuilder() {
        }

        public UserDtoBuilder setLogin(String login) {
            this.login = login;
            return this;
        }

        public UserDtoBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserDtoBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserDtoBuilder setBirthDate(String birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public UserDtoBuilder setRegisterDate(LocalDate registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }

        public UserDtoBuilder setRole(ERole role) {
            this.role = role;
            return this;
        }

        public UserCreateDto build() {
            return new UserCreateDto(login, password, name, birthDate, registrationDate, role);
        }
    }

    @Override
    public String toString() {
        return "UserCreateDto{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", registrationDate=" + registrationDate +
                ", role=" + role +
                '}';
    }
}
