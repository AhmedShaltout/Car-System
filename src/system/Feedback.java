package system;

public class Feedback {
	private String feedback;
	private String email;
	private String time;
	
	public Feedback(String feedback,String email,String time){
		this.feedback=feedback;this.email=email;this.time=time;
	}

	public String getFeedback() {
		return this.feedback;
	}

	public String getEmail() {
		return this.email;
	}

	public String getTime() {
		return this.time;
	}
	
	
}
