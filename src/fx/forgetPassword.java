package GUI;
import CarRentSystem.CarRentSystemException;
import CarRentSystem.Confirmation;
import CarRentSystem.FileManager;
import Persons.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
public class forgetPassword implements EventHandler<ActionEvent>{
    private Stage window;
    private Scene scene;
    private Button search;
    private Button cancel;
    private HBox footer,header;
    private GridPane glass2;
    private BorderPane layout;
    private Label copyRight,logo,email,text;
    private TextField forgetInput;
    forgetPassword()
    {
        window=new Stage();
        layout=new BorderPane();
        forgetInput=new TextField(null);
        forgetInput.setPromptText("type your eamil here...");
        forgetInput.setId("forgetInput");
        text=new Label("Find Your Account");
        email=new Label("Email: ");
        search=new Button("Send Password");
        search.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                String Email=forgetInput.getText();
                if(Email==null){
                    CarRentSystemException.warningMessage("Please enter your Email");
                }
                else{
                    if(CarRentSystemException.isEmail(Email)){
                    try {
                        CarRentSystemException.isUserThere(Email);
                        User Forget=FileManager.loadUsers(Email);
                        Confirmation.forgetPasswordConfirmation(Forget);
                        CarRentSystemException.warningMessage("Your password was sent to your Email");
                        window.close();
                        new LoginPag();
                        FileManager.saveUsers(Forget);
                        } catch (Exception ex) {
                            forgetInput.setText(null);
                            CarRentSystemException.warningMessage("There is no user with this Email");
                        }
                    }
                    else{
                        forgetInput.setText(null);
                        CarRentSystemException.warningMessage("Enter a valid Email");
                }
                }
                
            }
            
        });
        search.setId("forget");
        cancel=new Button("Cancel");
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
                new LoginPag();
            }
        });
        header=new HBox();
        header.setPadding(new Insets(5,5,5,5));
        header.setSpacing(5);
        logo=new Label();
        logo.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("logo.png"))));
        header.getChildren().add(logo);
        header.setId("header");
         footer=new HBox();
         footer.setPadding(new Insets(5,5,5,5));
         copyRight=new Label("all rights reserved. copyright © 2016");
         copyRight.setId("copyRight");
         footer.getChildren().add(copyRight);
         footer.setId("footer");
         footer.setAlignment(Pos.CENTER);
         ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("black.png")));
         imageView.setFitHeight(700);
         imageView.setFitWidth(1420);
         glass2= new GridPane();
         glass2.setPadding(new Insets(20,20,20,20));
         glass2.setVgap(10);
         glass2.setHgap(10);
         glass2.setMaxWidth(imageView.getFitWidth() - 100);
         glass2.setMaxHeight(600);
         glass2.setId("glass");
         search.setId("blackButton");
         cancel.setId("save");
         search.setPadding(new Insets(5,10,5,10));
         cancel.setPadding(new Insets(5,10,5,10));
         
         GridPane.setConstraints(text, 0, 16);
         GridPane.setConstraints(email, 0, 17);
         GridPane.setConstraints(forgetInput, 0, 18);
         GridPane.setConstraints(search, 2, 19);
         GridPane.setConstraints(cancel, 4, 19);
         
         glass2.getChildren().addAll(text,email,search,cancel,forgetInput);
         layout.getChildren().addAll(imageView);
         layout.setTop(header);
         layout.setCenter(glass2);
         layout.setBottom(footer);
        scene=new Scene(layout);
        scene.getStylesheets().add(this.getClass().getResource("stylesheet.css").toExternalForm());
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
        boolean result=confirmExit.display("Title", "Are you sure close the Application?!");
         if(result){
            
            window.close();
        }
            
    }
    @Override
    public void handle(ActionEvent event) {
    }
}
