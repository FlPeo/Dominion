package dominion;

import dominion.Actions.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Flo on 30/11/2016.
 */
public class ActionUnitTest {

    public List<Cards> creerCartesPourTest(Partie p){
        List<Action> listeActions = new ArrayList<Action>();
        listeActions.add(new ActionAddXAchats(p, 5));
        listeActions.add(new ActionAddXActions(p, 6));

        List<Cards> liste = new ArrayList<Cards>();
        liste.add(new ActionCards(listeActions, 4));
        liste.add(new CoinsCards(2));
        return liste;
    }

    @Test
    public void testPlusXCoins(){
        Joueur[] joueurs = new Joueur[1];
        Partie p = new Partie();
        joueurs[0] = new Joueur("joueur", creerCartesPourTest(p));
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
        Partie p = new Partie();
        joueurs[0] = new Joueur("joueur", creerCartesPourTest(p));
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
        Partie p = new Partie();
        joueurs[0] = new Joueur("joueur", creerCartesPourTest(p));
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
        Partie p = new Partie();
        joueurs[0] = new Joueur("joueur", creerCartesPourTest(p));
        p.setJoueurs(joueurs);


        Action a = new ActionAddXCartes(p, 1);
        a.action();
        Assert.assertEquals(1, joueurs[0].getSizeMain());
        Assert.assertEquals(1, joueurs[0].getSizeDeck());

        a.action();
        Assert.assertEquals(2, joueurs[0].getSizeMain());
        Assert.assertEquals(0, joueurs[0].getSizeDeck());
    }

    //Il faudra tester la reconstitution du deck à partir de la defausse

}
