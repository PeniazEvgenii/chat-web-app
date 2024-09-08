package by.it_academy.jd2.dao;

import by.it_academy.jd2.dao.api.IDao;
import by.it_academy.jd2.entity.UserEntity;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

public class UserDao implements IDao<Long, UserEntity> {
    @Override
    public UserEntity create(UserEntity entity) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(UserEntity entity) {

    }

    @Override
    public Optional<Entity> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<UserEntity> getAll() {
        return null;
    }

    public Optional<UserEntity> getByLoginPass(String login, String password) {
        return Optional.empty();
    }
}
