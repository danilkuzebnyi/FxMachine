package sk.fxmachine.main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import sk.fxmachine.event.StageReadyEvent;
import sk.fxmachine.logging.BasicLogger;

public class FxApplication extends Application {

    private ConfigurableApplicationContext context;

    public static void run() {
        launch();
    }

    @Override
    public void init() throws Exception {
        super.init();
        context = new SpringApplicationBuilder(SuperMain.class)
                .headless(false)
                .build()
                .run();
    }

    @Override
    public void start(Stage stage) {
        context.publishEvent(new StageReadyEvent(stage));
    }

    @Override
    public void stop() throws Exception {
        BasicLogger.info("Exiting application...");
        super.stop();
        context.stop();
        Platform.exit();
        System.exit(0);
    }
}
