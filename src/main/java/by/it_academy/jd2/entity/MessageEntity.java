package by.it_academy.jd2.entity;

import java.time.LocalDateTime;

public class MessageEntity {
    private final LocalDateTime dateTime;
    private final UserEntity userFrom;
    private final UserEntity userTo;
    private final String text;

    private MessageEntity(LocalDateTime dateTime, UserEntity userFrom, UserEntity userTo, String text) {
        this.dateTime = dateTime;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.text = text;
    }

    public static MessageEntityBuilder builder() {
        return new MessageEntityBuilder();
    }

    public LocalDateTime getDateTime() {
        return dateTime;
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

    public static class MessageEntityBuilder {
        private LocalDateTime dateTime;
        private UserEntity userFrom;
        private UserEntity userTo;
        private String text;

        private MessageEntityBuilder() {
        }

        public MessageEntityBuilder setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
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
            return new MessageEntity(dateTime, userFrom, userTo, text);
        }
    }
}
