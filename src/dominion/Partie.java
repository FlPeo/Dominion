package dominion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by florian on 13/11/2016.
 */

public class Partie
{
    private Joueur[] joueurs;
    private Joueur joueurCourrant;

    //listeDeToutesLesPiles sera très utile pour verifier si 3 des piles sont vide
    private List<List<Cards>> listeDeToutesLesPiles;

    private List<ArrayList<VictoireCards>> listeCartesVictoire;
    private List<ArrayList<CoinsCards>> listeCartesTresor;
    private List<ArrayList<ActionCards>> listeCartesAction;
    private ArrayList<ArrayList<ActionCards>> cartesAction;

    /*private List<Cards> pileCuivre;
    private List<Cards> pileArgent;
    private List<Cards> pileOr;

    private List<Cards> pileDomaine;
    private List<Cards> pileDuche;
    private List<Cards> pileProvince;

    private List<Cards> pileAction1;
    private List<Cards> pileAction2;
    private List<Cards> pileAction3;
    private List<Cards> pileAction4;
    private List<Cards> pileAction5;
    private List<Cards> pileAction6;
    private List<Cards> pileAction7;
    private List<Cards> pileAction8;
    private List<Cards> pileAction9;
    private List<Cards> pileAction10;*/


    public static Partie creerPartie(String[] nomJoueurs) {
        Joueur[] joueurs = new Joueur[nomJoueurs.length];

        for (int i = 0; i < nomJoueurs.length; i++) {
            joueurs[i] = new Joueur(nomJoueurs[i], Joueur.createDeckDepart());
        }




        ArrayList<ArrayList<VictoireCards>> cartesVictoire = VictoireCards.creerCartesVictoire(nomJoueurs.length);
        ArrayList<ArrayList<CoinsCards>> cartesTresor = CoinsCards.creerCartesTresor(nomJoueurs.length);


        Partie p = new Partie(cartesVictoire, cartesTresor);
        ArrayList<ArrayList<ActionCards>> cartesAction = ActionCards.creer10CartesAction(p, nomJoueurs.length);


        p.setCartesAction(cartesAction);
        p.setJoueurs(joueurs);
        return p;
    }






    public Partie(ArrayList<ArrayList<VictoireCards>> cartesVictoire, ArrayList<ArrayList<CoinsCards>> cartesTresor) {

        listeDeToutesLesPiles = new ArrayList<List<Cards>>();

        this.listeCartesVictoire= cartesVictoire;
        this.listeCartesTresor = cartesTresor;
        //initReservesCartesAction();
    }



    /*public int getReserveCarteDuche() {
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
    }*/

    public Joueur getJoueurCourrant() {
        return joueurCourrant;
    }

    public void setJoueurCourrant(Joueur joueurCourrant) {
        this.joueurCourrant = joueurCourrant;
    }

    public void setJoueurs(Joueur[] joueurs) {
        this.joueurs = joueurs;
        joueurCourrant = joueurs[0];
    }


    /**
     * Gère le déroulement de la partie
     * @return : le gagnant
     */
    public Joueur organisationPartie(){
        //todo : a faire
        return joueurCourrant;
    }

    /**
     * La partie est terminé si :
     * il n'y plus de carte province
     * ou si 3 piles sont vide
     * @return true si la partie est terminé
     */
    private boolean finDePartie() {
        if (listeCartesVictoire.get(2).size()==0) return true;


        int nombrePileVide = 0;
        for (int i=0; i<listeDeToutesLesPiles.size(); i++)
            if (listeDeToutesLesPiles.get(i).size()==0)
                nombrePileVide++;
        if (nombrePileVide>=3) return true;


        return false;
    }

    /**
     * Le joueur pioche un certain nombre de carte de son deck
     * @nbCarte : le nombre de carte à piocher
     */
    public void joueurPioche(Joueur joueur, int nbCarte){
        joueur.piocher(nbCarte);
    }



    /**
     * Le joueur reçoit une carte dans sa defausse
     * @carte : la carte reçu
     */
    public void giveCardToJoueur(Joueur joueur, Cards carte){
        joueur.getDeck().add(carte);
    }

    /**
     * Le joueur achete une carte action, victoire, coin ou malé
     * Va vérifier qu'il a assez de sous pour l'acheter
     * Puis va l'ajouter a sa défausse
     * @carte : la carte acheté
     */
    public void joueurAchete(Joueur joueur, Cards carte){
        if (joueur.getCoins()>=carte.getCout()){
            giveCardToJoueur(joueur, carte);
        }
    }

    public void setCartesAction(ArrayList<ArrayList<ActionCards>> cartesAction) {
        this.cartesAction = cartesAction;
    }
}
