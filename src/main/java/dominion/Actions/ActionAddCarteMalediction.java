package dominion.Actions;

import dominion.Partie;
import dominion.VictoireCards;

/**
 * Created by ndolo on 07/12/16.
 */
public class ActionAddCarteMalediction extends Action {
    public ActionAddCarteMalediction(Partie partie, int valeur) {
        super(partie, valeur);
    }

    public void action(){
        try{
            VictoireCards carte = partie.getListeCartesVictoire().get(3).get(0);
            partie.getListeCartesVictoire().get(3).remove(0);
            partie.getJoueurAdverse().piocherMalediction(carte);
        }
        catch(Exception e){

        }
    }
}
