package person;

import java.util.ArrayList;
import java.util.Calendar;

import car.CarForRent;
import car.CarForSell;
import car.RentFilter;
import payment.PaymentMethod;
import system.Activity;
import system.Book;
import system.Confirmation;
import system.DB;
import system.FileManager;
import system.Message;
import system.Report;

public class User extends Person {
    private Message Message;
    private int RentingNumber;
    private int BuyingNumber;
    private int SellingNumber;
    private boolean firstEdit;

	public User() {
        this.firstEdit = true;
        this.Message=new Message();
        this.BuyingNumber=0;
        this.SellingNumber=0;
        this.RentingNumber=0;
    }
	
	public int getRentingNumber() {
		return RentingNumber;
	}

	public int getBuyingNumber() {
		return BuyingNumber;
	}

	public int getSellingNumber() {
		return SellingNumber;
	}

	public boolean isFirstEdit() {
		return firstEdit;
	}
	
    public boolean editProfile(String Fname,String Lname, boolean Gender, String Address ,String State, String Email,String Password ,String PhoneNumber,String Pic,PaymentMethod Payment) {
        this.setAddress(Address);
        this.setEmail(Email);
        this.setfName(Fname);
        this.setGender(Gender);
        this.setlName(Lname);
        this.setPassword(Password);
        this.setPhoneNumber(PhoneNumber);
        this.setState(State);
        this.setPaymentMethod(Payment);
        this.setPic(Pic);
        if(this.firstEdit){
            Confirmation.accountConfirmation(Fname+" "+Lname);
            new Activity(true,this.getId(), "Welcome to our system.");
            this.firstEdit=false;
            return DB.saveClient(this);
        }
        else{
        	Confirmation.editConfirmation(Email,Fname+" "+Lname);
        	new Activity(true,this.getId(), "you have updated your personal info.");
    		return DB.updateClient(this);
        }
    }
    
	public void sellCar(CarForSell sellThisCar){
        new Activity(true,this.getId(),"you have added a car for sell and waiting for admin approval");
        new Activity(false,this.getId()," has added a car to be sold by "+sellThisCar.getCarPrice()+" $");
        this.SellingNumber++;
        sellThisCar.waitingForApproval();
    }
	
    public void unbookCar(CarForRent car){
        Book BookedFrom=car.getBookedFrom();
        Book BookedTo=car.getBookedTo();
        float CurrentMoney=CurrentCar.getPricePerH()*(CurrentCarBookedTo.getFullHours()-CurrentCarBookedFrom.getFullHours());
        CurrentCar.unbookCar(CurrentCarBookedFrom,CurrentCarBookedTo);
        CurrentCar.getMyMoneyBack(this.getMyMethod(),CurrentMoney);
        Confirmation.confirmUnbookCar(this);
        if(this.MyDriver.get(IndexOfCar)!=null){
            this.unbookDriver(IndexOfCar);
        }
        this.RentedCar.remove(CurrentCar);
        Calendar cal= Calendar.getInstance();
        
        Report report = new Report(this.getFname(),"Has unbooked his car",CurrentMoney,cal.get(Calendar.DATE),cal.get(Calendar.MONTH)+1);
        this.RecentActivity.append(this.getFname()).append(" ").append("Has unbooked his car").append(" ").append(CurrentMoney).append("   ").append(cal.get(Calendar.DATE)).append("\\").append(cal.get(Calendar.MONTH)+1).append("\n");
        this.MyActivity.add(report);
        this.RentingNumber--;
    }
    

    public void rescheduleCarRent(int IndexOfCar) {
        this.unbookCar(IndexOfCar);
        new RentFilter(this); 
    }
    
    
    public static void forgetPassword(String Email){
        User ThisUser=FileManager.loadUsers(Email);
        Confirmation.forgetPasswordConfirmation(ThisUser);
        FileManager.saveUsers(ThisUser);
    }
    
    
    public void sendFeedback(Boolean ForCompany , String feedback , int IndexOfCar) {
        Calendar cal= Calendar.getInstance();
        Admin a=FileManager.loadAdmin();
        if(ForCompany){
            a.setFeedbackForCompany(this.getFname()+"\t"+this.getEmail()+"\n"+feedback+"\n"+cal.getTime()+"\n");
            FileManager.saveAdmin(a);
        }
        else{
            this.RentedCar.get(IndexOfCar).setFeedbackForCar(this.getFname()+"\t"+this.getEmail()+"\n"+feedback+"\n"+cal.getTime()+"\n");
        }
        this.RecentActivity.append(this.getFname()).append(" ").append("Has sent feedback").append(" ").append(cal.get(Calendar.DATE)).append("\\").append(cal.get(Calendar.MONTH)+1).append("\n");
    }
    
    public void rate(Boolean ForCompany ,float Rate,int IndexOfCar) {
        if(!ForCompany)
            this.RentedCar.get(IndexOfCar).addRate(Rate);
        else{
            Admin a=FileManager.loadAdmin();
            a.addRateForCompany(Rate);
            FileManager.saveAdmin(a);
        }
            
        
        Calendar cal= Calendar.getInstance();
        this.RecentActivity.append(this.getFname()).append(" ").append("Has rated").append(" ").append(cal.get(Calendar.DATE)).append("\\").append(cal.get(Calendar.MONTH)+1).append("\n");    
    }
    
    public void sendMessage(String Message){
        this.Message.sendNewMessage(this , Message);
        Calendar cal= Calendar.getInstance();
        this.RecentActivity.append(this.getFname()).append(" ").append("Has sent message").append(" ").append(cal.get(Calendar.DATE)).append("\\").append(cal.get(Calendar.MONTH)+1).append("\n");
    }
    
    public void rentThis(CarForRent RentedCar){
        this.RentedCar.add(RentedCar);
        Book CurrentCarBookedFrom=RentedCar.getBookedFrom().get(RentedCar.getBookedFrom().size());
        Book CurrentCarBookedTo=RentedCar.getBookedTo().get(RentedCar.getBookedTo().size());
        float CurrentMoeny=(CurrentCarBookedTo.getFullHours()-CurrentCarBookedFrom.getFullHours()) *RentedCar.getPricePerH();
        RentedCar.Pay(this.getMyMethod(),CurrentCarBookedTo.getFullHours()-CurrentCarBookedFrom.getFullHours());
        Confirmation.rentConfirmation(this.getEmail());
        this.RentingNumber++;
        
        Calendar cal= Calendar.getInstance();
        this.RecentActivity.append(this.getFname()).append(" ").append("Has rented car").append(" ").append(CurrentMoeny).append(" ").append(cal.get(Calendar.DATE)).append("\\").append(cal.get(Calendar.MONTH)+1).append("\n");
        Report report = new Report(this.getFname(),"Has rented car",CurrentMoeny,cal.get(Calendar.DATE),cal.get(Calendar.MONTH)+1);
        this.MyActivity.add(report);
    }
    
    
    public void buyThis(CarForSell BuyThisCar){
        BuyThisCar.Pay(this.getMyMethod());
        BuyThisCar.sellConfirmation();
        Confirmation.buyConfirmation(BuyThisCar);
        BuyThisCar.sold();
        Calendar cal= Calendar.getInstance();
        Report report = new Report(this.getFname(),"Has baught car",BuyThisCar.getCarPrice(),cal.get(Calendar.DATE),cal.get(Calendar.MONTH)+1);
        this.RecentActivity.append(this.getFname()).append(" ").append("Has baught car").append(" ").append(BuyThisCar.getCarPrice()).append(" ").append(cal.get(Calendar.DATE)).append("\\").append(cal.get(Calendar.MONTH)+1).append("\n");
        this.MyActivity.add(report);
        this.BuyingNumber++;
    }
    
    public ArrayList<CarForRent> getBookedCars(){
        Calendar cal= Calendar.getInstance();
        for(CarForRent CurrentCar:this.RentedCar){
            Book CurrentCarBookedFrom=CurrentCar.getBookedFrom().get(CurrentCar.getBookedFrom().size()); 
            if(CurrentCarBookedFrom.getDay()<cal.get(Calendar.DATE)||CurrentCarBookedFrom.getHour()<cal.get(Calendar.HOUR_OF_DAY)){
                this.MyDriver.remove(this.RentedCar.indexOf(CurrentCar));
                this.RentedCar.remove(CurrentCar);
            }
        }
        return this.RentedCar;
    }
    public ArrayList<Driver> getBookedDrivers(){
        return this.MyDriver;
    }
    public StringBuilder gotAMessage(){
        if(this.Message.FromUser())
            return this.Message.viewMessages();
        return null;
    }
    public Message viewMessages(){
        return this.Message;
    }
    public StringBuilder viewRecentActivity(){
        return this.RecentActivity;
    }
    public ArrayList<Report> viewLog(){
        return this.MyActivity;
    }
    void setDoYouWantADriver(Boolean Choice,int IndexOfCar){
        if(Choice){
            this.bookDriver(IndexOfCar);
        }
        else{
            this.MyDriver.add(IndexOfCar, null);
        }
    }
    
    private void saveActivity(int id, String string) {
		DB.saveUserActivity(id,string);
	}
}