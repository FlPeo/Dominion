package dominion;

import dominion.Actions.*;
import javafx.print.Collation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by sguyot4 on 19/11/16.
 */
public class ActionCards extends Cards {

    /** NOTE :  j'ai rien compris à ce qu'il fallait faire ni comment le faire
     *  du coup j'ai fait ça sans grande conviction
     *  pleins de trucs seront a changer mais je pense que la base est là
     *  aucuns tests fait
     **/

    private List<Action> listeActions;

    public ActionCards(int id, List<Action> actions, int cout){   //id = numero de l'img de la carte
        super(id, cout);
        listeActions = actions;
    }

    public void action(){
        for(Action action : listeActions){
            action.action();
        }
    }



    public static ArrayList<ArrayList<ActionCards>> creer10CartesAction(Partie p, int nbJoueurs){
        ActionCards[] listeToutesCartesActions = creer25CartesAction(p);
        Collections.shuffle(Arrays.asList(listeToutesCartesActions));
        ArrayList<ArrayList<ActionCards>> liste10CartesAction = selectionner10Cartes(listeToutesCartesActions, nbJoueurs);
        return liste10CartesAction;
    }


    private static ActionCards[] creer25CartesAction(Partie p) {
        ActionCards[] listeCartesActions = new ActionCards[25];
        List<Action> listeActions = new ArrayList<Action>();

        listeActions.add(new ActionAddXCartes(p, 1));    //carte marché
        listeActions.add(new ActionAddXActions(p, 1));
        listeActions.add(new ActionAddXAchats(p, 1));
        listeActions.add(new ActionAddXCoins(p, 1));
        listeCartesActions[0] = new ActionCards(4, listeActions, 5);

        listeActions = new ArrayList<Action>();
        listeActions.add(new ActionAddXCartes(p, 2));    //carte laboratoire
        listeActions.add(new ActionAddXActions(p, 1));
        listeCartesActions[1] = new ActionCards(9, listeActions, 5);

        listeActions = new ArrayList<Action>();
        listeActions.add(new ActionAddXCartes(p, 1));    //carte village
        listeActions.add(new ActionAddXActions(p, 2));
        listeCartesActions[2] = new ActionCards(10, listeActions, 3);

        listeActions = new ArrayList<Action>();
        listeActions.add(new ActionAddXAchats(p, 1));  //carte bucheron
        listeActions.add(new ActionAddXCoins(p, 2));
        listeCartesActions[3] = new ActionCards(11, listeActions, 3);

        //14 et 20 = faisable facilement

        listeActions = new ArrayList<Action>();
        listeActions.add(new ActionAddXCartes(p, 3));  //carte forgeron
        listeCartesActions[4] = new ActionCards(15, listeActions, 4);

        listeActions = new ArrayList<Action>();
        listeActions.add(new ActionAddXActions(p, 2));  //carte festival
        listeActions.add(new ActionAddXAchats(p, 1));
        listeActions.add(new ActionAddXCoins(p, 2));
        listeCartesActions[5] = new ActionCards(23, listeActions, 5);


        return listeCartesActions;
    }

    private static ArrayList<ArrayList<ActionCards>> selectionner10Cartes(ActionCards[] listeToutesCartesActions, int nbJoueurs) {
        //TO DO
        ArrayList<ArrayList<ActionCards>> listeCartes = new ArrayList<ArrayList<ActionCards>>();
        ArrayList<ActionCards> cards;
        int nbCartesJardins = (nbJoueurs == 2)?8:12;

        for(int i = 0 ; i<10; i++){
            cards = new ArrayList<>();
            for(int j = 0 ; j<((listeToutesCartesActions[i].getId() == 12)?nbCartesJardins:10) ; j++){
                cards.add(listeToutesCartesActions[i]);
            }
            listeCartes.add(cards);
        }
        return listeCartes;
    }

    /*public static int[] creerReservesCartesAction(ActionCards[] cartesAction, int nbJoueurs) {
        int[] listeReservesCartesAction = new int[cartesAction.length];
        int nbCartesJardins = (nbJoueurs == 2)?8:12;

        for(int i = 0 ; i<cartesAction.length ; i++){
            listeReservesCartesAction[i] = (cartesAction[i].getId() == 12)?nbCartesJardins:10;
            //id 12 = carte Jardins (n'est pas presente avec le meme nombre que les autres)
        }

        return listeReservesCartesAction;
    }*/



    /*
    private int nbPioche;
    private int addAction;
    private int addAchat;
    private int addRessource;
    private int nbDefausse;

    public ActionCards(int nbPioche, int addAction, int addAchat, int addRessource, int nbDefausse){
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
