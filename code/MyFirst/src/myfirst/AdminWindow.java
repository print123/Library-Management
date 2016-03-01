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
import java.util.Date;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
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

public class AdminWindow {
    
    TableView<Book> table,table1,table2;
    public void display(String title,String message,Stage stage)
    {
        
        Label up=new Label(message);
        up.setFont(new Font("Sans-Serif",24));
        javafx.geometry.Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        BorderPane root=new BorderPane();
        VBox vb=new VBox();
        HBox hb=new HBox();
        HBox lab=new HBox();
        //HBox Bar=new HBox();
       // Bar.setMaxWidth(bounds.getWidth());
        hb.setPadding(new Insets(20,20,20,20));
        lab.setPadding(new Insets(20,20,20,20));
        vb.setPadding(new Insets(10,10,10,10));
        vb.setAlignment(Pos.CENTER);
        lab.setAlignment(Pos.TOP_CENTER);
        hb.setAlignment(Pos.TOP_RIGHT);
        
        //menu-bar
        MenuBar menuBar=new MenuBar();
        /*Menu dashboard=new Menu("_Dashboard");
        dashboard.setOnAction(e->new AdminWindow().display("Admin","Welcome Admin", stage));*/
        Menu Books=new Menu("_Books");
        Menu Patrons=new Menu("_Patrons");
        Menu Statistics=new Menu("_Statistics");
        Menu Tools=new Menu("Tools");
        
        //Books menu
        MenuItem addItem=new MenuItem("Add Book");
        addItem.setOnAction(e->new addBookWindow().display(stage));//try without passing argument
        MenuItem issueMenu=new MenuItem("Issue Book");
        issueMenu.setOnAction(e->new IssueBook().display(stage));
        MenuItem returnMenu=new MenuItem("Return Book");
        MenuItem renewMenu=new MenuItem("Renew Book");
        Books.getItems().addAll(addItem,issueMenu,returnMenu,renewMenu);
        
        //patrons Menu
        MenuItem addPatron=new MenuItem("Add Patron");
        MenuItem removePatron=new MenuItem("Remove Patron");
        MenuItem listPatron=new MenuItem("List Patron");
        Patrons.getItems().addAll(addPatron,removePatron,listPatron);
        
        //Statistics Menu
        MenuItem overdueBooks=new MenuItem("Overdue Books");
        MenuItem stats=new MenuItem("Books Statistics");
        Statistics.getItems().addAll(overdueBooks,stats);
        
        //Tools Menu
        MenuItem settings =new MenuItem("Settings");
        MenuItem help=new MenuItem("Help"); 
        Tools.getItems().addAll(settings,help);
        
        menuBar.getMenus().addAll(Books,Patrons,Statistics,Tools);
        root.setTop(menuBar);
        //Bar.getChildren().add(menuBar);
        //Buttons for search
        TextField tf=new TextField();
        tf.setPromptText("Search a Book");
        Button search = new Button("search");
        search.setOnAction(e -> {
            try
            {
            ObservableList<Book> li=FXCollections.observableArrayList();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library?zeroDateTimeBehavior=convertToNull","root","");
            Statement stmt=con.createStatement();
            ResultSet rs=null;
            rs =stmt.executeQuery("select * from books");
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
       // javafx.geometry.Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        
        
        //name col
        TableColumn<Book,String> nameCol=new TableColumn<>("Name");
        nameCol.setMinWidth(2*bounds.getWidth()/11);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        //issued _by
        TableColumn<Book,String> issued=new TableColumn<>("Issued By");
        issued.setMinWidth(2*bounds.getWidth()/11);
        issued.setCellValueFactory(new PropertyValueFactory<>("issued_by"));
        
        //issude_date
        TableColumn<Book,String> issued_date=new TableColumn<>("Issued Date");
        issued_date.setMinWidth(2*bounds.getWidth()/11);
        issued_date.setCellValueFactory(new PropertyValueFactory<>("issued_date"));
        
        
        //author  col
        TableColumn<Book,String> authorCol=new TableColumn<>("Author");
        authorCol.setMinWidth(2*bounds.getWidth()/11);
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        
        //category
        TableColumn<Book,String> categoryCol=new TableColumn<>("Category");
        categoryCol.setMinWidth(2*bounds.getWidth()/11);
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        
        //available col
        TableColumn<Book,String> availableCol=new TableColumn<>("Availablity");
        availableCol.setMinWidth(bounds.getWidth()/11);
        availableCol.setCellValueFactory(new PropertyValueFactory<>("available"));
        
        
        
        table=new TableView<>();
        table.setItems(insertBooks(0));
        table.getColumns().addAll(nameCol,authorCol,categoryCol,availableCol,issued,issued_date);
        
        
        TabPane tabPane = new TabPane();
        Tab tab = new Tab();
        tab.setText("All Books");
        tab.setContent(table);
        tab.setClosable(false);
      
        
        tabPane.getTabs().addAll(tab);
        
        
        // Action buttons
        Button addButton=new Button("  Add  ");
        addButton.setOnAction(e->{
            new addBookWindow().display(stage);
        });
        Button delButton=new Button("  Delete  ");
        delButton.setOnAction(e->{
           deleteItems(stage); 
        });
        Button renButton=new Button("  Renew  ");
        renButton.setOnAction(e->renewItem(stage));
        Button issueButton=new Button("  Issue  ");
        issueButton.setOnAction(e->{
            issueItem(stage);
        });
        Button returnButton=new Button("  Return  ");
        returnButton.setOnAction(e->{
            returnBook(stage);
        });
        HBox actionB=new HBox();
        actionB.getChildren().addAll(addButton,delButton,renButton,issueButton,returnButton);
        actionB.setSpacing(20);
        actionB.setPadding(new Insets(0,0,0,500));
        
        
        hb.setSpacing(10);
        hb.getChildren().addAll(tf,search);
        lab.getChildren().add(up);
        
        vb.getChildren().addAll(lab,hb,tabPane,actionB);        
        vb.setSpacing(20);
        root.setCenter(vb);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public ObservableList<Book> insertBooks(int flag)
    {
        ObservableList<Book> li=FXCollections.observableArrayList();
        try{
           Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library?zeroDateTimeBehavior=convertToNull","root","");
           Statement stmt=con.createStatement();
           ResultSet rs=null;
           if(flag==0)
                rs =stmt.executeQuery("select * from books");
                      
           
           while(rs.next())
           {
               li.add(new Book(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
                       
           }
            
        }catch(Exception e){}
        
        return li;
    }
    public void deleteItems(Stage primaryStage)
    {
        ObservableList<Book> selectedBooks;
        selectedBooks=table.getSelectionModel().getSelectedItems();
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library?zeroDateTimeBehavior=convertToNull","root","");
            for(Book c : selectedBooks){
                String name=c.getName();
                String author=c.getAuthor();
            
                Statement stmt=con.createStatement();
                stmt.executeUpdate("delete from books where name='"+name+"'and author='"+author+"'");                         
            }
            display("Next","Welcome admin",primaryStage);
        }catch(Exception e){System.out.println("Not deleted");}
    }
    public void issueItem(Stage primaryStage){
        ObservableList<Book> selectedBooks;
        selectedBooks=table.getSelectionModel().getSelectedItems();
        Button btn=new Button("Issue");
        Stage stage=new Stage();
        HBox hb=new HBox();
        TextField user=new TextField();
        hb.setSpacing(10);
        hb.setPadding(new Insets(25,25,25,25));
        hb.getChildren().addAll(new Label("Issued By"),user,btn);
        Scene scene=new Scene(hb);
        HBox hb1=new HBox();
        hb1.setPadding(new Insets(25,25,25,25));
        hb1.getChildren().addAll(new Label("Book Already issued"));
        Scene scene1=new Scene(hb1);
        
        for(Book c : selectedBooks){
                if(c.getAvailable().equals("yes")){
                    stage.setTitle("Issue Book");
                    stage.setScene(scene);
                    stage.show();
                }
                else{
                    stage.setTitle("Error");
                    stage.setScene(scene1);
                    stage.show();
                }
                            
            }
        
        btn.setOnAction(e->{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library?zeroDateTimeBehavior=convertToNull","root","");
            for(Book c : selectedBooks){
                String name=c.getName();
                String author=c.getAuthor();
                String issue=user.getText();
                DateFormat dtf=new SimpleDateFormat("dd/MM/yyyy");
		Date dt=new Date();
                String s=dtf.format(dt);
                
                Statement stmt=con.createStatement();
                stmt.executeUpdate("UPDATE books SET issued_by='"+issue+"',available='No',issued_date='"+s+"' where name='"+name+"'and author='"+author+"'");            
            }
            display("Next","Welcome admin",primaryStage);
            stage.close();
        }catch(Exception el){System.out.println("Not issued");}
        });
    }
    public void returnBook(Stage primaryStage){
        ObservableList<Book> selectedBooks;
        selectedBooks=table.getSelectionModel().getSelectedItems();
        for(Book c : selectedBooks){
            if(c.getAvailable().equals("yes")){
                Stage stage=new Stage();
                StackPane vb=new StackPane();
                vb.getChildren().add(new Label("Book not issued"));
                Scene scene=new Scene(vb);
                stage.setTitle("Error");
                stage.setScene(scene);
                stage.show();
                
            }
            else
            {
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/books?zeroDateTimeBehavior=convertToNull","root","root");
                    String name=c.getName();
                    String author=c.getAuthor();
                    Statement stmt=con.createStatement();
                    stmt.executeUpdate("UPDATE allBooks SET issued_by='',available='yes',issued_date='' where name='"+name+"'and author='"+author+"'");            
                    display("Next","Welcome admin",primaryStage);
            
                    
                }catch(Exception e){}
                
            }
        }
    }
    public void renewItem(Stage primaryStage){
        ObservableList<Book> selectedBooks;
        selectedBooks=table.getSelectionModel().getSelectedItems();
      
           
        
        
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/books?zeroDateTimeBehavior=convertToNull","root","root");
            for(Book c : selectedBooks){
                String name=c.getName();
                String author=c.getAuthor();
            //    String issue=user.getText();
                DateFormat dtf=new SimpleDateFormat("dd/MM/yyyy");
		Date dt=new Date();
                String s=dtf.format(dt);
                
                Statement stmt=con.createStatement();
                stmt.executeUpdate("UPDATE allBooks SET available='No',issued_date='"+s+"' where name='"+name+"'and author='"+author+"'");            
            }
            display("Next","Welcome admin",primaryStage);
            
        }catch(Exception el){System.out.println("Not Renewed");}
       
    }
}












