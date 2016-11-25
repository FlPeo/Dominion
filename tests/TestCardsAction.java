import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ndolo on 20/11/16.
 */
public class TestCardsAction {
    @Test
    public void testPiocheDecrementation(){
        CardsAction ca = new CardsAction(1,0,0,0,0,5,0,0,false);
        ca.pioche();
        int deck = ca.getDeck();
        Assert.assertEquals(deck,4);
    }

    @Test
    public void testPiochePasNegatif(){
        CardsAction ca = new CardsAction(1,0,0,0,0,0,0,0,false);
        ca.pioche();
        int deck = ca.getDeck();
        Assert.assertEquals(deck,0);
    }

    @Test
    public void testPiocheAddCarte(){
        CardsAction ca =  new CardsAction(0,0,0,0,0,5,0,0,false);
        ca.pioche();
        int main = ca.main;
        Assert.assertEquals(main,1);
    }

    @Test
    public void testAddGold(){
        int gold = 0;
        Assert.assertEquals(gold,0);
        CardsAction ca = new CardsAction(0,0,0,0,0,0,gold,0,false);
        ca.addGold();
        gold = ca.getGold();
        Assert.assertEquals(gold,1);
    }

    @Test
    public void testAddGold2(){
        int gold = 0;
        Assert.assertEquals(gold,0);
        CardsAction ca = new CardsAction(0,0,0,0,0,0,gold,0,false);
        ca.addGold2();
        gold = ca.getGold();
        Assert.assertEquals(gold,2);
    }

    @Test
    public void testDefausse3(){
        CardsAction ca = new CardsAction(0,0,0,0,0,0,0,5,false);
        ca.defausse3();
        int main = ca.getMain();
        Assert.assertEquals(main,2);
    }

    @Test
    public void testDefausse4(){
        CardsAction ca = new CardsAction(0,0,0,0,0,0,0,5,false);
        ca.defausse4();
        int main = ca.getMain();
        Assert.assertEquals(main,1);
    }

    @Test
    public void testAddAction(){
        CardsAction ca =  new CardsAction(0,0,0,0,0,5,0,0,false);
        ca.addAction();
        ca.getAddTourAction();
        int action = ca.getAddTourAction();
        Assert.assertEquals(action,1);
    }

    @Test
    public void testAddAchat(){
        CardsAction ca =  new CardsAction(0,0,0,0,0,5,0,0,false);
        ca.addAchat();
        ca.getAddTourAchat();
        int achat = ca.getAddTourAchat();
        Assert.assertEquals(achat,1);
    }

    @Test
    public void testAddCarteTresor3FoisPlusGrande(){
        CardsAction ca = new CardsAction(0,0,0,0,0,5,1,0,false);
        int tresor = ca.getGold();
        ca.AddCarteTresor3FoisPlusGrande(tresor);
        tresor = ca.getGold();
        Assert.assertEquals(tresor,4);
    }
    
    @Test
    public void testReveleCarte(){
        CardsAction ca = new CardsAction(0,0,0,0,0,5,1,0,false);
        ca.ReveleCarte();
        boolean isRevele = ca.isRevele();
        Assert.assertEquals(isRevele, true);
    }


    @Test
    public void testJouer2FoisLaMemeCarte(){
        CardsAction ca = new CardsAction(0,0,0,0,0,5,1,0,false);
        ca.dedouble();
        CardsAction ca2 = ca.getCaDedouble();
        Assert.assertEquals(ca,ca2);
    }
}
