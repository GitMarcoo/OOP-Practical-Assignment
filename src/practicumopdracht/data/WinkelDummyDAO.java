package practicumopdracht.data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.Winkel;

import java.util.List;

public class WinkelDummyDAO extends WinkelDAO {

    /**
     * Geeft alle winkels terug. Omdat dit een dummy DAO is, wordt hier altijd null geretourneerd.
     *
     * @return null, omdat deze DAO geen echte data bevat.
     */
    @Override
    public List<Winkel> getAll() {
        return null;
    }

    /**
     * Slaat de lijst met winkels niet op, omdat dit een dummy DAO is.
     *
     * @return altijd false, omdat deze DAO geen echte data bevat.
     */
    @Override
    public boolean save() {
        return false;
    }

    /**
     * Laadt de dummy data in deze DAO.
     *
     * @return altijd true, omdat deze DAO altijd dummy data heeft.
     */
    @Override
    public boolean load() {
        MainApplication.getWinkelDAO().addOrUpdate(new Winkel("Vomar", "Uitgeest"));
        MainApplication.getWinkelDAO().addOrUpdate(new Winkel("Albert Heijn", "Heerhugowaard"));

        return true;
    }
}
