package ru.j4jdraft.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(HelloWorld.class);
        String msg = "Hello World!";
        logger.trace("trace: {}", msg);
        logger.debug("debug: {}", msg);
        logger.info("info: {}", msg);
        logger.warn("warn: {}", msg);
        logger.error("error: {}", msg);
    }
}
