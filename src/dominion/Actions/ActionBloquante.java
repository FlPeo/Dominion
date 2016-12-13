package dominion.Actions;

import dominion.Partie;

/**
 * Created by Flo on 13/12/2016.
 */
public abstract class ActionBloquante extends Action{

    public ActionBloquante(Partie partie, int valeur){
        super(partie, valeur);
    }

    public boolean estBloquante(){
        return true;
    }
}
