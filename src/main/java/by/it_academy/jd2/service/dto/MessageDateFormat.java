package by.it_academy.jd2.service.dto;

import java.util.UUID;

public class MessageDateFormat {
    private final UUID id;
    private final String createAt;
    private final UserReadDto userFrom;
    private final UserReadDto userTo;
    private final String body;
    private final String updateAt;

    public MessageDateFormat(UUID id, String createAt, UserReadDto userFrom, UserReadDto userTo, String body, String updateAt) {
        this.createAt = createAt;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.body = body;
        this.updateAt = updateAt;
        this.id = id;
    }

    public static MessageDateFormatBuilder builder() {
        return new MessageDateFormatBuilder();
    }

    public UUID getId() {
        return id;
    }

    public String getCreateAt() {
        return createAt;
    }

    public UserReadDto getUserFrom() {
        return userFrom;
    }

    public UserReadDto getUserTo() {
        return userTo;
    }

    public String getBody() {
        return body;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public static class MessageDateFormatBuilder {
        private UUID id;
        private String createAt;
        private UserReadDto userFrom;
        private UserReadDto userTo;
        private String body;
        private String updateAt;

        private MessageDateFormatBuilder() {
        }

        public MessageDateFormatBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public MessageDateFormatBuilder setCreateAt(String createAt) {
            this.createAt = createAt;
            return this;
        }

        public MessageDateFormatBuilder setUserFrom(UserReadDto userFrom) {
            this.userFrom = userFrom;
            return this;
        }

        public MessageDateFormatBuilder setUserTo(UserReadDto userTo) {
            this.userTo = userTo;
            return this;
        }

        public MessageDateFormatBuilder setBody(String body) {
            this.body = body;
            return this;
        }

        public MessageDateFormatBuilder setUpdateAt(String updateAt) {
            this.updateAt = updateAt;
            return this;
        }

        public MessageDateFormat build() {
            return new MessageDateFormat(id, createAt, userFrom, userTo, body, updateAt);
        }
    }
}