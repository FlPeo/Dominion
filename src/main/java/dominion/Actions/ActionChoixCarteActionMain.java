package dominion.Actions;

import dominion.EtapesTour;
import dominion.Partie;

/**
 * Created by Flo on 13/12/2016.
 */
public class ActionChoixCarteActionMain extends ActionBloquante{
    public ActionChoixCarteActionMain(Partie partie, int valeur) {
        super(partie, valeur);
    }

    public void action(){
        partie.setEtapesTour(EtapesTour.CHOIX_1_CARTE_ACTION_DE_MAIN);
    }
}