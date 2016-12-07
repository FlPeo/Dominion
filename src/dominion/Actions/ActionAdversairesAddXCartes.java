package dominion.Actions;

import dominion.Joueur;
import dominion.Partie;

/**
 * Created by sakalypse on 07/12/16.
 */
public class ActionAdversairesAddXCartes extends Action {
    public ActionAdversairesAddXCartes(Partie partie, int valeur){
        super(partie, valeur);
    }

    public void action(){
        Joueur[] joueurs = partie.getJoueurs();
        for (int i = 0; i<joueurs.length; i++){
            joueurs[i].piocher(valeur);
        }
    }
}
