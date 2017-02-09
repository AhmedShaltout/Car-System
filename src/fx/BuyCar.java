package GUI;

import CarRentSystem.CarRentSystemException;
import CarRentSystem.FileManager;
import Cars.CarForSell;
import Filters.BuyFilter;
import Persons.User;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javafx.embed.swing.SwingFXUtils;
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

public class BuyCar {
    private Stage window;
    private Scene scene;
    private BorderPane layout;
    private HBox header,footer;
    private Label logo,copyRight,userPersonalImageLabel,forCar,carPrice;
    private Button rent,buy,sell,message;
    private TextField search;
    private GridPane glass;
    private VBox right,left;
    private User LoginUser;
    private Region rightRegion,leftRegion;
    private CheckBox price,model;
    private Label initPrice,endPrice;
    private TextField initPriceField,endPriceField,modelField;
    private Button previous,next,cancel,Buy,about,filter;
    private String Path="green.jpg",picPath="male.png";
    private ArrayList<CarForSell> Scanned;
    private int index=0;

    BuyCar(User LoginUser) {
        this.LoginUser=LoginUser;
        carPrice=new Label();
        
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
        message.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
                new AllMessages(LoginUser);
            }
        });
        Region region= new Region();
        region.resize(700, 100);
        region.setMinWidth(150);
        Image image=new Image(getClass().getResourceAsStream("male2.png"));
        ImageView imageView=new ImageView(image);
        userPersonalImageLabel=new Label();
        userPersonalImageLabel.setMinHeight(100);
        userPersonalImageLabel.setMinWidth(100);
        userPersonalImageLabel.setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream(picPath)), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        try{
            File file=new File(LoginUser.getPic());
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image2= SwingFXUtils.toFXImage(bufferedImage, null);
            userPersonalImageLabel.setBackground(new Background(new BackgroundImage(image2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        }
        catch(Exception w){
        }
        Menu service=new Menu(this.LoginUser.getFname());
        service.setId("service");
        MenuItem rentItem=new MenuItem("Rent Car...");
        rentItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
                new RentCar(LoginUser);
            }
        });
        MenuItem buyItem=new MenuItem("Buy Car...");
        buyItem.setDisable(true);
        MenuItem sellItem=new MenuItem("Sell Car...");
        sellItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
                new SellCar(LoginUser);
            }
        });
        MenuItem exit=new MenuItem("Log Out");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileManager.saveUsers(LoginUser);
                window.close();
                new LoginPag();
            }
        });
        MenuItem recentActivity=new MenuItem("Recent Activity...");
        recentActivity.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
                new RecentActivity(LoginUser);
            }
        });
        MenuItem reschedule=new MenuItem("Reschedule Your Rent...");
        reschedule.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
                new RescheduleRenr(LoginUser);
            }
        });
        MenuItem feedback=new MenuItem("Send Feedback...");
        feedback.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
                new Feedback(LoginUser,0);
            }
        });
        MenuItem rate=new MenuItem("Rate Us...");
        rate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
                new Feedback(LoginUser,1);
            }
        });
        MenuItem settings=new MenuItem("Settings");
        settings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
                new SettingsPage(LoginUser);
            }
        });
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
        settings.setId("rentItem");
        exit.setId("rentItem");
        service.getItems().addAll(rentItem,buyItem,sellItem,separator1,feedback,rate,separator2,reschedule,separator3,
                                 recentActivity ,separator4,settings,separator5,exit);
        MenuBar serviceBar=new MenuBar();
        serviceBar.getMenus().addAll(service);
        search=new TextField();
        search.setId("search");
        header.getChildren().addAll(logo,rent,buy,sell,search,region,message,userPersonalImageLabel,serviceBar);
        header.setAlignment(Pos.CENTER);
        footer=new HBox();
        footer.setPadding(new Insets(5,5,5,5));
        footer.setId("footer");
        copyRight=new Label("all rights reserved. copyright © 2016");
        footer.setAlignment(Pos.CENTER);
        copyRight.setId("copyRight");
        footer.getChildren().add(copyRight);
        
        price=new CheckBox("Search By Price");
        model=new CheckBox("Search By Model");
        
        initPrice=new Label("Init Price");
        endPrice=new Label("End Price");
        forCar=new Label();
        forCar.setMinHeight(370);
        forCar.setMinWidth(460);
//        pic=new Image(getClass().getResourceAsStream(Path));
//        Pic=new ImageView(pic);
//        Background back=new Background(new BackgroundImage(pic, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
//        forCar.setBackground(back);
        
        
        initPriceField=new TextField();
        initPriceField.setPromptText("type your initial price");
        endPriceField=new TextField();
        endPriceField.setPromptText("type your end price");
        modelField=new TextField();
        modelField.setPromptText("type a model");
        
        initPriceField.setDisable(true);
        endPriceField.setDisable(true);
        modelField.setDisable(true);
        price.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(price.isSelected()){
                    initPriceField.setDisable(false);
                    endPriceField.setDisable(false);
                }
                else if(!price.isSelected()){
                    initPriceField.setText("");
                    endPriceField.setText("");
                    initPriceField.setDisable(true);
                    endPriceField.setDisable(true);
                }
            }
        });
        model.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(model.isSelected()){
                    modelField.setDisable(false);
                }
                else if(!model.isSelected()){
                    modelField.setText("");
                    modelField.setDisable(true);
                }
            }
        });
        
        filter=new Button("Filter");
        filter.setId("save");
        filter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                index=0;
                next.setVisible(false);
                previous.setVisible(false);
                filter.setDisable(true);
                about.setVisible(false);
                Buy.setVisible(false);
                carPrice.setVisible(false);
                forCar.setVisible(false);
                BuyFilter Filter=new BuyFilter();
                if(price.isSelected()&&model.isSelected()){
                    try{
                        CarRentSystemException.mismatchString(model.getText());
                        try{
                            Float Pricefrom=Float.parseFloat(initPriceField.getText());
                            Float Priceto=Float.parseFloat(endPriceField.getText());
                            if(Pricefrom>=0&&Pricefrom<Priceto){
                                    Scanned=Filter.Merge(Pricefrom,Priceto,model.getText());    
                                    if(!Scanned.isEmpty()){
                                        DisplayCarinfo(Scanned.get(index));
                                        if(Scanned.size()>1)
                                            next.setVisible(true);
                                    }
                                    else{
                                        CarRentSystemException.warningMessage("We have no cars as given");
                                        initPriceField.setText(null);
                                        endPriceField.setText(null);
                                        modelField.setText(null);
                                    }
                            }
                            else{
                                CarRentSystemException.warningMessage("Wrong price");
                                initPriceField.setText(null);
                                endPriceField.setText(null);
                                modelField.setText(null);
                            }
                        }catch(Exception e){
                            CarRentSystemException.warningMessage("no available cars");
                            initPriceField.setText(null);
                            endPriceField.setText(null);
                            modelField.setText(null);
                        }
                    }
                    catch(Exception e){
                        CarRentSystemException.warningMessage("Wrong Values");
                        initPriceField.setText(null);
                        endPriceField.setText(null);
                        modelField.setText(null);
                    }
                }
                else if(price.isSelected()){
                    try{
                        Float from=Float.parseFloat(initPriceField.getText());
                        Float to=Float.parseFloat(endPriceField.getText());
                        try{
                            if(from>=0&&from<to){
                                Scanned=Filter.filterByPrice(from,to);
                                if(!Scanned.isEmpty()){
                                    DisplayCarinfo(Scanned.get(index));
                                    if(Scanned.size()>1)
                                        next.setVisible(true);
                                }
                                else{
                                    CarRentSystemException.warningMessage("no available cars for this price");
                                    initPriceField.setText(null);
                                    endPriceField.setText(null);
                                }
                            }
                            else{
                                CarRentSystemException.warningMessage("Wrong values");
                                initPriceField.setText(null);
                                endPriceField.setText(null);
                            }
                        }catch(Exception e){
                            CarRentSystemException.warningMessage("no available cars for this price");
                            initPriceField.setText(null);
                            endPriceField.setText(null);
                        }
                    }
                    catch(Exception e){
                        CarRentSystemException.warningMessage("Wrong Values");
                        initPriceField.setText(null);
                        endPriceField.setText(null);
                    }
                }
                else if(model.isSelected()){
                    try{
                        String x=model.getText();
                        Scanned=Filter.filterByModel(x);
                        if(!Scanned.isEmpty()){
                            DisplayCarinfo(Scanned.get(index));
                            if(Scanned.size()>1)
                                next.setVisible(true);
                        }
                        else{
                            modelField.setText(null);
                            CarRentSystemException.warningMessage("We don't have any cars of this model");
                        }
                    }
                    catch(Exception e){
                        CarRentSystemException.warningMessage("We don't have any cars of this model");
                        modelField.setText(null);
                    }
                }
                else{
                    CarRentSystemException.warningMessage("Choose some kind of filter");
                }
                filter.setDisable(false);
            }
        });
        
        previous=new Button("prev");
        previous.setId("prev");
        previous.setVisible(false);
        previous.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                next.setVisible(true);
                index--;
                if(index==0){
                    previous.setVisible(false);
                    
                DisplayCarinfo(Scanned.get(index));
                }
            }
        });
        next=new Button("next");
        next.setId("prev");
        next.setVisible(false);
        next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                previous.setVisible(true);
                index++;
                if(index==Scanned.size()-1){
                    next.setVisible(false);
                DisplayCarinfo(Scanned.get(index));
                }
            }
        });
        cancel=new Button("Cancel");
        cancel.setId("save");
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
                new Profile(LoginUser);
            }
        });
        Buy=new Button("Buy");
        Buy.setId("blackButton");
        Buy.setVisible(false);
        Buy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LoginUser.buyThis(Scanned.get(index));
            }
        });
        about=new Button("About");
        about.setId("blackButton");
        about.setVisible(false);
        about.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new CarPageForUser(LoginUser,Scanned.get(index));/*/********************************************************////
                window.close();
            }
        });
        
        glass=new GridPane();
        glass.setConstraints(price, 0, 0);
        glass.setConstraints(initPrice, 2, 0);
        glass.setConstraints(initPriceField, 4, 0);
        glass.setConstraints(endPrice, 6, 0);
        glass.setConstraints(endPriceField, 8, 0);
        
        glass.setConstraints(model, 0, 3);
        glass.setConstraints(modelField, 4, 3);
        
        glass.setConstraints(previous, 2, 4);
        glass.setConstraints(forCar, 4, 4);
        glass.setConstraints(next, 6, 4);
        
        glass.setConstraints(about, 2, 5);
        glass.setConstraints(Buy, 6, 5);
        glass.setConstraints(cancel, 7, 5);
        glass.setConstraints(carPrice, 3, 5);
        glass.setConstraints(filter, 8, 3);
        
        glass.getChildren().addAll(price,initPrice,initPriceField,endPrice,endPriceField,
                                   model,modelField,carPrice,forCar,filter,
                                   previous,next,about,Buy,cancel);
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
                closeProgram(LoginUser);
            }
        });
    }
    public void closeProgram(User LogOut){
        boolean result=confirmExit.display("Title", "Are you sure close the Application?!");
        if(result){
            window.close();
        }
            
    }
    private void DisplayCarinfo(CarForSell car){
        about.setVisible(true);
        carPrice.setVisible(true);
        forCar.setVisible(true);
        Buy.setVisible(true);
        carPrice.setText("Price: "+car.getCarPrice()+" $");
        File file;
        try{
            
            file=new File(car.getImage());
        }
        catch(Exception e){
            file=new File("logo.png");
        }
        try{
        BufferedImage bufferedImage = ImageIO.read(file);
        Image image= SwingFXUtils.toFXImage(bufferedImage, null);
        forCar.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        }
        catch(Exception w){
            CarRentSystemException.warningMessage("Can't load car picture");
        }
    }
    
}
