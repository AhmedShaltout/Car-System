package car;

public class CarForSell extends Car {
    private float carPrice;
    private  boolean available;
    private String email;
    
    public CarForSell(float carPrice,String email,boolean available){
    	this.carPrice=carPrice;this.email=email;this.available=available;
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
