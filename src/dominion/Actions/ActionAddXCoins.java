package dominion.Actions;

import dominion.Partie;

/**
 * Created by Flo on 30/11/2016.
 */
public class ActionAddXCoins extends Action {
    public ActionAddXCoins(Partie partie, int valeur){
        super(partie, valeur);
    }

    public void action(){
        partie.getJoueurCourrant().addCoin(valeur);
    }
}
