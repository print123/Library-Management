/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfirst;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
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
public class addBookWindow {
        
        TextField nm,auth,cat,ISBN;
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
            name.getChildren().addAll(new Label("Name"),nm);
            
            HBox author=new HBox();
            author.setSpacing(10);
            auth=new TextField();
            author.getChildren().addAll(new Label("Author"),auth);
            
            HBox category=new HBox();
            category.setSpacing(10);
            cat=new TextField();
            category.getChildren().addAll(new Label("Category"),cat);
            
            HBox isbn=new HBox();
            isbn.setSpacing(10);
            ISBN=new TextField();
            isbn.getChildren().addAll(new Label("ISBN"),ISBN);
            
           Button add=new Button("Add");
           add.setOnAction(e->{
               addData();
               nm.setText("");
               auth.setText("");
               cat.setText("");
               ISBN.setText("");
           });
           VBox vb=new VBox();
           vb.setSpacing(10);
           vb.setPadding(new Insets(25,25,25,25));
           vb.getChildren().addAll(name,author,category,isbn,add);
           Scene s=new Scene(vb);
           stage.setScene(s);
           stage.setTitle("Add a book");
           stage.show();
           stage.setOnCloseRequest(e->{
               stage.close();
               new AdminWindow().display("Next","Welcome admin",Primary);
           });
            
        }
        public void addData()
        {
            try{
                  Class.forName("com.mysql.jdbc.Driver");
                  Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/books?zeroDateTimeBehavior=convertToNull","root","root");
                  Statement stmt=con.createStatement();
                  String nam=nm.getText();
                  String au=auth.getText();
                  String c=cat.getText();
                  String is=ISBN.getText();
                  String query="insert into allBooks(name,author,category,available,ISBN) "+"VALUE ('"+nam+"','"+au+"','"+c+"','yes','"+is+"')";
                  stmt.executeUpdate(query);
            }catch(Exception e){System.out.println("error");}
        }
}



















