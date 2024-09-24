package by.it_academy.jd2.dao;

import by.it_academy.jd2.dao.api.IUserDao;
import by.it_academy.jd2.dao.connection.api.IConnectionManager;
import by.it_academy.jd2.dao.factory.UserDaoFactory;
import by.it_academy.jd2.entity.MessageEntity;
import by.it_academy.jd2.entity.MessageEntity2;
import by.it_academy.jd2.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MessageDao {
    private final IConnectionManager connectionManager;

    IUserDao userDao = UserDaoFactory.getInstance();   // может и не надо  интерфейс подавай!!!!

    public MessageDao(IConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    private static final String INSERT_SQL = "INSERT INTO app.messages (id, create_at, user_id_from, user_id_to, body)" +
            " VALUES (?, ?, ?, ?, ?) ";
    private static final String GET_BY_USER_ID_TO_SQL = "SELECT id, create_at, user_id_from, user_id_to, body FROM app.messages " +    //переменовать
            " WHERE app.messages.user_id_to = ?";

    private static final String GET_COUNT_SQL = "SELECT COUNT(id) as count_message FROM app.messages";

    public MessageEntity create(MessageEntity message) {
        try (Connection connection = connectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {

            preparedStatement.setObject(1, message.getId());
            preparedStatement.setObject(2, message.getCreateAt());
            preparedStatement.setObject(3, message.getUserFrom().getId());
            preparedStatement.setObject(4, message.getUserTo().getId());
            preparedStatement.setObject(5, message.getText());

            int row = preparedStatement.executeUpdate();
            if (row == 0) {
                throw new IllegalStateException("Сообщение не было сохранено");
            }
            return message;
        } catch (SQLException e) {
            throw new DaoException("Ошибка при сохранении данных", e);
        }
    }

    public MessageEntity2 create(MessageEntity2 message) {                                    //entity по user id хз
        try (Connection connection = connectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {

            preparedStatement.setObject(1, message.getId());
            preparedStatement.setObject(2, message.getCreateAt());
            preparedStatement.setObject(3, message.getUserIdFrom());
            preparedStatement.setObject(4, message.getUserIdTo());
            preparedStatement.setObject(5, message.getText());

            int row = preparedStatement.executeUpdate();
            if (row == 0) {
                throw new IllegalStateException("Сообщение не было сохранено");
            }
            return message;

        } catch (SQLException e) {
            throw new DaoException("Ошибка при сохранении данных", e);
        }
    }

    public List<MessageEntity> getByUserTo(UUID userIdTo) {          //это сообщение мне, себя знаю в сессии id у аттрибута // можно достать и конвертнуть в сущность
        try (Connection connection = connectionManager.open();       //сильно жирно данных достал
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_USER_ID_TO_SQL)) {

            preparedStatement.setObject(1, userIdTo);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<MessageEntity> messages = new ArrayList<>();
            while (resultSet.next()) {
                MessageEntity message = MessageEntity.builder()
                        .setId(resultSet.getObject("id", UUID.class))
                        .setCreateAt(resultSet.getObject("create_at", OffsetDateTime.class))
                        .setUserFrom(userDao.getById(resultSet.getObject("user_id_from", UUID.class)).orElse(null))  //и коннекшен передать надо
                        .setUserTo(userDao.getById(resultSet.getObject("user_id_to", UUID.class)).orElse(null))     //себя можно не заполнять, есть в
                        .setText(resultSet.getObject("body", String.class))
                        .build();
                messages.add(message);
            }
            return messages;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<MessageEntity2> getByUserTo2(UUID userIdTo) {          // это достаю обрезок Usera только id
        try (Connection connection = connectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_USER_ID_TO_SQL)) {

            preparedStatement.setObject(1, userIdTo);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<MessageEntity2> messages = new ArrayList<>();

            while (resultSet.next()) {
                MessageEntity2 message = MessageEntity2.builder()
                        .setId(resultSet.getObject("id", UUID.class))
                        .setCreateAt(resultSet.getObject("create_at", OffsetDateTime.class))
                        .setUserIdFrom(resultSet.getObject("user_id_from", UUID.class))
                        .setUserIdTo(resultSet.getObject("user_id_to", UUID.class))
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
}
