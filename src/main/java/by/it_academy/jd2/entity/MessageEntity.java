package by.it_academy.jd2.entity;

import java.time.OffsetDateTime;
import java.util.UUID;

public class MessageEntity {
    private UUID id;
    private final OffsetDateTime createAt;
    private final UserEntity userFrom;
    private final UserEntity userTo;
    private final String text;

    private MessageEntity(UUID id, OffsetDateTime createAt, UserEntity userFrom, UserEntity userTo, String text) {
        this.id = id;
        this.createAt = createAt;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.text = text;
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

    public String getText() {
        return text;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public static class MessageEntityBuilder {
        private UUID id;
        private OffsetDateTime createAt;
        private UserEntity userFrom;
        private UserEntity userTo;
        private String text;

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

        public MessageEntityBuilder setText(String text) {
            this.text = text;
            return this;
        }
        public MessageEntity build() {
            return new MessageEntity(id, createAt, userFrom, userTo, text);
        }
    }
}
