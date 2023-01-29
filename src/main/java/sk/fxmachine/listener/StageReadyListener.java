package sk.fxmachine.listener;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import sk.fxmachine.controller.MainScene;
import sk.fxmachine.event.StageReadyEvent;
import sk.fxmachine.logging.BasicLogger;

@Component
public class StageReadyListener implements ApplicationListener<StageReadyEvent> {

    @Override
    public void onApplicationEvent(StageReadyEvent stageReadyEvent) {
        loadMainScene(stageReadyEvent.getStage());
        BasicLogger.info("Application started...");
    }

    private void loadMainScene(Stage stage) {
        try {
            var loader = new FXMLLoader(MainScene.class.getResource("MainScene.fxml"));
            var scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
