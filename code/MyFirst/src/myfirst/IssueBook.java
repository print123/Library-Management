/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfirst;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
/**
 *
 * @author Shaunak
 */
public class IssueBook {
    TextField nm,auth,cat;
        public void display(Stage Primary)
        {
            javafx.geometry.Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setWidth(bounds.getWidth()/2);
            stage.setHeight(bounds.getHeight()/2);
            
            HBox name=new HBox();
            name.setSpacing(10);
            
            nm=new TextField();
            name.getChildren().addAll(new Label("ISBN"),nm);
            
            /*HBox author=new HBox();
            author.setSpacing(10);
            auth=new TextField();
            author.getChildren().addAll(new Label("Book Author"),auth);
            */
            HBox category=new HBox();
            category.setSpacing(10);
            cat=new TextField();
            category.getChildren().addAll(new Label("Issuer"),cat);
            
           Button add=new Button("Issue");
           add.setOnAction(e->{
               issueItem(stage);
               nm.setText("");
               //auth.setText("");
               cat.setText("");
           });
           VBox vb=new VBox();
           vb.setSpacing(10);
           vb.setPadding(new Insets(25,25,25,25));
           vb.getChildren().addAll(name,category,add);
           Scene s=new Scene(vb);
           stage.setScene(s);
           stage.setTitle("Add a book");
           stage.show();
           stage.setOnCloseRequest(e->{
               stage.close();
               new AdminWindow().display("Next","Welcome admin",Primary);
           });
        }
        public void issueItem(Stage stage){
            String name=nm.getText();
            //String author=auth.getText();
            String user=cat.getText();
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/books?zeroDateTimeBehavior=convertToNull","root","root");
                Statement stmt=con.createStatement();
                String query="select * from allbooks where ISBN='"+name+"' and available='yes'";
                ResultSet rs=stmt.executeQuery(query);
                if(rs.next()){
                    DateFormat dtf=new SimpleDateFormat("dd/MM/yyyy");
                    Date dt=new Date();
                    String s=dtf.format(dt);
                    Statement stmt1=con.createStatement();
                    stmt1.executeUpdate("UPDATE allBooks SET issued_by='"+user+"',available='No',issued_date='"+s+"' where ISBN='"+name+"'");     
                }
                else
                {
                    new AlertBox().display("Error","Book cannot be issued");
                }
            }catch(Exception e){}
        }
    
}
