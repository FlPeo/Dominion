package dominion.Actions;

import dominion.Cards;
import dominion.Partie;

/**
 * Created by sguyot4 on 07/12/16.
 */
public class ActionAddArgentToDeck extends Action {

    public ActionAddArgentToDeck(Partie partie, int valeur) {
        super(partie, valeur);
    }

    public void action() {
        Cards argent = partie.getListeCartesTresor().get(1).get(0);
        partie.getListeCartesTresor().get(1).remove(0);
        partie.getJoueurCourrant().addCoin(2);
        partie.getJoueurCourrant().getDeck().add(argent);
    }


}
