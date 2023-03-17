package com.neo.health.common.exception;

/**
 * 实体不存在
 */
public class EntityNotExistException extends EntityVaildateException {

    public EntityNotExistException(String message) {
        super(message);
    }
}
