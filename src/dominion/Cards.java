package dominion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by sakalypse on 20/11/16.
 */
public class Cards {
    List<Cards> deck;

    public Cards() {
        deck = new ArrayList<>();
    }

    public void melangeDeck(){
        Collections.shuffle(deck);
    }
}

