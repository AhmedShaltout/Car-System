package person;

import java.io.Serializable;
import java.util.ArrayList;
import payment.PaymentMethod;
import system.Book;
import system.FileManager;

public class Driver implements Serializable{

    private static final long serialVersionUID = 1L;

    private int DriverID ;
    private final float Salary;
    private ArrayList <Book> StartingHour;
    private ArrayList <Book> FinishingHour;
    public static int DriverIDCreator;

    public Driver() {
        this.Salary = 50f;
        this.DriverID=DriverIDCreator;
        this.StartingHour=new ArrayList <>();
        this.FinishingHour=new ArrayList <>();
        Driver.DriverIDCreator++;
    }

    public void setStartingHour(Book Start) {
        this.StartingHour.add(Start);
    }

    public void setFinishingHour(Book Finish) {
        this.FinishingHour.add(Finish);
    }

    public int getDriverID() {
        return this.DriverID;
    }
    
    public float getDriverSalaryPerH() {
        return this.Salary;
    }

    void unBookThisOne(Driver MyDriver,Book TakenFrom,Book TakenTo) {
        Driver This=FileManager.loadDrivers(MyDriver.DriverID);
        This.StartingHour.remove(TakenFrom);
        This.FinishingHour.remove(TakenTo);
        FileManager.saveDrivers(This);
    }

    Driver getDriverForMe(Book From, Book To) {
        ArrayList<Driver> Drivers=FileManager.loadDrivers();
        for(Driver FoundOne:Drivers){
            if(FinishingHour.isEmpty())
                return FoundOne;
            else if(FinishingHour.size()==1){
                if(From.getDay()>=FoundOne.FinishingHour.get(0).getDay()&&To.getHour()>FoundOne.FinishingHour.get(0).getHour()){
                    return FoundOne;
                }
                else if(To.getDay()<=FoundOne.StartingHour.get(0).getDay()&&To.getHour()<FoundOne.StartingHour.get(0).getHour()){
                    return FoundOne;
                }
            }
            else{
                for(int Index=0;Index<FoundOne.FinishingHour.size()-1;Index++){
                    Book Start= FoundOne.StartingHour.get(Index);
                    Book Finish=FoundOne.FinishingHour.get(Index);
                    Book NextStart=FoundOne.StartingHour.get(Index+1);
                    if(Index==0){
                        if(To.getDay()<=Start.getDay()&&To.getHour()<Start.getHour()){
                            return FoundOne;
                        }
                    }
                    else if(Index==FoundOne.FinishingHour.size()-1){
                        if(From.getDay()>=Finish.getDay()&&From.getHour()>Finish.getHour())
                            return FoundOne;
                    }
                    else if(From.getDay()>=Finish.getDay()&&From.getHour()>Finish.getHour()&&To.getDay()<=NextStart.getDay()&&To.getHour()<NextStart.getHour()){
                        return FoundOne;
                    }
                } 
            }
        }
        return null;
    }

    public void Pay(PaymentMethod myMethod, float CurrentDriverMoney) {
        myMethod.takeMoney(CurrentDriverMoney);
    }

    void getMyMoneyBack(PaymentMethod myMethod, float CurrentMoney) {
        myMethod.addBalance(CurrentMoney-this.Salary);
    }
}