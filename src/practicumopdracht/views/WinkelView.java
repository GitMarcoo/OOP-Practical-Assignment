package practicumopdracht.views;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import practicumopdracht.models.Winkel;

/**
 * De WinkelView klasse representeert de view waarin winkels worden weergegeven en toegevoegd kunnen worden.
 * @author Marco de Boer
 */

public class WinkelView extends View {

    private Button winkelOpslaanButton;

    private Button nieuwProductButton;

    private Button winkelNieuwButton;

    public Button winkelVerwijderButton;

    private TextField winkelTextField;

    private TextArea stadTextArea;

    private Button productVergelijkerButton;

    private static ListView<Winkel> winkelListView;

    private MenuItem sorteerOpNaamOplopendMenuItem;

    private MenuItem sorteerOpNaamAflopendMenuItem;


    /**
     * Initialiseert de view.
     */

    @Override
    protected void initializeView() {
        VBox schermVBox = new VBox();

        HBox nieuwProductHBox = new HBox();

        Label winkelsLabel = new Label("Winkels");
        winkelsLabel.setStyle("-fx-font-size: 30; -fx-font-weight: bold");
        HBox winkelsLabelHBox = new HBox(winkelsLabel);
        winkelsLabelHBox.setPadding(new Insets(40,10,40,10));
        winkelsLabelHBox.setAlignment(Pos.CENTER);

        nieuwProductButton = new Button("Nieuw Product");
        productVergelijkerButton = new Button("ProductVergelijker");
        setButtonStyle(nieuwProductButton);
        setButtonStyle(productVergelijkerButton);
        nieuwProductHBox.setSpacing(20);

        nieuwProductHBox.getChildren().addAll(nieuwProductButton,productVergelijkerButton);
        nieuwProductHBox.setPadding(new Insets(10,10,10,10));

        schermVBox.setPadding(new Insets(30,20,20,20));
        schermVBox.getChildren().addAll(winkelsLabelHBox,winkelSchermHBox(),nieuwProductHBox);

        BorderPane standaardBorderPane = getStandaardBorderPane();
        voegSorterenToeAanMenuBar();

        standaardBorderPane.setCenter(schermVBox);

        root = standaardBorderPane;
    }

    /**
     * Functie om het sorteren menu toe te voegen aan de menubalk
     */

    private void voegSorterenToeAanMenuBar(){
        MenuButton sorterenMenuButton = new MenuButton("Sorteren");
        setMenuButtonStyle(sorterenMenuButton);
        sorteerOpNaamOplopendMenuItem = new MenuItem("Naam A-Z");
        sorteerOpNaamAflopendMenuItem = new MenuItem("Naam Z-A");
        sorterenMenuButton.getItems().addAll(sorteerOpNaamOplopendMenuItem,sorteerOpNaamAflopendMenuItem);
        menubalkHBox.getChildren().add(sorterenMenuButton);
    }

    /**
     * Functie om het winkelscherm op te bouwen
     * @return Het winkelscherm
     */
    private GridPane winkelSchermHBox(){
        GridPane winkelSchermGridPane = new GridPane();

        VBox winkelListViewVBOX = new VBox();
        Label winkelsLabel = new Label("Winkels:");
        winkelListView = new ListView<>();
        winkelListView.setPrefWidth(300);
        winkelListView.setPrefHeight(100);
        setListViewStyle(winkelListView);

        winkelListViewVBOX.getChildren().addAll(winkelsLabel, winkelListView);

        GridPane winkelGridPane = new GridPane();
        Label winkelLabel = new Label("Winkel");
        Label stadLabel = new Label("Stad");

        winkelTextField = new TextField();
        setFieldStyle(winkelTextField);
        stadTextArea = new TextArea();
        setFieldStyle(stadTextArea);

        stadTextArea.setMaxHeight(3);
        stadTextArea.setMaxWidth(200);

        winkelOpslaanButton = new Button("Opslaan");
        setButtonStyle(winkelOpslaanButton);

        winkelNieuwButton = new Button("Nieuw");
        setButtonStyle(winkelNieuwButton);

        winkelVerwijderButton = new Button("Verwijder");
        setButtonStyle(winkelVerwijderButton);

        winkelGridPane.add(winkelLabel, 0, 0);
        winkelGridPane.add(winkelTextField,1,0);
        winkelGridPane.add(stadLabel,0,1);
        winkelGridPane.add(stadTextArea,1,1);
        winkelGridPane.add(winkelOpslaanButton,3,0);
        winkelGridPane.add(winkelNieuwButton, 3, 1);
        winkelGridPane.add(winkelVerwijderButton,4,0);

        winkelGridPane.setVgap(10);
        winkelGridPane.setHgap(10);

        winkelGridPane.setPadding(new Insets(0,0,0,30));

        winkelSchermGridPane.add(winkelListViewVBOX, 0, 0);
        winkelSchermGridPane.add(winkelGridPane, 1, 0);

        winkelGridPane.setAlignment(Pos.CENTER);

        return winkelSchermGridPane;
    }

    /**
     * Functie om de winkelopslaanbutton op te halen
     * @return De winkelopslaanbutton
     */
    public Button getWinkelOpslaanButton(){
        return winkelOpslaanButton;
    }
    /**
     * Geeft de knop terug om een nieuw product toe te voegen.
     *
     * @return de knop om een nieuw product toe te voegen.
     */
    public Button getProductToevoegButton() {
        return nieuwProductButton;
    }

    /**
     * Geeft de knop terug om een winkel te verwijderen.
     *
     * @return de knop om een winkel te verwijderen.
     */
    public Button getWinkelVerwijderButton() {
        return winkelVerwijderButton;
    }

    /**
     * Geeft het menu-item terug om op naam oplopend te sorteren.
     *
     * @return het menu-item om op naam oplopend te sorteren.
     */
    public MenuItem getSorteerOpNaamOplopendMenuItem() {
        return sorteerOpNaamOplopendMenuItem;
    }

    /**
     * Geeft het menu-item terug om op naam aflopend te sorteren.
     *
     * @return het menu-item om op naam aflopend te sorteren.
     */
    public MenuItem getSorteerOpNaamAflopendMenuItem() {
        return sorteerOpNaamAflopendMenuItem;
    }

    /**
     * Geeft het tekstveld terug voor de winkelnaam.
     *
     * @return het tekstveld voor de winkelnaam.
     */
    public TextField getWinkelTextField() {
        return winkelTextField;
    }

    /**
     * Geeft het tekstveld terug voor de stad van de winkel.
     *
     * @return het tekstveld voor de stad van de winkel.
     */
    public TextArea getStadTextArea() {
        return stadTextArea;
    }

    /**
     * Geeft de knop terug om een nieuwe winkel toe te voegen.
     *
     * @return de knop om een nieuwe winkel toe te voegen.
     */
    public Button getWinkelNieuwButton() {
        return winkelNieuwButton;
    }

    /**
     * Geeft de lijstweergave terug van alle winkels.
     *
     * @return de lijstweergave van alle winkels.
     */
    public static ListView<Winkel> getWinkelListView() {
        return winkelListView;
    }

    /**
     * Geeft de knop terug om de productvergelijker te openen.
     *
     * @return de knop om de productvergelijker te openen.
     */
    public Button getProductVergelijkerButton() {
        return productVergelijkerButton;
    }

}

