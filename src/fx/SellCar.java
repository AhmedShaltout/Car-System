package GUI;

import CarRentSystem.About;
import CarRentSystem.CarRentSystemException;
import CarRentSystem.FileManager;
import Cars.CarForSell;
import Cars.Engine;
import Persons.Admin;
import Persons.Person;
import Persons.User;
import java.awt.image.BufferedImage;
import java.io.File;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TextArea;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.imageio.ImageIO;

public class SellCar {
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
    private Button cancel,Sell,upload,ok1,ok2,addCarForSell,addCarForRent,about;
    private Label carDoors,speedKM,carColor,carType,carName,carModel,availableKM,aboutCar,motor,carImageLabel,carCylinder,carPrice;
    private TextField carDoorsF,speedKMF,carColorF,carTypeF,carNameF,carModelF,availableKMF,motorF,carCylinderF,carPriceF;
    private TextArea aboutT,D_report,M_report;
    private String Path="logo.png",picPath="male.png";
    private Image pic;
    private ImageView Pic;
    private String carImagePath;
    

    SellCar(User LoginUser) {
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
        sell.setDisable(true);
        buy=new Button("Buy");
        buy.setId("mainOrders");
        buy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new BuyCar(LoginUser);
                window.close();
            }
        });
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
        Menu service=new Menu(LoginUser.getFname());
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
        buyItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
                new BuyCar(LoginUser);
            }
        });
        MenuItem sellItem=new MenuItem("Sell Car...");
        sellItem.setDisable(true);
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
        
        aboutT=new TextArea();
        aboutT.setPromptText("type something about your car here...");
        
        cancel=new Button("Cancel");
        cancel.setId("save");
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new Profile(LoginUser);
                window.close();
            }
        });
        Sell=new Button("Sell");
        Sell.setId("blackButton");
        Sell.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Sell.setDisable(true);
                    CarRentSystemException.mismatchString(carColorF.getText());
                    CarRentSystemException.mismatchString(carTypeF.getText());
                    CarRentSystemException.mismatchString(carNameF.getText());
                    CarRentSystemException.mismatchString(carModelF.getText());
                    CarRentSystemException.mismatchString(motorF.getText());
                    CarRentSystemException.mismatchString(carCylinderF.getText());
                    CarForSell Car=new CarForSell();
                    Car.setMyMoney(LoginUser.getMyMethod());
                    Engine engine=new Engine();
                    Integer cc=Integer.parseInt(motorF.getText());
                    Float price=Float.parseFloat(carPriceF.getText());
                    engine.setCC(cc);
                    engine.setCylinder(carCylinderF.getText());
                    About about=new About();
                    about.setAbout(aboutT.getText());
                    Float Speed=Float.parseFloat(speedKMF.getText());
                    Integer Doors=Integer.parseInt(carDoorsF.getText());
                    Float PassKM=Float.parseFloat(availableKMF.getText());
                    Car.setConfirmationEmail(LoginUser.getEmail());
                    Car.setCarPrice(price);
                    Car.editCarProfile(carImagePath, engine, carColorF.getText(), Speed, PassKM ,Doors, about, carTypeF.getText(), carModelF.getText(), carNameF.getText());
                    Car.setAvailable(Boolean.FALSE);
                    LoginUser.sellCar(Car);
                    CarRentSystemException.warningMessage("Will Send you Email soon");
                    Sell.setDisable(false);
                    window.close();
                    new Profile(LoginUser);
                    FileManager.saveCarSell(Car);
                } catch (Exception ex){
                    CarRentSystemException.warningMessage("Invalid Info");
                    Sell.setDisable(false);
                }
            }
        });
        upload=new Button("Upload Your Car Image");
        upload.setId("prev");
        upload.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser=new FileChooser();
                try {
                File file = fileChooser.showOpenDialog(null);
                carImagePath=file.getAbsolutePath();
                BufferedImage bufferedImage = ImageIO.read(file);
                pic = SwingFXUtils.toFXImage(bufferedImage, null);
                carImageLabel.setBackground(new Background(new BackgroundImage(pic, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                } catch (Exception ex) {
                    CarRentSystemException.warningMessage("You need to choose a picture");
                } 
            }
        });
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileManager.saveUsers(LoginUser);
                window.close();
                new LoginPag();

            }
        });
        carDoors=new Label("Number Of Doors: ");
        speedKM=new Label("Speed/ km");
        carColor=new Label("Car Color: ");
        carType=new Label("Car Type: ");
        carName=new Label("Car Name: ");
        carModel=new Label("Car Model: ");
        availableKM=new Label("Available KMs: ");
        aboutCar=new Label("About: ");
        motor=new Label("Motor: ");
        carImageLabel=new Label();
        pic=new Image(getClass().getResourceAsStream(Path));
        Pic=new ImageView(pic);
        Background back=new Background(new BackgroundImage(pic, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
        carImageLabel.setMinHeight(150);
        carImageLabel.setMinWidth(250);
        
        carDoorsF=new TextField();
        carDoorsF.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                carDoorsF.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
        });
        carDoorsF.setPromptText("type number of doors");
        speedKMF=new TextField();
        speedKMF.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                speedKMF.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
        });
        
        speedKMF.setPromptText("type speed per km");
        carColorF=new TextField();
        
        carColorF.setPromptText("type your car color");
        carTypeF=new TextField();
        carTypeF.setPromptText("type your car type");
        carNameF=new TextField();
        carNameF.setPromptText("type your car name");
        carModelF=new TextField();
        carModelF.setPromptText("type your car model");
        availableKMF=new TextField();
        availableKMF.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                availableKMF.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
        });
        
        carCylinderF=new TextField();
        carCylinderF.setPromptText("type a cylinder");
        carCylinder=new Label("Cylinder: ");
        carPrice=new Label("Price: ");
        carPriceF=new TextField();
        carPriceF.setPromptText("type a price for the car");
        availableKMF.setPromptText("how many KMs did your car pass?");
        motorF=new TextField();
        motorF.setPromptText("type your CC (hourse power) ");
        carPriceF.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                carPriceF.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
        });
        motorF.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                motorF.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
        });
        glass.setConstraints(carImageLabel, 0, 0);
        glass.setConstraints(upload, 1, 0);
        
        glass.setConstraints(carDoors, 0, 1);
        glass.setConstraints(carDoorsF, 1, 1);
        glass.setConstraints(speedKM, 2, 1);
        glass.setConstraints(speedKMF, 3, 1);
        
        glass.setConstraints(carColor, 0, 2);
        glass.setConstraints(carColorF, 1, 2);
        glass.setConstraints(carType, 2, 2);
        glass.setConstraints(carTypeF, 3, 2);
        
        glass.setConstraints(carName, 0, 3);
        glass.setConstraints(carNameF, 1, 3);
        glass.setConstraints(carModel, 2, 3);
        glass.setConstraints(carModelF, 3, 3);
        
        glass.setConstraints(availableKM, 0, 4);
        glass.setConstraints(availableKMF, 1, 4);
        glass.setConstraints(motor, 2, 4);
        glass.setConstraints(motorF, 3, 4);
        
        glass.setConstraints(carCylinder, 0, 5);
        glass.setConstraints(carCylinderF, 1, 5);
        glass.setConstraints(carPrice, 2, 5);
        glass.setConstraints(carPriceF, 3, 5);
        
        glass.setConstraints(Sell, 3, 6);
        glass.setConstraints(cancel, 2, 6);
        glass.setConstraints(aboutCar, 0, 6);
        glass.setConstraints(aboutT, 1, 6);
        
        glass.getChildren().addAll(carImageLabel,upload,
                                   carDoors,carDoorsF,speedKM,speedKMF,
                                   carColor,carColorF,carType,carTypeF,
                                   carName,carNameF,carModel,carModelF,
                                   availableKM,availableKMF,motor,motorF,
                                   carCylinder,carCylinderF,carPrice,carPriceF,
                                   aboutCar,aboutT,Sell,cancel);
        
        glass.setId("glass");
        glass.setPadding(new Insets(20,15,20,15));
        glass.setVgap(10);
        glass.setHgap(10);
        glass.setMaxWidth(1320);
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    
    
    
    public SellCar(Admin Shaltout) {
        window=new Stage();
        D_report=new TextArea();
        D_report.setMaxHeight(200);
        D_report.setMaxWidth(500);
        D_report.setVisible(false);
        M_report=new TextArea();
        M_report.setMaxHeight(200);
        M_report.setMaxWidth(500);
        M_report.setVisible(false);
        ok1=new Button("Ok");
        ok1.setId("save");
        ok1.setVisible(false);
        ok1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                D_report.setVisible(false);
                ok1.setVisible(false);
            }
        });
        ok2=new Button("Ok");
        ok2.setId("save");
        ok2.setVisible(false);
        ok2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                M_report.setVisible(false);
                ok2.setVisible(false);
            }
        });
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
        addCarForRent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
                new addCarForRent(Shaltout);
            }
        });
        addCarForSell.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
                new SellCar(Shaltout);
            }
        });
        about=new Button("Show Waiting Cars");
        about.setId("AdminmainOrders");
        addCarForSell.setPadding(new Insets(25,5,25,5));
        addCarForRent.setPadding(new Insets(25,5,25,5));
        about.setPadding(new Insets(25,0,25,0));
        about.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    CarRentSystem.CarRentSystemException.EmptyArraySell(FileManager.loadCarSell(false));
                    AdminPage.window.close();
                    new ViewWaitingCars();
                } catch (Exception ex) {
                    CarRentSystemException.warningMessage("no waiting Cars");
                }
            }
        });
        
        message=new Button();
        message.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("wonderful.png"))));
        Region region= new Region();
        region.resize(700, 100);
        region.setMinWidth(100);
        userPersonalImageLabel=new Label();
        userPersonalImageLabel.setMinHeight(100);
        userPersonalImageLabel.setMinWidth(100);
        userPersonalImageLabel.setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("nnn.jpg")), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        try{
            File file=new File(Shaltout.getPic());
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image= SwingFXUtils.toFXImage(bufferedImage, null);
            userPersonalImageLabel.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        }
        catch(Exception w){
        }
        
        Menu service=new Menu(Shaltout.getFname());
        service.setId("service");
        MenuItem dailyReport=new MenuItem("Daily Report...");
        dailyReport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                D_report.setVisible(true);
                ok1.setVisible(true);
            }
        });
        MenuItem monthlyReport=new MenuItem("Monthly Report...");
        monthlyReport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                M_report.setVisible(true);
                ok2.setVisible(true);
            }
        });
        MenuItem addCarForRentItem=new MenuItem("Add Car For Rent...");
        addCarForRentItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
                new addCarForRent(Shaltout);
            }
        });
        MenuItem addCarForSellItem=new MenuItem("Add Car For Sell...");
        addCarForSellItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
                new SellCar(Shaltout);
            }
        });
        MenuItem viewAllCars=new MenuItem("View Waiting Cars...");
        viewAllCars.setDisable(true);
        
        MenuItem viewCompanyRate=new MenuItem("Our Rate: "+Shaltout.getCompanyRate());
        MenuItem companyBalance=new MenuItem("Our Balance Now: "+Shaltout.getBalance()+" $");
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
        addCarForRentItem.setId("rentItem");
        addCarForSellItem.setId("rentItem");
        viewAllCars.setId("rentItem");
        exit.setId("rentItem");
        viewCompanyRate.setId("rentItem");
        companyBalance.setId("rentItem");
        service.getItems().addAll(dailyReport,monthlyReport,separator1,addCarForRentItem,addCarForSellItem,viewAllCars,
                separator2,viewCompanyRate,companyBalance,separator3,exit);
        MenuBar serviceBar=new MenuBar();
        serviceBar.getMenus().addAll(service);
        header.getChildren().addAll(logo,addCarForSell,addCarForRent,about,message,userPersonalImageLabel,serviceBar);
        header.setAlignment(Pos.CENTER);
        footer=new HBox();
        footer.setPadding(new Insets(5,5,5,5));
        footer.setId("footer");
        copyRight=new Label("all rights reserved. copyright © 2016");
        footer.setAlignment(Pos.CENTER);
        copyRight.setId("copyRight");
        footer.getChildren().add(copyRight);
        glass=new GridPane();
        
        aboutT=new TextArea();
        aboutT.setPromptText("type something about your car here...");
        
        cancel=new Button("Cancel");
        cancel.setId("save");
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new AdminPage();
                window.close();
            }
        });
        Sell=new Button("Sell");
        Sell.setId("blackButton");
        Sell.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Sell.setDisable(true);
                try {
                    CarRentSystemException.mismatchString(carColorF.getText());
                    CarRentSystemException.mismatchString(carTypeF.getText());
                    CarRentSystemException.mismatchString(carNameF.getText());
                    CarRentSystemException.mismatchString(carModelF.getText());
                    CarRentSystemException.mismatchString(motorF.getText());
                    CarRentSystemException.mismatchString(carCylinderF.getText());
                    CarForSell Car=new CarForSell();
                    Car.setMyMoney(Shaltout.getMyMethod());
                    Car.setConfirmationEmail(Shaltout.getEmail());
                    Engine engine=new Engine();
                    Integer cc=Integer.parseInt(motorF.getText());
                    Float price=Float.parseFloat(carPriceF.getText());
                    engine.setCC(cc);
                    engine.setCylinder(carCylinderF.getText());
                    About about=new About();
                    about.setAbout(aboutT.getText());
                    Float Speed=Float.parseFloat(speedKMF.getText());
                    Integer Doors=Integer.parseInt(carDoorsF.getText());
                    Float PassKM=Float.parseFloat(availableKMF.getText());
                    Car.setConfirmationEmail(Shaltout.getEmail());
                    Car.editCarProfile(carImagePath, engine, carColorF.getText(), Speed, PassKM ,Doors, about, carTypeF.getText(), carModelF.getText(), carNameF.getText());
                    Car.setAvailable(Boolean.TRUE);
                    Car.setCarPrice(price);
                    CarRentSystemException.warningMessage("You Have Add a car Successfuly");
                    window.close();
                    Sell.setDisable(false);
                    new AdminPage();
                    FileManager.saveCarSell(Car);
                } catch (Exception ex) {
                    CarRentSystemException.warningMessage("Invalid Info");
                    Sell.setDisable(false);
                }
            }
        });
        upload=new Button("Upload Your Car Image");
        upload.setId("prev");
        upload.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser=new FileChooser();
                File file = fileChooser.showOpenDialog(null);
                try {
                carImagePath=file.getAbsolutePath();
                BufferedImage bufferedImage = ImageIO.read(file);
                pic = SwingFXUtils.toFXImage(bufferedImage, null);
                carImageLabel.setBackground(new Background(new BackgroundImage(pic, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                } catch (Exception ex){
                    CarRentSystemException.warningMessage("You need to choose a picture");
                }
            }
        });
        
        carDoors=new Label("Number Of Doors: ");
        speedKM=new Label("Speed/ km");
        carColor=new Label("Car Color: ");
        carType=new Label("Car Type: ");
        carName=new Label("Car Name: ");
        carModel=new Label("Car Model: ");
        availableKM=new Label("Available KMs: ");
        aboutCar=new Label("About: ");
        motor=new Label("Motor: ");
        carImageLabel=new Label();
        pic=new Image(getClass().getResourceAsStream(Path));
        Pic=new ImageView(pic);
        Background back=new Background(new BackgroundImage(pic, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
        carImageLabel.setMinHeight(150);
        carImageLabel.setMinWidth(250);
        
        carDoorsF=new TextField();
        carDoorsF.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                carDoorsF.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
        });
        carDoorsF.setPromptText("type number of doors");
        speedKMF=new TextField();
        speedKMF.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                speedKMF.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
        });
        
        speedKMF.setPromptText("type speed per km");
        carColorF=new TextField();
        
        carColorF.setPromptText("type your car color");
        carTypeF=new TextField();
        carTypeF.setPromptText("type your car type");
        carNameF=new TextField();
        carNameF.setPromptText("type your car name");
        carModelF=new TextField();
        carModelF.setPromptText("type your car model");
        availableKMF=new TextField();
        availableKMF.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                availableKMF.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
        });
        
        carCylinderF=new TextField();
        carCylinderF.setPromptText("type a cylinder");
        carCylinder=new Label("Cylinder: ");
        carPrice=new Label("Price: ");
        carPriceF=new TextField();
        carPriceF.setPromptText("type a price for the car");
        availableKMF.setPromptText("how many KMs did your car pass?");
        motorF=new TextField();
        motorF.setPromptText("type your CC (hourse power) ");
        carPriceF.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                carPriceF.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
        });
        motorF.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                motorF.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
        });
        glass.setConstraints(carImageLabel, 0, 0);
        glass.setConstraints(upload, 1, 0);
        
        glass.setConstraints(carDoors, 0, 1);
        glass.setConstraints(carDoorsF, 1, 1);
        glass.setConstraints(speedKM, 2, 1);
        glass.setConstraints(speedKMF, 3, 1);
        
        glass.setConstraints(carColor, 0, 2);
        glass.setConstraints(carColorF, 1, 2);
        glass.setConstraints(carType, 2, 2);
        glass.setConstraints(carTypeF, 3, 2);
        
        glass.setConstraints(carName, 0, 3);
        glass.setConstraints(carNameF, 1, 3);
        glass.setConstraints(carModel, 2, 3);
        glass.setConstraints(carModelF, 3, 3);
        
        glass.setConstraints(availableKM, 0, 4);
        glass.setConstraints(availableKMF, 1, 4);
        glass.setConstraints(motor, 2, 4);
        glass.setConstraints(motorF, 3, 4);
        
        glass.setConstraints(carCylinder, 0, 5);
        glass.setConstraints(carCylinderF, 1, 5);
        glass.setConstraints(carPrice, 2, 5);
        glass.setConstraints(carPriceF, 3, 5);
        
        glass.setConstraints(Sell, 3, 6);
        glass.setConstraints(cancel, 2, 6);
        glass.setConstraints(aboutCar, 0, 6);
        glass.setConstraints(aboutT, 1, 6);
        
        glass.getChildren().addAll(carImageLabel,upload,
                                   carDoors,carDoorsF,speedKM,speedKMF,
                                   carColor,carColorF,carType,carTypeF,
                                   carName,carNameF,carModel,carModelF,
                                   availableKM,availableKMF,motor,motorF,
                                   carCylinder,carCylinderF,carPrice,carPriceF,
                                   aboutCar,aboutT,Sell,cancel);
        
        glass.setId("glass");
        glass.setPadding(new Insets(20,15,20,15));
        glass.setVgap(10);
        glass.setHgap(10);
        glass.setMaxWidth(1320);
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
                closeProgram(Shaltout);
            }
        });
        
    }    public void closeProgram(Person LogOut){
        boolean result=confirmExit.display("Title", "Are you sure close the Application?!");
        if(result){
            if(LogOut instanceof User)
                FileManager.saveUsers((User) LogOut);
            else if(LogOut instanceof Admin)
                FileManager.saveAdmin((Admin) LogOut);
            window.close();
        } 
    }
    
}
