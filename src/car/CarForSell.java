package car;

import payment.PaymentMethod;
import system.Confirmation;

public class CarForSell extends Car {

    public static int CarForSellIDCreator;

    private float CarPrice;
    private  Boolean Available;
    private String Email;
    private boolean Sold;

    public CarForSell() {
        this.setID(CarForSellIDCreator);
        CarForSell.CarForSellIDCreator++;
        this.CarPrice=0f;
        this.Email="";
        Sold=false;
    }

    public void setCarPrice(float Price) {
        this.CarPrice=Price;
    }

    public Float getCarPrice() {
        return this.CarPrice;
    }

    public void waitingForApproval() {
        Confirmation.WaitingForAdminApproval(this.Email);
    }

    public void setConfirmationEmail(String email) {
        this.Email=email;
    }

    public void sellConfirmation() {
         Confirmation.sellConfirmation(this.Email);
    }
    
    public void setAvailable(Boolean Assign){
        this.Available= Assign;
    }
    
    public Boolean getAvailable(){
        return this.Available;
    }
    
    public String getConfirmationEmail(){
        return this.Email;
    }
    public void Pay(PaymentMethod Method){
        Method.takeMoney(this.CarPrice);
        this.getMyMoney().addBalance(this.CarPrice);
    }

    public void sold() {
        this.Sold=true;
    }
    public boolean situation(){
        return this.Sold;
    }
}
