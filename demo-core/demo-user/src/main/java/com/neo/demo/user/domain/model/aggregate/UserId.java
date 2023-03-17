package com.neo.demo.user.domain.model.aggregate;

import com.neo.health.common.type.AggregateIdentifier;
import com.neo.health.common.type.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Entity;

@Getter
@AllArgsConstructor
public class UserId implements AggregateIdentifier, ValueObject {
    private String userId;
}
