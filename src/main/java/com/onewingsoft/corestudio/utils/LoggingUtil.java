package com.onewingsoft.corestudio.utils;

import com.onewingsoft.corestudio.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Ignacio González Bullón - <nacho.gonzalez.bullon@gmail.com>
 * @since 01/01/16.
 */
public class LoggingUtil {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static final void writeInfoLog(String message) {
        logger.info(message);
    }

    public static final void writeDebugLog(String message) {
        logger.debug(message);
    }

    public static final void writeErrorLog(String message) {
        logger.error(message);
    }

    public static final void writeWarnLog(String message) {
        logger.warn(message);
    }
}
