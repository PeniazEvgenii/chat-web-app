package by.it_academy.jd2.dao;

import by.it_academy.jd2.dao.api.IMessageDao;
import by.it_academy.jd2.dao.api.IUserDao;
import by.it_academy.jd2.dao.connection.api.IConnectionManager;
import by.it_academy.jd2.dao.entity.MessageEntity;
import by.it_academy.jd2.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MessageDao implements IMessageDao {

    private final IConnectionManager connectionManager;
    private final IUserDao userDao;

    private static final String INSERT_SQL = "INSERT INTO app.messages (id, create_at, user_id_from, user_id_to, body, update_at)" +
            " VALUES (?, ?, ?, ?, ?, ?) ";
    private static final String GET_BY_USER_ID_TO_SQL = "SELECT id, create_at, user_id_from, user_id_to, body, update_at FROM app.messages " +
            " WHERE app.messages.user_id_to = ? ORDER BY create_at DESC";
    private static final String GET_COUNT_SQL = "SELECT COUNT(id) as count_message FROM app.messages";
    private static final String GET_AL_SQL = "SELECT id, create_at, user_id_from, user_id_to, body, update_at FROM app.messages";

    private static final String UPDATE_SQL = "UPDATE app.messages SET user_id_from = ?, user_id_to = ?, body = ?, update_at = ? WHERE id = ?";

    public MessageDao(IConnectionManager connectionManager, IUserDao userDao) {
        this.connectionManager = connectionManager;
        this.userDao = userDao;
    }

    public MessageEntity create(MessageEntity message) {
        try (Connection connection = connectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {

            preparedStatement.setObject(1, message.getId());
            preparedStatement.setObject(2, message.getCreateAt());
            preparedStatement.setObject(3, message.getUserFrom().getId());
            preparedStatement.setObject(4, message.getUserTo().getId());
            preparedStatement.setObject(5, message.getBody());
            preparedStatement.setObject(6, message.getUpdateAt());

            int row = preparedStatement.executeUpdate();
            if (row == 0) {
                throw new IllegalStateException("Сообщение не было сохранено");
            }
            return message;
        } catch (SQLException e) {
            throw new DaoException("Ошибка при сохранении данных", e);
        }
    }


    public List<MessageEntity> getByUserTo(UUID userIdTo) {
        try (Connection connection = connectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_USER_ID_TO_SQL)) {
            connection.setAutoCommit(false);
            connection.setReadOnly(true);

            preparedStatement.setObject(1, userIdTo);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<MessageEntity> messages = new ArrayList<>();
            while (resultSet.next()) {
                MessageEntity message = buildMessageEntity(resultSet, connection);
                messages.add(message);
            }
            connection.commit();
            return messages;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<MessageEntity> getAll() {
        try (Connection connection = connectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_AL_SQL)) {
            connection.setAutoCommit(false);
            connection.setReadOnly(true);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<MessageEntity> messages = new ArrayList<>();
            while (resultSet.next()) {
                MessageEntity message = buildMessageEntity(resultSet, connection);
                messages.add(message);
            }
            connection.commit();
            return messages;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Long getCount() {
        try (Connection connection = connectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_COUNT_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            Long countMessage = null;
            if (resultSet.next()) {
                countMessage = resultSet.getObject("count_message", Long.class);
            }
            return countMessage != null ? countMessage : 0L;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void update(MessageEntity messageEntity) {
        try (Connection connection = connectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            connection.setAutoCommit(false);

            preparedStatement.setObject(1, messageEntity.getUserFrom().getId());
            preparedStatement.setObject(2, messageEntity.getUserTo().getId());
            preparedStatement.setObject(3, messageEntity.getBody());
            preparedStatement.setObject(4, LocalDateTime.now());
            preparedStatement.setObject(5, messageEntity.getId());

            int count = preparedStatement.executeUpdate();

            if(count != 1){
                connection.rollback();
                throw new IllegalStateException("Не получилось обновить запись с id " + messageEntity.getId());
            }

            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private MessageEntity buildMessageEntity(ResultSet resultSet, Connection connection) throws SQLException {
        return MessageEntity.builder()
                .setId(resultSet.getObject("id", UUID.class))
                .setCreateAt(resultSet.getObject("create_at", OffsetDateTime.class))
                .setUserFrom(
                        userDao.getById(resultSet.getObject("user_id_from", UUID.class),
                                connection).orElseThrow(() -> new IllegalStateException("Пользователь не найден")))
                .setUserTo(
                        userDao.getById(resultSet.getObject("user_id_to", UUID.class),
                                connection).orElseThrow(() -> new IllegalStateException("Пользователь не найден")))
                .setBody(resultSet.getObject("body", String.class))
                .setUpdateAt(resultSet.getObject("update_at", LocalDateTime.class))
                .build();
    }
}
