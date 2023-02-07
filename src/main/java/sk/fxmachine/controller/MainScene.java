package sk.fxmachine.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import sk.fxmachine.logging.BasicLogger;
import sk.fxmachine.rest.RestApiAgent;
import sk.fxmachine.rest.RestApiAgentImpl;
import java.io.IOException;

public class MainScene {

    @FXML
    private TextField urlTextField;

    @FXML
    private Button sendButton;

    @FXML
    private Button resetButton;

    public void onSendButtonAction() {
        Stage stage = new Stage();
        final KeyFrame kf1 = new KeyFrame(Duration.seconds(0), event -> openProgressBarWindow(stage));
        final KeyFrame kf2 = new KeyFrame(Duration.seconds(0.4), event -> openResultWindow(stage));
        final Timeline timeline = new Timeline(kf1, kf2);
        Platform.runLater(timeline::play);
    }

    private void openProgressBarWindow(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(ProgressBarScene.class.getResource("ProgressBarScene.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();

            ProgressBarScene progressBarScene = loader.getController();
            progressBarScene.open();
        } catch (IOException e) {
            BasicLogger.info("Failed to create ProgressBar Window.");
        }
    }

    private void openResultWindow(Stage stage) {
        try {
            RestApiAgent restApiAgent = new RestApiAgentImpl();
            String response = restApiAgent.sendGetRequest(urlTextField.getText() + "/get");

            FXMLLoader loader = new FXMLLoader(ResultScene.class.getResource("ResultScene.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setTitle("Result");
            stage.setScene(scene);
            stage.show();

            ResultScene resultScene = loader.getController();
            resultScene.setData(response, urlTextField.getText());
        } catch (IOException e) {
            BasicLogger.info("Failed to create Result Window.");
        }
    }

    public void onResetButtonAction() {
        urlTextField.clear();
    }
}
