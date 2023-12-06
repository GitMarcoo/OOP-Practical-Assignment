package practicumopdracht.data;

import javafx.application.Platform;
import practicumopdracht.MainApplication;
import practicumopdracht.models.Winkel;

import java.io.*;

public class BinaryWinkelDAO extends WinkelDAO{

    private static final String FILENAME = "winkels.bin";

    /**
     * Slaat alle winkels op in een binair bestand.
     *
     * @author Marco de Boer
     *
     * @return true als het opslaan gelukt is, false als er een IOException optreedt
     */
    @Override
    public boolean save() {
        File bestand = new File(FILENAME);
        if (!bestandManager.controlleerOfBestandBestaat(bestand)){
            bestandManager.maakBestand(bestand);
        }
        try {
            FileOutputStream fos = new FileOutputStream(bestand);
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeInt(winkels.size());
            for (Winkel winkel : winkels){
                dos.writeUTF(winkel.getNaam());
                dos.writeUTF(winkel.getStad());
            }
            dos.close();
            fos.close();
            return true;
        } catch (IOException e){
            System.err.println("Er ging iets mis tijdens het schrijven!, het bestand is mogelijk ingebruik");
            return false;
        }
    }

    /**
     * Laadt alle winkels in vanuit een binair bestand.
     *
     * @return true als het laden gelukt is, false als er een IOException optreedt
     */
    @Override
    public boolean load() {
        File bestand = new File(FILENAME);
        if (!bestandManager.controlleerOfBestandBestaat(bestand)) {
            //bestand winkels bestaat niet!
            if (bestandManager.controlleerOfBestandBestaat(new File(MainApplication.getProductDAO().getBestandsnaam()))){
                //bestand producten bestaat wel!, maar dit geeft conflicten!
                if (MainApplication.geefBevestigAlert("LAAD FOUT","Er ontbreekt een bestand om de" +
                        " gegevens te laden, wilt u de opgeslagen data verwijderen")){
                    bestandManager.verwijderBestand(new File(MainApplication.getProductDAO().getBestandsnaam()));
                } else {
                    MainApplication.geefInformatieAlert("Programm Afsluiten", "Het programma kan niet verder," +
                            " het wordt afgesloten!");
                    Platform.exit();
                    return false;
                }
            }
            bestandManager.maakBestand(bestand);
            return true;
        }else{
        try {
            FileInputStream fileInputStream = new FileInputStream(bestand);
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);
            int aantalWinkels = dataInputStream.readInt();
            for (int i = 0; i < aantalWinkels; i++){
                String naam = dataInputStream.readUTF();
                String stad = dataInputStream.readUTF();
                winkels.add(i,new Winkel(naam, stad));
            }
            dataInputStream.close();
            fileInputStream.close();
            return true;
        } catch (EOFException e){
            System.err.println("Het bestand is leeg");
            return true;
        } catch (IOException e) {
            System.err.println("Er ging iets mis tijdens het lezen!, het bestand is mogelijk ingebruik");
            return false;
        }



        }
    }
}
