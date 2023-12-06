package practicumopdracht.Controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import practicumopdracht.MainApplication;
import practicumopdracht.comparators.PrijsComparator;
import practicumopdracht.comparators.ProductComparator;
import practicumopdracht.models.Product;
import practicumopdracht.models.Winkel;
import practicumopdracht.views.View;
import practicumopdracht.views.ProductView;
import practicumopdracht.views.WinkelView;

import java.time.LocalDate;
import java.util.Collections;

import static practicumopdracht.MainApplication.*;

/**
 * Controller class voor de producten.
 *
 * Deze controller klasse handelt alle acties af die betrekking hebben tot de producten. De klasse bevat de volgende acties:
 *
 * - Sorteren van de producten op naam en prijs
 * - Filteren van de producten op winkel
 * - Zoeken naar producten op naam
 * - Toevoegen van nieuwe producten
 * - Verwijderen van bestaande producten
 * - Valideren van de invoer van de gebruiker
 * - Terugkeren naar het Winkel overzicht scherm
 * - Laden, opslaan en afsluiten van een bestand
 *
 * De controller erft van de abstracte Controller klasse en implementeert de getView() methode.
 *
 * @author Marco
 */


public class ProductController extends Controller{

    private final ProductView view;

    private ObservableList<Winkel> winkelObservableList = FXCollections.observableList(MainApplication.getWinkelDAO().getAll());

    private ObservableList<Product>  productObservableList = FXCollections.observableArrayList(MainApplication.getProductDAO().getAll());

    private FilteredList<Product> productFilteredList = new FilteredList<>(productObservableList);

    private String selectedRadioButton;

    /**
     * Constructor voor de ProductController. Hierin worden de action handlers en overige functionaliteiten geinitialiseerd.
     */


    public ProductController(){
        view = new ProductView();


        setWinkelComboBox();
        //zet de default sortering op naam van A naar Z
        selectedRadioButton = "NaamAZ";

        view.getTerugButton().setOnAction(actionEvent -> handleTerugButton());
        view.getProductOpslaanButton().setOnAction(actionEvent -> handleProductToevoegenButton());
        view.getVerwijderProductButton().setOnAction(actionEvent -> handleVerwijderButton());
        view.getProductNieuwButton().setOnAction(actionEvent -> handleNieuwButton());


        view.getListView().getSelectionModel().selectedItemProperty().addListener((
                observableValue, productOud, productNieuw) ->  handleSelectie(productNieuw));
        view.getListView().setItems(productFilteredList);

        view.getZoekButtonView().setOnAction(actionEvent -> handleZoeken());
        view.getWinkelComboBox().setOnAction(actionEvent -> handleFilterWinkel());


        view.getBestandLaden().setOnAction(actionEvent -> handleBestand("Laden"));
        view.getBestandOpslaan().setOnAction(actionEvent -> handleBestand("Opslaan"));
        view.getBestandAfsluiten().setOnAction(actionEvent -> handleBestand("Afsluiten"));



        view.getDetailPrijsZAButton().setOnAction(actionEvent -> setSelectedRadioButton("PrijsZA"));
        view.getDetailPrijsAZButton().setOnAction(actionEvent -> setSelectedRadioButton("PrijsAZ"));
        view.getDetailNaamZAButton().setOnAction(actionEvent -> setSelectedRadioButton("NaamZA"));
        view.getDetailNaamAZButton().setOnAction(actionEvent -> setSelectedRadioButton("NaamAZ"));

        handleToggle();


    }

    /**
     Filter de producten in de productFilteredList op basis van de zoekwaarde in het zoekveld.
     Als er geen zoekwaarde is ingevuld, wordt er geen filter toegepast en worden alle producten getoond.
     De zoekwaarde wordt omgezet in kleine letters en vergeleken met de namen van de producten in de lijst.
     */

    private void handleZoeken(){
        productFilteredList.setPredicate(product ->{
            /*controleer eerst of er wel een text is ingevuld om naar te zoeken anders wordt true teruggegeven hiermee
            wordt er geen filter toegepast en alles laten zien*/
            if(view.getZoekTextField().getText() == null || view.getZoekTextField().getText().isEmpty()){
                return product.getHoortBijWinkel().equals(view.getWinkelComboBox().getValue());
            }
            /*Als er iets is ingevuld wordt de zoekwaarde omgezet in kleineletter en worden alle producten teruggeven naar
             * de productFilteredList waard de naam de zoekwaarde bevat*/
            String kleineLetterZoekwaarde = view.getZoekTextField().getText().toLowerCase();
            return product.getNaam().toLowerCase().contains(kleineLetterZoekwaarde);
        });

        view.getListView().refresh();
    }

    /**

     Deze methode handelt de actie af van het selecteren van een menu-item in de bestandsmenu.
     Afhankelijk van de meegegeven actie-string worden verschillende acties uitgevoerd:
     "Laden": Het inladen van de data uit het bestand naar de applicatie.
     "Opslaan": Het opslaan van de huidige applicatie-data naar een bestand.
     "Afsluiten": Het afsluiten van de applicatie.
     Bij het uitvoeren van een actie wordt de winkelObservableList en productObservableList geüpdatet
     met de gegevens uit de database. Daarnaast wordt de toggle-status opnieuw ingesteld en wordt
     de lijst van producten vernieuwd en ververst op het scherm.
     @param actie de te ondernemen actie ("Laden", "Opslaan", "Afsluiten")
     */

    private void handleBestand(String actie){
        menuHelper.handleBestand(actie);
        if (actie == "Laden"){
            winkelObservableList = FXCollections.observableArrayList(MainApplication.getWinkelDAO().getAll());
            view.getWinkelComboBox().setItems(winkelObservableList);
            productObservableList = FXCollections.observableArrayList(MainApplication.getProductDAO().getAll());
            handleToggle();
            view.getListView().setItems(productObservableList);
            view.getListView().setItems(productFilteredList);
            handleFilterWinkel();
            view.getListView().refresh();
        }
    }

    /**
     Set de geselecteerde radiobutton en voert de handleToggle functie uit om te sorteren.
     @param selectedRadioButton de naam van de geselecteerde radiobutton.
     */
    private void setSelectedRadioButton(String selectedRadioButton){
        this.selectedRadioButton = selectedRadioButton;
        handleToggle();
    }

    /**
     Deze methode selecteert de juiste sorteermethode op basis van de geselecteerde radiobutton.
     De sorteermethode wordt toegepast op de observableList van Producten en de ListView wordt ververst.
     */
    private void handleToggle(){
        switch (selectedRadioButton) {
            case "NaamAZ" -> Collections.sort(productObservableList, new ProductComparator(true));
            case "NaamZA" -> Collections.sort(productObservableList, new ProductComparator(false));
            case "PrijsAZ" -> Collections.sort(productObservableList, new PrijsComparator(true));
            case "PrijsZA" ->  Collections.sort(productObservableList, new PrijsComparator(false));
            default -> System.err.println("Er is iets mis gegaan met het sorteren van de producten!");
        }
    }

    /**
     Filtert de lijst van producten op de geselecteerde winkel in de winkelComboBox. Als er geen winkel is geselecteerd,
     worden alle producten getoond.
     */
    private void handleFilterWinkel(){
        productFilteredList.setPredicate(product -> {
            if (view.getWinkelComboBox().getValue() == null){
                return true;
            }
            return product.getHoortBijWinkel().equals(view.getWinkelComboBox().getValue());
        });
        view.getListView().refresh();
    }

    /**

     Stelt de combobox van winkels in met de gegeven winkelObservableList.
     Probeert de waarde van de geselecteerde winkel te krijgen uit de WinkelListView en past de filter voor de producten aan op basis van de geselecteerde winkel.
     */
    private void setWinkelComboBox(){
        view.getWinkelComboBox().setItems(winkelObservableList);
        try {
            view.getWinkelComboBox().setValue(WinkelView.getWinkelListView().getSelectionModel().getSelectedItem());
            handleFilterWinkel();
        }catch (Exception e){
            System.err.println("Tijdens het presetten van de winkelcombobox ging iets mis!");
        }
    }
    /**

     Zet alle velden van het ProductView object op leeg of de default waarde.
     */
    private void handleNieuwButton(){
        view.getProductNaamTextField().clear();
        view.getVariantTextField().clear();
        view.getMerkTextField().clear();
        view.getAanbiedingCheckBox().setSelected(false);
        view.getInhoudTextField().clear();
        view.getPrijsTextField().clear();
        view.getListView().getSelectionModel().clearSelection();
    }
    /**

     Vult de velden van het ProductView object met de gegevens van de geselecteerde Product en past de filter voor de producten aan op basis van de geselecteerde winkel.
     @param productNieuw Het geselecteerde Product dat weergegeven moet worden.
     */
    private void handleSelectie(Product productNieuw){
        if (productNieuw == null){
            return;
        }
        Product product = productNieuw;
        view.getProductNaamTextField().setText(product.getNaam());
        view.getVariantTextField().setText(product.getVariant());
        view.getMerkTextField().setText(product.getMerk());
        view.getDatumDatePicker().setValue(product.getLaatstePrijsWijziging());
        view.getAanbiedingCheckBox().setSelected(product.isInAanbieding());
        view.getInhoudTextField().setText(Integer.toString(product.getInhoud()));
        view.getPrijsTextField().setText(Double.toString(product.getPrijs()));
        view.getWinkelComboBox().setValue(product.getHoortBijWinkel());
    }

    /**
     Handelt de actie af wanneer er op de "Product toevoegen" knop wordt geklikt.
     Eerst worden de ingevulde gegevens gevalideerd. Vervolgens worden de gegevens opgehaald uit de tekstvelden,
     comboboxen, checkboxen en datepicker en worden ze gebruikt om een nieuw Product object te maken. Dit object wordt
     toegevoegd aan de lijst van producten en aan de database toegevoegd. Als er al een product geselecteerd was in de lijst,
     wordt deze geüpdatet in plaats van dat er een nieuw product wordt aangemaakt. Daarna wordt de lijst van producten
     gesorteerd en wordt de toggle handle methode aangeroepen.
     */

    private void handleProductToevoegenButton(){
        if(valideerInvulling()){
            String naam = view.getProductNaamTextField().getText();
            String variant = view.getVariantTextField().getText();
            String merk = view.getMerkTextField().getText();
            int inhoud = 0;
            if (view.getInhoudEenheidComboBox().getValue() == "gram"){
                inhoud = Integer.parseInt(view.getInhoudTextField().getText());
            }else {
                inhoud = Integer.parseInt(view.getInhoudTextField().getText() ) * 1000;
            }

            double prijs = Double.parseDouble(view.getPrijsTextField().getText());
            boolean isAanbieding = view.getAanbiedingCheckBox().isSelected();
            LocalDate laatstePrijsWijziging = view.getDatumDatePicker().getValue();

            Winkel winkel =  view.getWinkelComboBox().getValue();

            Product productUitListView = view.getListView().getSelectionModel().getSelectedItem();
            if(productUitListView == null){
                Product product = new Product(naam, variant,merk,inhoud,prijs,isAanbieding,laatstePrijsWijziging,winkel);
                productObservableList.add(product);
                getProductDAO().addOrUpdate(product);
                view.getListView().refresh();
                view.getListView().getSelectionModel().selectLast();

            }else{
                productUitListView.setProductNieuweWaard(naam, variant,merk,inhoud,prijs,isAanbieding,laatstePrijsWijziging);
                view.getListView().refresh();
            }
            handleToggle();
        }else {
            MainApplication.geefInformatieAlert("Niet alles ingevuld!","wel alles invullen heh!");
        }

    }

    /**
     Handelt de actie af wanneer er op de "Verwijderen" knop wordt geklikt. Als er een product is geselecteerd in de lijst,
     wordt deze verwijderd uit de lijst van producten en uit de database. Als er geen product is geselecteerd, gebeurt er niets.
     Als het verwijderen niet lukt om welke reden dan ook, wordt een informatiebericht getoond met de foutmelding.
     */
    private void handleVerwijderButton(){
        if (geefBevestigAlert("Verwijderen!", "Weet u zeker dat u dit wilt verwijderen")){
            try{
                Product product = view.getListView().getSelectionModel().getSelectedItem();
                getProductDAO().remove(product);
                productObservableList.remove(product);
                view.getListView().refresh();
            }catch (Exception e){
                geefInformatieAlert("Verwijder fout", "Er ging iets fout tijdens het verwijder, " +
                        "zorg dat je een product selecteerd en dan verwijderd!");
            }
        }
    }


    /**
     * Als op terug geklikt wordt, wordt door de switchcontroller van de mainapplicatie de controller gewisseld naar de WinkelController.
     */

    private void handleTerugButton(){
        switchController(new WinkelController());
    }

    /**
     * Als deze methode wordt aangeroepen wordt de view teruggegeven
     * @return de view
     */
    @Override
    public View getView() {
        return view;
    }


    /**
     Valideert of alle tekstvelden correct zijn ingevuld bij het toevoegen of wijzigen van een product.
     zo niet komt er een rode rand eromheen.
     @return Boolean waarde die aangeeft of de invoer correct is of niet.
     */
    private boolean valideerInvulling(){
        boolean invullenCorrect = true;
        String naam = view.getProductNaamTextField().getText();
        if (naam == ""){
            view.getProductNaamTextField().setStyle("-fx-border-color: red");
            invullenCorrect = false;
        }else{
            view.getProductNaamTextField().setStyle("-fx-border-color: none");
        }

        String merk = view.getMerkTextField().getText();
        if (merk == ""){
            view.getMerkTextField().setStyle("-fx-border-color: red");
            invullenCorrect = false;

        }else{
            view.getMerkTextField().setStyle("-fx-border-color: none");
        }

        try{
            int inhoud = Integer.parseInt(view.getInhoudTextField().getText());
            if (inhoud == 0){
                view.getInhoudTextField().setStyle("-fx-border-color: red");
                invullenCorrect = false;

            }else{
                view.getInhoudTextField().setStyle("-fx-border-color: none");
            }
        }catch (Exception error){
            view.getInhoudTextField().setStyle("-fx-border-color: red");
            invullenCorrect = false;
        }

        try {
            double prijs = Double.parseDouble(view.getPrijsTextField().getText());
            if (prijs == 0) {
                view.getPrijsTextField().setStyle("-fx-border-color: red");
                invullenCorrect = false;
            } else {
                view.getPrijsTextField().setStyle("-fx-border-color: none");
            }
        }catch (Exception error){
            view.getPrijsTextField().setStyle("-fx-border-color: red");
            invullenCorrect = false;
        }

        String inhoudEenheid = view.getInhoudEenheidComboBox().getValue();
        if (inhoudEenheid != "gram" && inhoudEenheid != "kg"){
            geefInformatieAlert("Waarde fout","Er was een fout tijdens het toevoegen van het" +
                    " product, controlleer of alle waardes kloppen!");
            invullenCorrect = false;
        }

        try {
            LocalDate laatstePrijsWijziging = view.getDatumDatePicker().getValue();
            if (laatstePrijsWijziging == null || laatstePrijsWijziging.isAfter(LocalDate.now())) {
                view.getDatumDatePicker().setStyle("-fx-border-color: red");
                invullenCorrect = false;
            } else {
                view.getDatumDatePicker().setStyle("-fx-border-color: none");
            }
        }catch (Exception error){
            view.getDatumDatePicker().setStyle("-fx-border-color: red");
            invullenCorrect = false;
        }

        try {
            Winkel winkel = view.getWinkelComboBox().getValue();
            if (winkel == null) {
                view.getWinkelComboBox().setStyle("-fx-border-color: red");
                invullenCorrect = false;

            } else {
                view.getWinkelComboBox().setStyle("-fx-border-color: none");
            }
        }catch (Exception error){
            view.getWinkelComboBox().setStyle("-fx-border-color: red");
            invullenCorrect = false;
        }


        return invullenCorrect;
    }

}
