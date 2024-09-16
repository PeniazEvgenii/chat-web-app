package by.it_academy.jd2.dao;

import by.it_academy.jd2.dao.api.IUserDao;
import by.it_academy.jd2.dao.connection.api.IConnectionManager;
import by.it_academy.jd2.dto.UserLoginDto;
import by.it_academy.jd2.entity.UserEntity;
import by.it_academy.jd2.dao.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements IUserDao {
    private static final String CREATE_USER_SQL = "INSERT INTO app.users " +
            "(login, password, name, birth_date, registration_date, role) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String GET_ALL_USER_SQL = "SELECT id, login, password, name, birth_date, registration_date, role FROM app.users";
    private static final String GET_USER_BY_ID_SQL = "SELECT id, login, password, name, birth_date, registration_date, role " +
            "FROM app.users WHERE id = ?";
    private static final String DELETE_USER_BY_ID = "DELETE FROM app.users WHERE id = ?";
    private static final String GET_USER_BY_LOG_PASS = "SELECT id, login, password, name, birth_date, registration_date, role " +
            "FROM app.users WHERE login = ? and password = ?";

    private static final String GET_USER_BY_LOG = "SELECT id, login, password, name, birth_date, registration_date, role " +
            "FROM app.users WHERE login = ?";

    private final IConnectionManager connectionManager;

    public UserDao(IConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public UserEntity create(UserEntity user) {
        try (Connection connection = connectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER_SQL, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setDate(4, Date.valueOf(user.getBirthDate()));
            preparedStatement.setDate(5, Date.valueOf(user.getRegistrationDate()));
            preparedStatement.setString(6, user.getRole());

            int i = preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                long id = generatedKeys.getLong("id");
                user.setId(id);
            }
            return user;

        } catch (SQLException e) {
            throw new DaoException("Ошибка при сохранении пользователя", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = connectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID)
        ) {
            preparedStatement.setLong(1, id);
            int i = preparedStatement.executeUpdate();
            return i > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<UserEntity> getById(Long id) {
        try (Connection connection = connectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID_SQL)
        ) {
            preparedStatement.setLong(1, id);
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
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USER_SQL);
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
    public Optional<UserEntity> getUserByPassLogin(UserLoginDto userLoginDto) {
        try (Connection connection = connectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_LOG_PASS)
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
    public Optional<UserEntity> getUserByLogin(String login) {
        try (Connection connection = connectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_LOG)
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

    private UserEntity buildUserEntity(ResultSet resultSet) throws SQLException {
        return UserEntity.builder()
                .setId(resultSet.getLong("id"))
                .setLogin(resultSet.getString("login"))
                .setPassword(resultSet.getString("password"))
                .setName(resultSet.getString("name"))
                .setBirthDate(resultSet.getDate("birth_date").toLocalDate())
                .setRegistrationDate(resultSet.getDate("registration_date").toLocalDate())
                .setRole(resultSet.getString("role"))
                .build();
    }
}
