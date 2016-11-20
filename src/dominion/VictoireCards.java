package dominion;

/**
 * Created by sakalypse on 20/11/16.
 */
public class VictoireCards extends Cards {

    public int reservePoint = 0;
    public int reserveCarteDomaine = 12;
    public int reserveCarteDuche = 12;
    public int reserveCarteProvince = 12;

    /**
     *  Vaut 1 point de victoire
     *  Commence la partie avec 3 cartes domaine
     */
    public void domaine() {
        reservePoint += 1;
        reserveCarteDomaine--;
    }

    /**
     *  Vaut 3 points de victoire
     */
    public void duche(){
        reservePoint += 3;
        reserveCarteDuche--;
    }

    /**
     *  Vaut 6 points de victoire
     */
    public void province(){
        reservePoint += 6;
        reserveCarteProvince--;
    }

}
