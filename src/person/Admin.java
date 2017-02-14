package person;

import java.util.ArrayList;
import car.CarForRent;
import car.CarForSell;
import system.Activity;
import system.Confirmation;
import system.DB;
import system.Feedback;


public abstract class Admin{
	public static boolean addCarForSell(CarForSell car){
		return DB.addCarForSell(car);
	}
	
	public static boolean addCarForRent(CarForRent car){
		return DB.addCarForRent(car);
	}
	
	public static boolean deleteCar(int id,boolean forSell){
		if(forSell)
			return DB.deleteCarForSell(id);
		return DB.deleteCarForRent(id);
	}
	
    public static ArrayList<Feedback> CompanyFeedback() {
    	return DB.seeCompanyFeedback();
    }
    
    public static ArrayList<Feedback> viewCarFeedback(int CarID){
    	return DB.seeCarFeedback(CarID);
    }
    
    public static void replyFeedback(int id,String message){
    	Confirmation.sendMessage(DB.emailOf(id),message);
    }
    
    public static ArrayList<CarForSell> viewUnAvailableCars(){
    	return DB.waitingCars();
    }
    
    public static boolean makeCarAvailable(int carId){
    	String email;
    	if((email=DB.acceptForSell(carId))!=null){
    		Confirmation.acceptedForSell(email);
    		return true;
    	}
    	return false;
    }
    
    public static float getCompanyRate() {
        return DB.getCompanyRate();
    }
    
    public static boolean setAbout(String something){
        return DB.setAboutCompany(something);
    }
    
    public static String getAbout(){
        return DB.getAboutCompany();
    }
    
    public static ArrayList<Activity> viewDailyReport(){
    	return DB.getDailyReport();
    }
    
    public static ArrayList<Activity> viewMonthlyReport(){
    	return DB.getMonthlyReport();
    }
    public static ArrayList<CarForRent> viewCarForRent(String carNameOrModel , boolean byName){
    	return User.rentFilter(carNameOrModel, byName);
    }
}