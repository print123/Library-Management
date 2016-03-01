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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class AlertBox {

        public static void display(String title,String message)
        {
            javafx.geometry.Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
            
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            StackPane vb=new StackPane();
            stage.setWidth(bounds.getWidth()/6);
            stage.setHeight(bounds.getHeight()/6);
            vb.getChildren().addAll(new Label(message));
            Scene scene = new Scene(vb);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
        }
}






























