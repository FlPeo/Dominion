package dominion;

/**
 * Created by Flo on 31/12/2016.
 */
public class SauvegardeException extends Exception{
    public enum TypeSauvegardeException{
        FICHIER_INEXISTANT,
        ECRITURE_IMPOSSIBLE,
        LECTURE_IMPOSSIBLE
    }

    private TypeSauvegardeException type;

    public SauvegardeException(TypeSauvegardeException t){
        super();
        type = t;
    }

    public TypeSauvegardeException getType() {
        return type;
    }
}
