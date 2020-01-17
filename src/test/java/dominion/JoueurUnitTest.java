package dominion;


import dominion.Actions.Action;
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

    @Test
    public void testPiocherMalediction(){
        Joueur[] joueurs = new Joueur[1];
        Partie p = creerPartie();
        ArrayList<Cards> cards = new ArrayList<>();
        joueurs[0] = new Joueur("joueur", cards, 0);
        p.setJoueurs(joueurs);

        VictoireCards carte = p.getListeCartesVictoire().get(3).get(0);
        p.getListeCartesVictoire().get(3).remove(0);
        joueurs[0].piocherMalediction(carte);
        Assert.assertEquals(1, joueurs[0].getSizeMain());
    }

    @Test
    public void testDeckToDefausse(){
        Joueur[] joueurs = new Joueur[1];
        Partie p = creerPartie();
        joueurs[0] = new Joueur("joueur", creerCartesPourTest(p), 0);
        p.setJoueurs(joueurs);

        joueurs[0].deckToDefausse();
        Assert.assertEquals(0,joueurs[0].getSizeDeck());
    }


    @Test
    public void testCalculePoints(){
        Joueur[] joueurs = new Joueur[1];
        Partie p = creerPartie();

        ArrayList<Cards> cards = new ArrayList<>();
        ArrayList<Action> listeActions;

        listeActions = new ArrayList<Action>();    //carte jardins
        cards.add(new ActionCards(12, listeActions, 4));   //+1 pt car 10 cartes

        cards.add(new VictoireCards(0));   // +1pt
        cards.add(new VictoireCards(1));   // 31pt
        cards.add(new VictoireCards(2));   // 61pt
        cards.add(new VictoireCards(3));   // -1pt

        for(int i = 0 ; i<5 ; i++){
            cards.add(new CoinsCards(2));   //+0pt
        }


        joueurs[0] = new Joueur("joueur", cards, 0);
        p.setJoueurs(joueurs);

        Assert.assertEquals(10, joueurs[0].calculePoints());
    }
}