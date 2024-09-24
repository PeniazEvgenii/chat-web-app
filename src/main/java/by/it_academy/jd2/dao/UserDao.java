package by.it_academy.jd2.dao;

import by.it_academy.jd2.dao.api.IUserDao;
import by.it_academy.jd2.dao.connection.api.IConnectionManager;
import by.it_academy.jd2.dto.UserLoginDto;
import by.it_academy.jd2.entity.UserEntity;
import by.it_academy.jd2.dao.exception.DaoException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserDao implements IUserDao {
    private static final String CREATE_SQL = "INSERT INTO app.users " +
            "(id, login, password, name, birth_date, registration_at, update_at, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_ALL_SQL = "SELECT id, login, password, name, birth_date, registration_at, update_at, role FROM app.users";
    private static final String GET_BY_ID_SQL = "SELECT id, login, password, name, birth_date, registration_at, update_at, role " +
            "FROM app.users WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM app.users WHERE id = ?";
    private static final String GET_BY_LOG_PASS = "SELECT id, login, password, name, birth_date, registration_at, update_at, role " +
            "FROM app.users WHERE login = ? and password = ?";
    private static final String GET_BY_LOG = "SELECT id, login, password, name, birth_date, registration_at, update_at, role " +
            "FROM app.users WHERE login = ?";
    private static final String GET_COUNT_SQL = "SELECT count(id) as count_users from app.users";

    private final IConnectionManager connectionManager;

    public UserDao(IConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public UserEntity create(UserEntity user) {
        try (Connection connection = connectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_SQL)
        ) {
            preparedStatement.setObject(1, user.getId());
            preparedStatement.setObject(2, user.getLogin());
            preparedStatement.setObject(3, user.getPassword());
            preparedStatement.setObject(4, user.getName());
            preparedStatement.setObject(5, user.getBirthDate());
            preparedStatement.setObject(6, user.getRegistrationAt());
            preparedStatement.setObject(7, user.getUpdateAt());
            preparedStatement.setObject(8, user.getRole());

            preparedStatement.executeUpdate();

            return user;
        } catch (SQLException e) {
            throw new DaoException("Ошибка при сохранении пользователя", e);
        }
    }

    @Override
    public boolean delete(UUID id) {
        try (Connection connection = connectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)
        ) {
            preparedStatement.setObject(1, id);
            int i = preparedStatement.executeUpdate();
            return i > 0;
        } catch (SQLException e) {
            throw new DaoException("Ошибка удаления пользователя",e);
        }
    }

    @Override
    public Optional<UserEntity> getById(UUID id) {
        try (Connection connection = connectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_SQL)
        ) {
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            UserEntity user = null;
            if(resultSet.next()) {
                user = buildUserEntity(resultSet);
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UserEntity> getAll() {
        try (Connection connection = connectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SQL);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<UserEntity> result = new ArrayList<>();
            while (resultSet.next()) {
                UserEntity user = buildUserEntity(resultSet);
                result.add(user);
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<UserEntity> getByPassLogin(UserLoginDto userLoginDto) {
        try (Connection connection = connectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_LOG_PASS)
        ) {
            preparedStatement.setString(1, userLoginDto.getLogin());
            preparedStatement.setString(2, userLoginDto.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            UserEntity user = null;
            while (resultSet.next()) {
                user = buildUserEntity(resultSet);
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<UserEntity> getByLogin(String login) {
        try (Connection connection = connectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_LOG)
        ) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            UserEntity user = null;
            if (resultSet.next()) {
                user = buildUserEntity(resultSet);
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Long getCount() {
        try (Connection connection = connectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_COUNT_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            Long countMessage = null;
            if(resultSet.next()) {
                countMessage = resultSet.getObject("count_users", Long.class);
            }
            return countMessage != null ? countMessage : 0L;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private UserEntity buildUserEntity(ResultSet resultSet) throws SQLException {
        return UserEntity.builder()
                .setId(resultSet.getObject("id", UUID.class))
                .setLogin(resultSet.getObject("login",String.class))
                .setPassword(resultSet.getObject("password", String.class))
                .setName(resultSet.getObject("name", String.class))
                .setBirthDate(resultSet.getDate("birth_date").toLocalDate())
                .setRegistrationAt(resultSet.getObject("registration_at", LocalDateTime.class))
                .setUpdateAt(resultSet.getObject("registration_at", LocalDateTime.class))
                .setRole(resultSet.getString("role"))
                .build();
    }
}
