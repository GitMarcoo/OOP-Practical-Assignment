package practicumopdracht.views;

import com.sun.javafx.scene.control.DoubleField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import practicumopdracht.models.Product;
import practicumopdracht.models.Winkel;

import java.time.LocalDate;

/**
 * De ProductView klasse representeert de view waarin producten worden weergegeven en toegevoegd kunnen worden.
 *
 * @author Marco de Boer
 */
public class ProductView extends View{
    private Button terugButton;
    private Button verwijderProductButton;

    private Button productOpslaanButton;

    private Button productNieuwButton;
    private TextField productNaamTextField;
    private TextField variantTextField;
    private TextField merkTextField;
    private DatePicker datumDatePicker;
    private CheckBox aanbiedingCheckBox;
    private TextField inhoudTextField;
    private ComboBox<String> inhoudEenheidComboBox;
    private TextField prijsTextField;

    public ComboBox<Winkel> winkelComboBox;

    /**
     * Initialiseert de view.
     */
    @Override
    protected void initializeView() {
        VBox productViewVBox = new VBox();
        GridPane productToevoegenGridPane = new GridPane();

        Label productNaamLabel = new Label("Product");
        productNaamTextField = new TextField();
        setFieldStyle(productNaamTextField);

        Label variantLabel = new Label("Variant");
        variantTextField = new TextField();
        setFieldStyle(variantTextField);

        Label winkelLabel = new Label("Winkel");
        winkelComboBox = new ComboBox<>();

        Label merkLabel = new Label("Merk");
        merkTextField = new TextField();
        setFieldStyle(merkTextField);

        Label datumLabel = new Label("Datum");
        datumDatePicker = new DatePicker();

        datumDatePicker.setValue(LocalDate.now());


        Label aanbiedingLabel = new Label("Aanbieding");
        aanbiedingCheckBox = new CheckBox("Ja");


        Label inhoudLabel = new Label("Inhoud");
        inhoudTextField = new TextField();

        inhoudEenheidComboBox = new ComboBox<>();
        inhoudEenheidComboBox.getItems().addAll("gram","kg");
        inhoudEenheidComboBox.setValue("gram");

        Label prijsLabel = new Label("Prijs");
        prijsTextField = new TextField();

        productOpslaanButton = new Button("Opslaan");
        setButtonStyle(productOpslaanButton);
        productNieuwButton = new Button("Nieuw");
        setButtonStyle(productNieuwButton);

        productToevoegenGridPane.add(productNaamLabel,0,0);
        productToevoegenGridPane.add(productNaamTextField,1,0);
        productToevoegenGridPane.add(variantLabel,0,1);
        productToevoegenGridPane.add(variantTextField,1,1);
        productToevoegenGridPane.add(merkLabel,0,2);
        productToevoegenGridPane.add(merkTextField,1,2);
        productToevoegenGridPane.add(winkelLabel,0,3);
        productToevoegenGridPane.add(winkelComboBox,1,3);
        productToevoegenGridPane.add(datumLabel,3,0);
        productToevoegenGridPane.add(datumDatePicker,4,0);
        productToevoegenGridPane.add(aanbiedingLabel,3,1);
        productToevoegenGridPane.add(aanbiedingCheckBox,4,1);
        productToevoegenGridPane.add(inhoudLabel,3,2);
        productToevoegenGridPane.add(inhoudTextField,4,2);
        productToevoegenGridPane.add(inhoudEenheidComboBox,5,2);
        productToevoegenGridPane.add(prijsLabel,3,3);
        productToevoegenGridPane.add(prijsTextField,4,3);
        productToevoegenGridPane.add(productOpslaanButton,0,4);
        productToevoegenGridPane.add(productNieuwButton,0,5);

        productToevoegenGridPane.setHgap(10);
        productToevoegenGridPane.setVgap(10);
        productToevoegenGridPane.setAlignment(Pos.CENTER);

        HBox onderBalkKnoppenHBox= new HBox();
        verwijderProductButton = new Button("Verwijder");
        setButtonStyle(verwijderProductButton);
        terugButton = new Button("Terug");
        setButtonStyle(terugButton);

        onderBalkKnoppenHBox.setSpacing(20);

        onderBalkKnoppenHBox.getChildren().addAll(verwijderProductButton,terugButton);

        onderBalkKnoppenHBox.setPadding(new Insets(20,0,20,0));

        productViewVBox.setPadding(new Insets(20,10,20,10));

        productViewVBox.getChildren().addAll(productToevoegenGridPane,tussenlijn(),productListViewVBox(),onderBalkKnoppenHBox);

        BorderPane standaarScherm = getStandaardBorderPane();
        standaarScherm.setCenter(productViewVBox);
        root = standaarScherm;
    }



    /**
     * Geeft de terugknop terug.
     *
     * @return de terugknop.
     */
    public Button getTerugButton() {
        return terugButton;
    }

    /**
     * Geeft de knop terug om het product te verwijderen.
     *
     * @return de knop om het product te verwijderen.
     */
    public Button getVerwijderProductButton() {
        return verwijderProductButton;
    }

    /**
     * Geeft de knop terug om het product op te slaan.
     *
     * @return de knop om het product op te slaan.
     */
    public Button getProductOpslaanButton() {
        return productOpslaanButton;
    }

    /**
     * Geeft het tekstveld terug voor de productnaam.
     *
     * @return het tekstveld voor de productnaam.
     */
    public TextField getProductNaamTextField() {
        return productNaamTextField;
    }

    /**
     * Geeft het tekstveld terug voor de productvariant.
     *
     * @return het tekstveld voor de productvariant.
     */
    public TextField getVariantTextField() {
        return variantTextField;
    }

    /**
     * Geeft het tekstveld terug voor het productmerk.
     *
     * @return het tekstveld voor het productmerk.
     */
    public TextField getMerkTextField() {
        return merkTextField;
    }

    /**
     * Geeft de datepicker terug voor de productdatum.
     *
     * @return de datepicker voor de productdatum.
     */
    public DatePicker getDatumDatePicker() {
        return datumDatePicker;
    }

    /**
     * Geeft de checkbox terug voor het productaanbieding.
     *
     * @return de checkbox voor het productaanbieding.
     */
    public CheckBox getAanbiedingCheckBox() {
        return aanbiedingCheckBox;
    }

    /**
     * Geeft het tekstveld terug voor de productinhoud.
     *
     * @return het tekstveld voor de productinhoud.
     */
    public TextField getInhoudTextField() {
        return inhoudTextField;
    }

    /**
     * Geeft de combobox terug voor de inhoudseenheid van het product.
     *
     * @return de combobox voor de inhoudseenheid van het product.
     */
    public ComboBox<String> getInhoudEenheidComboBox() {
        return inhoudEenheidComboBox;
    }

    /**
     * Geeft de combobox terug voor de winkel van het product.
     *
     * @return de combobox voor de winkel van het product.
     */
    public ComboBox<Winkel> getWinkelComboBox() {
        return winkelComboBox;
    }

    /**
     * Geeft het tekstveld terug voor de productprijs.
     *
     * @return het tekstveld voor de productprijs.
     */
    public TextField getPrijsTextField() {
        return prijsTextField;
    }

    /**
     * Geeft de knop terug om een nieuw product toe te voegen.
     *
     * @return de knop om een nieuw product toe te voegen.
     */
    public Button getProductNieuwButton() {
        return productNieuwButton;
    }

    /**
     * Geeft de lijstweergave terug van alle producten.
     *
     * @return de lijstweergave van alle producten.
     */
    public ListView<Product> getListView() {
        return getProductenListView();
    }

    /**
     * Geeft de knop terug om te zoeken naar een product.
     *
     * @return de knop om te zoeken naar een product.
     */
    public Button getZoekButtonView() {
        return getZoekButton();
    }

    /**
     * Geeft het tekstveld terug om te zoeken naar een product.
     *
     * @return het tekstveld om te zoeken naar een product.
     */
    public TextField getZoekTextField(){return getZoekbalkTextField();}




}
