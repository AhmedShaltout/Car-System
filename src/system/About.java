package system;

import java.io.Serializable;

public class About implements Serializable{

    private static final long serialVersionUID = 1L;

    private String About;
    public About(){
        this.About="";
    }
    public void setAbout(String SomthingAboutThis) {
        this.About=SomthingAboutThis;
    }

    public String getAbout() {
        return this.About;
    }
}
