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
    public void testAdversaireAddXCartes()
    {
        Joueur[] joueurs = new Joueur[2];
        Partie p = creerPartie();


        ArrayList<Action> listeActionsJ1 = new ArrayList<>();
        ActionAddCarteMalediction act = new ActionAddCarteMalediction(p,5);
        listeActionsJ1.add(act);
        ActionCards carte2 = new ActionCards(5, listeActionsJ1, 5);
        ArrayList<Cards> listeCartesJ1 = new ArrayList<>();
        listeCartesJ1.add(carte2);
        Joueur j1 = new Joueur("J1", listeCartesJ1, 1);


        Joueur j2 = new Joueur("J2", new ArrayList<>(), 2);

        joueurs[0] = j1;
        joueurs[1] = j2;

        p.setJoueurs(joueurs);
        p.setJoueurCourrant(j2);

        Action action = new ActionAdversairesAddXCartes(p,1);
        action.action();

        Assert.assertEquals(carte2, j1.getCarteMain(0));

    }


    @Test
    public void testAddCarteMalediction(){
        Joueur[] joueurs = new Joueur[1];
        Partie p = creerPartie();
        joueurs[0] = new Joueur("joueur", creerCartesPourTest(p), 0);
        p.setJoueurs(joueurs);

        VictoireCards carte = p.getListeCartesVictoire().get(3).get(0);
        p.getListeCartesVictoire().get(3).remove(0);
        joueurs[0].piocherMalediction(carte);

        Assert.assertEquals(joueurs[0].getCarteMain(0).getId(),3);
    }

}
