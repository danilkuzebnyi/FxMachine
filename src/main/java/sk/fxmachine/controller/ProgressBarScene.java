package sk.fxmachine.controller;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class ProgressBarScene {
    @FXML
    private Label label;

    @FXML
    private ProgressBar progressBar;

    @FXML
    public void open() {
        Task copyWorker = createWorker();
        progressBar.progressProperty().unbind();
        progressBar.progressProperty().bind(copyWorker.progressProperty());
        copyWorker.messageProperty().addListener((observable, oldValue, newValue) -> label.setText(newValue));
        new Thread(copyWorker).start();
    }

    private Task createWorker() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(30);
                    updateMessage("Loading: " + ((i * 10) + 10)  + "%");
                    updateProgress(i + 1, 10);
                }
                return true;
            }
        };
    }
}
