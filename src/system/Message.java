package system;

import java.io.Serializable;

import person.Person;
import person.User;

public class Message implements Serializable{

    private static final long serialVersionUID = 1L;

    private StringBuilder Message;
    Boolean FromUser;

    public Message() {
        Message=new StringBuilder("");
        FromUser=false;
    }

    public void sendNewMessage(Person Sender,String NewMessage) {
        this.Message.append(Sender.getFname());
        if(Sender instanceof User){
            this.Message.append("\t").append(Sender.getEmail());
            this.FromUser=true;
        }
        else
            this.FromUser=false;
 
        this.Message.append("\n").append(NewMessage).append("\n\n");
    }

    public StringBuilder viewMessages() {
        return this.Message;
    }
    public Boolean FromUser(){
        return this.FromUser;
    }
    public void deleteMessages() {
        this.Message=new StringBuilder("");
    }
}
