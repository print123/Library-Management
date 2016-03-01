/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shaunak
 */
package myfirst;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
public class UserWindow {
    
    TableView<Book> table,table1,table2;
    
    public void display(String title,String message,Stage stage)
    {
        //Label
        Label up=new Label(message);
        up.setFont(new Font("Sans-Serif",24));
        
        VBox vb=new VBox();
        HBox hb=new HBox();
        HBox lab=new HBox();
        
        hb.setPadding(new Insets(20,20,20,20));
        lab.setPadding(new Insets(20,20,20,20));
        vb.setPadding(new Insets(10,10,10,10));
        vb.setAlignment(Pos.CENTER);
        lab.setAlignment(Pos.TOP_CENTER);
        hb.setAlignment(Pos.TOP_RIGHT);
        
        //Buttons for search
        TextField tf=new TextField();
        tf.setPromptText("Search a Book");
        Button search = new Button("search");
        search.setOnAction(e -> {
            try
            {
            ObservableList<Book> li=FXCollections.observableArrayList();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/books?zeroDateTimeBehavior=convertToNull","root","root");
            Statement stmt=con.createStatement();
            ResultSet rs=null;
            rs =stmt.executeQuery("select * from allbooks");
            String searchText=tf.getText().toLowerCase();
           
            while(rs.next())
            {
               String currname=rs.getString(1).toLowerCase();
               String currauthor=rs.getString(2).toLowerCase();           
               String currcat=rs.getString(3).toLowerCase();
               if(currname.contains(searchText) || currauthor.contains(searchText) || currcat.contains(searchText))
               {
                   li.add(new Book(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
               }
            }
            if(li.size()==0)
            {
                AlertBox.display("Error","No Book found");
                tf.setText("");
            }
            else
            {
                SearchDisplay.display("Search Results","Search Results",li);
            }
            
             
            }catch(Exception el){}
        });
                
        //width of screen
        javafx.geometry.Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        
        
        //name col
        TableColumn<Book,String> nameCol=new TableColumn<>("Name");
        nameCol.setMinWidth(2*bounds.getWidth()/7);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        //author  col
        TableColumn<Book,String> authorCol=new TableColumn<>("Author");
        authorCol.setMinWidth(2*bounds.getWidth()/7);
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        
        //category
        TableColumn<Book,String> categoryCol=new TableColumn<>("Category");
        categoryCol.setMinWidth(2*bounds.getWidth()/7);
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        
        //available col
        TableColumn<Book,String> availableCol=new TableColumn<>("Availablity");
        availableCol.setMinWidth(bounds.getWidth()/7-35);
        availableCol.setCellValueFactory(new PropertyValueFactory<>("available"));
        
        //name col
        TableColumn<Book,String> nameCol1=new TableColumn<>("Name");
        nameCol1.setMinWidth(2*bounds.getWidth()/7);
        nameCol1.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        //author  col
        TableColumn<Book,String> authorCol1=new TableColumn<>("Author");
        authorCol1.setMinWidth(2*bounds.getWidth()/7);
        authorCol1.setCellValueFactory(new PropertyValueFactory<>("author"));
        
        //category
        TableColumn<Book,String> categoryCol1=new TableColumn<>("Category");
        categoryCol1.setMinWidth(2*bounds.getWidth()/7);
        categoryCol1.setCellValueFactory(new PropertyValueFactory<>("category"));
        
        //available col
        TableColumn<Book,String> availableCol1=new TableColumn<>("Availablity");
        availableCol1.setMinWidth(bounds.getWidth()/7-35);
        availableCol1.setCellValueFactory(new PropertyValueFactory<>("available"));
        
        //name col
        TableColumn<Book,String> nameCol2=new TableColumn<>("Name");
        nameCol2.setMinWidth(2*bounds.getWidth()/7);
        nameCol2.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        //author  col
        TableColumn<Book,String> authorCol2=new TableColumn<>("Author");
        authorCol2.setMinWidth(2*bounds.getWidth()/7);
        authorCol2.setCellValueFactory(new PropertyValueFactory<>("author"));
        
        //category
        TableColumn<Book,String> categoryCol2=new TableColumn<>("Category");
        categoryCol2.setMinWidth(2*bounds.getWidth()/7);
        categoryCol2.setCellValueFactory(new PropertyValueFactory<>("category"));
        
        //available col
        TableColumn<Book,String> availableCol2=new TableColumn<>("Availablity");
        availableCol2.setMinWidth(bounds.getWidth()/7-35);
        availableCol2.setCellValueFactory(new PropertyValueFactory<>("available"));

        
        table=new TableView<>();
        table.setItems(insertBooks(0));
        table.getColumns().addAll(nameCol,authorCol,categoryCol,availableCol);
        
        //category books
        table1=new TableView<>();
        table1.setItems(insertBooks(1));
        table1.getColumns().addAll(nameCol1,authorCol1,availableCol1);
        
        table2=new TableView<>();
        table2.setItems(insertBooks(2));
        table2.getColumns().addAll(nameCol2,authorCol2,availableCol2);
        
        TabPane tabPane = new TabPane();
        Tab tab = new Tab();
        tab.setText("All Books");
        tab.setContent(table);
        tab.setClosable(false);
        Tab tab1 = new Tab();
        tab1.setText("Positive");
        tab1.setContent(table1);
        Tab tab2 = new Tab();
        tab2.setText("Health");
        tab2.setContent(table2);
        
        tabPane.getTabs().addAll(tab,tab1,tab2);
        
        hb.setSpacing(10);
        hb.getChildren().addAll(tf,search);
        lab.getChildren().add(up);
        vb.getChildren().addAll(lab,hb,tabPane);        
        
        Scene scene = new Scene(vb);
        stage.setScene(scene);
        stage.show();
    }
    public ObservableList<Book> insertBooks(int flag)
    {
        ObservableList<Book> li=FXCollections.observableArrayList();
        try{
           Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/books?zeroDateTimeBehavior=convertToNull","root","root");
           Statement stmt=con.createStatement();
           ResultSet rs=null;
           if(flag==0)
                rs =stmt.executeQuery("select * from allbooks");
           else if(flag==1)
                rs =stmt.executeQuery("select * from allbooks where category='Positive'");
           else if(flag==2)
               rs =stmt.executeQuery("select * from allbooks where category='Health'");
           
           while(rs.next())
           {
               li.add(new Book(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
                       
           }
            
        }catch(Exception e){}
        
        return li;
    }
    
}
