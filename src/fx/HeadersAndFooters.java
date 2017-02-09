package GUI;
import CarRentSystem.CarRentSystemException;
import CarRentSystem.FileManager;
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
import javafx.scene.control.TextArea;
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
import javax.imageio.ImageIO;

public class HeadersAndFooters {
    private Stage window;
    private Scene scene;
    private BorderPane layout;
    private HBox header,footer;
    private Label logo,copyRight,userPersonalImageLabel;
    private Button addCarForSell,addCarForRent,about,message,ok1,ok2;
    private GridPane glass;
    private VBox right,left;
    private Region rightRegion,leftRegion;
    private Admin Shaltout;
    private TextArea D_report,M_report;
    ////////////////////For User Only//////////////
    private Button rent,buy,sell;
    private String PicPath="male2.png";
    private User LoginUser;
    
    public HBox headerForAdmin(Admin Shaltout){
        this.Shaltout=Shaltout;
        header=new HBox();
        header.setPadding(new Insets(5,5,5,5));
        header.setSpacing(10);
        header.setId("header");
        logo=new Label();
        logo.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("logo.png"))));
        addCarForSell=new Button("Add Car For Sell");
        addCarForSell.setId("AdminmainOrders");
        addCarForRent=new Button("Add Car For Rent");
        addCarForRent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AdminPage.window.close();
                new addCarForRent(Shaltout);
            }
        });
        addCarForRent.setId("AdminmainOrders");
        addCarForSell.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AdminPage.window.close();
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
        userPersonalImageLabel.setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("nnn.jpg")), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        userPersonalImageLabel.setMinHeight(100);
        userPersonalImageLabel.setMinWidth(100);
        
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
        MenuItem unavailableCars=new MenuItem("Show Unavailable Cars...");
        MenuItem addCarForRentItem=new MenuItem("Add Car For Rent...");
        MenuItem addCarForSellItem=new MenuItem("Add Car For Sell...");
        MenuItem aboutItem=new MenuItem("About...");
        MenuItem viewAllCars=new MenuItem("View Waiting Cars...");
        MenuItem viewCompanyFeedback=new MenuItem("View Company Feedback...");
        MenuItem viewCompanyRate=new MenuItem("Our Rate: "+Shaltout.getCompanyRate());
        MenuItem companyBalance=new MenuItem("Our Balance Now: "+Shaltout.getBalance()+" $");
        MenuItem exit=new MenuItem("Log Out");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AdminPage.window.close();
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
        exit.setId("rentItem");
        viewCompanyRate.setId("rentItem");
        companyBalance.setId("rentItem");
        service.getItems().addAll(dailyReport,monthlyReport,separator1,addCarForRentItem,addCarForSellItem,viewAllCars,
                unavailableCars,separator2,viewCompanyFeedback,viewCompanyRate,companyBalance,separator3,exit);
        MenuBar serviceBar=new MenuBar();
        serviceBar.getMenus().addAll(service);
        header.getChildren().addAll(logo,addCarForSell,addCarForRent,about,message,userPersonalImageLabel,serviceBar);
        header.setAlignment(Pos.CENTER);
        return header;
    }
    public TextArea dReportForAdmin(){
        D_report=new TextArea();
        D_report.setMaxHeight(200);
        D_report.setMaxWidth(500);
        D_report.setVisible(false);
        return D_report;
    }
    public TextArea mReportForAdmin(){
        M_report=new TextArea();
        M_report.setMaxHeight(200);
        M_report.setMaxWidth(500);
        M_report.setVisible(false);
        return M_report;
    }
    public Button ok1ForAdmin(){
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
        return ok1;
    }
    public Button ok2ForAdmin(){
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
        return ok2;
    }
    public HBox footerForAdmin(){
        footer=new HBox();
        footer.setPadding(new Insets(5,5,5,5));
        footer.setId("footer");
        copyRight=new Label("all rights reserved. copyright © 2016");
        footer.setAlignment(Pos.CENTER);
        copyRight.setId("copyRight");
        footer.getChildren().add(copyRight);
        return footer;
    }
    public GridPane glassForAdmin(){
        glass=new GridPane();
        glass.setId("glass");
        glass.setPadding(new Insets(20,10,20,10));
        glass.setVgap(10);
        glass.setHgap(10);
        glass.setMaxWidth(1320);
        glass.setMaxHeight(530);
        return glass;
    }
    public BorderPane layoutForAdmin(){
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
        return layout;
    }
    public void closeProgram(){
        boolean result=confirmExit.display("exit", "Are you sure close the Application?!");
        if(result){
            FileManager.saveAdmin(Shaltout);
            AdminPage.window.close();
        }
    } 
    public HBox headerForUser(User LoginUser){
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
              Profile.window.close();
              new RentCar(LoginUser);
            }
        });
        sell=new Button("Sell");
        sell.setId("mainOrders");
        sell.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Profile.window.close();
                new SellCar(LoginUser);
            }
        });
        buy=new Button("Buy");
        buy.setId("mainOrders");
        buy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Profile.window.close();
                new BuyCar(LoginUser);
            }
        });
        rent.setPadding(new Insets(25,20,25,20));
        buy.setPadding(new Insets(25,20,25,20));
        sell.setPadding(new Insets(25,20,25,20));
        message=new Button();
        message.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("wonderful.png"))));
        message.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Profile.window.close();
                new AllMessages(LoginUser);
            }
        });
        Region region= new Region();
        region.resize(700, 100);
        region.setMinWidth(135);
        userPersonalImageLabel=new Label();
        userPersonalImageLabel.setMinHeight(100);
        userPersonalImageLabel.setMinWidth(100);
        userPersonalImageLabel.setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream(PicPath)), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        try{
            File file=new File(LoginUser.getPic());
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image= SwingFXUtils.toFXImage(bufferedImage, null);
            userPersonalImageLabel.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        }
        catch(Exception w){
        }
        Menu service=new Menu(this.LoginUser.getFname());
        service.setId("service");
        MenuItem rentItem=new MenuItem("Rent Car...");
        rentItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                Profile.window.close();
                new RentCar(LoginUser);
            }
        });
        MenuItem buyItem=new MenuItem("Buy Car...");
        buyItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Profile.window.close();
                new BuyCar(LoginUser);
            }
        });
        MenuItem sellItem=new MenuItem("Sell Car...");
        sellItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Profile.window.close();
                new SellCar(LoginUser);
            }
        });
        MenuItem exit=new MenuItem("Log Out");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileManager.saveUsers(LoginUser);
                Profile.window.close();
                new LoginPag();

            }
        });
        MenuItem recentActivity=new MenuItem("Recent Activity...");
        recentActivity.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Profile.window.close();
                new RecentActivity(LoginUser);
            }
        });
        MenuItem reschedule=new MenuItem("Reschedule Your Rent...");
        reschedule.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Profile.window.close();
                new RescheduleRenr(LoginUser);
            }
        });
        MenuItem feedback=new MenuItem("Send Feedback...");
        feedback.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Profile.window.close();
                new Feedback(LoginUser,0);
            }
        });
        MenuItem rate=new MenuItem("Rate Us...");
        rate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Profile.window.close();
                new Feedback(LoginUser,1);
            }
        });
        MenuItem settings=new MenuItem("Settings");
        settings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Profile.window.close();
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
        header.getChildren().addAll(logo,rent,buy,sell,region,message,userPersonalImageLabel,serviceBar);
        header.setAlignment(Pos.CENTER);
        return header;
    }
    public HBox footerForUser(){
        footer=new HBox();
        footer.setPadding(new Insets(5,5,5,5));
        footer.setId("footer");
        copyRight=new Label("all rights reserved. copyright © 2016");
        footer.setAlignment(Pos.CENTER);
        copyRight.setId("copyRight");
        footer.getChildren().add(copyRight);
        return footer;
    }
    
 
}
