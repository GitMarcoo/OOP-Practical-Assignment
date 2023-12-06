package practicumopdracht.data;

import javafx.application.Platform;
import practicumopdracht.MainApplication;
import practicumopdracht.models.Winkel;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * DAO-klasse voor winkels waarbij de data wordt opgeslagen in een tekstbestand.
 * @author Marco de Boer
 */

public class TextWinkelDAO extends WinkelDAO {


    private static final String FILENAME = "winkels.txt";

    /**
     * Slaat de huidige staat van het object op in het tekstbestand.
     * @return true als het opslaan gelukt is, false als er een IOException optreedt
     */

    @Override
    public boolean save() {
        File bestand = new File(FILENAME);
        final String KOMMA = ",";
        try{
            PrintWriter schrijver = new PrintWriter(bestand);
            for (Winkel winkel: winkels){
                StringBuilder winkelString = new StringBuilder();
                winkelString.append(winkel.getNaam() + KOMMA);
                winkelString.append(winkel.getStad());
                schrijver.println(winkelString);
            }
            schrijver.close();
            return true;
        }catch (Exception e){
            System.err.println("Er ging iets mis tijdens het schrijven van Winkels!");
            return false;

        }
    }

    /**
     * Laadt de data van winkels in vanuit het tekstbestand.
     * @return true als het laden gelukt is, false als er een IOException optreedt
     */

    @Override
    public boolean load() {
        File bestand = new File(FILENAME);
        if(!bestandManager.controlleerOfBestandBestaat(bestand)){
            //bestand winkels bestaat niet!
          if (bestandManager.controlleerOfBestandBestaat(new File(MainApplication.getProductDAO().getBestandsnaam()))){
              //bestand product bestaat ook wel, maar dit geeft conflicten!
              if(MainApplication.geefBevestigAlert("LAAD FOUT","Er ontbreekt een bestand om de" +
                      " gegevens te laden, wilt u de opgeslagen data verwijderen")){
                  bestandManager.verwijderBestand(new File(MainApplication.getProductDAO().getBestandsnaam()));
              }else{
                  MainApplication.geefInformatieAlert("Programm Afsluiten", "Het programma kan niet verder," +
                          " het wordt afgesloten!");
                  Platform.exit();
                  return false;
              }
          }
          bestandManager.maakBestand(bestand);
          return true;
        }else {
            if(leesGegevens(bestand)){
                return true;
            }
            return false;
        }

    }

    /**
     * Leest de gegevens van het bestand in
     * @param bestand het bestand dat gelezen moet worden
     * @return true als het lezen gelukt is, false als er een IOException optreedt
     */
    private boolean leesGegevens(File bestand){
        final String KOMMA = ",";
        try{
            Scanner sc = new Scanner(bestand);
            while(sc.hasNext()){
                try {
                    String winkelString = sc.nextLine();
                    String naam = winkelString.split(KOMMA)[0];
                    String stad = winkelString.split(KOMMA)[1];
                    winkels.add(new Winkel(naam, stad));
                }catch (Exception e){
                    System.err.println("1 item is corrupt");
                }
            }
            sc.close();
            return true;
        }catch (Exception e){
            System.err.println("Er ging iets mis tijdens het lezen van winkels");
            System.err.println(e);
            return false;
        }
    }


}
