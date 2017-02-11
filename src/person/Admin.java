package person;

import java.util.ArrayList;
import car.CarForRent;
import car.CarForSell;
import system.Activity;
import system.Confirmation;
import system.DB;


public abstract class Admin{
	public boolean addCarForSell(CarForSell car){
		return DB.addCarForSell(-1, car);
	}
	
	public boolean addCarForRent(CarForRent car){
		return DB.addCarForRent(car);
	}
	
	public boolean deleteCar(int id,boolean forSell){
		if(forSell)
			return DB.deleteCarForSell(id);
		return DB.deleteCarForRent(id);
	}
	
    public ArrayList<String> CompanyFeedback() {
    	return DB.seeCompanyFeedback();
    }
    
    public ArrayList<String> viewCarFeedback(int CarID){
    	return DB.seeCarFeedback(CarID);
    }
    
    public void replyFeedback(int id,String message){
    	Confirmation.sendMessage(DB.emailOf(id),message);
    }
    
    public boolean deleteAllCarFeedback(int carId) {
    	return DB.deleteFeedbackForCar(carId);
    }
    
    public ArrayList<CarForSell> viewUnAvailableCars() {
    	return DB.waitingCars();
    }
    
    public boolean makeCarAvailable(int carId){
    	String email;
    	if((email=DB.acceptForSell(carId))!=null){
    		Confirmation.acceptedForSell(email);
    		return true;
    	}
    	return false;
    }
    
    public  float getCompanyRate() {
        return DB.getCompanyRate();
    }
    
    public boolean setAbout(String something){
        return DB.setAboutCompany(something);
    }
    
    public  String getAbout(){
        return DB.getAboutCompany();
    }
    
    public ArrayList<Activity> viewDailyReport(){
    	return DB.getDailyReport();
    }
    
    public ArrayList<Activity> viewMonthlyReport(){
    	return DB.getMonthlyReport();
    }

}