package practicumopdracht.data;

import practicumopdracht.models.Winkel;
import practicumopdracht.utils.BestandManager;

import java.util.ArrayList;
import java.util.List;

public abstract class WinkelDAO implements DAO<Winkel> {

    /**
     * De lijst met winkels.
     */
    protected ArrayList<Winkel> winkels;

    protected BestandManager bestandManager = new BestandManager();

    /**
     * Maakt een nieuwe WinkelDAO met een lege lijst van winkels.
     */
    public WinkelDAO(){
        winkels = new ArrayList<>();
    }

    @Override
    public List<Winkel> getAll() {
        return winkels;
    }

    @Override
    public void addOrUpdate(Winkel winkel) {
        if (!winkels.contains(winkel)){
            winkels.add(winkel);
        }
    }

    /**
     * Zoekt een Winkel op basis van zijn ID.
     *
     * @param id Het ID van de te zoeken winkel.
     * @return De Winkel met het opgegeven ID, of null als deze niet bestaat.
     */
    public Winkel getById(int id){
        try {
            return winkels.get(id);
        }catch (Exception e){
            System.out.println("WinkelId bestaat niet!");
            return null;
        }
    }

    /**
     * Geeft het ID van een Winkel terug.
     *
     * @param winkel De Winkel waarvan het ID moet worden opgehaald.
     * @return Het ID van de opgegeven Winkel, of -1 als deze niet bestaat.
     */
    public int getIdFor(Winkel winkel){
        return winkels.lastIndexOf(winkel);
    }

    @Override
    public void remove(Winkel winkel) {
        if (winkel != null){
            winkels.remove(winkel);
        }
    }

    /**
     * Slaat de lijst met winkels op.
     *
     * @return true als het opslaan is gelukt, anders false.
     */
    @Override
    public abstract boolean save();

    /**
     * Laadt de lijst met winkels in.
     *
     * @return true als het laden is gelukt, anders false.
     */
    @Override
    public abstract boolean load();

}
