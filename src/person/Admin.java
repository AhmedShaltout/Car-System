package person;

import java.util.ArrayList;
import java.util.Calendar;
import car.CarForRent;
import car.CarForSell;
import payment.Cash;
import system.About;
import system.Confirmation;
import system.Feedback;
import system.FileManager;
import system.Message;
import system.Report;


public class Admin extends Person {
    private  About AboutCompany;
    private float CompanyRate;
    private  float HowManyRated;
    private  float TotalRate;
    private Feedback feedback;
    private static final long serialVersionUID = 1L;
    public Admin(){
        this.setMyMethod(new Cash());
        this.CompanyRate=0f;
        this.HowManyRated=0f;
        this.TotalRate=0f;
        this.AboutCompany=new About();
        this.feedback=new Feedback();
        this.getMyMethod().takeMoney(100000000f);
    }
    public StringBuilder viewCompanyFeedback() {
        return this.feedback.getFeedback();
    }
    
    public StringBuilder viewCarFeedback(int CarID){
        CarForRent ViewThisFeedback =FileManager.loadCarRent(CarID);
        return ViewThisFeedback.getFeedbackForCar();
    }
    
    
    public void deleteAllCarFeedback() {
        ArrayList<CarForRent> Cars=FileManager.loadCarRent();
        for(CarForRent Car:Cars){
            Car.DeleteFeedbackForCar();
        }
        FileManager.saveCarRent(Cars);
    }
    

    public ArrayList<CarForSell> viewUnAvailableCars() {
        return FileManager.loadCarSell(false);
    }
    
    
    public void makeCarAvailable(CarForSell AcceptThis){
        CarForSell Accepted=FileManager.loadCarSell(AcceptThis.getID());
        Accepted.setAvailable(true);
        Confirmation.acceptedToSell(Accepted.getConfirmationEmail());
        FileManager.saveCarSell(Accepted);
    }
    
    public StringBuilder viewCarFeeedback(int ThisCarID) {
        CarForRent SomeCar=FileManager.loadCarRent(ThisCarID);
        StringBuilder Feed=SomeCar.getFeedbackForCar();
        FileManager.saveCarRent(SomeCar);
        return Feed;
    }

    
    public  float getCompanyRate() {
        return this.CompanyRate;
    }

    
    public StringBuilder showFeedBackForCompany(){
        return this.feedback.getFeedback();
    }
    
    
    public  void addRateForCompany(float RateCompany) {
        this.HowManyRated++;
        this.TotalRate+=RateCompany;
        this.CompanyRate=this.TotalRate/this.HowManyRated;
    }

    
    public  void setFeedbackForCompany(String Feedback){
        this.feedback.addFeedback(Feedback);
    }
    
    
    public void setAbout(String somthing){
        this.AboutCompany.setAbout(somthing);
    }
    
    
    public  String getAbout(){
        return this.AboutCompany.getAbout();
    }
    
    
    public void searchUserByEmail(String Email){
        User SomeUser= FileManager.loadUsers(Email);
        FileManager.saveUsers(SomeUser);
    }
    
    public float getBalance(){
        return this.getMyMethod().getBalance();
    }
    public ArrayList<Report> viewDailyReport(){
        Calendar cal=Calendar.getInstance();
        ArrayList<Report> AllReports=this.viewMonthlyReport();
            for(Report log:AllReports){
                if(log.getDay()==cal.get(Calendar.DATE))
                    AllReports.add(log);
            }
        return AllReports;
    }
    public ArrayList<Report> viewMonthlyReport(){
        Calendar cal=Calendar.getInstance();
        ArrayList<User> AllUsers=FileManager.loadUsers();
        ArrayList<Report> AllReports=new ArrayList<>();
        for(User thisUser:AllUsers){
            ArrayList<Report>thisUserLog=thisUser.viewLog();
            for(Report log:thisUserLog){
                if(log.getDay()==cal.get(Calendar.MONTH)+1)
                    AllReports.add(log);
            }
        }
        FileManager.saveUsers(AllUsers);
        return AllReports;
    }
    public ArrayList<Message> viewNewMessages(){
        ArrayList<Message> Messages=new ArrayList<>();
        ArrayList<User> AllUsers=FileManager.loadUsers();
        for(User thisUser:AllUsers){
            if(thisUser.viewMessages().FromUser()){
                Messages.add(thisUser.viewMessages());
            }
        }
        FileManager.saveUsers(AllUsers);
        return Messages;
    }
    public ArrayList<Message> viewAllMessages(){
        ArrayList<Message> AllMessages=viewNewMessages();
        ArrayList<User> AllUsers=FileManager.loadUsers();
        for(User thisUser:AllUsers){
            if(!thisUser.viewMessages().FromUser()){
                AllMessages.add(thisUser.viewMessages());
            }
        }
        FileManager.saveUsers(AllUsers);
        return AllMessages;
    }
}