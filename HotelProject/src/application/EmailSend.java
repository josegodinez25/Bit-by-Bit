package application;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * This class manages sending emails to customers using Gmail SMTP server.
 * It provides a method to send emails to customers using their IDs, subject, and message text.
 * @author Jose
 * @version 1.0
 * @since 1.0
 */
public class EmailSend {
	// Unsure why but we need this for the javadoc
	/**
     * Default constructor for the EmailSend class.
     * Use this constructor to create an instance of the EmailSend class.
     */
    public EmailSend() {
        // Default constructor logic, if needed
    }

	
	/**
     * Sends an email to a customer identified by their ID.
     *
     * @param customerID  the ID of the customer to whom the email will be sent
     * @param subject     the subject of the email
     * @param messageText the content/body of the email message
     */
	public void sendEmailToCustomer(String customerID, String subject, String messageText) {
        ReadWriteExcel excel = new ReadWriteExcel();
        String to = excel.getEmail(customerID);
        // Proceed to send an email only if the recipient's email address is retrieved
        if (to != null) { // Check if the email is a Gmail address
        	// Company Email
            String from = "asylumhotelcoporation@gmail.com";
            // application password (actual password is 123Password$ to login on gmail)
            String password = "ihni czms mtrh yyhp"; // 

            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com"); // Gmail SMTP server
            properties.put("mail.smtp.port", "587"); // Gmail SMTP port
            
            // creates a mail session
            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication(from, password);
                }
            });

            try {
            	// This creates and composes a new email message
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
                message.setSubject(subject);
                message.setText(messageText);
                // Sends the email
                Transport.send(message);
                System.out.println("Email sent successfully to " + to);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Failed to send email to " + to);
            }
        } 
    }
}