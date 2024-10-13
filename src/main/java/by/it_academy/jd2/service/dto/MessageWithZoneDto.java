package by.it_academy.jd2.service.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class MessageWithZoneDto {
    private final UUID id;
    private final LocalDateTime createAt;
    private final UserReadDto userFrom;
    private final UserReadDto userTo;
    private final String body;
    private final LocalDateTime updateAt;

    public MessageWithZoneDto(UUID id, LocalDateTime createAt, UserReadDto userFrom, UserReadDto userTo, String body, LocalDateTime updateAt) {
        this.createAt = createAt;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.body = body;
        this.updateAt = updateAt;
        this.id = id;
    }

    public static MessageWithZoneDtoBuilder builder() {
        return new MessageWithZoneDtoBuilder();
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getCreateAt() {
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

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public static class MessageWithZoneDtoBuilder {
        private UUID id;
        private LocalDateTime createAt;
        private UserReadDto userFrom;
        private UserReadDto userTo;
        private String body;
        private LocalDateTime updateAt;

        private MessageWithZoneDtoBuilder() {
        }

        public MessageWithZoneDtoBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public MessageWithZoneDtoBuilder setCreateAt(LocalDateTime createAt) {
            this.createAt = createAt;
            return this;
        }

        public MessageWithZoneDtoBuilder setUserFrom(UserReadDto userFrom) {
            this.userFrom = userFrom;
            return this;
        }

        public MessageWithZoneDtoBuilder setUserTo(UserReadDto userTo) {
            this.userTo = userTo;
            return this;
        }

        public MessageWithZoneDtoBuilder setBody(String body) {
            this.body = body;
            return this;
        }

        public MessageWithZoneDtoBuilder setUpdateAt(LocalDateTime updateAt) {
            this.updateAt = updateAt;
            return this;
        }

        public MessageWithZoneDto build() {
            return new MessageWithZoneDto(id, createAt, userFrom, userTo, body, updateAt);
        }
    }
}
