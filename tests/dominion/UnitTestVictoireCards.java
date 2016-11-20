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

    public void reserveDomainePioche1(){
        VictoireCards carte = new VictoireCards();
        carte.domaine();
        Assert.assertEquals(11, carte.reserveCarteDomaine);
    }

    public void ducheVaut1(){
        VictoireCards carte = new VictoireCards();
        carte.duche();
        Assert.assertEquals(3, carte.reservePoint);
    }

    public void reserveDuchePioche1(){
        VictoireCards carte = new VictoireCards();
        carte.duche();
        Assert.assertEquals(11, carte.reserveCarteDuche);
    }

    public void ProvinceVaut6(){
        VictoireCards carte = new VictoireCards();
        carte.province();
        Assert.assertEquals(6, carte.reservePoint);
    }

    public void reserveProvincePioche1(){
        VictoireCards carte = new VictoireCards();
        carte.province();
        Assert.assertEquals(11, carte.reserveCarteDuche);
    }
}