package dominion.Actions;

import dominion.Partie;

/**
 * Created by Flo on 01/12/2016.
 */
public class ActionEcarterCetteCarte extends Action{


    public ActionEcarterCetteCarte(Partie partie, int valeur){
        super(partie, valeur);
    }

    public void action(){
        partie.getJoueurCourrant().addCoin(valeur);
    }
}
