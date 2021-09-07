package service;

import config.ConfigurationFile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Zimi Li
 */
class PasswordServiceTest {

    @Test
    void generatePassword() {
        for (int i=0; i<100; i++)
            assertEquals(PasswordService.generatePassword().length(), ConfigurationFile.PASSWORD_LENGTH);
    }

    @Test
    void sendMail() {
        //PasswordService.sendMail("zimi.li@revature.net", "This is a test password");
    }
}