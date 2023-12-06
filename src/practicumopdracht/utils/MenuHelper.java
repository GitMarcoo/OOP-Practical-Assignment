package practicumopdracht.utils;

import javafx.application.Platform;
import practicumopdracht.MainApplication;
import practicumopdracht.views.View;

/**
 * De MenuHelper klasse biedt functionaliteit voor het uitvoeren van acties die te maken hebben met het menu.
 *
 * @author Marco de Boer
 */

public class MenuHelper{

    private boolean bestandenGeladen = false;

    /**
     * Handelt de gegeven actie af.
     *
     * @param actie De actie die moet worden uitgevoerd.
     */

    public void handleBestand(String actie){
        try{
            switch (actie) {
                case "Laden" -> executeBestand("Laden");
                case "Opslaan"-> executeBestand("Opslaan");
                case "Afsluiten" -> executeBestand("Afsluiten");
        }
        }catch (NullPointerException e){
            System.err.println("Er is geen bestand geselecteerd");
        }

    }

    /**
     * Handelt de gegeven actie af.
     * @param actie De actie die moet worden uitgevoerd.
     */

    private void executeBestand(String actie){
        switch (actie){
            case "Laden":
                if (!bestandenGeladen){
                    if(MainApplication.geefBevestigAlert("Bestand laden", "Weet u zeker dat u het de gegevens wilt laden?")){
                        if(!MainApplication.getWinkelDAO().load()){
                            MainApplication.geefInformatieAlert("LAAD FOUT", "Er ging iets fout tijdens het laden van de winkels!");
                            break;
                        }

                        if(!MainApplication.getProductDAO().load()){
                            MainApplication.geefInformatieAlert("LAAD FOUT", "Er ging iets fout tijdens het laden van de producten!");
                            break;
                        }
                        bestandenGeladen = true;
                        MainApplication.geefInformatieAlert("Bestand Laden", "Alle gegevens zijn succesvol geladen!");
                    }
                }else{
                    MainApplication.geefInformatieAlert("Bestand Laden", "Alle gegevens zijn al geladen!");
                }
                break;
            case "Opslaan":
                if(MainApplication.geefBevestigAlert("Gegevens opslaan", "Weet u zeker dat u het de gegevens wilt opslaan?")){
                    Opslaan();
                }
                break;
            case "Afsluiten":
                if(MainApplication.geefBevestigAlert("Programma sluiten", "Wilt u nog een keer de data opslaan?")){
                    Opslaan();
                    Platform.exit();
                }else{
                    Platform.exit();
                }
            default:break;
        }
    }

    /**
     * Standaard functie om de gegevens op te slaan
     *
     */

    private void Opslaan(){
        if (!bestandenGeladen){
            if (!MainApplication.geefBevestigAlert("OPSLAAN","U heeft nog geen gegevens geladen, als u " +
                    "nu opslaat worden de andere gegevens verwijderd, wilt u eerst de gegevens laden?")){
                bestandenGeladen = true;
            } else {
                if(!MainApplication.getWinkelDAO().load()){
                    MainApplication.geefInformatieAlert("LAAD FOUT", "Er ging iets fout tijdens het laden van de winkels!");
                    return;
                }
                if(!MainApplication.getProductDAO().load()){
                    MainApplication.geefInformatieAlert("LAAD FOUT", "Er ging iets fout tijdens het laden van de producten!");
                    return;
                }

                bestandenGeladen = true;
            }
        }
            if(!MainApplication.getWinkelDAO().save()){
                MainApplication.geefInformatieAlert("OPSLAAN FOUT", "Er ging iets fout tijdens het opslaan van de winkels!");
                return ;
            }
            if(!MainApplication.getProductDAO().save()){
                MainApplication.geefInformatieAlert("OPSLAAN FOUT", "Er ging iets fout tijdens het opslaan van de producten!");
                return ;
            }
            MainApplication.geefInformatieAlert("Bestand opslaan", "Alle gegevens zijn succesvol opgeslagen!");

        }

}
