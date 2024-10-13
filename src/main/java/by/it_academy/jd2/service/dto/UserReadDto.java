package by.it_academy.jd2.dto;

import by.it_academy.jd2.entity.ERole;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class UserReadDto {
    private final UUID id;
    private final String login;
    private final String password;
    private final String name;
    private final LocalDate birthDate;
    private final LocalDateTime createAt;
    private final LocalDateTime updateAt;
    private final ERole role;

    private UserReadDto(UUID id, String login, String password, String name, LocalDate birthDate, LocalDateTime createAt, LocalDateTime updateAt, ERole role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.role = role;
    }

    public static UserLoginDtoBuilder builder() {
        return new UserLoginDtoBuilder();
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

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public ERole getRole() {
        return role;
    }

    public UUID getId() {
        return id;
    }

    public static class UserLoginDtoBuilder {
        private UUID id;
        private String login;
        private String password;
        private String name;
        private LocalDate birthDate;
        private LocalDateTime createAt;
        private LocalDateTime updateAt;
        private ERole role;

        private UserLoginDtoBuilder() {
        }

        public UserLoginDtoBuilder setId(UUID id) {
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

        public UserLoginDtoBuilder setCreateAt(LocalDateTime createAt) {
            this.createAt = createAt;
            return this;
        }

        public UserLoginDtoBuilder setUpdateAt(LocalDateTime updateAt) {
            this.updateAt = updateAt;
            return this;
        }

        public UserLoginDtoBuilder setRole(ERole role) {
            this.role = role;
            return this;
        }

        public UserReadDto build() {
            return new UserReadDto(id, login, password, name, birthDate, createAt, updateAt, role);
        }
    }
}
