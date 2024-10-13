package by.it_academy.jd2.service.api;

import by.it_academy.jd2.service.dto.MessageByUserDto;
import by.it_academy.jd2.service.dto.MessageCreateDto;
import by.it_academy.jd2.service.dto.MessageReadDto;
import by.it_academy.jd2.service.dto.MessageWithZoneDto;

import java.util.List;
import java.util.UUID;

public interface IMessageService {

    void create(MessageCreateDto messageCreateDto);

    Long getCount();

    List<MessageWithZoneDto> getAllByUserTo(MessageByUserDto messageByUserDto);

    List<MessageReadDto> getAllByUserTo(UUID id);

}
