package by.it_academy.jd2.dto;

import by.it_academy.jd2.entity.ERole;

import java.time.LocalDate;

public class UserLoginDto {
    private Long id;
    private final String login;
    private final String password;
    private final String name;
    private final LocalDate birthDate;
    private final LocalDate registrationDate;
    private final ERole role;

    private UserLoginDto(Long id, String login, String password, String name, LocalDate birthDate, LocalDate registrationDate, ERole role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
        this.registrationDate = registrationDate;
        this.role = role;
    }

    public static UserLoginDtoBuilder builder() {
        return new UserLoginDtoBuilder();
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

    public ERole getRole() {
        return role;
    }

    public Long getId() {
        return id;
    }

    public static class UserLoginDtoBuilder {
        private Long id;
        private String login;
        private String password;
        private String name;
        private LocalDate birthDate;
        private LocalDate registrationDate;
        private ERole role;

        private UserLoginDtoBuilder() {
        }

        public UserLoginDtoBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public UserLoginDtoBuilder setLogin(String login) {
            this.login = login;
            return this;
        }

        public UserLoginDtoBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserLoginDtoBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserLoginDtoBuilder setBirthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public UserLoginDtoBuilder setRegistrationDate(LocalDate registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }

        public UserLoginDtoBuilder setRole(ERole role) {
            this.role = role;
            return this;
        }

        public UserLoginDto build() {
            return new UserLoginDto(id, login, password, name, birthDate, registrationDate, role);
        }
    }
}
