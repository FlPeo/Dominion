package dominion;
import org.junit.Assert;

/**
 * Created by sakalypse on 20/11/2016.
 */
public class UnitTestVictoireCards {

    public void domaineVaut1(){
        VictoireCards carte = new VictoireCards();
        carte.domaine();
        Assert.assertEquals(1, carte.reservePoint);
    }

    public void reserveDomainePerd1(){
        VictoireCards carte = new VictoireCards();
        carte.domaine();
        Assert.assertEquals(11, carte.reserveCarteDomaine);
    }
}