package dominion;


import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static dominion.CreationObjetsPourTests.creerCartesPourTest;
import static dominion.CreationObjetsPourTests.creerPartie;

/**
 * Created by Flo on 01/12/2016.
 */
public class JoueurUnitTest {

    @Test
    public void testMainToDefausse_DefausseToDeck(){
        Joueur[] joueurs = new Joueur[1];
        Partie p = creerPartie();
        joueurs[0] = new Joueur("joueur", creerCartesPourTest(p), 0);
        p.setJoueurs(joueurs);

        Assert.assertEquals(0, joueurs[0].getSizeMain());
        Assert.assertEquals(8, joueurs[0].getSizeDeck());
        Assert.assertEquals(0, joueurs[0].getSizeDefausse());

        joueurs[0].piocher(2);
        Assert.assertEquals(2, joueurs[0].getSizeMain());
        Assert.assertEquals(6, joueurs[0].getSizeDeck());
        Assert.assertEquals(0, joueurs[0].getSizeDefausse());

        joueurs[0].mainToDefausse();
        Assert.assertEquals(0, joueurs[0].getSizeMain());
        Assert.assertEquals(6, joueurs[0].getSizeDeck());
        Assert.assertEquals(2, joueurs[0].getSizeDefausse());

        joueurs[0].defausseToDeck();
        Assert.assertEquals(0, joueurs[0].getSizeMain());
        Assert.assertEquals(8, joueurs[0].getSizeDeck());
        Assert.assertEquals(0, joueurs[0].getSizeDefausse());
    }

    @Test
    public void testPiocherSi0CartesDeck(){
        Joueur[] joueurs = new Joueur[1];
        Partie p = creerPartie();
        joueurs[0] = new Joueur("joueur", creerCartesPourTest(p), 0);
        p.setJoueurs(joueurs);

        Assert.assertEquals(0, joueurs[0].getSizeMain());
        Assert.assertEquals(8, joueurs[0].getSizeDeck());
        Assert.assertEquals(0, joueurs[0].getSizeDefausse());

        joueurs[0].piocher(6);
        Assert.assertEquals(6, joueurs[0].getSizeMain());
        Assert.assertEquals(2, joueurs[0].getSizeDeck());
        Assert.assertEquals(0, joueurs[0].getSizeDefausse());

        joueurs[0].mainToDefausse();
        Assert.assertEquals(0, joueurs[0].getSizeMain());
        Assert.assertEquals(2, joueurs[0].getSizeDeck());
        Assert.assertEquals(6, joueurs[0].getSizeDefausse());

        joueurs[0].piocher(5);     //la defausse est remise dans deck
        Assert.assertEquals(5, joueurs[0].getSizeMain());
        Assert.assertEquals(3, joueurs[0].getSizeDeck());
        Assert.assertEquals(0, joueurs[0].getSizeDefausse());
    }


    @Test
    public void testRemoveCarteMain(){
        Joueur[] joueurs = new Joueur[1];
        Partie p = creerPartie();
        ArrayList<Cards> cards = new ArrayList<>();
        ActionCards carteAction = new ActionCards(2,null,3);
        ActionCards carteAction2 = new ActionCards(4, null, 8);
        cards.add(carteAction);
        cards.add(carteAction2);

        joueurs[0] = new Joueur("joueur", cards, 0);
        p.setJoueurs(joueurs);

        Assert.assertEquals(0, joueurs[0].getSizeMain());
        Assert.assertEquals(2, joueurs[0].getSizeDeck());
        Assert.assertEquals(0, joueurs[0].getSizeDefausse());

        joueurs[0].piocher(2);
        joueurs[0].removeCarteMainEcartee(carteAction);
        Assert.assertEquals(1, joueurs[0].getSizeMain());
        Assert.assertEquals(0, joueurs[0].getSizeDeck());
        Assert.assertEquals(0, joueurs[0].getSizeDefausse());

        joueurs[0].carteVaDeMainADefausse(0);
        Assert.assertEquals(0, joueurs[0].getSizeMain());
        Assert.assertEquals(0, joueurs[0].getSizeDeck());
        Assert.assertEquals(1, joueurs[0].getSizeDefausse());
    }
}