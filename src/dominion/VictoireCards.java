package dominion;

/**
 * Created by sakalypse on 20/11/16.
 */
public class VictoireCards extends Cards {

    private int pointVictoire;
    private int cout;

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
            case 4 : pointVictoire = -1; //malédictio
                    cout = 0;
                    break;
        }
    }

    public int getPointVictoire() {
        return pointVictoire;
    }

    public void setPointVictoire(int pointVictoire) {
        this.pointVictoire = pointVictoire;
    }

    public int getCout() {
        return cout;
    }

    public void setCout(int cout) {
        this.cout = cout;
    }
}
