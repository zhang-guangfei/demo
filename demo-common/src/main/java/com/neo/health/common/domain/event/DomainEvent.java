package com.neo.health.common.domain.event;

import com.neo.health.common.utils.UUIDGenerator;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 通用领域事件
 */
@Data
public abstract class DomainEvent {

    private String eventId;
    private LocalDateTime eventTime;

    public DomainEvent() {
        this.eventId = UUIDGenerator.eventId();
        this.eventTime = LocalDateTime.now();
    }

}
