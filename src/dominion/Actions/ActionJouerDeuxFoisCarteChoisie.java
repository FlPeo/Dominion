package dominion.Actions;

import dominion.ActionCards;
import dominion.Partie;

/**
 * Created by Flo on 13/12/2016.
 */
public class ActionJouerDeuxFoisCarteChoisie extends Action{
    public ActionJouerDeuxFoisCarteChoisie(Partie partie, int valeur){
        super(partie, valeur);
    }

    public void action(){
        ActionCards a = (ActionCards)partie.getCarteChoisieParJoueur(0);
        a.rempliActionsTour(partie, 1);
        a.rempliActionsTour(partie, 1);

        if(a.isCarteADefausserApresAction()){
            partie.getJoueurCourrant().carteVaDeMainADefausse(a);
        }
        partie.clearCarteChoisieParJoueur();

        System.out.println("eee");
        System.out.println(a.getCheminImage());
    }
}
