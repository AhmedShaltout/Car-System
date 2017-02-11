package car;

import java.io.Serializable;

import payment.PaymentMethod;
import system.About;

public abstract class Car implements Serializable{

    private String Image; //Path
    private int CarID;
    private String motor;
    private String Color;
    private float Speed;
    private float AvailableKM;
    private Short Doors;
    private String AboutCar;
    private String CarType;
    private String ModelType;
    private String Name;
    int 
    
    public Car() {
        this.motor=new Engine();
        this.AboutCar=new About();
        this.Color="";
        this.Speed=0;
        this.AvailableKM=0.0;
        this.Doors=0;
        this.CarType="";
        this.ModelType="";
        this.Name="";
        
    }
    public void editCarProfile(String Image,Engine motor,String Color,float Speed,double AvailableKM,int Doors,About AboutCar,String CarType,String ModelType,String Name){
        this.Image=Image;
        this.motor=motor;
        this.Speed=Speed;
        this.AvailableKM=AvailableKM;
        this.AboutCar=AboutCar;
        this.CarType=CarType;
        this.ModelType=ModelType;
    }
    public PaymentMethod getMyMoney() {
        return this.MyMoney;
    }
    
    public void setMyMoney(PaymentMethod MyMoney) {
        this.MyMoney = MyMoney;
    }
    
    public String getCarType() {
        return this.CarType;
    }
    
    public void setCarType(String CarType) {
        this.CarType = CarType;
    }
    
    public int getDoors() {
        return this.Doors;
    }
    
    public void setDoors(int Doors) {
        this.Doors = Doors;
    }
    
    public void setCarName(String Name){
        this.Name=Name;
    }
    public String getCarName(){
        return this.Name;
    }
    public void setImage(String Image){
        this.Image=Image;
    }
    public String getImage(){
        return this.Image;
    }
    public void addEngine(Engine NewEngine){
        this.motor=NewEngine;
    }
    public void addAbout(About NewAboutCar){
        this.AboutCar=NewAboutCar;
    }
    public void setColor(String NewColor) {
        this.Color=NewColor;
    }

    public void setSpeed(int NewSpeed) {
        this.Speed=NewSpeed;
    }

    public void setAvailableKM(int NewAvailableKM) {
        this.AvailableKM=NewAvailableKM;
    }

    public String getColor() {
        return this.Color;
    }

    public float getSpeed() {
        return this.Speed;
    }

    public double getAvailbleKM() {
        return this.AvailableKM;
    }

    public void setModelType(String NewModelType) {
        this.ModelType=NewModelType;
    }

    public String getModelType() {
        return this.ModelType;
    }
    public int getID(){
        return this.CarID;
    }
    public void setID(int ID){
        this.CarID=ID;
    }
    public About getAbout(){
        return this.AboutCar;
    }
    public Engine getEngine(){
        return this.motor;
    }
}
