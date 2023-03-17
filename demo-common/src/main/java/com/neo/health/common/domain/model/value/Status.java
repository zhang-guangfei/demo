package com.neo.health.common.domain.model.value;

import com.neo.health.common.type.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 通用状态值对象
 */
@Getter
@AllArgsConstructor
public enum Status implements ValueObject {

    DISABLE(0, "禁用"),
    ENABLE(1, "启用");

    private Integer code;
    private String name;


}
