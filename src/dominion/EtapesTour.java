package dominion;

/**
 * Created by Flo on 06/12/2016.
 */
public enum EtapesTour {
    ACTION("Tour d'action"),
    ACHAT("Tour d'achat"),
    CHOIX_1_CARTE_DE_MAIN("Veuillez choisir une carte de votre main"),
    CHOIX_1_CARTE_TRESOR_DE_MAIN("Veuillez choisir une carte trésor de votre main"),
    CHOIX_1_CARTE_ACTION_DE_MAIN("Veuillez choisir une carte action de votre main"),
    CHOIX_JUSQUA_4_CARTES_DE_MAIN("Veuillez choisir jusqu'à quatre cartes de votre main"),
    DEFAUSSER_AUTANT_CARTES_MAIN_QUON_VEUT("Défaussez de votre main autant de cartes que vous voulez"),

    TOUS_ADVERSAIRES_DEFAUSSENT_POUR_PLUS_QUE_3_CARTES_EN_MAIN("Bouche trou"),
    TOUS_ADVERSAIRES_CHOIX_CARTE_VICTOIRE_DANS_MAIN("Bouche trou"),

    TOUS_ADVERSAIRES_DEVOILENT_2_PREMIERES_CARTES_DECK("Bouche trou"),
    TOUS_ADVERSAIRES_DEVOILENT_1ERE_CARTE_DECK("Bouche trou");

    private String indication;
    EtapesTour(String s) {
        indication = s;
    }

    public String getIndication() {
        return indication;
    }
}
