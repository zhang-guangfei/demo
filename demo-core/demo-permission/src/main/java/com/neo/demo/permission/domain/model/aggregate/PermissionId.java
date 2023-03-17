package com.neo.demo.permission.domain.model.aggregate;

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
public class PermissionId implements AggregateIdentifier, ValueObject {
    private String permissionId;
}
