package dominion;

import dominion.Actions.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static dominion.CreationObjetsPourTests.creerCartesPourTest;
import static dominion.CreationObjetsPourTests.creerPartie;

/**
 * Created by Flo on 30/11/2016.
 */
public class ActionUnitTest {



    @Test
    public void testPlusXCoins(){
        Joueur[] joueurs = new Joueur[1];
        Partie p = creerPartie();
        joueurs[0] = new Joueur("joueur", creerCartesPourTest(p), 0);
        p.setJoueurs(joueurs);

        Action a = new ActionAddXCoins(p, 2);
        a.action();
        Assert.assertEquals(2, joueurs[0].getCoins());

        a = new ActionAddXCoins(p, 3);
        a.action();
        Assert.assertEquals(5, joueurs[0].getCoins());
    }


    @Test
    public void testPlusXActions(){
        Joueur[] joueurs = new Joueur[1];
        Partie p = creerPartie();
        joueurs[0] = new Joueur("joueur", creerCartesPourTest(p), 0);
        p.setJoueurs(joueurs);

        Action a = new ActionAddXActions(p, 1);
        a.action();
        Assert.assertEquals(2, joueurs[0].getNbTourAction());  //Il y a un tour d'action mis de base dans constructeur

        a = new ActionAddXActions(p, 2);
        a.action();
        Assert.assertEquals(4, joueurs[0].getNbTourAction());
    }


    @Test
    public void testPlusXAchats(){
        Joueur[] joueurs = new Joueur[1];
        Partie p = creerPartie();
        joueurs[0] = new Joueur("joueur", creerCartesPourTest(p), 0);
        p.setJoueurs(joueurs);


        Action a = new ActionAddXAchats(p, 6);
        a.action();
        Assert.assertEquals(7, joueurs[0].getNbTourAchat());  //Il y a un tour d'achat mis de base dans constructeur

        a = new ActionAddXAchats(p, 5);
        a.action();
        Assert.assertEquals(12, joueurs[0].getNbTourAchat());
    }

    @Test
    public void testPlusXCartes(){
        Joueur[] joueurs = new Joueur[1];
        Partie p = creerPartie();
        joueurs[0] = new Joueur("joueur", creerCartesPourTest(p), 0);
        p.setJoueurs(joueurs);


        Action a = new ActionAddXCartes(p, 1);
        a.action();
        Assert.assertEquals(1, joueurs[0].getSizeMain());
        Assert.assertEquals(7, joueurs[0].getSizeDeck());   //de base, 8 cartes dans deck

        a.action();
        Assert.assertEquals(2, joueurs[0].getSizeMain());
        Assert.assertEquals(6, joueurs[0].getSizeDeck());

        a = new ActionAddXCartes(p, 2);
        a.action();
        Assert.assertEquals(4, joueurs[0].getSizeMain());
        Assert.assertEquals(4, joueurs[0].getSizeDeck());
    }

    @Test
    public void testEcarterCetteCarteSiPileNonVide(){
        Joueur[] joueurs = new Joueur[1];
        Partie p = creerPartie();


        ArrayList<Action> listeActions = new ArrayList<>();
        ActionEcarterCetteCarte act = new ActionEcarterCetteCarte(p,5);
        listeActions.add(act);
        ActionCards carte = new ActionCards(2, listeActions, 5);


        ArrayList<ActionCards>  listeCarteAction= new ArrayList<>();
        listeCarteAction.add(carte);
        act.setCarteAction(listeCarteAction.get(0));

        ArrayList<Cards>  listeCarteAction2= new ArrayList<>();
        listeCarteAction2.add(carte);
        joueurs[0] = new Joueur("joueur", listeCarteAction2, 0);
        p.setJoueurs(joueurs);

        ArrayList<ArrayList<ActionCards>> listeDeListeCartesAction = new ArrayList<>();
        listeDeListeCartesAction.add(listeCarteAction);
        p.setCartesAction(listeDeListeCartesAction);

        joueurs[0].piocher(1);
        Assert.assertEquals(1, p.getJoueurCourrant().getSizeMain());
        Assert.assertEquals(1, p.getNbRestantCartesAction(0));
        Assert.assertEquals(0, p.getJoueurCourrant().getSizeDefausse());


        act.action();
        Assert.assertEquals(0, p.getJoueurCourrant().getSizeMain());
        Assert.assertEquals(2, p.getNbRestantCartesAction(0));
        Assert.assertEquals(0, p.getJoueurCourrant().getSizeDefausse());


    }


    @Test
    public void testEcarterCetteCarteSiPileVide(){
        Joueur[] joueurs = new Joueur[1];
        Partie p = creerPartie();



        ArrayList<Action> listeActions = new ArrayList<>();
        ActionEcarterCetteCarte act = new ActionEcarterCetteCarte(p,5);
        listeActions.add(act);
        ActionCards carte = new ActionCards(2, listeActions, 5);
        ActionCards carte2 = new ActionCards(5, listeActions, 5);


        ArrayList<ActionCards>  listeCartesAction= new ArrayList<>();
        listeCartesAction.add(carte2);


        ArrayList<Cards>  listeCartesAction2= new ArrayList<>();
        listeCartesAction2.add(carte);
        act.setCarteAction(carte);
        joueurs[0] = new Joueur("joueur", listeCartesAction2, 0);
        p.setJoueurs(joueurs);

        ArrayList<ArrayList<ActionCards>> listeDeListeCartesAction = new ArrayList<>();
        listeDeListeCartesAction.add(listeCartesAction);
        for(int i =0 ; i<9 ; i++){
            listeDeListeCartesAction.add(new ArrayList<>());
        }
        p.setCartesAction(listeDeListeCartesAction);

        joueurs[0].piocher(1);
        Assert.assertEquals(1, p.getJoueurCourrant().getSizeMain());
        Assert.assertEquals(1, p.getNbRestantCartesAction(0));
        Assert.assertEquals(0, p.getNbRestantCartesAction(1));
        Assert.assertEquals(0, p.getJoueurCourrant().getSizeDefausse());


        act.action();
        Assert.assertEquals(0, p.getJoueurCourrant().getSizeMain());
        Assert.assertEquals(1, p.getNbRestantCartesAction(0));
        Assert.assertEquals(1, p.getNbRestantCartesAction(9));
        Assert.assertEquals(0, p.getJoueurCourrant().getSizeDefausse());


    }

}
