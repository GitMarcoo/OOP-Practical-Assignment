package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import practicumopdracht.models.Product;

/**
 * De ProductVergelijkerView klasse representeert de view waarin producten worden weergegeven en vergeleken kunnen worden.
 *
 * @author Marco de Boer
 */
public class ProductVergelijkerView extends View{

    private Button winkelsButton;

    /**
     * Initialiseert de view.
     */
    @Override
    protected void initializeView() {
        VBox schermVBox = new VBox();
        HBox winkelsHBox = new HBox();
        Label productVergelijkerLabel = new Label("Productvergelijker");
        productVergelijkerLabel.setStyle("-fx-font-size: 30; -fx-font-weight: bold");
        HBox productVergelijkerLabelHBox = new HBox(productVergelijkerLabel);
        productVergelijkerLabelHBox.setPadding(new Insets(40,10,40,10));
        productVergelijkerLabelHBox.setAlignment(Pos.CENTER);
        winkelsButton = new Button("Winkels");
        setButtonStyle(winkelsButton);
        winkelsHBox.getChildren().add(winkelsButton);
        winkelsHBox.setPadding(new Insets(10,10,10,10));
        schermVBox.getChildren().addAll(productVergelijkerLabelHBox,productListViewVBox(),winkelsHBox);
        schermVBox.setPadding(new Insets(20,20,20,20));
        BorderPane standaardBorderPane = getStandaardBorderPane();

        standaardBorderPane.setCenter(schermVBox);

        root = standaardBorderPane;
    }

    /**
     * Geeft de winkels button terug.
     *
     * @return De winkels button.
     */
    public Button getWinkelsButton() {
        return winkelsButton;
    }

    /**
     * Geeft de producten ListView terug.
     *
     * @return De producten ListView.
     */
    public ListView<Product> getListView(){
        return getProductenListView();
    }

    /**
     * Geeft de zoek button terug.
     *
     * @return De zoek button.
     */
    public Button getZoekButtonView(){return getZoekButton();}

    /**
     * Geeft de zoekbalk TextField terug.
     *
     * @return De zoekbalk TextField.
     */
    public TextField getZoekTextField(){return getZoekbalkTextField();}

}
