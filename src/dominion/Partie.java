package dominion;

/**
 * Created by florian on 13/11/2016.
 */

public class Partie
{

    private int reserveCarteDomaine = 12;
    private int reserveCarteDuche = 12;
    private int reserveCarteProvince = 12;

    /**
     * Constructeur du model en lien avec la partie
     */
    Partie()
    {

    }

    public int getReserveCarteDomaine() {
        return reserveCarteDomaine;
    }

    public void setReserveCarteDomaine(int reserveCarteDomaine) {
        this.reserveCarteDomaine = reserveCarteDomaine;
    }

    public int getReserveCarteDuche() {
        return reserveCarteDuche;
    }

    public void setReserveCarteDuche(int reserveCarteDuche) {
        this.reserveCarteDuche = reserveCarteDuche;
    }

    public int getReserveCarteProvince() {
        return reserveCarteProvince;
    }

    public void setReserveCarteProvince(int reserveCarteProvince) {
        this.reserveCarteProvince = reserveCarteProvince;
    }
}
