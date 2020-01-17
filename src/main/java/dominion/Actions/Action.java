package dominion.Actions;

import dominion.Partie;

import java.io.Serializable;

/**
 * Created by Flo on 30/11/2016.
 */
public abstract class Action implements Serializable {
    protected int valeur;
    protected Partie partie;

    public Action(Partie partie, int valeur){
        this.partie = partie;
        this.valeur = valeur;
    }

    public abstract void action();

    public boolean estBloquante(){
        return false;
    }
}