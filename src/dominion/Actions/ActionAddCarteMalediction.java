package dominion.Actions;

import dominion.Partie;

/**
 * Created by ndolo on 07/12/16.
 */
public class ActionAddCarteMalediction extends Action {
    public ActionAddCarteMalediction(Partie partie, int valeur) {
        super(partie, valeur);
    }

    public void action(){
        partie.getJoueurAdverse().piocherMalediction();
    }
}
