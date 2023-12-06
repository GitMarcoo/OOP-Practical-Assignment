package practicumopdracht.data;

import practicumopdracht.utils.BestandManager;

import java.util.List;

/**
 * Interface voor DAO-objecten die CRUD-operaties en het laden en opslaan van gegevens uitvoeren.
 *
 * @param <T> het type object dat door de DAO wordt beheerd
 * @author Marco de Boer
 */
public interface DAO<T> {

    /**
     * Geeft een lijst van alle modellen terug.
     *
     * @return een lijst van alle modellen
     * @author
     */
    List<T> getAll();

    /**
     * Voegt een nieuw model toe of werkt een bestaand model bij.
     *
     * @param model het model dat moet worden toegevoegd of bijgewerkt
     * @author
     */
    void addOrUpdate(T model);

    /**
     * Verwijdert het opgegeven model.
     *
     * @param model het model dat moet worden verwijderd
     * @autor Marco de Boer
     */
    void remove(T model);

    /**
     * Slaat de gegevens van het model op.
     *
     * @return true als het opslaan gelukt is, false als er een IOException optreedt
     * @autor Marco de Boer
     */
    boolean save();

    /**
     * Laadt de gegevens van het model in.
     *
     * @return true als het laden gelukt is, false als er een IOException optreedt
     * @autor Marco de Boer
     */
    boolean load();
}
