package GUI;

import CarRentSystem.FileManager;
import Persons.Admin;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AdminPage {
    public static Stage window;
    private Scene scene;
    private BorderPane layout;
    private HBox header,footer;
    private Button message,ok1,ok2;
    private GridPane glass;
    private VBox right,left;
    private Region rightRegion,leftRegion;
    private Admin Shaltout;
    private TextArea D_report,M_report;
    private HeadersAndFooters obj;
    public AdminPage() {
        Shaltout = FileManager.loadAdmin();
        obj=new HeadersAndFooters();
        header= obj.headerForAdmin(this.Shaltout);
        footer= obj.footerForAdmin();
        D_report=obj.dReportForAdmin();
        M_report=obj.mReportForAdmin();
        ok1=obj.ok1ForAdmin();
        ok2=obj.ok2ForAdmin();
        glass=obj.glassForAdmin();
        glass.setConstraints(D_report, 0, 0);
        glass.setConstraints(ok1, 1, 0);
        glass.setConstraints(M_report, 0, 1);
        glass.setConstraints(ok2, 1, 1);
        glass.getChildren().addAll(D_report,ok1,M_report,ok2);
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
                obj.closeProgram();
            }
        });
    }
}