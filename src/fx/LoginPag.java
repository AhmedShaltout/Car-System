//package fx;
//
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.HBox;
//import javafx.stage.Stage;
//import javafx.stage.WindowEvent;
//import person.User;
//import system.CarRentSystemException;
//
//public class LoginPag implements EventHandler<ActionEvent>{
//    TextField mailInput,passwordInput;
//    Label mail,password;
//    Button login,signUp,forget;
//    HBox footer;
//    Stage window;
//    public LoginPag(){
//        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
//            @Override
//            public void handle(WindowEvent event) {
//                event.consume();
//                closeProgram();
//            }
//        });
//        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("black.png")));
//          imageView.setFitHeight(700);
//          imageView.setFitWidth(1420);
//     mail=new Label("E-Mail: ");
//     password=new Label("Password: ");
//     mailInput= new TextField();
//     mailInput.setPromptText("type your email .");
//    passwordInput=new PasswordField();
//    passwordInput.setPromptText("type your password .");
//     login=new Button("Login");
//     login.setId("login");
//     login.setOnAction(new EventHandler<ActionEvent>() {
//        
//        private User LoginUser;
//        @Override
//        public void handle(ActionEvent event) {
//            login.setDisable(true);
//            if(passwordInput.getText()!=null&&mailInput.getText()!=null){
//                String Email=mailInput.getText();
//                if(passwordInput.getText().equals("123")&Email.equals("Shaltout")){
//                window.close();
//                new AdminPage();
//                login.setDisable(false);
//                }
//                else if(CarRentSystemException.isEmail(Email)){
//                    if(userExists(Email)){
//                        this.LoginUser=FileManager.loadUsers(Email);
//                        String Password=passwordInput.getText();
//                        if(LoginUser.getPassword().equals(Password)){
//                            window.close();
//                            new Profile(LoginUser);
//                        }
//                        else{
//                            CarRentSystemException.warningMessage("Password is not correct");
//                            passwordInput.setText(null);
//                            login.setDisable(false);
//                        }
//                    }
//                    else{
//                        CarRentSystemException.warningMessage("Email isn't used");
//                        mailInput.setText(null);
//                        login.setDisable(false);
//                    }
//                }
//                else{
//                    CarRentSystemException.warningMessage("Enter a valid Email");
//                    mailInput.setText(null);
//                    passwordInput.setText(null);
//                    login.setDisable(false);
//                }
//            }
//            else if(passwordInput.getText()==null&&mailInput.getText()!=null){
//                CarRentSystemException.warningMessage("Please Enter Your Password");
//                passwordInput.setText(null);
//                login.setDisable(false);
//            }
//            else if(passwordInput.getText()!=null&&mailInput.getText()==null){
//                CarRentSystemException.warningMessage("Please Enter Your Email");
//                passwordInput.setText(null);
//                mailInput.setText(null);
//                login.setDisable(false);
//                
//            }
//            else{
//                CarRentSystemException.warningMessage("Enter a Valid Email and Password");
//                login.setDisable(false);
//            }
//       }
//   });
//
//     signUp=new Button("Sign Up >>");
//     signUp.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                window.close();
//                new signUp();
//            }
//        });
//     forget=new Button("Forgot Password");
//     forget.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                window.close();
//                new forgetPassword();
//            }
//        });
//     footer=new HBox();
//     footer.setPadding(new Insets(5,5,5,5));
//     Label copyRight=new Label("all rights reserved. copyright © 2016");
//     copyRight.setId("copyRight");
//     footer.getChildren().add(copyRight);
//     footer.setAlignment(Pos.CENTER);
//     footer.setId("footer");
//    GridPane glass = new GridPane();
//    glass.setPadding(new Insets(20,20,20,20));
//    glass.setHgap(10);
//    glass.setVgap(10);
//    GridPane.setConstraints(mail, 0, 13);
//    GridPane.setConstraints(mailInput, 0, 15);
//    GridPane.setConstraints(password, 0, 17);
//    GridPane.setConstraints(passwordInput, 0, 19);
//    GridPane.setConstraints(forget, 0, 22);
//    GridPane.setConstraints(signUp, 0, 23);
//    GridPane.setConstraints(login, 2, 22);
//    forget.setId("forget");
//    signUp.setId("forget");
//    glass.setId("glass");
//    glass.setMaxWidth(imageView.getFitWidth() - 60);
//    glass.setMaxHeight(imageView.getFitHeight() - 40);
//    glass.getChildren().addAll(mail,mailInput,password,passwordInput,login,signUp,forget);
//    
//    BorderPane layout = new BorderPane();
//    layout.getChildren().addAll( imageView);
//    layout.setBottom(footer);
//    layout.setCenter(glass);
//    layout.setId("layout");
//    Scene scene=new Scene(layout);
//    scene.getStylesheets().add(this.getClass().getResource("stylesheet.css").toExternalForm());
//    window.setScene(scene);
//    window.show();
//    }
//    public void closeProgram(){
//        boolean result=confirmExit.display("Title", "Are you sure close the Application?!");
//        if(result)
//             window.close();
//    }
//
//    @Override
//    public void handle(ActionEvent event) {
//    }
//    private boolean userExists(String Email) {
//        try{
//            CarRentSystemException.isUserThere(Email);
//            return true;
//        }
//        catch(Exception ex){
//        }
//        return false;
//    }
//}
