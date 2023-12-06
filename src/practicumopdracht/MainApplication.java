package practicumopdracht;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import practicumopdracht.Controllers.Controller;
import practicumopdracht.Controllers.WinkelController;
import practicumopdracht.data.*;
import practicumopdracht.views.View;

import java.util.Optional;

public class MainApplication extends Application {
    private final String TITLE = String.format("Practicumopdracht OOP2 - %s / Boodschappenprijs-vergelijker", Main.studentNaam);
    private final int WIDTH = 600;
    private final int HEIGHT = 640;
    private static WinkelDAO winkelDAO = new TextWinkelDAO();
    private static ProductDAO productDAO = new ObjectProductDAO();
    private static Stage stage = new Stage();


    @Override
    public void start(Stage stage) {
        if(!Main.launchedFromMain) {
            System.err.println("Je moet deze applicatie opstarten vanuit de Main-class, niet de MainApplication-class!");
            System.exit(1337);

            return;
        }

        stage.setTitle(TITLE);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        switchController(new WinkelController());
    }

    public static void switchController(Controller controller) {
        View view = controller.getView();
        Scene scene = new Scene(view.getRoot());
        stage.setScene(scene);
        stage.show();
    }

    public static void geefInformatieAlert(String melding,String bericht){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(melding);
        alert.setContentText(bericht);
        alert.showAndWait();
    }

    public static boolean geefBevestigAlert(String melding, String bericht){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(melding);
        alert.setContentText(bericht);

        ButtonType buttonTypeJa = new ButtonType("Ja");
        ButtonType buttonTypeNee = new ButtonType("Nee");
        alert.getButtonTypes().setAll(buttonTypeJa,buttonTypeNee);

        Optional<ButtonType> resultaat = alert.showAndWait();
        if (resultaat.get() == buttonTypeJa){
            return true;
        }else {
            return false;
        }
    }

    public static WinkelDAO getWinkelDAO() {
        return winkelDAO;
    }

    public static ProductDAO getProductDAO() {
        return productDAO;
    }
}
