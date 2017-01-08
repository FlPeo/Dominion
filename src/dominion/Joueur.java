package dominion;

import javax.sql.rowset.CachedRowSet;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by mlucile on 22/11/16.
 */
public class Joueur implements Serializable
{
    private int numero;  //numero ordre pour partie. commence à 0

    private int coins;
    private int nbTourAction;
    private int nbTourAchat;
    private int nbDeCartesADefausser;
    private String nomJoueur;
    private List<Cards> main;
    private List<Cards> deck; // 0=fin, taille-1=debut
    private List<Cards> defausse;

    Joueur(String nomJoueur, List<Cards> deck, int numero){
        this.numero = numero;
        this.nomJoueur = nomJoueur;
        this.deck= deck;
        main = new ArrayList<Cards>();
        defausse = new ArrayList<Cards>();
        coins = 0;
        nbTourAction = 1;
        nbTourAchat = 1;
        nbDeCartesADefausser = 0;
    }




    //les add basiques
    public int addCoin(int nbAjout){
        coins += nbAjout;
        return coins;
    }

    public int addTourAction(int nbAjout){
        nbTourAction += nbAjout;
        return nbTourAction;
    }

    public int addTourAchat(int nbAjout){
        nbTourAchat += nbAjout;
        return nbTourAchat;
    }





    //les reductions de tours
    public void reduitTourAction(){
        nbTourAction--;
    }

    public void reduitTourAchat(){
        nbTourAchat--;
    }





    //operations sur la main


    public void removeCarteMainEcartee(Cards c){
        main.remove(c);
    }

    public void carteVaDeMainADefausse(int index){
        Cards c = main.remove(index);
        defausse.add(c);
    }

    public void carteVaDeMainADefausse(Cards c){
        main.remove(c);
        defausse.add(c);
    }






    //mouvements de carte

    public void piocher(int valeur) {
        Cards card;
        for (int i = 0; i < valeur; i++){
            if(deck.size()==0){
                defausseToDeck();
            }

            card = deck.get(deck.size() - 1);
            main.add(card);
            deck.remove(card);
        }
    }

    public void piocherMalediction(VictoireCards cardsMalediction) {
        if (!douveDansMain()) {
            main.add(cardsMalediction);
        }
    }

    public void defausseToDeck(){
        Collections.shuffle(defausse);
        for(Cards c : defausse){
            deck.add(c);
        }
        defausse.clear();
    }

    public void mainToDefausse(){
        for(Cards c : main){
            defausse.add(c);
        }
        main.clear();
    }

    // Envoie le deck entier à la défausse
    public void deckToDefausse(){
        for(Cards c : deck){
            defausse.add(c);
        }
        deck.clear();
    }


    //methode static de creation de carte
    public static List<Cards> createDeckDepart(){
        List<Cards> deck = new ArrayList<Cards>();

        for(int i = 0 ; i<7 ; i++){
            deck.add(new CoinsCards(0));
        }

        for(int i = 0 ; i<3 ; i++){
            deck.add(new VictoireCards(0));
        }

        Collections.shuffle(deck);

        return deck;
    }




    //get et set

    public String getNomJoueur() {
        return nomJoueur;
    }
    public String getCheminImageCarte(int index){
        return main.get(index).getCheminImage();
    }
    public int getNumero() {
        return numero;
    }

    public int getCoins() {
        return coins;
    }
    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getNbTourAction() {
        return nbTourAction;
    }
    public void setNbTourAction(int nbTourAction) {
        this.nbTourAction = nbTourAction;
    }

    public int getNbTourAchat() {
        return nbTourAchat;
    }
    public void setNbTourAchat(int nbTourAchat) {
        this.nbTourAchat = nbTourAchat;
    }

    public List<Cards> getDeck(){
        return deck;
    }
    public int getSizeDeck(){
        return deck.size();
    }
    public Cards getCarteDeck(int index){
        return deck.get(index);
    }    //il faudra voir si c'est utile

    public Cards getCarteMain(int index){
        return main.get(index);
    }
    public void ajoutCarteMain(Cards c){
        main.add(c);
    }
    public int getSizeMain(){
        return main.size();
    }

    public int getSizeDefausse(){
        return defausse.size();
    }

    //return la main
    public List<Cards> getMain(){
        return main;
    }

    public boolean douveDansMain(){
        for (Cards c : main){
            if (c.getId()==7) return true;
        }
        return false;
    }


}
