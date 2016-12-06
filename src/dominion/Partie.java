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
    private ArrayList<ArrayList<ArrayList<Cards>>> listeDeToutesLesPiles;

    private ArrayList<ArrayList<VictoireCards>> listeCartesVictoire;
    private ArrayList<ArrayList<CoinsCards>> listeCartesTresor;
    private ArrayList<ArrayList<ActionCards>> listeCartesAction;

    private EtapesTour etapesTour;

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
            joueurs[i] = new Joueur(nomJoueurs[i], Joueur.createDeckDepart(), i);
            joueurs[i].piocher(5);
        }




        ArrayList<ArrayList<VictoireCards>> cartesVictoire = VictoireCards.creerCartesVictoire(nomJoueurs.length);
        ArrayList<ArrayList<CoinsCards>> cartesTresor = CoinsCards.creerCartesTresor(nomJoueurs.length);


        Partie p = new Partie(EtapesTour.ACTION, cartesVictoire, cartesTresor);
        ArrayList<ArrayList<ActionCards>> cartesAction = ActionCards.creer10CartesAction(p, nomJoueurs.length);


        p.setCartesAction(cartesAction);
        p.setJoueurs(joueurs);
        return p;
    }






    public Partie(EtapesTour etapesTour, ArrayList<ArrayList<VictoireCards>> cartesVictoire,
                  ArrayList<ArrayList<CoinsCards>> cartesTresor) {

        this.etapesTour = etapesTour;

        this.listeCartesVictoire= cartesVictoire;
        this.listeCartesTresor = cartesTresor;

        /*listeDeToutesLesPiles = new ArrayList<>();
        listeDeToutesLesPiles.add(listeCartesVictoire);
        listeDeToutesLesPiles.add(listeCartesTresor);*/
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
    public boolean finDePartie() {
        if (listeCartesVictoire.get(2).size()==0) return true;


        int nombrePileVide = 0;

        for(ArrayList<ActionCards> array : listeCartesAction){
            if (array.size()==0)
                nombrePileVide++;
        }

        for(ArrayList<CoinsCards> array : listeCartesTresor){
            if (array.size()==0)
                nombrePileVide++;
        }

        for(ArrayList<VictoireCards> array : listeCartesVictoire){
            if (array.size()==0)
                nombrePileVide++;
        }

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

    public void finTourAction(){
        joueurCourrant.reduitTourAction();
        if(joueurCourrant.getNbTourAction() == 0){
            etapesTour = EtapesTour.ACHAT;
            joueurCourrant.setNbTourAction(1);

            Cards c;
            for(int i = 0; i<joueurCourrant.getSizeMain() ; i++){
                c = joueurCourrant.getCarteMain(i);
                if(c.isCarteTresor()){
                    joueurCourrant.addCoin(((CoinsCards)c).getPointCoin());
                }
            }
        }
    }

    public boolean finTourAchat(){
        joueurCourrant.reduitTourAchat();
        boolean finAchat = joueurCourrant.getNbTourAchat() == 0;
        if(finAchat){
            etapesTour = EtapesTour.ACTION;
            joueurCourrant.setNbTourAchat(1);
            joueurCourrant.setCoins(0);
            joueurCourrant.mainToDefausse();
            joueurCourrant.piocher(5);

            int idJoueurSuiv = (joueurCourrant.getNumero() == getNbJoueurs()-1)?0:joueurCourrant.getNumero()+1;
            joueurCourrant = joueurs[idJoueurSuiv];

        }
        return finAchat;
    }

    public void setCartesAction(ArrayList<ArrayList<ActionCards>> cartesAction) {
        this.listeCartesAction = cartesAction;
        //listeDeToutesLesPiles.add(listeCartesAction);
    }

    public String getIdAction(int index){
        return ""+listeCartesAction.get(index).get(0).getId();
    }

    public String getNomJoueur(int index){
        return ""+joueurs[index].getNomJoueur();
    }

    public EtapesTour getEtapesTour() {
        return etapesTour;
    }

    public int getNbRestantCartesVictoire(int i) {
        return listeCartesVictoire.get(i).size();
    }

    public int getNbRestantCartesAction(int i) {
        return listeCartesAction.get(i).size();
    }

    public int getNbRestantCartesTresor(int i) {
        return listeCartesTresor.get(i).size();
    }

    public int getIdPileCarteAction(int index){
        if(getNbRestantCartesAction(index) == 0){
            return -1;
        }

        return listeCartesAction.get(index).get(0).getId();
    }

    public void ecarteCarteActionSurPile(ActionCards carte, int index){
        joueurCourrant.removeCarteMainEcartee(carte);
        listeCartesAction.get(index).add(carte);
    }

    public boolean tryAchatCarteVictoire(int i) {
        Cards c = listeCartesVictoire.get(i).get(listeCartesVictoire.get(i).size() - 1);
        if(c.getCout()>joueurCourrant.getCoins()){
            return false;
        }
        listeCartesVictoire.get(i).remove(c);
        joueurCourrant.addCoin(-c.getCout());
        joueurCourrant.ajoutCarteMain(c);
        return true;
    }

    public boolean tryAchatCarteTresor(int i) {
        Cards c = listeCartesTresor.get(i).get(listeCartesTresor.get(i).size() - 1);
        if(c.getCout()>joueurCourrant.getCoins()){
            return false;
        }
        listeCartesTresor.get(i).remove(c);
        joueurCourrant.addCoin(-c.getCout());
        joueurCourrant.ajoutCarteMain(c);
        return true;
    }

    public boolean tryAchatCarteAction(int i) {
        Cards c = listeCartesAction.get(i).get(listeCartesAction.get(i).size() - 1);
        if(c.getCout()>joueurCourrant.getCoins()){
            return false;
        }
        listeCartesAction.get(i).remove(c);
        joueurCourrant.addCoin(-c.getCout());
        joueurCourrant.ajoutCarteMain(c);
        return true;
    }

    public int getNbJoueurs(){
        return joueurs.length;
    }
}
