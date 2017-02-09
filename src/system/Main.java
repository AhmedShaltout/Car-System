package system;

import java.util.ArrayList;

import car.CarForRent;
import car.CarForSell;
import fx.LoginPag;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import person.Driver;

public class Main extends Application implements EventHandler<Event>{
    public static void main(String[] args){
        IDsGenerate();
        launch(args);
        IDSUpdate();
    }
    private static void IDsGenerate() {
        ArrayList<Integer>numbers=FileManager.importantNumbersReader();
        CarForSell.CarForSellIDCreator=numbers.get(0);
        CarForRent.CarForRentIDCreator=numbers.get(1);
        Driver.DriverIDCreator=numbers.get(2);
    }

    private static void IDSUpdate() {
        ArrayList<Integer>numbers=new ArrayList<>();
        numbers.add(CarForSell.CarForSellIDCreator);
        numbers.add(CarForRent.CarForRentIDCreator);
        numbers.add(Driver.DriverIDCreator);
        FileManager.importantNumbersWriter(numbers);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        new LoginPag();
    }

    @Override
    public void handle(Event event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
