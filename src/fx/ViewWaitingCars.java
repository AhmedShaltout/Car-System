package GUI;

import CarRentSystem.CarRentSystemException;
import CarRentSystem.Confirmation;
import CarRentSystem.FileManager;
import Cars.CarForSell;
import Persons.Admin;
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
import javafx.stage.WindowEvent;
import javax.imageio.ImageIO;

public class ViewWaitingCars {
    public static Stage window;
    private Scene scene;
    private BorderPane layout;
    private HBox header,footer;
    private Button message,next,prev,delete,about,approve,addCarForRent,addCarForSell,ok1,ok2;
    private GridPane glass;
    private VBox right,left;
    private Region rightRegion,leftRegion;
    private Admin Shaltout;
    private HeadersAndFooters obj;
    private Label carShow,logo,userPersonalImageLabel,copyRight;
    private ArrayList<CarForSell> Scanned;
    private int index=0;
    private TextArea D_report,M_report;
    private String picPath="nnn.jpg";
    
    public ViewWaitingCars() 
    {
        index=0;
        message=new Button();
        next=new Button();
        prev=new Button();
        delete=new Button();
        about=new Button();
        approve=new Button();
        next.setVisible(false);
        prev.setVisible(false);
        delete.setVisible(false);
        about.setVisible(false);
        approve.setVisible(false);
        Shaltout = FileManager.loadAdmin();
        try {
            Scanned=FileManager.loadCarSell(Boolean.FALSE);
            CarRentSystemException.EmptyArraySell(Scanned);
            if(Scanned.size()>1){
                next.setVisible(true);
                DisplayCarinfo(Scanned.get(index));
            }
            
        } catch (Exception ex) {
            CarRentSystemException.warningMessage(ex.getMessage());
        }
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
        MenuItem aboutItem=new MenuItem("About...");
        aboutItem.setOnAction(new EventHandler<ActionEvent>() {
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
        aboutItem.setId("rentItem");
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
        carShow=new Label();
        carShow.setMinHeight(400);
        carShow.setMinWidth(550);
        next=new Button("Next");next.setId("prev");
        prev=new Button("Previous");prev.setId("prev");
        prev.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                next.setVisible(true);
                index--;
                if(index==0){
                    prev.setVisible(false);
                }
                
                DisplayCarinfo(Scanned.get(index));
            }
        });
        next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                prev.setVisible(true);
                index++;
                if(index==Scanned.size()-1){
                    next.setVisible(false);
                }
                DisplayCarinfo(Scanned.get(index));
            }
        });
        delete=new Button("Delete");delete.setId("next");
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CarForSell get = Scanned.get(index);
                Confirmation.acceptedToSell(get.getConfirmationEmail());
                get.setAvailable(Boolean.TRUE);
                FileManager.saveCarSell(get);
            }
        });
        about=new Button("About");about.setId("next");
        approve=new Button("Approve");approve.setId("prev");
        approve.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CarForSell get = Scanned.get(index);
                Scanned.remove(index);
                Confirmation.acceptedToSell(get.getConfirmationEmail());
                get.setAvailable(Boolean.TRUE);
                FileManager.saveCarSell(get);
            }
        });
        glass=obj.glassForAdmin();
        glass.setConstraints(prev, 0, 0);
        glass.setConstraints(carShow, 1, 0);
        glass.setConstraints(next, 2, 0);
        glass.setConstraints(delete, 4, 1);
        glass.setConstraints(about, 3, 1);
        glass.setConstraints(approve, 2, 1);
        glass.getChildren().addAll(prev,carShow,next,delete,about,approve);
        layout=obj.layoutForAdmin();
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
    public void closeProgram(){
        boolean result=confirmExit.display("exit", "Are you sure close the Application?!");
        if(result){
            FileManager.saveAdmin(Shaltout);
            window.close();
        }
    }
    private void DisplayCarinfo(CarForSell car){
        about.setVisible(true);
        about.setVisible(true);
        about.setText(car.getCarPrice().toString());
        delete.setVisible(true);
        approve.setVisible(true);
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
        carShow.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        }
        catch(Exception w){
            CarRentSystemException.warningMessage("Can't load car picture");
        }
    }
}