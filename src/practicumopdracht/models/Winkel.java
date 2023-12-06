package practicumopdracht.models;

/**
 * De Winkel klasse representeert een winkel met een naam en stad.
 * @author Marco de Boer
 */
public class Winkel {

    private String naam;
    private String stad;

    /**
     * Constructor voor de Winkel klasse.
     *
     * @param naam De naam van de winkel.
     * @param stad De stad waar de winkel zich bevindt.
     */
    public Winkel(String naam, String stad){
        this.naam = naam;
        this.stad = stad;
    }

    /**
     * Getter voor de naam van de winkel.
     *
     * @return De naam van de winkel.
     */
    public String getNaam(){
        return naam;
    }

    /**
     * Getter voor de stad waar de winkel zich bevindt.
     *
     * @return De stad waar de winkel zich bevindt.
     */
    public String getStad(){
        return stad;
    }

    /**
     * Setter om de naam en stad van de winkel te veranderen.
     *
     * @param naam De nieuwe naam van de winkel.
     * @param stad De nieuwe stad waar de winkel zich bevindt.
     */
    public void setWinkelNieuweWaard(String naam, String stad){
        this.naam = naam;
        this.stad = stad;
    }

    /**
     * Geeft een stringrepresentatie van de Winkel klasse.
     *
     * @return Een stringrepresentatie van de Winkel klasse.
     */
    @Override
    public String toString() {
        return String.format("Winkel: %-15s  |  Stad:  %s",naam,stad);
    }
}
