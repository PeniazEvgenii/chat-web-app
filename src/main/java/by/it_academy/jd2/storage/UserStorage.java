package by.it_academy.jd2.storage;

import by.it_academy.jd2.entity.ERole;
import by.it_academy.jd2.entity.UserEntity;
import by.it_academy.jd2.storage.api.IUserStorage;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserStorage implements IUserStorage {
    public static final IUserStorage INSTANCE = new UserStorage();

    private final Map<Long, UserEntity> userMap = new HashMap<>();

    private UserStorage() {
        UserEntity admin = UserEntity.builder()
                .setLogin("root")
                .setPassword("root")
                .setRole(ERole.ADMIN.name())
                .setBirthDate(LocalDate.of(1991, 1, 1))
                .setName("Петрович")
                .build();
        saveUser(admin);
    }

    private Long saveUser(UserEntity admin) {
        long id = userMap.size() + 1;
        userMap.put(id, admin);
        return id;
    }

    @Override
    public Long create(UserEntity userEntity) {
        return saveUser(userEntity);
    }

    @Override
    public Optional<UserEntity> getById(Long id) {
        return Optional.of(userMap.get(id));
    }

    @Override
    public Map<Long, UserEntity> getAll() {
        return userMap;
    }

    public static IUserStorage getInstance() {
        return INSTANCE;
    }
}
