package system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import car.CarForRent;
import car.CarForSell;
import person.Admin;
import person.Driver;
import person.User;

public class FileManager {
    private static final File UsersFile       =new File(           "Users.bin");
    private static final File DriversFile     =new File(         "Drivers.bin");
    private static final File CarForRentFile  =new File(  "CarForRentFile.bin");
    private static final File CarForSellFile  =new File(  "CarForSellFile.bin");
    private static final File ImportantNumbers=new File("ImportantNumbers.txt");
    private static final File AdminFile       =new File(           "Admin.bin");

    
    private static ObjectInputStream openToRead(File SomePath){
        try {
            return new ObjectInputStream(new FileInputStream(SomePath));
        } catch (IOException  ex) {
        }        
        return null;
    }
    private static ObjectOutputStream openToWrite(File Path){
        try{    
            return new ObjectOutputStream(new FileOutputStream(Path));
        } catch(IOException ex){
                FileManager.warningMessage(ex);
        }
        return null;
    }
    
    
    
    
    public static User loadUsers(String Email) {
        ArrayList<User> Users = FileManager.returnListOfUsers();
        if(Users.isEmpty())
            return null;
        else{
            User User=null;
            try{
                for (User user : Users) {
                    if(user.getEmail().equals(Email)){
                        User=user;
                        //Users.remove(User);
                    }
                }
        }
        catch(NullPointerException ex){}
//        FileManager.saveUsers(Users);
        return User;
        }
    }
    
    public static ArrayList<User> loadUsers(){
        return FileManager.returnListOfUsers();
    }
    
    public static void saveUsers(User user) {
        ArrayList<User> Users= new ArrayList<>();
        Users.add(user);
        FileManager.saveUsersDate(Users);
    }
    
    public static void saveUsers(ArrayList<User> Users){
        FileManager.saveUsersDate(Users);
    }
    
    private static void saveUsersDate(ArrayList<User> Users){
        ArrayList<User>ListOfUsers=FileManager.returnListOfUsers();
        ArrayList<User>SaveThese=new ArrayList<>();
        if(!ListOfUsers.isEmpty()){  
            for(User updateOld:ListOfUsers){
                for(User useNew:Users){
                    if(!updateOld.getEmail().equals(useNew.getEmail())){
                        SaveThese.add(updateOld);
                    }
                }
            }
        }
        SaveThese.addAll(Users);
        try (ObjectOutputStream OOS = FileManager.openToWrite(FileManager.UsersFile)) {
            OOS.writeObject(SaveThese);
        }
        catch (IOException ex){
                FileManager.warningMessage(ex);
        }
    }
    
    private static ArrayList<User> returnListOfUsers(){
        ArrayList<User> ListOfUsers=new ArrayList<>();
        try {
            ObjectInputStream OIS=FileManager.openToRead(FileManager.UsersFile);
            try {
                ListOfUsers=(ArrayList<User>) OIS.readObject();
            } catch (ClassNotFoundException | NullPointerException ex) {}
        } catch (IOException ex) {
                FileManager.warningMessage(ex);
        }
        return ListOfUsers;
    }
    
    
    
    
    public static CarForRent loadCarRent(int CarID){
        ArrayList<CarForRent> Cars = FileManager.returnListOfCarsForRent();
        if(Cars.isEmpty())
            return null;
        else{
            CarForRent Car=null;
            try{
                for (CarForRent car : Cars) {
                    if(car.getID()==CarID){
                        Car=car;
                    }
                }
            }
            catch(NullPointerException ex){
            }
            return Car;
        }
    }
    
    public static ArrayList<CarForRent> loadCarRent(String CarName) {
        ArrayList<CarForRent> CarsList = new ArrayList<>();
        ArrayList<CarForRent> Cars=FileManager.returnListOfCarsForRent();
        if(Cars.isEmpty())
            return null;
        else{
            try{
                for(CarForRent Car:Cars){
                    if(Car.getCarName().equalsIgnoreCase(CarName)){
                    CarsList.add(Car);
                    }
                }
            }
            catch(NullPointerException ex){}
            return CarsList;
        }
    }
    
    public static ArrayList<CarForRent> loadCarRent(){
        return FileManager.returnListOfCarsForRent();
    }
    
    public static void saveCarRent(CarForRent carForRent) {
        ArrayList<CarForRent> Cars=new ArrayList<>();
        Cars.add(carForRent);
        FileManager.saveCarsForRentData(Cars);
    }

    public static void saveCarRent(ArrayList<CarForRent> Cars) {
        FileManager.saveCarsForRentData(Cars);
    }
    
    private static void saveCarsForRentData(ArrayList<CarForRent> CarsForRent) {
        ArrayList<CarForRent> ListOfCars=returnListOfCarsForRent();
        ArrayList<CarForRent>SaveThese=new ArrayList<>();
        for(CarForRent updateOld:ListOfCars){
            for(CarForRent useNew:CarsForRent){
                if(updateOld.getID()!=useNew.getID()){
                    SaveThese.add(updateOld);
                }
            }
        }
        SaveThese.addAll(CarsForRent);
        try {
            ObjectOutputStream OOS=FileManager.openToWrite(FileManager.CarForRentFile);
            OOS.writeObject(SaveThese);
            OOS.close();
        } catch (IOException ex) {
                FileManager.warningMessage(ex);
        }
    }
    
	private static ArrayList<CarForRent> returnListOfCarsForRent() {
        ArrayList<CarForRent> ListOfCars=new ArrayList<>();
        try {
            ObjectInputStream OIS=FileManager.openToRead(FileManager.CarForRentFile);
            try {
                ListOfCars=(ArrayList<CarForRent>) OIS.readObject();
            } catch (ClassNotFoundException | NullPointerException ex) {
            }
        } catch (IOException ex) {
                FileManager.warningMessage(ex);
        }
        return ListOfCars;
    }
    
    
    
    
    public static CarForSell loadCarSell(int CarID) {
        ArrayList<CarForSell> Cars= FileManager.returnListOfCarsForSell();
        if(Cars.isEmpty())
            return null;
        else{
            try{
                for(CarForSell car:Cars){
                    if(car.getID()==CarID){
                        return car;
                    }
                }
            }
            catch(NullPointerException ex){}
            return null;
        }
    }
    
    public static ArrayList<CarForSell> loadCarSell(boolean True) {
        ArrayList<CarForSell> Cars= FileManager.returnListOfCarsForSell();
        if(Cars.isEmpty())
            return null;
        else{
            ArrayList<CarForSell> CarsList=new ArrayList<>();
            try{
                for(CarForSell car:Cars){
                if(car.getAvailable()==True&&car.situation()==false){
                    CarsList.add(car);
                    }
                }
            }catch(NullPointerException ex){}
            return CarsList;
        } 
    }
    public static void saveCarSell(CarForSell carForSell) {
        ArrayList<CarForSell> Cars=new ArrayList<>();
        Cars.add(carForSell);
        FileManager.saveCarsForSellData(Cars);
    }

    public static void saveCarSell(ArrayList<CarForSell> Cars) {
        FileManager.saveCarsForSellData(Cars);
    }
    private static ArrayList<CarForSell> returnListOfCarsForSell() {
        ArrayList<CarForSell> ListOfCars=new ArrayList<>();
        try {
            ObjectInputStream OIS=FileManager.openToRead(FileManager.CarForSellFile);
            try {
                ListOfCars=(ArrayList<CarForSell>) OIS.readObject();
            } catch (ClassNotFoundException | NullPointerException ex) {
            }
        } catch (IOException ex) {
                FileManager.warningMessage(ex);
        }
        return ListOfCars;
    }
    private static void saveCarsForSellData(ArrayList<CarForSell> Cars) {
        ArrayList<CarForSell> ListOfCars=returnListOfCarsForSell();
        ArrayList<CarForSell> SaveThese=new ArrayList<>();
        for(CarForSell updateOld:ListOfCars){
            for(CarForSell useNew:Cars){
                if(updateOld.getID()!=useNew.getID())
                    SaveThese.add(updateOld);
            }
        }
        SaveThese.addAll(Cars);
        try {
            ObjectOutputStream OOS=FileManager.openToWrite(FileManager.CarForSellFile);
            OOS.writeObject(SaveThese);
            OOS.close();
        } catch (IOException ex) {
                FileManager.warningMessage(ex);
        }
    }
    
    
    
    
    
    public static Driver loadDrivers(int DriverID) {
        ArrayList<Driver> Drivers=FileManager.returnListOfDrivers();
        if(Drivers.isEmpty())
            return null;
        else{
            Driver Driver=null;
            try{
                for(Driver driver:Drivers){
                    if(driver.getDriverID()==DriverID){
                        Driver=driver;
                    }
                }
            }catch(NullPointerException ex){}
        return Driver;
        }
    }

    public static ArrayList<Driver> loadDrivers() {
        return FileManager.returnListOfDrivers();
    }

    public static void saveDrivers(Driver This) {
        ArrayList<Driver> Drivers=new ArrayList<>();
        Drivers.add(This);
        FileManager.saveDriversData(Drivers);
    }
    public static void saveDrivers(ArrayList<Driver> list){
        FileManager.saveDriversData(list);
    }
    private static ArrayList<Driver> returnListOfDrivers() {
        ArrayList<Driver> DriversList=new ArrayList<>();
        try {
            ObjectInputStream OIS=FileManager.openToRead(FileManager.DriversFile);
            try {
                DriversList= (ArrayList<Driver>) OIS.readObject();
            } catch (ClassNotFoundException| NullPointerException  ex) {
            }
        } catch (IOException ex) {
                FileManager.warningMessage(ex);
        }
        return DriversList;
    }
    
    private static void saveDriversData(ArrayList<Driver> Drivers) {
        ArrayList<Driver> ListOfDrivers=returnListOfDrivers();
        ArrayList<Driver> SaveThese=new ArrayList<>();
        for(Driver removeOld:ListOfDrivers){
            for(Driver useNew:Drivers){
                if(removeOld.getDriverID()==useNew.getDriverID()){
                    SaveThese.add(removeOld);
                }
            }
        }
        SaveThese.addAll(Drivers);
        try {
            ObjectOutputStream OOS=FileManager.openToWrite(FileManager.DriversFile);
            OOS.writeObject(SaveThese);
            OOS.close();
        } catch (IOException ex) {
                FileManager.warningMessage(ex);
        }
    }

    
    
    public static ArrayList<Integer> importantNumbersReader(){
        ArrayList<Integer>IDList=new ArrayList<>();
        try{
            Scanner Cin =new Scanner(ImportantNumbers);
            while(Cin.hasNextInt()){
                IDList.add(Cin.nextInt());
        }
        } catch (FileNotFoundException ex) {
                FileManager.warningMessage(ex);
        }
        return IDList;
    }
    
    public static void importantNumbersWriter(ArrayList<Integer> IDList){
        try {
            PrintWriter BR=new PrintWriter(ImportantNumbers);
            for(int x:IDList){
                BR.println(x);
            }
            BR.close();
        } catch (IOException ex) {
                FileManager.warningMessage(ex);
        }
    }
    
    public static Admin loadAdmin(){
        ObjectInputStream OIS=FileManager.openToRead(FileManager.AdminFile);
        Admin admin=null;
        try {
            admin= (Admin) OIS.readObject();
        } catch (ClassNotFoundException |NullPointerException ex) {}
        catch(IOException ex){
            FileManager.warningMessage(ex);
        }
        return admin;
    }
    
    public static void saveAdmin(Admin admin){
        ObjectOutputStream OOS=FileManager.openToWrite(FileManager.AdminFile);
        try {
            OOS.writeObject(admin);
        } catch (IOException ex) {
            FileManager.warningMessage(ex);
        }
    }
    
    
    private static void warningMessage(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
    }

}