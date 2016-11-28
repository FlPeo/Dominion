package dominion;

/**
 * Created by sakalypse on 20/11/16.
 */
public class VictoireCards extends Cards {

    int pointVictoire;
    int cout;

    VictoireCards(int valeurIdCarteVictoire){
        switch (valeurIdCarteVictoire){
            case 1: pointVictoire = 1; //domaine
                    cout = 2;
                    break;
            case 2: pointVictoire = 3; //duche
                    cout = 5;
                    break;
            case 3 : pointVictoire = 6; //province
                    cout = 8;
                    break;
        }
    }
}
