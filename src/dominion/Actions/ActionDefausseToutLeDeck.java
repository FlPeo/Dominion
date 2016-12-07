package dominion.Actions;

import dominion.Partie;

/**
 * Created by ndolo on 07/12/16.
 */
public class ActionDefausseToutLeDeck extends Action {
    public ActionDefausseToutLeDeck(Partie partie, int valeur) {
        super(partie, valeur);
    }

    public void action(){
        partie.getJoueurCourrant().deckToDefausse();
    }
}