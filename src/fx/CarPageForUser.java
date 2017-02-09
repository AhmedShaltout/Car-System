package GUI;

import CarRentSystem.CarRentSystemException;
import CarRentSystem.FileManager;
import Cars.CarForRent;
import Cars.CarForSell;
import Persons.Admin;
import Persons.User;
import java.awt.image.BufferedImage;
import java.io.File;
import javafx.embed.swing.SwingFXUtils;
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
import javax.imageio.ImageIO;

public class CarPageForUser {
    private Stage window;
    private User LoginUser;
    private Scene scene;
    private CarForRent carForRent;
    private CarForSell carForSell;
    private BorderPane layout;
    private HBox header,footer;
    private Label logo,copyRight,userPersonalImageLabel;
    private Button rent,buy,sell,message;
    private TextField search;
    private GridPane glass;
    private VBox right,left;
    private Region rightRegion,leftRegion;
    private Label carDoors,speedKM,carColor,carType,carName,carModel,availableKM,about,motor,carImageLabel,carCylinder,carPrice;
    private String Path="2.jpg";
    private Image pic;
    private ImageView Pic;
    private String carImagePath="2.jpg";

    CarPageForUser(User LoginUser,CarForRent carForRent) {
        this.LoginUser=LoginUser;
        this.carForRent=carForRent;
        headerAndFooterForUser();
        carImagePath="2.jpg";
        carDoors=new Label("Number Of Doors: "+carForRent.getDoors());
        speedKM=new Label("Speed/ km"+carForRent.getSpeed());
        carColor=new Label("Car Color: "+carForRent.getColor());
        carType=new Label("Car Type: "+carForRent.getCarType());
        carName=new Label("Car Name: "+carForRent.getCarName());
        carModel=new Label("Car Model: "+carForRent.getModelType());
        availableKM=new Label("Available KMs: "+carForRent.getAvailbleKM());
        about=new Label("About: " +carForRent.getAbout().getAbout());/////+++++car.getAbout()???where is it
        motor=new Label("Motor: "+carForRent.getEngine().getCC()+" cc");/////+++++car.getMotor()???where is it
        carCylinder=new Label("Cylinder: "+carForRent.getEngine().getcylinder());
        carPrice=new Label("Price: "+carForRent.getPricePerH());
        carImageLabel=new Label();
        File file;
        try{
            
            file=new File(carForSell.getImage());
        }
        catch(Exception e){
            file=new File("logo.png");
        }
        try{
        BufferedImage bufferedImage = ImageIO.read(file);
        Image image= SwingFXUtils.toFXImage(bufferedImage, null);
        carImageLabel.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        }
        catch(Exception w){
            CarRentSystemException.warningMessage("Can't load car picture");
        }
        carImageLabel.setMinHeight(200);
        carImageLabel.setMinWidth(300);
        
        glass.setConstraints(carImageLabel, 0, 0);
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
        glass.setPadding(new Insets(20,5,20,5));
        glass.setVgap(10);
        glass.setHgap(10);
        glass.setMaxWidth(1320);
        glass.setMaxHeight(530);
        rightRegion=new Region();
        rightRegion.setMinWidth(140);
        leftRegion=new Region();
        leftRegion.setMinWidth(140);
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
        layout.setCenter(glass);
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
                closeProgram(LoginUser);
            }
        });
    }
    CarPageForUser(User LoginUser, CarForSell carForSell)
    {
        carImagePath="2.jpg";
        this.LoginUser=LoginUser;
        this.carForSell=carForSell;
        headerAndFooterForUser();
        carDoors=new Label("Number Of Doors: "+carForSell.getDoors());
        speedKM=new Label("Speed/ km"+carForSell.getSpeed());
        carColor=new Label("Car Color: "+carForSell.getColor());
        carType=new Label("Car Type: "+carForSell.getCarType());
        carName=new Label("Car Name: "+carForSell.getCarName());
        carModel=new Label("Car Model: "+carForSell.getModelType());
        availableKM=new Label("Available KMs: "+carForSell.getAvailbleKM());
        about=new Label("About: " +carForSell.getAbout().getAbout());
        motor=new Label("Motor: "+carForSell.getEngine().getCC()+"CC");
        carCylinder=new Label("Cylinder: "+carForSell.getEngine().getcylinder());
        carPrice=new Label("Price: "+carForSell.getCarPrice());
        carImageLabel=new Label();
        File file;
        try{
            
            file=new File(carForSell.getImage());
        }
        catch(Exception e){
            file=new File("logo.png");
        }
        try{
        BufferedImage bufferedImage = ImageIO.read(file);
        Image image= SwingFXUtils.toFXImage(bufferedImage, null);
        carImageLabel.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        }
        catch(Exception w){
            CarRentSystemException.warningMessage("Can't load car picture");
        }
        carImageLabel.setMinHeight(200);
        carImageLabel.setMinWidth(300);
        glass=new GridPane();
        glass.setConstraints(carImageLabel, 0, 0);
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
        
        glass.setId("glass");
        glass.setPadding(new Insets(20,5,20,5));
        glass.setVgap(10);
        glass.setHgap(10);
        glass.setMaxWidth(1320);
        glass.setMaxHeight(530);
        rightRegion=new Region();
        rightRegion.setMinWidth(140);
        leftRegion=new Region();
        leftRegion.setMinWidth(140);
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
        layout.setCenter(glass);
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
                closeProgram(LoginUser);
            }
        });
        
    }
    public void closeProgram(User LogOut){
        boolean result=confirmExit.display("Title", "Are you sure close the Application?!");
        if(result){
            FileManager.saveUsers(LogOut);
            window.close();
        }
            
    }
    public void headerAndFooterForUser(){
        header=new HBox();
        header.setPadding(new Insets(5,5,5,5));
        header.setSpacing(10);
        header.setId("header");
        logo=new Label();
        logo.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("logo.png"))));
        rent=new Button("Rent");
        rent.setId("mainOrders");
        rent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new RentCar(LoginUser);
                window.close();
            }
        });
        sell=new Button("Sell");
        sell.setId("mainOrders");
        sell.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new SellCar(LoginUser);
                window.close();
            }
        });
        buy=new Button("Buy");
        buy.setId("mainOrders");
        buy.setDisable(true);
        rent.setPadding(new Insets(25,20,25,20));
        buy.setPadding(new Insets(25,20,25,20));
        sell.setPadding(new Insets(25,20,25,20));
        search=new TextField();
        search.setId("search");
        message=new Button();
        message.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("wonderful.png"))));
        Region region= new Region();
        region.resize(700, 100);
        region.setMinWidth(150);
        Image image=new Image(getClass().getResourceAsStream("male2.png"));
        ImageView imageView=new ImageView(image);
        userPersonalImageLabel=new Label();
        userPersonalImageLabel.setGraphic(imageView);
        Menu service=new Menu(this.LoginUser.getFname());
        service.setId("service");
        MenuItem rentItem=new MenuItem("Rent Car...");
        MenuItem buyItem=new MenuItem("Buy Car...");
        MenuItem sellItem=new MenuItem("Sell Car...");
        MenuItem exit=new MenuItem("Log Out");
        MenuItem recentActivity=new MenuItem("Recent Activity...");
        MenuItem reschedule=new MenuItem("Reschedule Your Rent...");
        MenuItem feedback=new MenuItem("Send Feedback...");
        MenuItem rate=new MenuItem("Give Us Rate...");
        MenuItem searchItem=new MenuItem("Search Car...");
        MenuItem settings=new MenuItem("Settings");
        SeparatorMenuItem separator1=new SeparatorMenuItem();
        SeparatorMenuItem separator2=new SeparatorMenuItem();
        SeparatorMenuItem separator3=new SeparatorMenuItem();
        SeparatorMenuItem separator4=new SeparatorMenuItem();
        SeparatorMenuItem separator5=new SeparatorMenuItem();
        rentItem.setId("rentItem");
        buyItem.setId("rentItem");
        sellItem.setId("rentItem");
        reschedule.setId("rentItem");
        feedback.setId("rentItem");
        rate.setId("rentItem");
        recentActivity.setId("rentItem");
        searchItem.setId("rentItem");
        settings.setId("rentItem");
        settings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
            }
        });
        exit.setId("rentItem");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileManager.saveUsers(LoginUser);
                window.close();
                new LoginPag();

            }
        });
        service.getItems().addAll(rentItem,buyItem,sellItem,separator1,feedback,rate,separator2,reschedule,separator3,
                                 recentActivity ,separator4,searchItem,settings,separator5,exit);
        MenuBar serviceBar=new MenuBar();
        serviceBar.getMenus().addAll(service);
        header.getChildren().addAll(logo,rent,buy,sell,search,region,message,userPersonalImageLabel,serviceBar);
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
