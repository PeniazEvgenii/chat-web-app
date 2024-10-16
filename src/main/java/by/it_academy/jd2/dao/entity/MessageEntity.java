package by.it_academy.jd2.dao.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "messages")
public class MessageEntity {
    @Id
    private UUID id;

    @Column(nullable = false)
    private OffsetDateTime createAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id_from", nullable = false)
    private UserEntity userFrom;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id_to", nullable = false)
    private UserEntity userTo;

    @Column
    private String body;

    @Column(nullable = false)
    private OffsetDateTime updateAt;

    private MessageEntity(UUID id, OffsetDateTime createAt, UserEntity userFrom, UserEntity userTo, String body, OffsetDateTime updateAt) {
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

    public OffsetDateTime getUpdateAt() {
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
        private OffsetDateTime updateAt;

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

        public MessageEntityBuilder setUpdateAt(OffsetDateTime updateAt) {
            this.updateAt = updateAt;
            return this;
        }

        public MessageEntity build() {
            return new MessageEntity(id, createAt, userFrom, userTo, body, updateAt);
        }
    }
}
