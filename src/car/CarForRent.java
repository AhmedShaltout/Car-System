package car;

import java.util.ArrayList;

import payment.PaymentMethod;
import person.Admin;
import system.Book;
import system.Feedback;
import system.FileManager;

public class CarForRent extends Car {
    private static final long serialVersionUID = 1L;
    public static int CarForRentIDCreator;

    private ArrayList<Book> BookedFrom;
    private ArrayList<Book> BookedTo;
    private Feedback Feedback;
    
    private float PricePerH;
    private float Rate;
    private float howManyRated;
    private float totalRate;
    
    public CarForRent(Admin Shaltout) {
        this.setID(CarForRentIDCreator);
        CarForRent.CarForRentIDCreator++;
        
        this.setMyMoney(Shaltout.getMyMethod());
        
        this.BookedFrom=new ArrayList<>();
        this.BookedTo=new ArrayList<>();
        this.Feedback = new Feedback();
        this.PricePerH=0f;
        this.Rate=0f;
        this.howManyRated=0f;
        this.totalRate=0f;
    }

    public CarForRent() {
        //Never type anything here pls
    }
    
    public void setBookedFrom(Book BookFrom) {
        this.BookedFrom.add(BookFrom);
    }

    public void setBookedTo(Book BookTo) {
        this.BookedTo.add(BookTo);
    }

    public ArrayList<Book> getBookedFrom() {
        return this.BookedFrom;
    }

    public ArrayList<Book> getBookedTo() {
        return this.BookedTo;
    }

    public void setPricePerH(Float PricePerH) {
        this.PricePerH=PricePerH;
    }

    public float getPricePerH() {
        return this.PricePerH;
    }
    
    private void setRate(float Rate) {
        this.Rate=Rate;
    }
    
    public float getRate() {
        return this.Rate;
    }
    
    public void addRate(float Rate) {
        this.totalRate+=Rate;
        this.howManyRated++;
        this.setRate(this.totalRate/this.howManyRated);
    }
    
    public void setFeedbackForCar(String Feedback) {
        this.Feedback.addFeedback(Feedback);
    }
    public StringBuilder getFeedbackForCar(){
        return this.Feedback.getFeedback();
    }
    
    public void DeleteFeedbackForCar() {
        this.Feedback.deleteFeedback();
    }
    
    public void unbookCar(Book From, Book To) {
        CarForRent UnBook=FileManager.loadCarRent(this.getID());
        UnBook.BookedFrom.remove(From);
        UnBook.BookedTo.remove(To);
        FileManager.saveCarRent(UnBook);
    }
    
    public void Pay(PaymentMethod Method,int HowManyHours){
        Method.takeMoney((float)HowManyHours*this.PricePerH);
        this.getMyMoney().addBalance((float)HowManyHours*this.PricePerH);
    }

    public void getMyMoneyBack(PaymentMethod myMethod, float CurrentMoney) {
        myMethod.addBalance(CurrentMoney-this.PricePerH);
        this.getMyMoney().takeMoney(CurrentMoney-this.PricePerH);
    }
}