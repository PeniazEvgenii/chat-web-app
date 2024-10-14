package by.it_academy.jd2.dao.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    private UUID id;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "registration_at", nullable = false)
    private LocalDateTime createAt;

    @Column(nullable = false)
    private LocalDateTime updateAt;

    @Column
    private String role;

    private UserEntity(UUID id, String login, String password, String name, LocalDate birthDate,
                       LocalDateTime createAt, LocalDateTime updateAt, String role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.role = role;
    }

    public static UserEntityBuilder builder() {
        return new UserEntityBuilder();
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public UUID getId() {
        return id;
    }


    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", role='" + role + '\'' +
                '}';
    }

    public static class UserEntityBuilder {
        private UUID id;
        private String login;
        private String password;
        private String name;
        private LocalDate birthDate;
        private LocalDateTime createAt;
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

        public UserEntityBuilder setCreateAt(LocalDateTime createAt) {
            this.createAt = createAt;
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
            return new UserEntity(id, login, password, name, birthDate, createAt, updateAt,role);
        }
    }
}
