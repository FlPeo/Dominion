package dominion;

import dominion.Actions.Action;
import dominion.Actions.ActionAddXAchats;
import dominion.Actions.ActionAddXActions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Flo on 01/12/2016.
 */
public class CreationObjetsPourTests {

    public static Partie creerPartie(Joueur[] joueurs){
        Partie p = new Partie(EtapesTour.ACTION, creerCartesVictoire(),creerCartesTresor());
        p.setCartesAction(creerCartesAction());
        p.setJoueurs(joueurs);
        return p;
    }

    public static ArrayList<ArrayList<VictoireCards>> creerCartesVictoire(){
        ArrayList<ArrayList<VictoireCards>> listeCartes = new ArrayList<ArrayList<VictoireCards>>();
        ArrayList<VictoireCards> listeUneSorteCarte;

        for(int i = 0 ; i<4 ; i++){
            listeUneSorteCarte = new ArrayList<VictoireCards>();
            for(int j = 0 ; j<3+i ; j++){
                listeUneSorteCarte.add(new VictoireCards(i));
            }
            listeCartes.add(listeUneSorteCarte);
        }

        return listeCartes;
    }

    public static ArrayList<ArrayList<CoinsCards>> creerCartesTresor(){
        ArrayList<ArrayList<CoinsCards>> listeCartes = new ArrayList<ArrayList<CoinsCards>>();
        ArrayList<CoinsCards> listeUneSorteCarte;

        for(int i = 0 ; i<3 ; i++){
            listeUneSorteCarte = new ArrayList<CoinsCards>();
            for(int j = 0 ; j<3+i ; j++){
                listeUneSorteCarte.add(new CoinsCards(i));
            }
            listeCartes.add(listeUneSorteCarte);
        }

        return listeCartes;
    }

    public static ArrayList<ArrayList<ActionCards>> creerCartesAction(){
        ArrayList<ArrayList<ActionCards>> listeCartes = new ArrayList<ArrayList<ActionCards>>();
        ArrayList<ActionCards> listeUneSorteCarte;

        for(int i = 0 ; i<3 ; i++){
            listeUneSorteCarte = new ArrayList<ActionCards>();
            for(int j = 0 ; j<3+i ; j++){
                listeUneSorteCarte.add(new ActionCards(j, null, 2+i));
            }
            listeCartes.add(listeUneSorteCarte);
        }

        return listeCartes;
    }

    public static List<Cards> creerCartesPourTest(Partie p){
        List<Action> listeActions = new ArrayList<Action>();
        listeActions.add(new ActionAddXAchats(p, 5));
        listeActions.add(new ActionAddXActions(p, 6));

        List<Cards> liste = new ArrayList<Cards>();
        liste.add(new ActionCards(5, listeActions, 4));
        liste.add(new CoinsCards(2));
        liste.add(new VictoireCards(2));
        liste.add(new VictoireCards(3));
        liste.add(new CoinsCards(1));
        liste.add(new CoinsCards(3));
        liste.add(new VictoireCards(4));
        liste.add(new VictoireCards(1));
        return liste;
    }
}
