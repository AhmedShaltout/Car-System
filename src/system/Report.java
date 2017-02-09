package system;

import java.io.Serializable;

public class Report implements Serializable{
    private String Report;
    private int Day;
    private int Month;
    private float Money;
    public Report(String UserName,String Report,float Money,int Day,int Month){
        this.Report=Report;
        this.Day=Day;
        this.Month=Month;
        this.Money=Money;
    }
    public Report(int id, String string) {
		// TODO Auto-generated constructor stub
	}
	public String getReport(){
        return this.Report;
    }
    public int getDay(){
        return this.Day;
    }
    public int getMonth(){
        return this.Month;
    }
}
