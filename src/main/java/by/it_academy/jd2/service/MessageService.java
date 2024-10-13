package by.it_academy.jd2.service;

import by.it_academy.jd2.dao.api.IMessageDao;
import by.it_academy.jd2.dao.entity.MessageEntity;
import by.it_academy.jd2.dao.entity.UserEntity;
import by.it_academy.jd2.service.mapper.api.IMessageMapper;
import by.it_academy.jd2.service.api.IMessageService;
import by.it_academy.jd2.service.api.IUserService;
import by.it_academy.jd2.service.dto.MessageByUserDto;
import by.it_academy.jd2.service.dto.MessageCreateDto;
import by.it_academy.jd2.service.dto.MessageReadDto;
import by.it_academy.jd2.service.dto.MessageWithZoneDto;
import by.it_academy.jd2.service.validation.Error;
import by.it_academy.jd2.service.validation.ValidationResult;
import by.it_academy.jd2.service.validation.api.IValidateMessage;
import by.it_academy.jd2.service.validation.exception.ValidationException;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class MessageService implements IMessageService {

    private final IUserService userService;
    private final IMessageDao messageDao;
    private final IValidateMessage validateMessage;
    private final IMessageMapper messageMapper;

    public MessageService(IUserService userService, IMessageDao messageDao, IValidateMessage validateMessage, IMessageMapper messageMapper) {
        this.userService = userService;
        this.messageDao = messageDao;
        this.validateMessage = validateMessage;
        this.messageMapper = messageMapper;
    }

    public void create(MessageCreateDto messageCreateDto) {

        UserEntity userEntity = userService.getEntityByLogin(messageCreateDto.getUsername()).orElse(null);

        ValidationResult validationResult = validateMessage.isValid(messageCreateDto, userEntity);
        if (!validationResult.checkErrorEmpty()) {
            throw new ValidationException(validationResult.getErrors());
        }

        MessageEntity messageEntity = mapMessageEntity(messageCreateDto, userEntity);

        messageDao.create(messageEntity);
    }

    public Long getCount() {
        return messageDao.getCount();
    }

     public List<MessageWithZoneDto> getAllByUserTo(MessageByUserDto messageByUserDto) {
            return getAllByUserTo(messageByUserDto.getUserId()).stream()
                    .map(message -> messageMapper.mapFrom(message, messageByUserDto.getZoneId()))
                    .collect(Collectors.toList());
     }

    public  List<MessageReadDto> getAllByUserTo(UUID id) {
        return messageDao.getByUserTo(id)
                .stream()
                .map(messageMapper::mapFrom)
                .collect(Collectors.toList());
    }

    private MessageEntity mapMessageEntity(MessageCreateDto messageCreateDto, UserEntity userEntity) {
        return MessageEntity.builder()
                .setId(UUID.randomUUID())
                .setCreateAt(OffsetDateTime.now())
                .setUpdateAt(LocalDateTime.now())
                .setBody(messageCreateDto.getBody())
                .setUserTo(userEntity)
                .setUserFrom(userService.getEntityByLogin
                         (messageCreateDto.getUserFrom().getLogin())
                         .orElseThrow(() -> new ValidationException(
                         List.of(new Error("sender unknown", "sender is unknown"))))
                )
                .build();
    }
}
