
package com.risjavafx.pages.referrals;

import com.risjavafx.popups.models.PopupAlert;
import com.risjavafx.popups.models.Notification;
import com.risjavafx.pages.PageManager;
import com.risjavafx.popups.PopupManager;
import com.risjavafx.Driver;
import com.risjavafx.Miscellaneous;
import com.risjavafx.popups.Popups;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReferralEditPopup implements Initializable {
    public VBox popupContainer;
    public static VBox usablePopupContainer;

    public Label patientIDLabel;
    public TextField firstNameTextField;
    public TextField lastNameTextField;
    public TextField birthDateTextField;
    public TextField sexTextField;
    public TextField raceTextField;
    public TextField ethnicityTextField;
    public Button cancelButton;
    public Button submitButton;
    public static int clickedPatientId;

    Driver driver = new Driver();
    Miscellaneous misc = new Miscellaneous();

    public ReferralEditPopup() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resizeElements();
        setPatientIDLabel();
        Popups.REFERRALS_EDIT.getPopup().showingProperty().addListener((observableValue, aBoolean, t1) -> {
            PageManager.getRoot().setDisable(!aBoolean);

            if (Popups.REFERRALS_EDIT.getPopup().isShowing()) {
                patientIDLabel.setText(String.valueOf(getPatientClickedId()));
                refreshElements();
            }
        });
        usablePopupContainer = popupContainer;
    }

    public void refreshElements() {
        firstNameTextField.clear();
        lastNameTextField.clear();
        birthDateTextField.clear();
        raceTextField.clear();
        sexTextField.clear();
        ethnicityTextField.clear();
    }
    public int setPatientIDLabel() {
        try {
            String sql = """
                    select MAX(patient_id)
                    from patients;
                    """;
            ResultSet resultSet = driver.connection.createStatement().executeQuery(sql);
            if (resultSet.next()) {
                return (resultSet.getInt("MAX(patient_id)") + 1);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return -1;
    }

    public static void setPatientClickedId(int patientClickedId) {
        ReferralEditPopup.clickedPatientId = patientClickedId;
    }

    public static int getPatientClickedId() {
        return ReferralEditPopup.clickedPatientId;
    }

    public void updatePatientQuery() throws SQLException {
        String sql = """
                UPDATE patients
                SET first_name = ?, last_name = ?, dob = ?, sex = ?, race = ?, ethnicity = ?
                WHERE patients.patient_id = ?;
                """;
        PreparedStatement preparedStatement = driver.connection.prepareStatement(sql);
        preparedStatement.setString(1, firstNameTextField.getText());
        preparedStatement.setString(2, lastNameTextField.getText());
        preparedStatement.setString(3, birthDateTextField.getText());
        preparedStatement.setString(4, sexTextField.getText());
        preparedStatement.setString(5, raceTextField.getText());
        preparedStatement.setString(6, ethnicityTextField.getText());
        preparedStatement.setInt(7, getPatientClickedId());
        preparedStatement.execute();
    }


    // Returns false if any input field is invalid
    public boolean validInput() {
        return
                !firstNameTextField.getText().isBlank() &&
                        !birthDateTextField.getText().isBlank() &&
                        !lastNameTextField.getText().isBlank() &&
                        !sexTextField.getText().isBlank() &&
                        !raceTextField.getText().isBlank() &&
                        !ethnicityTextField.getText().isBlank();
    }

    public void resizeElements() {
        popupContainer.setPrefHeight(Popups.getMenuDimensions()[0]);
        popupContainer.setPrefWidth(Popups.getMenuDimensions()[1]);
        popupContainer.setMaxHeight(Popups.getMenuDimensions()[0]);
        popupContainer.setMaxWidth(Popups.getMenuDimensions()[1]);

        cancelButton.setPrefHeight(misc.getScreenWidth() * .033);
        cancelButton.setPrefWidth(misc.getScreenWidth() * .11);
        submitButton.setPrefHeight(misc.getScreenWidth() * .033);
        submitButton.setPrefWidth(misc.getScreenWidth() * .11);

        double fontSize;
        if ((misc.getScreenWidth()/80) < 20) {
            fontSize = misc.getScreenWidth()/80;
        } else {
            fontSize = 20;
        }
        cancelButton.setStyle("-fx-font-size: " + fontSize);
        submitButton.setStyle("-fx-font-size: " + fontSize);
    }

    //Button Onclicks
    // Onclick for submit button
    public void submitButtonOnclick() throws SQLException {
        if (validInput()) {
            updatePatientQuery();
            Referrals.queryData(Referrals.getLastRowStringQuery());
            PopupManager.removePopup("MENU");
            Notification.createNotification("Submission Complete", "You have successfully created a new appointment");

        } else if (!validInput()) {
            PopupManager.createPopup(Popups.ALERT);
            new PopupAlert() {{
                setAlertImage(new Image("file:C:/Users/johnn/IdeaProjects/RISJavaFX/src/main/resources/com/risjavafx/images/error.png"));
                setHeaderLabel("Submission Failed");
                setContentLabel("Please make sure you filled out all fields");
                setExitButtonLabel("Retry");
            }};

        }
    }

    // Onclick for cancel button
    public void cancelButtonOnclick() {
        try {
            PopupManager.removePopup("MENU");
        } catch (Exception ignore) {}
    }
}