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
  
  public static boolean sendMail(String from, String password, String Message, String to[]){
    String host = "smtp.gmail.com";
    Properties props = System.getProperties();
    props.put("mail.smtp.stattls.enable", "true");
    props.put("mail.smtp.host",host);
    props.put("mail.smtp.user", from);
    props.put("mail.smtp.password", password);
    props.put("mail.smtp.port", 587);
    props.put("mail.smtp.auth", "true");	
    props.put("mail.smtp.starttls.enable", "true");

    Session session = Session.getDefaultInstance(props,null);
    MimeMessage mimeMessage = new MimeMessage(session);
    try{mimeMessage.setFrom(new InternetAddress(from));
    InternetAddress[] toAddress = new InternetAddress[to.length];
    for(int i=0;i<to.length;i++){
        toAddress[i]= new InternetAddress(to[1]);
        
    }
    for(int i=0;i<toAddress.length;i++){
        mimeMessage.addRecipient(RecipientType.TO, toAddress[i]);
    }
    mimeMessage.setSubject("Car Rent Company");
    mimeMessage.setText(Message);
    Transport transport = session.getTransport("smtp");
    transport.connect(host,from,password);
    transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
    transport.close();
    return true;
    
    }catch(MessagingException ex){
        JOptionPane.showMessageDialog(null, ex.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
    }
    return false;
  }  
}
