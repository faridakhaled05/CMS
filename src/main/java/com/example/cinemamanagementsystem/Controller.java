package com.example.cinemamanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public abstract class Controller {
    protected String userid;

    public void switchScene(ActionEvent event, String fxmlFile, String title, String userid) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        // Pass data to the new scene's controller
        Controller controller = loader.getController(); // Get the controller instance of the new scene
        controller.setUserid(userid); // Assuming you add a setUserid method in the Controller class

        Scene scene = new Scene(root, 400, 350);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }

    public void switchScene(ActionEvent event, String fxmlFile, String title, String userid, double totalPrice) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();

        // Get the controller for the new scene
        NewBookingController newBookingController = loader.getController();

        // Pass data to the new scene's controller
        newBookingController.setUserid(userid); // Assuming you have a setUserid method
        newBookingController.setTotalPrice(totalPrice); // Set the total price

        Scene scene = new Scene(root, 400, 350);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }

    public void switchScene(ActionEvent event, String fxmlFile, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        Controller controller = loader.getController();
        Scene scene=new Scene(root,400, 350);
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }

    // Add this setter in the Controller class
    public void setUserid(String userid) {
        this.userid = userid;
    }


    public void switchScene(ActionEvent event, String fxmlFile, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        Controller controller = loader.getController();
        Scene scene=new Scene(root,400, 350);
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }

    public void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.show();
    }


    public void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.showAndWait();
    }
    public void showAlert(Window owner, String message) {
        showAlert(Alert.AlertType.INFORMATION, owner, "Success", message);
    }
    public void showAlert(Window owner) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Form Error!");
        alert.setHeaderText(null);
        alert.setContentText("Text field empty");
        alert.initOwner(owner);
        alert.show();
    }
}