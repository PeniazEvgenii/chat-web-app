package by.it_academy.jd2.dao;

import by.it_academy.jd2.dao.api.IUserDao;
import by.it_academy.jd2.dao.factory.UserDaoFactory;
import by.it_academy.jd2.dto.UserForMassageEntity;
import by.it_academy.jd2.entity.MessageEntity;
import by.it_academy.jd2.entity.MessageEntity2;
import by.it_academy.jd2.dao.exception.DaoException;
import by.it_academy.jd2.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessageDao {
    IUserDao userDao = UserDaoFactory.getInstance();   // может и не надо

    public MessageDao() {}

    private static final String INSERT_MESSAGE_SQL = "INSERT INTO app.messages (create_at, user_id_from, user_id_to, body)" +
            " VALUES (?, ?, ?, ?) RETURNING id";
    private static final String GET_MESSAGE_BY_LOGIN_SQL = "SELECT id, create_at, user_id_from, user_id_to, body FROM app.messages " +
            "WHERE app.messages.user_id_to = ?";

    private static final String GET_MESSAGE_BY_LOGIN_SQL2 = "SELECT app.messages.id, create_at, user_id_from, login as user_from_login,  user_id_to, body FROM app.messages " +
            "JOIN app.users ON app.users.id = app.messages.user_id_from WHERE user_id_to = ?";                    // в запросе touser_id надо убрать

    private static final String GET_MESSAGE_COUNT_SQL = "SELECT COUNT(id) as count_message FROM app.messages";

    public MessageEntity create(MessageEntity message) {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MESSAGE_SQL)) {
            preparedStatement.setObject(1, message.getCreateAt());
            preparedStatement.setLong(2, message.getUserFrom().getId());
            preparedStatement.setLong(3, message.getUserTo().getId());
            preparedStatement.setString(4, message.getText());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getObject(1, Long.class);
                message.setId(id);
            }
            return message;

        } catch (SQLException e) {
            throw new DaoException("Ошибка при сохранении данных", e);
        }
    }

    public MessageEntity2 create(MessageEntity2 message) {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MESSAGE_SQL)) {
            preparedStatement.setObject(1, message.getCreateAt());
            preparedStatement.setLong(2, message.getUserFrom().getId());
            preparedStatement.setLong(3, message.getUserTo().getId());
            preparedStatement.setString(4, message.getText());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getObject(1, Long.class);
                message.setId(id);
            }
            return message;

        } catch (SQLException e) {
            throw new DaoException("Ошибка при сохранении данных", e);
        }
    }

    public List<MessageEntity> getByUserTo(Long userToId) {          //это сообщение мне, себя знаю в сессии id у аттрибута
        try (Connection connection = ConnectionManager.open();       //сильно жирно данных достал
             PreparedStatement preparedStatement = connection.prepareStatement(GET_MESSAGE_BY_LOGIN_SQL)) {
            preparedStatement.setObject(1, userToId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<MessageEntity> messages = new ArrayList<>();
            while (resultSet.next()) {
                MessageEntity message = MessageEntity.builder()
                        .setId(resultSet.getObject("id", Long.class))
                        .setCreateAt(resultSet.getObject("create_at", OffsetDateTime.class))
                        .setUserFrom(userDao.getById(resultSet.getObject("user_id_from", Long.class)).orElse(null))
                        .setUserTo(userDao.getById(resultSet.getObject("user_id_to", Long.class)).orElse(null))     //себя можно не заполнять, есть в
                        .setText(resultSet.getObject("body", String.class))
                        .build();
                messages.add(message);
            }
            return messages;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<MessageEntity2> getByUserTo2(UserForMassageEntity userTo) {          // это достаю обрезок Usera
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_MESSAGE_BY_LOGIN_SQL2)) {
            preparedStatement.setObject(1, userTo.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            List<MessageEntity2> messages = new ArrayList<>();
            while (resultSet.next()) {
                MessageEntity2 message = MessageEntity2.builder()
                        .setId(resultSet.getObject("id", Long.class))
                        .setCreateAt(resultSet.getObject("create_at", OffsetDateTime.class))
                        .setUserFrom(new UserForMassageEntity(
                                resultSet.getObject("user_id_from", Long.class),
                                resultSet.getObject("user_from_login", String.class)))
                        .setUserTo(userTo)
                        .setText(resultSet.getObject("body", String.class))
                        .build();
                messages.add(message);
            }
            return messages;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Long getCount() {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_MESSAGE_COUNT_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            Long countMessage = null;
            if(resultSet.next()) {
                countMessage = resultSet.getObject("count_message", Long.class);
            }
            return countMessage != null ? countMessage : 0L;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
