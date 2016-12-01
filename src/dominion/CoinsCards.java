package dominion;

/**
 * Created by mlucile on 25/11/16.
 */
public class CoinsCards extends Cards
{
    private int pointCoin;

    CoinsCards(int valeurIdCarteCoins){
        switch (valeurIdCarteCoins){
            case 1: pointCoin = 1; //cuivre
                setCout(2);
                break;
            case 2: pointCoin = 2; //argent
                setCout(3);
                break;
            case 3 : pointCoin = 3; //or
                setCout(6);
                break;
        }
    }


    public int getPointCoin() {
        return pointCoin;
    }

    public void setPointCoin(int pointCoin) {
        this.pointCoin = pointCoin;
    }
}
