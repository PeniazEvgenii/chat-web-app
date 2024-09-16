package by.it_academy.jd2.entity;

import by.it_academy.jd2.dto.UserForMassageEntity;

import java.time.OffsetDateTime;

public class MessageEntity2 {
    private Long id;
    private final OffsetDateTime createAt;
    private final UserForMassageEntity userFrom;
    private final UserForMassageEntity userTo;
    private final String text;

    private MessageEntity2(Long id, OffsetDateTime createAt, UserForMassageEntity userFrom, UserForMassageEntity userTo, String text) {
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

    public UserForMassageEntity getUserFrom() {
        return userFrom;
    }

    public UserForMassageEntity getUserTo() {
        return userTo;
    }

    public String getText() {
        return text;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public static class MessageEntityBuilder {
        private Long id;
        private OffsetDateTime createAt;
        private UserForMassageEntity userFrom;
        private UserForMassageEntity userTo;
        private String text;

        private MessageEntityBuilder() {
        }

        public MessageEntityBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public MessageEntityBuilder setCreateAt(OffsetDateTime createAt) {
            this.createAt = createAt;
            return this;
        }

        public MessageEntityBuilder setUserFrom(UserForMassageEntity userFrom) {
            this.userFrom = userFrom;
            return this;
        }

        public MessageEntityBuilder setUserTo(UserForMassageEntity userTo) {
            this.userTo = userTo;
            return this;
        }

        public MessageEntityBuilder setText(String text) {
            this.text = text;
            return this;
        }
        public MessageEntity2 build() {
            return new MessageEntity2(id, createAt, userFrom, userTo, text);
        }
    }
}
