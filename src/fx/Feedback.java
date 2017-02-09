package GUI;

import CarRentSystem.CarRentSystemException;
import CarRentSystem.FileManager;
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
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.imageio.ImageIO;

public class Feedback implements EventHandler<ActionEvent>{
    private Stage window;
    private Scene scene;
    private BorderPane layout;
    private Label logo,copyRight,userPersonalImageLabel,forRate;
    private GridPane glass;
    private HBox header,footer;
    private Image pic;
    private User LoginUser;
    private ImageView Pic;
    private TextArea feedbackArea;
    private Button sendFeedback,sendRate,rent,buy,sell,message;
    private TextField search,rateField;
    private Region rightRegion,leftRegion,neededRegion;
    private VBox right,left,neededBox;
    private String picPath="male.png";
    
    public Feedback(User LoginUser,int choice)
    {
        this.LoginUser=LoginUser;
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
        rentItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new RentCar(LoginUser);
                window.close();
            }
        });
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
        });
        MenuItem feedback=new MenuItem("Send Feedback...");
        feedback.setDisable(true);
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
                                 recentActivity,separator4,settings,separator5,exit);
        MenuBar serviceBar=new MenuBar();
        serviceBar.getMenus().addAll(service);
        header.getChildren().addAll(logo,rent,buy,sell,search,region,message,userPersonalImageLabel,serviceBar);
        header.setAlignment(Pos.CENTER);
        layout=new BorderPane();
        footer=new HBox();
        footer.setPadding(new Insets(5,5,5,5));
        copyRight=new Label("all rights reserved. copyright © 2016");
        copyRight.setId("copyRight");
        footer.getChildren().add(copyRight);
        footer.setId("footer");
        footer.setAlignment(Pos.CENTER);
        ImageView background=new ImageView(new Image(getClass().getResourceAsStream("black.png")));
        background.setFitHeight(700);
        background.setFitWidth(1420);
        sendFeedback=new Button("Send");
        sendFeedback.setId("blackButton");
        feedbackArea=new TextArea();
        feedbackArea.setPromptText("type your feedback here.......");
        forRate=new Label();
        Image stars=new Image(getClass().getResourceAsStream("samaStar.png"));
        ImageView starsView=new ImageView(stars);
        forRate.setMinWidth(200);
        forRate.setMinHeight(77);
        forRate.setBackground(new Background(new BackgroundImage(stars, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        rateField=new TextField();
        rateField.setPromptText("enter a rate from 0 to 5");
        sendRate=new Button("Send Rate");
        glass= new GridPane();
        neededRegion=new Region();
        neededBox=new VBox();
        neededRegion.setMinHeight(140);
        neededBox.setMinHeight(140);
        neededBox.getChildren().add(neededRegion);
        glass.setConstraints(feedbackArea, 0, 0);
        glass.setConstraints(sendFeedback, 0, 1);
        glass.setConstraints(forRate, 0, 2);
        glass.setConstraints(rateField, 0, 3);
        glass.setConstraints(sendRate, 0, 4);
        glass.setConstraints(neededRegion, 0, 5);
        glass.getChildren().addAll(feedbackArea,sendFeedback,forRate,rateField,sendRate,neededRegion);
        forRate.setVisible(false);
        rateField.setVisible(false);
        sendRate.setVisible(false);
        feedbackArea.setVisible(false);
        sendFeedback.setVisible(false);
        if(choice==0) { //give feedback
            feedback.setDisable(true);
            feedbackArea.setVisible(true);
            sendFeedback.setVisible(true);
            sendFeedback.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        CarRentSystemException.mismatchString(feedbackArea.getText());
                        LoginUser.sendFeedback(Boolean.TRUE, feedbackArea.getText(), 0);
                        feedbackArea.setText(null);
                        CarRentSystemException.warningMessage("feedback sent");
                    } catch (Exception ex) {
                        CarRentSystemException.warningMessage("invalid message");
                        feedbackArea.setText(null);
                    }
                }
            });
                
        }
        else if(choice==1){ //give rate
            rate.setDisable(true);
            forRate.setVisible(true);
            rateField.setVisible(true);
            sendRate.setVisible(true);
            sendRate.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Float x=Float.parseFloat(rateField.getText());
                        if(x<5&&x>=0){
                            LoginUser.rate(Boolean.TRUE, x, 0);
                            CarRentSystemException.warningMessage("Rate sent");
                            rateField.setText("");
                            
                        }
                        else{
                            CarRentSystemException.warningMessage("invalid input");
                            rateField.setText("");
                        }
                    } catch (Exception ex) {
                        CarRentSystemException.warningMessage("invalid input");
                        rateField.setText("");
                    }
                }
            });
            
        }
        glass.setPadding(new Insets(20,20,20,20));
        glass.setVgap(10);
        glass.setHgap(10);
        glass.setMaxWidth(background.getFitWidth() - 100);
        glass.setMaxHeight(550);
        glass.setId("glass");
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
        layout.getChildren().addAll(background);
        layout.setRight(right);
        layout.setLeft(left);
        layout.setTop(header);
        layout.setCenter(glass);
        layout.setBottom(footer);
        scene=new Scene(layout);
        scene.getStylesheets().add(this.getClass().getResource("stylesheet.css").toExternalForm());
        window=new Stage();
        window.setScene(scene);
        window.show();
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                event.consume();
                closeProgram();
            }
        });
    }
    @Override
    public void handle(ActionEvent event) {
        
    }
    public void closeProgram(){
        boolean result=confirmExit.display("exit", "Are you sure close the Application?!");
         if(result){
            window.close();
        }
            
    }
    
}
