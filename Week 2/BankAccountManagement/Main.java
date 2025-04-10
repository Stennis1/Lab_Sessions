// JavaFX Launcher

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        MainUI ui = new MainUI();
        Scene scene = new Scene(ui, 1200, 900);

        primaryStage.setTitle("AmaliTech Banking App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String []args) {
        launch(args);
    }

}
