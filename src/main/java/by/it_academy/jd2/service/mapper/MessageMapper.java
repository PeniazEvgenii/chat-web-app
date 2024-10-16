package by.it_academy.jd2.service.mapper;

import by.it_academy.jd2.service.dto.MessageDateFormat;
import by.it_academy.jd2.service.dto.MessageReadDto;
import by.it_academy.jd2.service.dto.MessageWithZoneDto;
import by.it_academy.jd2.dao.entity.MessageEntity;
import by.it_academy.jd2.service.mapper.api.IMessageMapper;
import by.it_academy.jd2.service.mapper.api.IUserMapper;
import by.it_academy.jd2.util.DateFormatUtil;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class MessageMapper implements IMessageMapper {
    private final IUserMapper userMapper;

    public MessageMapper(IUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public MessageReadDto mapFrom(MessageEntity messageEntity) {
        return MessageReadDto.builder()
                .setId(messageEntity.getId())
                .setBody(messageEntity.getBody())
                .setCreateAt(messageEntity.getCreateAt())
                .setUpdateAt(messageEntity.getUpdateAt())
                .setUserTo(userMapper.mapEntityToDto(messageEntity.getUserTo()))
                .setUserFrom(userMapper.mapEntityToDto(messageEntity.getUserFrom()))
                .build();
    }

    public MessageWithZoneDto mapFrom(MessageReadDto messageReadDto, ZoneId zoneId) {
        return MessageWithZoneDto.builder()
                .setId(messageReadDto.getId())
                .setBody(messageReadDto.getBody())
                .setCreateAt(convertOffsetToLocalDateTime(messageReadDto.getCreateAt(), zoneId))
                .setUpdateAt(convertOffsetToLocalDateTime(messageReadDto.getUpdateAt(), zoneId))
                .setUserTo(messageReadDto.getUserTo())
                .setUserFrom(messageReadDto.getUserFrom())
                .build();
    }

    public MessageDateFormat mapFrom(MessageWithZoneDto messageWithZoneDto) {
        return MessageDateFormat.builder()
                .setId(messageWithZoneDto.getId())
                .setBody(messageWithZoneDto.getBody())
                .setCreateAt(DateFormatUtil.getFormatStringDateTime(messageWithZoneDto.getCreateAt()))
                .setUpdateAt(DateFormatUtil.getFormatStringDateTime(messageWithZoneDto.getUpdateAt()))
                .setUserTo(messageWithZoneDto.getUserTo())
                .setUserFrom(messageWithZoneDto.getUserFrom())
                .build();
    }

    private LocalDateTime convertOffsetToLocalDateTime(OffsetDateTime offsetDateTime, ZoneId zoneId) {
        ZonedDateTime zonedDateTime = offsetDateTime.atZoneSameInstant(zoneId);
        return zonedDateTime.toLocalDateTime();
    }
}
