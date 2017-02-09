package system;

import java.io.Serializable;

public class Feedback implements Serializable{

    private static final long serialVersionUID = 1L;
    private  StringBuilder FeaadBack =new StringBuilder("");
    
    public void addFeedback(String Feedback) {
        this.FeaadBack.append(Feedback).append("\n");
    }

    public StringBuilder getFeedback() {
        return this.FeaadBack;
    }
    public void deleteFeedback(){
        this.FeaadBack=new StringBuilder("");
    }
}
