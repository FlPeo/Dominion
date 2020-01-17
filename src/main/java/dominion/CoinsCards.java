package dominion;

import java.util.ArrayList;

/**
 * Created by mlucile on 25/11/16.
 */
public class CoinsCards extends Cards
{
    private int pointCoin;

    CoinsCards(int valeurIdCarteCoins){
        super(valeurIdCarteCoins);
        switch (valeurIdCarteCoins){
            case 0: pointCoin = 1; //copper
                setCout(2);
                break;
            case 1: pointCoin = 2; //silver
                setCout(3);
                break;
            case 2 : pointCoin = 3; //gold
                setCout(6);
                break;
        }
    }

    public static ArrayList<ArrayList<CoinsCards>> creerCartesTresor(int nbJoueurs) {
        ArrayList<ArrayList<CoinsCards>> listeCartes = new ArrayList<ArrayList<CoinsCards>>();
        ArrayList<CoinsCards> listeCartesUnType;

        listeCartesUnType = new ArrayList<>();
        for(int j = 0 ; j<(60 - 7*nbJoueurs) ; j++){
            listeCartesUnType.add(new CoinsCards(0));
        }
        listeCartes.add(listeCartesUnType);

        listeCartesUnType = new ArrayList<>();
        for(int j = 0 ; j<40 ; j++){
            listeCartesUnType.add(new CoinsCards(1));
        }
        listeCartes.add(listeCartesUnType);

        listeCartesUnType = new ArrayList<>();
        for(int j = 0 ; j<30 ; j++){
            listeCartesUnType.add(new CoinsCards(2));
        }
        listeCartes.add(listeCartesUnType);

        return listeCartes;
    }

    public boolean isCarteTresor(){
        return true;
    }

    public String getCheminImage(){
        return super.getCheminImage() + "Tresor/Tresor" + id + ".jpg";
    }

    public int getPointCoin() {
        return pointCoin;
    }
}
