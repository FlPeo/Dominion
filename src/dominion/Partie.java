package dominion;

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

    private List<Cards> pileCuivre;
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
    private List<Cards> pileAction10;



    /**
     * Constructeur du model en lien avec la partie
     * Met en place la partie
     */
    Partie()
    {
        int i;

        //coins
        for (i=0; i<46; i++) //46 cartes cuivre
            pileCuivre.add(new CoinsCards(1));
        for (i=0; i<40; i++) //40 cartes argent
            pileArgent.add(new CoinsCards(2));
        for (i=0; i<30; i++) //30 cartes or
            pileOr.add(new CoinsCards(3));

        //victoires
        for (i=0; i<8; i++) { //8 cartes pour toutes les piles victoires
            pileDomaine.add(new VictoireCards(1));
            pileDuche.add(new VictoireCards(2));
            pileProvince.add(new VictoireCards(3));
        }

        //actions
        for (i=0; i<10; i++) { //10 cartes pour toutes les piles actions
            //todo : choisir aléatoirement 10 types de cartes actions
            //todo : mettre en place les piles actions une fois que les cartes action seront faites
            //pileAction1.add(...);
            //pileAction2.add(...);
            //...
        }

        //met en place la liste de toutes les piles
        listeDeToutesLesPiles.add(pileDomaine);
        listeDeToutesLesPiles.add(pileDuche);
        listeDeToutesLesPiles.add(pileProvince);
        listeDeToutesLesPiles.add(pileCuivre);
        listeDeToutesLesPiles.add(pileArgent);
        listeDeToutesLesPiles.add(pileOr);
        listeDeToutesLesPiles.add(pileAction1);
        listeDeToutesLesPiles.add(pileAction2);
        listeDeToutesLesPiles.add(pileAction3);
        listeDeToutesLesPiles.add(pileAction4);
        listeDeToutesLesPiles.add(pileAction5);
        listeDeToutesLesPiles.add(pileAction6);
        listeDeToutesLesPiles.add(pileAction7);
        listeDeToutesLesPiles.add(pileAction8);
        listeDeToutesLesPiles.add(pileAction9);
        listeDeToutesLesPiles.add(pileAction10);
    }

    /**
     * Gère le déroulement de la partie
     * @return : le gagnant
     */
    public Joueur organisationPartie(){
        //1 phase d'action
        //1.5 joue les cartes coins
        //2 phase d'achat
        //3 fin de phases
        int i,j;
        //continuer a faire des tours tant que la partie n'est pas terminé
        while(!finDePartie()){
            //a chaque début de tour, tous les joueurs pioche 5 cartes :
            for (i=0; i<joueurs.length;i++){
                joueurs[i].piocher(5);
            }

            //chaque joueurs jouent dans un tour
            for (i=0; i<joueurs.length;i++){
                joueurCourrant = joueurs[i];

                //1) Peut jouer des cartes actions, jusqu'à ce qu'il ne puisse plus
                for (j=0; j<joueurCourrant.getNbTourAction(); j++){
                    //todo :
                    //attend l'action du joueur
                    //if carte donne des + nbTourAction -> augmenter le nbTourAction du joueur
                    //if plus de carte action dans la main -> sort de la boucle
                }

                //1.5) todo : Joue les cartes coins

                //2) Peut acheter des cartes, jusqu'à ce qu'il ne puisse plus
                for (j=0; j<joueurCourrant.getNbTourAchat(); j++){
                    //todo :
                    //attend l'action du joueur
                }

                //3) Vide la main du joueur
                joueurCourrant.mainToDefausse();
            }
        }

        return joueurCourrant; //todo : a changer, il faut retourner le gagnant
    }

    /**
     * La partie est terminé si :
     * il n'y plus de carte province
     * ou si 3 piles sont vide
     * @return true si la partie est terminé
     */
    private boolean finDePartie() {
        if (pileProvince.size()==0) return true;


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
