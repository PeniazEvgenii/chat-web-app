package by.it_academy.jd2.service.dto;

import java.time.ZoneId;
import java.util.UUID;

public class MessageByUserDto {
    private final UUID userId;
    private final ZoneId zoneId;

    public MessageByUserDto(UUID userId, ZoneId zoneId) {
        this.userId = userId;
        this.zoneId = zoneId;
    }

    public UUID getUserId() {
        return userId;
    }

    public ZoneId getZoneId() {
        return zoneId;
    }
}
