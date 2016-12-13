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
    private boolean carteADefausserApresAction;
    private static ActionCards carteBoucheTrou = new ActionCards(5, new ArrayList<Action>(), 3);
    //Supprimer la carte bouche trou des que possible ! (quand on aura fait toutes les cartes action


    public ActionCards(int id, List<Action> actions, int cout){   //id = numero de l'img de la carte
        super(id, cout);
        listeActions = actions;
        carteADefausserApresAction = true;   //false que si la carte doit etre ecartée (concerne une seule carte action normalement)
    }

    public void action(){
        for(Action action : listeActions){
            action.action();
        }
    }

    public String getCheminImage(){
        return super.getCheminImage() + "Action/Action" + id + ".jpg";
    }

    public boolean isCarteAction(){
        return true;
    }

    public void setCarteADefausserApresAction() {
        this.carteADefausserApresAction = false;
    }

    public boolean isCarteADefausserApresAction() {
        return this.carteADefausserApresAction;
    }



    public static ArrayList<ArrayList<ActionCards>> creer10CartesAction(Partie p, int nbJoueurs){
        ActionCards[] listeToutesCartesActions = creer25CartesAction(p);
        Collections.shuffle(Arrays.asList(listeToutesCartesActions));
        ArrayList<ArrayList<ActionCards>> liste10CartesAction = selectionner10Cartes(listeToutesCartesActions, nbJoueurs);
        return liste10CartesAction;
    }


    private static ActionCards[] creer25CartesAction(Partie p) {
        //ActionCards[] listeCartesActions = new ActionCards[25];
        ActionCards[] listeCartesActions = new ActionCards[11];
        for(int i = 0; i< listeCartesActions.length ; i++){
            listeCartesActions[i] = carteBoucheTrou;
        }



        List<Action> listeActions = new ArrayList<Action>();

        listeActions = new ArrayList<Action>(); // carte sorcière
        listeActions.add(new ActionAddCarteMalediction(p,1));
        listeCartesActions[0] = new ActionCards(3, listeActions, 5);

        listeActions.add(new ActionAddXCartes(p, 1));    //carte marché
        listeActions.add(new ActionAddXActions(p, 1));
        listeActions.add(new ActionAddXAchats(p, 1));
        listeActions.add(new ActionAddXCoins(p, 1));
        listeCartesActions[1] = new ActionCards(4, listeActions, 5);

        listeActions = new ArrayList<Action>();
        listeActions.add(new ActionAddXCartes(p, 2));    //carte douves
        listeCartesActions[2] = new ActionCards(7, listeActions, 2);

        listeActions = new ArrayList<Action>();
        listeActions.add(new ActionAddXCartes(p, 2));    //carte laboratoire
        listeActions.add(new ActionAddXActions(p, 1));
        listeCartesActions[3] = new ActionCards(9, listeActions, 5);

        listeActions = new ArrayList<Action>();
        listeActions.add(new ActionAddXCartes(p, 1));    //carte village
        listeActions.add(new ActionAddXActions(p, 2));
        listeCartesActions[4] = new ActionCards(10, listeActions, 3);

        listeActions = new ArrayList<Action>();
        listeActions.add(new ActionAddXAchats(p, 1));  //carte bucheron
        listeActions.add(new ActionAddXCoins(p, 2));
        listeCartesActions[5] = new ActionCards(11, listeActions, 3);


        listeActions = new ArrayList<Action>();    //carte jardins
        listeCartesActions[6] = new ActionCards(12, listeActions, 4);


        listeActions = new ArrayList<Action>();    //carte chambre du conseil
        listeActions.add(new ActionAddXCartes(p, 4));
        listeActions.add(new ActionAddXAchats(p, 1));
        listeActions.add(new ActionAdversairesAddXCartes(p, 1));
        listeCartesActions[7] = new ActionCards(14, listeActions, 5);



        listeActions = new ArrayList<Action>();
        listeActions.add(new ActionAddXCartes(p, 3));  //carte forgeron
        listeCartesActions[8] = new ActionCards(15, listeActions, 4);


        //21 : jouer carte action deux fois
        //A tester !!
        listeActions = new ArrayList<Action>();
        listeActions.add(new ActionChoixCarteActionMain(p, 1));  //salle du trone
        listeActions.add(new ActionJouerDeuxFoisCarteChoisie(p, 1));
        listeCartesActions[9] = new ActionCards(21, listeActions, 4);


        listeActions = new ArrayList<Action>();
        listeActions.add(new ActionAddXActions(p, 2));  //carte festival
        listeActions.add(new ActionAddXAchats(p, 1));
        listeActions.add(new ActionAddXCoins(p, 2));
        listeCartesActions[10] = new ActionCards(23, listeActions, 5);

        // Adonis

        // ------


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


    public void rempliActionsTour(Partie modelPartie) {
        for(Action a : listeActions){
            modelPartie.addAction(a);
        }
    }

    public void rempliActionsTour(Partie modelPartie, int index) {
        for(Action a : listeActions){
            modelPartie.addAction(a, index);
            index++;
        }
    }
}
