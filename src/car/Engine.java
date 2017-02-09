package car;

import java.io.Serializable;

public class Engine implements Serializable{

    private static final long serialVersionUID = 1L;
    private int CC;
    private String cylinder; 

    public Engine() {
        this.CC=0;
        this.cylinder="";
    }

    public void setCC(int NewCC) {
        this.CC=NewCC;}

    public void setCylinder(String NewCylinder) {
        this.cylinder=NewCylinder;
    }

    public int getCC() {
        return this.CC;
        
    }

    public String getcylinder() {
        return this.cylinder;
    }
}
