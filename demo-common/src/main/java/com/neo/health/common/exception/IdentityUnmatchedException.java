package com.neo.health.common.exception;

/**
 * ID不匹配异常
 */
public class IdentityUnmatchedException extends EntityVaildateException {

    public IdentityUnmatchedException(String message) {
        super(message);
    }
}
