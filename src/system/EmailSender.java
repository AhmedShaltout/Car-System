package system;

import java.util.Properties;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class EmailSender {
    private static final String Email="carrentproject2016@gmail.com";
    private static final String Password="carrentpassword";
	
    public static boolean sendMail(String Message, String to){
	    String host = "smtp.gmail.com";
		Properties props = System.getProperties();
		props.put("mail.smtp.stattls.enable", "true");
		props.put("mail.smtp.host",host);
		props.put("mail.smtp.user", Email);
		props.put("mail.smtp.password", Password);
		props.put("mail.smtp.port", 587);
		props.put("mail.smtp.auth", "true");	
		props.put("mail.smtp.starttls.enable", "true");
		Session session = Session.getDefaultInstance(props,null);
		MimeMessage mimeMessage = new MimeMessage(session);
		try{
			mimeMessage.setFrom(new InternetAddress(Email));
		    mimeMessage.addRecipient(RecipientType.TO, new InternetAddress(to));
		    mimeMessage.setSubject("A.S Company");
		    mimeMessage.setText(Message);
			Transport transport = session.getTransport("smtp");
			transport.connect(host,Email,Password);
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
			transport.close();
			return true;
		}catch(MessagingException ex){
		    JOptionPane.showMessageDialog(null, ex.getMessage(), "Warning", JOptionPane.OK_OPTION);
		    return false;
		   }
    }  
}
