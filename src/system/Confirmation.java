package system;

import java.util.ArrayList;

public class Confirmation {
    
	public static void accountConfirmation(String email, String string2) {
		send("Hi "+string2+" ,\nWelcome to our system.", email);
	}
	public static void waitingForApproval(String to) {
		send("Your car is waiting for approval.", to);
	}
	public static void rent(String to) {
		send("you have rented a car successfuly.", to);
	}
	public static void unbook(String to) {
		send("you have unbooked a car and your money with be back to your account in minutes.", to);
	}
	public static void reschedule(String to) {
		send("you have rescheduled your booking successfuly.", to);
	}
	public static void sendPassword(String to, Long s) {
		send("the key is \n"+s+" \n please change your password now for security.", to);
	}
	public static void sold(String to, int carId) {
		send("congratulations your car with id '"+carId+"' was sold.", to);
	}
	public static void baught(String to) {
		send("congratulations you have baught a car.", to);
	}
	public static void sendMessage(String to, String message2) {
		send("we have got a feedback from you and here is the replymemt.\n "+message2+"", to);
	}
	public static void acceptedForSell(String to) {
		send("your car is accepted to be sold", to);
	}
	public static void sendAn(String message){
		ArrayList<String> to=DB.emails();
		for(String s:to)
			send(message,s);
	}
	private static void send(String message,String to){
		new Thread(new Runnable() {
			@Override
			public void run() {
				EmailSender.sendMail(message, to);
			}
		});
	}
}
