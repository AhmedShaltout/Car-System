package system;

import java.time.LocalDateTime;

public class Activity {
	private int id;
	private String date;
	private String action;

	public Activity(boolean isActivity,int id,String action){
		this.date= LocalDateTime.now().toLocalDate().toString();
		this.action=action;
		this.id=id;
		if(isActivity)
			saveA(this);
		else
			saveR(this);
			
	}
	private void saveA(Activity activity){
		new Thread(new Runnable() {
			@Override
			public void run() {
				DB.saveActivity(activity);
			}
		});
	}
	private void saveR(Activity report){
		new Thread(new Runnable() {
			@Override
			public void run() {
				DB.saveReport(report);
			}
		});
	}
	
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

	public int getId() {
		return this.id;
	}

}
