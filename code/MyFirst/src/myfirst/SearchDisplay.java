/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfirst;

/**
 *
 * @author Shaunak
 */
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SearchDisplay {

        public static void display(String title,String message,ObservableList<Book> li)
        {
            javafx.geometry.Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
            
            //name col
        TableColumn<Book,String> nameCol=new TableColumn<>("Name");
        nameCol.setMinWidth(2*bounds.getWidth()/14);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        //author  col
        TableColumn<Book,String> authorCol=new TableColumn<>("Author");
        authorCol.setMinWidth(2*bounds.getWidth()/14);
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        
        //category
        TableColumn<Book,String> categoryCol=new TableColumn<>("Category");
        categoryCol.setMinWidth(2*bounds.getWidth()/14);
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        
        //available col
        TableColumn<Book,String> availableCol=new TableColumn<>("Availablity");
        availableCol.setMinWidth(bounds.getWidth()/14-17.5);
        availableCol.setCellValueFactory(new PropertyValueFactory<>("available"));
        
        TableView<Book> table=new TableView<>();
        table.setItems(li);
        table.getColumns().addAll(nameCol,authorCol,categoryCol,availableCol);
        
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            StackPane vb=new StackPane();
            stage.setWidth(bounds.getWidth()/2);
            stage.setHeight(bounds.getHeight()/2);
            vb.getChildren().addAll(table);
            Scene scene = new Scene(vb);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
        }
}


