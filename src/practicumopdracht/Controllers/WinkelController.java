/**Deze controller regelt alles voor de Winkel
 *
  *@author Marco de Boer IS102 500902539
 */

package practicumopdracht.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import practicumopdracht.MainApplication;
import practicumopdracht.comparators.NaamComparator;
import practicumopdracht.models.Product;
import practicumopdracht.models.Winkel;
import practicumopdracht.views.View;
import practicumopdracht.views.WinkelView;

import java.util.Collections;

import static practicumopdracht.MainApplication.switchController;

public class WinkelController extends Controller {

    private final WinkelView view;






    private ObservableList<Winkel> winkelObservableList;



    private ObservableList<Product> productObservableList = FXCollections.observableArrayList(MainApplication.getProductDAO().getAll());

    /**
     * De constructor maakt de view aan en set alle acties voor de knoppen, ook worden er gegevens in de observablelist
     * geladen.
     */
    public WinkelController(){
        view = new WinkelView();

        //Gegevens in observable list zetten en in listviews
        winkelObservableList = FXCollections.observableList(MainApplication.getWinkelDAO().getAll());
        Collections.sort(winkelObservableList, new NaamComparator(true));
        view.getWinkelListView().setItems(winkelObservableList);
        view.getWinkelListView().refresh();


        /*Alle button actions toevoegen*/
        view.getWinkelOpslaanButton().setOnAction(actionEvent -> handleWinkelOpslaanButton());
        view.getWinkelNieuwButton().setOnAction(actionEvent -> handleWinkelNieuwButton());
        view.getWinkelVerwijderButton().setOnAction(actionEvent -> handleWinkelVerwijder());
        view.getProductVergelijkerButton().setOnAction(actionEvent -> handleProductVergelijker());
        view.getProductToevoegButton().setOnAction(actionEvent -> handleProductToevoegButton());

        view.getSorteerOpNaamAflopendMenuItem().setOnAction(actionEvent -> handleSorteren("NaamZA"));
        view.getSorteerOpNaamOplopendMenuItem().setOnAction(actionEvent -> handleSorteren("NaamAZ"));

        WinkelView.getWinkelListView().getSelectionModel().selectedItemProperty().addListener((
                (observableValue, winkelOud, winkelNieuw) -> handleSelectie(winkelNieuw)
                ));

        view.getBestandLaden().setOnAction(actionEvent -> handleBestand("Laden"));
        view.getBestandOpslaan().setOnAction(actionEvent -> handleBestand("Opslaan"));
        view.getBestandAfsluiten().setOnAction(actionEvent -> handleBestand("Afsluiten"));

    }

    /**
     * Deze functie sorteer de observablelist op naam
     * @param actie is de type sortering
     */

    private void handleSorteren(String actie){
            switch (actie) {
                /*Naam van A tot Z*/
                case "NaamAZ" -> Collections.sort(winkelObservableList, new NaamComparator(true));
                /*Naam van Z tot A*/
                case "NaamZA" -> Collections.sort(winkelObservableList, new NaamComparator(false));
                default -> System.err.println("Fout bij sorteren");
            }
    }





    /**
     * handleBestand is een functie die uit de menuHelper een functie gebruikt. Bestand is een item uit het menu
     * hierin kan je laden, opslaan en afsluiten
     */
    private void handleBestand(String actie){
        //functie van menuHelper die de actie afhandeld
        menuHelper.handleBestand(actie);

        /*Als voor opslaan of laden gekozen is wordt dit uitgevoerd, Dit gedeelte is voor de observablelist
        te updaten en de listview te updaten, anders blijven deze leeg totdat je van scene wisselt*/
        winkelObservableList = FXCollections.observableArrayList(MainApplication.getWinkelDAO().getAll());
        handleSorteren("NaamAZ");
        view.getWinkelListView().setItems( winkelObservableList);

        view.getWinkelListView().refresh();
    }

    /**
     * Wisselt de controller naar de productvergelijkercontroller
     */

    private void handleProductVergelijker(){
        switchController(new ProductVergelijkerController());
    }



    /**
     * Als je op de verwijderknop klikt wordt deze actie aangeroepen. Met deze actie wordt een winkel verwijderd
     */

    private void handleWinkelVerwijder(){
        /*G*/
        if (MainApplication.geefBevestigAlert("Verwijder", "Weet u zeker dat u dilt wilt verwijderen!")){
            try{
                Winkel winkel = view.getWinkelListView().getSelectionModel().getSelectedItem();
                if (!MainApplication.getProductDAO().getAllFor(winkel).isEmpty()){
                    //Er zijn producten gekoppeld nu eerst een check of de gebruiker deze ook wilt verwijderen
                    if(!MainApplication.geefBevestigAlert("Verwijder", "Er zijn producten gekoppeld " +
                            "wilt u deze ook verwijderen?")){
                        //Gebruiker wilt niet verwijderen
                        return;
                    }
                    for (Product product: MainApplication.getProductDAO().getAllFor(winkel)){
                        MainApplication.getProductDAO().remove(product);
                        productObservableList.remove(product);
                        view.getWinkelListView().refresh();
                    }
                }
                MainApplication.getWinkelDAO().remove(winkel);
                winkelObservableList.remove(winkel);
                view.getWinkelListView().refresh();
                view.getStadTextArea().clear();
                view.getWinkelTextField().clear();
            }catch (Exception e){
                MainApplication.geefInformatieAlert("Verwijder fout", "Er ging iets fout tijdens het verwijder, " +
                        "zorg dat je een product selecteerd en dan verwijderd!");
            }
        }
    }


    /**
     * Deze functie wordt aangeroepen als er op de knop product toevoegen wordt geklikt
     */

    private void handleWinkelOpslaanButton(){


        if(valideerInvulling()){
            String naam = view.getWinkelTextField().getText();
            String stad = view.getStadTextArea().getText();



            Winkel winkelUitListView = view.getWinkelListView().getSelectionModel().getSelectedItem();
            if (winkelUitListView == null){
                Winkel winkel = new Winkel(naam, stad);
                view.getWinkelListView().getItems().add(winkel);
                MainApplication.getWinkelDAO().addOrUpdate(winkel);
                view.getWinkelListView().getSelectionModel().selectLast();
            }else{
                winkelUitListView.setWinkelNieuweWaard(naam, stad);
                view.getWinkelListView().refresh();
            }
        }


    }

    /**
     * Deze functie wordt aangeroepen als er op de nieuw knop wordt geklikt
     * Hiermee wordt de textfield en textarea leeg gemaakt en de listview geselecteerd op niks
     */

    private void handleWinkelNieuwButton(){
        view.getWinkelTextField().clear();
        view.getStadTextArea().clear();
        view.getWinkelListView().getSelectionModel().clearSelection();
    }

    /**
     *  Deze functie wordt aangeroepen als er op een item in de listview wordt geklikt
     *  Hiermee wordt de textfield en textarea gevuld met de gegevens van de winkel
     * @param winkelNieuw is de winkel die geselecteerd is
     */

    private void handleSelectie(Winkel winkelNieuw){
        if (winkelNieuw != null){
            view.getWinkelTextField().setText(winkelNieuw.getNaam());
            view.getStadTextArea().setText(winkelNieuw.getStad());
        }
    }

    /**
     * Deze functie wordt aangeroepen als er op de product toevoegen knop wordt geklikt
     * Hiermee wordt de controller gewisseld naar de productcontroller
     */

    private void handleProductToevoegButton(){
        switchController(new ProductController());
    }


    /**
     * Deze functie geeft de view terug
     * @return view
     */

    @Override
    public View getView() {
        return view;
    }

    /**
     * Deze functie valideert of de invulling van de textfield en textarea correct is
     * Als dit niet het geval is wordt de border rood gemaakt
     * @return invullingCorrect is true of false
     */

    private boolean valideerInvulling(){
        boolean invullingCorrect = true;
        String winkelnaam = view.getWinkelTextField().getText();
        if (winkelnaam.equals("")){
            view.getWinkelTextField().setStyle("-fx-border-color: red");
            invullingCorrect = false;
        } else {
            view.getWinkelTextField().setStyle("-fx-border-color: none;");
        }

        String stad = view.getStadTextArea().getText();
        if (stad.equals("")){
            view.getStadTextArea().setStyle("-fx-border-color: red");
            invullingCorrect = false;
        }else{
            view.getStadTextArea().setStyle("-fx-border-color: none");
        }
        return invullingCorrect;

    }



}
