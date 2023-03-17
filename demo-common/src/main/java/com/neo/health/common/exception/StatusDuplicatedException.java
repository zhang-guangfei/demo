package com.neo.health.common.exception;

/**
 * 状态重复异常
 */
public class StatusDuplicatedException extends EntityVaildateException {

    public StatusDuplicatedException(String message) {
        super(message);
    }
}
