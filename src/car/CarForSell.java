package car;

import system.DB;

public class CarForSell extends Car {
    private float carPrice;
    private  boolean available;
    private String email;

    public CarForSell() {
    	
    }
    
    //for the DB class.
    public CarForSell(float carPrice,String email,boolean available){
    	this.carPrice=carPrice;this.email=email;this.available=available;
    }
    
    public boolean editCarForSellProfile(float carPrice,String email,boolean available){
    	this.carPrice=carPrice;this.email=email;this.available=available;
    	return DB.saveCarForSell(this);
    }
    
	public float getCarPrice() {
		return this.carPrice;
	}
	public boolean getAvailable() {
		return this.available;
	}
	public String getEmail() {
		return this.email;
	}
}
