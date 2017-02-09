package GUI;

import CarRentSystem.CarRentSystemException;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.imageio.ImageIO;

public class SettingsPage {
    private User LoginUser;
    private Stage window;
    private Scene scene;
    private BorderPane layout;
    private GridPane glass;
    private HBox header,footer;
    private VBox right,left;
    private Region rightRegion,leftRegion,region;
    private Label copyRight,logo,userPersonalImageLabel,
                  userFname,userLname,userPasswod,userPhone,userAddress,userState,userGender,userEmail;
    private Button rent,buy,sell,message,saveFName,saveLName,savePhone,savePassword,saveEmail,saveAddress,saveImage,
                   saveState,saveGender,editFname,editLname,editPhone,editPassword,editEmail,editAddress,
                   editImage,editState,editGender;
    private TextField search,fNameChange,lNameChange,addressChange,phonechange,emailChange,passChange,
                      passConfirm1,passConfirm2,passConfirm3,passConfirm4,passConfirm5,passConfirm6,passConfirm7,
                      passConfirm8,passConfirm9,passConfirm10;
    private MenuBar serviceBar;
    private String UserPicPath,picPath="male.png";
    private ComboBox<String>stateBox;
    private ComboBox<String>genderBox;
    public SettingsPage(User LoginUser) {
        
        this.LoginUser=LoginUser;
        header=new HBox();
        header.setPadding(new Insets(5,5,5,5));
        header.setSpacing(10);
        header.setId("header");
        header.setAlignment(Pos.CENTER);
        logo=new Label();
        logo.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("logo.png"))));
        rent=new Button("Rent");
        rent.setId("mainOrders");
        rent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
                new RentCar(LoginUser);
            }
        });
        sell=new Button("Sell");
        sell.setId("mainOrders");
        sell.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
                new SellCar(LoginUser);
            }
        });
        buy=new Button("Buy");
        buy.setId("mainOrders");
        buy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
                new BuyCar(LoginUser);
            }
        });
        rent.setPadding(new Insets(25,20,25,20));
        buy.setPadding(new Insets(25,20,25,20));
        sell.setPadding(new Insets(25,20,25,20));
        search=new TextField();
        search.setId("search");
        Region region= new Region();
        region.resize(700, 100);
        region.setMinWidth(150);
        message=new Button();
        message.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("wonderful.png"))));
        message.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
                new AllMessages(LoginUser);
            }
        });
        Image image=new Image(getClass().getResourceAsStream("male2.png"));
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
                window.close();
                new RentCar(LoginUser);
            }
        });
        MenuItem buyItem=new MenuItem("Buy Car...");
        buyItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
                new BuyCar(LoginUser);
            }
        });
        MenuItem sellItem=new MenuItem("Sell Car...");
        sellItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
                new SellCar(LoginUser);
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
        recentActivity.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
                new RecentActivity(LoginUser);
            }
        });
        MenuItem reschedule=new MenuItem("Reschedule Your Rent...");
        reschedule.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
                new RescheduleRenr(LoginUser);
            }
        });
        MenuItem feedback=new MenuItem("Send Feedback...");
        feedback.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
                new Feedback(LoginUser,0);
            }
        });
        MenuItem rate=new MenuItem("Rate Us...");
        rate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
                new Feedback(LoginUser,1);
            }
        });
        MenuItem settings=new MenuItem("Settings");
        settings.setDisable(true);
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
        settings.setVisible(false);
        exit.setId("rentItem");
        service.getItems().addAll(rentItem,buyItem,sellItem,separator1,feedback,rate,separator2,reschedule,separator3,
                                 recentActivity ,separator4,settings,separator5,exit);
        MenuBar serviceBar=new MenuBar();
        serviceBar.getMenus().addAll(service);
        header.getChildren().addAll(logo,rent,buy,sell,search,region,message,userPersonalImageLabel,serviceBar);
        footer=new HBox();
        footer.setPadding(new Insets(5,5,5,5));
        footer.setId("footer");
        copyRight=new Label("all rights reserved. copyright © 2016");
        footer.setAlignment(Pos.CENTER);
        copyRight.setId("copyRight");
        footer.getChildren().add(copyRight);
        rightRegion=new Region();
        rightRegion.setMinWidth(150);
        leftRegion=new Region();
        leftRegion.setMinWidth(150);
        right=new VBox();
        right.setId("header");
        right.getChildren().add(rightRegion);
        left=new VBox();
        left.setId("header");
        left.getChildren().add(leftRegion);
        glass=new GridPane();
        
        userFname=new Label("First Name: "+LoginUser.getFname());
        userLname=new Label("Last Name: "+LoginUser.getLname());
        userAddress=new Label("Address: "+LoginUser.getAddress());
        userPhone=new Label("Phone Number: "+LoginUser.getPhoneNumber());
        userState=new Label("State: "+LoginUser.getState());
        userGender=new Label("Gender: "+LoginUser.getGender());
        userEmail=new Label("Email: "+LoginUser.getEmail());
        userPasswod=new Label("Password: ");
        saveFName=new Button("Save");
        saveFName.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    CarRentSystemException.password(LoginUser,passConfirm2.getText());
                    try {
                        CarRentSystemException.mismatchString(fNameChange.getText());
                        LoginUser.setFname(fNameChange.getText());
                        userFname=new Label("First Name: "+LoginUser.getFname());
                        CarRentSystemException.warningMessage("Saved");
                        passConfirm2.setText("");
                        CarRentSystemException.warningMessage("Saved");
                    } catch (Exception ex) {
                        CarRentSystemException.warningMessage("Wrong input");
                    }
                } catch (Exception ex) {
                    CarRentSystemException.warningMessage(ex.getMessage());
                }
            }
        });
        saveFName.setId("save");
        saveLName=new Button("Save");
        saveLName.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    CarRentSystemException.password(LoginUser,passConfirm3.getText());
                    try {
                        CarRentSystemException.mismatchString(lNameChange.getText());
                        LoginUser.setLname(lNameChange.getText());
                        userLname=new Label("Last Name: "+LoginUser.getLname());
                        passConfirm3.setText("");
                        CarRentSystemException.warningMessage("Saved");
                    } catch (Exception ex) {
                        CarRentSystemException.warningMessage("Wrong input");
                    }
                } catch (Exception ex) {
                    CarRentSystemException.warningMessage(ex.getMessage());
                }
            }
        });
        saveLName.setId("save");
        savePassword=new Button("Save");
        savePassword.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    CarRentSystemException.password(LoginUser,passConfirm10.getText());
                    try {
                        CarRentSystemException.mismatchString(passChange.getText());
                        LoginUser.setPassword(passChange.getText());
                        passConfirm10.setText("");
                        CarRentSystemException.warningMessage("Saved");
                    } catch (Exception ex) {
                        CarRentSystemException.warningMessage("Wrong input");
                    }
                } catch (Exception ex) {
                    CarRentSystemException.warningMessage(ex.getMessage());
                }
            }
        });
        savePassword.setId("save");
        savePhone=new Button("Save");
        savePhone.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    CarRentSystemException.password(LoginUser,passConfirm5.getText());
                    try {
                        CarRentSystemException.mismatchString(phonechange.getText());
                        CarRentSystemException.phoneNumberException(phonechange.getText());
                        LoginUser.setPhoneNumber(phonechange.getText());
                        userPhone=new Label("Phone Number: "+LoginUser.getPhoneNumber());
                        CarRentSystemException.warningMessage("Saved");
                        passConfirm5.setText("");
                        CarRentSystemException.warningMessage("Saved");
                    } catch (Exception ex) {
                        CarRentSystemException.warningMessage("Wrong input");
                    }
                } catch (Exception ex) {
                    CarRentSystemException.warningMessage(ex.getMessage());
                }
            }
        });
        savePhone.setId("save");
        saveEmail=new Button("Save");
        saveEmail.setId("save");
        saveEmail.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    CarRentSystemException.password(LoginUser,passConfirm9.getText());
                    try {
                        CarRentSystemException.mismatchString(emailChange.getText());
                        LoginUser.setEmail(emailChange.getText());
                        userEmail=new Label("Phone Number: "+LoginUser.getEmail());
                        passConfirm9.setText("");
                        CarRentSystemException.warningMessage("Saved");
                        CarRentSystemException.warningMessage("Saved");
                    } catch (Exception ex) {
                        CarRentSystemException.warningMessage("Wrong input");
                    }
                } catch (Exception ex) {
                    CarRentSystemException.warningMessage(ex.getMessage());
                }
            }
        });
        saveAddress=new Button("Save");
        saveAddress.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    CarRentSystemException.password(LoginUser,passConfirm4.getText());
                    try {
                        CarRentSystemException.mismatchString(addressChange.getText());
                        LoginUser.setAddress(addressChange.getText());
                        userAddress=new Label("Phone Number: "+LoginUser.getAddress());
                        passConfirm4.setText("");
                        CarRentSystemException.warningMessage("Saved");
                    } catch (Exception ex) {
                        CarRentSystemException.warningMessage("Wrong input");
                    }
                } catch (Exception ex) {
                    CarRentSystemException.warningMessage(ex.getMessage());
                }
            }
        });
        saveAddress.setId("save");
        saveImage=new Button("Save");
        saveImage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    CarRentSystemException.password(LoginUser,passConfirm1.getText());
                    LoginUser.setPic(UserPicPath);
                    passConfirm1.setText("");
                    CarRentSystemException.warningMessage("Saved");
                } catch (Exception ex) {
                    CarRentSystemException.warningMessage(ex.getMessage());
                }
            }
        });
        saveImage.setId("save");
        saveState=new Button("Save");
        saveState.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    CarRentSystemException.password(LoginUser,passConfirm6.getText());
                    CarRentSystemException.mismatchCheckBox(stateBox.getSelectionModel().getSelectedIndex());
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
                    LoginUser.setState(state);
                    userState=new Label("State: "+LoginUser.getState());
                    passConfirm6.setText("");
                    CarRentSystemException.warningMessage("Saved");
                } catch (Exception ex) {
                    CarRentSystemException.warningMessage("Wrong input");
                }
            }
        });
        saveState.setId("save");
        saveGender=new Button("Save");
        saveGender.setId("save");
        saveGender.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    CarRentSystemException.password(LoginUser,passConfirm7.getText());
                    String gend;
                    int Gender=genderBox.getSelectionModel().getSelectedIndex();
                    switch (Gender) {
                        case 0:
                            gend="Male";
                            LoginUser.setGender(gend);
                            CarRentSystemException.warningMessage("Saved");
                            break;
                        case 1:
                            gend="Female";
                            LoginUser.setGender(gend);
                            CarRentSystemException.warningMessage("Saved");
                            break;
                        default:
                            CarRentSystemException.warningMessage("Wrong input");
                            break;
                    }
                        passConfirm7.setText("");
                        CarRentSystemException.warningMessage("Saved");
                } catch (Exception ex) {
                    CarRentSystemException.warningMessage(ex.getMessage());
                }
                    
            }
        });
        editFname=new Button("Edit");
        editFname.setId("prev");
        editLname=new Button("Edit");
        editLname.setId("prev");
        editAddress=new Button("Edit");
        editAddress.setId("prev");
        editPhone=new Button("Edit");
        editPhone.setId("prev");
        editEmail=new Button("Edit");
        editEmail.setId("prev");
        editPassword=new Button("Edit");
        editPassword.setId("prev");
        editImage=new Button("Edit");
        editImage.setId("prev");
        editState=new Button("Edit");
        editState.setId("prev");
        editGender=new Button("Edit");
        editGender.setId("prev");
        
        fNameChange=new TextField();
        fNameChange.setPromptText("type your new first name");
        lNameChange=new TextField();
        lNameChange.setPromptText("type your new last name");
        addressChange=new TextField();
        addressChange.setPromptText("type your new address");
        phonechange=new TextField();
        phonechange.setPromptText("type your new phone number");
        emailChange=new TextField();
        emailChange.setPromptText("type your new email");
        passChange=new TextField();
        passChange.setPromptText("type your new password");
        
        passConfirm1=new PasswordField();
        passConfirm1.setPromptText("type your password");
        passConfirm2=new PasswordField();
        passConfirm2.setPromptText("type your password");
        passConfirm3=new PasswordField();
        passConfirm3.setPromptText("type your password");
        passConfirm4=new PasswordField();
        passConfirm4.setPromptText("type your password");
        passConfirm5=new PasswordField();
        passConfirm5.setPromptText("type your password");
        passConfirm6=new PasswordField();
        passConfirm6.setPromptText("type your password");
        passConfirm7=new PasswordField();
        passConfirm7.setPromptText("type your password");
        passConfirm8=new PasswordField();
        passConfirm8.setPromptText("type your password");
        passConfirm9=new PasswordField();
        passConfirm9.setPromptText("type your password");
        passConfirm10=new PasswordField();
        passConfirm10.setPromptText("type your old password");
        
        stateBox=new ComboBox<String>();
        stateBox.getItems().addAll("Alexandria","Beheira","Cairo","Giza");
        stateBox.setPromptText("choose your state");
        genderBox=new ComboBox<String>();
        genderBox.getItems().addAll("Male","Female");
        genderBox.setPromptText("gender");
        
        GridPane.setConstraints(userPersonalImageLabel, 0, 0);
        GridPane.setConstraints(userFname, 0, 1);
        GridPane.setConstraints(userLname, 0, 2);
        GridPane.setConstraints(userAddress, 0, 3);
        GridPane.setConstraints(userPhone, 0, 4);
        GridPane.setConstraints(userState, 0, 5);
        GridPane.setConstraints(userGender, 0, 6);
        GridPane.setConstraints(userEmail, 0, 8);
        GridPane.setConstraints(userPasswod, 0, 9);
        
        GridPane.setConstraints(editImage, 1, 0);
        GridPane.setConstraints(editFname, 1, 1);
        GridPane.setConstraints(editLname, 1, 2);
        GridPane.setConstraints(editAddress, 1, 3);
        GridPane.setConstraints(editPhone, 1, 4);
        GridPane.setConstraints(editState, 1, 5);
        GridPane.setConstraints(editGender, 1, 6);
        GridPane.setConstraints(editEmail, 1, 8);
        GridPane.setConstraints(editPassword, 1, 9);
        
        GridPane.setConstraints(fNameChange, 2, 1);
        GridPane.setConstraints(lNameChange, 2, 2);
        GridPane.setConstraints(addressChange, 2, 3);
        GridPane.setConstraints(phonechange, 2, 4);
        GridPane.setConstraints(stateBox, 2, 5);
        GridPane.setConstraints(genderBox, 2, 6);
        GridPane.setConstraints(emailChange, 2, 8);
        GridPane.setConstraints(passChange, 2, 9);
        
        GridPane.setConstraints(passConfirm1, 3, 0);
        GridPane.setConstraints(passConfirm2, 3, 1);
        GridPane.setConstraints(passConfirm3, 3, 2);
        GridPane.setConstraints(passConfirm4, 3, 3);
        GridPane.setConstraints(passConfirm5, 3, 4);
        GridPane.setConstraints(passConfirm6, 3, 5);
        GridPane.setConstraints(passConfirm7, 3, 6);
        GridPane.setConstraints(passConfirm8, 3, 7);
        GridPane.setConstraints(passConfirm9, 3, 8);
        GridPane.setConstraints(passConfirm10, 3, 9);
        
        GridPane.setConstraints(saveImage, 4, 0);
        GridPane.setConstraints(saveFName, 4, 1);
        GridPane.setConstraints(saveLName, 4, 2);
        GridPane.setConstraints(saveAddress, 4, 3);
        GridPane.setConstraints(savePhone, 4, 4);
        GridPane.setConstraints(saveState, 4, 5);
        GridPane.setConstraints(saveGender, 4, 6);
        GridPane.setConstraints(saveEmail, 4, 8);
        GridPane.setConstraints(savePassword, 4, 9);
        //// Disable 30 items:( ////
        passConfirm1.setVisible(false);passConfirm2.setVisible(false);passConfirm3.setVisible(false);
        passConfirm4.setVisible(false);passConfirm5.setVisible(false);passConfirm6.setVisible(false);
        passConfirm7.setVisible(false);passConfirm8.setVisible(false);passConfirm9.setVisible(false);
        passConfirm10.setVisible(false);saveFName.setVisible(false);saveLName.setVisible(false);
        saveAddress.setVisible(false);savePhone.setVisible(false);savePassword.setVisible(false);
        saveEmail.setVisible(false);saveImage.setVisible(false);saveState.setVisible(false);
        saveGender.setVisible(false);genderBox.setVisible(false);fNameChange.setVisible(false);
        lNameChange.setVisible(false);addressChange.setVisible(false);phonechange.setVisible(false);
        passChange.setVisible(false);emailChange.setVisible(false);stateBox.setVisible(false);
        UserPicPath=LoginUser.getPic();
        editImage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                passConfirm1.setVisible(true);
                 saveImage.setVisible(true);
                 Image pic;
                FileChooser fileChooser=new FileChooser();
                try {
                    File file = fileChooser.showOpenDialog(null);
                    UserPicPath=file.getAbsolutePath();
                    BufferedImage bufferedImage = ImageIO.read(file);
                    pic = SwingFXUtils.toFXImage(bufferedImage, null);
                    userPersonalImageLabel.setBackground(new Background(new BackgroundImage(pic, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                } catch (Exception ex) {
                    CarRentSystemException.warningMessage("You need to choose a picture");
                    
                }
            }
        });
        editFname.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                 passConfirm2.setVisible(true);
                 fNameChange.setVisible(true);
                 saveFName.setVisible(true);
            }
        });
        editLname.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                passConfirm3.setVisible(true);
                 lNameChange.setVisible(true);
                 saveLName.setVisible(true);
            }
        });
        editAddress.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                passConfirm4.setVisible(true);
                 addressChange.setVisible(true);
                 saveAddress.setVisible(true);
            }
        });
        editPhone.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                passConfirm5.setVisible(true);
                 phonechange.setVisible(true);
                 savePhone.setVisible(true);
            }
        });
        editState.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                passConfirm6.setVisible(true);
                 stateBox.setVisible(true);
                 saveState.setVisible(true);
            }
        });
        editGender.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                passConfirm7.setVisible(true);
                 genderBox.setVisible(true);
                 saveGender.setVisible(true);
            }
        });
        editEmail.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                passConfirm9.setVisible(true);
                 emailChange.setVisible(true);
                 saveEmail.setVisible(true);
            }
        });
        editPassword.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                passConfirm10.setVisible(true);
                 passChange.setVisible(true);
                 savePassword.setVisible(true);
            }
        });
        
        
        glass.getChildren().addAll(userPersonalImageLabel,userFname,userLname,userPhone,userAddress,userState,
                                   userGender,userEmail,userPasswod,editFname,editLname,
                                   editPhone,editPassword,editEmail,editAddress,editImage,editState,editGender,
                                   fNameChange,lNameChange,addressChange,phonechange,emailChange,passChange,stateBox,
                                   genderBox,passConfirm1,passConfirm2,passConfirm3,passConfirm4,passConfirm5,
                                   passConfirm6,passConfirm7,passConfirm8,passConfirm9,passConfirm10,saveFName,saveLName,
                                   savePhone,savePassword,saveEmail,saveAddress,saveImage,saveState,saveGender);
        glass.setId("glass");
        glass.setPadding(new Insets(20,20,20,20));
        glass.setVgap(10);
        glass.setHgap(10);
        glass.setMaxWidth(1320);
        glass.setMaxHeight(530);
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
