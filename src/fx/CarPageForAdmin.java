package GUI;

import CarRentSystem.FileManager;
import Cars.CarForRent;
import Cars.CarForSell;
import Persons.Admin;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;

public class CarPageForAdmin {
    private Stage window;
    private CarForRent carForRent;
    private CarForSell carForSell;
    private Scene scene;
    private BorderPane layout;
    private HBox header,footer;
    private Label logo,copyRight,userPersonalImageLabel;
    private Label carDoors,speedKM,carColor,carType,carName,carModel,availableKM,aboutCar,motor,carImageLabel,carCylinder,carPrice;
    private Button addCarForSell,addCarForRent,about,message,deleteCar;
    private TextField search;
    private GridPane glass;
    private VBox right,left;
    private Region rightRegion,leftRegion;
    private Admin Shaltout;
    private Image pic;
    private ImageView Pic;
    public CarPageForAdmin(Admin Shaltout,CarForRent carForRent) {
        this.Shaltout=Shaltout;
        this.carForRent=carForRent;
        this.carForSell=carForSell;
        this.Shaltout = FileManager.loadAdmin();
        headerAndFooterForAdmin();
        
        carDoors=new Label("Number Of Doors: "+carForRent.getDoors());
        speedKM=new Label("Speed/ km"+carForRent.getSpeed());
        carColor=new Label("Car Color: "+carForRent.getColor());
        carType=new Label("Car Type: "+carForRent.getCarType());
        carName=new Label("Car Name: "+carForRent.getCarName());
        carModel=new Label("Car Model: "+carForRent.getModelType());
        availableKM=new Label("Available KMs: "+carForRent.getAvailbleKM());
        aboutCar=new Label("About: ");/////+++++car.getAbout()???where is it
        motor=new Label("Motor: ");/////+++++car.getMotor()???where is it
        deleteCar=new Button("Delete This Car");
        deleteCar.setId("prev");
        carImageLabel=new Label();
        pic=new Image(getClass().getResourceAsStream(carForRent.getImage()));
        Pic=new ImageView(pic);
        Background back=new Background(new BackgroundImage(pic, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
        carImageLabel.setMinHeight(200);
        carImageLabel.setMinWidth(300);
        
        glass.setConstraints(carImageLabel, 0, 0);
        glass.setConstraints(deleteCar, 1, 0);
        glass.setConstraints(carDoors, 0, 1);
        glass.setConstraints(speedKM, 1, 1);
        glass.setConstraints(carColor, 0, 2);
        glass.setConstraints(carType, 1, 2);
        glass.setConstraints(carName, 0, 3);
        glass.setConstraints(carModel, 1, 3);
        glass.setConstraints(availableKM, 0, 4);
        glass.setConstraints(motor, 1, 4);
        glass.setConstraints(carCylinder, 0, 5);
        glass.setConstraints(carPrice, 1, 5);
        glass.setConstraints(about, 0, 6);
        
        glass.getChildren().addAll(carImageLabel,carDoors,speedKM,carColor,carType,carName,
                                   carModel,availableKM,motor,about,carPrice,carCylinder);
        
        glass=new GridPane();
        glass.setId("glass");
        glass.setMaxWidth(1300);
        glass.setMaxHeight(530);
        rightRegion=new Region();
        rightRegion.setMinWidth(150);
        leftRegion=new Region();
        leftRegion.setMinWidth(150);
        left=new VBox();
        left.setId("header");
        left.getChildren().add(leftRegion);
        right=new VBox();
        right.setId("header");
        right.getChildren().add(rightRegion);
        layout=new BorderPane();
        layout.setTop(header);
        layout.setBottom(footer);
        layout.setRight(right);
        layout.setLeft(left);
        layout.setId("layout");
        scene=new Scene(layout);
        scene.getStylesheets().add(this.getClass().getResource("stylesheet.css").toExternalForm());
        window=new Stage();
        window.setScene(scene);
        window.show();
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                event.consume();
                closeProgram(Shaltout);
            }
        });
    }
    public CarPageForAdmin(Admin Shaltout, CarForSell carForSell)
    {
        this.Shaltout=Shaltout;
        this.carForSell=carForSell;
        this.carForSell=carForSell;
        this.Shaltout = FileManager.loadAdmin();
        headerAndFooterForAdmin();
        
        carDoors=new Label("Number Of Doors: "+carForSell.getDoors());
        speedKM=new Label("Speed/ km"+carForSell.getSpeed());
        carColor=new Label("Car Color: "+carForSell.getColor());
        carType=new Label("Car Type: "+carForSell.getCarType());
        carName=new Label("Car Name: "+carForSell.getCarName());
        carModel=new Label("Car Model: "+carForSell.getModelType());
        availableKM=new Label("Available KMs: "+carForSell.getAvailbleKM());
        aboutCar=new Label("About: ");/////+++++car.getAbout()???where is it
        motor=new Label("Motor: ");/////+++++car.getMotor()???where is it
        deleteCar=new Button("Delete This Car");
        deleteCar.setId("prev");
        carImageLabel=new Label();
        pic=new Image(getClass().getResourceAsStream(carForSell.getImage()));
        Pic=new ImageView(pic);
        Background back=new Background(new BackgroundImage(pic, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
        carImageLabel.setMinHeight(200);
        carImageLabel.setMinWidth(300);
        
        glass.setConstraints(carImageLabel, 0, 0);
        glass.setConstraints(deleteCar, 1, 0);
        glass.setConstraints(carDoors, 0, 1);
        glass.setConstraints(speedKM, 1, 1);
        glass.setConstraints(carColor, 0, 2);
        glass.setConstraints(carType, 1, 2);
        glass.setConstraints(carName, 0, 3);
        glass.setConstraints(carModel, 1, 3);
        glass.setConstraints(availableKM, 0, 4);
        glass.setConstraints(motor, 1, 4);
        glass.setConstraints(carCylinder, 0, 5);
        glass.setConstraints(carPrice, 1, 5);
        glass.setConstraints(about, 0, 6);
        
        glass.getChildren().addAll(carImageLabel,carDoors,speedKM,carColor,carType,carName,
                                   carModel,availableKM,motor,about,carPrice,carCylinder);
        
        glass=new GridPane();
        glass.setId("glass");
        glass.setMaxWidth(1300);
        glass.setMaxHeight(530);
        rightRegion=new Region();
        rightRegion.setMinWidth(150);
        leftRegion=new Region();
        leftRegion.setMinWidth(150);
        left=new VBox();
        left.setId("header");
        left.getChildren().add(leftRegion);
        right=new VBox();
        right.setId("header");
        right.getChildren().add(rightRegion);
        layout=new BorderPane();
        layout.setTop(header);
        layout.setBottom(footer);
        layout.setRight(right);
        layout.setLeft(left);
        layout.setId("layout");
        scene=new Scene(layout);
        scene.getStylesheets().add(this.getClass().getResource("stylesheet.css").toExternalForm());
        window=new Stage();
        window.setScene(scene);
        window.show();
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                event.consume();
                closeProgram(Shaltout);
            }
        });
    }
    public void closeProgram(Admin LogOut){
        boolean result=confirmExit.display("Title", "Are you sure close the Application?!");
        if(result){
            window.close();
            FileManager.saveAdmin(LogOut);
        }
    }
    public void headerAndFooterForAdmin(){
        header=new HBox();
        header.setPadding(new Insets(5,5,5,5));
        header.setSpacing(10);
        header.setId("header");
        logo=new Label();
        logo.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("logo.png"))));
        addCarForSell=new Button("Add Car For Sell");
        addCarForSell.setId("AdminmainOrders");
        addCarForRent=new Button("Add Car For Rent");
        addCarForRent.setId("AdminmainOrders");
        addCarForSell.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
                new SellCar(Shaltout);
            }
        });
        addCarForRent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
                
            }
        });
        about=new Button("About");
        about.setId("AdminmainOrders");
        addCarForSell.setPadding(new Insets(25,5,25,5));
        addCarForRent.setPadding(new Insets(25,5,25,5));
        about.setPadding(new Insets(25,5,25,5));
        search=new TextField();
        search.setId("search");
        message=new Button();
        message.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("wonderful.png"))));
        Region region= new Region();
        region.resize(700, 100);
        region.setMinWidth(100);
        Image image=new Image(getClass().getResourceAsStream("male2.png"));
        ImageView imageView=new ImageView(image);
        userPersonalImageLabel=new Label();
        userPersonalImageLabel.setGraphic(imageView);
        Menu service=new Menu("Samaa");
        service.setId("service");
        MenuItem dailyReport=new MenuItem("Daily Report...");
        MenuItem monthlyReport=new MenuItem("Monthly Report...");
        MenuItem unavailableCars=new MenuItem("Show Unavailable Cars...");
        MenuItem addCarForRentItem=new MenuItem("Add Car For Rent...");
        MenuItem addCarForSellItem=new MenuItem("Add Car For Sell...");
        MenuItem aboutItem=new MenuItem("About...");
        MenuItem viewAllCars=new MenuItem("View All Cars...");
        MenuItem viewCompanyFeedback=new MenuItem("View Company Feedback...");
        MenuItem viewCompanyRate=new MenuItem("Our Rate: "+Shaltout.getCompanyRate());
        MenuItem companyBalance=new MenuItem("Our Balance Now: "+Shaltout.getBalance()+" $");
        companyBalance.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                JOptionPane.showMessageDialog(null, "Our Balance Right Now Is "+Shaltout.getBalance()+" $", "From the bank", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        MenuItem settings=new MenuItem("Settings");
        MenuItem exit=new MenuItem("Log Out");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
                FileManager.saveAdmin(Shaltout);
                new LoginPag();
            }
        });
        SeparatorMenuItem separator1=new SeparatorMenuItem();
        SeparatorMenuItem separator2=new SeparatorMenuItem();
        SeparatorMenuItem separator3=new SeparatorMenuItem();
        SeparatorMenuItem separator4=new SeparatorMenuItem();
        dailyReport.setId("rentItem");
        monthlyReport.setId("rentItem");
        unavailableCars.setId("rentItem");
        addCarForRentItem.setId("rentItem");
        addCarForSellItem.setId("rentItem");
        aboutItem.setId("rentItem");
        viewAllCars.setId("rentItem");
        viewCompanyFeedback.setId("rentItem");
        settings.setId("rentItem");
        exit.setId("rentItem");
        viewCompanyRate.setId("rentItem");
        companyBalance.setId("rentItem");
        settings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
            }
        });
        service.getItems().addAll(dailyReport,monthlyReport,separator1,addCarForRentItem,addCarForSellItem,viewAllCars,
                unavailableCars,separator2,viewCompanyFeedback,viewCompanyRate,companyBalance,settings,separator3,exit);
        MenuBar serviceBar=new MenuBar();
        serviceBar.getMenus().addAll(service);
        header.getChildren().addAll(logo,addCarForSell,addCarForRent,about,search,message,userPersonalImageLabel,serviceBar);
        header.setAlignment(Pos.CENTER);
        footer=new HBox();
        footer.setPadding(new Insets(5,5,5,5));
        footer.setId("footer");
        copyRight=new Label("all rights reserved. copyright © 2016");
        footer.setAlignment(Pos.CENTER);
        copyRight.setId("copyRight");
        footer.getChildren().add(copyRight);
    }
}
