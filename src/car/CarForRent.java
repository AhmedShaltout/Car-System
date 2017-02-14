package car;

public class CarForRent extends Car {
    private float pricePerH;
    private float rate;
    private String from;
    private String to;
    //for the database.
    public CarForRent(float pricePerH,float rate,String from,String to){
    	this.pricePerH=pricePerH;this.rate=rate;this.from=from;this.to=to;
    }
    
    public CarForRent(float pricePerH,float rate){
    	this.pricePerH=pricePerH;this.rate=rate;
    }

	public float getPricePerH() {
		return this.pricePerH;
	}

	public float getRate() {
		return this.rate;
	}

	public String getFrom() {
		return this.from;
	}

	public String getTo() {
		return this.to;
	}
    
}