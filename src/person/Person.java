package person;

import java.io.Serializable;
import java.util.ArrayList;

import car.CarForRent;
import car.CarForSell;
import payment.PaymentMethod;
import system.CarRentSystemException;
import system.FileManager;

public abstract class Person implements Serializable 
{
    private static final long serialVersionUID = 1L;
    private String Fname;
    private String Lname;
    private String Gender;
    private String Address;
    private String State;
    private String Email;
    private String Password;
    private String PhoneNumber;
    private PaymentMethod MyMethod;
    private String Pic;
    public Person() 
    {
    
    }

    public String getPic() {
        return this.Pic;
    }

    public void setPic(String Pic) {
        this.Pic = Pic;
    }

    public PaymentMethod getMyMethod() {
        return this.MyMethod;
    }

    public void setMyMethod(PaymentMethod MyMethod) {
        this.MyMethod = MyMethod;
    }
    public void setFname(String Fname)
    {
        this.Fname=Fname;
    }
    public void setLname(String Lname)
    {
        this.Lname=Lname;
    }
    public void setPhoneNumber(String PhoneNumber)
    {
        this.PhoneNumber=PhoneNumber;
    }
    public void setGender(String Gender)
    {
        this.Gender=Gender;
    }
    public void setAddress(String Address)
    {
        this.Address=Address;
    }
    public void setState(String State) 
    {
        this.State=State;
    }
    public void setEmail(String Email)
    {
        this.Email=Email;
    }
    public void setPassword(String Password)
    {
        this.Password=Password;
    }
    public String getFname() 
    {
        return this.Fname;
    }
    public String getLname() 
    {
        return this.Lname;
    }
    public String getPhoneNumber() 
    {
        return this.PhoneNumber;
    }
    public String getGender()
    {
        return this.Gender;
    }
    public String getAddress()
    {
        return this.Address;
    }
    public String getState()
    {
        return this.State;
    }
    public String getEmail()
    {
        return this.Email;
    }
    public String getPassword()
    {
        return this.Password;
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
