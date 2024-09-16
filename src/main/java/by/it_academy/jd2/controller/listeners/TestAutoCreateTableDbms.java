package by.it_academy.jd2.controller.listeners;

import by.it_academy.jd2.util.ConnectionManagerOld;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.sql.*;
import java.time.LocalDate;

//@WebListener
public class TestAutoCreateTableDbms implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try (Connection connection = ConnectionManagerOld.open()) {
            createTableUser(connection);
            addAdminToTable(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void createTableUser(Connection connection) {
        String createTableUsers = "CREATE TABLE IF NOT EXISTS app.users " +
                "(id bigserial PRIMARY KEY," +
                " login varchar(30) NOT NULL unique," +
                "password varchar(30)," +
                "name varchar(50)," +
                "birth_date date," +
                "registration_date date," +
                "role varchar(10) );";
        try (Statement statement = connection.createStatement()) {
            int i = statement.executeUpdate(createTableUsers);
            System.out.println("Таблица 'users' создана или уже существует.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void addAdminToTable(Connection connection) {
        String insertAdmin = "INSERT INTO app.users " +
                "(login, name, password, registration_date, role) " +
                "VALUES (?, ?, ?, ?, ?)" +
                " ON CONFLICT DO NOTHING;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertAdmin)) {
            preparedStatement.setString(1, "admin");
            preparedStatement.setString(2, "Петрович");
            preparedStatement.setString(3, "admin");
            preparedStatement.setDate(4, Date.valueOf(LocalDate.now()));
            preparedStatement.setString(5, "admin");

            int i = preparedStatement.executeUpdate();

            if (i > 0) {
                System.out.println("Пользователь 'admin' добавлен.");
            } else {
                System.out.println("Пользователь 'admin' уже существует.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
