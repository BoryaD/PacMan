package tritpo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tritpo.menu.Menu;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Menu menuScreen = new Menu();
        Data.content.getChildren().add(menuScreen);
        primaryStage.setResizable(false);
        primaryStage.setTitle(Data.APPLICATION_TITLE);
        primaryStage.setWidth((double)Data.WINDOW_WIDTH);
        primaryStage.setHeight((double)Data.WINDOW_HEIGHT);
        Scene scene = new Scene(Data.content, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}