package dominion;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static dominion.CreationObjetsPourTests.creerCartesPourTest;
import static dominion.CreationObjetsPourTests.creerPartie;

/**
 * Created by Flo on 06/12/2016.
 */
public class PartieUnitTest {

    //test getIdPileAction

    @Test
    public void testGetIdPileCarteActionVide(){
        ArrayList<ArrayList<ActionCards>> listeCartesAction = new ArrayList<ArrayList<ActionCards>>();
        listeCartesAction.add(new ArrayList<ActionCards>());


        Joueur[] joueurs = new Joueur[2];
        Partie p = creerPartie();
        joueurs[0] = new Joueur("joueur", creerCartesPourTest(p), 0);
        joueurs[1] = new Joueur("j2", creerCartesPourTest(p), 0);
        p.setJoueurs(joueurs);
        p.setCartesAction(listeCartesAction);

        Assert.assertEquals(-1, p.getIdPileCarteAction(0));
    }

    @Test
    public void testGetIdPileCarteActionNonVide(){
        ArrayList<ArrayList<ActionCards>> listeCartesAction = new ArrayList<ArrayList<ActionCards>>();
        ArrayList<ActionCards> toutesCarteActionDUnType = new ArrayList<ActionCards>();
        toutesCarteActionDUnType.add(new ActionCards(5, null, 1));
        listeCartesAction.add(toutesCarteActionDUnType);

        Joueur[] joueurs = new Joueur[2];
        Partie p = creerPartie();
        joueurs[0] = new Joueur("joueur", creerCartesPourTest(p), 0);
        joueurs[1] = new Joueur("j2", creerCartesPourTest(p), 0);
        p.setJoueurs(joueurs);
        p.setCartesAction(listeCartesAction);

        Assert.assertEquals(5, p.getIdPileCarteAction(0));
    }



    //test ecarteCarteActionSurPile
    @Test
    public void testEcarteCarteActionSurPile(){
        ArrayList<ArrayList<ActionCards>> listeCartesAction = new ArrayList<ArrayList<ActionCards>>();
        ArrayList<ActionCards> toutesCarteActionDUnType = new ArrayList<ActionCards>();
        ActionCards carte = new ActionCards(5, null, 1);
        toutesCarteActionDUnType.add(carte);
        listeCartesAction.add(toutesCarteActionDUnType);

        List liste = new ArrayList<Cards>();
        liste.add(carte);


        Joueur[] joueurs = new Joueur[1];
        Partie p = creerPartie();
        joueurs[0] = new Joueur("joueur", liste, 0);
        p.setJoueurs(joueurs);
        p.setCartesAction(listeCartesAction);

        joueurs[0].piocher(1);
        Assert.assertEquals(1, joueurs[0].getSizeMain());
        Assert.assertEquals(1, p.getNbRestantCartesAction(0));

        p.ecarteCarteActionSurPile(carte, 0);
        Assert.assertEquals(0, joueurs[0].getSizeMain());
        Assert.assertEquals(2, p.getNbRestantCartesAction(0));
    }



    //test tryAchat

    @Test
    public void testTryAchatCarteSurPileVide(){
        ArrayList<ArrayList<VictoireCards>> listeCartesVictoire = new ArrayList<ArrayList<VictoireCards>>();
        ArrayList<ArrayList<CoinsCards>> listeCoinsCards = new ArrayList<ArrayList<CoinsCards>>();
        ArrayList<ArrayList<ActionCards>> listeCartesAction = new ArrayList<ArrayList<ActionCards>>();

        listeCartesVictoire.add(new ArrayList<VictoireCards>());
        listeCartesAction.add(new ArrayList<ActionCards>());
        listeCoinsCards.add(new ArrayList<CoinsCards>());


        Joueur[] joueurs = new Joueur[2];
        Partie p = new Partie(EtapesTour.ACHAT, listeCartesVictoire, listeCoinsCards, null, null);
        joueurs[0] = new Joueur("joueur", creerCartesPourTest(p), 0);
        joueurs[1] = new Joueur("j2", creerCartesPourTest(p), 0);
        p.setJoueurs(joueurs);
        p.setCartesAction(listeCartesAction);

        joueurs[0].addCoin(5000);
        joueurs[0].piocher(5);
        Assert.assertEquals(false, p.tryAchatCarteAction(0));
        Assert.assertEquals(false, p.tryAchatCarteTresor(0));
        Assert.assertEquals(false, p.tryAchatCarteVictoire(0));
        Assert.assertEquals(5000, joueurs[0].getCoins());
        Assert.assertEquals(5, joueurs[0].getSizeMain());
    }

    @Test
    public void testTryAchatCarteSiPasAssezDeCoin(){
        ArrayList<ArrayList<VictoireCards>> listeCartesVictoire = new ArrayList<ArrayList<VictoireCards>>();
        ArrayList<ArrayList<CoinsCards>> listeCoinsCards = new ArrayList<ArrayList<CoinsCards>>();
        ArrayList<ArrayList<ActionCards>> listeCartesAction = new ArrayList<ArrayList<ActionCards>>();

        ArrayList<VictoireCards> toutesCarteVictoireDUnType = new ArrayList<VictoireCards>();
        ArrayList<CoinsCards> toutesCarteAchatDUnType = new ArrayList<CoinsCards>();
        ArrayList<ActionCards> toutesCarteActionDUnType = new ArrayList<ActionCards>();

        toutesCarteVictoireDUnType.add(new VictoireCards(0));
        toutesCarteAchatDUnType.add(new CoinsCards(0));
        toutesCarteActionDUnType.add(new ActionCards(5, null, 1));

        listeCartesVictoire.add(toutesCarteVictoireDUnType);
        listeCartesAction.add(toutesCarteActionDUnType);
        listeCoinsCards.add(toutesCarteAchatDUnType);


        Joueur[] joueurs = new Joueur[2];
        Partie p = new Partie(EtapesTour.ACHAT, listeCartesVictoire, listeCoinsCards, null, null);
        joueurs[0] = new Joueur("joueur", creerCartesPourTest(p), 0);
        joueurs[1] = new Joueur("j2", creerCartesPourTest(p), 0);
        p.setJoueurs(joueurs);
        p.setCartesAction(listeCartesAction);

        //de base, le joueur a 0 coin
        joueurs[0].piocher(5);
        Assert.assertEquals(false, p.tryAchatCarteAction(0));
        Assert.assertEquals(false, p.tryAchatCarteTresor(0));
        Assert.assertEquals(false, p.tryAchatCarteVictoire(0));
        Assert.assertEquals(0, joueurs[0].getCoins());
        Assert.assertEquals(5, joueurs[0].getSizeMain());
    }

    @Test
    public void testTryAchatCarteReussi(){
        ArrayList<ArrayList<VictoireCards>> listeCartesVictoire = new ArrayList<ArrayList<VictoireCards>>();
        ArrayList<ArrayList<CoinsCards>> listeCoinsCards = new ArrayList<ArrayList<CoinsCards>>();
        ArrayList<ArrayList<ActionCards>> listeCartesAction = new ArrayList<ArrayList<ActionCards>>();

        ArrayList<VictoireCards> toutesCarteVictoireDUnType = new ArrayList<VictoireCards>();
        ArrayList<CoinsCards> toutesCarteAchatDUnType = new ArrayList<CoinsCards>();
        ArrayList<ActionCards> toutesCarteActionDUnType = new ArrayList<ActionCards>();

        toutesCarteVictoireDUnType.add(new VictoireCards(0));   //coute 2
        toutesCarteAchatDUnType.add(new CoinsCards(0));        //coute 2
        toutesCarteActionDUnType.add(new ActionCards(5, null, 1));  //coute 1

        listeCartesVictoire.add(toutesCarteVictoireDUnType);
        listeCartesAction.add(toutesCarteActionDUnType);
        listeCoinsCards.add(toutesCarteAchatDUnType);


        Joueur[] joueurs = new Joueur[2];
        Partie p = new Partie(EtapesTour.ACHAT, listeCartesVictoire, listeCoinsCards, null, null);
        joueurs[0] = new Joueur("joueur", creerCartesPourTest(p), 0);
        joueurs[1] = new Joueur("j2", creerCartesPourTest(p), 0);
        p.setJoueurs(joueurs);
        p.setCartesAction(listeCartesAction);

        //de base, le joueur a 0 coin
        joueurs[0].piocher(5);
        joueurs[0].addCoin(4);
        Assert.assertEquals(true, p.tryAchatCarteTresor(0));
        Assert.assertEquals(true, p.tryAchatCarteVictoire(0));
        Assert.assertEquals(false, p.tryAchatCarteAction(0));
        Assert.assertEquals(0, joueurs[0].getCoins());
        Assert.assertEquals(7, joueurs[0].getSizeMain());
        Assert.assertEquals(1, p.getNbRestantCartesAction(0));
        Assert.assertEquals(0, p.getNbRestantCartesTresor(0));
        Assert.assertEquals(0, p.getNbRestantCartesVictoire(0));


        joueurs[0].addCoin(1);
        Assert.assertEquals(true, p.tryAchatCarteAction(0));
        Assert.assertEquals(0, joueurs[0].getCoins());
        Assert.assertEquals(8, joueurs[0].getSizeMain());
        Assert.assertEquals(0, p.getNbRestantCartesAction(0));
    }



    //tests fin tour

    @Test
    public void testFinTourActionSiResteToursAction(){
        Joueur[] joueurs = new Joueur[2];
        Partie p = creerPartie();
        joueurs[0] = new Joueur("joueur", creerCartesPourTest(p), 0);
        joueurs[1] = new Joueur("j2", creerCartesPourTest(p), 0);
        p.setJoueurs(joueurs);

        joueurs[0].addTourAction(2);
        Assert.assertEquals(3, joueurs[0].getNbTourAction());
        p.finTourAction();
        Assert.assertEquals(EtapesTour.ACTION, p.getEtapesTour());
        Assert.assertEquals(2, joueurs[0].getNbTourAction());
    }

    @Test
    public void testFinTourActionSiDernierTourAction(){
        Joueur[] joueurs = new Joueur[2];
        Partie p = creerPartie();
        joueurs[0] = new Joueur("joueur", creerCartesPourTest(p), 0);
        joueurs[1] = new Joueur("j2", creerCartesPourTest(p), 0);
        p.setJoueurs(joueurs);

        joueurs[0].piocher(5);
        Assert.assertEquals(3, joueurs[0].getSizeDeck());
        Assert.assertEquals(5, joueurs[0].getSizeMain());
        Assert.assertEquals(0, joueurs[0].getSizeDefausse());
        Assert.assertEquals(1, joueurs[0].getNbTourAction());

        p.finTourAction();
        Assert.assertEquals(EtapesTour.ACHAT, p.getEtapesTour());
        Assert.assertEquals(1, joueurs[0].getNbTourAction());
        Assert.assertEquals(5, joueurs[0].getSizeMain());
        Assert.assertEquals(0, joueurs[0].getSizeDefausse());
        Assert.assertEquals(3, joueurs[0].getSizeDeck());
    }





    @Test
    public void testFinTourAchatSiResteToursAchat(){
        Joueur[] joueurs = new Joueur[2];
        Partie p = creerPartie();
        joueurs[0] = new Joueur("joueur", creerCartesPourTest(p), 0);
        joueurs[1] = new Joueur("j2", creerCartesPourTest(p), 0);
        p.setJoueurs(joueurs);

        joueurs[0].addTourAchat(2);
        Assert.assertEquals(3, joueurs[0].getNbTourAchat());
        p.finTourAchat();
        Assert.assertEquals(EtapesTour.ACTION, p.getEtapesTour());
        Assert.assertEquals(2, joueurs[0].getNbTourAchat());
    }

    @Test
    public void testFinTourAchatSiDernierTourAchat(){
        Joueur[] joueurs = new Joueur[2];
        Partie p = creerPartie();
        joueurs[0] = new Joueur("joueur", creerCartesPourTest(p), 0);
        joueurs[1] = new Joueur("j2", creerCartesPourTest(p), 0);
        p.setJoueurs(joueurs);

        joueurs[0].piocher(5);
        Assert.assertEquals(3, joueurs[0].getSizeDeck());
        Assert.assertEquals(5, joueurs[0].getSizeMain());
        Assert.assertEquals(0, joueurs[0].getSizeDefausse());
        Assert.assertEquals(1, joueurs[0].getNbTourAchat());

        p.finTourAchat();
        Assert.assertEquals(EtapesTour.ACTION, p.getEtapesTour());
        Assert.assertEquals(1, joueurs[0].getNbTourAchat());
        Assert.assertEquals(5, joueurs[0].getSizeMain());
        Assert.assertEquals(0, joueurs[0].getSizeDefausse());
        Assert.assertEquals(3, joueurs[0].getSizeDeck());
    }




    //test fin partie

    @Test
    public void testFinDePartieSiPlusDeProvince(){
        Joueur[] joueurs = new Joueur[2];
        Partie p = creerPartie();
        joueurs[0] = new Joueur("joueur", creerCartesPourTest(p), 0);
        joueurs[1] = new Joueur("j2", creerCartesPourTest(p), 0);
        p.setJoueurs(joueurs);
        Assert.assertEquals(false, p.finDePartie());

        joueurs[0].addCoin(5000);
        for(int i=0 ; i<7; i++){
            p.tryAchatCarteVictoire(2);
        }
        Assert.assertEquals(false, p.finDePartie());

        p.tryAchatCarteVictoire(2);
        Assert.assertEquals(true, p.finDePartie());
    }

    @Test
    public void testFinDePartieSi3TasVides(){
        Joueur[] joueurs = new Joueur[2];
        Partie p = creerPartie();
        joueurs[0] = new Joueur("joueur", creerCartesPourTest(p), 0);
        joueurs[1] = new Joueur("j2", creerCartesPourTest(p), 0);
        p.setJoueurs(joueurs);
        Assert.assertEquals(false, p.finDePartie());

        joueurs[0].addCoin(5000);
        for(int i=0 ; i<8; i++){
            p.tryAchatCarteVictoire(0);
        }
        Assert.assertEquals(false, p.finDePartie());

        for(int i=0 ; i<10; i++){
            p.tryAchatCarteTresor(0);
        }
        Assert.assertEquals(false, p.finDePartie());

        for(int i=0 ; i<9; i++){
            p.tryAchatCarteAction(0);
        }
        Assert.assertEquals(false, p.finDePartie());

        p.tryAchatCarteAction(0);
        Assert.assertEquals(true, p.finDePartie());
    }
}
