/**
 * Created by ndolo on 20/11/16.
 */
public class CardsAction {
    private boolean revele;
    private int pioche;
    private int addTourAction;
    private int addTourAchat;
    private int addRessource;
    private int defausse;
    private int deck;
    private int gold;
    public int main;
    private CardsAction caDedouble;


    public CardsAction(int pioche, int addTourAction, int addTourAchat,
                       int addRessource, int defausse, int deck,
                       int gold, int main, boolean revele) {
        this.pioche = pioche;
        this.addTourAction = addTourAction;
        this.addTourAchat = addTourAchat;
        this.addRessource = addRessource;
        this.defausse = defausse;
        this.deck = deck;
        this.gold = gold;
        this.main = main;
        this.revele = revele;
    }

    public void Action(){
        if (pioche != 0){
            pioche();
        }
    }

    public void pioche() {
        if (this.deck > 0) {
            this.deck-=1;
            this.main+=1;
        }
    }

    public int getPioche() {
        return pioche;
    }

    public void setPioche(int pioche) {
        this.pioche = pioche;
    }

    public int getDeck() {
        return deck;
    }

    public void setDeck(int deck) {
        this.deck = deck;
    }

    public void addGold() {
        this.gold++;
    }

    public int getGold() {
        return gold;
    }

    public void addGold2() {
        this.gold+=2;
    }

    public void defausse4() {
        this.main-=4;
    }

    public int getAddTourAction() {
        return this.addTourAction;
    }

    public void addAction() {
        this.addTourAction+=1;
    }

    public void addAchat() {
        this.addTourAchat+=1;
    }

    public int getAddTourAchat() {
        return this.addTourAchat;
    }

    public void defausse3() {
        this.main-=3;
    }

    public int getMain() {
        return this.main;
    }

    public void AddCarteTresor3FoisPlusGrande(int tresor) {
        this.gold += tresor*3;
    }

    public boolean isRevele() {
        return revele;
    }

    public int getAddRessource() {
        return addRessource;
    }

    public int getDefausse() {
        return defausse;
    }

    public void ReveleCarte() {
        this.revele = true;
    }

    public void dedouble() {
        this.caDedouble = this;
    }

    public CardsAction getCaDedouble() {
        return caDedouble;
    }
}