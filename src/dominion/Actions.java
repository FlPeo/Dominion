package dominion;

/**
 * Created by sguyot4 on 19/11/16.
 */
public class Actions extends Cards {

    /** NOTE :  j'ai rien compris à ce qu'il fallait faire ni comment le faire
     *  du coup j'ai fait ça sans grande conviction
     *  pleins de trucs seront a changer mais je pense que la base est là
     *  aucuns tests fait
     **//*
    private int nbPioche;
    private int addAction;
    private int addAchat;
    private int addRessource;
    private int nbDefausse;

    public Actions(int nbPioche, int addAction, int addAchat, int addRessource, int nbDefausse){
        this.nbPioche = nbPioche;
        this.nbDefausse = nbDefausse;
        this.addAction = addAction;
        this.addAchat = addAchat;
        this.addRessource = addRessource;
    }

    public void piocheCarte(int nbPioche) {
        for (int i = 0; i < nbPioche; i++){
            main.addCarte();
        }
    }

    public void defausseCarte(int nbDefausse){
        for (int i = 0; i < nbDefausse; i++){
            main.defausseCarte();
        }
    }

    public void addCoin(int nbCoin){
        joueur.nbCoin += nbCoin;
    }

    public void addAction(int nbAction){
        joueur.nbAction += nbAction;
    }*/

}
