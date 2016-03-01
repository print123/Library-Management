/*
 * Library Management Software 
 * version 1.0
 * author shaunak
 */

package myfirst;

import java.awt.geom.Rectangle2D;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;



public class MyFirst extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane b=new BorderPane();
        GridPane root = new GridPane();
        root.setPadding(new Insets(10,10,10,10));
        root.setVgap(10);
        root.setHgap(10);
       
        //Label for username
        Label user=new Label("Username");
        GridPane.setConstraints(user,60,24);
        
        //TextField for username 
        TextField tfuser=new TextField();
        tfuser.setPromptText("Enter username");
        GridPane.setConstraints(tfuser,60,26);
        
        //Label for password
        Label pass=new Label("Password");
        GridPane.setConstraints(pass,60,28);
        
        //TextField for password
        PasswordField pf=new PasswordField();
        GridPane.setConstraints(pf,60,30);
        
        //Login button
        Button btn = new Button();
        btn.setText("Login");
        GridPane.setConstraints(btn,60,32);
        
        btn.setOnAction(e -> 
        {
            if(tfuser.getText().equals("shaunak") && pf.getText().equals("vidisha"))
            {
                new AdminWindow().display("Next","Welcome admin",primaryStage);
            }
            else if(tfuser.getText().equals("user") && pf.getText().equals("1234"))
            {
                new UserWindow().display("next","Books in the Library", primaryStage);
            }
            else
            {
                AlertBox.display("Error","You have entered incorrect credentials");
                tfuser.setText("");
                pf.setText("");
            }
        });
        
        
        
        root.getChildren().addAll(user,tfuser,pass,pf,btn);
        b.setCenter(root);
        Scene scene = new Scene(b);
        javafx.geometry.Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        
        primaryStage.setTitle("Diamond Library");
        primaryStage.setScene(scene);
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}


