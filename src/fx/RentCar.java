  package GUI;

import CarRentSystem.Book;
import CarRentSystem.CarRentSystemException;
import CarRentSystem.FileManager;
import Cars.CarForRent;
import Filters.RentFilter;
import Persons.User;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

public class RentCar {
    private Stage window;
    private Scene scene;
    private BorderPane layout;
    private HBox header,footer;
    private Label logo,copyRight,userPersonalImageLabel;
    private Button rent,buy,sell,message;
    private TextField search;
    private GridPane glass;
    private VBox right,left;
    private User LoginUser;
    private Region rightRegion,leftRegion;
    private CheckBox price,date,model;
    private Label initPrice,initDay,initHour,endPrice,endDay,endHour;
    private TextField initPriceField,initDayField,initHourField,endPriceField,endDayField,endHourField,modelField;
    private Label forCar;
    private Button previous,next,cancel,Rent,about,filter;
    private Image pic;
    private ImageView Pic;
    private int index;
    private ArrayList<CarForRent> Scanned;
    private String Path,picPath="male.png";
    
    RentCar(User LoginUser) {
        index=0;
        Scanned=new ArrayList<>();
        this.LoginUser=LoginUser;
        window=new Stage();
        header=new HBox();
        header.setPadding(new Insets(5,5,5,5));
        header.setSpacing(10);
        header.setId("header");
        logo=new Label();
        logo.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("logo.png"))));
        rent=new Button("Rent");
        rent.setId("mainOrders");
        rent.setDisable(true);
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
        Menu service=new Menu(this.LoginUser.getFname());
        service.setId("service");
        MenuItem rentItem=new MenuItem("Rent Car...");
        rentItem.setDisable(true);
        MenuItem buyItem=new MenuItem("Buy Car...");
        buyItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new BuyCar(LoginUser);
                window.close();
            }
        });
        MenuItem sellItem=new MenuItem("Sell Car...");
        sellItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new SellCar(LoginUser);
                window.close();
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
        }
        );
        MenuItem feedback=new MenuItem("Send Feedback...");
        feedback.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
                new Feedback(LoginUser, 0);
            }
        });
        MenuItem rate=new MenuItem("Rate Us...");
        rate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
                new Feedback(LoginUser, 1);
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
        
        price=new CheckBox("Search By Price");
        date=new CheckBox("Search By Date");
        model=new CheckBox("Search By Model");
        initPrice=new Label("Init Price");
        initDay=new Label("Init Date");
        initHour=new Label("Init Hour");
        endPrice=new Label("End Price");
        endDay=new Label("End Day");
        endHour=new Label("End Hour");
        forCar=new Label();
        forCar.setMinHeight(360);
        forCar.setMinWidth(470);
        initPriceField=new TextField(null);
        initPriceField.setPromptText("From price..");
        initPriceField.setDisable(true);
        endPriceField=new TextField(null);
        endPriceField.setPromptText("To price..");
        endPriceField.setDisable(true);
        initDayField=new TextField(null);
        initDayField.setPromptText("from today to 30..");
        initDayField.setDisable(true);
        endDayField=new TextField(null);
        endDayField.setPromptText("To day..");
        endDayField.setDisable(true);
        initHourField=new TextField(null);
        initHourField.setPromptText("from now to 24..");
        initHourField.setDisable(true);
        endHourField=new TextField(null);
        endHourField.setPromptText("To hour..");
        endHourField.setDisable(true);
        modelField=new TextField();
        modelField.setPromptText("type a model");
        modelField.setDisable(true);
        initPriceField.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                initPriceField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
        });
        endPriceField.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                endPriceField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
        });
        initHourField.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                initHourField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
        });
        endHourField.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                endHourField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
        });
        initDayField.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                initDayField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
        });
        endDayField.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                endDayField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
        });
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
        date.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(date.isSelected()){
                    initDayField.setDisable(false);
                    endDayField.setDisable(false);
                    initHourField.setDisable(false);
                    endHourField.setDisable(false);
                }
                else if(!date.isSelected()){
                    initDayField.setText("");
                    endDayField.setText("");
                    initHourField.setText("");
                    endHourField.setText("");
                    initDayField.setDisable(true);
                    endDayField.setDisable(true);
                    initHourField.setDisable(true);
                    endHourField.setDisable(true);
                }
            }
        });
        
        filter=new Button("Filter");
        filter.setId("save");
        filter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FilterIsPressed();
                filter.setDisable(false);
                if(!Scanned.isEmpty()){
                    next.setVisible(true);
                    about.setVisible(true);
                    Rent.setVisible(true);
                    
                    
                }
                else{
                    
                    next.setVisible(false);
                    about.setVisible(false);
                    Rent.setVisible(false);
                }
            }
        });
        previous=new Button("prev");
        previous.setId("prev");
        next=new Button("next");
        previous.setVisible(false);
        next.setVisible(false);
        next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                next.setVisible(false);
                    index++;
                    DisplayCarinfo(Scanned.get(index));
                    if(index==Scanned.size()){
                        next.setVisible(false);
                        previous.setVisible(true);
                }
            }
        });
        previous.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                previous.setDisable(true);
                index--;
                DisplayCarinfo(Scanned.get(index));
                if(index==0){
                    next.setVisible(true);
                    previous.setVisible(false);
                }
            }
        });
        next.setId("prev");
        cancel=new Button("Cancel");
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
                new Profile(LoginUser);
            }
        });
        
        cancel.setId("save");
        Rent=new Button("Rent");
        Rent.setVisible(false);
        Rent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LoginUser.rentThis(Scanned.get(index));
            }
        });
        
        Rent.setId("blackButton");
        about=new Button("About");
        about.setVisible(false);
        about.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /*******
                 * 
                 * 
                 * 
                 * 
                 * 
                 * 
                 * 
                 * 
                 * 
                 * 
                 * car Info Page
                 * 
                 * 
                 * 
                 * 
                 * 
                 *******/
            }
        });
        about.setId("blackButton");
        
        
        glass=new GridPane();
        glass.setConstraints(price, 0, 0);
        glass.setConstraints(initPrice, 2, 0);
        glass.setConstraints(initPriceField, 4, 0);
        glass.setConstraints(endPrice, 6, 0);
        glass.setConstraints(endPriceField, 8, 0);
        
        glass.setConstraints(date, 0, 1);
        glass.setConstraints(initDay, 2, 1);
        glass.setConstraints(initDayField, 4, 1);
        glass.setConstraints(endDay, 6, 1);
        glass.setConstraints(endDayField, 8, 1);
        
        glass.setConstraints(initHour, 2, 2);
        glass.setConstraints(initHourField, 4, 2);
        glass.setConstraints(endHour, 6, 2);
        glass.setConstraints(endHourField, 8, 2);
        
        glass.setConstraints(model, 0, 3);
        glass.setConstraints(modelField, 4, 3);
        
        glass.setConstraints(previous, 2, 4);
        glass.setConstraints(forCar, 4, 4);
        glass.setConstraints(next, 6, 4);
        
        glass.setConstraints(about, 2, 5);
        glass.setConstraints(Rent, 6, 5);
        glass.setConstraints(cancel, 7, 5);
        glass.setConstraints(filter, 8, 3);
        
        glass.getChildren().addAll(price,initPrice,initPriceField,endPrice,endPriceField,
                                   date,initDay,initDayField,endDay,endDayField,
                                   initHour,initHourField,endHour,endHourField,
                                   model,modelField,forCar,filter,
                                   previous,next,about,Rent,cancel);
        glass.setId("glass");
        glass.setPadding(new Insets(20,5,20,5));
        glass.setVgap(10);
        glass.setHgap(10);
        glass.setMaxWidth(1320);
        glass.setMaxHeight(530);
        rightRegion=new Region();
        rightRegion.setMinWidth(130);
        leftRegion=new Region();
        leftRegion.setMinWidth(130);
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
        boolean result=confirmExit.display("exit", "Are you sure close the Application?!");
        if(result){
            FileManager.saveUsers(LogOut);
            window.close();
        }
    }
    private void FilterIsPressed(){
        index=0;
        filter.setDisable(true);
        RentFilter Filter=new RentFilter();
        if(price.isSelected()&&model.isSelected()&&model.isSelected()){
            try{
                CarRentSystemException.mismatchString(model.getText());
                try{
                    Float Pricefrom=Float.parseFloat(initPriceField.getText());
                    Float Priceto=Float.parseFloat(endPriceField.getText());
                    if(Pricefrom>0&&Pricefrom<Priceto){
                        Book from=new Book();
                        Book to=new Book();
                        Integer day=Integer.parseInt(initDayField.getText());
                        Integer hour=Integer.parseInt(initHourField.getText());
                        from.setDay(day);
                        from.setHour(hour);
                        Integer day1=Integer.parseInt(endDayField.getText());
                        to.setDay(day1);
                        Integer hour1=Integer.parseInt(endHourField.getText());
                        to.setHour(hour1);
                        Calendar a=Calendar.getInstance();
                        if(from.getFullHours()<to.getFullHours()&&to.getFullHours()<721&&from.getDay()>=a.get(Calendar.DATE)&&from.getHour()>a.get(Calendar.HOUR_OF_DAY)){ 
                            Scanned=Filter.Merge(Pricefrom,Priceto,model.getText(),from,to);
                        }else{
                            CarRentSystemException.warningMessage("Wrong date");
                            filter.setDisable(false);
                        }
                    }
                    else{
                        CarRentSystemException.warningMessage("Wrong price");
                        filter.setDisable(false);
                    }
                }catch(Exception e){
                    CarRentSystemException.warningMessage("no available cars for this price");
                    filter.setDisable(false);
                }
            }
            catch(Exception e){
                CarRentSystemException.warningMessage("Wrong Values");
                initPriceField.setText("");
                endPriceField.setText("");
                filter.setDisable(false);
            }
        }
        else if(price.isSelected()&&model.isSelected()){
            try{
                CarRentSystemException.mismatchString(model.getText());
                try{
                    Float Pricefrom=Float.parseFloat(initPriceField.getText());
                    Float Priceto=Float.parseFloat(endPriceField.getText());
                    if(Pricefrom>0&&Pricefrom<Priceto){
                            Scanned=Filter.Merge(Pricefrom,Priceto,model.getText());
                    }
                    else{
                        CarRentSystemException.warningMessage("Wrong price");
                        filter.setDisable(false);
                    }
                }catch(Exception e){
                    CarRentSystemException.warningMessage("no available cars for this price");
                    filter.setDisable(false);
                }
            }
            catch(Exception e){
                CarRentSystemException.warningMessage("Wrong Values");
                initPriceField.setText("");
                endPriceField.setText("");
                modelField.setText("");
                filter.setDisable(false);
            }
        }
        else if(price.isSelected()&&date.isSelected()){
            try{
                try{
                Book from=new Book();
                Book to=new Book();
                Integer day=Integer.parseInt(initDayField.getText());
                Integer hour=Integer.parseInt(initHourField.getText());
                from.setDay(day);
                from.setHour(hour);
                Integer day1=Integer.parseInt(endDayField.getText());
                to.setDay(day1);
                Integer hour1=Integer.parseInt(endHourField.getText());
                to.setHour(hour1);
                Calendar a=Calendar.getInstance();
                
                Float Pricefrom=Float.parseFloat(initPriceField.getText());
                Float Priceto=Float.parseFloat(endPriceField.getText());
                    if(Pricefrom>0&&Pricefrom<Priceto){ 
                        if(from.getFullHours()<to.getFullHours()&&to.getFullHours()<721&&from.getDay()>=a.get(Calendar.DATE)&&from.getHour()>a.get(Calendar.HOUR_OF_DAY)){ 
                        
                        Scanned=Filter.Merge(Pricefrom, Priceto,from,to);
                        }
                        else{
                            CarRentSystemException.warningMessage("Wrong date");
                            filter.setDisable(false);
                        }
                    }
                    else{
                        CarRentSystemException.warningMessage("Wrong Price");
                        filter.setDisable(false);
                    }
                    
                }catch(Exception e){
                    CarRentSystemException.warningMessage("no available cars for this price");
                    filter.setDisable(false);
                }
            }
            catch(Exception e){
                CarRentSystemException.warningMessage("Wrong Values");
                initPriceField.setText("");
                endPriceField.setText("");
                filter.setDisable(false);
            }
        }
        else if(model.isSelected()&&date.isSelected()){
            try{
                CarRentSystemException.mismatchString(model.getText());
                try{
                    
                    Book from=new Book();
                    Book to=new Book();
                    Integer day=Integer.parseInt(initDayField.getText());
                    Integer hour=Integer.parseInt(initHourField.getText());
                    from.setDay(day);
                    from.setHour(hour);
                    Integer day1=Integer.parseInt(endDayField.getText());
                    to.setDay(day1);
                    Integer hour1=Integer.parseInt(endHourField.getText());
                    to.setHour(hour1);
                    Calendar a=Calendar.getInstance();
                    if(from.getFullHours()<to.getFullHours()&&to.getFullHours()<721&&from.getDay()>=a.get(Calendar.DATE)&&from.getHour()>a.get(Calendar.HOUR_OF_DAY)){
                            Scanned=Filter.Merge(model.getText(),from,to);
                        }else{
                            CarRentSystemException.warningMessage("Wrong date");
                            filter.setDisable(false);
                        }
                }catch(Exception e){
                    CarRentSystemException.warningMessage("no available cars for this price");
                    filter.setDisable(false);
                }
            }
            catch(Exception e){
                CarRentSystemException.warningMessage("Wrong Values");
                initPriceField.setText("");
                endPriceField.setText("");
                filter.setDisable(false);
            }
        }
        else if(price.isSelected()){
            try{
                Float from=Float.parseFloat(initPriceField.getText());
                Float to=Float.parseFloat(endPriceField.getText());
                try{
                    if(from<to&&from>=0){
                        Scanned=Filter.filterByPrice(from,to );
                    }
                    else{
                        CarRentSystemException.warningMessage("Wrong values");
                        filter.setDisable(false);
                    }
                }catch(Exception e){
                    CarRentSystemException.warningMessage("no available cars for this price");
                    filter.setDisable(false);
                }
            }
            catch(Exception e){
                CarRentSystemException.warningMessage("Wrong Values");
                initPriceField.setText("");
                endPriceField.setText("");
                filter.setDisable(false);
            }
        }
        else if(model.isSelected()){
            try{
                String x=model.getText();
                Scanned=Filter.filterByModel(x);
            }
            catch(Exception e){
                CarRentSystemException.warningMessage("We don't have any cars of this model");
                initPriceField.setText("");
                endPriceField.setText("");
                filter.setDisable(false);
            }
        }
        else if(date.isSelected()){
            try{
                Book from=new Book();
                Book to=new Book();
                Integer day=Integer.parseInt(initDayField.getText());
                Integer hour=Integer.parseInt(initHourField.getText());
                from.setDay(day);
                from.setHour(hour);
                Integer day1=Integer.parseInt(endDayField.getText());
                to.setDay(day1);
                Integer hour1=Integer.parseInt(endHourField.getText());
                to.setHour(hour1);
                Calendar a=Calendar.getInstance();
                if(from.getFullHours()<to.getFullHours()&&to.getFullHours()<721&&from.getDay()>=a.get(Calendar.DATE)&&from.getHour()>a.get(Calendar.HOUR_OF_DAY)){
                    Scanned=Filter.filterByDate(from,to);
                }
                else
                {
                    CarRentSystemException.warningMessage("Wrong Date");
                    filter.setDisable(false);
                }
                    
            }catch(Exception e){
                CarRentSystemException.warningMessage("no available cars for this Date");
                filter.setDisable(false);
            }
        }
        else{
            CarRentSystemException.warningMessage("choose Some kind of filter");
            filter.setDisable(false);
        }
    }
    private void DisplayCarinfo(CarForRent car){
        try{
        File file=new File(car.getImage());
        BufferedImage bufferedImage = ImageIO.read(file);
        Image image= SwingFXUtils.toFXImage(bufferedImage, null);
        forCar.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        }
        catch(Exception w){
            CarRentSystemException.warningMessage("Can't load car picture");
        }
    }
}