package sk.fxmachine.main;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("sk")
public class SuperMain {

    public static void main(String[] args) {
        FxApplication.run();
    }

}
