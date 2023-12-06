package practicumopdracht.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import practicumopdracht.MainApplication;
import practicumopdracht.comparators.PrijsComparator;
import practicumopdracht.comparators.ProductComparator;
import practicumopdracht.models.Product;
import practicumopdracht.views.ProductVergelijkerView;
import practicumopdracht.views.View;

import java.util.Collections;

import static practicumopdracht.MainApplication.switchController;

/**
 * De controller voor het scherm waarop producten kunnen worden vergeleken.
 * @author Marco de Boer
 */
public class ProductVergelijkerController extends Controller {

    private ProductVergelijkerView view;

    private String selectedRadioButton;
    private ObservableList<Product> productObservableList = FXCollections.observableArrayList(MainApplication.getProductDAO().getAll());
    private FilteredList<Product> productFilteredList = new FilteredList<>(productObservableList);

    /**
     * Constructor voor de ProductVergelijkerController.
     * Initialiseert de view en event handlers.
     */
    public ProductVergelijkerController() {
        view = new ProductVergelijkerView();
        selectedRadioButton = "NaamAZ";

        view.getListView().setItems(productFilteredList);
        view.getZoekButtonView().setOnAction(actionEvent -> handleZoeken());

        view.getWinkelsButton().setOnAction(actionEvent -> handleWinkelsButton());

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
     * Verwerkt het bestand de actie die vanuit de menubalk bestand aangroepen wordt.
     *
     * @param actie de actie die is geselecteerd in het menu
     */
    private void handleBestand(String actie){
        //functie van menuHelper die de actie afhandeld
        menuHelper.handleBestand(actie);

        /*Als voor opslaan of laden gekozen is wordt dit uitgevoerd, Dit gedeelte is voor de observablelist
        te updaten en de listview te updaten, anders blijven deze leeg totdat je van scene wisselt*/
        productObservableList = FXCollections.observableArrayList(MainApplication.getProductDAO().getAll());
        handleToggle();
        view.getListView().setItems(productObservableList);

        view.getListView().refresh();
    }

    /**
     * Verwerkt het selecteren van een radiobutton om de sorteer volgorde te veranderen.
     *
     * @param selectedRadioButton de geselecteerde radiobutton
     */
    private void setSelectedRadioButton(String selectedRadioButton){
        this.selectedRadioButton = selectedRadioButton;
        handleToggle();
    }

    /**
     * Verwerkt het sorteren van de lijst met producten.
     */
    private void handleToggle(){
        switch (selectedRadioButton) {
            case "NaamAZ" -> Collections.sort(productObservableList, new ProductComparator(true));
            case "NaamZA" -> Collections.sort(productObservableList, new ProductComparator(false));
            case "PrijsAZ" -> Collections.sort(productObservableList, new PrijsComparator(true));
            case "PrijsZA" ->  Collections.sort(productObservableList, new PrijsComparator(false));
            default -> System.err.println("Fout bij het sorteren van de lijst met producten.");
        }

    }

    /**
     * Verwerkt het zoeken van een product.
     */
    private void handleZoeken(){
        productFilteredList.setPredicate(product ->{
            /*controleer eerst of er wel een text is ingevuld om naar te zoeken anders wordt true teruggegeven hiermee
            wordt er geen filter toegepast en alles laten zien*/
            if(view.getZoekTextField().getText() == null || view.getZoekTextField().getText().isEmpty()){
                return true;
            }
            /*Als er iets is ingevuld wordt de zoekwaarde omgezet in kleineletter en worden alle producten teruggeven naar
             * de productFilteredList waard de naam de zoekwaarde bevat*/
            String kleineLetterZoekwaarde = view.getZoekTextField().getText().toLowerCase();
            return product.getNaam().toLowerCase().contains(kleineLetterZoekwaarde);
        });

        view.getListView().refresh();
    }


    /**
     * Verwerkt het klikken op de winkels knop.
     */
    private void handleWinkelsButton(){
        switchController(new WinkelController());
    }


    /**
     * Geeft de view terug.
     * @return de view
     */


    @Override
    public View getView() {
        return view;
    }
}
