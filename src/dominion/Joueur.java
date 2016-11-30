package dominion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by mlucile on 22/11/16.
 */
public class Joueur
{
    private int coins;
    private int nbTourAction;
    private int nbTourAchat;
    private int nbDeCartesADefausser;
    private String nomJoueur;
    private List<Cards> main;
    private List<Cards> deck; // 0=fin, taille-1=debut
    private List<Cards> defausse;

    Joueur(String nomJoueur, List<Cards> deck){
        this.nomJoueur = nomJoueur;
        this.deck= deck;
        main = new ArrayList<Cards>();
        defausse = new ArrayList<Cards>();
        coins = 0;
        nbTourAction = 1;
        nbTourAchat = 1;
        nbDeCartesADefausser = 0;
    }

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

    /*public void piocher(int nbCarte){
        int taille;
        Cards card;
        for (int i = 0; i < nbCarte; i++){
            taille = deck.size();
            card = deck.get((int)Math.random()*(taille));
            main.add(card);
            deck.remove(card);
        }
    }

    public void defausser(){

    }*/

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

    public int getSizeMain(){
        return main.size();
    }

    public Cards getCarteMain(int index){
        return main.get(index);
    }

    public int getSizeDeck(){
        return deck.size();
    }

    public Cards getCarteDeck(int index){
        return deck.get(index);
    }

    public void piocher(int valeur) {
        Cards card;
        for (int i = 0; i < valeur; i++){
            card = deck.get(deck.size() - 1);
            main.add(card);
            deck.remove(card);
            if(deck.size()==0){
                defausseToDeck();
            }
        }
    }

    public void defausseToDeck(){
        Collections.shuffle(defausse);
        Collections.copy(deck, defausse);
        defausse = new ArrayList<Cards>();
    }
}
