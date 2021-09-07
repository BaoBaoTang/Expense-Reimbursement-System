package service;

import config.ConfigurationFile;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Zimi Li
 */

public class PasswordService {

    public static String generatePassword() {
        return new Random().ints(ConfigurationFile.PASSWORD_LENGTH, 33, 122)
                .mapToObj(c -> String.valueOf((char) c)).collect(Collectors.joining());
    }

    public static void sendMail(String to, String password) {
        String from = ConfigurationFile.EMAIL_USERNAME;
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", 465);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ConfigurationFile.EMAIL_USERNAME, ConfigurationFile.EMAIL_PASSWORD);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Welcome to Expense Reimbursement System");
            message.setText("Thanks for registering Expense Reimbursement System. Your new password is " + password);
            Transport.send(message);
            ConfigurationFile.EMAIL_LOGGER.info("Sent message successfully to " + to);
            ConfigurationFile.EMAIL_LOGGER.info("Password: " + password);
        } catch (MessagingException e) {
            ConfigurationFile.EMAIL_LOGGER.error(e, e.fillInStackTrace());
        }
    }
}
