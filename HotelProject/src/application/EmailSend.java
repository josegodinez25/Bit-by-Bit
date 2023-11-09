package application;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailSend {
	
	public void sendEmailToCustomer(String customerID, String subject, String messageText) {
        ReadWriteExcel excel = new ReadWriteExcel();
        String to = excel.getEmail(customerID);
        //comment saved
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

            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication(from, password);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
                message.setSubject(subject);
                message.setText(messageText);

                Transport.send(message);
                System.out.println("Email sent successfully to " + to);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Failed to send email to " + to);
            }
        } 
    }
}
