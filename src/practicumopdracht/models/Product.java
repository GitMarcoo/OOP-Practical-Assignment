package practicumopdracht.models;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Model class voor een product.
 *
 * @author Marco de Boer
 */

public class Product implements Serializable {
    //naam,variant,merk,inhoud,prijs,isAanbieding,laatstePrijsWijziging,hoortBijWinkel

    /**
     * De winkel waar dit product bij hoort.
     */
    private  Winkel hoortBijWinkel;

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
     * De inhoud van het Product, in kilogram.
     */

    private int inhoud;



    private static final String EURO = "€";

    /**
     * De prijs van het Product.
     */

    private double prijs;

    /**
     * Geeft aan of het Product in de aanbieding is.
     */

    private boolean inAanbieding;

    /**
     * De datum waarop de prijs voor het laatst is gewijzigd.
     */

    private LocalDate laatstePrijsWijziging;






    /**
     * Maakt een nieuw Product met de opgegeven gegevens.
     *
     * @param naam                    De naam van het Product.
     * @param variant                 De variant van het Product.
     * @param merk                    Het merk van het Product.
     * @param inhoud                  De inhoud van het Product, in kilogram.
     * @param prijs                   De prijs van het Product.
     * @param isAanbieding            Geeft aan of het Product in de aanbieding is.
     * @param laatstePrijsWijziging   De datum waarop de prijs voor het laatst is gewijzigd.
     * @param winkel                  De winkel waar dit product bij hoort.
     */

    public Product(String naam, String variant, String merk, int inhoud, double prijs,
                   boolean isAanbieding, LocalDate laatstePrijsWijziging, Winkel winkel){
        this.naam = naam;
        this.variant = variant;
        this.merk = merk;
        this.inhoud = inhoud;
        this.prijs = prijs;
        this.inAanbieding = isAanbieding;
        this.laatstePrijsWijziging = laatstePrijsWijziging;
        this.hoortBijWinkel = winkel;
    }

    /**
     * Zet de waardes van het product op de waardes van de opgegeven parameters.
     * @param naam nieuwe naam
     * @param variant nieuwe variant
     * @param merk nieuwe merk
     * @param inhoud nieuwe inhoud
     * @param prijs nieuwe prijs
     * @param isAanbieding nieuwe isAanbieding
     * @param laatstePrijsWijziging nieuwe laatstePrijsWijziging
     */
    public void setProductNieuweWaard(String naam, String variant, String merk, int inhoud, double prijs, boolean isAanbieding, LocalDate laatstePrijsWijziging){
        this.naam = naam;
        this.variant = variant;
        this.merk = merk;
        this.inhoud = inhoud;
        this.prijs = prijs;
        this.inAanbieding = inAanbieding;
        this.laatstePrijsWijziging = laatstePrijsWijziging;
    }

    /**
     * Krijg het merk van het product
     * @return merk
     */
    public String getMerk() {
        return merk;
    }

    /**
     * Krijg de inhoud van het product
     * @return inhoud
     */

    public int getInhoud() {
        return inhoud;
    }

    /**
     * Krijg de prijs van het product
     * @return prijs
     */

    public double getPrijs() {
        return prijs;
    }

    /**
     * Krijg de boolean van het product
     * @return inAanbieding
     */

    public boolean isInAanbieding() {
        return inAanbieding;
    }

    /**
     * Krijg de laatste prijs wijziging van het product
     * @return laatstePrijsWijziging
     */

    public LocalDate getLaatstePrijsWijziging() {
        return laatstePrijsWijziging;
    }

    /**
     * Krijg de naam van het product
     * @return naam
     */

    public String getNaam() {
        return naam;
    }

    /**
     * Krijg de variant van het product
     * @return variant
     */

    public String getVariant() {
        return variant;
    }

    /**
     * Krijg de winkel van het product
     * @return hoortBijWinkel
     */

    public Winkel getHoortBijWinkel(){
        return hoortBijWinkel;
    }

    /**
     * Geeft een string representatie van het product.
     * @return String representatie van het product.
     */


    @Override
    public String toString() {
    if(hoortBijWinkel != null){
        if (inAanbieding){
            return String.format("%-30s| %-30s | %-30s | %-25d gram |" +
                    " %1s %-26.2f | %-30s | %-40s  | %-50s",naam, variant, merk, inhoud, EURO,prijs,"Ja", laatstePrijsWijziging.toString(),hoortBijWinkel);

        }else{
            return String.format("%-30s| %-30s | %-30s | %-25d gram |" +
                    " %1s %-26.2f | %-30s | %-40s  | %-50s",naam, variant, merk, inhoud, EURO,prijs,"Nee", laatstePrijsWijziging.toString(),hoortBijWinkel);
        }
    }
    return "";
    }
}

