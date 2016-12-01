package dominion;

import java.util.List;

/**
 * Created by florian on 13/11/2016.
 */

public class Partie
{
    private Joueur[] joueurs;
    private Joueur joueurCourrant;

    private int reserveCarteDomaine = 12;
    private int reserveCarteDuche = 12;
    private int reserveCarteProvince = 12;

    /**
     * Constructeur du model en lien avec la partie
     */
    Partie()
    {
        //1 phase d'action
        //2 phase d'achat
        //3 fin de phases
        int i,j;
        //continuer a faire des tours tant que la partie n'est pas terminé
        while(!finDePartie()){
            //chaque joueurs jouent dans un tour
            for (i=0; i<joueurs.length;i++){
                joueurCourrant = joueurs[i];

                //Peut jouer des cartes actions, jusqu'à ce qu'il ne puisse plus
                for (j=0; j<joueurCourrant.getNbTourAction(); j++){
                    //todo :
                        //attend l'action du joueur
                            //if joue une carte -> -1 a son nbTourAction
                            //if carte donne des + nbTourAction -> augmenter le nbTourAction du joueur
                }

                //Joue les coins



            }
        }
    }

    /**
     * Sylvain doit faire ça
     * @return true si la partie est terminé
     */
    private boolean finDePartie() {
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
}
