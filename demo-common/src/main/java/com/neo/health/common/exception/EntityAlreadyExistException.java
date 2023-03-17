package com.neo.health.common.exception;

/**
 * 实体已存在
 */
public class EntityAlreadyExistException extends EntityVaildateException {

    public EntityAlreadyExistException(String message) {
        super(message);
    }
}
