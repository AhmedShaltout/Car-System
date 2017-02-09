package GUI;

import CarRentSystem.FileManager;
import Persons.Admin;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

public class Search_Approve
{
    private Stage window;
    private Scene scene;
    private BorderPane layout;
    private HBox header,footer;
    private Label logo,copyRight,userPersonalImageLabel,carView,carView2,carView3;
    private Button rent,buy,sell,message,next,prev,next2,prev2,next3,prev3,delete1,
                   about,about2,about3,delete3,delete2,Approve;
    private TextField search;
    private GridPane glass;
    private Region rightRegion,leftRegion;
    private VBox right,left;
    private HeadersAndFooters obj;
    private Admin Shaltout;
    
    public Search_Approve(Admin Shaltout)//ArrayList<CarForSell>Cars
    {
        this.Shaltout=Shaltout;
        header=obj.headerForAdmin(this.Shaltout);
        footer=new HBox();
        footer.setPadding(new Insets(5,5,5,5));
        footer.setId("footer");
        copyRight=new Label("all rights reserved. copyright © 2016");
        footer.setAlignment(Pos.CENTER);
        copyRight.setId("copyRight");
        footer.getChildren().add(copyRight);
        //////////////////////////////////////////
        next=new Button("Next");
        next.setId("next");
        prev=new Button("Previous");
        prev.setId("next");
        delete1=new Button("Delete");
        delete1.setId("next");
        about=new Button("About");
        about.setId("next");
        carView=new Label();
        carView.setMinHeight(210);
        carView.setMinWidth(370);
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
        delete2=new Button("Delete");
        delete2.setId("prev");
        about2=new Button("About");
        about2.setId("prev");
        carView2=new Label();
        carView2.setMinHeight(210);
        carView2.setMinWidth(370);
        //code el-Background elTania kolo
        String Path2="green.jpg";
        Image pic2=new Image(getClass().getResourceAsStream(Path2));
        ImageView Pic2=new ImageView(pic2);
        Background background2=new Background(new BackgroundImage(pic2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
        carView2.setBackground(background2);
        ////////////////////////////////////////  
        next3=new Button("Next");
        next3.setId("prev");
        prev3=new Button("Previous");
        prev3.setId("prev");
        delete3=new Button("Delete");
        delete3.setId("prev");
        about3=new Button("About");
        about3.setId("prev");
        Approve=new Button("Approve");
        Approve.setId("prev");
        carView3=new Label();
        carView3.setMinHeight(210);
        carView3.setMinWidth(370);
        //code el-Background elTalta kolo
        String Path3="Blogo.png";
        Image pic3=new Image(getClass().getResourceAsStream(Path3));
        ImageView Pic3=new ImageView(pic3);
        Background background3=new Background(new BackgroundImage(pic3, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
        carView3.setBackground(background3);
        /////////////////////////////////
        glass=new GridPane();
        glass.setConstraints(prev, 0, 0);
        glass.setConstraints(carView, 1, 0);
        glass.setConstraints(next, 2, 0);
        glass.setConstraints(prev2, 4, 0);
        glass.setConstraints(carView2, 5, 0);
        glass.setConstraints(next2, 6, 0);
        
        glass.setConstraints(delete1, 0, 1);
        glass.setConstraints(about, 1, 1);
        
        glass.setConstraints(delete2, 6, 1);
        glass.setConstraints(about2, 5, 1);
        
        glass.setConstraints(prev3, 0, 2);
        glass.setConstraints(carView3, 1, 2);
        glass.setConstraints(next3, 2, 2);
        glass.setConstraints(delete3, 2, 3);
        glass.setConstraints(about3, 1, 3);
        glass.setConstraints(Approve, 0, 3);
        
        glass.getChildren().addAll(carView,next,prev,delete1,about,
                                   carView2,next2,prev2,delete2,about2,
                                   carView3,next3,prev3,delete3,about3,Approve);
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
        window=new Stage();
        window.setScene(scene);
        window.show();
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                event.consume();
                closeProgram(Shaltout);
            }
        });
        
    }
    public void closeProgram(Admin LogOut){
        boolean result=confirmExit.display("exit", "Are you sure close the Application?!");
        if(result) {
            FileManager.saveAdmin(LogOut);
            window.close();
        }
    }
    
}