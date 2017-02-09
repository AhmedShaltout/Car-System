package GUI;

import CarRentSystem.FileManager;
import Persons.User;
import java.util.ArrayList;
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

public class Search_Buy_Rent
{
    private User LoginUser;
    private Stage window;
    private Scene scene;
    private BorderPane layout;
    private HBox header,footer;
    private Label logo,copyRight,userPersonalImageLabel,carView,carView2;
    private Button rent,buy,sell,message,next,prev,next2,prev2,about,about2,Buy,Rent;
    private TextField search;
    private GridPane glass;
    private Region rightRegion,leftRegion;
    private VBox right,left;
    private HeadersAndFooters obj;
    
    public Search_Buy_Rent(User LoginUser)//ArrayList<CarForSell>Cars
    {
        this.LoginUser=LoginUser;
        window=new Stage();
        header=obj.headerForUser(LoginUser);
        footer=obj.footerForUser();
        //////////////////////////////////////////
        next=new Button("Next");
        next.setId("prev");
        prev=new Button("Previous");
        prev.setId("prev");
        about=new Button("About");
        about.setId("next");
        Buy=new Button("Buy");
        Buy.setId("next");
        carView=new Label();
        carView.setMinHeight(220);
        carView.setMinWidth(500);
        //code el-Background elOla kolo
        String Path1="Blogo.png";
        Image pic1=new Image(getClass().getResourceAsStream(Path1));
        ImageView Pic1=new ImageView(pic1);
        Background background=new Background(new BackgroundImage(pic1, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
        carView.setBackground(background);
        /////////////////////////////////////////
        next2=new Button("Next");
        next2.setId("prev");
        prev2=new Button("Previous");
        prev2.setId("prev");
        about2=new Button("About");
        about2.setId("next");
        Rent=new Button("Rent");
        Rent.setId("next");
        carView2=new Label();
        carView2.setMinHeight(220);
        carView2.setMinWidth(500);
        //code el-Background elTania kolo
        String Path2="green.jpg";
        Image pic2=new Image(getClass().getResourceAsStream(Path2));
        ImageView Pic2=new ImageView(pic2);
        Background background2=new Background(new BackgroundImage(pic2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
        carView2.setBackground(background2);
        ////////////////////////////////////////     
        glass=new GridPane();
        glass.setConstraints(prev, 0, 0);
        glass.setConstraints(carView, 1, 0);
        glass.setConstraints(next, 2, 0);
        glass.setConstraints(Buy, 4, 0);
        glass.setConstraints(about, 3, 0);
        
        glass.setConstraints(prev2, 0, 1);
        glass.setConstraints(carView2, 1, 1);
        glass.setConstraints(next2, 2, 1);
        glass.setConstraints(Rent, 4, 1);
        glass.setConstraints(about2, 3, 1);
        glass.getChildren().addAll(carView,next,prev,about,Buy,
                                   carView2,next2,prev2,about2,Rent);
        ////////////////////////////////////////
        glass.setId("glass");
        glass.setPadding(new Insets(20,20,20,20));
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
        if(result)
            FileManager.saveUsers(LogOut);
            window.close();
    }
    
}