package dominion.Actions;

import dominion.Partie;

/**
 * Created by Flo on 30/11/2016.
 */
public class ActionAddXCartes extends Action {
    public ActionAddXCartes(Partie partie, int valeur) {
        super(partie, valeur);
    }

    public void action(){
        partie.getJoueurCourrant().piocher(valeur);
    }
}
