package system;

import java.io.Serializable;

public class Book implements Serializable{

    private static final long serialVersionUID = 1L;
    private int Day;
    private int hour;
    
    public Book(){
        this.Day=0;
        this.hour=0;
    }
    
    public void setDay(int NewDay){
        this.Day=NewDay;
    }
    public void setHour(int NewHour){
        this.hour=NewHour;
    }
    public int getDay(){
        return this.Day;
    }
    public int getHour(){
        return this.hour;
    }
    
    public int getFullHours() {
        return ((this.Day-1)*24)+(this.hour);
    }
    
}
