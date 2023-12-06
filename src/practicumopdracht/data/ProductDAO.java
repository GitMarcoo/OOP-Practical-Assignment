package practicumopdracht.data;

import javafx.collections.transformation.FilteredList;
import practicumopdracht.models.Product;
import practicumopdracht.models.Winkel;
import practicumopdracht.utils.BestandManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstracte klasse voor DAO-objecten die CRUD-operaties en het laden en opslaan van gegevens uitvoeren voor producten.
 *

 * @autor Marco de Boer
 */
public abstract class ProductDAO implements DAO<Product>{

    protected ArrayList<Product> producten;

    protected BestandManager bestandManager = new BestandManager();

    /**
     * Constructor voor ProductDAO-objecten.
     */
    public ProductDAO(){
        producten = new ArrayList<>();
    }

    /**
     * Geeft een lijst van alle producten terug.
     *
     * @return een lijst van alle producten
     */
    @Override
    public List getAll() {
        return producten;
    }

    /**
     * Voegt een nieuw product toe of werkt een bestaand product bij.
     *
     * @param product het product dat moet worden toegevoegd of bijgewerkt
     */
    @Override
    public void addOrUpdate(Product product) {
        if (!producten.contains(product)){
            producten.add(product);
        }
    }

    /**
     * Geeft een lijst van alle producten voor de opgegeven winkel.
     *
     * @param winkel de winkel waarvan de producten moeten worden opgehaald
     * @return een lijst van alle producten voor de opgegeven winkel
     */
    public ArrayList<Product> getAllFor (Winkel winkel){
        ArrayList<Product> gefilterdeProducten = new ArrayList<>();
        for (Product product: producten){
            if (product.getHoortBijWinkel().equals(winkel)){
                gefilterdeProducten.add(product);
            }
        }
        return gefilterdeProducten;
    }

    /**
     * Verwijdert het opgegeven product.
     *
     * @param product het product dat moet worden verwijderd
     */
    @Override
    public void remove(Product product) {
        if (product != null){
            producten.remove(product);
        }
    }

    public abstract String getBestandsnaam();

    /**
     * Slaat de gegevens van het product op.
     *
     * @return true als het opslaan gelukt is, false als er een IOException optreedt
     */
    @Override
    public abstract boolean save();

    /**
     * Laadt de gegevens van het product in.
     *
     * @return true als het laden gelukt is, false als er een IOException optreedt
     */
    @Override
    public abstract  boolean load();
}
