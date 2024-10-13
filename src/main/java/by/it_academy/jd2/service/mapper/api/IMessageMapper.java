package by.it_academy.jd2.service.mapper.api;

import by.it_academy.jd2.service.dto.MessageReadDto;
import by.it_academy.jd2.service.dto.MessageWithZoneDto;
import by.it_academy.jd2.dao.entity.MessageEntity;

import java.time.ZoneId;

public interface IMessageMapper {

    MessageReadDto mapFrom(MessageEntity messageEntity);

    MessageWithZoneDto mapFrom(MessageReadDto messageReadDto, ZoneId zoneId);


}
