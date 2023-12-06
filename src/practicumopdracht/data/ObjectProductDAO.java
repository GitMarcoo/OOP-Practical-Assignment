package practicumopdracht.data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.Product;
import practicumopdracht.models.ProductForSerialization;

import java.io.*;
import java.time.LocalDate;

public class ObjectProductDAO extends ProductDAO {

    private final String FILENAME = "products.obj";

    @Override
    public String getBestandsnaam() {
        return FILENAME;
    }

    /**
     * Slaat de producten op in een objectbestand.
     *
     * @return true als het opslaan gelukt is, false als er een IOException optreedt
     * @author Marco de Boer
     */
    @Override
    public boolean save() {
        File bestand = new File(FILENAME);
        if (!bestandManager.controlleerOfBestandBestaat(bestand)){
            bestandManager.maakBestand(bestand);
        }
        try {
            FileOutputStream fis = new FileOutputStream(bestand);
            ObjectOutputStream oos = new ObjectOutputStream(fis);
            oos.writeInt(producten.size());
            for (Product product : producten){
                ProductForSerialization productForSerialization = new ProductForSerialization(product.getNaam(),
                        product.getVariant(), product.getMerk(), product.getInhoud(), product.getPrijs(),
                        product.isInAanbieding(), product.getLaatstePrijsWijziging().toString(), MainApplication.getWinkelDAO().getIdFor(product.getHoortBijWinkel()));

                oos.writeObject(productForSerialization);
            }
            oos.close();
            fis.close();
            return true;
        } catch (IOException e) {
            System.err.println("Er ging iets mis tijdens het schrijven!. Het bestand is mogelijk in gebruik.");
            return false;
        }
    }

    /**
     * Laadt de producten in vanuit een objectbestand.
     *
     * @return true als het laden gelukt is, false als er een IOException optreedt
     * @author Marco de Boer
     */
    @Override
    public boolean load() {
        File bestand = new File(FILENAME);
        if (!bestandManager.controlleerOfBestandBestaat(bestand)){
            bestandManager.maakBestand(bestand);
        }
        try{
            if (bestand.length() == 0){
                return true;
            }
            FileInputStream fis = new FileInputStream(bestand);
            ObjectInputStream ois = new ObjectInputStream(fis);
            int aantalProducten = ois.readInt();
            System.out.println(aantalProducten);
            for(int i = 0; i<aantalProducten; i++){
                ProductForSerialization product = (ProductForSerialization) ois.readObject();
                Product product1 = new Product(product.getNaam(), product.getVariant(), product.getMerk(), product.getInhoud(), product.getPrijs(),
                        product.isIsAanbieding(), LocalDate.parse(product.getLaatstePrijsWijziging()), MainApplication.getWinkelDAO().getById(product.getHoortBijWinkelId()));
                producten.add(product1);

            }
            ois.close();
            fis.close();
            return true;
        } catch (EOFException e){
            System.err.println("Het bestand is leeg");
            return true;
        } catch (IOException e) {
            System.err.println("Er ging iets mis tijdens het lezen!, het bestand is mogelijk ingebruik");
            return false;
        } catch (ClassNotFoundException e) {
            System.err.println("Er ging iets mis tijdens het lezen!, mogelijk is het bestand corrupt");
            return false;
        }
    }


}

