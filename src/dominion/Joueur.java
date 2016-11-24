package dominion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mlucile on 22/11/16.
 */
public class Joueur
{
    private int coins;
    private int nbTourAction;
    private int nbTourAchat;
    private int pointsVictoires;
    private int nbDeCartesADefausser;
    private String nomJoueur;
    private List<Cards> main;
    private List<Cards> deck;

    Joueur(String nomJoueur){
        this.nomJoueur = nomJoueur;
        coins = 1;
        nbTourAction = 1;
        nbTourAchat = 1;
        pointsVictoires = 0;
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

    public int addPoints(int nbAjout){
        pointsVictoires += nbAjout;
        return pointsVictoires;
    }

    public void piocher(int nbCarte){
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

    }

}
