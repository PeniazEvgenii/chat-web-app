package by.it_academy.jd2.dao.entity;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

public class MessageEntity {
    private final UUID id;
    private final OffsetDateTime createAt;
    private final UserEntity userFrom;
    private final UserEntity userTo;
    private final String body;
    private final LocalDateTime updateAt;

    private MessageEntity(UUID id, OffsetDateTime createAt, UserEntity userFrom, UserEntity userTo, String body, LocalDateTime updateAt) {
        this.id = id;
        this.createAt = createAt;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.body = body;
        this.updateAt = updateAt;
    }

    public static MessageEntityBuilder builder() {
        return new MessageEntityBuilder();
    }

    public OffsetDateTime getCreateAt() {
        return createAt;
    }

    public UserEntity getUserFrom() {
        return userFrom;
    }

    public UserEntity getUserTo() {
        return userTo;
    }

    public String getBody() {
        return body;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    @Override
    public String toString() {
        return "MessageEntity{" +
                "id=" + id +
                ", createAt=" + createAt +
                ", userFrom=" + userFrom +
                ", userTo=" + userTo +
                ", body='" + body + '\'' +
                ", updateAt=" + updateAt +
                '}';
    }

    public static class MessageEntityBuilder {
        private UUID id;
        private OffsetDateTime createAt;
        private UserEntity userFrom;
        private UserEntity userTo;
        private String body;
        private LocalDateTime updateAt;

        private MessageEntityBuilder() {
        }

        public MessageEntityBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public MessageEntityBuilder setCreateAt(OffsetDateTime createAt) {
            this.createAt = createAt;
            return this;
        }

        public MessageEntityBuilder setUserFrom(UserEntity userFrom) {
            this.userFrom = userFrom;
            return this;
        }

        public MessageEntityBuilder setUserTo(UserEntity userTo) {
            this.userTo = userTo;
            return this;
        }

        public MessageEntityBuilder setBody(String body) {
            this.body = body;
            return this;
        }

        public MessageEntityBuilder setUpdateAt(LocalDateTime updateAt) {
            this.updateAt = updateAt;
            return this;
        }

        public MessageEntity build() {
            return new MessageEntity(id, createAt, userFrom, userTo, body, updateAt);
        }
    }
}
