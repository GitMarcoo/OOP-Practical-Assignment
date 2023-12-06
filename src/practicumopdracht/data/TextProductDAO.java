package practicumopdracht.data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.Product;
import practicumopdracht.models.Winkel;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * DAO-klasse voor producten waarbij de data wordt opgeslagen in een tekstbestand.
 *
 * @autor Marco de Boer
 */
public class TextProductDAO extends ProductDAO{



    private static final String FILENAME = "producten.txt";

    @Override
    public String getBestandsnaam() {
        return FILENAME;
    }

    /**
     * Slaat de huidige staat van het object op in het tekstbestand.
     *
     * @return true als het opslaan gelukt is, false als er een IOException optreedt
     */
    @Override
    public boolean save() {
        File bestand = new File(FILENAME);
        final String KOMMA = ",";
        try{
            PrintWriter schrijver = new PrintWriter(bestand);
            for (Product  product: producten){
                //naam,variant,merk,inhoud,prijs,isAanbieding,laatstePrijsWijziging,hoortBijWinkel
                StringBuilder productString = new StringBuilder();
                productString.append(product.getNaam()).append(KOMMA);
                productString.append(product.getVariant()).append(KOMMA);
                productString.append(product.getMerk()).append(KOMMA);
                productString.append(product.getInhoud()).append(KOMMA);
                productString.append(product.getPrijs()).append(KOMMA);
                productString.append(product.isInAanbieding()).append(KOMMA);
                productString.append(product.getLaatstePrijsWijziging().toString()).append(KOMMA);
                productString.append(MainApplication.getWinkelDAO().getIdFor(product.getHoortBijWinkel()));
                schrijver.println(productString);
            }
            schrijver.close();
            return true;
        }catch (Exception e){
            System.err.println("Er ging iets mis tijdens het schrijven!");
        }
        return false;
    }

    /**
     * Laadt de data van producten in vanuit het tekstbestand.
     *
     * @return true als het laden gelukt is, false als er een IOException optreedt
     */
    @Override
    public boolean load() {
        File bestand = new File(FILENAME);
        if(!bestandManager.controlleerOfBestandBestaat(bestand)){
            bestandManager.maakBestand(bestand);
            return true;
        }else {
            final String KOMMA = ",";
            try{
                Scanner sc = new Scanner(bestand);
                while(sc.hasNext()){
                    try {
                        String productString = sc.nextLine();
                        String naam = productString.split(KOMMA)[0];
                        String variant = productString.split(KOMMA)[1];
                        String merk = productString.split(KOMMA)[2];
                        int inhoud = Integer.parseInt(productString.split(KOMMA)[3]);
                        double prijs = Double.parseDouble(productString.split(KOMMA)[4]);
                        boolean isAanbieding = Boolean.parseBoolean(productString.split(KOMMA)[5]);
                        LocalDate laatstePrijsWijziging = LocalDate.parse(productString.split(KOMMA)[6]);
                        int hoortBijWinkelID = Integer.parseInt(productString.split(KOMMA)[7]);
                        Winkel winkel = MainApplication.getWinkelDAO().getById(hoortBijWinkelID);
                        if (winkel != null){
                            producten.add(new Product(naam, variant, merk, inhoud, prijs, isAanbieding, laatstePrijsWijziging, winkel));
                        }else {
                            return false;
                        }
                    }catch (Exception e){
                        System.err.println("1 item is corrupt");
                        return false;
                    }
                }
                sc.close();

                //naam,variant,merk,inhoud,prijs,isAanbieding,laatstePrijsWijziging,hoortBijWinkel

            }catch (Exception e){
                System.err.println("Er ging iets mis tijdens het lezen!");
                return false;
            }
            return true;
        }
    }

    /**
     * Geeft de naam van het bestand terug
     * @return de naam van het bestand
     */




}
