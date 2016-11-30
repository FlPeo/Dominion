package dominion.Actions;

import dominion.Partie;

/**
 * Created by Flo on 30/11/2016.
 */
public abstract class Action {
    protected int valeur;
    protected Partie partie;

    public Action(Partie partie, int valeur){
        this.partie = partie;
        this.valeur = valeur;
    }

    public abstract void action();
}
