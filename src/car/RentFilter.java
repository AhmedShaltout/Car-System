package car;

import java.util.ArrayList;

import person.User;
import system.Book;
import system.FileManager;

public class RentFilter{
    public RentFilter(){
        
    }
    public RentFilter(User aThis) {
        
    }
    public ArrayList<CarForRent> Merge(float PriceFrom,float PriceTo,Book From,Book To){
      ArrayList<CarForRent> PriceList = filterByPrice(PriceFrom,PriceTo);
      ArrayList<CarForRent> DateList = filterByDate(From,To);
      ArrayList<CarForRent> Merg = new ArrayList<>();
      Merg.addAll(this.searchDate(From, To, PriceList));
      Merg.addAll(this.searchPrice(PriceFrom, PriceTo, DateList));
      return Merg;
    }
    public ArrayList<CarForRent> Merge(String Model,Book From,Book To){
     ArrayList<CarForRent> ModelList = filterByModel(Model);
     ArrayList<CarForRent> DateList = filterByDate(From,To);
     ArrayList<CarForRent> Merg=new ArrayList<>();
     Merg.addAll(this.searchDate(From, To, ModelList));
     Merg.addAll(this.searchModel(Model, DateList));
     return Merg;
    }
    public ArrayList<CarForRent> Merge(float PriceFrom,float PriceTo,String Model) {
    ArrayList<CarForRent> ModelList = filterByModel(Model);
    ArrayList<CarForRent> PriceList = filterByPrice(PriceFrom,PriceTo);
    ArrayList<CarForRent> Merg=new ArrayList<>();
    Merg.addAll(this.searchModel(Model, PriceList));
    Merg.addAll(this.searchPrice(PriceFrom, PriceTo, ModelList));
    return Merg;
    }
     
    public ArrayList<CarForRent> Merge(float PriceFrom,float PriceTo,String Model,Book From,Book To){
        ArrayList<CarForRent> Merg=new ArrayList<>();
        ArrayList<CarForRent> MergPriceModel=Merge(PriceFrom,PriceTo,Model);
        ArrayList<CarForRent> MergPriceDate=Merge(PriceFrom,PriceTo,From,To);
        ArrayList<CarForRent> MergModelDate=Merge(Model,From,To);
        Merg.addAll(this.searchDate(From, To, MergPriceModel));
        Merg.addAll(this.searchModel(Model, MergPriceDate));
        Merg.addAll(this.searchPrice(PriceFrom, PriceTo, MergModelDate));
        return Merg;
    }
    
    public ArrayList<CarForRent> filterByPrice(float PriceFrom,float PriceTo) {
        ArrayList<CarForRent> Cars=FileManager.loadCarRent();
        ArrayList<CarForRent> PriceList=searchPrice(PriceFrom,PriceTo,Cars);
        return PriceList;
    }
    public ArrayList<CarForRent> filterByModel(String Model) {
        ArrayList<CarForRent> Cars=FileManager.loadCarRent();
        ArrayList<CarForRent> ModelList=searchModel(Model,Cars);
        return ModelList;
        
    }
    
    public ArrayList<CarForRent> filterByDate(Book From,Book To){
        ArrayList<CarForRent> Cars=FileManager.loadCarRent();
        ArrayList<CarForRent>DateList=searchDate(From,To,Cars);
        return DateList;
    }
    private ArrayList<CarForRent>searchDate(Book From,Book To,ArrayList<CarForRent> Cars){
        ArrayList<CarForRent> cfr = new ArrayList<>();
        for(CarForRent FoundOne:Cars){
            if(FoundOne.getBookedTo().isEmpty()){
                cfr.add(FoundOne);
            }
            else if(FoundOne.getBookedTo().size()==1){
                if(From.getFullHours()>FoundOne.getBookedTo().get(0).getFullHours()||To.getFullHours()<FoundOne.getBookedFrom().get(0).getFullHours()){
                    cfr.add(FoundOne);
                }
            }
            else{
                int size=FoundOne.getBookedFrom().size();
                for(int Index=0;Index<size;Index++){
                    Book Finish=FoundOne.getBookedTo().get(Index);
                    if(Index==size-1){
                        if(From.getFullHours()>Finish.getFullHours()){
                            cfr.add(FoundOne);
                        }
                    }
                    else{
                        Book NextStart=FoundOne.getBookedFrom().get(Index+1);
                        if(From.getFullHours()>Finish.getFullHours()&&To.getFullHours()<NextStart.getFullHours()){
                            cfr.add(FoundOne);
                        }
                        if(Index==0){
                            Book Start= FoundOne.getBookedFrom().get(Index);
                            if(To.getFullHours()<Start.getFullHours()){
                                cfr.add(FoundOne);
                            }
                        }
                    }
                } 
            }
        }
        return cfr;
    }
    private ArrayList<CarForRent>searchModel(String Model,ArrayList<CarForRent> Cars){
        ArrayList<CarForRent> Found=new ArrayList<>();
        for(CarForRent Car:Cars){
            if(Car.getModelType().equalsIgnoreCase(Model))
                Found.add(Car);
        }
        return Found;
    }
    private ArrayList<CarForRent>searchPrice(float PriceFrom,float PriceTo,ArrayList<CarForRent> Cars){
        ArrayList<CarForRent> Found=new ArrayList<>();
        for(CarForRent Car:Cars){
            if(Car.getPricePerH()<=PriceTo&&Car.getPricePerH()>=PriceFrom){
                Found.add(Car);
            }
        }
        return Found;
    }
}
