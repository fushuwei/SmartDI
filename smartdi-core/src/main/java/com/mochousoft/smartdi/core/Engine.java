package com.mochousoft.smartdi.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 入口类
 */
public class Engine {

    private static final Logger logger = LoggerFactory.getLogger(Engine.class);

    public static void main(String[] args) {
        int exitStatus = 0;

        try {
            // todo
        } catch (Throwable cause) {
            exitStatus = 1;
            logger.error("SDI执行异常", cause);
        }

        System.exit(exitStatus);
    }
}
