package com.wy.synthetic.exception;

public class CommandQueueFullException extends RuntimeException {
    public CommandQueueFullException(String message) {
        super(message);
    }
}
