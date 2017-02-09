package system;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import car.CarForRent;
import car.CarForSell;
import person.User;

public class CarRentSystemException{
    
    public static void isUserThere(String Email) throws Exception{
        User user;
        user=FileManager.loadUsers(Email);
        if(user==null){
            throw new Exception("user not found");
        }
    }
    public static boolean isEmail(String email) {
           String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(email);
           return m.matches();
    }
    public static void warningMessage(String ex) {
            JOptionPane.showMessageDialog(null, ex, "Warning", JOptionPane.WARNING_MESSAGE);
    }

    public static void mismatchString(String Input) throws Exception{
        if(Input.length()==0)
            throw new Exception ("Can't leave a feild empty");
        else if(Input.charAt(0)==' ')
            throw new Exception("Can't Start feild With a Space");
    }
    public static void phoneNumberException(String phone) throws Exception{
        Long x=Long.parseLong(phone);
        if(phone.charAt(0)!='0'&&phone.charAt(1)!='1')
            throw new Exception("Phone number must start with 01");
        else if(x<1000000000||x>1999999999)
            throw new Exception("Enter a valid number");
        else if(phone.length()!=11)
            throw new Exception("Phone number must have 11 digits");
    }

    public static void UserNotNull(User NewUser) throws Exception {
        if(NewUser.getEmail()==null){
            throw new Exception("Trying to save nothing");
        }
    }
    public static void EmptyArrayRent(ArrayList<CarForRent> Cars) throws Exception{
        if(Cars.isEmpty()||Cars==null)
            throw new Exception("there are no cars");
    }
    public static void EmptyArraySell(ArrayList<CarForSell> Cars) throws Exception{
        if(Cars.isEmpty()||Cars==null)
            throw new Exception("there are no cars");
    }
    public static void CarRentFound(CarForRent car) throws Exception{
        if(car==null){
            throw new Exception("car not found");
        }
        else if(car.getCarName()==""){
            throw new Exception("car not found");
        }
    }
    public static void CarSellFound(CarForSell car) throws Exception{
        if(car==null){
            throw new Exception("car not found");
        }
        else if(car.getCarName()==""){
            throw new Exception("car not found");
        }
    }

	public static void mismatchNumber(String text) throws Exception {
        for(int index=0;index<text.length();index++){
            if(text.charAt(index)!='0'&&text.charAt(index)!='1'&&text.charAt(index)!='2'&&text.charAt(index)!='3'&&text.charAt(index)!='4'&&text.charAt(index)!='5'&&text.charAt(index)!='6'&&text.charAt(index)!='7'&&text.charAt(index)!='8'&&text.charAt(index)!='9'){
                throw new Exception("not number");
            }
        }
    }

    public static void mismatchCheckBox(int numbers) throws Exception{
        if(numbers==-1)
            throw new Exception("You need to choose from the menus");
    }

    public static void password(User LoginUser, String passConfirm1) throws Exception {
        if(!LoginUser.getPassword().equals(passConfirm1)){
            throw new Exception("Passwords arn't match");
        }
            
    }
}