/**
 * Een comparator klasse voor het sorteren van Product objecten op naam en variant.
 * Als twee producten dezelfde naam hebben, worden ze gesorteerd op variant.
 * De volgorde kan oplopend of aflopend zijn.
 *
 * @author Marco de Boer
 */

package practicumopdracht.comparators;

import practicumopdracht.models.Product;

import java.util.Comparator;

public class ProductComparator implements Comparator<Product> {
    private boolean isOplopend;


    /**
     * Constructor voor ProductComparator.
     *
     * @param isOplopend bepaalt of de sortering oplopend of aflopend is
     */
    public ProductComparator(boolean isOplopend) {
        this.isOplopend = isOplopend;
    }

    /**
     * Vergelijkt twee Product objecten op basis van naam en variant.
     *
     * @param product1 het eerste te vergelijken Product object
     * @param product2 het tweede te vergelijken Product object
     * @return een negatief getal, nul, of een positief getal als product1 respectievelijk kleiner dan, gelijk aan, of groter dan product2 is
     */
    public int compare(Product product1, Product product2) {
        if (!product1.getNaam().equals(product2.getNaam())){
            if (isOplopend){
                return product1.getNaam().compareTo(product2.getNaam());
            }else{
                return product2.getNaam().compareTo(product1.getNaam());
            }
        } else {
            if (isOplopend){
                return product1.getVariant().compareTo(product2.getVariant());
            }else{
                return product2.getVariant().compareTo(product1.getVariant());
            }
        }
    }
}
