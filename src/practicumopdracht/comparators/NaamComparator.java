/**
 * De NaamComparator klasse is een comparator voor het vergelijken van Winkel objecten op basis van hun naam.
 * Deze comparator kan worden gebruikt om Winkel objecten te sorteren op alfabetische volgorde van hun naam.
 * De comparator kan zowel in oplopende als aflopende volgorde sorteren.
 *
 * @author Marco de Boer
 */

package practicumopdracht.comparators;

import practicumopdracht.models.Winkel;

import java.util.Comparator;

public class NaamComparator  implements Comparator<Winkel> {
    private boolean isOplopend;

    /**
     * Maakt een nieuwe instantie van de NaamComparator klasse aan met de opgegeven sorteervolgorde.
     * @param isOplopend Geeft aan of de comparator in oplopende of aflopende volgorde moet sorteren.
     */

    public NaamComparator(boolean isOplopend) {
        this.isOplopend = isOplopend;
    }

    /**
     * Vergelijkt twee Winkel objecten op basis van hun naam.
     * @param winkel1 Het eerste Winkel object om te vergelijken.
     * @param winkel2 Het tweede Winkel object om te vergelijken.
     * @return Een negatief getal als winkel1 voor winkel2 komt, een positief getal als winkel1 na winkel2 komt,
     * en 0 als de winkels gelijk zijn.
     */
    @Override
    public int compare(Winkel winkel1, Winkel winkel2) {
        if (isOplopend){
            return winkel1.getNaam().compareTo(winkel2.getNaam());
        }else{
            return winkel2.getNaam().compareTo(winkel1.getNaam());
        }
    }
}

