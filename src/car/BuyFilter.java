package car;


import java.util.ArrayList;
import person.Person;
import system.FileManager;

public class BuyFilter{
    public BuyFilter(){
        
    }
    public BuyFilter(Person aThis) {
        
    }
    
    public ArrayList<CarForSell> Merge(float PriceFrom,float PriceTo,String Model){
        ArrayList<CarForSell> ModelList=filterByModel(Model);
        ArrayList<CarForSell> PriceList=filterByPrice(PriceFrom,PriceTo);
        ArrayList<CarForSell> Merg=new ArrayList<>();
        Merg.addAll(this.searchModel(Model, PriceList));
        Merg.addAll(this.searchPrice(PriceFrom, PriceTo, ModelList));
        return Merg;
    }


    public ArrayList<CarForSell> filterByPrice(float PriceFrom,float PriceTo) {
        ArrayList<CarForSell> Cars=FileManager.loadCarSell(true);
        ArrayList<CarForSell> List= searchPrice(PriceFrom,PriceTo,Cars);
        return List;
    }


    public ArrayList<CarForSell> filterByModel(String Model) {
        ArrayList<CarForSell> Cars=FileManager.loadCarSell(true);
        ArrayList<CarForSell> List=searchModel(Model,Cars);
        return List;
    }

    private ArrayList<CarForSell> searchModel(String Model, ArrayList<CarForSell> Cars) {
        ArrayList<CarForSell> Found=new ArrayList<CarForSell>();
        for(CarForSell Car:Cars){
            if(Car.getModelType().equalsIgnoreCase(Model))
                Found.add(Car);
        }
        for(CarForSell remove:Found){
            Cars.remove(remove);
        }
        FileManager.saveCarSell(Cars);
        return Found;
    }

    private ArrayList<CarForSell> searchPrice(float PriceFrom, float PriceTo, ArrayList<CarForSell> Cars) {
        ArrayList<CarForSell> Found=new ArrayList<CarForSell>();
        for(CarForSell Car:Cars){
            if(Car.getCarPrice()<=PriceTo&&Car.getCarPrice()>=PriceFrom){
                Found.add(Car);
            }
        }
        return Found;
    }
}
