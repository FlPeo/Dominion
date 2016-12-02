package dominion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by sakalypse on 20/11/16.
 */
public abstract class Cards {
    private int cout;
    private int id;

    public Cards(int id){
        this.id = id;
    }

    public Cards(int id, int cout){
        this(id);
        this.cout = cout;
    }

    public int getCout() {
        return cout;
    }

    public void setCout(int cout) {
        this.cout = cout;
    }

    public int getId() {
        return id;
    }
    /*List<Cards> deck;

    public Cards() {
        deck = new ArrayList<>();
    }

    public void melangeDeck(){
        Collections.shuffle(deck);
    }*/
}

