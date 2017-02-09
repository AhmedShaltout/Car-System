package GUI;

import CarRentSystem.FileManager;
import Persons.User;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

public class Profile {
    public static Stage window;
    private Scene scene;
    private BorderPane layout;
    private HBox header,footer;
    private Label logo,copyRight,userPersonalImageLabel,forDisplayCars;
    private Button rent,buy,sell,message;
    private TextField search;
    private GridPane glass;
    private VBox right,left;
    private User LoginUser;
    private Region rightRegion,leftRegion;
    private String Path="Car3.png";
    private ImageView Pic;
    private Image pic;
    private HeadersAndFooters obj;

    Profile(User LoginUser) {
        this.LoginUser=LoginUser;
        obj=new HeadersAndFooters();
        header=obj.headerForUser(this.LoginUser);
        footer=obj.footerForUser();
        glass=new GridPane();
        glass.setId("glass");
        glass.setPadding(new Insets(30,5,20,5));
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
        forDisplayCars=new Label();
        forDisplayCars.setMinWidth(900);
        forDisplayCars.setMinHeight(440);
        pic=new Image(getClass().getResourceAsStream(Path));
        Pic=new ImageView(pic);
        Background back=new Background(new BackgroundImage(pic, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
        forDisplayCars.setBackground(back);
        glass.setConstraints(forDisplayCars, 0, 1);
        glass.getChildren().addAll(forDisplayCars);
        
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
    
}