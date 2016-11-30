package dominion.Actions;

import dominion.Actions.Action;
import dominion.Partie;

/**
 * Created by Flo on 30/11/2016.
 */
public class ActionAddXActions extends Action {
    public ActionAddXActions(Partie partie, int valeur) {
        super(partie, valeur);
    }

    public void action(){
        partie.getJoueurCourrant().addTourAction(valeur);
    }
}
