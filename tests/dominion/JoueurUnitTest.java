package dominion;


import org.junit.Assert;
import org.junit.Test;

import static dominion.CreationObjetsPourTests.creerCartesPourTest;
import static dominion.CreationObjetsPourTests.creerPartie;

/**
 * Created by Flo on 01/12/2016.
 */
public class JoueurUnitTest {

    @Test
    public void testMainToDefausse_DefausseToDeck(){
        Joueur[] joueurs = new Joueur[1];
        Partie p = creerPartie(joueurs);
        joueurs[0] = new Joueur("joueur", creerCartesPourTest(p));
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
        Partie p = creerPartie(joueurs);
        joueurs[0] = new Joueur("joueur", creerCartesPourTest(p));
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

}
