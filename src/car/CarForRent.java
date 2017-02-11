package car;

import system.DB;

public class CarForRent extends Car {
    private float pricePerH;
    private float rate;
    
    //for the database.
    public CarForRent(float pricePerH,float rate){
    	this.pricePerH=pricePerH;this.rate=rate;
    }
    
    public CarForRent(){
    	
    }
    
    public boolean editCarForRentProfile(float pricePerH,float rate){
    	this.pricePerH=pricePerH;this.rate=rate;
    	return DB.saveCarForRent(this);
    }

	public float getPricePerH() {
		return this.pricePerH;
	}

	public float getRate() {
		return this.rate;
	}
    
}