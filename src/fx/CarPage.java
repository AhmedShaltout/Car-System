package GUI;

import CarRentSystem.FileManager;
import Cars.Car;
import Persons.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class CarPage {
    private Car car;
    private User user;
    private Stage window;
    private Scene scene;
    private BorderPane layout;
    private HBox header,footer;
    private Label logo,copyRight,userPersonalImageLabel;
    private Button rent,buy,sell,message;
    private TextField search;
    private GridPane glass;
    private VBox right,left;
    private Region rightRegion,leftRegion;
    private CheckBox price,date,model;
    private Label initPrice,initDay,initHour,endPrice,endDay,endHour;
    private TextField initPriceField,initDayField,initHourField,endPriceField,endDayField,endHourField,modelField;
    private Label forCar;
    private Button previous,next,cancel,Rent,about;
    private Image pic;
    private ImageView Pic;
    private String Path="green.jpg";

    CarPage(Car car,User user) {
        this.car=car;
        this.user=user;
        window=new Stage();
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
                new RentCar(user);
                window.close();
            }
        });
        sell=new Button("Sell");
        sell.setId("mainOrders");
        sell.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new SellCar(user);
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
        Menu service=new Menu(this.user.getFname());
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
                FileManager.saveUsers(user);
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
        window.setScene(scene);
        window.show();
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                event.consume();
                closeProgram(user);
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
    
}
