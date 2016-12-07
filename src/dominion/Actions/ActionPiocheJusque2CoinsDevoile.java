package dominion.Actions;

import dominion.Joueur;
import dominion.Partie;

/**
 * Created by sakalypse on 07/12/16.
 */
public class ActionPiocheJusque2CoinsDevoile extends Action {
    public ActionPiocheJusque2CoinsDevoile(Partie partie, int valeur){
        super(partie, valeur);
    }

    public void action(){
        int nombreCoinsFound = 0;
        while(nombreCoinsFound != 2) {
            Joueur joueur = partie.getJoueurCourrant();
            joueur.piocher(1);
            if (joueur.getCarteMain(joueur.getSizeMain()-1).getId() == 0
                    || joueur.getCarteMain(joueur.getSizeMain()-1).getId() == 1
                    || joueur.getCarteMain(joueur.getSizeMain()-1).getId() == 2){ //id 0,1 et 2 = ids des cartes coins
                nombreCoinsFound++;
            }
            else{
                joueur.getMain().remove(joueur.getSizeMain()-1);
            }
        }
    }
}