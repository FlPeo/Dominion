package dominion.Actions;

import dominion.Actions.Action;
import dominion.Partie;

/**
 * Created by Flo on 30/11/2016.
 */
public class ActionAddXAchats extends Action {
    public ActionAddXAchats(Partie partie, int valeur) {
        super(partie, valeur);
    }

    public void action(){
        partie.getJoueurCourrant().addTourAchat(valeur);
    }
}
