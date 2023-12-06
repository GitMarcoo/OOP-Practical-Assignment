package practicumopdracht.models;

import java.io.Serializable;

/**
 * Een klasse die een Product voorstelt.
 * Deze klasse is Serializable, zodat het object kan worden opgeslagen in een bestand.
 * Het verschil met deze class is dat de Winkel niet wordt opgeslagen, maar alleen het ID van de Winkel.
 * @author Marco de Boer
 */

public class ProductForSerialization implements Serializable {

    /**
     * De naam van het Product.
     */
    private String naam;

    /**
     * De variant van het Product.
     */
    private String variant;

    /**
     * Het merk van het Product.
     */
    private String merk;

    /**
     * De inhoud van het Product, in gram.
     */
    private int inhoud;

    /**
     * De prijs van het Product.
     */
    private double prijs;

    /**
     * Geeft aan of het Product in de aanbieding is.
     */
    private boolean isAanbieding;

    /**
     * De datum waarop de prijs voor het laatst is gewijzigd, in de vorm "YYYY-MM-DD".
     */
    private String laatstePrijsWijziging;

    /**
     * Het ID van de Winkel waar dit Product bij hoort.
     */
    private int hoortBijWinkelId;

    /**
     * Maakt een nieuw ProductForSerialization met de opgegeven gegevens.
     *
     * @param naam                    De naam van het Product.
     * @param variant                 De variant van het Product.
     * @param merk                    Het merk van het Product.
     * @param inhoud                  De inhoud van het Product, in gram.
     * @param prijs                   De prijs van het Product.
     * @param isAanbieding            Geeft aan of het Product in de aanbieding is.
     * @param laatstePrijsWijziging   De datum waarop de prijs voor het laatst is gewijzigd, in de vorm "YYYY-MM-DD".
     * @param hoortBijWinkelId        Het ID van de Winkel waar dit Product bij hoort.
     */
    public ProductForSerialization(String naam, String variant, String merk, int inhoud, double prijs, boolean isAanbieding, String laatstePrijsWijziging, int hoortBijWinkelId) {
        this.naam = naam;
        this.variant = variant;
        this.merk = merk;
        this.inhoud = inhoud;
        this.prijs = prijs;
        this.isAanbieding = isAanbieding;
        this.laatstePrijsWijziging = laatstePrijsWijziging;
        this.hoortBijWinkelId = hoortBijWinkelId;
    }

    /**
     * Geeft de naam van het Product terug.
     *
     * @return De naam van het Product.
     */
    public String getNaam() {
        return naam;
    }

    /**
     * Geeft de variant van het Product terug.
     *
     * @return De variant van het Product.
     */
    public String getVariant() {
        return variant;
    }

    /**
     * Geeft het merk van het Product terug.
     *
     * @return Het merk van het Product.
     */
    public String getMerk() {
        return merk;
    }

    /**
     * Geeft de inhoud van het Product terug, in gram.
     *
     * @return De inhoud van het Product.
     */
    public int getInhoud() {
        return inhoud;
    }

    /**
     * Geeft de prijs van het Product terug.
     *
     * @return De prijs van het Product.
     */
    public double getPrijs() {
        return prijs;
    }

    /**
     * Geeft aan of het Product in de aanbieding is.
     *
     * @return True als het Product in de aanbieding is, anders false.
     */

    public boolean isIsAanbieding() {
        return isAanbieding;
    }

    /**
     * Geeft de datum waarop de prijs voor het laatst is gewijzigd, in de vorm "YYYY-MM-DD".
     *
     * @return De datum waarop de prijs voor het laatst is gewijzigd, in de vorm "YYYY-MM-DD".
     */

    public String getLaatstePrijsWijziging() {
        return laatstePrijsWijziging;
    }


    public int getHoortBijWinkelId() {
        return hoortBijWinkelId;
    }

}
