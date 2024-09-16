package by.it_academy.jd2.dto;

public class UserForMassageEntity {
    private Long id;
    private String login;

    public UserForMassageEntity(Long id, String login) {
        this.id = id;
        this.login = login;
    }

    public UserForMassageEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public String toString() {
        return "UserForMassageEntity{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }
}
