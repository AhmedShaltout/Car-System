package person;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import car.CarForRent;
import car.CarForSell;
import system.Activity;
import system.Confirmation;
import system.DB;


public class User{
    private int id;
    private String fName;
    private String lName;
    private boolean gender;
    private String address;
    private String state;
    private String email;
    private String password;
    private String phoneNumber;
    private String pic;
    private int rentN;
    private int buyN;
    private int sellN;
    
	public User(String Fname,String Lname, boolean Gender, String Address ,String State,
			String Email,String Password ,String PhoneNumber,String pic) {
        this.address=Address; this.email=(Email);
        this.fName=(Fname);this.gender=(Gender);
        this.lName=(Lname);this.password=(Password);
        this.phoneNumber=(PhoneNumber);
        this.state=(State);this.pic=(pic);
		this.rentN=0;
		this.buyN=0;
		this.sellN=0;
        Confirmation.accountConfirmation(this.email,Fname+" "+Lname);
        DB.saveClient(this);
    }
	
	public User(int id,String fName,String lName,boolean gender,String address,String state,String email,
			String password,String phoneNumber,String pic,int rentN,int buyN,int sellN){
		this.address= address;this.email=email;this.fName=fName;
		this.gender= gender;this.lName=lName;this.password=password;
		this.phoneNumber=phoneNumber;this.state=state;this.pic=pic;
		this.id=id;this.rentN=rentN;this.buyN=buyN;this.sellN=sellN;
	}
	
    private void updateUser(User user){
    	new Thread(new Runnable() {
			@Override
			public void run() {
		    	DB.updateClient(user);
			}
		}).start();
    }
	public boolean sellCar(CarForSell sellThisCar){
		if(DB.addCarForSell(sellThisCar)){
	        saveA(this.id,"you have added a car for sell and waiting for admin approval");
	        saveR(""+this.id+" has added a car to be sold by "+sellThisCar.getCarPrice()+" $");
	        this.sellN++;
	        Confirmation.waitingForApproval(this.email);
	        updateUser(this);
	        return true;
		}
        return false;
    }
	public boolean bookCar(int carId,String from, String to){
		if(DB.rentThis(this.id,carId,from,to)){
			saveA(this.id, "you have rented a new car from: "+from+"  to: "+to+"");
			saveR(""+this.id+" has rented a car ("+id+") from: "+from+"  to: "+to+"");
			Confirmation.rent(this.email);
			this.rentN++;
			updateUser(this);
			return true;
		}
		return false;
	}
    public boolean unbookCar(int carID,String from,String to){
    	if(DB.unbookCar(this.id,carID,from,to)){
    		this.rentN--;
    		saveA(this.id, "you have unbooked a car sucessfuly");
    		saveR(""+this.id+" has unbooked car:("+carID+")");
    		Confirmation.unbook(this.email);
    		updateUser(this);
    		return true;
    	}
    	return false;
    }
    
    public boolean rescheduleCarRent(int carId,String Ufrom,String Uto,String Bfrom, String Bto) {
    	if(DB.reschaduleCar(this.id,carId,Ufrom,Uto,Bfrom,Bto)){
    		saveA(this.id,"you have rescedualed your book sucessfuly");
    		saveR(""+this.id+" has rescheduled a rent car(+carId+) to "+Bfrom+""+Bto+"");
    		Confirmation.reschedule(this.email);
    		return true;
    	}
    	return false;
    }
    
    
    public static long forgetPassword(String email){
    	if(DB.exists(email)){
    		Long s= new Random(10000000).nextLong()+10000000;
    		Confirmation.sendPassword(email,s);
    		return s;
    	}
    	return 0;
    }
    
    public boolean carFeedback(int carId,String feedback){
    	if(DB.addCarFeedback(this.email,carId,feedback,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))){
    		saveA(this.id,"your feedback for the car was sent successfuly");
    		return true;
    	}
    	return false;
    }
    
    public boolean companyFeedback(String feedback){
    	if(DB.addCompanyFeedback(this.email,feedback,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))){
    		saveA(this.id, "your feedback for the company was sent successfuly");
    		return true;
    	}
    	return false;
    }
    
    public boolean carRate(int carId,float rate){
    	if(DB.addCarRate(carId,rate)){
    		saveA(this.id, "your rate for the car was added successfuly");
    		return true;
    	}
    	return false;
    }
    
    public boolean companyRate(float rate){
    	if(DB.addCompanyRate(rate)){
    		saveA(this.id, "your rate for the company was added successfly");
    		return true;
    	}
    	return false;
    }
    
    public boolean buyThis(int carId){
    	String email;
    	if((email=DB.buyCar(carId))!=null){
    		Confirmation.sold(email,carId);
    		Confirmation.baught(this.email);
    		buyN++;
    		saveA(this.id, "you have baught a car congratulations");
    		saveR(""+this.id+" has baught a car("+carId+")");
    		DB.updateClient(this);
    		return true;
    	}
    	return false;
    }

    public ArrayList<CarForRent> bookedCars(){
    	return DB.myBookedCars(this.id);
    }
    
    public ArrayList<Activity> recentActivity(){
    	return DB.userActivity(this.id);
    }
    
    public static ArrayList<CarForRent> rentFilter(String carNameOrModel , boolean byName){
    	if(byName)
    		return DB.findRentByName(carNameOrModel);
    	return DB.findRentByModel(carNameOrModel);
    }
    public static ArrayList<CarForRent> rentFilterDate(String from ,String to){
    	return DB.findRentByDate(from,to);
    }
    public static ArrayList<CarForRent> rentFilter(String carName,String carModel){
    	return DB.findRentByNameAndModel(carName,carModel);
    }
    public static ArrayList<CarForRent> rentFilter(String carNameOrModel , boolean byName,String from ,String to){
    	if(byName)
    		return DB.findRentByNameAndDate(carNameOrModel,from,to);
    	return DB.findRentByModelAndDate(carNameOrModel,from,to);
    }
    public static ArrayList<CarForRent> rentFilter(String carName,String carModel,String from ,String to){
    	return DB.findRentByNameModelAndDate(carName,carModel,from,to);
    }
    public static ArrayList<CarForSell> sellFilter(String carNameOrModel , boolean byName){
    	if(byName)
    		return DB.findSellByName(carNameOrModel);
    	return DB.findSellByModel(carNameOrModel);
    }
    public static ArrayList<CarForSell> sellFilter(String carName,String carModel){
    	return DB.findSellByNameAndModel(carName,carModel);
    }
    
    private void saveA(int id,String action){
		new Thread(new Runnable() {
		@Override
		public void run() {
			DB.saveActivity(id,action,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		}
		}).start();
		
	}
	private void saveR(String  action){
		new Thread(new Runnable() {
			@Override
			public void run() {
				DB.saveReport(action,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			}
		}).start();
	}
    
    
    public int getId() {
		return this.id;
	}
    
	public String getfName() {
		return this.fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
		updateUser(this);
		saveA(this.id, "you have updated your first name to be "+fName+"");
	}

	public String getlName() {
		return this.lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
		updateUser(this);
		saveA(this.id, "you have updated your last name to be "+lName+"");
	}

	public boolean getGender() {
		return this.gender;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
		updateUser(this);
		saveA(this.id, "you have updated your address to be "+address+"");
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
		updateUser(this);
		saveA(this.id, "you have updated your state to be "+state+"");
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
		updateUser(this);
		saveA(this.id, "you have updated your Email to be "+email+"");
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
		updateUser(this);
		saveA(this.id, "you have updated your password");
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		updateUser(this);
		saveA(this.id, "you have updated your phone number to be "+phoneNumber+"");
	}

	public String getPic() {
		return this.pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
		updateUser(this);
		saveA(this.id, "you have updated your profile picture");
	}

	public int getRentN() {
		return this.rentN;
	}

	public int getBuyN() {
		return this.buyN;
	}
	
	public int getSellingNumber() {
		return this.sellN;
	}

	public void setSellN(int sellN) {
		this.sellN = sellN;
	}
}