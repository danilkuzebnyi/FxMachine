package sk.fxmachine.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BasicLogger {

    private static final Logger logger;

    static {
        logger = LogManager.getLogger();
    }

    private BasicLogger() {
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void error(Exception exception) {
        logger.error(exception);
    }

}
