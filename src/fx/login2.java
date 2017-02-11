package fx;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class login2 {
	/////////////////////Login Glass Objects/////////////////////
	TextField mailInput;
	PasswordField passwordInput;
    Label mail,password,copyRight;
    Button login,signUp,forget;
    HBox footer;
    GridPane glass;
    BorderPane layout;
    Scene scene;
    Stage window;
    public login2(){
    	///////////////////////Footer Components////////////////////////
    	copyRight=new Label("all rights reserved. copyright © 2016");
    	copyRight.setId("copyRight");
    	footer=new HBox();
    	footer.getChildren().add(copyRight);
    	footer.setId("footer");
    	///////////////////Login Glass Components//////////////////////
    	mailInput=new TextField();
    	mailInput.setPromptText("type your mail here");
    	passwordInput=new PasswordField();
    	passwordInput.setPromptText("type your password here");
    	mail=new Label("E-Mail: ");
    	password=new Label("Password: ");
    	login=new Button("Login");
    	signUp=new Button("Sign Up");
    	forget=new Button("Forget Password");
    	
    	glass=new GridPane();
    	glass.setPadding(new Insets(200,200,200,100));
    	GridPane.setConstraints(mail, 0,0);
    	GridPane.setConstraints(mailInput, 1,0);
    	GridPane.setConstraints(password, 0,1);
    	GridPane.setConstraints(passwordInput, 1,1);
    	GridPane.setConstraints(login, 2,2);
    	glass.getChildren().addAll(mail,mailInput,password,passwordInput,login);
    	
    	/////////////////////Layout Components////////////////////////
    	ImageView layoutBackground= new ImageView(new Image(getClass().getResourceAsStream("black.png")));
    	layoutBackground.setFitHeight(700);
    	layoutBackground.setFitWidth(1420);
    	layout=new BorderPane();
    	layout.getChildren().add(layoutBackground);
    	layout.setBottom(footer);
    	layout.setCenter(glass);
    	////////////////////Scene Components/////////////////////////
    	scene=new Scene(layout);
    	window=new Stage();
    	window.setResizable(false);
    	window.setScene(scene);
    	window.setMinHeight(700);
    	window.setMinWidth(1420);
    	
    }
}
