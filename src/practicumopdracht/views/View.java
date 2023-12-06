package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import practicumopdracht.models.Product;

/**
 * De View klasse is een abstracte klasse die de basis vormt voor alle views.
 * @author Marco de Boer
 */

public abstract class View {

    protected Parent root;

    protected ListView<Product> productenListView = new ListView<>();

    protected TextField zoekbalkTextField;

    protected Button zoekButton;

    protected RadioButton detailNaamAZButton;
    protected RadioButton detailNaamZAButton;
    protected RadioButton detailPrijsAZButton;
    protected RadioButton detailprijsZAButton;

    protected MenuButton bestandMenuButton;
    protected MenuItem bestandLaden;
    protected MenuItem bestandOpslaan;
    protected MenuItem bestandAfsluiten;

    protected HBox menubalkHBox;



    public View(){initializeView();}

    /**
     * Initialiseert de view.
     */

    protected abstract void initializeView();

    /**
     * Geeft de root terug.
     * @return De root.
     */

    public Parent getRoot(){return root;}

    /**
     * Geeft de producten ListView terug. Hierdoor hoef je eenmalig een ListView aan te maken.
     * en kan je deze in andere views gebruiken.
     * @return De producten ListView.
     */

    protected VBox productListViewVBox(){
        VBox productListViewVBox = new VBox();
        Label productenLabel = new Label("Producten:");
        GridPane zoekGridPane = new GridPane();
        zoekbalkTextField = new TextField();
        setFieldStyle(zoekbalkTextField);
        zoekbalkTextField.setPromptText("Wat wilt u zoeken...");
        zoekbalkTextField.setPrefWidth(200);
        zoekButton = new Button("Zoek");
        setButtonStyle(zoekButton);
        zoekGridPane.add(productenLabel,0,0);
        zoekGridPane.add(zoekbalkTextField,1,0);
        zoekGridPane.add(zoekButton,2,0);

        ToggleGroup detailToggleGroup = new ToggleGroup();

        detailNaamAZButton = new RadioButton("Naam A-Z");
        detailNaamZAButton = new RadioButton("Naam Z-A");
        detailPrijsAZButton = new RadioButton("Prijs oplopend");
        detailprijsZAButton = new RadioButton("Prijs aflopend");

        detailToggleGroup.getToggles().addAll(detailNaamAZButton,detailNaamZAButton,detailPrijsAZButton,detailprijsZAButton);
        detailToggleGroup.selectToggle(detailNaamAZButton);

        zoekGridPane.add(detailNaamAZButton, 3, 0);
        zoekGridPane.add(detailNaamZAButton, 4, 0);
        zoekGridPane.add(detailPrijsAZButton, 5, 0);
        zoekGridPane.add(detailprijsZAButton, 6, 0);


        zoekGridPane.setHgap(40);
        zoekGridPane.setPadding(new Insets(0,0,20,50));

        Label kolomnamenListView = new Label(String.format("%-30s | %-30s | %-30s | %-30s | %-30s | %-30s | %-40s | %-50s",
                                                "     Product","        Variant","      Merk", "     Inhoud", "     Prijs",
                "     Aanbieding", "   Laatste Prijs Wijziging", " Winkel"));

        productenListView.setPrefWidth(Double.MAX_VALUE); //max

        productenListView.setPrefHeight(200);
        setListViewStyle(productenListView);

        productListViewVBox.getChildren().addAll(zoekGridPane,kolomnamenListView,productenListView);

        return productListViewVBox;
    }

    /**
     * Geeft de standaard BorderPane terug.Met deze borderpane bouw je een view op
     * @return De standaard BorderPane.
     */

    protected BorderPane getStandaardBorderPane(){
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefHeight(640);
        borderPane.setPrefWidth(1200);
        borderPane.setStyle("-fx-background-color: #EDE7F6");
        menubalkHBox = new HBox();
        bestandMenuButton = new MenuButton("Bestand");
        setMenuButtonStyle(bestandMenuButton);
        bestandLaden = new MenuItem("Laden");
        bestandOpslaan = new MenuItem("Opslaan");
        bestandAfsluiten = new MenuItem("Afsluiten");
        bestandMenuButton.getItems().addAll(bestandLaden,bestandOpslaan,bestandAfsluiten);



        menubalkHBox.setPadding(new Insets(0,10,0,10));
        menubalkHBox.setStyle("-fx-background-color: #673AB7 ");
        menubalkHBox.getChildren().addAll(bestandMenuButton);
        menubalkHBox.setAlignment(Pos.TOP_LEFT);
        borderPane.setTop(menubalkHBox);


        return borderPane;
    }


    /**
     * Geeft productenListView terug
     * @return productenListView
     */
    protected ListView<Product> getProductenListView() {
        return productenListView;
    }


    /**
     * Een standaard tussenlijn die in andere views gebruikt kan worden.
     * @return Een Separator.
     */
    protected static Separator tussenlijn(){
        Separator tussenlijn = new Separator();
        tussenlijn.setMaxWidth(Double.MAX_VALUE);   //max
        tussenlijn.setPadding(new Insets(20,0,20,0));

        return tussenlijn;
    }


    /**
     * Geeft een textfieldterug. Dit TextField wordt gebruikt als input voor het zoeken
     * @return De zoekbalkTextField.
     */
    public TextField getZoekbalkTextField(){return zoekbalkTextField;}

    /**
     * Geeft de zoekButton terug. Deze button wordt gebruikt om te zoeken.
     * @return De zoekButton.
     */
    public Button getZoekButton(){return zoekButton;}

    /**
     * Maakt het mogelijk om met 1 functie de MenuButton te stylen. Dit kan weer in andere views gebruikt worden.
     * @param menuButton De menuButton die je wilt stylen.
     */
    public void setMenuButtonStyle(MenuButton menuButton){
        menuButton.setStyle("-fx-background-color: #673AB7; -fx-font-weight: bold; -fx-text-fill: white");
        menuButton.onMouseEnteredProperty().set(event -> menuButton.setStyle("-fx-background-color: #8c36e7; -fx-font-weight: bold; -fx-text-fill: white"));
        menuButton.onMouseExitedProperty().set(event -> menuButton.setStyle("-fx-background-color: #673AB7; -fx-font-weight: bold; -fx-text-fill: white"));
    }

    /**
     * Maakt het mogelijk om met 1 functie de button te stylen. Dit kan weer in andere views gebruikt worden.
     * @param button De button die je wilt stylen.
     */

    public void setButtonStyle(ButtonBase button){
        button.setStyle("-fx-background-color: #7E57C2; -fx-text-fill: white; -fx-font-size: 14; -fx-padding: 5 15;");
        button.setOnMouseEntered(mouseEvent -> button.setStyle("-fx-background-color: #5E35B1; -fx-text-fill: white; -fx-font-size: 14; -fx-padding: 5 15;"));
        button.setOnMouseExited(mouseEvent -> button.setStyle("-fx-background-color: #7E57C2;-fx-text-fill: white; -fx-font-size: 14; -fx-padding: 5 15;"));
    }

    /**
     * Maakt het mogelijk om met 1 functie de textfield te stylen. Dit kan weer in andere views gebruikt worden.
     * @param textInputControl De textfield die je wilt stylen.
     */

    public void setFieldStyle(TextInputControl textInputControl){
        textInputControl.setStyle("-fx-background-color: white;" +
                "-fx-border-color: #7E57C2;" +
                "-fx-border-width: 1;" +
                "-fx-border-radius: 5;" +
                "-fx-background-radius: 5;");
    }

    /**
     * Maakt het mogelijk om met 1 functie de listview te stylen. Dit kan weer in andere views gebruikt worden.
     * @param listView De listview die je wilt stylen.
     */
    public void setListViewStyle(ListView listView){
        listView.setStyle(" -fx-background-color: white; -fx-border-color: #7E57C2;" +
                "-fx-border-width: 1; -fx-border-radius: 5;-fx-background-radius: 5; ");
    }

    /**
     * Hierma kan van de detailPrijsdeZAButton de waarde opgevraagd worden.
     * @return De detailPrijsdeZAButton.
     */
    public RadioButton getDetailPrijsZAButton(){
        return detailprijsZAButton;
    }

    /**
     *  Hierma kan van de detailPrijsAZButton de waarde opgevraagd worden.
     * @return De detailPrijsAZButton.
     */
    public RadioButton getDetailPrijsAZButton(){
        return detailPrijsAZButton;
    }

    /**
     * Hierma kan van de detailNaamZAButton de waarde opgevraagd worden.
     * @return De detailNaamZAButton.
     */
    public RadioButton getDetailNaamZAButton(){
        return detailNaamZAButton;
    }

    /**
     * Hierma kan van de detailNaamAZButton de waarde opgevraagd worden.
     * @return De detailNaamAZButton.
     */
    public RadioButton getDetailNaamAZButton(){
        return detailNaamAZButton;
    }


    /**
     * Hiermee kan de bestandenladden menuItem opgehaald worden
     * @return bestandLaden
     */
    public MenuItem getBestandLaden() {
        return bestandLaden;
    }

    /**
     * Hiermee kan de bestandOpslaan menuItem opgehaald worden
     * @return bestandOpslaan
     */
    public MenuItem getBestandOpslaan() {
        return bestandOpslaan;
    }

    /**
     * Hiermee kan de bestandAfsluiten menuItem opgehaald worden
     * @return bestandAfsluiten
     */
    public MenuItem getBestandAfsluiten() {
        return bestandAfsluiten;
    }

}
