package by.it_academy.jd2.dto;


import java.util.UUID;

public class MessageCreateDto {
    private final String username;
    private final String body;
    private final UserReadDto userFrom;

    private MessageCreateDto(String username, String body, UserReadDto userFrom) {
        this.username = username;
        this.body = body;
        this.userFrom = userFrom;
    }

    public String getUsername() {
        return username;
    }

    public String getBody() {
        return body;
    }

    public UserReadDto getUserFrom() {
        return userFrom;
    }

    public static MessageCreateDtoBuilder builder() {
        return new MessageCreateDtoBuilder();
    }

    @Override
    public String toString() {
        return "MessageCreateDto{" +
                "username='" + username + '\'' +
                ", body='" + body + '\'' +
                ", userFrom=" + userFrom +
                '}';
    }

    public static class MessageCreateDtoBuilder {
        private String username;
        private String body;
        private UserReadDto userFrom;

        private MessageCreateDtoBuilder() {}

        public MessageCreateDtoBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public MessageCreateDtoBuilder setBody(String body) {
            this.body = body;
            return this;
        }

        public MessageCreateDtoBuilder setUserFrom(UserReadDto userFrom) {
            this.userFrom = userFrom;
            return this;
        }

        public MessageCreateDto build() {
            return new MessageCreateDto(username, body, userFrom);
        }
    }
}
