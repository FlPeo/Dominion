package dominion;


import java.io.Serializable;

/**
 * Created by sakalypse on 20/11/16.
 */
public abstract class Cards implements Serializable {
    protected int cout;
    protected int id;

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

    public String getCheminImage(){
        return "Images/";
    }

    public boolean isCarteAction(){
        return false;
    }

    public boolean isCarteVictoire(){
        return false;
    }

    public boolean isCarteTresor(){
        return false;
    }

}

