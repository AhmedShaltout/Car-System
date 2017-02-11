package system;

public class Activity {
	private String date;
	private String action;
	
	
	public Activity(String action , String date){
		this.date=date;
		this.action=action;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public String getAction() {
		return this.action;
	}

}
