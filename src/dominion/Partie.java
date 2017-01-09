package dominion;

import dominion.Actions.Action;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by florian on 13/11/2016.
 */

public class Partie implements Serializable
{
    private Joueur[] joueurs;
    private Joueur joueurCourrant;
    private EtapesTour etapesTour;

    private ArrayList<ArrayList<VictoireCards>> listeCartesVictoire;
    private ArrayList<ArrayList<CoinsCards>> listeCartesTresor;
    private ArrayList<ArrayList<ActionCards>> listeCartesAction;
    private Joueur joueurAdverse;

    private ArrayList<Cards> cartesChoisiesParJoueur;
    private ArrayList<Action> listeActions;

    public static Partie creerPartie(String[] nomJoueurs) {
        Joueur[] joueurs = new Joueur[nomJoueurs.length];
        for (int i = 0; i < nomJoueurs.length; i++) {
            joueurs[i] = new Joueur(nomJoueurs[i], Joueur.createDeckDepart(), i);
            joueurs[i].piocher(5);
        }

        ArrayList<ArrayList<VictoireCards>> cartesVictoire = VictoireCards.creerCartesVictoire(nomJoueurs.length);
        ArrayList<ArrayList<CoinsCards>> cartesTresor = CoinsCards.creerCartesTresor(nomJoueurs.length);

        ArrayList<Cards> cartesChoisiesParJoueur = new ArrayList<Cards>();
        ArrayList<Action> listeActions = new ArrayList<Action>();

        Partie p = new Partie(EtapesTour.ACTION, cartesVictoire, cartesTresor, cartesChoisiesParJoueur, listeActions);
        ArrayList<ArrayList<ActionCards>> cartesAction = ActionCards.creer10CartesAction(p, nomJoueurs.length);

        p.setCartesAction(cartesAction);
        p.setJoueurs(joueurs);
        return p;
    }

    public Partie(EtapesTour etapesTour, ArrayList<ArrayList<VictoireCards>> cartesVictoire,
                  ArrayList<ArrayList<CoinsCards>> cartesTresor, ArrayList<Cards> cartesChoisiesParJoueur,
                    ArrayList<Action> listeActions) {

        this.listeActions = listeActions;
        this.cartesChoisiesParJoueur= cartesChoisiesParJoueur;

        this.etapesTour = etapesTour;
        this.listeCartesVictoire= cartesVictoire;
        this.listeCartesTresor = cartesTresor;
    }


    //les fins
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

    //achats
    public boolean tryAchatCarteVictoire(int i) {
        if(listeCartesVictoire.get(i).size() == 0) return false;

        Cards c = listeCartesVictoire.get(i).get(listeCartesVictoire.get(i).size()-1);
        if(c.getCout()>joueurCourrant.getCoins()){
            return false;
        }

        listeCartesVictoire.get(i).remove(c);
        joueurCourrant.addCoin(-c.getCout());
        joueurCourrant.ajoutCarteMain(c);
        return true;
    }

    public boolean tryAchatCarteTresor(int i) {
        if(listeCartesTresor.get(i).size() == 0) return false;

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
        if(listeCartesAction.get(i).size() == 0) return false;

        Cards c = listeCartesAction.get(i).get(listeCartesAction.get(i).size() - 1);
        if(c.getCout()>joueurCourrant.getCoins()){
            return false;
        }

        listeCartesAction.get(i).remove(c);
        joueurCourrant.addCoin(-c.getCout());
        joueurCourrant.ajoutCarteMain(c);
        return true;
    }

    //operations sur les piles de carte action
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

    //get et set
    public int getNbJoueurs(){
        return joueurs.length;
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
    public String getNomJoueur(int index){
        return ""+joueurs[index].getNomJoueur();
    }



    public void setCartesAction(ArrayList<ArrayList<ActionCards>> cartesAction) {
        this.listeCartesAction = cartesAction;
    }
    public String getIdAction(int index){
        return ""+listeCartesAction.get(index).get(0).getId();
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

    public Joueur[] getJoueurs(){
        return joueurs;
    }

    public EtapesTour getEtapesTour() {
        return etapesTour;
    }

    public ArrayList<ArrayList<VictoireCards>> getListeCartesVictoire() {
        return listeCartesVictoire;
    }

    public Joueur getJoueurAdverse() {
        for (int i = 0; i < joueurs.length; i++){
            if (!(joueurs[i].equals(joueurCourrant))){
                joueurAdverse = joueurs[i];
            }
        }

        return joueurAdverse;
    }

    public void addAction(Action a) {
        listeActions.add(a);
    }

    public void addAction(Action a, int index) {
        listeActions.add(index, a);
    }

    public void removeFirstAction(){
        listeActions.remove(0);
    }

    public void actions() {

        boolean bloque = false;

        int i = 0;

        for(int k = 0 ; k<listeActions.size() && !bloque; k++){
            Action a =listeActions.get(i);
            a.action();
            bloque = a.estBloquante();
            i++;
        }

        for(int j = 0; j<i; j++){
           listeActions.remove(0);
        }

        if(!bloque){
            finTourAction();
        }
    }

    public void sauvegarder() throws SauvegardeException{
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("sauvegarde/partie.object")));
            oos.writeObject(this);
            oos.close();
        }
        catch(FileNotFoundException fe){
            throw new SauvegardeException(SauvegardeException.TypeSauvegardeException.FICHIER_INEXISTANT);
        }
        catch(IOException ie){
            throw new SauvegardeException(SauvegardeException.TypeSauvegardeException.ECRITURE_IMPOSSIBLE);
        }
    }

    public static Partie getSauvegardePartie() throws SauvegardeException{
        Partie p;
        try{
            ObjectInputStream oos = new ObjectInputStream(new BufferedInputStream(new FileInputStream("sauvegarde/partie.object")));
            p = (Partie)oos.readObject();
            oos.close();
        }
        catch(FileNotFoundException fe){
            throw new SauvegardeException(SauvegardeException.TypeSauvegardeException.FICHIER_INEXISTANT);
        }
        catch(IOException ie){
            throw new SauvegardeException(SauvegardeException.TypeSauvegardeException.LECTURE_IMPOSSIBLE);
        }
        catch(ClassNotFoundException ce){
            throw new SauvegardeException(SauvegardeException.TypeSauvegardeException.LECTURE_IMPOSSIBLE);
        }

        return p;
    }

    public void setEtapesTour(EtapesTour etapesTour) {
        this.etapesTour = etapesTour;
    }

    public void addCarteChoisieParJoueur(Cards c){
        cartesChoisiesParJoueur.add(c);
    }

    public Cards getCarteChoisieParJoueur(int i){
        return cartesChoisiesParJoueur.get(i);
    }

    public void clearCarteChoisieParJoueur(){
        cartesChoisiesParJoueur.clear();
    }
}
