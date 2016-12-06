package dominion;

import java.util.ArrayList;

/**
 * Created by sakalypse on 20/11/16.
 */
public class VictoireCards extends Cards {

    private int pointVictoire;

    VictoireCards(int valeurIdCarteVictoire){
        super(valeurIdCarteVictoire);
        switch (valeurIdCarteVictoire){
            case 0: pointVictoire = 1; //domaine
                    setCout(2);
                    break;
            case 1: pointVictoire = 3; //duche
                    setCout(5);
                    break;
            case 2 : pointVictoire = 6; //province
                    setCout(8);
                    break;
            case 3 : pointVictoire = -1; //malédiction
                    setCout(0);
                    break;
        }
    }



    public static ArrayList<ArrayList<VictoireCards>> creerCartesVictoire(int nbJoueurs) {
        ArrayList<ArrayList<VictoireCards>> listeCartes = new ArrayList<ArrayList<VictoireCards>>();

        ArrayList<VictoireCards> listeUneSorteCarte;
        for(int i = 0 ; i<3 ; i++){
            listeUneSorteCarte = new ArrayList<VictoireCards>();
            for(int j = 0 ; j<((nbJoueurs ==2)?8:12) ; j++){
                listeUneSorteCarte.add(new VictoireCards(i));
            }
            listeCartes.add(listeUneSorteCarte);
        }

        listeUneSorteCarte = new ArrayList<VictoireCards>();
        for(int j = 0 ; j<((nbJoueurs-1) * 10) ; j++){
            listeUneSorteCarte.add(new VictoireCards(3));
        }
        listeCartes.add(listeUneSorteCarte);

        return listeCartes;
    }



    //get et set
    public String getCheminImage(){
        return super.getCheminImage() + "Victoire/Victoire" + id + ".jpg";
    }
    public int getPointVictoire() {
        return pointVictoire;
    }
    public boolean isCarteVictoire(){
        return true;
    }
}
