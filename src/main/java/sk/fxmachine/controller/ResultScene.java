package sk.fxmachine.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sk.fxmachine.rest.RestApiAgent;
import sk.fxmachine.rest.RestApiAgentImpl;

public class ResultScene {
    private String url;

    @FXML
    private Label resultLabel;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private Button sendDataButton;

    public void setData(String response, String url) {
        resultLabel.setText(response);
        this.url = url;
    }

    public void onSendDataAction() {
        RestApiAgent restApiAgent = new RestApiAgentImpl();
        String json = "{ \"FirstName\": \"" + firstNameField.getText() + "\", " +
                        "\"LastName\": \"" + lastNameField.getText() + "\" }";
        resultLabel.setText(restApiAgent.sendPostRequest(url + "/post", json));
    }
}
