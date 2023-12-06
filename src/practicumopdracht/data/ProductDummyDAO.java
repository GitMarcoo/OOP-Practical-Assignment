package practicumopdracht.data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.Product;

import java.time.LocalDate;
import java.util.List;

/**
 * Dummy DAO-klasse die tijdelijke data beheert voor producten.
 *
 * @autor Marco de Boer
 */
public class ProductDummyDAO extends ProductDAO{

    @Override
    public String getBestandsnaam() {
        return null;
    }

    /**
     * Slaat de huidige staat van het object op.
     *
     * @return true als het opslaan gelukt is, false als er een IOException optreedt
     */
    @Override
    public boolean save() {
        return false;
    }

    /**
     * Laadt de tijdelijke data in voor producten.
     *
     * @return true als het laden gelukt is, false als er een IOException optreedt
     */
    @Override
    public boolean load() {
        MainApplication.getProductDAO().addOrUpdate(new Product("Kaas", "Stink",
                "Gouda", 200, 5.30, false, LocalDate.now(), MainApplication.getWinkelDAO().getById(0)));
        MainApplication.getProductDAO().addOrUpdate(new Product("Rijst", "",
                "Vomar", 1000, 3.40, true, LocalDate.now(), MainApplication.getWinkelDAO().getById(0)));
        MainApplication.getProductDAO().addOrUpdate(new Product("Kaas", "Stink",
                "Gouda", 200, 4.30, true, LocalDate.now(), MainApplication.getWinkelDAO().getById(1)));
        MainApplication.getProductDAO().addOrUpdate(new Product("Kaas", "Stink",
                "Rijst", 900, 6.30, false, LocalDate.now(), MainApplication.getWinkelDAO().getById(1)));

        return true;
    }
}
