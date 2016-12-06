package dominion.Actions;

import dominion.ActionCards;
import dominion.Partie;

import java.util.ArrayList;

/**
 * Created by Flo on 01/12/2016.
 */
public class ActionEcarterCetteCarte extends Action{
    public ActionCards carteAction;

    public ActionEcarterCetteCarte(Partie partie, int valeur){
        super(partie, valeur);
    }

    public void action(){
        ArrayList<ActionCards> listeUnTypeCarteAction;
        boolean defausse = false;
        int id;
        int indexPileVide = -1;

        for(int i = 0 ; i<10 && !defausse ; i++){
            id = partie.getIdPileCarteAction(i);
            if(id == carteAction.getId()){
                partie.ecarteCarteActionSurPile(carteAction, i);
                defausse = true;
            }
            else if(id == -1){
                indexPileVide = i;
            }
        }

        //ici, on a le cas où la pile de la carte est vide est vide
        if(!defausse) partie.ecarteCarteActionSurPile(carteAction, indexPileVide);
    }

    public void setCarteAction(ActionCards carteAction){
        this.carteAction = carteAction;
        carteAction.setCarteADefausserApresAction();
    }
}
