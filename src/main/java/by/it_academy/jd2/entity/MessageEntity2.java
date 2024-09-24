package by.it_academy.jd2.entity;

import java.time.OffsetDateTime;
import java.util.UUID;

public class MessageEntity2 {
    private UUID id;
    private final OffsetDateTime createAt;
    private final UUID userIdFrom;   //вообще лучше будет с id поработать или накрай с логином
    private final UUID userIdTo;
    private final String text;

    private MessageEntity2(UUID id, OffsetDateTime createAt, UUID userIdFrom, UUID userIdTo, String text) {
        this.id = id;
        this.createAt = createAt;
        this.userIdFrom = userIdFrom;
        this.userIdTo = userIdTo;
        this.text = text;
    }

    public static MessageEntityBuilder builder() {
        return new MessageEntityBuilder();
    }

    public OffsetDateTime getCreateAt() {
        return createAt;
    }

    public UUID getUserIdFrom() {
        return userIdFrom;
    }

    public UUID getUserIdTo() {
        return userIdTo;
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
        private UUID userIdFrom;
        private UUID userIdTo;
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

        public MessageEntityBuilder setUserIdFrom(UUID userIdFrom) {
            this.userIdFrom = userIdFrom;
            return this;
        }

        public MessageEntityBuilder setUserIdTo(UUID userIdTo) {
            this.userIdTo = userIdTo;
            return this;
        }

        public MessageEntityBuilder setText(String text) {
            this.text = text;
            return this;
        }
        public MessageEntity2 build() {
            return new MessageEntity2(id, createAt, userIdFrom, userIdTo, text);
        }
    }
}
