package ru.j4jdraft.mt.nonblockingcache;

public class OptimisticException extends RuntimeException {
    public OptimisticException(String message) {
        super(message);
    }
}
