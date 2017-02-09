package GUI;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.imageio.ImageIO;

public class RecentActivity {
    private User LoginUser;
    private Stage window;
    private Scene scene;
    private BorderPane layout;
    private HBox header,footer;
    private Label logo,copyRight,userPersonalImageLabel;
    private Button rent,buy,sell,message;
    private TextField search;
    private TextArea Activities;
    private StackPane glass;
    private Region rightRegion,leftRegion;
    private VBox right,left;
    private String picPath="male.png";
    
    RecentActivity(User LoginUser) {
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
        recentActivity.setDisable(true);
        MenuItem rescheduleItem=new MenuItem("Reschedule Your Rent...");
        rescheduleItem.setOnAction(new EventHandler<ActionEvent>() {
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
        rescheduleItem.setId("rentItem");
        feedback.setId("rentItem");
        rate.setId("rentItem");
        recentActivity.setId("rentItem");
        settings.setId("rentItem");
        exit.setId("rentItem");
        service.getItems().addAll(rentItem,buyItem,sellItem,separator1,feedback,rate,separator2,rescheduleItem,separator3,
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
        glass=new StackPane();
        Activities=new TextArea(LoginUser.viewRecentActivity().toString());
        Activities.setEditable(false);
        glass.setId("glass");
        glass.setPadding(new Insets(20,20,20,20));
        glass.getChildren().add(Activities);
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
    
}
