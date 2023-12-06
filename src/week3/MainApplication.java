package week3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class MainApplication extends Application {
    private final String TITLE = String.format("Practicumopdracht OOP2 - %s / Boodschappenprijs-vergelijker", practicumopdracht.Main.studentNaam);
    private final int WIDTH = 600;
    private final int HEIGHT = 640;


    @Override
    public void start(Stage stage) {
        if(!Main.launchedFromMain) {
            System.err.println("Je moet deze applicatie opstarten vanuit de Main-class, niet de MainApplication-class!");
            System.exit(1337);

            return;
        }

        stage.setTitle(String.format("Practicumopdracht OOP2 - %s / Boodschappenprijs-vergelijker", practicumopdracht.Main.studentNaam));
        stage.setWidth(640);
        stage.setHeight(600);
        Scene scene = new Scene(new TellerController().getView().getRoot());
        stage.setScene(scene);
        stage.show();
    }

}
