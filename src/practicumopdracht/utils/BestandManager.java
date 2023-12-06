package practicumopdracht.utils;

import java.io.File;
import java.io.IOException;

/**
 * De BestandManager klasse biedt functionaliteit om bestanden te controleren, verwijderen en aanmaken.
 * @author Marco de Boer
 */
public class BestandManager {

    /**
     * Controleert of het gegeven bestand bestaat.
     *
     * @param bestand Het te controleren bestand.
     * @return true als het bestand bestaat, anders false.
     */
    public boolean controlleerOfBestandBestaat(File bestand){
        return bestand.exists();
    }

    /**
     * Verwijdert het gegeven bestand als het bestaat.
     *
     * @param bestand Het te verwijderen bestand.
     * @return true als het bestand is verwijderd, anders false.
     */
    public boolean verwijderBestand(File bestand){
        if(controlleerOfBestandBestaat(bestand)){
            bestand.delete();
            return true;
        }
        return false;

    }

    /**
     * Maakt het gegeven bestand aan als het nog niet bestaat.
     *
     * @param bestand Het aan te maken bestand.
     * @return true als het bestand is aangemaakt, anders false.
     */
    public boolean maakBestand(File bestand){
        try {
            bestand.createNewFile();
            return true;
        }catch (IOException e){
            System.err.println("Fout tijdens het maken van bestand");
            return false;
        }
    }

}
