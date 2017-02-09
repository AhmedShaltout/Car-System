package GUI;

import CarRentSystem.CarRentSystemException;
import CarRentSystem.FileManager;
import Payment.Cash;
import Payment.PaymentMethod;
import Payment.Visa;
import Payment.bankTransaction;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.imageio.ImageIO;

public class signUp implements EventHandler<ActionEvent>{
    private Stage window;
    private Scene scene;
    private BorderPane layout;
    private Label logo;
    private GridPane glass2;
    private Label copyRight,userPicture,fName,lName,address,userName,email,password,confirmPass,phoneNumber,state,genderLabel;
    private TextField fNameInput,lNameInput,addressInput,phoneNumberInput,emailInput;
    private PasswordField passwordInput,confirmPassInput;
    private Button browseImage,save,cancel;
    private ComboBox<String>stateBox;
    private ComboBox<String>gender;
    private HBox header,footer;
    private Image pic;
    private User NewUser;
    private String Path="male.png";
    private ImageView Pic;
    public signUp()
    {
        NewUser=new User();
        window=new Stage();
        userPicture=new Label();
        layout=new BorderPane();
        header=new HBox();
        header.setPadding(new Insets(5,5,5,5));
        header.setSpacing(5);
        logo=new Label();
        logo.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("logo.png"))));
        header.getChildren().add(logo);
        header.setId("header");
        save=new Button("Save");
        save.setId("blackButton");
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    CarRentSystemException.UserNotNull(NewUser);
                    FileManager.saveUsers(NewUser);
                } catch (Exception ex) {}
            }
        });
        cancel=new Button("Cancel");
        cancel.setId("save");
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
                new LoginPag();
            }
        });
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
         glass2.setMaxHeight(550);
         glass2.setId("glass");
         browseImage=new Button("Choose Image");
         browseImage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser=new FileChooser();
                File file = fileChooser.showOpenDialog(null);
                try {
                BufferedImage bufferedImage = ImageIO.read(file);
                pic = SwingFXUtils.toFXImage(bufferedImage, null);
                userPicture.setBackground(new Background(new BackgroundImage(pic, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                Path=file.getAbsolutePath();
                } catch (Exception ex) {
                    CarRentSystemException.warningMessage("You need to choose a picture");
                }  
            }
        });
        pic=new Image(getClass().getResourceAsStream(Path));
        Pic=new ImageView(pic);
        Background back=new Background(new BackgroundImage(pic, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
        userPicture.setBackground(back);
        userPicture.setMinHeight(150);
        userPicture.setMinWidth(150);
        userPicture.setAccessibleText("type");
        fName=new Label("First Name: ");
        lName=new Label("Last Name: ");
        address=new Label("Address: ");
        phoneNumber=new Label("PhoneNumber: ");
        email=new Label("E-mail: ");
        password=new Label("Password: ");
        confirmPass=new Label("Confirm Password: ");
        state=new Label("State: ");
        genderLabel=new Label("Gender: ");
        fNameInput=new TextField();
        fNameInput.setPromptText("type your first name...");
        lNameInput=new TextField();
        lNameInput.setPromptText("type your last name...");
        addressInput=new TextField();
        addressInput.setPromptText("type your address...");
        phoneNumberInput=new TextField();
        phoneNumberInput.setPromptText("type your phone number...");
        emailInput=new TextField();
        emailInput.setPromptText("type your email...");
        passwordInput=new PasswordField();
        passwordInput.setPromptText("type a password...");
        confirmPassInput=new PasswordField();
        confirmPassInput.setPromptText("re-type your password...");
        stateBox=new ComboBox<String>();
        stateBox.getItems().addAll("Alexandria","Beheira","Cairo","Giza");
        stateBox.setPromptText("choose your state");
        gender=new ComboBox<String>();
        gender.getItems().addAll("Male","Female");
        gender.setPromptText("gender");
        ComboBox <String>paymentMethod=new ComboBox<String>();
        paymentMethod.getItems().addAll("Bank transaction","Visa","Cash");
        paymentMethod.setPromptText("Payment Method");
        TextField visaNumber=new TextField();
        visaNumber.setPromptText("type your visa number");
        visaNumber.setVisible(false);
        TextField visaType=new TextField();
        visaType.setPromptText("type your visa type");
        visaType.setVisible(false);
        TextField bankName=new TextField();
        bankName.setPromptText("type your bank name");
        TextField AccountNumber=new TextField();
        AccountNumber.setPromptText("type your account number");
        AccountNumber.setVisible(false);
        bankName.setVisible(false);
        GridPane.setConstraints(browseImage, 0, 0);
        GridPane.setConstraints(userPicture, 1, 0);
        GridPane.setConstraints(fName, 0, 1);
        GridPane.setConstraints(fNameInput, 1, 1);
        GridPane.setConstraints(lName, 0, 2);
        GridPane.setConstraints(lNameInput, 1, 2);
        GridPane.setConstraints(address, 0, 3);
        GridPane.setConstraints(addressInput, 1, 3);
        GridPane.setConstraints(phoneNumber, 0, 4);
        GridPane.setConstraints(phoneNumberInput, 1, 4);
        GridPane.setConstraints(email, 0, 5);
        GridPane.setConstraints(emailInput, 1, 5);
        GridPane.setConstraints(password, 0, 6);
        GridPane.setConstraints(passwordInput, 1, 6);
        GridPane.setConstraints(confirmPass, 0, 7);
        GridPane.setConstraints(confirmPassInput, 1, 7);
        GridPane.setConstraints(state, 0, 8);
        GridPane.setConstraints(stateBox, 1, 8);
        GridPane.setConstraints(genderLabel, 0, 9);
        GridPane.setConstraints(gender, 1, 9);
        GridPane.setConstraints(save, 2, 10);
        GridPane.setConstraints(cancel, 3, 10);
        GridPane.setConstraints(paymentMethod, 4, 0);
        GridPane.setConstraints(visaType, 4, 1);
        GridPane.setConstraints(visaNumber, 4, 2);
        GridPane.setConstraints(bankName, 4, 3);
        GridPane.setConstraints(AccountNumber, 4, 4);
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                save.setDisable(true);
                boolean check=false;
                try{
                    CarRentSystemException.mismatchString(emailInput.getText());
                    check=true;
                }
                catch(Exception ex){
                    CarRentSystemException.warningMessage(ex.getMessage());
                    save.setDisable(false);
                }
                if(check){
                    String Email=emailInput.getText();
                    if(CarRentSystemException.isEmail(Email)){
                        boolean NotUser=false;
                        try {
                            CarRentSystemException.isUserThere(Email);
                            CarRentSystemException.warningMessage("this email is used");
                            emailInput.setText(null);
                            save.setDisable(false);
                        } catch (Exception ex) {
                            NotUser = true;
                        }
                        if(NotUser){
                            try{
                                CarRentSystemException.mismatchString(passwordInput.getText());
                                CarRentSystemException.mismatchString(addressInput.getText());
                                CarRentSystemException.mismatchString(confirmPassInput.getText());
                                CarRentSystemException.mismatchString(fNameInput.getText());
                                CarRentSystemException.mismatchString(lNameInput.getText());
                                CarRentSystemException.mismatchString(phoneNumberInput.getText());
                                CarRentSystemException.phoneNumberException(phoneNumberInput.getText());
                                CarRentSystemException.mismatchCheckBox(gender.getSelectionModel().getSelectedIndex());
                                CarRentSystemException.mismatchCheckBox(paymentMethod.getSelectionModel().getSelectedIndex());
                                CarRentSystemException.mismatchCheckBox(stateBox.getSelectionModel().getSelectedIndex());
                                String Fname=fNameInput.getText();
                                String LName=lNameInput.getText();
                                String Address=addressInput.getText();
                                String PhoneNumber=phoneNumberInput.getText();
                                String Password=passwordInput.getText();
                                String CnonfirmPass=confirmPassInput.getText();
                                int State=stateBox.getSelectionModel().getSelectedIndex();
                                String state;
                                switch (State) {
                                    case 0:
                                        state="Alexandria";
                                        break;
                                    case 1:
                                        state="Beheira";
                                        break;
                                    case 2:
                                        state="Cairo";
                                        break;
                                    default:
                                        state="Giza";
                                        break;
                                }
                                String gend;
                                int Gender=gender.getSelectionModel().getSelectedIndex();
                                if(Gender==0)
                                    gend="Male";
                                else
                                    gend="Female";
                                int PaymentMethod= paymentMethod.getSelectionModel().getSelectedIndex();
                                PaymentMethod Payment;
                                switch (PaymentMethod) {
                                    case 0:
                                        Payment= new bankTransaction();
                                        break;
                                    case 1:
                                        Payment=new Visa();
                                        break;
                                    default:
                                        Payment= new Cash();
                                        break;
                                }
                                if(Password.equals(CnonfirmPass)){
                                    NewUser=new User();
                                    NewUser.editProfile(Fname, LName, gend, Address, state, Email, Password, PhoneNumber, Path,Payment);
                                    FileManager.saveUsers(NewUser);
                                    CarRentSystemException.warningMessage("You created account successfuly");
                                    window.close();
                                    save.setDisable(false);
                                    new LoginPag();
                                }
                                else{
                                    CarRentSystemException.warningMessage("Passwords don't match");
                                    passwordInput.setText(null);
                                    confirmPassInput.setText(null);
                                    save.setDisable(false);
                                }
                            }
                            catch(Exception e){
                                emailInput.setText(null);
                                passwordInput.setText(null);
                                confirmPassInput.setText(null);
                                fNameInput.setText(null);
                                lNameInput.setText(null);
                                phoneNumberInput.setText(null);
                                addressInput.setText(null);
                                save.setDisable(false);
                                CarRentSystemException.warningMessage(e.getMessage());
                            }
                        }
                    }
                    else{
                        CarRentSystemException.warningMessage("Please Enter a valid Email");
                            emailInput.setText(null);
                            passwordInput.setText(null);
                            confirmPassInput.setText(null);
                            fNameInput.setText(null);
                            lNameInput.setText(null);
                            phoneNumberInput.setText(null);
                            addressInput.setText(null);
                            save.setDisable(false);
                    }
                }
            }
                
            
        });
        glass2.getChildren().addAll(browseImage,userPicture,fName,fNameInput,lName,lNameInput,address,addressInput,phoneNumber,phoneNumberInput,email,emailInput,password,
                                    passwordInput,confirmPass,confirmPassInput,
                                    state,stateBox,genderLabel,gender,save,cancel,paymentMethod,visaType,visaNumber,bankName,AccountNumber);
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
    @Override
    public void handle(ActionEvent event) {
        
    }
    public void closeProgram(){
        boolean result=confirmExit.display("Title", "Are you sure close the Application?!");
         if(result){
            window.close();
        }
            
    }
    
}
