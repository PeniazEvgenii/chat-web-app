package by.it_academy.jd2.service.dto;


import java.time.OffsetDateTime;
import java.util.UUID;

public class MessageReadDto {
    private final UUID id;
    private final OffsetDateTime createAt;
    private final UserReadDto userFrom;
    private final UserReadDto userTo;
    private final String body;
    private final OffsetDateTime updateAt;

    public MessageReadDto(UUID id, OffsetDateTime createAt, UserReadDto userFrom, UserReadDto userTo, String body, OffsetDateTime updateAt) {
        this.createAt = createAt;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.body = body;
        this.updateAt = updateAt;
        this.id = id;
    }

    public static MessageReadDtoBuilder builder() {
        return new MessageReadDtoBuilder();
    }

    public UUID getId() {
        return id;
    }

    public OffsetDateTime getCreateAt() {
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

    public OffsetDateTime getUpdateAt() {
        return updateAt;
    }

    public static class MessageReadDtoBuilder {
        private UUID id;
        private OffsetDateTime createAt;
        private UserReadDto userFrom;
        private UserReadDto userTo;
        private String body;
        private OffsetDateTime updateAt;

        private MessageReadDtoBuilder() {
        }

        public MessageReadDtoBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public MessageReadDtoBuilder setCreateAt(OffsetDateTime createAt) {
            this.createAt = createAt;
            return this;
        }

        public MessageReadDtoBuilder setUserFrom(UserReadDto userFrom) {
            this.userFrom = userFrom;
            return this;
        }

        public MessageReadDtoBuilder setUserTo(UserReadDto userTo) {
            this.userTo = userTo;
            return this;
        }

        public MessageReadDtoBuilder setBody(String body) {
            this.body = body;
            return this;
        }

        public MessageReadDtoBuilder setUpdateAt(OffsetDateTime updateAt) {
            this.updateAt = updateAt;
            return this;
        }

        public MessageReadDto build() {
            return new MessageReadDto(id, createAt, userFrom, userTo, body, updateAt);
        }
    }
}
