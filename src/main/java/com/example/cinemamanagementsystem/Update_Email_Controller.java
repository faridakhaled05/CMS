package com.example.cinemamanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Window;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Update_Email_Controller extends Controller {
    @FXML
    private Button CancelButton;

    @FXML
    private TextField NewEmailTF;

    @FXML
    private TextField OldEmailTF;

    @FXML
    private Button UpdateButton;

    // Initialize variables here

    private String Newemail;
    private String Oldemail;

    private String query = "UPDATE person SET email=? WHERE id=?";
    private String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private Pattern pattern = Pattern.compile(emailRegex);

    //@FXML
   // void initialize() {
        // Initialize variables after FXML components are loaded
     
    //}
          // 'UpdateButton' is available here
    //}

    @FXML
    void CancelFun(ActionEvent event) {
        try {
            if (event.getSource() == CancelButton) {
                switchScene(event, "UpdateInfo.fxml", "UpdateInfo", userid);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void UpdateEmailFunc(ActionEvent event) {
        Window owner = UpdateButton.getScene().getWindow();
        // Get the email values from TextFields during button click
        Newemail = NewEmailTF.getText();
        Oldemail = OldEmailTF.getText();

        if (event.getSource() == UpdateButton) {
            if (Newemail.isEmpty() || Oldemail.isEmpty()) {
                showAlert(owner);
            } else {
                Matcher matcher = pattern.matcher(Oldemail);
                Matcher match = pattern.matcher(Newemail);

                if (!matcher.matches()) {
                    // If the email is not in a valid format, show an error
                    showAlert(Alert.AlertType.ERROR, owner, "UpdateEmail", "Please enter a valid email address.");
                } else if (!match.matches()) {
                    // If the email is not in a valid format, show an error
                    showAlert(Alert.AlertType.ERROR, owner, "UpdateEmail", "Please enter a valid email address.");
                } else if (Newemail.equals(Oldemail)) {
                    showAlert(owner, "Both emails are the same. Please enter a different email address.");
                } else {
                    boolean flag = Jdbc.UpdateEmail(Newemail, query, userid);
                    try {
                        if (flag) {
                            infoBox("Your email address has been updated in our system.", "Email Updated Successfully", " Update Successful");
                            switchScene(event, "UpdateInfo.fxml", "UpdateInfo", userid);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    private void setEnterKeyEvent(TextField currentField, TextField nextField) {
        currentField.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                nextField.requestFocus();
                event.consume(); // Consume the event
            }
        });
    }


    private void setEnterKeyEvent(TextField currentField, Button nextButton) {
        currentField.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                nextButton.requestFocus();
                event.consume(); // Consume the event
            }
        });
    }@FXML
    public void initialize() {
        // Set up Enter key event handling for text fields
        setEnterKeyEvent(OldEmailTF, NewEmailTF);
        setEnterKeyEvent(NewEmailTF, UpdateButton);
    }
}
