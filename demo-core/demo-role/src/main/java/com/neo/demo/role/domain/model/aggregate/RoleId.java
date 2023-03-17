package com.neo.demo.role.domain.model.aggregate;

import com.neo.health.common.type.AggregateIdentifier;
import com.neo.health.common.type.ValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class RoleId implements AggregateIdentifier, ValueObject {
    private String RoleId;
}
