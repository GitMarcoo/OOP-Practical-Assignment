/**
 * Comparator-klasse voor het vergelijken van de prijzen van producten.
 * De klasse implementeert de Comparator-interface van het Product-model.
 * De prijzen worden oplopend of aflopend gesorteerd op basis van de boolean isOplopend.
 *
 * @author Marco de Boer
 */

package practicumopdracht.comparators;

import practicumopdracht.models.Product;

import java.util.Comparator;

public class PrijsComparator implements Comparator<Product> {
    private boolean isOplopend;


    /**
     * Constructor voor PrijsComparator-klasse.
     * @param isOplopend Een boolean-waarde die aangeeft of de prijzen oplopend of aflopend moeten worden gesorteerd.
     */
    public PrijsComparator(boolean isOplopend) {
        this.isOplopend = isOplopend;
    }

    /**
     * Vergelijkt twee producten op basis van hun prijs.
     * @param product1 Het eerste product dat vergeleken moet worden.
     * @param product2 Het tweede product dat vergeleken moet worden.
     * @return Een integer die aangeeft of het eerste product kleiner, gelijk aan of groter dan het tweede product is.
     */
    public int compare(Product product1, Product product2) {
        Double prijs1 = product1.getPrijs();
        Double prijs2 = product2.getPrijs();
        if (isOplopend){
            return prijs1.compareTo(prijs2);
        }else{
            return prijs2.compareTo(prijs1);
        }
    }
}
