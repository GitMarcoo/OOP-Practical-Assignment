package practicumopdracht.Controllers;

import practicumopdracht.utils.MenuHelper;
import practicumopdracht.views.View;

/**
 * Abstracte controller klasse voor alle controllers
 *
 * Deze klasse dient als basis voor alle controllers. Hierbij hoort een view
 * en een instantie van de {@link MenuHelper} om de menu opties van de
 * verschillende views te kunnen tonen.
 *
 * @author Marco de Boer
 */
public abstract class Controller {
    /**
     * Geeft de view van de controller terug
     *
     * @return De view van de controller
     */
    public abstract View getView();

    /**
     * Instantie van {@link MenuHelper} die door alle controllers gebruikt kan worden
     */
    protected static MenuHelper menuHelper = new MenuHelper();
}
