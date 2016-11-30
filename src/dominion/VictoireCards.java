package dominion;

/**
 * Created by sakalypse on 20/11/16.
 */
public class VictoireCards extends Cards {

    private int pointVictoire;

    VictoireCards(int valeurIdCarteVictoire){
        switch (valeurIdCarteVictoire){
            case 1: pointVictoire = 1; //domaine
                    setCout(2);
                    break;
            case 2: pointVictoire = 3; //duche
                    setCout(5);
                    break;
            case 3 : pointVictoire = 6; //province
                    setCout(8);
                    break;
            case 4 : pointVictoire = -1; //malédiction
                    setCout(0);
                    break;
        }
    }

    public int getPointVictoire() {
        return pointVictoire;
    }

    public void setPointVictoire(int pointVictoire) {
        this.pointVictoire = pointVictoire;
    }
}
