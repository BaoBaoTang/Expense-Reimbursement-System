package config;

import org.apache.log4j.Logger;

/**
 * @author Zimi Li
 */
public class ConfigurationFile {
    public static final String DATABASE_URL = "";
    public static final Integer DATABASE_PORT = 5432;
    public static final String DATABASE_NAME = "";
    public static final String DATABASE_USERNAME = "";
    public static final String DATABASE_PASSWORD = "";
    public static final Integer MANAGER = 2;
    public static final Integer PENDING = 1;
    public static final Integer APPROVED = 2;
    public static final Integer DENIED = 3;
    public static final Logger SQL_LOGGER = Logger.getLogger("SQL Exception");
    public static final Logger EMAIL_LOGGER = Logger.getLogger("Email Exception");
    public static final Logger FILE_LOGGER = Logger.getLogger("File Exception");
    public static final String EMAIL_USERNAME = "";
    public static final String EMAIL_PASSWORD = "";
    public static final Integer PASSWORD_LENGTH = 16;
    public static final String CONTEXT_PATH = "";
    public static byte[] TEMP_FILE;
}
