package person;

import java.util.ArrayList;
import car.CarForRent;
import car.CarForSell;
import payment.PaymentMethod;
import system.CarRentSystemException;
import system.FileManager;

public abstract class Person 
{
	private int id;
    private String fName;
    private String lName;
    private boolean gender;
    private String address;
    private String state;
    private String email;
    private String password;
    private String phoneNumber;
    private PaymentMethod paymentMethod;
    private String Pic;
    
    public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getfName() {
		return this.fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return this.lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public boolean getGender() {
		return this.gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return this.address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getState() {
		return this.state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public PaymentMethod getPaymentMethod() {
		return this.paymentMethod;
	}
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getPic() {
		return this.Pic;
	}
	public void setPic(String pic) {
		this.Pic = pic;
	}
	public ArrayList<CarForRent> searchCarRent(String CarName){
        return FileManager.loadCarRent(CarName);
    }
    public ArrayList<CarForSell> searchCarSell(String  CarName){
        ArrayList<CarForSell> FoundForSell=new ArrayList<>();
        try {
            CarRentSystemException.EmptyArraySell(FileManager.loadCarSell(true));
            ArrayList<CarForSell> FoundCars=FileManager.loadCarSell(true);
            for(CarForSell car:FoundCars){
                if(car.getCarName().equalsIgnoreCase(CarName))
                    FoundForSell.add(car);
            }
        } catch (Exception ex) {}
        return FoundForSell;
    }
}
