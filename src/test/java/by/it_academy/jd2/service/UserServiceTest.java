package by.it_academy.jd2.service;

import by.it_academy.jd2.dao.api.IUserDao;
import by.it_academy.jd2.service.dto.UserReadDto;
import by.it_academy.jd2.dao.entity.UserEntity;
import by.it_academy.jd2.service.mapper.api.IUserMapper;
import by.it_academy.jd2.service.mapper.factory.MapperFactory;
import by.it_academy.jd2.service.validation.api.IValidateForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private IValidateForm validationForm;

    @Spy   // если поставлю @Spy будет proxy, т.е. и mock и реальный маппер
    private IUserMapper userMapper = MapperFactory.getUserMapper();

    @Mock
    private IUserDao userDao;

    @InjectMocks
    private UserService userService;

    @Test
    void getByLogin() {
        UUID userId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();
        UserEntity userEntity = UserEntity.builder().setId(userId)
                .setLogin("test")
                .setPassword("test")
                .setRole("USER")
                .setBirthDate(LocalDate.now())
                .setCreateAt(now)
                .setName("test")
                .setUpdateAt(now)
                .build();

        UserReadDto userReadDto = userMapper.mapEntityToDto(userEntity);  //с моком null, со spy норм

        Mockito.doReturn(Optional.of(userEntity)).when(userDao).getByLogin("test");
        Mockito.doReturn(userReadDto).when(userMapper).mapEntityToDto(userEntity);

        Optional<UserReadDto> actual = userService.getByLogin("test");

        Assertions.assertTrue(actual.isPresent());
        actual.ifPresent(act -> Assertions.assertEquals(userReadDto, act));

     //   Mockito.verify(userMapper).mapEntityToDto(Mockito.any(UserEntity.class));
    }

}