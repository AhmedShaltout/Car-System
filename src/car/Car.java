package car;

public abstract class Car{

    private int carId;
    private String image;
    private String motor;
    private String color;
    private float speed;
    private Short doors;
    private String about;
    private String model;
    private String type;
    private String name;
    private int cc;
    
    public Car() {
        
    }
    
    public void editCarProfileForDB(String image,String motor,String color,float speed,Short doors,
    		String about,String model,String type,String name,int cc,int carId){
    	this.image=image;this.model=model;this.motor=motor;this.color=color;this.speed=speed;
    	this.about=about;this.model=model;this.type=type;this.name=name;this.cc=cc;this.carId=carId;
    }
    
    public void editCarProfile(String image,String motor,String color,float speed,Short doors,
    		String about,String model,String type,String name,int cc){
    	this.image=image;this.model=model;this.motor=motor;this.color=color;this.speed=speed;
    	this.about=about;this.model=model;this.type=type;this.name=name;this.cc=cc;
    }
    
	public int getCarId() {
		return this.carId;
	}
	
	public String getImage() {
		return this.image;
	}
	
	public String getMotor() {
		return this.motor;
	}
	
	public String getColor() {
		return this.color;
	}
	
	public float getSpeed() {
		return this.speed;
	}
	
	public Short getDoors() {
		return this.doors;
	}
	
	public String getAbout() {
		return this.about;
	}
	
	public String getModel() {
		return this.model;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getCc() {
		return this.cc;
	}
}
